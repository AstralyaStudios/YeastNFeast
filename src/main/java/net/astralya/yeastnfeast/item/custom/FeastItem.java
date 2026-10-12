package net.astralya.yeastnfeast.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

import java.util.function.Supplier;

public class FeastItem extends AbstractConsumableItem {

    private final int duration;
    private final int amplifier;
    private final Text tooltip;
    private final Supplier<StatusEffect> effectSupplier;

    public FeastItem(Settings settings, Supplier<StatusEffect> effectSupplier, int duration, int amplifier, Text tooltip) {
        super(settings.maxCount(1));
        this.effectSupplier = effectSupplier;
        this.duration = duration;
        this.amplifier = amplifier;
        this.tooltip = tooltip;
    }

    @Override
    protected void handleEffects(World world, LivingEntity entity, ItemStack stack) {
        if (!world.isClient) {
            entity.addStatusEffect(new StatusEffectInstance(effectSupplier.get(), duration, amplifier));
        }
    }

    @Override
    protected ItemStack getReturnContainer(ItemStack consumedStack) {
        return new ItemStack(Items.BOWL);
    }

    @Override
    public SoundEvent getEatSound() {
        return SoundEvents.ENTITY_GENERIC_EAT;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.EAT;
    }

    @Override
    protected Text getTooltip(ItemStack stack) {
        return tooltip;
    }
}