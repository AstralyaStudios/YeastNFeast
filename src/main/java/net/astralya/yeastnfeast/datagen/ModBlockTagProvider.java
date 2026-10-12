package net.astralya.yeastnfeast.datagen;

import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        addMiningTags();
        addPlantTags();
        addTreeRelatedTags();
        addCustomModTags();
        addAdditionalTags();
        addSereneSeasonsTags();
    }

    private void addMiningTags() {
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(ModBlocks.TREE_TAP)
                .add(ModBlocks.CHEESE_PRESS)
                .add(ModBlocks.KEG);

        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)
                .addTag(ModTags.Blocks.STORAGE_BLOCKS);

        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .addTag(ModTags.Blocks.STORAGE_BLOCKS)
                .add(ModBlocks.TREE_TAP)
                .add(ModBlocks.CHEESE_PRESS)
                .add(ModBlocks.KEG);
    }

    private void addPlantTags() {
        getOrCreateTagBuilder(BlockTags.CROPS)
                .add(ModBlocks.BARLEY_CROP)
                .add(ModBlocks.RYE_CROP)
                .add(ModBlocks.GARLIC_CROP)
                .add(ModBlocks.GINGER_CROP)
                .add(ModBlocks.MINT_CROP);

        getOrCreateTagBuilder(BlockTags.BEE_GROWABLES)
                .add(ModBlocks.BARLEY_CROP)
                .add(ModBlocks.RYE_CROP)
                .add(ModBlocks.GARLIC_CROP)
                .add(ModBlocks.GINGER_CROP)
                .add(ModBlocks.MINT_CROP);

        getOrCreateTagBuilder(BlockTags.SAPLINGS)
                .add(ModBlocks.LEMON_SAPLING)
                .add(ModBlocks.HAWTHORN_SAPLING)
                .add(ModBlocks.MAPLE_SAPLING);
    }

    private void addCustomModTags() {
        getOrCreateTagBuilder(ModTags.Blocks.STORAGE_BLOCKS)
                .add(ModBlocks.BAG_OF_ELDERBERRIES)
                .add(ModBlocks.BAG_OF_GARLIC)
                .add(ModBlocks.BAG_OF_GINGER)
                .add(ModBlocks.BAG_OF_HAWTHORN_BERRIES)
                .add(ModBlocks.BAG_OF_LEMON)
                .add(ModBlocks.BAG_OF_MINT)
                .add(ModBlocks.BAG_OF_ROSE_HIPS)
                .add(ModBlocks.BAG_OF_THISTLE)
                .add(ModBlocks.RYE_BLOCK)
                .add(ModBlocks.BARLEY_BLOCK);
    }

    private void addTreeRelatedTags() {
        getOrCreateTagBuilder(ModTags.Blocks.MAPLE_LOGS)
                .add(ModBlocks.MAPLE_LOG)
                .add(ModBlocks.STRIPPED_MAPLE_LOG)
                .add(ModBlocks.MAPLE_WOOD)
                .add(ModBlocks.STRIPPED_MAPLE_WOOD);

        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .addTag(ModTags.Blocks.MAPLE_LOGS);

        getOrCreateTagBuilder(BlockTags.LEAVES)
                .add(ModBlocks.MAPLE_LEAVES);

        getOrCreateTagBuilder(BlockTags.PLANKS)
                .add(ModBlocks.MAPLE_PLANKS);

        getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS)
                .add(ModBlocks.MAPLE_STAIRS);

        getOrCreateTagBuilder(BlockTags.WOODEN_SLABS)
                .add(ModBlocks.MAPLE_SLAB);

        getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS)
                .add(ModBlocks.MAPLE_BUTTON);

        getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.MAPLE_PRESSURE_PLATE);

        getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.MAPLE_TRAPDOOR);

        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(ModBlocks.MAPLE_FENCE_GATE);

        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.MAPLE_FENCE);

        getOrCreateTagBuilder(BlockTags.WOODEN_DOORS)
                .add(ModBlocks.MAPLE_DOOR);

        getOrCreateTagBuilder(BlockTags.SIGNS)
                .add(ModBlocks.MAPLE_SIGN)
                .add(ModBlocks.MAPLE_WALL_SIGN);

        getOrCreateTagBuilder(BlockTags.ALL_HANGING_SIGNS)
                .add(ModBlocks.MAPLE_HANGING_SIGN)
                .add(ModBlocks.MAPLE_HANGING_WALL_SIGN);
    }

    private void addAdditionalTags() {
        getOrCreateTagBuilder(BlockTags.FLOWER_POTS)
                .add(ModBlocks.POTTED_LEMON_SAPLING)
                .add(ModBlocks.POTTED_HAWTHORN_SAPLING)
                .add(ModBlocks.POTTED_THISTLE)
                .add(ModBlocks.POTTED_MAPLE_SAPLING);
    }

    private void addSereneSeasonsTags() {
        getOrCreateTagBuilder(ModTags.Compat.SERENE_SEASONS_SPRING_CROPS_BLOCK)
                .add(ModBlocks.BARLEY_CROP)
                .add(ModBlocks.MINT_CROP);

        getOrCreateTagBuilder(ModTags.Compat.SERENE_SEASONS_SUMMER_CROPS_BLOCK)
                .add(ModBlocks.GINGER_CROP)
                .add(ModBlocks.MINT_CROP);

        getOrCreateTagBuilder(ModTags.Compat.SERENE_SEASONS_AUTUMN_CROPS_BLOCK)
                .add(ModBlocks.RYE_CROP);

        getOrCreateTagBuilder(ModTags.Compat.SERENE_SEASONS_WINTER_CROPS_BLOCK)
                .add(ModBlocks.GARLIC_CROP);

        getOrCreateTagBuilder(ModTags.Compat.SERENE_SEASONS_UNBREAKABLE_FERTILE_CROPS)
                .add(ModBlocks.MINT_CROP);
    }
}