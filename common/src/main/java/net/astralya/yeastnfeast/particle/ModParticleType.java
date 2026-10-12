package net.astralya.yeastnfeast.particle;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import dev.architectury.registry.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModParticleType {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(YeastNFeastMod.MODID, Registries.PARTICLE_TYPE);

    public static final Supplier<SimpleParticleType> MAPLE_LEAVES = PARTICLE_TYPES.register("maple_leaves",
            () -> new SimpleParticleType(true) {});

    public static final Supplier<SimpleParticleType> DRIPPING_SYRUP = PARTICLE_TYPES.register("dripping_syrup",
            () -> new SimpleParticleType(true) {});

    public static final Supplier<SimpleParticleType> FERMENTATION_BUBBLES = PARTICLE_TYPES.register("fermentation_bubbles",
            () -> new SimpleParticleType(false) {});

    public static void init() {
        PARTICLE_TYPES.register();
    }

}

