package net.astralya.yeastnfeast.datagen;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.datagen.custom.CheesePressRecipeBuilder;
import net.astralya.yeastnfeast.datagen.custom.KegRecipeBuilder;
import net.astralya.yeastnfeast.item.ModItems;
import net.astralya.yeastnfeast.util.ModTags;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        // Shaped Recipes for Food Items ' '
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.TANKARD.get())
                .pattern("S S")
                .pattern("PSP")
                .define('S', ItemTags.PLANKS)
                .define('P', Items.IRON_NUGGET)
                .unlockedBy("has_iron_nugget", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.IRON_NUGGET).build()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.ELDERBERRY_PIE.get())
                .pattern(" S ")
                .pattern("STS")
                .pattern("PHP")
                .define('P', ModTags.Items.GRAINS_WHEAT)
                .define('S', ModItems.ELDERBERRIES.get())
                .define('T', Items.SUGAR)
                .define('H', ModTags.Items.MILK)
                .unlockedBy("has_elderberries", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ELDERBERRIES.get()).build()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.ROSE_TART.get())
                .pattern(" S ")
                .pattern("STS")
                .pattern("PHP")
                .define('P', ModTags.Items.GRAINS_WHEAT)
                .define('S', ModItems.ROSE_HIPS.get())
                .define('T', Items.SUGAR)
                .define('H', ModTags.Items.MILK)
                .unlockedBy("has_rose_hips", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.ROSE_HIPS.get()).build()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.APPLE_PIE.get())
                .pattern(" S ")
                .pattern("STS")
                .pattern("PHP")
                .define('P', ModTags.Items.GRAINS_WHEAT)
                .define('S', Items.APPLE)
                .define('T', Items.SUGAR)
                .define('H', ModTags.Items.MILK)
                .unlockedBy("has_apple", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.APPLE).build()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.BERRY_ROLL.get())
                .pattern(" S ")
                .pattern("SPS")
                .pattern("PHP")
                .define('P', ModTags.Items.GRAINS_WHEAT)
                .define('S', ModItems.HAWTHORN_BERRIES.get())
                .define('H', ModTags.Items.MILK)
                .unlockedBy("has_hawthorn_berries", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.HAWTHORN_BERRIES.get()).build()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.TREE_TAP.get())
                .pattern(" H")
                .pattern("PS")
                .define('P', Items.STICK)
                .define('S', ItemTags.PLANKS)
                .define('H', Items.IRON_NUGGET)
                .unlockedBy("has_hawthorn_berries", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.HAWTHORN_BERRIES.get()).build()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.KEG.get())
                .pattern("PSP")
                .define('P', Items.STICK)
                .define('S', Blocks.BARREL)
                .unlockedBy("has_barrel", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Blocks.BARREL).build()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.JAR.get(), 3)
                .pattern("PSP")
                .pattern(" P ")
                .define('P', Blocks.GLASS)
                .define('S', ItemTags.PLANKS)
                .unlockedBy("has_glass", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Blocks.GLASS).build()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.MINTED_CHEESE_TART.get())
                .pattern(" R ")
                .pattern("STS")
                .pattern("PHP")
                .define('P', ModTags.Items.GRAINS_WHEAT)
                .define('S', ModItems.FRESHWHEEL_SLICE.get())
                .define('R', ModItems.MINT.get())
                .define('T', Items.SUGAR)
                .define('H', ModTags.Items.MILK)
                .unlockedBy("has_freshwheel_slice", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.FRESHWHEEL_SLICE.get()).build()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.QUICHE.get())
                .pattern(" R ")
                .pattern("STS")
                .pattern("PHP")
                .define('P', ModTags.Items.GRAINS_WHEAT)
                .define('S', ModTags.Items.CHEESE)
                .define('R', ModItems.GARLIC.get())
                .define('T', Items.EGG)
                .define('H', ModTags.Items.MILK)
                .unlockedBy("has_cheese_slice", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.CHEESE_SLICE.get()).build()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CHEESE_PRESS.get())
                .pattern(" P ")
                .pattern("PSP")
                .pattern("HHH")
                .define('P', Items.STICK)
                .define('S', Items.CHAIN)
                .define('H', ItemTags.PLANKS)
                .unlockedBy("has_chains", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.CHAIN).build()))
                .save(pWriter);

        // Shapeless Recipes
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.YEAST.get())
                .requires(Tags.Items.MUSHROOMS)
                .requires(Tags.Items.MUSHROOMS)
                .unlockedBy("has_mushroom", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Tags.Items.MUSHROOMS).build()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MILK_BOTTLE.get(), 4)
                .requires(Items.MILK_BUCKET)
                .requires(Items.GLASS_BOTTLE)
                .requires(Items.GLASS_BOTTLE)
                .requires(Items.GLASS_BOTTLE)
                .requires(Items.GLASS_BOTTLE)
                .unlockedBy("has_milk_bucket", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.MILK_BUCKET).build()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.MILK_BUCKET)
                .requires(Items.BUCKET)
                .requires(ModItems.MILK_BOTTLE.get())
                .requires(ModItems.MILK_BOTTLE.get())
                .requires(ModItems.MILK_BOTTLE.get())
                .requires(ModItems.MILK_BOTTLE.get())
                .unlockedBy("has_milk_bottle", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.MILK_BOTTLE.get()).build()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SWEET_PORRIDGE.get())
                .requires(Items.BOWL)
                .requires(ModItems.MOLASSES.get())
                .requires(ModTags.Items.MILK)
                .requires(Items.WHEAT)
                .requires(ModTags.Items.BERRIES)
                .unlockedBy("has_molasses", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.MOLASSES.get()).build()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SPICED_PORRIDGE.get())
                .requires(Items.BOWL)
                .requires(ModItems.GARLIC.get())
                .requires(ModTags.Items.MILK)
                .requires(ModItems.RYE.get())
                .requires(ModItems.GINGER.get())
                .unlockedBy("has_rye", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.RYE.get()).build()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BARLEY_AND_BEEF_STEW.get())
                .requires(Items.BOWL)
                .requires(ModItems.BARLEY.get())
                .requires(ModTags.Items.VEGETABLES)
                .requires(ModTags.Items.VEGETABLES)
                .requires(ModTags.Items.COOKED_BEEF)
                .unlockedBy("has_cooked_beef", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.COOKED_BEEF).build()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MEAD_BRAISED_PORK.get())
                .requires(Items.BOWL)
                .requires(ModItems.HONEY_MEAD.get())
                .requires(ModTags.Items.VEGETABLES)
                .requires(ModItems.BARLEY.get())
                .requires(ModTags.Items.COOKED_PORK)
                .unlockedBy("has_cooked_porkchop", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.COOKED_PORKCHOP).build()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.HERBAL_COD.get())
                .requires(Items.BOWL)
                .requires(ModItems.MINT.get())
                .requires(ModItems.GINGER.get())
                .requires(ModItems.LEMON.get())
                .requires(ModTags.Items.COOKED_COD)
                .unlockedBy("has_cooked_cod", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.COOKED_COD).build()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LEMON_GLAZED_CHICKEN.get())
                .requires(Items.BOWL)
                .requires(Items.HONEY_BOTTLE)
                .requires(ModItems.GARLIC.get())
                .requires(ModItems.LEMON.get())
                .requires(ModTags.Items.COOKED_CHICKEN)
                .unlockedBy("has_cooked_chicken", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.COOKED_CHICKEN).build()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.FORAGER_FEAST.get())
                .requires(Items.BOWL)
                .requires(ModItems.RYE.get())
                .requires(ModItems.MINT.get())
                .requires(ModItems.ELDERBERRIES.get())
                .requires(ModTags.Items.COOKED_MUTTON)
                .unlockedBy("has_cooked_mutton", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.COOKED_MUTTON).build()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MAPLE_GLAZED_RABBIT.get())
                .requires(Items.BOWL)
                .requires(ModItems.MAPLE_SYRUP.get())
                .requires(ModItems.BARLEY.get())
                .requires(Items.BEETROOT)
                .requires(Items.COOKED_RABBIT)
                .unlockedBy("has_cooked_rabbit", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.COOKED_RABBIT).build()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SALMON_CHOWDER.get())
                .requires(Items.BOWL)
                .requires(ModItems.LEMON.get())
                .requires(ModTags.Items.MILK)
                .requires(Items.KELP)
                .requires(ModTags.Items.COOKED_SALMON)
                .unlockedBy("has_cooked_salmon", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.COOKED_SALMON).build()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BARLEY_BREAD.get())
                .requires(ModItems.BARLEY.get())
                .requires(ModItems.BARLEY.get())
                .requires(ModTags.Items.MILK)
                .requires(ModItems.YEAST.get())
                .unlockedBy("has_barley", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.BARLEY.get()).build()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RYE_BREAD.get())
                .requires(ModItems.RYE.get())
                .requires(ModItems.RYE.get())
                .requires(ModTags.Items.MILK)
                .requires(ModItems.YEAST.get())
                .unlockedBy("has_rye", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.RYE.get()).build()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MOLASSES_BREAD.get())
                .requires(ModTags.Items.GRAINS_WHEAT)
                .requires(ModTags.Items.GRAINS_WHEAT)
                .requires(ModItems.MOLASSES.get())
                .requires(ModItems.YEAST.get())
                .unlockedBy("has_molasses", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.RYE.get()).build()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RYE_SEEDS.get())
                .requires(ModItems.RYE.get())
                .unlockedBy("has_rye", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.RYE.get()).build()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BARLEY_SEEDS.get())
                .requires(ModItems.BARLEY.get())
                .unlockedBy("has_barley", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.BARLEY.get()).build()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.LEMON_SAPLING.get())
                .requires(ModItems.LEMON.get())
                .requires(Blocks.OAK_SAPLING)
                .unlockedBy("has_lemon", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.LEMON.get()).build()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.HAWTHORN_SAPLING.get())
                .requires(ModItems.HAWTHORN_BERRIES.get())
                .requires(Blocks.OAK_SAPLING)
                .unlockedBy("has_hawthorn_berries", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.HAWTHORN_BERRIES.get()).build()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MOLASSES.get())
                .requires(Items.GLASS_BOTTLE)
                .requires(Items.SUGAR_CANE)
                .requires(Items.SUGAR_CANE)
                .requires(Items.SUGAR_CANE)
                .unlockedBy("has_sugar_cane", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.SUGAR_CANE).build()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RENNET.get())
                .requires(ModBlocks.THISTLE.get())
                .requires(Items.ROTTEN_FLESH)
                .requires(Items.GLASS_BOTTLE)
                .unlockedBy("has_thistle", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModBlocks.THISTLE.get().asItem()).build()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.STUFFED_RYE_DUMPLINGS.get())
                .requires(Items.BOWL)
                .requires(ModItems.RYE.get())
                .requires(ModItems.RYE.get())
                .requires(ModItems.GINGER.get())
                .requires(ModItems.SHARPWHEEL_SLICE.get())
                .requires(ModItems.SHARPWHEEL_SLICE.get())
                .unlockedBy("has_sharpwheel_slice", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.SHARPWHEEL_SLICE.get()).build()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DUSKWHEEL_SKEWER.get())
                .requires(Items.STICK)
                .requires(ModItems.GARLIC.get())
                .requires(ModTags.Items.COOKED_CHICKEN)
                .requires(ModItems.DUSKWHEEL_SLICE.get())
                .requires(ModItems.DUSKWHEEL_SLICE.get())
                .unlockedBy("has_duskwheel_slice", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.DUSKWHEEL_SLICE.get()).build()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CHEESE_SOUP.get())
                .requires(Items.BOWL)
                .requires(ModTags.Items.CHEESE)
                .requires(ModTags.Items.CHEESE)
                .requires(ModTags.Items.BREAD)
                .requires(ModItems.RYE.get())
                .requires(ModItems.GARLIC.get())
                .unlockedBy("has_cheese_slice", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.CHEESE_SLICE.get()).build()))
                .save(pWriter);


        /*ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.HOMESTEADERS_HANDBOOK.get())
                .requires(Items.BOOK)
                .requires(Items.WHEAT)
                .unlockedBy("has_book",
                        inventoryTrigger(ItemPredicate.Builder.item().of(Items.BOOK).build()))
                .save(pWriter);*/

        // Convertible for Storage Bags
        nineBlockStorageRecipes(pWriter, RecipeCategory.MISC, ModItems.ELDERBERRIES.get(), RecipeCategory.MISC, ModBlocks.BAG_OF_ELDERBERRIES.get(),
                "yeastnfeast:elderberries", "elderberries","yeastnfeast:bag_of_elderberries", "elderberries");
        nineBlockStorageRecipes(pWriter, RecipeCategory.MISC, ModItems.GARLIC.get(), RecipeCategory.MISC, ModBlocks.BAG_OF_GARLIC.get(),
                "yeastnfeast:garlic", "garlic","yeastnfeast:bag_of_garlic", "garlic");
        nineBlockStorageRecipes(pWriter, RecipeCategory.MISC, ModItems.GINGER.get(), RecipeCategory.MISC, ModBlocks.BAG_OF_GINGER.get(),
                "yeastnfeast:ginger", "ginger","yeastnfeast:bag_of_ginger", "ginger");
        nineBlockStorageRecipes(pWriter, RecipeCategory.MISC, ModItems.HAWTHORN_BERRIES.get(), RecipeCategory.MISC, ModBlocks.BAG_OF_HAWTHORN_BERRIES.get(),
                "yeastnfeast:hawthorn_berries", "hawthorn_berries","yeastnfeast:bag_of_hawthorn_berries", "hawthorn_berries");
        nineBlockStorageRecipes(pWriter, RecipeCategory.MISC, ModItems.LEMON.get(), RecipeCategory.MISC, ModBlocks.BAG_OF_LEMON.get(),
                "yeastnfeast:lemon", "lemon","yeastnfeast:bag_of_lemon", "lemon");
        nineBlockStorageRecipes(pWriter, RecipeCategory.MISC, ModItems.MINT.get(), RecipeCategory.MISC, ModBlocks.BAG_OF_MINT.get(),
                "yeastnfeast:mint", "mint","yeastnfeast:bag_of_mint", "mint");
        nineBlockStorageRecipes(pWriter, RecipeCategory.MISC, ModItems.ROSE_HIPS.get(), RecipeCategory.MISC, ModBlocks.BAG_OF_ROSE_HIPS.get(),
                "yeastnfeast:rose_hips", "rose_hips","yeastnfeast:bag_of_rose_hips", "rose_hips");
        nineBlockStorageRecipes(pWriter, RecipeCategory.MISC, ModItems.BARLEY.get(), RecipeCategory.MISC, ModBlocks.BARLEY_BLOCK.get(),
                "yeastnfeast:barley", "barley","yeastnfeast:barley_block", "barley");
        nineBlockStorageRecipes(pWriter, RecipeCategory.MISC, ModItems.RYE.get(), RecipeCategory.MISC, ModBlocks.RYE_BLOCK.get(),
                "yeastnfeast:rye", "rye","yeastnfeast:rye_block", "rye");
        nineBlockStorageRecipes(pWriter, RecipeCategory.MISC, ModBlocks.THISTLE.get().asItem(), RecipeCategory.MISC, ModBlocks.BAG_OF_THISTLE.get(),
                "yeastnfeast:thistle", "thistle", "yeastnfeast:bag_of_thistle", "thistle");

        // Recipes for Wood-related Blocks & Items
        planksFromLog(pWriter, ModBlocks.MAPLE_PLANKS.get(), ModTags.Items.MAPLE_LOGS, 4);
        oneToOneConversionRecipe(pWriter, ModBlocks.MAPLE_BUTTON.get(), ModBlocks.MAPLE_PLANKS.get(), "wooden_button");
        trapdoorBuilder(ModBlocks.MAPLE_TRAPDOOR.get(), Ingredient.of(ModBlocks.MAPLE_PLANKS.get()))
                .unlockedBy("has_planks", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.MAPLE_PLANKS.get()).build()))
                .save(pWriter);
        doorBuilder(ModBlocks.MAPLE_DOOR.get(), Ingredient.of(ModBlocks.MAPLE_PLANKS.get()))
                .unlockedBy("has_planks", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.MAPLE_PLANKS.get()).build()))
                .save(pWriter);
        pressurePlateBuilder(RecipeCategory.REDSTONE, ModBlocks.MAPLE_PRESSURE_PLATE.get(), Ingredient.of(ModBlocks.MAPLE_PLANKS.get()))
                .unlockedBy("has_planks", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.MAPLE_PLANKS.get()).build()))
                .save(pWriter);
        stairBuilder(ModBlocks.MAPLE_STAIRS.get(), Ingredient.of(ModBlocks.MAPLE_PLANKS.get()))
                .unlockedBy("has_planks", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.MAPLE_PLANKS.get()).build()))
                .save(pWriter);
        slabBuilder(RecipeCategory.DECORATIONS, ModBlocks.MAPLE_SLAB.get(), Ingredient.of(ModBlocks.MAPLE_PLANKS.get()))
                .unlockedBy("has_planks", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.MAPLE_PLANKS.get()).build()))
                .save(pWriter);
        fenceBuilder(ModBlocks.MAPLE_FENCE.get(), Ingredient.of(ModBlocks.MAPLE_PLANKS.get()))
                .unlockedBy("has_planks", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.MAPLE_PLANKS.get()).build()))
                .save(pWriter);
        fenceGateBuilder(ModBlocks.MAPLE_FENCE_GATE.get(), Ingredient.of(ModBlocks.MAPLE_PLANKS.get()))
                .unlockedBy("has_planks", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.MAPLE_PLANKS.get()).build()))
                .save(pWriter);
        signBuilder(ModBlocks.MAPLE_SIGN.get(), Ingredient.of(ModBlocks.MAPLE_PLANKS.get()))
                .unlockedBy("has_planks", inventoryTrigger(ItemPredicate.Builder.item().of(ModBlocks.MAPLE_PLANKS.get()).build()))
                .save(pWriter);
        hangingSign(pWriter, ModItems.MAPLE_HANGING_SIGN.get(), ModBlocks.STRIPPED_MAPLE_LOG.get());
        woodFromLogs(pWriter, ModBlocks.MAPLE_WOOD.get(), ModBlocks.MAPLE_LOG.get());

        chestBoat(pWriter, ModItems.MAPLE_CHEST_BOAT.get(), ModItems.MAPLE_BOAT.get());
        woodenBoat(pWriter, ModItems.MAPLE_BOAT.get(), ModBlocks.MAPLE_PLANKS.get());

        // Recipes for Cheese Press Items
        CheesePressRecipeBuilder.ofIngredients(
                        List.of(
                                CheesePressRecipeBuilder.tag(ModTags.Items.MILK_BOTTLE),
                                CheesePressRecipeBuilder.item(ModItems.RENNET.get())
                        ),
                        null,
                        ModItems.CHEESE_WHEEL.get(),
                        1,
                        0.2f,
                        7200
                )
                .unlockedBy("has_rennet", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.RENNET.get()).build()))
                .save(pWriter, new ResourceLocation(YeastNFeastMod.MODID, "cheese_wheel_from_cheese_press"));

        CheesePressRecipeBuilder.ofIngredients(
                        List.of(
                                CheesePressRecipeBuilder.tag(ModTags.Items.MILK_BOTTLE),
                                CheesePressRecipeBuilder.item(ModItems.RENNET.get())
                        ),
                        CheesePressRecipeBuilder.item(ModItems.MINT.get()),
                        ModItems.FRESHWHEEL.get(),
                        1,
                        0.2f,
                        7200
                )
                .unlockedBy("has_rennet", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.RENNET.get()).build()))
                .save(pWriter, new ResourceLocation(YeastNFeastMod.MODID, "freshwheel_from_cheese_press"));

        CheesePressRecipeBuilder.ofIngredients(
                        List.of(
                                CheesePressRecipeBuilder.tag(ModTags.Items.MILK_BOTTLE),
                                CheesePressRecipeBuilder.item(ModItems.RENNET.get())
                        ),
                        CheesePressRecipeBuilder.item(ModItems.ELDERBERRIES.get()),
                        ModItems.DUSKWHEEL.get(),
                        1,
                        0.2f,
                        7200
                )
                .unlockedBy("has_rennet", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.RENNET.get()).build()))
                .save(pWriter, new ResourceLocation(YeastNFeastMod.MODID, "duskwheel_from_cheese_press"));

        CheesePressRecipeBuilder.ofIngredients(
                        List.of(
                                CheesePressRecipeBuilder.tag(ModTags.Items.MILK_BOTTLE),
                                CheesePressRecipeBuilder.item(ModItems.RENNET.get())
                        ),
                        CheesePressRecipeBuilder.item(ModItems.GARLIC.get()),
                        ModItems.SHARPWHEEL.get(),
                        1,
                        0.2f,
                        7200
                )
                .unlockedBy("has_rennet", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.RENNET.get()).build()))
                .save(pWriter, new ResourceLocation(YeastNFeastMod.MODID, "sharpwheel_from_cheese_press"));

        // Recipes for Keg Items
        new KegRecipeBuilder(List.of(Items.APPLE, Items.HONEY_BOTTLE, Items.WHEAT),
                ModItems.TANKARD.get(), ModItems.YEAST.get(),
                ModItems.HONEY_MEAD.get(), 7200)
                .unlockedBy("has_tankard", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.TANKARD.get()).build()))
                .save(pWriter);

        new KegRecipeBuilder(List.of(ModItems.BARLEY.get(), ModItems.MOLASSES.get(), Items.SUGAR_CANE),
                ModItems.TANKARD.get(), ModItems.YEAST.get(),
                ModItems.MOLASSES_MEAD.get(), 7200)
                .unlockedBy("has_tankard", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.TANKARD.get()).build()))
                .save(pWriter);

        new KegRecipeBuilder(List.of(ModItems.ELDERBERRIES.get(), ModItems.LEMON.get(), ModItems.GINGER.get()),
                ModItems.TANKARD.get(), ModItems.YEAST.get(),
                ModItems.SOUR_MEAD.get(), 7200)
                .unlockedBy("has_tankard", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.TANKARD.get()).build()))
                .save(pWriter);

        new KegRecipeBuilder(List.of(ModItems.HAWTHORN_BERRIES.get(), ModItems.ELDERBERRIES.get(), Items.SWEET_BERRIES),
                ModItems.TANKARD.get(), ModItems.YEAST.get(),
                ModItems.THORNBERRY_MEAD.get(), 7200)
                .unlockedBy("has_tankard", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.TANKARD.get()).build()))
                .save(pWriter);

        new KegRecipeBuilder(List.of(ModItems.ROSE_HIPS.get(), ModItems.MAPLE_SYRUP.get(), ModItems.MINT.get()),
                ModItems.TANKARD.get(), ModItems.YEAST.get(),
                ModItems.BLOSSOM_MEAD.get(), 7200)
                .unlockedBy("has_tankard", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.TANKARD.get()).build()))
                .save(pWriter);

        new KegRecipeBuilder(List.of(ModItems.RYE.get(), ModItems.MAPLE_SYRUP.get(), ModItems.ROSE_HIPS.get()),
                ModItems.TANKARD.get(), ModItems.YEAST.get(),
                ModItems.AMBER_MEAD.get(), 7200)
                .unlockedBy("has_tankard", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.TANKARD.get()).build()))
                .save(pWriter);

        new KegRecipeBuilder(List.of(Items.APPLE, Items.APPLE, Items.APPLE),
                ModItems.JAR.get(), Items.SUGAR,
                ModItems.APPLE_JAM.get(), 3600)
                .unlockedBy("has_jar", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.JAR.get()).build()))
                .save(pWriter);

        new KegRecipeBuilder(List.of(Items.GLOW_BERRIES, Items.GLOW_BERRIES, Items.GLOW_BERRIES),
                ModItems.JAR.get(), Items.SUGAR,
                ModItems.GLOW_BERRIES_JAM.get(), 3600)
                .unlockedBy("has_jar", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.JAR.get()).build()))
                .save(pWriter);

        new KegRecipeBuilder(List.of(Items.CHORUS_FRUIT, Items.CHORUS_FRUIT, Items.CHORUS_FRUIT),
                ModItems.JAR.get(), Items.SUGAR,
                ModItems.CHORUS_FRUIT_JAM.get(), 3600)
                .unlockedBy("has_jar", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.JAR.get()).build()))
                .save(pWriter);

        new KegRecipeBuilder(List.of(ModItems.ELDERBERRIES.get(), ModItems.ELDERBERRIES.get(), ModItems.ELDERBERRIES.get()),
                ModItems.JAR.get(), Items.SUGAR,
                ModItems.ELDERBERRIES_JAM.get(), 3600)
                .unlockedBy("has_jar", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.JAR.get()).build()))
                .save(pWriter);

        new KegRecipeBuilder(List.of(Items.GOLDEN_APPLE, Items.GOLDEN_APPLE, Items.GOLDEN_APPLE),
                ModItems.JAR.get(), Items.SUGAR,
                ModItems.GOLDEN_APPLE_JAM.get(), 3600)
                .unlockedBy("has_jar", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.JAR.get()).build()))
                .save(pWriter);

        new KegRecipeBuilder(List.of(ModItems.HAWTHORN_BERRIES.get(), ModItems.HAWTHORN_BERRIES.get(), ModItems.HAWTHORN_BERRIES.get()),
                ModItems.JAR.get(), Items.SUGAR,
                ModItems.HAWTHORN_BERRIES_JAM.get(), 3600)
                .unlockedBy("has_jar", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.JAR.get()).build()))
                .save(pWriter);

        new KegRecipeBuilder(List.of(ModItems.LEMON.get(), ModItems.LEMON.get(), ModItems.LEMON.get()),
                ModItems.JAR.get(), Items.SUGAR,
                ModItems.LEMON_JAM.get(), 3600)
                .unlockedBy("has_jar", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.JAR.get()).build()))
                .save(pWriter);

        new KegRecipeBuilder(List.of(ModItems.ROSE_HIPS.get(), ModItems.ROSE_HIPS.get(), ModItems.ROSE_HIPS.get()),
                ModItems.JAR.get(), Items.SUGAR,
                ModItems.ROSE_HIPS_JAM.get(), 3600)
                .unlockedBy("has_jar", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.JAR.get()).build()))
                .save(pWriter);

        new KegRecipeBuilder(List.of(Items.SWEET_BERRIES, Items.SWEET_BERRIES, Items.SWEET_BERRIES),
                ModItems.JAR.get(), Items.SUGAR,
                ModItems.SWEET_BERRIES_JAM.get(), 3600)
                .unlockedBy("has_jar", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.JAR.get()).build()))
                .save(pWriter);

        new KegRecipeBuilder(List.of(Items.MELON_SLICE, Items.MELON_SLICE, Items.MELON_SLICE),
                ModItems.JAR.get(), Items.SUGAR,
                ModItems.MELON_JAM.get(), 3600)
                .unlockedBy("has_jar", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.JAR.get()).build()))
                .save(pWriter);
    }
}
