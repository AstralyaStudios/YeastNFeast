package net.astralya.yeastnfeast.particle;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, YeastNFeastMod.MODID);

    public static final RegistryObject<SimpleParticleType> MAPLE_LEAVES =
            PARTICLE_TYPES.register("maple_leaves", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> DRIPPING_SYRUP =
            PARTICLE_TYPES.register("dripping_syrup", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }

}
