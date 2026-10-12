package net.astralya.yeastnfeast.neoforge;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.block.ModFlammables;
import net.astralya.yeastnfeast.block.entity.ModBlockEntityTypes;
import net.astralya.yeastnfeast.block.entity.renderer.CheesePressBlockEntityRenderer;
import net.astralya.yeastnfeast.block.entity.renderer.FeastTableBlockEntityRenderer;
import net.astralya.yeastnfeast.entity.ModEntities;
import net.astralya.yeastnfeast.entity.boat.ModBoatRenderer;
import net.astralya.yeastnfeast.datagen.DataGenerators;
import net.astralya.yeastnfeast.neoforge.event.ClientSetupEvents;
import net.astralya.yeastnfeast.neoforge.event.ModEventBusSubscriber;
import net.astralya.yeastnfeast.neoforge.event.ModVillagerEvents;
import net.astralya.yeastnfeast.screen.ModMenuTypes;
import net.astralya.yeastnfeast.screen.custom.KegScreen;
import net.astralya.yeastnfeast.util.ModWoodTypes;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@Mod(YeastNFeastMod.MODID)
public final class YeastNFeastNeoForge {
    public YeastNFeastNeoForge(IEventBus modEventBus) {
        YeastNFeastMod.init();
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(DataGenerators::gatherData);
        modEventBus.addListener(ModEventBusSubscriber::registerCapabilities);
        NeoForge.EVENT_BUS.addListener(ModVillagerEvents::onVillagerTrades);
        if (FMLEnvironment.dist == Dist.CLIENT) {
            modEventBus.addListener(ClientEvents::registerRenderers);
            modEventBus.addListener(ClientEvents::registerAdditionalModels);
            modEventBus.addListener(ClientEvents::registerScreens);
            modEventBus.addListener(EventPriority.LOWEST, ClientSetupEvents::registerParticles);
            modEventBus.addListener(ModEventBusSubscriber::registerLayers);
        }
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(ModFlammables::register);
    }

    public static final class ClientEvents {
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            EntityRenderers.register(ModEntities.MOD_BOAT.get(), context -> new ModBoatRenderer(context, false));
            EntityRenderers.register(ModEntities.MOD_CHEST_BOAT.get(), context -> new ModBoatRenderer(context, true));
            event.registerBlockEntityRenderer(ModBlockEntityTypes.MOD_SIGN.get(), SignRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntityTypes.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntityTypes.CHEESE_PRESS.get(), CheesePressBlockEntityRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntityTypes.FEAST_TABLE.get(), FeastTableBlockEntityRenderer::new);
            Sheets.addWoodType(ModWoodTypes.MAPLE);
        }

        public static void registerAdditionalModels(ModelEvent.RegisterAdditional event) {
            event.register(new ModelResourceLocation(FeastTableBlockEntityRenderer.PLATE_MODEL, "standalone"));
        }

        public static void registerScreens(RegisterMenuScreensEvent event) {
            event.register(ModMenuTypes.KEG_MENU.get(), KegScreen::new);
        }
    }
}
