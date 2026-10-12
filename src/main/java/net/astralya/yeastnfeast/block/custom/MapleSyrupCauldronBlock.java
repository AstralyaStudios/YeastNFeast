package net.astralya.yeastnfeast.block.custom;

import net.astralya.yeastnfeast.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class MapleSyrupCauldronBlock extends LeveledCauldronBlock {

    public MapleSyrupCauldronBlock(Settings settings) {
        super(settings, precipitation -> false, CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR);
        this.setDefaultState(this.getStateManager().getDefaultState().with(LEVEL, 1));
    }

    @Override
    public boolean isFull(BlockState state) {
        return state.get(LEVEL) == 3;
    }

    @Override
    protected double getFluidHeight(BlockState state) {
        return (6.0D + 3.0D * state.get(LEVEL)) / 16.0D;
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return new ItemStack(Items.CAULDRON);
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return state.get(LEVEL);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack held = player.getStackInHand(hand);

        if (held.isOf(Items.GLASS_BOTTLE) && state.get(LEVEL) > 0) {
            if (!world.isClient) {
                if (!player.getAbilities().creativeMode) {
                    held.decrement(1);
                }

                ItemStack syrup = new ItemStack(ModItems.MAPLE_SYRUP);
                if (!player.getInventory().insertStack(syrup)) {
                    player.dropItem(syrup, false);
                }

                int newLevel = state.get(LEVEL) - 1;
                BlockState newState = newLevel == 0 ? Blocks.CAULDRON.getDefaultState() : state.with(LEVEL, newLevel);

                world.setBlockState(pos, newState, Block.NOTIFY_ALL);
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, newState));
            }

            return ActionResult.success(world.isClient);
        }

        return ActionResult.PASS;
    }
}