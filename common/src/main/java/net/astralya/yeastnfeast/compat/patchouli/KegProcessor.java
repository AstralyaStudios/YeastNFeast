package net.astralya.yeastnfeast.compat.patchouli;

import net.astralya.yeastnfeast.recipe.KegRecipe;
import net.astralya.yeastnfeast.recipe.ModRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariable;
import vazkii.patchouli.api.IVariableProvider;

import java.util.List;

public class KegProcessor implements IComponentProcessor {

    private KegRecipe recipe;

    @Override
    public void setup(Level level, IVariableProvider variables) {
        String recipeIdStr = variables.get("recipe", level != null ? level.registryAccess() : Minecraft.getInstance().level.registryAccess()).asString();
        ResourceLocation recipeId = ResourceLocation.parse(recipeIdStr);

        List<RecipeHolder<KegRecipe>> allRecipes =
                Minecraft.getInstance().level.getRecipeManager().getAllRecipesFor(ModRecipes.KEG_TYPE.get());

        this.recipe = allRecipes.stream()
                .filter(holder -> holder.id().equals(recipeId))
                .findFirst()
                .map(RecipeHolder::value)
                .orElseThrow(() -> new IllegalArgumentException("Keg recipe not found: " + recipeId));
    }

    @Override
    public IVariable process(Level level, String key) {
        if (recipe == null) return null;

        switch (key) {
            case "output":
                return IVariable.from(recipe.getResultItem(reg(level)), reg(level));
            case "header":
                return IVariable.from(recipe.getResultItem(reg(level)).getHoverName(), reg(level));

            case "yeast_slot":
                return fromIngredient(recipe.getYeastSlot(), reg(level));
        }

        for (int i = 0; i < recipe.getIngredients().size(); i++) {
            if (key.equals("ingredients" + i)) {
                return fromIngredient(recipe.getIngredients().get(i), reg(level));
            }
        }
        return null;
    }

    // Helpers
    private static IVariable fromIngredient(net.minecraft.world.item.crafting.Ingredient ing, net.minecraft.core.RegistryAccess reg) {
        if (ing == null) return null;
        ItemStack[] stacks = ing.getItems();
        return stacks.length > 0 ? IVariable.from(stacks[0], reg) : null;
    }

    private static net.minecraft.core.RegistryAccess reg(Level level) {
        return (level != null ? level.registryAccess() : Minecraft.getInstance().level.registryAccess());
    }
}
