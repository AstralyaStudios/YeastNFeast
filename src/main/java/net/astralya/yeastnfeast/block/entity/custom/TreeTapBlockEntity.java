package net.astralya.yeastnfeast.block.entity.custom;

import net.astralya.yeastnfeast.Configuration;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.block.custom.TreeTapBlock;
import net.astralya.yeastnfeast.block.entity.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class TreeTapBlockEntity extends SyncBlockEntity {

    private int tickCounter = 0;

    public TreeTapBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntityTypes.TREE_TAP.get(), pos, blockState);
    }

    public static void tick(Level world, BlockPos pos, BlockState state, TreeTapBlockEntity be) {
        if (!state.getValue(TreeTapBlock.DRIPPING)) {
            be.tickCounter++;
            if (be.tickCounter >= Configuration.TREE_TAP_DRIP_DELAY.get()) {
                world.setBlock(pos, state.setValue(TreeTapBlock.DRIPPING, true), 3);
                be.tickCounter = 0;
            }
            return;
        }

        BlockPos below = pos.below();
        BlockState belowState = world.getBlockState(below);

        if (belowState.is(Blocks.CAULDRON)) {
            BlockState ns = ModBlocks.MAPLE_SYRUP_CAULDRON.get()
                    .defaultBlockState()
                    .setValue(LayeredCauldronBlock.LEVEL, 1);

            world.setBlockAndUpdate(below, ns);
            world.gameEvent(GameEvent.BLOCK_CHANGE, below, GameEvent.Context.of(ns));
            world.levelEvent(1047, below, 0);
            world.playSound(null, below, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 0.7F, 1.0F);

            world.setBlock(pos, state.setValue(TreeTapBlock.DRIPPING, false), 3);
            be.tickCounter = 0;
            be.setChanged();
            return;
        }

        if (belowState.is(ModBlocks.MAPLE_SYRUP_CAULDRON.get())) {
            int lvl = belowState.getValue(LayeredCauldronBlock.LEVEL);
            if (lvl < 3) {
                BlockState ns = belowState.setValue(LayeredCauldronBlock.LEVEL, lvl + 1);

                world.setBlockAndUpdate(below, ns);
                world.gameEvent(GameEvent.BLOCK_CHANGE, below, GameEvent.Context.of(ns));
                world.levelEvent(1047, below, 0);
                world.playSound(null, below, SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 0.6F, 1.1F);

                world.setBlock(pos, state.setValue(TreeTapBlock.DRIPPING, false), 3);
                be.tickCounter = 0;
                be.setChanged();
            }
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("tickCounter", tickCounter);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        tickCounter = tag.getInt("tickCounter");
    }
}