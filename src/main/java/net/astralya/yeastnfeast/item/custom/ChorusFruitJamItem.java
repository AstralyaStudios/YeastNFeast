package net.astralya.yeastnfeast.item.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ChorusFruitJamItem extends ConsumableItem {

    public ChorusFruitJamItem(Properties properties) {
        super(properties);
    }

    @Override
    protected void handleEffects(Level level, LivingEntity entity, ItemStack stack) {
        if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
            double x = entity.getX();
            double y = entity.getY();
            double z = entity.getZ();

            for (int i = 0; i < 16; ++i) {
                double targetX = x + (entity.getRandom().nextDouble() - 0.5) * 16.0;
                double targetY = Mth.clamp(y + entity.getRandom().nextInt(16) - 8,
                        level.getMinBuildHeight(),
                        level.getMinBuildHeight() + serverLevel.getLogicalHeight() - 1);
                double targetZ = z + (entity.getRandom().nextDouble() - 0.5) * 16.0;

                if (entity.isPassenger()) {
                    entity.stopRiding();
                }

                Vec3 oldPos = entity.position();
                if (entity.randomTeleport(targetX, targetY, targetZ, true)) {
                    level.playSound(null, x, y, z, SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
                    entity.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
                    break;
                }
            }

            if (entity instanceof Player player) {
                player.getCooldowns().addCooldown(this, 20);
            }
        }
    }
}