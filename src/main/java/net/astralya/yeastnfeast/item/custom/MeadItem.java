package net.astralya.yeastnfeast.item.custom;

import net.astralya.yeastnfeast.item.ModItems;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.UseAction;

import java.util.function.Supplier;

public class MeadItem extends FeastItem {

    public MeadItem(Settings settings, Supplier<StatusEffect> effectSupplier, int duration, int amplifier, Text tooltip) {
        super(settings.maxCount(8), effectSupplier, duration, amplifier, tooltip);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 40;
    }

    @Override
    public SoundEvent getEatSound() {
        return SoundEvents.ENTITY_GENERIC_DRINK;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    protected ItemStack getReturnContainer(ItemStack stack) {
        return new ItemStack(ModItems.TANKARD);
    }
}