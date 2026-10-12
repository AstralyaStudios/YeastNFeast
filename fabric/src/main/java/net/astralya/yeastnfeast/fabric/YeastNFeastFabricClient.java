package net.astralya.yeastnfeast.fabric;

import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.block.entity.ModBlockEntityTypes;
import net.astralya.yeastnfeast.block.entity.renderer.CheesePressBlockEntityRenderer;
import net.astralya.yeastnfeast.block.entity.renderer.FeastTableBlockEntityRenderer;
import net.astralya.yeastnfeast.entity.ModEntities;
import net.astralya.yeastnfeast.entity.boat.ModBoatRenderer;
import net.astralya.yeastnfeast.entity.layers.ModModelLayers;
import net.astralya.yeastnfeast.particle.ModParticleType;
import net.astralya.yeastnfeast.particle.custom.DrippingSyrupParticle;
import net.astralya.yeastnfeast.particle.custom.MapleLeavesParticle;
import net.astralya.yeastnfeast.particle.custom.FermentationBubbleParticle;
import net.astralya.yeastnfeast.screen.ModMenuTypes;
import net.astralya.yeastnfeast.screen.custom.KegScreen;
import net.astralya.yeastnfeast.util.ModWoodTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;

public final class YeastNFeastFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(),
                ModBlocks.WILD_BARLEY.get(), ModBlocks.WILD_RYE.get(), ModBlocks.WILD_GINGER.get(),
                ModBlocks.WILD_GARLIC.get(), ModBlocks.BARLEY_CROP.get(), ModBlocks.RYE_CROP.get(),
                ModBlocks.GINGER_CROP.get(), ModBlocks.GARLIC_CROP.get(), ModBlocks.MINT_CROP.get(),
                ModBlocks.ROSE_HIPS_BUSH.get(), ModBlocks.ELDERBERRY_BUSH.get(), ModBlocks.MAPLE_SAPLING.get(),
                ModBlocks.POTTED_MAPLE_SAPLING.get(), ModBlocks.MAPLE_DOOR.get(), ModBlocks.MAPLE_TRAPDOOR.get(),
                ModBlocks.LEMON_SAPLING.get(), ModBlocks.POTTED_LEMON_SAPLING.get(), ModBlocks.LEMON_TREE_LEAVES.get(),
                ModBlocks.FLOWERING_LEMON_TREE_LEAVES.get(), ModBlocks.HAWTHORN_SAPLING.get(),
                ModBlocks.POTTED_HAWTHORN_SAPLING.get(), ModBlocks.HAWTHORN_TREE_LEAVES.get(),
                ModBlocks.FLOWERING_HAWTHORN_TREE_LEAVES.get(), ModBlocks.THISTLE.get(),
                ModBlocks.POTTED_THISTLE.get(), ModBlocks.CHEESE_PRESS.get());

        ParticleFactoryRegistry.getInstance().register(ModParticleType.DRIPPING_SYRUP.get(), DrippingSyrupParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticleType.MAPLE_LEAVES.get(), MapleLeavesParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticleType.FERMENTATION_BUBBLES.get(), FermentationBubbleParticle.Factory::new);

        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.MAPLE_BOAT_LAYER, BoatModel::createBodyModel);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.MAPLE_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);
        EntityRendererRegistry.register(ModEntities.MOD_BOAT.get(), context -> new ModBoatRenderer(context, false));
        EntityRendererRegistry.register(ModEntities.MOD_CHEST_BOAT.get(), context -> new ModBoatRenderer(context, true));

        BlockEntityRenderers.register(ModBlockEntityTypes.MOD_SIGN.get(), SignRenderer::new);
        BlockEntityRenderers.register(ModBlockEntityTypes.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
        BlockEntityRenderers.register(ModBlockEntityTypes.CHEESE_PRESS.get(), CheesePressBlockEntityRenderer::new);
        BlockEntityRenderers.register(ModBlockEntityTypes.FEAST_TABLE.get(), FeastTableBlockEntityRenderer::new);
        ModelLoadingPlugin.register(context -> context.addModels(FeastTableBlockEntityRenderer.PLATE_MODEL));
        MenuScreens.register(ModMenuTypes.KEG_MENU.get(), KegScreen::new);
        Sheets.SIGN_MATERIALS.put(ModWoodTypes.MAPLE, Sheets.getSignMaterial(ModWoodTypes.MAPLE));
        Sheets.HANGING_SIGN_MATERIALS.put(ModWoodTypes.MAPLE, Sheets.getHangingSignMaterial(ModWoodTypes.MAPLE));
    }
}
