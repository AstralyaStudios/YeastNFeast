package net.astralya.yeastnfeast.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Supplier;

public abstract class BaseCheeseWheelBlock extends Block {

    public static final IntegerProperty CUTS = IntegerProperty.create("cuts", 0, 3);

    private final Supplier<Item> sliceItem;

    protected BaseCheeseWheelBlock(BlockBehaviour.Properties properties, Supplier<Item> sliceItem) {
        super(properties);
        this.sliceItem = sliceItem;
        this.registerDefaultState(this.stateDefinition.any().setValue(CUTS, 0));
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    protected abstract VoxelShape shapeForCuts(int cuts);

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        int cuts = Mth.clamp(state.getValue(CUTS), 0, 3);
        return shapeForCuts(cuts);
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        int cuts = Mth.clamp(state.getValue(CUTS), 0, 3);
        return shapeForCuts(cuts);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        int cuts = Mth.clamp(state.getValue(CUTS), 0, 3);
        return shapeForCuts(cuts);
    }

    @Override
    public VoxelShape getOcclusionShape(BlockState state, BlockGetter level, BlockPos pos) {
        int cuts = Mth.clamp(state.getValue(CUTS), 0, 3);
        return shapeForCuts(cuts);
    }

    @Override
    public VoxelShape getInteractionShape(BlockState state, BlockGetter level, BlockPos pos) {
        int cuts = Mth.clamp(state.getValue(CUTS), 0, 3);
        return shapeForCuts(cuts);
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return 4 - state.getValue(CUTS);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        return takeSlice(state, level, pos, player);
    }

    private InteractionResult takeSlice(BlockState state, Level level, BlockPos pos, Player player) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        Item slice = sliceItem.get();
        if (slice == null) {
            return InteractionResult.PASS;
        }

        ItemStack give = new ItemStack(slice);
        if (give.isEmpty()) {
            return InteractionResult.PASS;
        }

        if (!player.addItem(give)) {
            player.drop(give, false);
        }

        level.playSound(null, pos, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 0.6f, 1.0f);

        int cuts = state.getValue(CUTS);
        if (cuts < 3) {
            BlockState updated = state.setValue(CUTS, cuts + 1);
            level.setBlock(pos, updated, 3);
            level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
        } else {
            level.removeBlock(pos, false);
            level.gameEvent(player, GameEvent.BLOCK_DESTROY, pos);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CUTS);
    }
}