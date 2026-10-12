package net.astralya.yeastnfeast.datagen.custom;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.recipe.custom.CheesePressRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public final class CheesePressRecipeBuilder implements RecipeBuilder {

    private final List<Ingredient> ingredients;
    private final Ingredient flavorSlot;
    private final Item resultItem;
    private final int resultCount;
    private final float experience;
    private final int pressTime;
    private final Advancement.Builder advancement;

    private CheesePressRecipeBuilder(List<Ingredient> ingredients,
                                     @Nullable Ingredient flavorSlot,
                                     ItemLike result,
                                     int resultCount,
                                     float experience,
                                     int pressTime) {
        this.ingredients = new ArrayList<>(ingredients);
        this.flavorSlot = flavorSlot == null ? Ingredient.EMPTY : flavorSlot;
        this.resultItem = result.asItem();
        this.resultCount = Math.max(1, resultCount);
        this.experience = experience;
        this.pressTime = pressTime;
        this.advancement = Advancement.Builder.advancement();
    }

    public static CheesePressRecipeBuilder ofIngredients(List<Ingredient> ingredients,
                                                         @Nullable Ingredient flavorSlot,
                                                         ItemLike result,
                                                         int resultCount,
                                                         float experience,
                                                         int pressTime) {
        return new CheesePressRecipeBuilder(ingredients, flavorSlot, result, resultCount, experience, pressTime);
    }

    public static CheesePressRecipeBuilder ofItems(List<ItemLike> ingredients,
                                                   @Nullable ItemLike flavorSlot,
                                                   ItemLike result,
                                                   int resultCount,
                                                   float experience,
                                                   int pressTime) {
        return new CheesePressRecipeBuilder(toIngredientList(ingredients), flavorSlot == null ? null : Ingredient.of(flavorSlot), result, resultCount, experience, pressTime);
    }

    public static Ingredient tag(TagKey<Item> tag) {
        return Ingredient.of(tag);
    }

    public static Ingredient item(ItemLike item) {
        return Ingredient.of(item);
    }

    private static List<Ingredient> toIngredientList(List<ItemLike> items) {
        List<Ingredient> list = new ArrayList<>(items.size());
        for (ItemLike item : items) {
            list.add(Ingredient.of(item));
        }
        return list;
    }

    @Override
    public RecipeBuilder unlockedBy(String criterionName, CriterionTriggerInstance criterionTrigger) {
        this.advancement.addCriterion(criterionName, criterionTrigger);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String groupName) {
        return this;
    }

    @Override
    public Item getResult() {
        return resultItem;
    }

    @Override
    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation recipeId) {
        this.advancement.parent(new ResourceLocation("recipes/root"))
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .requirements(RequirementsStrategy.OR);

        ResourceLocation advancementId = new ResourceLocation(recipeId.getNamespace(), "recipes/" + recipeId.getPath());

        consumer.accept(new Result(
                recipeId,
                resultItem,
                resultCount,
                ingredients,
                flavorSlot,
                experience,
                pressTime,
                advancement,
                advancementId
        ));
    }

    private static final class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final Item resultItem;
        private final int resultCount;
        private final List<Ingredient> ingredients;
        private final Ingredient flavorSlot;
        private final float experience;
        private final int pressTime;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        private Result(ResourceLocation id,
                       Item resultItem,
                       int resultCount,
                       List<Ingredient> ingredients,
                       Ingredient flavorSlot,
                       float experience,
                       int pressTime,
                       Advancement.Builder advancement,
                       ResourceLocation advancementId) {
            this.id = id;
            this.resultItem = resultItem;
            this.resultCount = resultCount;
            this.ingredients = ingredients;
            this.flavorSlot = flavorSlot;
            this.experience = experience;
            this.pressTime = pressTime;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        @Override
        public void serializeRecipeData(JsonObject json) {
            json.addProperty("type", YeastNFeastMod.MODID + ":" + CheesePressRecipe.Type.ID);

            JsonArray jsonIngredients = new JsonArray();
            for (Ingredient ingredient : ingredients) {
                jsonIngredients.add(ingredient.toJson());
            }
            json.add("ingredients", jsonIngredients);

            if (!flavorSlot.isEmpty()) {
                json.add("flavor_slot", flavorSlot.toJson());
            }

            JsonObject jsonResult = new JsonObject();
            jsonResult.addProperty("item", ForgeRegistries.ITEMS.getKey(resultItem).toString());
            if (resultCount != 1) {
                jsonResult.addProperty("count", resultCount);
            }
            json.add("result", jsonResult);

            if (experience != 0.0F) {
                json.addProperty("experience", experience);
            }

            json.addProperty("pressTime", pressTime);
        }

        @Override
        public ResourceLocation getId() {
            return new ResourceLocation(
                    YeastNFeastMod.MODID,
                    ForgeRegistries.ITEMS.getKey(this.resultItem).getPath() + "_from_cheese_press"
            );
        }

        @Override
        public net.minecraft.world.item.crafting.RecipeSerializer<?> getType() {
            return CheesePressRecipe.Serializer.INSTANCE;
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