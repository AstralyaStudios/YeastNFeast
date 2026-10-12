package net.astralya.yeastnfeast.recipe;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.recipe.custom.CheesePressRecipe;
import net.astralya.yeastnfeast.recipe.custom.KegRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, YeastNFeastMod.MODID);

    public static final RegistryObject<RecipeSerializer<KegRecipe>> KEG_RECIPE_SERIALIZER =
            SERIALIZERS.register("keg", () -> KegRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<CheesePressRecipe>> CHEESE_PRESS_RECIPE_SERIALIZER =
            SERIALIZERS.register("cheese_press", () -> CheesePressRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
