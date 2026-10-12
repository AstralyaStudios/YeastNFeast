package net.astralya.yeastnfeast;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import net.astralya.yeastnfeast.block.entity.renderer.CheesePressBlockEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.block.entity.ModBlockEntityTypes;
import net.astralya.yeastnfeast.entity.ModBoats;
import net.astralya.yeastnfeast.particle.ModParticles;
import net.astralya.yeastnfeast.particle.custom.MapleLeavesParticle;
import net.astralya.yeastnfeast.particle.custom.DrippingSyrupParticle;
import net.astralya.yeastnfeast.screen.KegScreen;
import net.astralya.yeastnfeast.screen.ModScreenHandler;
import net.astralya.yeastnfeast.util.ModWoodTypes;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.block.entity.HangingSignBlockEntityRenderer;
import net.minecraft.client.render.block.entity.SignBlockEntityRenderer;

public class YeastNFeastModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        registerBlockRenderLayers();
        registerBlockEntityRenderers();
        registerWoodTypes();
        registerParticles();
        registerScreens();
    }

    private void registerBlockRenderLayers(){
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                ModBlocks.WILD_BARLEY, ModBlocks.WILD_RYE,
                ModBlocks.WILD_GINGER, ModBlocks.BARLEY_CROP,
                ModBlocks.RYE_CROP, ModBlocks.GINGER_CROP,
                ModBlocks.ROSE_HIPS_BUSH, ModBlocks.GARLIC_CROP,
                ModBlocks.ELDERBERRY_BUSH, ModBlocks.MINT_CROP,
                ModBlocks.MAPLE_SAPLING, ModBlocks.POTTED_MAPLE_SAPLING,
                ModBlocks.MAPLE_DOOR, ModBlocks.MAPLE_TRAPDOOR,
                ModBlocks.FLOWERING_LEMON_TREE_LEAVES, ModBlocks.LEMON_TREE_LEAVES,
                ModBlocks.LEMON_SAPLING, ModBlocks.POTTED_MAPLE_SAPLING,
                ModBlocks.FLOWERING_HAWTHORN_TREE_LEAVES, ModBlocks.HAWTHORN_TREE_LEAVES,
                ModBlocks.HAWTHORN_SAPLING, ModBlocks.POTTED_HAWTHORN_SAPLING,
                ModBlocks.WILD_GARLIC, ModBlocks.THISTLE,
                ModBlocks.POTTED_THISTLE, ModBlocks.CHEESE_PRESS
                );
    }

    private void registerBlockEntityRenderers() {
        BlockEntityRendererFactories.register(ModBlockEntityTypes.MOD_SIGN, SignBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntityTypes.MOD_HANGING_SIGN, HangingSignBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntityTypes.CHEESE_PRESS, CheesePressBlockEntityRenderer::new);
    }

    private void registerWoodTypes() {
        TexturedRenderLayers.SIGN_TYPE_TEXTURES.put(ModWoodTypes.MAPLE,
                TexturedRenderLayers.getSignTextureId(ModWoodTypes.MAPLE));
        TerraformBoatClientHelper.registerModelLayers(ModBoats.MAPLE_BOAT_ID, false);
    }

    private void registerParticles() {
        ParticleFactoryRegistry.getInstance().register(ModParticles.MAPLE_LEAVES, MapleLeavesParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.SYRUP_DRIPPING, DrippingSyrupParticle.Factory::new);
    }

    private void registerScreens() {
        HandledScreens.register(ModScreenHandler.KEG_SCREEN_HANDLER, KegScreen::new);
    }
}
