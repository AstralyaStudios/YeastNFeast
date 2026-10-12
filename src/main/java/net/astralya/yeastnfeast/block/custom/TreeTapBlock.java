package net.astralya.yeastnfeast.block.custom;

import net.astralya.yeastnfeast.block.entity.ModBlockEntityTypes;
import net.astralya.yeastnfeast.block.entity.custom.TreeTapBlockEntity;
import net.astralya.yeastnfeast.item.ModItems;
import net.astralya.yeastnfeast.particle.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class TreeTapBlock extends BaseEntityBlock {

    public static final BooleanProperty DRIPPING = BooleanProperty.create("dripping");
    public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.Plane.HORIZONTAL);

    private static final VoxelShape NORTH_WALL_SHAPE = Block.box(5.0, 4.0, 10.0, 11.0, 12.0, 16.0);
    private static final VoxelShape SOUTH_WALL_SHAPE = Block.box(5.0, 4.0, 0.0, 11.0, 12.0, 6.0);
    private static final VoxelShape WEST_WALL_SHAPE = Block.box(10.0, 4.0, 5.0, 16.0, 12.0, 11.0);
    private static final VoxelShape EAST_WALL_SHAPE = Block.box(0.0, 4.0, 5.0, 6.0, 12.0, 11.0);

    public TreeTapBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(DRIPPING, false));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (direction.getOpposite() == state.getValue(FACING) && !state.canSurvive(level, pos)) {
            return Blocks.AIR.defaultBlockState();
        }
        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        return switch (direction) {
            case SOUTH -> SOUTH_WALL_SHAPE;
            case WEST -> WEST_WALL_SHAPE;
            case EAST -> EAST_WALL_SHAPE;
            default -> NORTH_WALL_SHAPE;
        };
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState blockBehind = level.getBlockState(pos.relative(state.getValue(FACING).getOpposite()));
        String blockId = blockBehind.getBlock().getDescriptionId();

        return blockId.contains("maple_log") || blockId.contains("maple_wood");
    }


    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (state.getValue(DRIPPING) && player.getItemInHand(hand).is(Items.GLASS_BOTTLE)) {
            player.getItemInHand(hand).shrink(1);
            player.addItem(new ItemStack(ModItems.MAPLE_SYRUP.get()));
            level.setBlock(pos, state.setValue(DRIPPING, false), 3);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (state.getValue(DRIPPING)) {
            if (level.isClientSide && level.random.nextInt(2) == 0) {
                Direction facing = state.getValue(FACING);
                double x = pos.getX() + 0.5;
                double y = pos.getY() + 0.5;
                double z = pos.getZ() + 0.5;

                switch (facing) {
                    case NORTH:
                        z = pos.getZ() + 0.8;
                        break;
                    case SOUTH:
                        z = pos.getZ() + 0.2;
                        break;
                    case WEST:
                        x = pos.getX() + 0.8;
                        break;
                    case EAST:
                        x = pos.getX() + 0.2;
                        break;
                }
                level.addParticle(ModParticles.DRIPPING_SYRUP.get(), x, y, z, 0.0, 0.0, 0.0);
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, DRIPPING);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TreeTapBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide) {
            return null;
        }
        return type == ModBlockEntityTypes.TREE_TAP.get() ? (level1, pos, state1, blockEntity) -> {
            if (blockEntity instanceof TreeTapBlockEntity treeTap) {
                TreeTapBlockEntity.tick(level1, pos, state1, treeTap);
            }
        } : null;
    }
}