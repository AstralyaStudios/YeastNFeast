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
import net.astralya.yeastnfeast.recipe.custom.CheesePressRecipe;
import net.astralya.yeastnfeast.recipe.custom.KegRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

@JeiPlugin
@SuppressWarnings("unused")
public class JEIPlugin implements IModPlugin {

    private static final ResourceLocation ID = new ResourceLocation(YeastNFeastMod.MODID, "jei_plugin");

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
        if (Minecraft.getInstance().level == null) return;

        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<KegRecipe> kegRecipes = recipeManager.getAllRecipesFor(KegRecipe.Type.INSTANCE);
        registration.addRecipes(KegRecipeCategory.KEG_RECIPE_TYPE, kegRecipes);

        List<CheesePressRecipe> cheesePressRecipes = recipeManager.getAllRecipesFor(CheesePressRecipe.Type.INSTANCE);
        registration.addRecipes(CheesePressRecipeCategory.CHEESE_PRESS_RECIPE_TYPE, cheesePressRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
    }
}