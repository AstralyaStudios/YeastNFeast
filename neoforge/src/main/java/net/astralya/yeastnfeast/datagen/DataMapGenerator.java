package net.astralya.yeastnfeast.datagen;

import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings("deprecation")
public class DataMapGenerator extends DataMapProvider {
    protected DataMapGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        builder(NeoForgeDataMaps.COMPOSTABLES)
                // 30%
                .add(ModItems.BARLEY_SEEDS.get().asItem().builtInRegistryHolder(), new Compostable(0.3f), false)
                .add(ModItems.RYE_SEEDS.get().asItem().builtInRegistryHolder(), new Compostable(0.3f), false)
                .add(ModItems.MINT_SEEDS.get().asItem().builtInRegistryHolder(), new Compostable(0.3f), false)
                .add(ModItems.ELDERBERRIES.get().asItem().builtInRegistryHolder(), new Compostable(0.3f), false)
                .add(ModItems.HAWTHORN_BERRIES.get().asItem().builtInRegistryHolder(), new Compostable(0.3f), false)
                .add(ModItems.ROSE_HIPS.get().asItem().builtInRegistryHolder(), new Compostable(0.3f), false)
                .add(ModItems.LEMON.get().asItem().builtInRegistryHolder(), new Compostable(0.3f), false)
                .add(ModBlocks.HAWTHORN_SAPLING.get().asItem().builtInRegistryHolder(), new Compostable(0.3f), false)
                .add(ModBlocks.LEMON_SAPLING.get().asItem().builtInRegistryHolder(), new Compostable(0.3f), false)
                .add(ModBlocks.MAPLE_SAPLING.get().asItem().builtInRegistryHolder(), new Compostable(0.3f), false)
                .add(ModBlocks.FLOWERING_LEMON_TREE_LEAVES.get().asItem().builtInRegistryHolder(), new Compostable(0.3f), false)
                .add(ModBlocks.LEMON_TREE_LEAVES.get().asItem().builtInRegistryHolder(), new Compostable(0.3f), false)
                .add(ModBlocks.FLOWERING_HAWTHORN_TREE_LEAVES.get().asItem().builtInRegistryHolder(), new Compostable(0.3f), false)
                .add(ModBlocks.HAWTHORN_TREE_LEAVES.get().asItem().builtInRegistryHolder(), new Compostable(0.3f), false)

                // 50%
                .add(ModItems.GARLIC.get().asItem().builtInRegistryHolder(), new Compostable(0.6f), false)
                .add(ModItems.MINT.get().asItem().builtInRegistryHolder(), new Compostable(0.6f), false)
                .add(ModItems.GINGER.get().asItem().builtInRegistryHolder(), new Compostable(0.6f), false)
                .add(ModItems.BARLEY.get().asItem().builtInRegistryHolder(), new Compostable(0.6f), false)
                .add(ModItems.RYE.get().asItem().builtInRegistryHolder(), new Compostable(0.6f), false)
                .add(ModBlocks.THISTLE.get().asItem().builtInRegistryHolder(), new Compostable(0.6f), false)

                // 80%
                .add(ModBlocks.BARLEY_BLOCK.get().asItem().builtInRegistryHolder(), new Compostable(0.8f), false)
                .add(ModBlocks.RYE_BLOCK.get().asItem().builtInRegistryHolder(), new Compostable(0.8f), false)
                .add(ModItems.BARLEY_BREAD.get().asItem().builtInRegistryHolder(), new Compostable(0.8f), false)
                .add(ModItems.RYE_BREAD.get().asItem().builtInRegistryHolder(), new Compostable(0.8f), false)
                .add(ModItems.MOLASSES_BREAD.get().asItem().builtInRegistryHolder(), new Compostable(0.8f), false)

                // 100%
                .add(ModItems.BERRY_ROLL.get().asItem().builtInRegistryHolder(), new Compostable(1.0f), false)
                .add(ModItems.ROSE_TART.get().asItem().builtInRegistryHolder(), new Compostable(1.0f), false)
                .add(ModItems.ELDERBERRY_PIE.get().asItem().builtInRegistryHolder(), new Compostable(1.0f), false)
                .add(ModItems.APPLE_PIE.get().asItem().builtInRegistryHolder(), new Compostable(1.0f), false)
                .add(ModItems.MINTED_CHEESE_TART.get().asItem().builtInRegistryHolder(), new Compostable(1.0f), false)
                .add(ModItems.QUICHE.get().asItem().builtInRegistryHolder(), new Compostable(1.0f), false);
    }

}
