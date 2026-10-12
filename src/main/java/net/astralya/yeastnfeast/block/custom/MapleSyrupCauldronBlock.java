package net.astralya.yeastnfeast.block.custom;

import net.astralya.yeastnfeast.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.function.Predicate;

public class MapleSyrupCauldronBlock extends LayeredCauldronBlock {

    private static final Predicate<Biome.Precipitation> NO_PRECIPITATION = precipitation -> false;

    public MapleSyrupCauldronBlock(BlockBehaviour.Properties properties) {
        super(properties, NO_PRECIPITATION, CauldronInteraction.EMPTY);
        this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, 1));
    }

    @Override
    protected double getContentHeight(BlockState state) {
        return (6.0D + 3.0D * state.getValue(LEVEL)) / 16.0D;
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        return new ItemStack(Items.CAULDRON);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack held = player.getItemInHand(hand);

        if (held.is(Items.GLASS_BOTTLE) && state.getValue(LEVEL) > 0) {
            if (!level.isClientSide) {
                if (!player.getAbilities().instabuild) {
                    held.shrink(1);
                }

                ItemStack syrup = new ItemStack(ModItems.MAPLE_SYRUP.get());
                if (!player.addItem(syrup)) {
                    player.drop(syrup, false);
                }

                int newLevel = state.getValue(LEVEL) - 1;
                BlockState newState = (newLevel == 0)
                        ? Blocks.CAULDRON.defaultBlockState()
                        : state.setValue(LEVEL, newLevel);

                level.setBlockAndUpdate(pos, newState);
                level.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(newState));
            }

            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        return InteractionResult.PASS;
    }
}