package net.astralya.yeastnfeast.recipe;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import dev.architectury.registry.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModRecipes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(YeastNFeastMod.MODID, Registries.RECIPE_TYPE);

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(YeastNFeastMod.MODID, Registries.RECIPE_SERIALIZER);

    // Keg Recipe
    public static final Supplier<RecipeType<KegRecipe>> KEG_TYPE =
            RECIPE_TYPES.register("keg", () -> new RecipeType<KegRecipe>() {
                @Override
                public String toString() {
                    return "keg";
                }
            });

    public static final Supplier<RecipeSerializer<KegRecipe>> KEG_SERIALIZER =
            RECIPE_SERIALIZERS.register("keg", KegRecipe.Serializer::new);

    // Cheese Press Recipe
    public static final Supplier<RecipeType<CheesePressRecipe>> CHEESE_PRESS_TYPE =
            RECIPE_TYPES.register("cheese_press", () -> new RecipeType<CheesePressRecipe>() {
                @Override
                public String toString() {
                    return "cheese_press";
                }
            });

    public static final Supplier<RecipeSerializer<CheesePressRecipe>> CHEESE_PRESS_SERIALIZER =
            RECIPE_SERIALIZERS.register("cheese_press", CheesePressRecipe.Serializer::new);

    public static void init() {
        RECIPE_TYPES.register();
        RECIPE_SERIALIZERS.register();
    }
}


