package net.astralya.yeastnfeast.datagen;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, YeastNFeastMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        addMiningTags();
        addPlantTags();
        addTreeRelatedTags();
        addCustomModTags();
        addAdditionalTags();
        addSereneSeasonsTags();
    }

    private void addMiningTags() {
        ModBlocks.FEAST_TABLES.forEach(table -> tag(BlockTags.MINEABLE_WITH_AXE).add(table.get()));

        // Axe mineable blocks
        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.TREE_TAP.get())
                .add(ModBlocks.CHEESE_PRESS.get())
                .add(ModBlocks.KEG.get());

        // Hoe mineable blocks
        tag(BlockTags.MINEABLE_WITH_HOE)
                .addTag(ModTags.Blocks.STORAGE_BLOCKS);

        // Tool requirements
        tag(BlockTags.NEEDS_STONE_TOOL)
                .addTag(ModTags.Blocks.STORAGE_BLOCKS)
                .add(ModBlocks.TREE_TAP.get())
                .add(ModBlocks.CHEESE_PRESS.get())
                .add(ModBlocks.KEG.get());
    }

    private void addPlantTags() {
        // Crops
        tag(BlockTags.CROPS)
                .add(ModBlocks.BARLEY_CROP.get())
                .add(ModBlocks.RYE_CROP.get())
                .add(ModBlocks.GARLIC_CROP.get())
                .add(ModBlocks.GINGER_CROP.get())
                .add(ModBlocks.MINT_CROP.get());

        // Bee Growable
        tag(BlockTags.BEE_GROWABLES)
                .add(ModBlocks.BARLEY_CROP.get())
                .add(ModBlocks.RYE_CROP.get())
                .add(ModBlocks.GARLIC_CROP.get())
                .add(ModBlocks.GINGER_CROP.get())
                .add(ModBlocks.MINT_CROP.get());

        // Saplings
        tag(BlockTags.SAPLINGS)
                .add(ModBlocks.LEMON_SAPLING.get())
                .add(ModBlocks.HAWTHORN_SAPLING.get())
                .add(ModBlocks.MAPLE_SAPLING.get());
    }

    private void addCustomModTags() {
        // Storage Blocks
        tag(ModTags.Blocks.STORAGE_BLOCKS)
                .add(ModBlocks.BAG_OF_ELDERBERRIES.get())
                .add(ModBlocks.BAG_OF_GARLIC.get())
                .add(ModBlocks.BAG_OF_GINGER.get())
                .add(ModBlocks.BAG_OF_HAWTHORN_BERRIES.get())
                .add(ModBlocks.BAG_OF_LEMON.get())
                .add(ModBlocks.BAG_OF_MINT.get())
                .add(ModBlocks.BAG_OF_ROSE_HIPS.get())
                .add(ModBlocks.BAG_OF_THISTLE.get())
                .add(ModBlocks.RYE_BLOCK.get())
                .add(ModBlocks.BARLEY_BLOCK.get());
    }

    private void addTreeRelatedTags() {
       // Maple
        tag(ModTags.Blocks.MAPLE_LOGS)
                .add(ModBlocks.MAPLE_LOG.get())
                .add(ModBlocks.STRIPPED_MAPLE_LOG.get())
                .add(ModBlocks.MAPLE_WOOD.get())
                .add(ModBlocks.STRIPPED_MAPLE_WOOD.get());

        // Vanilla wood categories
        tag(BlockTags.LOGS_THAT_BURN)
                .addTag(ModTags.Blocks.MAPLE_LOGS);

        tag(BlockTags.LEAVES)
                .add(ModBlocks.MAPLE_LEAVES.get());

        // Wooden items
        tag(BlockTags.PLANKS)
                .add(ModBlocks.MAPLE_PLANKS.get());

        tag(BlockTags.WOODEN_STAIRS)
                .add(ModBlocks.MAPLE_STAIRS.get());

        tag(BlockTags.WOODEN_SLABS)
                .add(ModBlocks.MAPLE_SLAB.get());

        tag(BlockTags.WOODEN_BUTTONS)
                .add(ModBlocks.MAPLE_BUTTON.get());

        tag(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.MAPLE_PRESSURE_PLATE.get());

        tag(BlockTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.MAPLE_TRAPDOOR.get());

        tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.MAPLE_FENCE_GATE.get());

        tag(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.MAPLE_FENCE.get());

        tag(BlockTags.WOODEN_DOORS)
                .add(ModBlocks.MAPLE_DOOR.get());

        // Signs
        tag(BlockTags.SIGNS)
                .add(ModBlocks.MAPLE_SIGN.get())
                .add(ModBlocks.MAPLE_WALL_SIGN.get());

        tag(BlockTags.ALL_HANGING_SIGNS)
                .add(ModBlocks.MAPLE_HANGING_SIGN.get())
                .add(ModBlocks.MAPLE_HANGING_WALL_SIGN.get());
    }

    private void addAdditionalTags() {
        // Flower Pots
        tag(BlockTags.FLOWER_POTS)
                .add(ModBlocks.POTTED_LEMON_SAPLING.get())
                .add(ModBlocks.POTTED_HAWTHORN_SAPLING.get())
                .add(ModBlocks.POTTED_THISTLE.get())
                .add(ModBlocks.POTTED_MAPLE_SAPLING.get());
    }

    private void addSereneSeasonsTags() {
        tag(ModTags.Compat.SERENE_SEASONS_SPRING_CROPS_BLOCK)
                .add(ModBlocks.BARLEY_CROP.get())
                .add(ModBlocks.MINT_CROP.get());

        tag(ModTags.Compat.SERENE_SEASONS_SUMMER_CROPS_BLOCK)
                .add(ModBlocks.GINGER_CROP.get())
                .add(ModBlocks.MINT_CROP.get());

        tag(ModTags.Compat.SERENE_SEASONS_AUTUMN_CROPS_BLOCK)
                .add(ModBlocks.RYE_CROP.get());

        tag(ModTags.Compat.SERENE_SEASONS_WINTER_CROPS_BLOCK)
                .add(ModBlocks.GARLIC_CROP.get());

        tag(ModTags.Compat.SERENE_SEASONS_UNBREAKABLE_FERTILE_CROPS)
                .add(ModBlocks.MINT_CROP.get());
    }
}
