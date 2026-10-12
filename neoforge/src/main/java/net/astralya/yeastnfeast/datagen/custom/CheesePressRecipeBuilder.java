package net.astralya.yeastnfeast.datagen.custom;

import net.astralya.yeastnfeast.recipe.CheesePressRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;

public class CheesePressRecipeBuilder implements RecipeBuilder {

    private final NonNullList<Ingredient> ingredients = NonNullList.create();
    private Ingredient flavorSlot = Ingredient.EMPTY;
    private final ItemStack output;
    private final Item result;
    private float experience = 0f;
    private int pressTime = 1200;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

    private CheesePressRecipeBuilder(ItemStack output) {
        this.output = output;
        this.result = output.getItem();
    }

    public static CheesePressRecipeBuilder cheesePress(ItemStack output) {
        return new CheesePressRecipeBuilder(output);
    }

    public CheesePressRecipeBuilder addIngredient(Ingredient ing) { ingredients.add(ing); return this; }
    public CheesePressRecipeBuilder addIngredient(Item item) { return addIngredient(Ingredient.of(item)); }
    public CheesePressRecipeBuilder addIngredient(ItemStack stack) { return addIngredient(Ingredient.of(stack)); }
    public CheesePressRecipeBuilder addIngredient(TagKey<Item> tag) { return addIngredient(Ingredient.of(tag)); }

    public CheesePressRecipeBuilder flavor(Ingredient ing) { this.flavorSlot = ing; return this; }
    public CheesePressRecipeBuilder flavor(Item item) { return flavor(Ingredient.of(item)); }
    public CheesePressRecipeBuilder flavor(ItemStack stack) { return flavor(Ingredient.of(stack)); }
    public CheesePressRecipeBuilder flavor(TagKey<Item> tag) { return flavor(Ingredient.of(tag)); }

    public CheesePressRecipeBuilder experience(float xp) { this.experience = xp; return this; }
    public CheesePressRecipeBuilder pressTime(int ticks) { this.pressTime = ticks; return this; }

    @Override
    public CheesePressRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        criteria.put(name, criterion);
        return this;
    }

    public CheesePressRecipeBuilder unlockedByItems(String name, Item... items) {
        return unlockedBy(name, InventoryChangeTrigger.TriggerInstance.hasItems(items));
    }

    public CheesePressRecipeBuilder unlockedByTag(String name, TagKey<Item> tag) {
        ItemPredicate predicate = ItemPredicate.Builder.item().of(tag).build();
        return unlockedBy(name, InventoryChangeTrigger.TriggerInstance.hasItems(predicate));
    }

    @Override public RecipeBuilder group(@Nullable String s) { return this; }
    @Override public Item getResult() { return result; }

    @Override
    public void save(RecipeOutput out, ResourceLocation id) {
        if (ingredients.isEmpty())
            throw new IllegalStateException("Cheese Press recipe with no ingredients: " + id);
        if (ingredients.size() > CheesePressRecipe.INPUT_SLOTS)
            throw new IllegalStateException("Too many ingredients for Cheese Press recipe (max " + CheesePressRecipe.INPUT_SLOTS + "): " + id);

        Advancement.Builder adv = out.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
                .rewards(AdvancementRewards.Builder.recipe(id))
                .requirements(AdvancementRequirements.Strategy.OR);
        criteria.forEach(adv::addCriterion);

        CheesePressRecipe recipe = new CheesePressRecipe(ingredients, flavorSlot, output, experience, pressTime);
        out.accept(id, recipe, adv.build(id.withPrefix("recipes/cheese_press/")));
    }
}
