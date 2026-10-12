package net.astralya.yeastnfeast.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.function.Supplier;

public class RenewablePlantBlock extends FlowerBlock {

    public RenewablePlantBlock(Supplier<MobEffect> effectSupplier, int pEffectDuration, Properties pProperties) {
        super(effectSupplier, pEffectDuration, pProperties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.is(Items.BONE_MEAL)) {
            if (!level.isClientSide()) {
                popResource(level, pos, new ItemStack(this));
                if (!player.isCreative()) {
                    itemStack.shrink(1);
                }
                level.playSound(null, pos, SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS, 1.0f, 1.0f);
                ((ServerLevel) level).sendParticles(ParticleTypes.HAPPY_VILLAGER, pos.getX() + 0.5, pos.getY() + 1,
                        pos.getZ() + 0.5, 10, 0.25, 0.25, 0.25, 0.05);
                return InteractionResult.SUCCESS;
            } else {
                level.playSound(player, pos, SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS, 1.0f, 1.0f);
                level.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX() + 0.5, pos.getY() + 1.0,
                        pos.getZ() + 0.5, 0.0, 0.0, 0.0);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }
}
