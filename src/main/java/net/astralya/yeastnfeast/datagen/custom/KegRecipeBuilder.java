package net.astralya.yeastnfeast.datagen.custom;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.recipe.custom.KegRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class KegRecipeBuilder implements RecipeBuilder {

    private final List<Ingredient> ingredients = new ArrayList<>();
    private final Item output;
    private final Item tankardItem;
    private final Item yeastItem;
    private final int brewTime;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();

    public KegRecipeBuilder(List<ItemLike> ingredients, ItemLike tankardItem, ItemLike yeastItem, ItemLike output, int brewTime) {
        for (ItemLike ingredient : ingredients) {
            this.ingredients.add(Ingredient.of(ingredient));
        }
        this.tankardItem = tankardItem.asItem();
        this.yeastItem = yeastItem.asItem();
        this.output = output.asItem();
        this.brewTime = brewTime;
    }

    @Override
    public RecipeBuilder unlockedBy(String pCriterionName, CriterionTriggerInstance pCriterionTrigger) {
        this.advancement.addCriterion(pCriterionName, pCriterionTrigger);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String pGroupName) {
        return this;
    }

    @Override
    public Item getResult() {
        return output;
    }

    @Override
    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
        this.advancement.parent(new ResourceLocation("recipes/root"))
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(pRecipeId))
                .rewards(AdvancementRewards.Builder.recipe(pRecipeId)).requirements(RequirementsStrategy.OR);

        pFinishedRecipeConsumer.accept(new Result(pRecipeId, this.output, this.ingredients, this.tankardItem, this.yeastItem,
                this.brewTime, this.advancement, new ResourceLocation(pRecipeId.getNamespace(), "recipes/"
                + pRecipeId.getPath())));
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final Item output;
        private final List<Ingredient> ingredients;
        private final Item tankardSlotItem;
        private final Item yeastSlotItem;
        private final int brewTime;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation id, Item output, List<Ingredient> ingredients, Item tankardSlotItem,
                      Item yeastSlotItem, int brewTime, Advancement.Builder advancement, ResourceLocation advancementId) {
            this.id = id;
            this.output = output;
            this.ingredients = ingredients;
            this.tankardSlotItem = tankardSlotItem;
            this.yeastSlotItem = yeastSlotItem;
            this.brewTime = brewTime; // Initialize brewTime
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {
            pJson.addProperty("type", "yeastnfeast:keg");

            JsonArray jsonIngredients = new JsonArray();
            for (Ingredient ingredient : ingredients) {
                jsonIngredients.add(ingredient.toJson());
            }
            pJson.add("ingredients", jsonIngredients);

            JsonObject jsonTankardSlot = new JsonObject();
            jsonTankardSlot.addProperty("item", ForgeRegistries.ITEMS.getKey(this.tankardSlotItem).toString());
            pJson.add("tankard_slot", jsonTankardSlot);

            JsonObject jsonYeastSlot = new JsonObject();
            jsonYeastSlot.addProperty("item", ForgeRegistries.ITEMS.getKey(this.yeastSlotItem).toString());
            pJson.add("yeast_slot", jsonYeastSlot);

            JsonObject jsonOutput = new JsonObject();
            jsonOutput.addProperty("item", ForgeRegistries.ITEMS.getKey(this.output).toString());
            pJson.add("output", jsonOutput);

            pJson.addProperty("brew_time", this.brewTime); // Add brewTime to the JSON
        }

        @Override
        public ResourceLocation getId() {
            return new ResourceLocation(YeastNFeastMod.MODID,
                    ForgeRegistries.ITEMS.getKey(this.output).getPath() + "_from_keg");
        }

        @Override
        public RecipeSerializer<?> getType() {
            return KegRecipe.Serializer.INSTANCE;
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return this.advancement.serializeToJson();
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}