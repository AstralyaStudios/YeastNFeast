package net.astralya.yeastnfeast.effect.custom;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class VigorousEffect extends MobEffect {

    protected final double modifier;

    public VigorousEffect(MobEffectCategory category, int color, double modifier) {
        super(category, color);
        this.modifier = modifier;
    }

    public double adjustModifierAmount(int amplifier, AttributeModifier modifier) {
        return this.modifier * (double)(amplifier + 1);
    }
}