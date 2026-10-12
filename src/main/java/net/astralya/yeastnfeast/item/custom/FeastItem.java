package net.astralya.yeastnfeast.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.function.Supplier;

public class FeastItem extends AbstractConsumableItem {

    private final int duration;
    private final int amplifier;
    private final Component tooltip;
    private final Supplier<MobEffect> effectSupplier;

    public FeastItem(Properties properties, Supplier<MobEffect> effectSupplier, int duration, int amplifier, Component tooltip) {
        super(properties.stacksTo(1));
        this.effectSupplier = effectSupplier;
        this.duration = duration;
        this.amplifier = amplifier;
        this.tooltip = tooltip;
    }

    @Override
    protected void handleEffects(Level level, LivingEntity entity, ItemStack stack) {
        if (!level.isClientSide) {
            entity.addEffect(new MobEffectInstance(effectSupplier.get(), duration, amplifier));
        }
    }

    @Override
    protected ItemStack getReturnContainer(ItemStack stack) {
        return new ItemStack(Items.BOWL);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.EAT;
    }

    @Override
    protected Component getTooltip(ItemStack stack) {
        return tooltip;
    }
}