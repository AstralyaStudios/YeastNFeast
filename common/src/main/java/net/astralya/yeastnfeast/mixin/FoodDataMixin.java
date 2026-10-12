package net.astralya.yeastnfeast.mixin;

import net.astralya.yeastnfeast.effect.ModMobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoodData.class)
public class FoodDataMixin {
    @Shadow
    private int tickTimer;

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;getGameRules()Lnet/minecraft/world/level/GameRules;"))
    private void yeastnfeast$accelerateNaturalRegeneration(Player player, CallbackInfo ci) {
        FoodData foodData = (FoodData) (Object) this;
        if ((player.level().getGameTime() & 1L) == 0L
                && player.hasEffect(ModMobEffects.RECOVERY)
                && player.level().getGameRules().getBoolean(GameRules.RULE_NATURAL_REGENERATION)
                && player.isHurt() && foodData.getFoodLevel() >= 18) {
            tickTimer++;
        }
    }
}
