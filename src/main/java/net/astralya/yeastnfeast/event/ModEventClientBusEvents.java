package net.astralya.yeastnfeast.event;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.block.entity.ModBlockEntityTypes;
import net.astralya.yeastnfeast.block.entity.renderer.CheesePressBlockEntityRenderer;
import net.astralya.yeastnfeast.particle.ModParticles;
import net.astralya.yeastnfeast.particle.custom.DrippingSyrupParticle;
import net.astralya.yeastnfeast.particle.custom.MapleLeavesParticle;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = YeastNFeastMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventClientBusEvents {

    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.MAPLE_LEAVES.get(), MapleLeavesParticle.Factory::new);
        event.registerSpriteSet(ModParticles.DRIPPING_SYRUP.get(), DrippingSyrupParticle.Factory::new);
    }

    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntityTypes.MOD_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntityTypes.CHEESE_PRESS.get(), CheesePressBlockEntityRenderer::new);
    }
}
