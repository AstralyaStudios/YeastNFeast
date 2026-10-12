package net.astralya.yeastnfeast.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.function.Supplier;

public abstract class BaseCheeseWheelBlock extends Block {

    public static final IntProperty CUTS = IntProperty.of("cuts", 0, 3);

    private final Supplier<Item> sliceItem;

    protected BaseCheeseWheelBlock(Settings settings, Supplier<Item> sliceItem) {
        super(settings);
        this.sliceItem = sliceItem;
        this.setDefaultState(this.getStateManager().getDefaultState().with(CUTS, 0));
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    protected abstract VoxelShape shapeForCuts(int cuts);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int cuts = MathHelper.clamp(state.get(CUTS), 0, 3);
        return shapeForCuts(cuts);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int cuts = MathHelper.clamp(state.get(CUTS), 0, 3);
        return shapeForCuts(cuts);
    }

    @Override
    public VoxelShape getCullingShape(BlockState state, BlockView world, BlockPos pos) {
        int cuts = MathHelper.clamp(state.get(CUTS), 0, 3);
        return shapeForCuts(cuts);
    }

    @Override
    public VoxelShape getRaycastShape(BlockState state, BlockView world, BlockPos pos) {
        int cuts = MathHelper.clamp(state.get(CUTS), 0, 3);
        return shapeForCuts(cuts);
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return 4 - state.get(CUTS);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return takeSlice(state, world, pos, player);
    }

    private ActionResult takeSlice(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        }

        Item slice = sliceItem.get();
        if (slice == null) {
            return ActionResult.PASS;
        }

        ItemStack give = new ItemStack(slice);
        if (give.isEmpty()) {
            return ActionResult.PASS;
        }

        if (!player.getInventory().insertStack(give)) {
            player.dropItem(give, false);
        }

        world.playSound(null, pos, SoundEvents.ENTITY_ITEM_FRAME_REMOVE_ITEM, SoundCategory.BLOCKS, 0.6f, 1.0f);

        int cuts = state.get(CUTS);
        if (cuts < 3) {
            BlockState updated = state.with(CUTS, cuts + 1);
            world.setBlockState(pos, updated, Block.NOTIFY_ALL);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, updated));
        } else {
            world.breakBlock(pos, false);
            world.emitGameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Emitter.of(player));
        }

        return ActionResult.SUCCESS;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CUTS);
    }
}