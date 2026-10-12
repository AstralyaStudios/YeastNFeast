package net.astralya.yeastnfeast.compat.rei.cheese_press;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.astralya.yeastnfeast.recipe.CheesePressRecipe;
import net.minecraft.recipe.Ingredient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheesePressDisplay extends BasicDisplay {

    private final EntryIngredient flavorSlot;

    public CheesePressDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, EntryIngredient flavorSlot) {
        super(inputs, outputs);
        this.flavorSlot = flavorSlot;
    }

    public CheesePressDisplay(CheesePressRecipe recipe) {
        super(getInputList(recipe), List.of(EntryIngredient.of(EntryStacks.of(recipe.getOutput(null)))));
        Ingredient flavor = recipe.getFlavorSlot();
        this.flavorSlot = (flavor == null || flavor.isEmpty()) ? EntryIngredient.empty() : EntryIngredients.ofIngredient(flavor);
    }

    private static List<EntryIngredient> getInputList(CheesePressRecipe recipe) {
        if (recipe == null) {
            return Collections.emptyList();
        }

        List<EntryIngredient> list = new ArrayList<>();
        int added = 0;

        for (Ingredient ingredient : recipe.getIngredients()) {
            if (ingredient == null || ingredient.isEmpty()) {
                continue;
            }
            list.add(EntryIngredients.ofIngredient(ingredient));
            added++;
            if (added >= 2) {
                break;
            }
        }

        return list;
    }

    public EntryIngredient getFlavorSlot() {
        return flavorSlot;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return CheesePressCategory.CHEESE_PRESS;
    }
}