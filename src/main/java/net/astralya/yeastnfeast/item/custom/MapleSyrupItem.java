package net.astralya.yeastnfeast.item.custom;

import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.block.custom.MapleSyrupCauldronBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class MapleSyrupItem extends ConsumableItem {
    public MapleSyrupItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        Level level = ctx.getLevel();
        BlockPos pos = ctx.getClickedPos();
        BlockState state = level.getBlockState(pos);

        if (level.isClientSide) return InteractionResult.SUCCESS;

        if (state.is(ModBlocks.MAPLE_SYRUP_CAULDRON.get())) {
            int lvl = state.getValue(MapleSyrupCauldronBlock.LEVEL);
            if (lvl >= 3) return InteractionResult.PASS;
            BlockState ns = state.setValue(MapleSyrupCauldronBlock.LEVEL, lvl + 1);
            level.setBlockAndUpdate(pos, ns);
            level.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(ns));
            level.levelEvent(1047, pos, 0);
            consumeAndReturnGlass(ctx);
            return InteractionResult.SUCCESS;
        }

        if (state.is(Blocks.CAULDRON)) {
            BlockState ns = ModBlocks.MAPLE_SYRUP_CAULDRON.get()
                    .defaultBlockState()
                    .setValue(LayeredCauldronBlock.LEVEL, 1);
            level.setBlockAndUpdate(pos, ns);
            level.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(ns));
            level.levelEvent(1047, pos, 0);
            consumeAndReturnGlass(ctx);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    private void consumeAndReturnGlass(UseOnContext ctx) {
        ItemStack stack = ctx.getItemInHand();
        if (!ctx.getPlayer().getAbilities().instabuild) stack.shrink(1);
        ItemStack empty = new ItemStack(Items.GLASS_BOTTLE);
        if (!ctx.getPlayer().addItem(empty)) ctx.getPlayer().drop(empty, false);
    }
}