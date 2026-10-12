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
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture,
                              CompletableFuture<TagLookup<Block>> lookupCompletableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, completableFuture, lookupCompletableFuture, YeastNFeastMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
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
        tag(ModTags.Items.MILK_BOTTLE)
                .add(ModItems.MILK_BOTTLE.get());

        tag(ModTags.Items.MILK)
                .add(ModItems.MILK_BOTTLE.get());

        tag(ModTags.Items.VEGETABLES)
                .add(ModItems.GINGER.get())
                .add(ModItems.GARLIC.get());

        tag(ModTags.Items.BREAD)
                .add(ModItems.BARLEY_BREAD.get())
                .add(ModItems.MOLASSES_BREAD.get())
                .add(Items.BREAD)
                .add(ModItems.BERRY_ROLL.get())
                .add(ModItems.RYE_BREAD.get());

        // Cooked Meat
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

        tag(ModTags.Items.CHEESE)
                .add(ModItems.CHEESE_SLICE.get())
                .add(ModItems.SHARPWHEEL_SLICE.get())
                .add(ModItems.FRESHWHEEL_SLICE.get())
                .add(ModItems.DUSKWHEEL_SLICE.get());
    }

    private void addCommonTags() {
        // Berry
        tag(ModTags.Items.BERRIES)
                .add(ModItems.ELDERBERRIES.get())
                .add(ModItems.ROSE_HIPS.get())
                .add(ModItems.HAWTHORN_BERRIES.get());

        // Crops
        tag(ModTags.Items.CROPS)
                .add(ModItems.GINGER.get())
                .add(ModItems.GARLIC.get());

        // Crops
        tag(ModTags.Items.GRAINS)
                .add(ModItems.BARLEY.get())
                .add(Items.WHEAT)
                .add(ModItems.RYE.get());

        // Seeds
        tag(ModTags.Items.SEEDS)
                .add(ModItems.BARLEY_SEEDS.get())
                .add(ModItems.MINT_SEEDS.get())
                .add(ModItems.RYE_SEEDS.get());

        // Mushrooms
        tag(ModTags.Items.MUSHROOM)
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

    @Override
    public String getName() {
        return "Item Tags";
    }
}
