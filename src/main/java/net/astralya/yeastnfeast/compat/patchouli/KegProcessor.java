package net.astralya.yeastnfeast.compat.patchouli;

import net.astralya.yeastnfeast.recipe.custom.KegRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariable;
import vazkii.patchouli.api.IVariableProvider;

public class KegProcessor implements IComponentProcessor {
    protected KegRecipe recipe;

    @Override
    public void setup(Level level, IVariableProvider variables) {
        ResourceLocation recipeId = new ResourceLocation(variables.get("recipe").asString());
        recipe = (KegRecipe) level.getRecipeManager().byKey(recipeId)
                .filter(recipe -> recipe.getType().equals(KegRecipe.Type.INSTANCE))
                .orElseThrow(() -> new IllegalArgumentException("Recipe not found: " + recipeId));
    }

    @Override
    public IVariable process(Level level, String key) {
        if (key.equals("output")) {
            return IVariable.from(recipe.getResultItem(level.registryAccess()));
        } else if (key.equals("header")) {
            return IVariable.from(recipe.getResultItem(level.registryAccess()).getHoverName());
        } else if (key.equals("brew_time")) {
            return IVariable.wrap(recipe.getBrewTime());
        } else if (key.equals("yeast_slot")) {
            ItemStack[] yeastStacks = recipe.getYeastSlot().getItems();
            return yeastStacks.length > 0 ? IVariable.from(yeastStacks[0]) : null;
        } else if (key.equals("tankard_slot")) {
            ItemStack[] tankardStacks = recipe.getTankardSlot().getItems();
            return tankardStacks.length > 0 ? IVariable.from(tankardStacks[0]) : null;
        }

        for (int i = 0; i < recipe.getIngredients().size(); i++) {
            if (key.equals("ingredient" + i)) {
                ItemStack[] stack = recipe.getIngredients().get(i).getItems();
                return stack.length > 0 ? IVariable.from(stack[0]) : null;
            }
        }

        return null;
    }
}