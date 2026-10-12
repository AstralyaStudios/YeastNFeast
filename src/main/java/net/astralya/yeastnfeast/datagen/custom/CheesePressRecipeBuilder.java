package net.astralya.yeastnfeast.datagen.custom;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.recipe.CheesePressRecipe;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.CriterionConditions;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CheesePressRecipeBuilder implements CraftingRecipeJsonBuilder {

    private final List<Ingredient> ingredients = new ArrayList<>();
    private final @Nullable Ingredient flavorSlot;
    private final ItemStackJson result;
    private final int pressTime;
    private final float experience;
    private final Advancement.Builder advancement = Advancement.Builder.create();

    public CheesePressRecipeBuilder(List<Ingredient> ingredients,
                                    @Nullable Ingredient flavorSlot,
                                    ItemConvertible result,
                                    int resultCount,
                                    float experience,
                                    int pressTime) {
        this.ingredients.addAll(ingredients);
        this.flavorSlot = flavorSlot;
        this.result = new ItemStackJson(result.asItem(), resultCount);
        this.experience = experience;
        this.pressTime = pressTime;
    }

    public static Ingredient ing(ItemConvertible item) {
        return Ingredient.ofItems(item);
    }

    public static Ingredient tag(TagKey<Item> tag) {
        return Ingredient.fromTag(tag);
    }

    @Override
    public CraftingRecipeJsonBuilder criterion(String name, CriterionConditions conditions) {
        this.advancement.criterion(name, conditions);
        return this;
    }

    @Override
    public CraftingRecipeJsonBuilder group(@Nullable String group) {
        return this;
    }

    @Override
    public Item getOutputItem() {
        return result.item;
    }

    @Override
    public void offerTo(Consumer<RecipeJsonProvider> exporter, Identifier recipeId) {
        this.advancement.parent(new Identifier("recipes/root"))
                .criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId));

        exporter.accept(new JsonBuilder(
                recipeId,
                this.ingredients,
                this.flavorSlot,
                this.result,
                this.experience,
                this.pressTime,
                this.advancement,
                new Identifier(recipeId.getNamespace(), "recipes/" + recipeId.getPath())
        ));
    }

    private static final class ItemStackJson {
        private final Item item;
        private final int count;

        private ItemStackJson(Item item, int count) {
            this.item = item;
            this.count = count;
        }
    }

    public static class JsonBuilder implements RecipeJsonProvider {

        private final Identifier id;
        private final List<Ingredient> ingredients;
        private final @Nullable Ingredient flavorSlot;
        private final ItemStackJson result;
        private final float experience;
        private final int pressTime;
        private final Advancement.Builder advancement;
        private final Identifier advancementId;

        public JsonBuilder(Identifier id,
                           List<Ingredient> ingredients,
                           @Nullable Ingredient flavorSlot,
                           ItemStackJson result,
                           float experience,
                           int pressTime,
                           Advancement.Builder advancement,
                           Identifier advancementId) {
            this.id = id;
            this.ingredients = ingredients;
            this.flavorSlot = flavorSlot;
            this.result = result;
            this.experience = experience;
            this.pressTime = pressTime;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        @Override
        public void serialize(JsonObject json) {
            json.addProperty("type", "yeastnfeast:cheese_press");

            JsonArray jsonIngredients = new JsonArray();
            for (Ingredient ingredient : ingredients) {
                jsonIngredients.add(ingredient.toJson());
            }
            json.add("ingredients", jsonIngredients);

            if (flavorSlot != null && !flavorSlot.isEmpty()) {
                json.add("flavor_slot", flavorSlot.toJson());
            }

            JsonObject jsonResult = new JsonObject();
            jsonResult.addProperty("item", Registries.ITEM.getId(this.result.item).toString());
            if (this.result.count != 1) {
                jsonResult.addProperty("count", this.result.count);
            }
            json.add("result", jsonResult);

            if (this.experience != 0.0f) {
                json.addProperty("experience", this.experience);
            }

            json.addProperty("pressTime", this.pressTime);
        }

        @Override
        public Identifier getRecipeId() {
            return new Identifier(
                    YeastNFeastMod.MODID,
                    Registries.ITEM.getId(this.result.item).getPath() + "_from_cheese_press"
            );
        }

        @Override
        public RecipeSerializer<?> getSerializer() {
            return CheesePressRecipe.Serializer.INSTANCE;
        }

        @Nullable
        @Override
        public JsonObject toAdvancementJson() {
            return this.advancement.toJson();
        }

        @Nullable
        @Override
        public Identifier getAdvancementId() {
            return this.advancementId;
        }
    }
}