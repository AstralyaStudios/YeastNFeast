package net.astralya.yeastnfeast.compat.patchouli;

import net.astralya.yeastnfeast.recipe.KegRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariable;
import vazkii.patchouli.api.IVariableProvider;

public class KegProcessor implements IComponentProcessor {
    protected KegRecipe recipe;

    @Override
    public void setup(World level, IVariableProvider variables) {
        Identifier recipeId = new Identifier(variables.get("recipe").asString());
        recipe = (KegRecipe) level.getRecipeManager().get(recipeId)
                .filter(recipe -> recipe.getType().equals(KegRecipe.Type.INSTANCE))
                .orElseThrow(() -> new IllegalArgumentException("Recipe not found: " + recipeId));
    }

    @Override
    public IVariable process(World level, String key) {
        if (key.equals("output")) {
            return IVariable.from(recipe.getOutput(level.getRegistryManager()));
        } else if (key.equals("header")) {
            return IVariable.from(recipe.getOutput(level.getRegistryManager()).getName());
        } else if (key.equals("brew_time")) {
            return IVariable.wrap(recipe.getBrewTime());
        } else if (key.equals("yeast_slot")) {
            ItemStack[] yeastStacks = recipe.getYeastSlot().getMatchingStacks();
            return yeastStacks.length > 0 ? IVariable.from(yeastStacks[0]) : null;
        } else if (key.equals("tankard_slot")) {
            ItemStack[] tankardStacks = recipe.getTankardSlot().getMatchingStacks();
            return tankardStacks.length > 0 ? IVariable.from(tankardStacks[0]) : null;
        }

        // Handle ingredients
        for (int i = 0; i < recipe.getIngredients().size(); i++) {
            if (key.equals("ingredient" + i)) {
                ItemStack[] stack = recipe.getIngredients().get(i).getMatchingStacks();
                return stack.length > 0 ? IVariable.from(stack[0]) : null;
            }
        }

        return null;
    }
}