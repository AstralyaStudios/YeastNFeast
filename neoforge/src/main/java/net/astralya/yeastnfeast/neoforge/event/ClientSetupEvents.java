package net.astralya.yeastnfeast.neoforge.event;

import net.astralya.yeastnfeast.particle.ModParticleType;
import net.astralya.yeastnfeast.particle.custom.DrippingSyrupParticle;
import net.astralya.yeastnfeast.particle.custom.MapleLeavesParticle;
import net.astralya.yeastnfeast.particle.custom.FermentationBubbleParticle;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

public class ClientSetupEvents {

    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticleType.MAPLE_LEAVES.get(), MapleLeavesParticle.Factory::new);
        event.registerSpriteSet(ModParticleType.DRIPPING_SYRUP.get(), DrippingSyrupParticle.Factory::new);
        event.registerSpriteSet(ModParticleType.FERMENTATION_BUBBLES.get(), FermentationBubbleParticle.Factory::new);
    }
}
