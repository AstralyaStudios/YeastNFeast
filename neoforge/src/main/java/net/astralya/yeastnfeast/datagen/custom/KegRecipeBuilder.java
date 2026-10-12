package net.astralya.yeastnfeast.datagen.custom;

import net.astralya.yeastnfeast.recipe.KegRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;

public class KegRecipeBuilder implements RecipeBuilder {

    private final NonNullList<Ingredient> ingredients = NonNullList.create();
    private Ingredient yeastSlot = Ingredient.EMPTY;
    private Ingredient extractionContainer = Ingredient.EMPTY;
    private final ItemStack output;
    private final Item result;
    private float experience = 0f;
    private int brewTime = 200;
    private int liquidColor = 0xA45C72;
    private int servings = 1;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

    private KegRecipeBuilder(ItemStack output) {
        this.output = output;
        this.result = output.getItem();
    }

    public static KegRecipeBuilder kegRecipe(ItemStack output) {
        return new KegRecipeBuilder(output);
    }

    public KegRecipeBuilder addIngredient(Ingredient ing)   { ingredients.add(ing); return this; }
    public KegRecipeBuilder addIngredient(Item i)           { return addIngredient(Ingredient.of(i)); }
    public KegRecipeBuilder addIngredient(ItemStack s)      { return addIngredient(Ingredient.of(s)); }

    public KegRecipeBuilder yeast(Ingredient ing)           { this.yeastSlot = ing; return this; }
    public KegRecipeBuilder yeast(Item item)                { return yeast(Ingredient.of(item)); }

    public KegRecipeBuilder extractionContainer(Ingredient ing) { this.extractionContainer = ing; return this; }
    public KegRecipeBuilder extractionContainer(Item item) { return extractionContainer(Ingredient.of(item)); }

    public KegRecipeBuilder experience(float xp)            { this.experience = xp; return this; }
    public KegRecipeBuilder brewTime(int ticks)             { this.brewTime = ticks; return this; }
    public KegRecipeBuilder liquidColor(int rgb)            { this.liquidColor = rgb; return this; }
    public KegRecipeBuilder servings(int servings)          { this.servings = servings; return this; }

    @Override public KegRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        criteria.put(name, criterion); return this;
    }

    public KegRecipeBuilder unlockedByItems(String name, Item... items) {
        return unlockedBy(name, InventoryChangeTrigger.TriggerInstance.hasItems(items));
    }

    @Override public RecipeBuilder group(@Nullable String s) { return this; }
    @Override public Item getResult() { return result; }

    @Override
    public void save(RecipeOutput out, ResourceLocation id) {
        if (ingredients.size() != KegRecipe.INPUT_SLOTS)
            throw new IllegalStateException("Keg recipe requires exactly "
                    + KegRecipe.INPUT_SLOTS + " ingredients: " + id);
        if (yeastSlot.isEmpty())
            throw new IllegalStateException("Keg recipe requires yeast/sugar: " + id);

        if (extractionContainer.isEmpty())
            throw new IllegalStateException("Keg recipe requires an extraction container: " + id);
        if (servings < 1)
            throw new IllegalStateException("Keg recipe servings must be positive: " + id);

        Advancement.Builder adv = out.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
                .rewards(AdvancementRewards.Builder.recipe(id))
                .requirements(AdvancementRequirements.Strategy.OR);
        criteria.forEach(adv::addCriterion);

        KegRecipe recipe = new KegRecipe(
                ingredients, yeastSlot,
                output, experience, brewTime, liquidColor, extractionContainer, servings);

        out.accept(id, recipe, adv.build(id.withPrefix("recipes/keg/")));
    }
}
