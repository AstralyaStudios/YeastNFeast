package net.astralya.yeastnfeast.datagen;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import dev.architectury.registry.registries.RegistrySupplier;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, YeastNFeastMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        basicItem(ModItems.YEAST.get());
        basicItem(ModItems.MAPLE_SYRUP.get());
        basicItem(ModItems.MOLASSES.get());
        basicItem(ModItems.MILK_BOTTLE.get());
        basicItem(ModItems.RENNET.get());

        basicItem(ModItems.MINT_SEEDS.get());
        basicItem(ModItems.BARLEY_SEEDS.get());
        basicItem(ModItems.RYE_SEEDS.get());
        basicItem(ModItems.ELDERBERRIES.get());
        basicItem(ModItems.HAWTHORN_BERRIES.get());
        basicItem(ModItems.ROSE_HIPS.get());
        basicItem(ModItems.LEMON.get());
        basicItem(ModItems.MINT.get());
        basicItem(ModItems.GINGER.get());
        basicItem(ModItems.GARLIC.get());
        basicItem(ModItems.BARLEY.get());
        basicItem(ModItems.RYE.get());

        basicItem(ModItems.SWEET_PORRIDGE.get());
        basicItem(ModItems.SPICED_PORRIDGE.get());
        basicItem(ModItems.BARLEY_AND_BEEF_STEW.get());
        basicItem(ModItems.SALMON_CHOWDER.get());
        basicItem(ModItems.MEAD_BRAISED_PORK.get());
        basicItem(ModItems.HERBAL_COD.get());
        basicItem(ModItems.LEMON_GLAZED_CHICKEN.get());
        basicItem(ModItems.FORAGER_FEAST.get());
        basicItem(ModItems.MAPLE_GLAZED_RABBIT.get());
        basicItem(ModItems.BARLEY_BREAD.get());
        basicItem(ModItems.RYE_BREAD.get());
        basicItem(ModItems.MOLASSES_BREAD.get());
        basicItem(ModItems.BERRY_ROLL.get());
        basicItem(ModItems.ROSE_TART.get());
        basicItem(ModItems.ELDERBERRY_PIE.get());
        basicItem(ModItems.APPLE_PIE.get());
        basicItem(ModItems.STUFFED_RYE_DUMPLINGS.get());
        basicItem(ModItems.MINTED_CHEESE_TART.get());
        basicItem(ModItems.DUSKWHEEL_SKEWER.get());
        basicItem(ModItems.QUICHE.get());
        basicItem(ModItems.CHEESE_SOUP.get());

        basicItem(ModItems.TANKARD.get());
        basicItem(ModItems.HONEY_MEAD.get());
        basicItem(ModItems.MOLASSES_MEAD.get());
        basicItem(ModItems.SOUR_MEAD.get());
        basicItem(ModItems.THORNBERRY_MEAD.get());
        basicItem(ModItems.BLOSSOM_MEAD.get());
        basicItem(ModItems.AMBER_MEAD.get());

        basicItem(ModItems.JAR.get());
        basicItem(ModItems.APPLE_JAM.get());
        basicItem(ModItems.CHORUS_FRUIT_JAM.get());
        basicItem(ModItems.ELDERBERRIES_JAM.get());
        basicItem(ModItems.GLOW_BERRIES_JAM.get());
        basicItem(ModItems.GOLDEN_APPLE_JAM.get());
        basicItem(ModItems.HAWTHORN_BERRIES_JAM.get());
        basicItem(ModItems.LEMON_JAM.get());
        basicItem(ModItems.MELON_JAM.get());
        basicItem(ModItems.ROSE_HIPS_JAM.get());
        basicItem(ModItems.SWEET_BERRIES_JAM.get());

        basicItem(ModItems.CHEESE_WHEEL.get());
        basicItem(ModItems.CHEESE_SLICE.get());
        basicItem(ModItems.DUSKWHEEL.get());
        basicItem(ModItems.DUSKWHEEL_SLICE.get());
        basicItem(ModItems.SHARPWHEEL.get());
        basicItem(ModItems.SHARPWHEEL_SLICE.get());
        basicItem(ModItems.FRESHWHEEL.get());
        basicItem(ModItems.FRESHWHEEL_SLICE.get());

        buttonItem(ModBlocks.MAPLE_BUTTON, ModBlocks.MAPLE_PLANKS);
        fenceItem(ModBlocks.MAPLE_FENCE, ModBlocks.MAPLE_PLANKS);
        basicItem(ModBlocks.MAPLE_DOOR.get().asItem());
        basicItem(ModItems.MAPLE_BOAT.get());
        basicItem(ModItems.MAPLE_CHEST_BOAT.get());

        // basicItem(ModItems.HOMESTEADERS_HANDBOOK.get());
    }

    public void buttonItem(RegistrySupplier<Block> block, RegistrySupplier<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture",
                        ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID,
                                "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(RegistrySupplier<Block> block, RegistrySupplier<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",
                        ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID,
                                "block/" + baseBlock.getId().getPath()));
    }
}
