package net.astralya.yeastnfeast.item.custom;

import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.block.custom.MapleSyrupCauldronBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class MapleSyrupItem extends ConsumableItem {

    public MapleSyrupItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext ctx) {
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        BlockState state = world.getBlockState(pos);

        if (world.isClient) {
            return ActionResult.SUCCESS;
        }

        PlayerEntity player = ctx.getPlayer();
        if (player == null) {
            return ActionResult.PASS;
        }

        Block mapleSyrupCauldron = ModBlocks.MAPLE_SYRUP_CAULDRON;
        if (state.isOf(mapleSyrupCauldron)) {
            int lvl = state.get(MapleSyrupCauldronBlock.LEVEL);
            if (lvl >= 3) {
                return ActionResult.PASS;
            }

            BlockState ns = state.with(MapleSyrupCauldronBlock.LEVEL, lvl + 1);
            world.setBlockState(pos, ns, Block.NOTIFY_ALL);
            world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, ns));
            world.syncWorldEvent(1047, pos, 0);
            consumeAndReturnGlass(ctx, player);
            return ActionResult.SUCCESS;
        }

        if (state.isOf(Blocks.CAULDRON)) {
            BlockState ns = mapleSyrupCauldron.getDefaultState().with(LeveledCauldronBlock.LEVEL, 1);
            world.setBlockState(pos, ns, Block.NOTIFY_ALL);
            world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, ns));
            world.syncWorldEvent(1047, pos, 0);
            consumeAndReturnGlass(ctx, player);
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    private void consumeAndReturnGlass(ItemUsageContext ctx, PlayerEntity player) {
        ItemStack stack = ctx.getStack();
        if (!player.isCreative()) {
            stack.decrement(1);
        }

        ItemStack empty = new ItemStack(Items.GLASS_BOTTLE);
        if (!player.getInventory().insertStack(empty)) {
            player.dropItem(empty, false);
        }
    }
}