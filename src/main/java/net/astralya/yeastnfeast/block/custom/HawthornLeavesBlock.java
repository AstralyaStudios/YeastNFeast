package net.astralya.yeastnfeast.block.custom;

import net.astralya.yeastnfeast.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.OptionalInt;

public class HawthornLeavesBlock extends Block implements BonemealableBlock, SimpleWaterloggedBlock {

    public static final int MAX_AGE = 2;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 2);

    public static final int MAX_DISTANCE = 7;
    public static final IntegerProperty DISTANCE = BlockStateProperties.DISTANCE;
    public static final BooleanProperty PERSISTENT = BlockStateProperties.PERSISTENT;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public HawthornLeavesBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(AGE, 0)
                .setValue(DISTANCE, 7)
                .setValue(PERSISTENT, false)
                .setValue(WATERLOGGED, false));
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        int age = state.getValue(AGE);
        ItemStack itemStack = player.getItemInHand(hand);

        if (age == MAX_AGE) {
            int berryCount = 1 + level.random.nextInt(2);
            popResource(level, pos, new ItemStack(ModItems.HAWTHORN_BERRIES.get(), berryCount + 1));
            level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
            level.setBlock(pos, state.setValue(AGE, 0), Block.UPDATE_CLIENTS);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, state.setValue(AGE, 1)));
            return InteractionResult.SUCCESS;
        } else if (itemStack.is(Items.BONE_MEAL)) {
            return InteractionResult.PASS;
        } else {
            return super.use(state, level, pos, player, hand, hit);
        }
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        return pState.getValue(AGE) < MAX_AGE;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        int age = Math.min(MAX_AGE, state.getValue(AGE) + 1);
        level.setBlock(pos, state.setValue(AGE, age), 2);
    }

    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    protected boolean shouldDecay(BlockState state) {
        return !state.getValue(PERSISTENT) && state.getValue(DISTANCE) == 7;
    }

    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        level.setBlock(pos, updateDistanceFromLogs(state, level, pos), 3);
    }

    public int getLightBlock(BlockState state, BlockGetter level, BlockPos pos) {
        return 1;
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < MAX_AGE || (state.getValue(DISTANCE) == 7 && !state.getValue(PERSISTENT));
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (this.shouldDecay(state)) {
            dropResources(state, level, pos);
            level.removeBlock(pos, false);
            return;
        }

        if (state.getValue(AGE) < MAX_AGE && random.nextInt(5) == 0 && level.getRawBrightness(pos.above(), 0) >= 9) {
            level.setBlock(pos, state.setValue(AGE, state.getValue(AGE) + 1), 2);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(state.setValue(AGE, state.getValue(AGE) + 1)));
        }
    }

    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        int i = getDistanceFromLog(neighborState) + 1;
        if (i != 1 || state.getValue(DISTANCE) != i) {
            level.scheduleTick(pos, this, 1);
        }

        return state;
    }

    private static BlockState updateDistanceFromLogs(BlockState state, LevelAccessor level, BlockPos pos) {
        int i = 7;
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
        Direction[] directions = Direction.values();

        for (Direction direction : directions) {
            mutablePos.setWithOffset(pos, direction);
            i = Math.min(i, getDistanceFromLog(level.getBlockState(mutablePos)) + 1);
            if (i == 1) {
                break;
            }
        }

        return state.setValue(DISTANCE, i);
    }

    private static int getDistanceFromLog(BlockState state) {
        return getOptionalDistanceFromLog(state).orElse(7);
    }

    public static OptionalInt getOptionalDistanceFromLog(BlockState state) {
        if (state.is(BlockTags.LOGS)) {
            return OptionalInt.of(0);
        } else {
            return state.hasProperty(DISTANCE) ? OptionalInt.of(state.getValue(DISTANCE)) : OptionalInt.empty();
        }
    }

    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (level.isRainingAt(pos.above())) {
            if (random.nextInt(15) == 1) {
                BlockPos blockPos = pos.below();
                BlockState blockState = level.getBlockState(blockPos);
                if (!blockState.canOcclude() || !blockState.isFaceSturdy(level, blockPos, Direction.UP)) {
                    double d = (double)pos.getX() + random.nextDouble();
                    double e = (double)pos.getY() - 0.05;
                    double f = (double)pos.getZ() + random.nextDouble();
                    level.addParticle(net.minecraft.core.particles.ParticleTypes.DRIPPING_WATER, d, e, f, 0.0, 0.0, 0.0);
                }
            }
        }
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
        BlockState blockState = this.defaultBlockState()
                .setValue(PERSISTENT, true)
                .setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER)
                .setValue(AGE, 0);
        return updateDistanceFromLogs(blockState, context.getLevel(), context.getClickedPos());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE, DISTANCE, PERSISTENT, WATERLOGGED);
    }
}