package net.astralya.yeastnfeast.effect;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.effect.custom.VigorousEffect;
import net.astralya.yeastnfeast.effect.custom.OverfedEffect;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEffects {

    public static final StatusEffect OVERFED = registerStatusEffect("overfed",
            new OverfedEffect(StatusEffectCategory.BENEFICIAL, 0xDCD789).addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED,
                    "BF8B6E3F-3328-4C0A-AA66-3BA6BB6DBEF6", -0.1f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));

    public static final StatusEffect VIGOROUS = registerStatusEffect("vigorous",
            new VigorousEffect(StatusEffectCategory.BENEFICIAL, 0x90C19A));

    private static StatusEffect registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(YeastNFeastMod.MODID, name), statusEffect);
    }
    public static void registerEffects() {
        YeastNFeastMod.LOGGER.info("Registering Potion Effects for " + YeastNFeastMod.MODID);
    }
}
