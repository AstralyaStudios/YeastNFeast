package net.astralya.yeastnfeast.datagen;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.item.ModItems;
import net.astralya.yeastnfeast.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider,
                              CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, YeastNFeastMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(ModTags.Items.KEG_CONTAINERS).add(ModItems.TANKARD.get(), ModItems.JAR.get());
        addVanillaTags();
        addTreeRelatedTags();
        addFoodTags();
        addCommonTags();
        addSignAndBoatTags();
        addSereneSeasonsTags();
    }

    private void addVanillaTags() {
        // Animal food
        tag(ItemTags.FOX_FOOD)
                .add(ModItems.ELDERBERRIES.get())
                .add(ModItems.HAWTHORN_BERRIES.get())
                .add(ModItems.ROSE_HIPS.get());

        // Villager related
        tag(ItemTags.VILLAGER_PLANTABLE_SEEDS)
                .add(ModItems.BARLEY_SEEDS.get())
                .add(ModItems.MINT_SEEDS.get())
                .add(ModItems.RYE_SEEDS.get());
    }

    private void addTreeRelatedTags() {
        this.tag(ModTags.Items.MAPLE_LOGS)
                .add(ModBlocks.MAPLE_LOG.get().asItem(), ModBlocks.STRIPPED_MAPLE_LOG.get().asItem(),
                        ModBlocks.MAPLE_WOOD.get().asItem(), ModBlocks.STRIPPED_MAPLE_WOOD.get().asItem());

        this.tag(ItemTags.LOGS_THAT_BURN)
                .addTag(ModTags.Items.MAPLE_LOGS);

        this.tag(ItemTags.PLANKS)
                .add(ModBlocks.MAPLE_PLANKS.get().asItem());
        this.tag(ItemTags.WOODEN_STAIRS)
                .add(ModBlocks.MAPLE_STAIRS.get().asItem());
        this.tag(ItemTags.WOODEN_SLABS)
                .add(ModBlocks.MAPLE_SLAB.get().asItem());
        this.tag(ItemTags.WOODEN_BUTTONS)
                .add(ModBlocks.MAPLE_BUTTON.get().asItem());
        this.tag(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.MAPLE_PRESSURE_PLATE.get().asItem());
        this.tag(ItemTags.FENCE_GATES)
                .add(ModBlocks.MAPLE_FENCE_GATE.get().asItem());
        this.tag(ItemTags.WOODEN_FENCES)
                .add(ModBlocks.MAPLE_FENCE.get().asItem());

        this.tag(ItemTags.WOODEN_DOORS)
                .add(ModBlocks.MAPLE_DOOR.get().asItem());
        this.tag(ItemTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.MAPLE_TRAPDOOR.get().asItem());

    }

    private void addFoodTags() {
        // Foods
        tag(ModTags.Items.FOODS)
                .addTag(ModTags.Items.FOODS_MILK)
                .add(ModItems.MOLASSES.get())
                .add(ModItems.MILK_BOTTLE.get())
                .addTag(ModTags.Items.FOODS_BERRY)
                .addTag(ModTags.Items.FOODS_FRUIT)
                .addTag(ModTags.Items.FOODS_VEGETABLE)
                .add(ModItems.SWEET_PORRIDGE.get())
                .add(ModItems.SPICED_PORRIDGE.get())
                .addTag(ModTags.Items.SOUP)
                .add(ModItems.MEAD_BRAISED_PORK.get())
                .add(ModItems.HERBAL_COD.get())
                .add(ModItems.LEMON_GLAZED_CHICKEN.get())
                .add(ModItems.FORAGER_FEAST.get())
                .add(ModItems.MAPLE_GLAZED_RABBIT.get())
                .addTag(ModTags.Items.FOODS_BREAD)
                .addTag(ModTags.Items.PIE);

        tag(ModTags.Items.FOODS_MILK)
                .add(ModItems.MILK_BOTTLE.get());

        tag(ModTags.Items.FOODS_FRUIT)
                .add(ModItems.LEMON.get())
                .add(ModItems.ELDERBERRIES.get())
                .add(ModItems.ROSE_HIPS.get())
                .add(ModItems.HAWTHORN_BERRIES.get());

        tag(ModTags.Items.FOODS_VEGETABLE)
                .add(ModItems.GINGER.get())
                .add(ModItems.GARLIC.get());

        tag(ModTags.Items.FOODS_BREAD)
                .add(ModItems.BARLEY_BREAD.get())
                .add(ModItems.MOLASSES_BREAD.get())
                .add(Items.BREAD)
                .add(ModItems.BERRY_ROLL.get())
                .add(ModItems.RYE_BREAD.get());

        tag(ModTags.Items.FOODS_BERRY)
                .add(ModItems.ELDERBERRIES.get())
                .add(ModItems.ROSE_HIPS.get())
                .add(ModItems.HAWTHORN_BERRIES.get());

        tag(ModTags.Items.FOODS_CHEESE)
                .add(ModItems.CHEESE_SLICE.get())
                .add(ModItems.SHARPWHEEL_SLICE.get())
                .add(ModItems.FRESHWHEEL_SLICE.get())
                .add(ModItems.DUSKWHEEL_SLICE.get());

        tag(ModTags.Items.CHEESE_INGREDIENT)
                .add(ModItems.MINT.get())
                .add(ModItems.GARLIC.get())
                .add(ModItems.ELDERBERRIES.get());

        tag(ModTags.Items.FEAST_BREADS)
                .add(ModItems.BARLEY_BREAD.get())
                .add(ModItems.RYE_BREAD.get())
                .add(ModItems.MOLASSES_BREAD.get())
                .add(Items.BREAD);

        tag(ModTags.Items.FEAST_MAINS)
                .add(ModItems.MEAD_BRAISED_PORK.get())
                .add(ModItems.HERBAL_COD.get())
                .add(ModItems.LEMON_GLAZED_CHICKEN.get())
                .add(ModItems.FORAGER_FEAST.get())
                .add(ModItems.MAPLE_GLAZED_RABBIT.get())
                .add(ModItems.STUFFED_RYE_DUMPLINGS.get())
                .add(ModItems.DUSKWHEEL_SKEWER.get())
                .add(ModItems.QUICHE.get())
                .add(Items.COOKED_BEEF)
                .add(Items.COOKED_CHICKEN)
                .add(Items.COOKED_MUTTON)
                .add(Items.COOKED_PORKCHOP)
                .add(Items.COOKED_RABBIT)
                .add(Items.COOKED_COD)
                .add(Items.COOKED_SALMON);

        tag(ModTags.Items.FEAST_BOWLS)
                .add(ModItems.SWEET_PORRIDGE.get())
                .add(ModItems.SPICED_PORRIDGE.get())
                .add(ModItems.BARLEY_AND_BEEF_STEW.get())
                .add(ModItems.SALMON_CHOWDER.get())
                .add(ModItems.CHEESE_SOUP.get())
                .add(Items.MUSHROOM_STEW)
                .add(Items.RABBIT_STEW)
                .add(Items.BEETROOT_SOUP)
                .add(Items.SUSPICIOUS_STEW);

        tag(ModTags.Items.FEAST_DAIRY)
                .add(ModItems.MILK_BOTTLE.get())
                .add(ModItems.CHEESE_SLICE.get())
                .add(ModItems.FRESHWHEEL_SLICE.get())
                .add(ModItems.DUSKWHEEL_SLICE.get())
                .add(ModItems.SHARPWHEEL_SLICE.get())
                .add(ModItems.MINTED_CHEESE_TART.get())
                .add(ModItems.CHEESE_SOUP.get())
                .add(Items.MILK_BUCKET);

        tag(ModTags.Items.FEAST_SWEETS)
                .add(ModItems.BERRY_ROLL.get())
                .add(ModItems.ROSE_TART.get())
                .add(ModItems.ELDERBERRY_PIE.get())
                .add(ModItems.APPLE_PIE.get())
                .add(ModItems.APPLE_JAM.get())
                .add(ModItems.CHORUS_FRUIT_JAM.get())
                .add(ModItems.ELDERBERRIES_JAM.get())
                .add(ModItems.GLOW_BERRIES_JAM.get())
                .add(ModItems.GOLDEN_APPLE_JAM.get())
                .add(ModItems.HAWTHORN_BERRIES_JAM.get())
                .add(ModItems.LEMON_JAM.get())
                .add(ModItems.MELON_JAM.get())
                .add(ModItems.ROSE_HIPS_JAM.get())
                .add(ModItems.SWEET_BERRIES_JAM.get())
                .add(Items.CAKE)
                .add(Items.COOKIE)
                .add(Items.PUMPKIN_PIE);

        tag(ModTags.Items.FEAST_DRINKS)
                .add(ModItems.MILK_BOTTLE.get())
                .add(ModItems.HONEY_MEAD.get())
                .add(ModItems.MOLASSES_MEAD.get())
                .add(ModItems.SOUR_MEAD.get())
                .add(ModItems.THORNBERRY_MEAD.get())
                .add(ModItems.BLOSSOM_MEAD.get())
                .add(ModItems.AMBER_MEAD.get())
                .add(Items.MILK_BUCKET);

        // Cooked Meat
        tag(ModTags.Items.FOODS_COOKED_MEAT)
                .addTag(ModTags.Items.COOKED_BEEF)
                .addTag(ModTags.Items.COOKED_CHICKEN)
                .addTag(ModTags.Items.COOKED_MUTTON)
                .addTag(ModTags.Items.COOKED_PORK)
                .addTag(ModTags.Items.COOKED_COD)
                .addTag(ModTags.Items.COOKED_SALMON);

        tag(ModTags.Items.COOKED_BEEF)
                .add(Items.COOKED_BEEF);
        tag(ModTags.Items.COOKED_CHICKEN)
                .add(Items.COOKED_CHICKEN);
        tag(ModTags.Items.COOKED_MUTTON)
                .add(Items.COOKED_MUTTON);
        tag(ModTags.Items.COOKED_PORK)
                .add(Items.COOKED_PORKCHOP);
        tag(ModTags.Items.COOKED_COD)
                .add(Items.COOKED_COD);
        tag(ModTags.Items.COOKED_SALMON)
                .add(Items.COOKED_SALMON);

       // Soup
        tag(ModTags.Items.SOUP)
                .add(ModItems.BARLEY_AND_BEEF_STEW.get())
                .add(ModItems.SALMON_CHOWDER.get());

       // Pie
        tag(ModTags.Items.PIE)
                .add(ModItems.ROSE_TART.get())
                .add(ModItems.ELDERBERRY_PIE.get())
                .add(ModItems.APPLE_PIE.get());
    }

    private void addCommonTags() {
        // Berry
        tag(ModTags.Items.BERRY)
                .add(ModItems.ELDERBERRIES.get())
                .add(ModItems.ROSE_HIPS.get())
                .add(ModItems.HAWTHORN_BERRIES.get());

        // Crops
        tag(ModTags.Items.CROPS)
                .add(ModItems.GINGER.get())
                .add(ModItems.GARLIC.get());

        // Crops
        tag(ModTags.Items.CROPS_GRAIN)
                .add(ModItems.BARLEY.get())
                .add(Items.WHEAT)
                .add(ModItems.RYE.get());

        // Seeds
        tag(ModTags.Items.SEEDS)
                .add(ModItems.BARLEY_SEEDS.get())
                .add(ModItems.MINT_SEEDS.get())
                .add(ModItems.RYE_SEEDS.get());

        // Mushrooms
        tag(ModTags.Items.MUSHROOMS)
                .add(Items.RED_MUSHROOM)
                .add(Items.BROWN_MUSHROOM);
    }

    private void addSignAndBoatTags() {
        // Boats
        tag(ItemTags.BOATS).add(ModItems.MAPLE_BOAT.get());
        tag(ItemTags.CHEST_BOATS).add(ModItems.MAPLE_CHEST_BOAT.get());

        // Signs
        tag(ItemTags.SIGNS).add(ModItems.MAPLE_SIGN.get());
        tag(ItemTags.HANGING_SIGNS).add(ModItems.MAPLE_HANGING_SIGN.get());
    }

    private void addSereneSeasonsTags() {
        tag(ModTags.Compat.SERENE_SEASONS_SPRING_CROPS)
                .add(ModItems.BARLEY_SEEDS.get())
                .add(ModItems.MINT_SEEDS.get());

        tag(ModTags.Compat.SERENE_SEASONS_SUMMER_CROPS)
                .add(ModItems.GINGER.get())
                .add(ModItems.MINT_SEEDS.get());

        tag(ModTags.Compat.SERENE_SEASONS_AUTUMN_CROPS)
                .add(ModItems.RYE.get());

        tag(ModTags.Compat.SERENE_SEASONS_WINTER_CROPS)
                .add(ModItems.GARLIC.get());
    }
}
