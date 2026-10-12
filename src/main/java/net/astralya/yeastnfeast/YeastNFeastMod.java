package net.astralya.yeastnfeast;

import com.mojang.logging.LogUtils;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.block.entity.ModBlockEntityTypes;
import net.astralya.yeastnfeast.effect.ModMobEffects;
import net.astralya.yeastnfeast.entity.ModEntities;
import net.astralya.yeastnfeast.entity.client.ModBoatRenderer;
import net.astralya.yeastnfeast.item.ModCreativeModeTabs;
import net.astralya.yeastnfeast.item.ModItems;
import net.astralya.yeastnfeast.particle.ModParticles;
import net.astralya.yeastnfeast.recipe.ModRecipes;
import net.astralya.yeastnfeast.screen.KegScreen;
import net.astralya.yeastnfeast.screen.ModMenuTypes;
import net.astralya.yeastnfeast.util.ModRegistries;
import net.astralya.yeastnfeast.util.ModWoodTypes;
import net.astralya.yeastnfeast.villager.ModVillagers;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(YeastNFeastMod.MODID)
public class YeastNFeastMod {
    public static final String MODID = "yeastnfeast";
    private static final Logger LOGGER = LogUtils.getLogger();

    public YeastNFeastMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);

        ModItems.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModMobEffects.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModEntities.register(modEventBus);
        ModBlockEntityTypes.register(modEventBus);
        ModRecipes.register(modEventBus);
        ModParticles.register(modEventBus);
        ModVillagers.register(modEventBus);
        Configuration.register();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModRegistries.registerCompostables();
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.MAPLE_SAPLING.getId(), ModBlocks.POTTED_MAPLE_SAPLING);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.LEMON_SAPLING.getId(), ModBlocks.POTTED_LEMON_SAPLING);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.HAWTHORN_SAPLING.getId(), ModBlocks.POTTED_HAWTHORN_SAPLING);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.THISTLE.getId(), ModBlocks.POTTED_THISTLE);
        });
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == ModCreativeModeTabs.YEASTNFEAST_TAB.get()) {
            if (ModList.get().isLoaded("hexalia")) {
                event.accept(ModItems.CHILLBERRIES_JAM);
                event.accept(ModItems.FROSTGALE_MEAD);
                event.accept(ModItems.SUNFIRE_TOMATO_BRUSCHETTA);
                event.accept(ModItems.STUFFED_MANDRAKE);
            }
            if (ModList.get().isLoaded("farmersdelight")) {
                event.accept(ModItems.GARDEN_SOUP);
                event.accept(ModItems.GINGER_TEA);
                event.accept(ModItems.SPICED_FLATBREAD);
            }
            if (ModList.get().isLoaded("farm_and_charm")) {
                event.accept(ModItems.STRAWBERRIES_JAM);
                event.accept(ModItems.MAPLE_RYE_PANCAKES);
                event.accept(ModItems.ELDERBERRIES_OATMEAL);
            }

            /*if (ModList.get().isLoaded("patchouli")) {
                event.accept(ModItems.HOMESTEADERS_HANDBOOK);
            }*/
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

            EntityRenderers.register(ModEntities.MOD_BOAT.get(), pContext -> new ModBoatRenderer(pContext, false));
            EntityRenderers.register(ModEntities.MOD_CHEST_BOAT.get(), pContext -> new ModBoatRenderer(pContext, true));

            Sheets.addWoodType(ModWoodTypes.MAPLE);

            MenuScreens.register(ModMenuTypes.KEG_MENU.get(), KegScreen::new);

        }
    }
}