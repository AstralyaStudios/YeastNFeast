package net.astralya.yeastnfeast.mixin;

import net.astralya.yeastnfeast.effect.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class HealingMixin {
    @Inject(method = "heal", at = @At("HEAD"), cancellable = true)
    private void modifyHealing(float amount, CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (entity.hasStatusEffect(ModEffects.VIGOROUS)) {
            StatusEffectInstance effectInstance = entity.getStatusEffect(ModEffects.VIGOROUS);
            if (effectInstance != null) {
                int amplifier = effectInstance.getAmplifier();
                float boost = amount * (0.25f + (0.25f * amplifier));
                entity.setHealth(Math.min(entity.getMaxHealth(), entity.getHealth() + boost));
                ci.cancel();
            }
        }
    }
}
