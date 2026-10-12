package net.astralya.yeastnfeast.effect;

import net.minecraft.core.registries.Registries;
import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.effect.custom.OverfedEffect;
import net.astralya.yeastnfeast.effect.custom.MeadEffect;
import net.astralya.yeastnfeast.effect.custom.VigorousEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import dev.architectury.registry.registries.DeferredRegister;

public class ModMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(YeastNFeastMod.MODID, Registries.MOB_EFFECT);

    public static final Holder<MobEffect> OVERFED = MOB_EFFECTS.register("overfed",
            () -> new OverfedEffect(MobEffectCategory.NEUTRAL, 0x36ebab).addAttributeModifier(Attributes.MOVEMENT_SPEED,
                    ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "overfed"), -0.1f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public static final Holder<MobEffect> VIGOROUS = MOB_EFFECTS.register("vigorous",
            () -> new VigorousEffect(MobEffectCategory.BENEFICIAL, 0x90C19A, 0).addAttributeModifier(Attributes.MAX_HEALTH,
                    ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "vigorous"), 6.0f, AttributeModifier.Operation.ADD_VALUE));

    public static final Holder<MobEffect> RECOVERY = MOB_EFFECTS.register("recovery",
            () -> new MeadEffect(MobEffectCategory.BENEFICIAL, 0xD979A7));

    public static final Holder<MobEffect> RESILIENCE = MOB_EFFECTS.register("resilience",
            () -> new MeadEffect(MobEffectCategory.BENEFICIAL, 0xE3A83B).addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE,
                    ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "resilience"), 0.2f, AttributeModifier.Operation.ADD_VALUE));

    public static final Holder<MobEffect> INVIGORATION = MOB_EFFECTS.register("invigoration",
            () -> new MeadEffect(MobEffectCategory.BENEFICIAL, 0xB8523E)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "invigoration_movement_speed"),
                            0.1f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(Attributes.ATTACK_SPEED, ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "invigoration_attack_speed"),
                            0.1f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public static void init() {
        MOB_EFFECTS.register();
    }
}

