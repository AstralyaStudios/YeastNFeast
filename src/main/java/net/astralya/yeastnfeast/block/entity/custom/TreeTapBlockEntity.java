package net.astralya.yeastnfeast.block.entity.custom;

import net.astralya.yeastnfeast.Configuration;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.block.custom.TreeTapBlock;
import net.astralya.yeastnfeast.block.entity.ModBlockEntityTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class TreeTapBlockEntity extends SyncBlockEntity {

    private int tickCounter;

    public TreeTapBlockEntity(BlockPos pos, BlockState state) {
        super(getTypeOrThrow(), pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, TreeTapBlockEntity be) {
        if (!state.get(TreeTapBlock.DRIPPING)) {
            be.tickCounter++;
            if (be.tickCounter >= Configuration.get().treeTapDripDelay) {
                world.setBlockState(pos, state.with(TreeTapBlock.DRIPPING, true), Block.NOTIFY_ALL);
                be.tickCounter = 0;
                be.markDirty();
            }
            return;
        }

        BlockPos below = pos.down();
        BlockState belowState = world.getBlockState(below);

        if (belowState.isOf(Blocks.CAULDRON)) {
            BlockState ns = ModBlocks.MAPLE_SYRUP_CAULDRON
                    .getDefaultState()
                    .with(LeveledCauldronBlock.LEVEL, 1);

            world.setBlockState(below, ns, Block.NOTIFY_ALL);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, below, GameEvent.Emitter.of(ns));
            world.syncWorldEvent(1047, below, 0);
            world.playSound(null, below, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 0.7F, 1.0F);

            world.setBlockState(pos, state.with(TreeTapBlock.DRIPPING, false), Block.NOTIFY_ALL);
            be.tickCounter = 0;
            be.markDirty();
            return;
        }

        if (belowState.isOf(ModBlocks.MAPLE_SYRUP_CAULDRON)) {
            int lvl = belowState.get(LeveledCauldronBlock.LEVEL);
            if (lvl < 3) {
                BlockState ns = belowState.with(LeveledCauldronBlock.LEVEL, lvl + 1);

                world.setBlockState(below, ns, Block.NOTIFY_ALL);
                world.emitGameEvent(GameEvent.BLOCK_CHANGE, below, GameEvent.Emitter.of(ns));
                world.syncWorldEvent(1047, below, 0);
                world.playSound(null, below, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 0.6F, 1.1F);

                world.setBlockState(pos, state.with(TreeTapBlock.DRIPPING, false), Block.NOTIFY_ALL);
                be.tickCounter = 0;
                be.markDirty();
            }
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("tickCounter", tickCounter);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        tickCounter = nbt.getInt("tickCounter");
        super.readNbt(nbt);
    }

    private static BlockEntityType<TreeTapBlockEntity> getTypeOrThrow() {
        BlockEntityType<TreeTapBlockEntity> type = ModBlockEntityTypes.TREE_TAP;
        if (type == null) {
            throw new IllegalStateException("ModBlockEntityTypes.TREE_TAP is null");
        }
        return type;
    }
}
