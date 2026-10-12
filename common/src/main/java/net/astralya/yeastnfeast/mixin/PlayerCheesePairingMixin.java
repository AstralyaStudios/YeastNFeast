package net.astralya.yeastnfeast.mixin;

import net.astralya.yeastnfeast.pairing.CheesePairingManager;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class PlayerCheesePairingMixin {
    @Inject(method = "tick", at = @At("TAIL"))
    private void yeastnfeast$tickCheesePairing(CallbackInfo ci) {
        Player player = (Player) (Object) this;
        if (!player.level().isClientSide()) {
            CheesePairingManager.tick(player);
        }
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void yeastnfeast$saveCheesePairing(CompoundTag tag, CallbackInfo ci) {
        CheesePairingManager.save((Player) (Object) this, tag);
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void yeastnfeast$loadCheesePairing(CompoundTag tag, CallbackInfo ci) {
        CheesePairingManager.load((Player) (Object) this, tag);
    }

    @Inject(method = "die", at = @At("HEAD"))
    private void yeastnfeast$clearCheesePairing(DamageSource damageSource, CallbackInfo ci) {
        CheesePairingManager.clear((Player) (Object) this);
    }
}
