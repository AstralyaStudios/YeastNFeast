package net.astralya.yeastnfeast.item.custom;

import net.astralya.yeastnfeast.item.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.*;

import java.util.function.Supplier;

public class MeadItem extends FeastItem {

    public MeadItem(Properties properties, Supplier<MobEffect> effectSupplier, int duration, int amplifier, Component tooltip) {
        super(properties.stacksTo(8), effectSupplier, duration, amplifier, tooltip);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 40;
    }

    @Override
    public SoundEvent getEatingSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    @Override
    protected ItemStack getReturnContainer(ItemStack stack) {
        return new ItemStack(ModItems.TANKARD.get());
    }
}