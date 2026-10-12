package net.astralya.yeastnfeast.datagen;

import net.astralya.yeastnfeast.datagen.custom.CheesePressRecipeBuilder;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.datagen.custom.KegRecipeBuilder;
import net.astralya.yeastnfeast.item.ModItems;
import net.astralya.yeastnfeast.util.ModTags;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeGenerator extends FabricRecipeProvider {
    public ModRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {

        // Shaped Recipes
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.TANKARD)
                .pattern("S S")
                .pattern("PSP")
                .input('P', Items.IRON_NUGGET)
                .input('S', ItemTags.PLANKS)
                .criterion(hasItem(Items.IRON_NUGGET), conditionsFromItem(Items.IRON_NUGGET))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TANKARD)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.ELDERBERRY_PIE)
                .pattern(" S ")
                .pattern("STS")
                .pattern("PHP")
                .input('P', ModTags.Items.WHEAT_CROPS)
                .input('S', ModItems.ELDERBERRIES)
                .input('T', Items.SUGAR)
                .input('H', ModTags.Items.MILKS)
                .criterion(hasItem(ModItems.ELDERBERRIES), conditionsFromItem(ModItems.ELDERBERRIES))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ELDERBERRY_PIE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.ROSE_TART)
                .pattern(" S ")
                .pattern("STS")
                .pattern("PHP")
                .input('P', ModTags.Items.WHEAT_CROPS)
                .input('S', ModItems.ROSE_HIPS)
                .input('T', Items.SUGAR)
                .input('H', ModTags.Items.MILKS)
                .criterion(hasItem(ModItems.ROSE_HIPS), conditionsFromItem(ModItems.ROSE_HIPS))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ROSE_TART)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.APPLE_PIE)
                .pattern(" S ")
                .pattern("STS")
                .pattern("PHP")
                .input('P', ModTags.Items.WHEAT_CROPS)
                .input('S', Items.APPLE)
                .input('T', Items.SUGAR)
                .input('H', ModTags.Items.MILKS)
                .criterion(hasItem(Items.APPLE), conditionsFromItem(Items.APPLE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.APPLE_PIE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.BERRY_ROLL)
                .pattern(" S ")
                .pattern("SPS")
                .pattern("PHP")
                .input('P', ModTags.Items.WHEAT_CROPS)
                .input('S', ModItems.HAWTHORN_BERRIES)
                .input('H', ModTags.Items.MILKS)
                .criterion(hasItem(ModItems.HAWTHORN_BERRIES), conditionsFromItem(ModItems.HAWTHORN_BERRIES))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.BERRY_ROLL)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.TREE_TAP)
                .pattern(" H")
                .pattern("PS")
                .input('P', Items.STICK)
                .input('S', ItemTags.PLANKS)
                .input('H', Items.IRON_NUGGET)
                .criterion(hasItem(ModItems.HAWTHORN_BERRIES), conditionsFromItem(ModItems.HAWTHORN_BERRIES))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.TREE_TAP)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.KEG)
                .pattern("PSP")
                .input('P', Items.STICK)
                .input('S', Blocks.BARREL)
                .criterion(hasItem(Blocks.BARREL), conditionsFromItem(Blocks.BARREL))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.KEG)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.JAR, 3)
                .pattern("PSP")
                .pattern(" P ")
                .input('P', Blocks.GLASS)
                .input('S', ItemTags.PLANKS)
                .criterion(hasItem(Blocks.GLASS), conditionsFromItem(Blocks.GLASS))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.JAR)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.CHEESE_PRESS)
                .pattern(" P ")
                .pattern("PSP")
                .pattern("HHH")
                .input('P', Items.STICK)
                .input('S', Items.CHAIN)
                .input('H', ItemTags.PLANKS)
                .criterion(hasItem(Items.CHAIN), conditionsFromItem(Items.CHAIN))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.CHEESE_PRESS)));

        // Shapeless Recipes
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.YEAST)
                .input(ModTags.Items.MUSHROOMS)
                .input(ModTags.Items.MUSHROOMS)
                .criterion("has_mushroom", conditionsFromTag(ModTags.Items.MUSHROOMS))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MILK_BOTTLE, 4)
                .input(Items.MILK_BUCKET)
                .input(Items.GLASS_BOTTLE)
                .input(Items.GLASS_BOTTLE)
                .input(Items.GLASS_BOTTLE)
                .input(Items.GLASS_BOTTLE)
                .criterion("has_milk_bucket", conditionsFromItem(Items.MILK_BUCKET))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.MILK_BUCKET)
                .input(Items.BUCKET)
                .input(ModItems.MILK_BOTTLE)
                .input(ModItems.MILK_BOTTLE)
                .input(ModItems.MILK_BOTTLE)
                .input(ModItems.MILK_BOTTLE)
                .criterion("has_milk_bottle", conditionsFromItem(Items.BUCKET))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RENNET)
                .input(Items.GLASS_BOTTLE)
                .input(Items.ROTTEN_FLESH)
                .input(ModBlocks.THISTLE)
                .criterion("has_thistle", conditionsFromItem(ModBlocks.THISTLE))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.SWEET_PORRIDGE)
                .input(Items.BOWL)
                .input(ModItems.MOLASSES)
                .input(ModTags.Items.MILKS)
                .input(Items.WHEAT)
                .input(ModTags.Items.BERRY_FOODS)
                .criterion("has_molasses", conditionsFromItem(ModItems.MOLASSES))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.SPICED_PORRIDGE)
                .input(Items.BOWL)
                .input(ModItems.GARLIC)
                .input(ModTags.Items.MILKS)
                .input(ModItems.RYE)
                .input(ModItems.GINGER)
                .criterion("has_rye", conditionsFromItem(ModItems.RYE))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.BARLEY_AND_BEEF_STEW)
                .input(Items.BOWL)
                .input(ModItems.BARLEY)
                .input(ModTags.Items.VEGETABLE_FOODS)
                .input(ModTags.Items.VEGETABLE_FOODS)
                .input(ModTags.Items.COOKED_BEEF)
                .criterion("has_cooked_beef", conditionsFromItem(Items.COOKED_BEEF))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.MEAD_BRAISED_PORK)
                .input(Items.BOWL)
                .input(ModItems.HONEY_MEAD)
                .input(ModItems.BARLEY)
                .input(ModTags.Items.VEGETABLE_FOODS)
                .input(ModTags.Items.COOKED_PORK)
                .criterion("has_cooked_pork", conditionsFromItem(Items.COOKED_PORKCHOP))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.HERBAL_COD)
                .input(Items.BOWL)
                .input(ModItems.MINT)
                .input(ModItems.GINGER)
                .input(ModItems.LEMON)
                .input(ModTags.Items.COOKED_FISH_COD)
                .criterion("has_cooked_cod", conditionsFromItem(Items.COOKED_COD))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.LEMON_GLAZED_CHICKEN)
                .input(Items.BOWL)
                .input(Items.HONEY_BOTTLE)
                .input(ModItems.GARLIC)
                .input(ModItems.LEMON)
                .input(ModTags.Items.COOKED_CHICKEN)
                .criterion("has_cooked_chicken", conditionsFromItem(Items.COOKED_CHICKEN))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.FORAGER_FEAST)
                .input(Items.BOWL)
                .input(ModItems.ELDERBERRIES)
                .input(ModItems.RYE)
                .input(ModItems.MINT)
                .input(ModTags.Items.COOKED_MUTTON)
                .criterion("has_cooked_mutton", conditionsFromItem(Items.COOKED_SALMON))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.MAPLE_GLAZED_RABBIT)
                .input(Items.BOWL)
                .input(ModItems.MAPLE_SYRUP)
                .input(ModItems.BARLEY)
                .input(Items.BEETROOT)
                .input(Items.COOKED_RABBIT)
                .criterion("has_cooked_rabbit", conditionsFromItem(Items.COOKED_RABBIT))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.SALMON_CHOWDER)
                .input(Items.BOWL)
                .input(ModItems.LEMON)
                .input(ModTags.Items.MILKS)
                .input(Items.KELP)
                .input(ModTags.Items.COOKED_FISH_SALMON)
                .criterion("has_cooked_salmon", conditionsFromItem(Items.COOKED_SALMON))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.BARLEY_BREAD)
                .input(ModItems.BARLEY)
                .input(ModItems.BARLEY)
                .input(ModTags.Items.MILKS)
                .input(ModItems.YEAST)
                .criterion("has_barley", conditionsFromItem(ModItems.BARLEY))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.RYE_BREAD)
                .input(ModItems.RYE)
                .input(ModItems.RYE)
                .input(ModTags.Items.MILKS)
                .input(ModItems.YEAST)
                .criterion("has_rye", conditionsFromItem(ModItems.RYE))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.MOLASSES_BREAD)
                .input(ModTags.Items.WHEAT_CROPS)
                .input(ModTags.Items.WHEAT_CROPS)
                .input(ModItems.MOLASSES)
                .input(ModItems.YEAST)
                .criterion("has_molasses", conditionsFromItem(ModItems.MOLASSES))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.MINTED_CHEESE_TART)
                .pattern(" R ")
                .pattern("STS")
                .pattern("PHP")
                .input('P', ModTags.Items.WHEAT_CROPS)
                .input('R', ModItems.MINT)
                .input('S', ModItems.FRESHWHEEL_SLICE)
                .input('T', Items.SUGAR)
                .input('H', ModTags.Items.MILKS)
                .criterion(hasItem(ModItems.FRESHWHEEL_SLICE), conditionsFromItem(ModItems.FRESHWHEEL_SLICE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.MINTED_CHEESE_TART)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.QUICHE)
                .pattern(" R ")
                .pattern("STS")
                .pattern("PHP")
                .input('P', ModTags.Items.WHEAT_CROPS)
                .input('R', ModItems.GARLIC)
                .input('S', ModTags.Items.CHEESE_FOODS)
                .input('T', Items.SUGAR)
                .input('H', ModTags.Items.MILKS)
                .criterion(hasItem(ModItems.CHEESE_SLICE), conditionsFromItem(ModItems.CHEESE_SLICE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.QUICHE)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.STUFFED_RYE_DUMPLINGS)
                .input(Items.BOWL)
                .input(ModItems.RYE)
                .input(ModItems.RYE)
                .input(ModItems.GINGER)
                .input(ModItems.SHARPWHEEL_SLICE)
                .input(ModItems.SHARPWHEEL_SLICE)
                .criterion("has_sharpwheel_slice", conditionsFromItem(ModItems.SHARPWHEEL_SLICE))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.DUSKWHEEL_SKEWER)
                .input(Items.STICK)
                .input(ModItems.GARLIC)
                .input(ModTags.Items.COOKED_CHICKEN)
                .input(ModItems.DUSKWHEEL_SLICE)
                .input(ModItems.DUSKWHEEL_SLICE)
                .criterion("has_duskwheel_slice", conditionsFromItem(ModItems.DUSKWHEEL_SLICE))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.CHEESE_SOUP)
                .input(Items.BOWL)
                .input(ModTags.Items.CHEESE_FOODS)
                .input(ModTags.Items.CHEESE_FOODS)
                .input(ModTags.Items.BREAD_FOODS)
                .input(ModItems.RYE)
                .input(ModItems.GARLIC)
                .criterion("has_cheese_slice", conditionsFromItem(ModItems.CHEESE_SLICE))
                .offerTo(exporter);

        /*ShapelessRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.HOMESTEADERS_HANDBOOK)
                .input(Items.WHEAT)
                .input(Items.BOOK)
                .criterion("has_book", conditionsFromItem(Items.BOOK))
                .offerTo(exporter);*/

        // Convertible for Storage Bags
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.ELDERBERRIES, RecipeCategory.MISC, ModBlocks.BAG_OF_ELDERBERRIES);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.GARLIC, RecipeCategory.MISC, ModBlocks.BAG_OF_GARLIC);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.GINGER, RecipeCategory.MISC, ModBlocks.BAG_OF_GINGER);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.HAWTHORN_BERRIES, RecipeCategory.MISC, ModBlocks.BAG_OF_HAWTHORN_BERRIES);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.LEMON, RecipeCategory.MISC, ModBlocks.BAG_OF_LEMON);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.MINT, RecipeCategory.MISC, ModBlocks.BAG_OF_MINT);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.ROSE_HIPS, RecipeCategory.MISC, ModBlocks.BAG_OF_ROSE_HIPS);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.BARLEY, RecipeCategory.MISC, ModBlocks.BARLEY_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.RYE, RecipeCategory.MISC, ModBlocks.RYE_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModBlocks.THISTLE, RecipeCategory.MISC, ModBlocks.BAG_OF_THISTLE);

        // Cheese Press Recipe Generator
        new CheesePressRecipeBuilder(
                List.of(CheesePressRecipeBuilder.ing(ModItems.RENNET), CheesePressRecipeBuilder.tag(ModTags.Items.MILKS)),
                null, ModItems.CHEESE_WHEEL, 1, 0.0f, 7200)
                .criterion(hasItem(ModItems.RENNET), conditionsFromItem(ModItems.RENNET))
                .offerTo(exporter);

        new CheesePressRecipeBuilder(
                List.of(CheesePressRecipeBuilder.ing(ModItems.RENNET), CheesePressRecipeBuilder.tag(ModTags.Items.MILKS)),
                CheesePressRecipeBuilder.ing(ModItems.MINT), ModItems.FRESHWHEEL, 1, 0.0f, 7200)
                .criterion(hasItem(ModItems.RENNET), conditionsFromItem(ModItems.RENNET))
                .offerTo(exporter);

        new CheesePressRecipeBuilder(
                List.of(CheesePressRecipeBuilder.ing(ModItems.RENNET), CheesePressRecipeBuilder.tag(ModTags.Items.MILKS)),
                CheesePressRecipeBuilder.ing(ModItems.GARLIC), ModItems.SHARPWHEEL, 1, 0.0f, 7200)
                .criterion(hasItem(ModItems.RENNET), conditionsFromItem(ModItems.RENNET))
                .offerTo(exporter);

        new CheesePressRecipeBuilder(
                List.of(CheesePressRecipeBuilder.ing(ModItems.RENNET), CheesePressRecipeBuilder.tag(ModTags.Items.MILKS)),
                CheesePressRecipeBuilder.ing(ModItems.ELDERBERRIES), ModItems.DUSKWHEEL, 1, 0.0f, 7200)
                .criterion(hasItem(ModItems.RENNET), conditionsFromItem(ModItems.RENNET))
                .offerTo(exporter);

        // Keg Recipe Generator
        new KegRecipeBuilder(List.of(Items.APPLE, Items.HONEY_BOTTLE, Items.WHEAT),
                ModItems.YEAST, ModItems.TANKARD,
                ModItems.HONEY_MEAD, 7200)
                .criterion(hasItem(ModItems.TANKARD), conditionsFromItem(ModItems.TANKARD))
                .offerTo(exporter);

        new KegRecipeBuilder(List.of(ModItems.ELDERBERRIES, ModItems.LEMON, ModItems.GINGER),
                ModItems.YEAST, ModItems.TANKARD,
                ModItems.SOUR_MEAD, 7200)
                .criterion(hasItem(ModItems.TANKARD), conditionsFromItem(ModItems.TANKARD))
                .offerTo(exporter);

        new KegRecipeBuilder(List.of(ModItems.BARLEY, ModItems.MOLASSES, Items.SUGAR_CANE),
                ModItems.YEAST, ModItems.TANKARD,
                ModItems.MOLASSES_MEAD, 7200)
                .criterion(hasItem(ModItems.TANKARD), conditionsFromItem(ModItems.TANKARD))
                .offerTo(exporter);

        new KegRecipeBuilder(List.of(ModItems.HAWTHORN_BERRIES, ModItems.ELDERBERRIES, Items.SWEET_BERRIES),
                ModItems.YEAST, ModItems.TANKARD,
                ModItems.THORNBERRY_MEAD, 7200)
                .criterion(hasItem(ModItems.TANKARD), conditionsFromItem(ModItems.TANKARD))
                .offerTo(exporter);

        new KegRecipeBuilder(List.of(ModItems.ROSE_HIPS, ModItems.MAPLE_SYRUP, ModItems.MINT),
                ModItems.YEAST, ModItems.TANKARD,
                ModItems.BLOSSOM_MEAD, 7200)
                .criterion(hasItem(ModItems.TANKARD), conditionsFromItem(ModItems.TANKARD))
                .offerTo(exporter);

        new KegRecipeBuilder(List.of(ModItems.RYE, ModItems.MAPLE_SYRUP, ModItems.ROSE_HIPS),
                ModItems.YEAST, ModItems.TANKARD,
                ModItems.AMBER_MEAD, 7200)
                .criterion(hasItem(ModItems.TANKARD), conditionsFromItem(ModItems.TANKARD))
                .offerTo(exporter);

        new KegRecipeBuilder(List.of(Items.APPLE, Items.APPLE, Items.APPLE),
                Items.SUGAR, ModItems.JAR,
                ModItems.APPLE_JAM, 3600)
                .criterion(hasItem(ModItems.JAR), conditionsFromItem(ModItems.JAR))
                .offerTo(exporter);
        new KegRecipeBuilder(List.of(Items.CHORUS_FRUIT, Items.CHORUS_FRUIT, Items.CHORUS_FRUIT),
                Items.SUGAR, ModItems.JAR,
                ModItems.CHORUS_FRUIT_JAM, 3600)
                .criterion(hasItem(ModItems.JAR), conditionsFromItem(ModItems.JAR))
                .offerTo(exporter);
        new KegRecipeBuilder(List.of(ModItems.ELDERBERRIES, ModItems.ELDERBERRIES, ModItems.ELDERBERRIES),
                Items.SUGAR, ModItems.JAR,
                ModItems.ELDERBERRIES_JAM, 3600)
                .criterion(hasItem(ModItems.JAR), conditionsFromItem(ModItems.JAR))
                .offerTo(exporter);
        new KegRecipeBuilder(List.of(Items.GLOW_BERRIES, Items.GLOW_BERRIES, Items.GLOW_BERRIES),
                Items.SUGAR, ModItems.JAR,
                ModItems.GLOW_BERRIES_JAM, 3600)
                .criterion(hasItem(ModItems.JAR), conditionsFromItem(ModItems.JAR))
                .offerTo(exporter);
        new KegRecipeBuilder(List.of(Items.GOLDEN_APPLE, Items.GOLDEN_APPLE, Items.GOLDEN_APPLE),
                Items.SUGAR, ModItems.JAR,
                ModItems.GOLDEN_APPLE_JAM, 3600)
                .criterion(hasItem(ModItems.JAR), conditionsFromItem(ModItems.JAR))
                .offerTo(exporter);
        new KegRecipeBuilder(List.of(ModItems.HAWTHORN_BERRIES, ModItems.HAWTHORN_BERRIES, ModItems.HAWTHORN_BERRIES),
                Items.SUGAR, ModItems.JAR,
                ModItems.HAWTHORN_BERRIES_JAM, 3600)
                .criterion(hasItem(ModItems.JAR), conditionsFromItem(ModItems.JAR))
                .offerTo(exporter);
        new KegRecipeBuilder(List.of(ModItems.LEMON, ModItems.LEMON, ModItems.LEMON),
                Items.SUGAR, ModItems.JAR,
                ModItems.LEMON_JAM, 3600)
                .criterion(hasItem(ModItems.JAR), conditionsFromItem(ModItems.JAR))
                .offerTo(exporter);
        new KegRecipeBuilder(List.of(ModItems.ROSE_HIPS, ModItems.ROSE_HIPS, ModItems.ROSE_HIPS),
                Items.SUGAR, ModItems.JAR,
                ModItems.ROSE_HIPS_JAM, 3600)
                .criterion(hasItem(ModItems.JAR), conditionsFromItem(ModItems.JAR))
                .offerTo(exporter);
        new KegRecipeBuilder(List.of(Items.SWEET_BERRIES, Items.SWEET_BERRIES, Items.SWEET_BERRIES),
                Items.SUGAR, ModItems.JAR,
                ModItems.SWEET_BERRIES_JAM, 3600)
                .criterion(hasItem(ModItems.JAR), conditionsFromItem(ModItems.JAR))
                .offerTo(exporter);
        new KegRecipeBuilder(List.of(Items.MELON_SLICE, Items.MELON_SLICE, Items.MELON_SLICE),
                Items.SUGAR, ModItems.JAR,
                ModItems.MELON_JAM, 3600)
                .criterion(hasItem(ModItems.JAR), conditionsFromItem(ModItems.JAR))
                .offerTo(exporter);

        // Shapeless Recipes
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BARLEY_SEEDS)
                .input(ModItems.BARLEY)
                .criterion(hasItem(ModItems.BARLEY), conditionsFromItem(ModItems.BARLEY))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RYE_SEEDS)
                .input(ModItems.RYE)
                .criterion(hasItem(ModItems.RYE), conditionsFromItem(ModItems.RYE))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.LEMON_SAPLING)
                .input(ModItems.LEMON)
                .input(Items.OAK_SAPLING)
                .criterion(hasItem(ModItems.LEMON), conditionsFromItem(ModItems.LEMON))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.HAWTHORN_SAPLING)
                .input(ModItems.HAWTHORN_BERRIES)
                .input(Items.OAK_SAPLING)
                .criterion(hasItem(ModItems.HAWTHORN_BERRIES), conditionsFromItem(ModItems.HAWTHORN_BERRIES))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.MOLASSES)
                .input(Items.GLASS_BOTTLE)
                .input(Items.SUGAR_CANE)
                .input(Items.SUGAR_CANE)
                .input(Items.SUGAR_CANE)
                .criterion(hasItem(Items.SUGAR_CANE), conditionsFromItem(Items.SUGAR_CANE))
                .offerTo(exporter);

        // Recipes for Wood-related Blocks & Items
        offerPlanksRecipe(exporter, ModBlocks.MAPLE_PLANKS, ModTags.Items.MAPLE_LOGS, 4);
        offerSingleOutputShapelessRecipe(exporter, ModBlocks.MAPLE_BUTTON, ModBlocks.MAPLE_PLANKS, "wooden_button");
        createTrapdoorRecipe(ModBlocks.MAPLE_TRAPDOOR, Ingredient.ofItems(ModBlocks.MAPLE_PLANKS))
                .criterion("has_planks", InventoryChangedCriterion.Conditions.items(ModBlocks.MAPLE_PLANKS))
                .offerTo(exporter);
        createDoorRecipe(ModBlocks.MAPLE_DOOR, Ingredient.ofItems(ModBlocks.MAPLE_PLANKS))
                .criterion("has_planks", InventoryChangedCriterion.Conditions.items(ModBlocks.MAPLE_PLANKS))
                .offerTo(exporter);
        createPressurePlateRecipe(RecipeCategory.REDSTONE, ModBlocks.MAPLE_PRESSURE_PLATE, Ingredient.ofItems(ModBlocks.MAPLE_PLANKS))
                .criterion("has_planks", InventoryChangedCriterion.Conditions.items(ModBlocks.MAPLE_PLANKS))
                .offerTo(exporter);
        createStairsRecipe(ModBlocks.MAPLE_STAIRS, Ingredient.ofItems(ModBlocks.MAPLE_PLANKS))
                .criterion("has_planks", InventoryChangedCriterion.Conditions.items(ModBlocks.MAPLE_PLANKS))
                .offerTo(exporter);
        createSlabRecipe(RecipeCategory.DECORATIONS, ModBlocks.MAPLE_SLAB, Ingredient.ofItems(ModBlocks.MAPLE_PLANKS))
                .criterion("has_planks", InventoryChangedCriterion.Conditions.items(ModBlocks.MAPLE_PLANKS))
                .offerTo(exporter);
        createFenceRecipe(ModBlocks.MAPLE_FENCE, Ingredient.ofItems(ModBlocks.MAPLE_PLANKS))
                .criterion("has_planks", InventoryChangedCriterion.Conditions.items(ModBlocks.MAPLE_PLANKS))
                .offerTo(exporter);
        createFenceGateRecipe(ModBlocks.MAPLE_FENCE_GATE, Ingredient.ofItems(ModBlocks.MAPLE_PLANKS))
                .criterion("has_planks", InventoryChangedCriterion.Conditions.items(ModBlocks.MAPLE_PLANKS))
                .offerTo(exporter);
        offerBoatRecipe(exporter, ModItems.MAPLE_BOAT, ModBlocks.MAPLE_PLANKS);
        offerChestBoatRecipe(exporter, ModItems.MAPLE_CHEST_BOAT, ModItems.MAPLE_BOAT);

        createSignRecipe(ModItems.MAPLE_SIGN, Ingredient.ofItems(ModBlocks.MAPLE_PLANKS))
                .criterion("has_planks", InventoryChangedCriterion.Conditions.items(ModBlocks.MAPLE_PLANKS))
                .offerTo(exporter);
        offerHangingSignRecipe(exporter, ModItems.MAPLE_HANGING_SIGN, ModBlocks.STRIPPED_MAPLE_LOG);
    }
}
