package net.astralya.yeastnfeast.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.compat.jei.category.CheesePressRecipeCategory;
import net.astralya.yeastnfeast.compat.jei.category.KegRecipeCategory;
import net.astralya.yeastnfeast.recipe.CheesePressRecipe;
import net.astralya.yeastnfeast.recipe.KegRecipe;
import net.astralya.yeastnfeast.recipe.ModRecipes;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@JeiPlugin
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@SuppressWarnings("unused")
public class JEIPlugin implements IModPlugin {


    private static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "jei_plugin");

    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new KegRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new CheesePressRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.KEG.get()), KegRecipeCategory.KEG_RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.CHEESE_PRESS.get()), CheesePressRecipeCategory.CHEESE_PRESS_RECIPE_TYPE);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<KegRecipe> kegRecipes = recipeManager
                .getAllRecipesFor(ModRecipes.KEG_TYPE.get()).stream().map(RecipeHolder::value).toList();
        registration.addRecipes(KegRecipeCategory.KEG_RECIPE_TYPE, kegRecipes);

        List<CheesePressRecipe> cheeseRecipes = recipeManager
                .getAllRecipesFor(ModRecipes.CHEESE_PRESS_TYPE.get()).stream().map(RecipeHolder::value).toList();
        registration.addRecipes(CheesePressRecipeCategory.CHEESE_PRESS_RECIPE_TYPE, cheeseRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        IModPlugin.super.registerGuiHandlers(registration);
    }
}
