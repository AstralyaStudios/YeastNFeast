package net.astralya.yeastnfeast.effect.custom;

import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.LivingEntity;

import java.util.Objects;
import java.util.UUID;

public class VigorousEffect extends StatusEffect {

    private static final UUID HEALTH_BOOST_ID = UUID.fromString("7c7b5f69-45a3-4e6f-b0a2-9db8cf8c94b7");

    public VigorousEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        if (!entity.getWorld().isClient()) {
            Objects.requireNonNull(entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH))
                    .addTemporaryModifier(new EntityAttributeModifier(HEALTH_BOOST_ID,
                            "Vigorous Health Bonus", 6.0 * (amplifier + 1),
                            EntityAttributeModifier.Operation.ADDITION));
        }
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        if (!entity.getWorld().isClient()) {
            Objects.requireNonNull(entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH))
                    .removeModifier(HEALTH_BOOST_ID);
        }
    }
}
