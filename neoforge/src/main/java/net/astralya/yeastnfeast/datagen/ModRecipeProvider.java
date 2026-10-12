package net.astralya.yeastnfeast.datagen;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.datagen.custom.CheesePressRecipeBuilder;
import net.astralya.yeastnfeast.datagen.custom.KegRecipeBuilder;
import net.astralya.yeastnfeast.item.ModItems;
import net.astralya.yeastnfeast.util.ModTags;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
        // Shaped Recipes for Food Items
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.TANKARD.get())
                .pattern("S S")
                .pattern("PSP")
                .define('S', ItemTags.PLANKS)
                .define('P', Items.IRON_NUGGET)
                .unlockedBy("has_iron_nugget", inventoryTrigger(ItemPredicate.Builder.item().of(Items.IRON_NUGGET).build()))
                .save(recipeOutput);ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.TANKARD.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.ELDERBERRY_PIE.get())
                .pattern(" S ")
                .pattern("STS")
                .pattern("PHP")
                .define('P', ModTags.Items.CROPS_GRAIN)
                .define('S', ModItems.ELDERBERRIES.get())
                .define('T', Items.SUGAR)
                .define('H', ModTags.Items.FOODS_MILK)
                .unlockedBy("has_elderberries", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ELDERBERRIES.get()).build()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.ROSE_TART.get())
                .pattern(" S ")
                .pattern("STS")
                .pattern("PHP")
                .define('P', ModTags.Items.CROPS_GRAIN)
                .define('S', ModItems.ROSE_HIPS.get())
                .define('T', Items.SUGAR)
                .define('H', ModTags.Items.FOODS_MILK)
                .unlockedBy("has_rose_hips", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ROSE_HIPS.get()).build()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.APPLE_PIE.get())
                .pattern(" S ")
                .pattern("STS")
                .pattern("PHP")
                .define('P', ModTags.Items.CROPS_GRAIN)
                .define('S', Items.APPLE)
                .define('T', Items.SUGAR)
                .define('H', ModTags.Items.FOODS_MILK)
                .unlockedBy("has_apple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.APPLE).build()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.BERRY_ROLL.get())
                .pattern(" S ")
                .pattern("SPS")
                .pattern("PHP")
                .define('P', ModTags.Items.CROPS_GRAIN)
                .define('S', ModItems.HAWTHORN_BERRIES.get())
                .define('H', ModTags.Items.FOODS_MILK)
                .unlockedBy("has_hawthorn_berries", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.HAWTHORN_BERRIES.get()).build()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.MINTED_CHEESE_TART.get())
                .pattern(" R ")
                .pattern("STS")
                .pattern("PHP")
                .define('P', ModTags.Items.CROPS_GRAIN)
                .define('S', ModItems.FRESHWHEEL_SLICE.get())
                .define('R', ModItems.MINT.get())
                .define('T', Items.SUGAR)
                .define('H', ModTags.Items.FOODS_MILK)
                .unlockedBy("has_freshwheel_slice", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.FRESHWHEEL_SLICE.get()).build()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.QUICHE.get())
                .pattern(" R ")
                .pattern("STS")
                .pattern("PHP")
                .define('P', ModTags.Items.CROPS_GRAIN)
                .define('S', ModTags.Items.FOODS_CHEESE)
                .define('R', ModItems.GARLIC.get())
                .define('T', Items.EGG)
                .define('H', ModTags.Items.FOODS_MILK)
                .unlockedBy("has_cheese_slice", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.CHEESE_SLICE.get()).build()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.TREE_TAP.get())
                .pattern(" H")
                .pattern("PS")
                .define('P', Items.STICK)
                .define('S', ItemTags.PLANKS)
                .define('H', Items.IRON_NUGGET)
                .unlockedBy("has_hawthorn_berries", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.HAWTHORN_BERRIES.get()).build()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.KEG.get())
                .pattern("PSP")
                .define('P', Items.STICK)
                .define('S', Blocks.BARREL)
                .unlockedBy("has_barrel", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Blocks.BARREL).build()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.JAR.get(), 3)
                .pattern("PSP")
                .pattern(" P ")
                .define('P', Blocks.GLASS)
                .define('S', ItemTags.PLANKS)
                .unlockedBy("has_glass", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Blocks.GLASS).build()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModBlocks.CHEESE_PRESS.get())
                .pattern(" P ")
                .pattern("PSP")
                .pattern("HHH")
                .define('P', Items.STICK)
                .define('S', Items.CHAIN)
                .define('H', ItemTags.PLANKS)
                .unlockedBy("has_chains", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.CHAIN).build()))
                .save(recipeOutput);

        // Shapeless Recipe
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.YEAST.get())
                .requires(ModTags.Items.MUSHROOMS)
                .requires(ModTags.Items.MUSHROOMS)
                .unlockedBy("has_mushroom", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModTags.Items.MUSHROOMS).build()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MILK_BOTTLE.get(), 4)
                .requires(Items.MILK_BUCKET)
                .requires(Items.GLASS_BOTTLE)
                .requires(Items.GLASS_BOTTLE)
                .requires(Items.GLASS_BOTTLE)
                .requires(Items.GLASS_BOTTLE)
                .unlockedBy("has_milk_bucket", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.MILK_BUCKET).build()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.MILK_BUCKET)
                .requires(Items.BUCKET)
                .requires(ModItems.MILK_BOTTLE.get())
                .requires(ModItems.MILK_BOTTLE.get())
                .requires(ModItems.MILK_BOTTLE.get())
                .requires(ModItems.MILK_BOTTLE.get())
                .unlockedBy("has_milk_bottle", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.MILK_BOTTLE.get()).build()))
                .save(recipeOutput);
        
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RENNET.get())
                .requires(ModBlocks.THISTLE.get())
                .requires(Items.ROTTEN_FLESH)
                .requires(Items.GLASS_BOTTLE)
                .unlockedBy("has_thistle", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.THISTLE.get()).build()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.SWEET_PORRIDGE.get())
                .requires(Items.BOWL)
                .requires(ModItems.MOLASSES.get())
                .requires(ModTags.Items.FOODS_MILK)
                .requires(Items.WHEAT)
                .requires(ModTags.Items.FOODS_BERRY)
                .unlockedBy("has_molasses", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.MOLASSES.get()).build()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.SPICED_PORRIDGE.get())
                .requires(Items.BOWL)
                .requires(ModItems.GARLIC.get())
                .requires(ModTags.Items.FOODS_MILK)
                .requires(ModItems.RYE.get())
                .requires(ModItems.GINGER.get())
                .unlockedBy("has_rye", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.RYE.get()).build()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.BARLEY_AND_BEEF_STEW.get())
                .requires(Items.BOWL)
                .requires(ModItems.BARLEY.get())
                .requires(ModTags.Items.FOODS_VEGETABLE)
                .requires(ModTags.Items.FOODS_VEGETABLE)
                .requires(ModTags.Items.COOKED_BEEF)
                .unlockedBy("has_cooked_beef", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.COOKED_BEEF).build()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.MEAD_BRAISED_PORK.get())
                .requires(Items.BOWL)
                .requires(ModItems.HONEY_MEAD.get())
                .requires(ModTags.Items.FOODS_VEGETABLE)
                .requires(ModItems.BARLEY.get())
                .requires(ModTags.Items.COOKED_PORK)
                .unlockedBy("has_cooked_porkchop", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.COOKED_PORKCHOP).build()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.HERBAL_COD.get())
                .requires(Items.BOWL)
                .requires(ModItems.MINT.get())
                .requires(ModItems.GINGER.get())
                .requires(ModItems.LEMON.get())
                .requires(ModTags.Items.COOKED_COD)
                .unlockedBy("has_cooked_cod", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.COOKED_COD).build()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.LEMON_GLAZED_CHICKEN.get())
                .requires(Items.BOWL)
                .requires(Items.HONEY_BOTTLE)
                .requires(ModItems.GARLIC.get())
                .requires(ModItems.LEMON.get())
                .requires(ModTags.Items.COOKED_CHICKEN)
                .unlockedBy("has_cooked_chicken", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.COOKED_CHICKEN).build()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.FORAGER_FEAST.get())
                .requires(Items.BOWL)
                .requires(ModItems.RYE.get())
                .requires(ModItems.MINT.get())
                .requires(ModItems.ELDERBERRIES.get())
                .requires(ModTags.Items.COOKED_MUTTON)
                .unlockedBy("has_cooked_mutton", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.COOKED_MUTTON).build()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.MAPLE_GLAZED_RABBIT.get())
                .requires(Items.BOWL)
                .requires(ModItems.MAPLE_SYRUP.get())
                .requires(ModItems.BARLEY.get())
                .requires(Items.BEETROOT)
                .requires(Items.COOKED_RABBIT)
                .unlockedBy("has_cooked_rabbit", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.COOKED_RABBIT).build()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.SALMON_CHOWDER.get())
                .requires(Items.BOWL)
                .requires(ModItems.LEMON.get())
                .requires(ModTags.Items.FOODS_MILK)
                .requires(Items.KELP)
                .requires(ModTags.Items.COOKED_SALMON)
                .unlockedBy("has_cooked_salmon", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.COOKED_SALMON).build()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.STUFFED_RYE_DUMPLINGS.get())
                .requires(Items.BOWL)
                .requires(ModItems.RYE.get())
                .requires(ModItems.RYE.get())
                .requires(ModItems.GINGER.get())
                .requires(ModItems.SHARPWHEEL_SLICE.get())
                .requires(ModItems.SHARPWHEEL_SLICE.get())
                .unlockedBy("has_sharpwheel_slice", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.SHARPWHEEL_SLICE.get()).build()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.DUSKWHEEL_SKEWER.get())
                .requires(Items.STICK)
                .requires(ModItems.GARLIC.get())
                .requires(ModTags.Items.COOKED_CHICKEN)
                .requires(ModItems.DUSKWHEEL_SLICE.get())
                .requires(ModItems.DUSKWHEEL_SLICE.get())
                .unlockedBy("has_duskwheel_slice", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.DUSKWHEEL_SLICE.get()).build()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.CHEESE_SOUP.get())
                .requires(Items.BOWL)
                .requires(ModTags.Items.FOODS_CHEESE)
                .requires(ModTags.Items.FOODS_CHEESE)
                .requires(ModTags.Items.FOODS_BREAD)
                .requires(ModItems.RYE.get())
                .requires(ModItems.GARLIC.get())
                .unlockedBy("has_cheese_slice", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.CHEESE_SLICE.get()).build()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.BARLEY_BREAD.get())
                .requires(ModItems.BARLEY.get())
                .requires(ModItems.BARLEY.get())
                .requires(ModTags.Items.FOODS_MILK)
                .requires(ModItems.YEAST.get())
                .unlockedBy("has_barley", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.BARLEY.get()).build()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.RYE_BREAD.get())
                .requires(ModItems.RYE.get())
                .requires(ModItems.RYE.get())
                .requires(ModTags.Items.FOODS_MILK)
                .requires(ModItems.YEAST.get())
                .unlockedBy("has_rye", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.RYE.get()).build()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.MOLASSES_BREAD.get())
                .requires(ModTags.Items.CROPS_GRAIN)
                .requires(ModTags.Items.CROPS_GRAIN)
                .requires(ModItems.MOLASSES.get())
                .requires(ModItems.YEAST.get())
                .unlockedBy("has_molasses", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.RYE.get()).build()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RYE_SEEDS.get())
                .requires(ModItems.RYE.get())
                .unlockedBy("has_rye", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.RYE.get()).build()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BARLEY_SEEDS.get())
                .requires(ModItems.BARLEY.get())
                .unlockedBy("has_barley", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.BARLEY.get()).build()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.LEMON_SAPLING.get())
                .requires(ModItems.LEMON.get())
                .requires(Blocks.OAK_SAPLING)
                .unlockedBy("has_lemon", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.LEMON.get()).build()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.HAWTHORN_SAPLING.get())
                .requires(ModItems.HAWTHORN_BERRIES.get())
                .requires(Blocks.OAK_SAPLING)
                .unlockedBy("has_hawthorn_berries", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.HAWTHORN_BERRIES.get()).build()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MOLASSES.get())
                .requires(Items.GLASS_BOTTLE)
                .requires(Items.SUGAR_CANE)
                .requires(Items.SUGAR_CANE)
                .requires(Items.SUGAR_CANE)
                .unlockedBy("has_sugar_cane", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.SUGAR_CANE).build()))
                .save(recipeOutput);

        /*ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.HOMESTEADERS_HANDBOOK.get())
                .requires(Items.BOOK)
                .requires(Items.WHEAT)
                .unlockedBy("has_book",
                        inventoryTrigger(ItemPredicate.Builder.item().of(Items.BOOK).build()))
                .save(recipeOutput);*/

        // Convertible for Storage Bags
        nineBlockStorageRecipes(recipeOutput, RecipeCategory.MISC, ModItems.ELDERBERRIES.get(), RecipeCategory.MISC, ModBlocks.BAG_OF_ELDERBERRIES.get(),
                "yeastnfeast:elderberries", "elderberries","yeastnfeast:bag_of_elderberries", "elderberries");
        nineBlockStorageRecipes(recipeOutput, RecipeCategory.MISC, ModItems.GARLIC.get(), RecipeCategory.MISC, ModBlocks.BAG_OF_GARLIC.get(),
                "yeastnfeast:garlic", "garlic","yeastnfeast:bag_of_garlic", "garlic");
        nineBlockStorageRecipes(recipeOutput, RecipeCategory.MISC, ModItems.GINGER.get(), RecipeCategory.MISC, ModBlocks.BAG_OF_GINGER.get(),
                "yeastnfeast:ginger", "ginger","yeastnfeast:bag_of_ginger", "ginger");
        nineBlockStorageRecipes(recipeOutput, RecipeCategory.MISC, ModItems.HAWTHORN_BERRIES.get(), RecipeCategory.MISC, ModBlocks.BAG_OF_HAWTHORN_BERRIES.get(),
                "yeastnfeast:hawthorn_berries", "hawthorn_berries","yeastnfeast:bag_of_hawthorn_berries", "hawthorn_berries");
        nineBlockStorageRecipes(recipeOutput, RecipeCategory.MISC, ModItems.LEMON.get(), RecipeCategory.MISC, ModBlocks.BAG_OF_LEMON.get(),
                "yeastnfeast:lemon", "lemon","yeastnfeast:bag_of_lemon", "lemon");
        nineBlockStorageRecipes(recipeOutput, RecipeCategory.MISC, ModItems.MINT.get(), RecipeCategory.MISC, ModBlocks.BAG_OF_MINT.get(),
                "yeastnfeast:mint", "mint","yeastnfeast:bag_of_mint", "mint");
        nineBlockStorageRecipes(recipeOutput, RecipeCategory.MISC, ModItems.ROSE_HIPS.get(), RecipeCategory.MISC, ModBlocks.BAG_OF_ROSE_HIPS.get(),
                "yeastnfeast:rose_hips", "rose_hips","yeastnfeast:bag_of_rose_hips", "rose_hips");
        nineBlockStorageRecipes(recipeOutput, RecipeCategory.MISC, ModBlocks.THISTLE.get(), RecipeCategory.MISC, ModBlocks.BAG_OF_THISTLE.get(),
                "yeastnfeast:thistle", "thistle","yeastnfeast:bag_of_thistle", "thistle");
        nineBlockStorageRecipes(recipeOutput, RecipeCategory.MISC, ModItems.BARLEY.get(), RecipeCategory.MISC, ModBlocks.BARLEY_BLOCK.get(),
                "yeastnfeast:barley", "barley","yeastnfeast:barley_block", "barley");
        nineBlockStorageRecipes(recipeOutput, RecipeCategory.MISC, ModItems.RYE.get(), RecipeCategory.MISC, ModBlocks.RYE_BLOCK.get(),
                "yeastnfeast:rye", "rye","yeastnfeast:rye_block", "rye");

        // Mead recipes
        KegRecipeBuilder.kegRecipe(new ItemStack(ModItems.HONEY_MEAD.get()))
                .extractionContainer(ModItems.TANKARD.get())
                .servings(4)
                .liquidColor(0xD6AA45)
                .addIngredient(Items.APPLE)
                .addIngredient(Items.HONEY_BOTTLE)
                .addIngredient(Items.WHEAT)
                .yeast(ModItems.YEAST.get())
                .experience(0.3f)
                .brewTime(7200)
                .unlockedByItems("has_tankard", ModItems.TANKARD.get())
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "honey_mead_from_keg"));

        KegRecipeBuilder.kegRecipe(new ItemStack(ModItems.MOLASSES_MEAD.get()))
                .extractionContainer(ModItems.TANKARD.get())
                .servings(4)
                .liquidColor(0x79543C)
                .addIngredient(ModItems.BARLEY.get())
                .addIngredient(ModItems.MOLASSES.get())
                .addIngredient(Items.SUGAR_CANE)
                .yeast(ModItems.YEAST.get())
                .experience(0.3f)
                .brewTime(7200)
                .unlockedByItems("has_tankard", ModItems.TANKARD.get())
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "molasses_mead_from_keg"));

        KegRecipeBuilder.kegRecipe(new ItemStack(ModItems.SOUR_MEAD.get()))
                .extractionContainer(ModItems.TANKARD.get())
                .servings(4)
                .liquidColor(0x92764B)
                .addIngredient(ModItems.ELDERBERRIES.get())
                .addIngredient(ModItems.LEMON.get())
                .addIngredient(ModItems.GINGER.get())
                .yeast(ModItems.YEAST.get())
                .experience(0.3f)
                .brewTime(7200)
                .unlockedByItems("has_tankard", ModItems.TANKARD.get())
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "sour_mead_from_keg"));

        KegRecipeBuilder.kegRecipe(new ItemStack(ModItems.THORNBERRY_MEAD.get()))
                .extractionContainer(ModItems.TANKARD.get())
                .servings(4)
                .liquidColor(0xA45C72)
                .addIngredient(ModItems.HAWTHORN_BERRIES.get())
                .addIngredient(ModItems.ELDERBERRIES.get())
                .addIngredient(Items.SWEET_BERRIES)
                .yeast(ModItems.YEAST.get())
                .experience(0.3f)
                .brewTime(7200)
                .unlockedByItems("has_tankard", ModItems.TANKARD.get())
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "thornberry_mead_from_keg"));

        KegRecipeBuilder.kegRecipe(new ItemStack(ModItems.BLOSSOM_MEAD.get()))
                .extractionContainer(ModItems.TANKARD.get())
                .servings(4)
                .liquidColor(0xA45C72)
                .addIngredient(ModItems.ROSE_HIPS.get())
                .addIngredient(ModItems.MAPLE_SYRUP.get())
                .addIngredient(ModItems.MINT.get())
                .yeast(ModItems.YEAST.get())
                .experience(0.3f)
                .brewTime(7200)
                .unlockedByItems("has_tankard", ModItems.TANKARD.get())
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "blossom_mead_from_keg"));

        KegRecipeBuilder.kegRecipe(new ItemStack(ModItems.AMBER_MEAD.get()))
                .extractionContainer(ModItems.TANKARD.get())
                .servings(4)
                .liquidColor(0xC58B38)
                .addIngredient(ModItems.RYE.get())
                .addIngredient(ModItems.MAPLE_SYRUP.get())
                .addIngredient(ModItems.ROSE_HIPS.get())
                .yeast(ModItems.YEAST.get())
                .experience(0.3f)
                .brewTime(7200)
                .unlockedByItems("has_tankard", ModItems.TANKARD.get())
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "amber_mead_from_keg"));

        // Jam recipes
        KegRecipeBuilder.kegRecipe(new ItemStack(ModItems.APPLE_JAM.get()))
                .extractionContainer(ModItems.JAR.get())
                .servings(1)
                .liquidColor(0xC58B38)
                .addIngredient(Items.APPLE)
                .addIngredient(Items.APPLE)
                .addIngredient(Items.APPLE)
                .yeast(Items.SUGAR)
                .experience(0.3f)
                .brewTime(3600)
                .unlockedByItems("has_jar", ModItems.JAR.get())
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "apple_jam_from_keg"));

        KegRecipeBuilder.kegRecipe(new ItemStack(ModItems.GLOW_BERRIES_JAM.get()))
                .extractionContainer(ModItems.JAR.get())
                .servings(1)
                .liquidColor(0xD6AA45)
                .addIngredient(Items.GLOW_BERRIES)
                .addIngredient(Items.GLOW_BERRIES)
                .addIngredient(Items.GLOW_BERRIES)
                .yeast(Items.SUGAR)
                .experience(0.3f)
                .brewTime(3600)
                .unlockedByItems("has_jar", ModItems.JAR.get())
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "glow_berries_jam_from_keg"));

        KegRecipeBuilder.kegRecipe(new ItemStack(ModItems.CHORUS_FRUIT_JAM.get()))
                .extractionContainer(ModItems.JAR.get())
                .servings(1)
                .liquidColor(0x78548C)
                .addIngredient(Items.CHORUS_FRUIT)
                .addIngredient(Items.CHORUS_FRUIT)
                .addIngredient(Items.CHORUS_FRUIT)
                .yeast(Items.SUGAR)
                .experience(0.3f)
                .brewTime(3600)
                .unlockedByItems("has_jar", ModItems.JAR.get())
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "chorus_fruit_jam_from_keg"));

        KegRecipeBuilder.kegRecipe(new ItemStack(ModItems.ELDERBERRIES_JAM.get()))
                .extractionContainer(ModItems.JAR.get())
                .servings(1)
                .liquidColor(0x78548C)
                .addIngredient(ModItems.ELDERBERRIES.get())
                .addIngredient(ModItems.ELDERBERRIES.get())
                .addIngredient(ModItems.ELDERBERRIES.get())
                .yeast(Items.SUGAR)
                .experience(0.3f)
                .brewTime(3600)
                .unlockedByItems("has_jar", ModItems.JAR.get())
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "elderberries_jam_from_keg"));

        KegRecipeBuilder.kegRecipe(new ItemStack(ModItems.GOLDEN_APPLE_JAM.get()))
                .extractionContainer(ModItems.JAR.get())
                .servings(1)
                .liquidColor(0xD6AA45)
                .addIngredient(Items.GOLDEN_APPLE)
                .addIngredient(Items.GOLDEN_APPLE)
                .addIngredient(Items.GOLDEN_APPLE)
                .yeast(Items.SUGAR)
                .experience(0.3f)
                .brewTime(3600)
                .unlockedByItems("has_jar", ModItems.JAR.get())
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "golden_apple_jam_from_keg"));

        KegRecipeBuilder.kegRecipe(new ItemStack(ModItems.HAWTHORN_BERRIES_JAM.get()))
                .extractionContainer(ModItems.JAR.get())
                .servings(1)
                .liquidColor(0xA45C72)
                .addIngredient(ModItems.HAWTHORN_BERRIES.get())
                .addIngredient(ModItems.HAWTHORN_BERRIES.get())
                .addIngredient(ModItems.HAWTHORN_BERRIES.get())
                .yeast(Items.SUGAR)
                .experience(0.3f)
                .brewTime(3600)
                .unlockedByItems("has_jar", ModItems.JAR.get())
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "hawthorn_berries_jam_from_keg"));

        KegRecipeBuilder.kegRecipe(new ItemStack(ModItems.LEMON_JAM.get()))
                .extractionContainer(ModItems.JAR.get())
                .servings(1)
                .liquidColor(0xD6AA45)
                .addIngredient(ModItems.LEMON.get())
                .addIngredient(ModItems.LEMON.get())
                .addIngredient(ModItems.LEMON.get())
                .yeast(Items.SUGAR)
                .experience(0.3f)
                .brewTime(3600)
                .unlockedByItems("has_jar", ModItems.JAR.get())
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "lemon_jam_from_keg"));

        KegRecipeBuilder.kegRecipe(new ItemStack(ModItems.ROSE_HIPS_JAM.get()))
                .extractionContainer(ModItems.JAR.get())
                .servings(1)
                .liquidColor(0xA45C72)
                .addIngredient(ModItems.ROSE_HIPS.get())
                .addIngredient(ModItems.ROSE_HIPS.get())
                .addIngredient(ModItems.ROSE_HIPS.get())
                .yeast(Items.SUGAR)
                .experience(0.3f)
                .brewTime(3600)
                .unlockedByItems("has_jar", ModItems.JAR.get())
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "rose_hips_jam_from_keg"));

        KegRecipeBuilder.kegRecipe(new ItemStack(ModItems.SWEET_BERRIES_JAM.get()))
                .extractionContainer(ModItems.JAR.get())
                .servings(1)
                .liquidColor(0xA45C72)
                .addIngredient(Items.SWEET_BERRIES)
                .addIngredient(Items.SWEET_BERRIES)
                .addIngredient(Items.SWEET_BERRIES)
                .yeast(Items.SUGAR)
                .experience(0.3f)
                .brewTime(3600)
                .unlockedByItems("has_jar", ModItems.JAR.get())
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "sweet_berries_jam_from_keg"));

        KegRecipeBuilder.kegRecipe(new ItemStack(ModItems.MELON_JAM.get()))
                .extractionContainer(ModItems.JAR.get())
                .servings(1)
                .liquidColor(0xA45C72)
                .addIngredient(Items.MELON_SLICE)
                .addIngredient(Items.MELON_SLICE)
                .addIngredient(Items.MELON_SLICE)
                .yeast(Items.SUGAR)
                .experience(0.3f)
                .brewTime(3600)
                .unlockedByItems("has_jar", ModItems.JAR.get())
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "melon_jam_from_keg"));

        // Cheese Press
        CheesePressRecipeBuilder.cheesePress(new ItemStack(ModItems.CHEESE_WHEEL.get()))
                .addIngredient(ModTags.Items.FOODS_MILK)
                .addIngredient(ModItems.RENNET.get())
                .experience(0.2f)
                .pressTime(7200)
                .unlockedByItems("has_rennet", ModItems.RENNET.get())
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("yeastnfeast",
                        ModItems.CHEESE_WHEEL.getId().getPath() + "_from_cheese_press"));

        CheesePressRecipeBuilder.cheesePress(new ItemStack(ModItems.DUSKWHEEL.get()))
                .addIngredient(ModTags.Items.FOODS_MILK)
                .addIngredient(ModItems.RENNET.get())
                .flavor(ModItems.ELDERBERRIES.get())
                .experience(0.2f)
                .pressTime(7200)
                .unlockedByItems("has_rennet", ModItems.RENNET.get())
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("yeastnfeast",
                        ModItems.DUSKWHEEL.getId().getPath() + "_from_cheese_press"));

        CheesePressRecipeBuilder.cheesePress(new ItemStack(ModItems.SHARPWHEEL.get()))
                .addIngredient(ModTags.Items.FOODS_MILK)
                .addIngredient(ModItems.RENNET.get())
                .flavor(ModItems.GARLIC.get())
                .experience(0.2f)
                .pressTime(7200)
                .unlockedByItems("has_rennet", ModItems.RENNET.get())
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("yeastnfeast",
                        ModItems.SHARPWHEEL.getId().getPath() + "_from_cheese_press"));

        CheesePressRecipeBuilder.cheesePress(new ItemStack(ModItems.FRESHWHEEL.get()))
                .addIngredient(ModTags.Items.FOODS_MILK)
                .addIngredient(ModItems.RENNET.get())
                .flavor(ModItems.MINT.get())
                .experience(0.2f)
                .pressTime(7200)
                .unlockedByItems("has_rennet", ModItems.RENNET.get())
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath("yeastnfeast",
                        ModItems.FRESHWHEEL.getId().getPath() + "_from_cheese_press"));

        // Recipes for Wood-related Blocks & Items
        planksFromLog(recipeOutput, ModBlocks.MAPLE_PLANKS.get(), ModTags.Items.MAPLE_LOGS, 4);
        oneToOneConversionRecipe(recipeOutput, ModBlocks.MAPLE_BUTTON.get(), ModBlocks.MAPLE_PLANKS.get(), "wooden_button");
        trapdoorBuilder(ModBlocks.MAPLE_TRAPDOOR.get(), Ingredient.of(ModBlocks.MAPLE_PLANKS.get()))
                .unlockedBy("has_planks", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.MAPLE_PLANKS.get()).build()))
                .save(recipeOutput);
        doorBuilder(ModBlocks.MAPLE_DOOR.get(), Ingredient.of(ModBlocks.MAPLE_PLANKS.get()))
                .unlockedBy("has_planks", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.MAPLE_PLANKS.get()).build()))
                .save(recipeOutput);
        pressurePlateBuilder(RecipeCategory.REDSTONE, ModBlocks.MAPLE_PRESSURE_PLATE.get(), Ingredient.of(ModBlocks.MAPLE_PLANKS.get()))
                .unlockedBy("has_planks", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.MAPLE_PLANKS.get()).build()))
                .save(recipeOutput);
        stairBuilder(ModBlocks.MAPLE_STAIRS.get(), Ingredient.of(ModBlocks.MAPLE_PLANKS.get()))
                .unlockedBy("has_planks", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.MAPLE_PLANKS.get()).build()))
                .save(recipeOutput);
        slabBuilder(RecipeCategory.DECORATIONS, ModBlocks.MAPLE_SLAB.get(), Ingredient.of(ModBlocks.MAPLE_PLANKS.get()))
                .unlockedBy("has_planks", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.MAPLE_PLANKS.get()).build()))
                .save(recipeOutput);
        fenceBuilder(ModBlocks.MAPLE_FENCE.get(), Ingredient.of(ModBlocks.MAPLE_PLANKS.get()))
                .unlockedBy("has_planks", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.MAPLE_PLANKS.get()).build()))
                .save(recipeOutput);
        fenceGateBuilder(ModBlocks.MAPLE_FENCE_GATE.get(), Ingredient.of(ModBlocks.MAPLE_PLANKS.get()))
                .unlockedBy("has_planks", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.MAPLE_PLANKS.get()).build()))
                .save(recipeOutput);
        signBuilder(ModBlocks.MAPLE_SIGN.get(), Ingredient.of(ModBlocks.MAPLE_PLANKS.get()))
                .unlockedBy("has_planks", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.MAPLE_PLANKS.get()).build()))
                .save(recipeOutput);
        hangingSign(recipeOutput, ModItems.MAPLE_HANGING_SIGN.get(), ModBlocks.STRIPPED_MAPLE_LOG.get());
        woodFromLogs(recipeOutput, ModBlocks.MAPLE_WOOD.get(), ModBlocks.MAPLE_LOG.get());

        chestBoat(recipeOutput, ModItems.MAPLE_CHEST_BOAT.get(), ModItems.MAPLE_BOAT.get());
        woodenBoat(recipeOutput, ModItems.MAPLE_BOAT.get(), ModBlocks.MAPLE_PLANKS.get());

        FeastTableData.VALUES.forEach(data -> feastTable(recipeOutput, data));
    }

    private void feastTable(RecipeOutput recipeOutput, FeastTableData data) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, data.table().get())
                .pattern("PPP")
                .pattern("L L")
                .define('P', data.planksIngredient().get())
                .define('L', data.logIngredient().get())
                .unlockedBy("has_" + data.wood() + "_planks", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(data.planksIngredient().get()).build()))
                .save(recipeOutput);
    }
}
