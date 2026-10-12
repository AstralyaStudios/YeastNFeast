package net.astralya.yeastnfeast.datagen;

import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.item.ModItems;
import net.astralya.yeastnfeast.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        addVanillaTags();
        addTreeRelatedTags();
        addFoodTags();
        addCommonTags();
        addSignAndBoatTags();
        addSereneSeasonsTags();
    }

    private void addVanillaTags() {
        getOrCreateTagBuilder(ItemTags.FOX_FOOD)
                .add(ModItems.ELDERBERRIES)
                .add(ModItems.HAWTHORN_BERRIES)
                .add(ModItems.ROSE_HIPS);

        getOrCreateTagBuilder(ItemTags.VILLAGER_PLANTABLE_SEEDS)
                .add(ModItems.BARLEY_SEEDS)
                .add(ModItems.MINT_SEEDS)
                .add(ModItems.RYE_SEEDS);
    }

    private void addTreeRelatedTags() {
        getOrCreateTagBuilder(ModTags.Items.MAPLE_LOGS)
                .add(ModBlocks.MAPLE_LOG.asItem())
                .add(ModBlocks.STRIPPED_MAPLE_LOG.asItem())
                .add(ModBlocks.MAPLE_WOOD.asItem())
                .add(ModBlocks.STRIPPED_MAPLE_WOOD.asItem());

        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                .addTag(ModTags.Items.MAPLE_LOGS);

        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(ModBlocks.MAPLE_PLANKS.asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_STAIRS)
                .add(ModBlocks.MAPLE_STAIRS.asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_SLABS)
                .add(ModBlocks.MAPLE_SLAB.asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_BUTTONS)
                .add(ModBlocks.MAPLE_BUTTON.asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.MAPLE_PRESSURE_PLATE.asItem());
        getOrCreateTagBuilder(ItemTags.FENCE_GATES)
                .add(ModBlocks.MAPLE_FENCE_GATE.asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_FENCES)
                .add(ModBlocks.MAPLE_FENCE.asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_DOORS)
                .add(ModBlocks.MAPLE_DOOR.asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.MAPLE_TRAPDOOR.asItem());
    }

    private void addFoodTags() {
        getOrCreateTagBuilder(ModTags.Items.FOODS)
                .add(ModItems.MOLASSES)
                .add(ModItems.MILK_BOTTLE)
                .add(ModItems.ELDERBERRIES)
                .add(ModItems.ROSE_HIPS)
                .add(ModItems.HAWTHORN_BERRIES)
                .add(ModItems.GINGER)
                .add(ModItems.GARLIC)
                .add(ModItems.BARLEY_BREAD)
                .add(ModItems.MOLASSES_BREAD)
                .add(ModItems.BERRY_ROLL)
                .add(ModItems.RYE_BREAD)
                .add(ModItems.LEMON)
                .add(ModItems.ROSE_TART)
                .add(ModItems.ELDERBERRY_PIE)
                .add(ModItems.APPLE_PIE)
                .add(ModItems.SWEET_PORRIDGE)
                .add(ModItems.SPICED_PORRIDGE)
                .add(ModItems.BARLEY_AND_BEEF_STEW)
                .add(ModItems.SALMON_CHOWDER)
                .add(ModItems.MEAD_BRAISED_PORK)
                .add(ModItems.HERBAL_COD)
                .add(ModItems.LEMON_GLAZED_CHICKEN)
                .add(ModItems.FORAGER_FEAST)
                .add(ModItems.MAPLE_GLAZED_RABBIT);

        getOrCreateTagBuilder(ModTags.Items.MILKS)
                .add(ModItems.MILK_BOTTLE);

        getOrCreateTagBuilder(ModTags.Items.VEGETABLE_FOODS)
                .add(ModItems.GINGER)
                .add(ModItems.GARLIC);

        getOrCreateTagBuilder(ModTags.Items.FRUIT_FOODS)
                .add(ModItems.LEMON);

        getOrCreateTagBuilder(ModTags.Items.BREAD_FOODS)
                .add(ModItems.BARLEY_BREAD)
                .add(Items.BREAD)
                .add(ModItems.MOLASSES_BREAD)
                .add(ModItems.RYE_BREAD);

        getOrCreateTagBuilder(ModTags.Items.BERRY_FOODS)
                .add(ModItems.ELDERBERRIES)
                .add(ModItems.ROSE_HIPS)
                .add(ModItems.HAWTHORN_BERRIES);

        getOrCreateTagBuilder(ModTags.Items.PIE_FOODS)
                .add(ModItems.ROSE_TART)
                .add(ModItems.ELDERBERRY_PIE)
                .add(ModItems.APPLE_PIE);

        getOrCreateTagBuilder(ModTags.Items.SOUP_FOODS)
                .add(ModItems.BARLEY_AND_BEEF_STEW)
                .add(ModItems.SALMON_CHOWDER);

        getOrCreateTagBuilder(ModTags.Items.GOLDEN_FOODS)
                .add(ModItems.GOLDEN_APPLE_JAM);

        getOrCreateTagBuilder(ModTags.Items.COOKED_MEAT_FOODS)
                .addTag(ModTags.Items.COOKED_BEEF)
                .addTag(ModTags.Items.COOKED_CHICKEN)
                .addTag(ModTags.Items.COOKED_MUTTON)
                .addTag(ModTags.Items.COOKED_PORK)
                .addTag(ModTags.Items.COOKED_FISH_COD)
                .addTag(ModTags.Items.COOKED_FISH_SALMON);

        getOrCreateTagBuilder(ModTags.Items.COOKED_BEEF)
                .add(Items.COOKED_BEEF);
        getOrCreateTagBuilder(ModTags.Items.COOKED_CHICKEN)
                .add(Items.COOKED_CHICKEN);
        getOrCreateTagBuilder(ModTags.Items.COOKED_MUTTON)
                .add(Items.COOKED_MUTTON);
        getOrCreateTagBuilder(ModTags.Items.COOKED_PORK)
                .add(Items.COOKED_PORKCHOP);
        getOrCreateTagBuilder(ModTags.Items.COOKED_FISH_COD)
                .add(Items.COOKED_COD);
        getOrCreateTagBuilder(ModTags.Items.COOKED_FISH_SALMON)
                .add(Items.COOKED_SALMON);

        getOrCreateTagBuilder(ModTags.Items.JAMS)
                .add(ModItems.APPLE_JAM)
                .add(ModItems.CHORUS_FRUIT_JAM)
                .add(ModItems.ELDERBERRIES_JAM)
                .add(ModItems.GLOW_BERRIES_JAM)
                .add(ModItems.GOLDEN_APPLE_JAM)
                .add(ModItems.HAWTHORN_BERRIES_JAM)
                .add(ModItems.LEMON_JAM)
                .add(ModItems.MELON_JAM)
                .add(ModItems.ROSE_HIPS_JAM)
                .add(ModItems.SWEET_BERRIES_JAM);

        getOrCreateTagBuilder(ModTags.Items.CHEESE_INGREDIENT)
                .add(ModItems.MINT)
                .add(ModItems.ELDERBERRIES)
                .add(ModItems.GARLIC);
    }

    private void addCommonTags() {
        getOrCreateTagBuilder(ModTags.Items.BERRY_FOODS)
                .add(ModItems.ELDERBERRIES)
                .add(ModItems.ROSE_HIPS)
                .add(ModItems.HAWTHORN_BERRIES);

        getOrCreateTagBuilder(ModTags.Items.CROPS)
                .add(ModItems.GINGER)
                .add(ModItems.GARLIC);

        getOrCreateTagBuilder(ModTags.Items.GRAINS)
                .add(ModItems.BARLEY)
                .add(Items.WHEAT)
                .add(ModItems.RYE);

        getOrCreateTagBuilder(ModTags.Items.WHEAT_CROPS)
                .add(ModItems.BARLEY)
                .add(ModItems.RYE);

        getOrCreateTagBuilder(ModTags.Items.SEEDS)
                .add(ModItems.BARLEY_SEEDS)
                .add(ModItems.MINT_SEEDS)
                .add(ModItems.RYE_SEEDS);

        getOrCreateTagBuilder(ModTags.Items.MUSHROOMS)
                .add(Items.RED_MUSHROOM)
                .add(Items.BROWN_MUSHROOM);

        getOrCreateTagBuilder(ModTags.Items.CHEESE_FOODS)
                .add(ModItems.CHEESE_SLICE)
                .add(ModItems.SHARPWHEEL_SLICE)
                .add(ModItems.FRESHWHEEL_SLICE)
                .add(ModItems.DUSKWHEEL_SLICE);
    }

    private void addSignAndBoatTags() {
        getOrCreateTagBuilder(ItemTags.BOATS)
                .add(ModItems.MAPLE_BOAT);
        getOrCreateTagBuilder(ItemTags.CHEST_BOATS)
                .add(ModItems.MAPLE_CHEST_BOAT);
        getOrCreateTagBuilder(ItemTags.SIGNS)
                .add(ModItems.MAPLE_SIGN);
        getOrCreateTagBuilder(ItemTags.HANGING_SIGNS)
                .add(ModItems.MAPLE_HANGING_SIGN);
    }

    private void addSereneSeasonsTags() {
        getOrCreateTagBuilder(ModTags.Compat.SERENE_SEASONS_SPRING_CROPS)
                .add(ModItems.BARLEY_SEEDS)
                .add(ModItems.MINT_SEEDS);

        getOrCreateTagBuilder(ModTags.Compat.SERENE_SEASONS_SUMMER_CROPS)
                .add(ModItems.GINGER)
                .add(ModItems.MINT_SEEDS);

        getOrCreateTagBuilder(ModTags.Compat.SERENE_SEASONS_AUTUMN_CROPS)
                .add(ModItems.RYE_SEEDS);

        getOrCreateTagBuilder(ModTags.Compat.SERENE_SEASONS_WINTER_CROPS)
                .add(ModItems.GARLIC);
    }
}