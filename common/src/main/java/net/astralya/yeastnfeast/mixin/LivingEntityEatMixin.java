package net.astralya.yeastnfeast.mixin;

import net.astralya.yeastnfeast.pairing.CheesePairingManager;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityEatMixin {
    @Inject(method = "eat(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/food/FoodProperties;)Lnet/minecraft/world/item/ItemStack;", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;consume(ILnet/minecraft/world/entity/LivingEntity;)V"))
    private void yeastnfeast$handleCheesePairing(Level level, ItemStack stack, FoodProperties foodProperties,
                                                 CallbackInfoReturnable<ItemStack> cir) {
        if (!level.isClientSide() && (Object) this instanceof Player player) {
            CheesePairingManager.onFoodConsumed(player, stack, foodProperties);
        }
    }
}
