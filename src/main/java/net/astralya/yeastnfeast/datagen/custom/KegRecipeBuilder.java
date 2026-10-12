package net.astralya.yeastnfeast.datagen.custom;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.recipe.KegRecipe;
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
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class KegRecipeBuilder implements CraftingRecipeJsonBuilder {

    private final List<Ingredient> ingredients = new ArrayList<>();
    private final Item output;
    private final Item yeastItem;
    private final Item tankardItem;
    private final int brewTime;
    private final Advancement.Builder advancement = Advancement.Builder.create();

    public KegRecipeBuilder(List<ItemConvertible> ingredients, ItemConvertible yeastItem, ItemConvertible tankardItem, ItemConvertible output, int brewTime) {
        for (ItemConvertible ingredient : ingredients) {
            this.ingredients.add(Ingredient.ofItems(ingredient));
        }
        this.yeastItem = yeastItem.asItem();
        this.tankardItem = tankardItem.asItem();
        this.output = output.asItem();
        this.brewTime = brewTime;
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
        return output;
    }

    @Override
    public void offerTo(Consumer<RecipeJsonProvider> exporter, Identifier recipeId) {
        this.advancement.parent(new Identifier("recipes/root"))
                .criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId));

        exporter.accept(new JsonBuilder(recipeId, this.output, this.ingredients, this.yeastItem, this.tankardItem,
                this.brewTime, this.advancement, new Identifier(recipeId.getNamespace(), "recipes/" + recipeId.getPath())));
    }

    public static class JsonBuilder implements RecipeJsonProvider {
        private final Identifier id;
        private final Item output;
        private final List<Ingredient> ingredients;
        private final Item yeastSlotItem;
        private final Item tankardSlotItem;
        private final int brewTime;
        private final Advancement.Builder advancement;
        private final Identifier advancementId;

        public JsonBuilder(Identifier id, Item output, List<Ingredient> ingredients, Item yeastSlotItem,
                           Item tankardSlotItem, int brewTime, Advancement.Builder advancement, Identifier advancementId) {
            this.id = id;
            this.output = output;
            this.ingredients = ingredients;
            this.yeastSlotItem = yeastSlotItem;
            this.tankardSlotItem = tankardSlotItem;
            this.brewTime = brewTime;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        @Override
        public void serialize(JsonObject json) {
            json.addProperty("type", "yeastnfeast:keg");

            JsonArray jsonIngredients = new JsonArray();
            for (Ingredient ingredient : ingredients) {
                jsonIngredients.add(ingredient.toJson());
            }
            json.add("ingredients", jsonIngredients);

            JsonObject jsonYeastSlot = new JsonObject();
            jsonYeastSlot.addProperty("item", Registries.ITEM.getId(this.yeastSlotItem).toString());
            json.add("yeast_slot", jsonYeastSlot);

            JsonObject jsonTankardSlot = new JsonObject();
            jsonTankardSlot.addProperty("item", Registries.ITEM.getId(this.tankardSlotItem).toString());
            json.add("tankard_slot", jsonTankardSlot);

            JsonObject jsonOutput = new JsonObject();
            jsonOutput.addProperty("item", Registries.ITEM.getId(this.output).toString());
            json.add("output", jsonOutput);

            json.addProperty("brew_time", this.brewTime);
        }

        @Override
        public Identifier getRecipeId() {
            return new Identifier(YeastNFeastMod.MODID,
                    Registries.ITEM.getId(this.output).getPath() + "_from_keg");
        }

        @Override
        public RecipeSerializer<?> getSerializer() {
            return KegRecipe.Serializer.INSTANCE;
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