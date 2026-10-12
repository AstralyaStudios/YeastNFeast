package net.astralya.yeastnfeast.recipe.custom;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class KegRecipe implements Recipe<SimpleContainer> {

    private final NonNullList<Ingredient> recipeItems;
    private final Ingredient tankardSlot;
    private final Ingredient yeastSlot;
    private final ItemStack output;
    private final ResourceLocation id;
    private final int brewTime;

    public KegRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems, Ingredient yeastSlot, Ingredient tankardSlot, int brewTime) {
        this.recipeItems = recipeItems;
        this.output = output;
        this.tankardSlot = tankardSlot;
        this.yeastSlot = yeastSlot;
        this.id = id;
        this.brewTime = brewTime;
    }

    public int getBrewTime() {
        return brewTime;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if (pLevel.isClientSide()) {
            return false;
        }

        if (recipeItems.size() > 3) {
            return false;
        }

        boolean[] inputSlotsUsed = new boolean[3];

        for (int ingredientIndex = 0; ingredientIndex < recipeItems.size(); ingredientIndex++) {
            Ingredient ingredient = recipeItems.get(ingredientIndex);
            boolean foundMatch = false;

            for (int i = 0; i < 3; i++) {
                ItemStack slotStack = pContainer.getItem(i);

                if (!inputSlotsUsed[i] && ingredient.test(slotStack)) {
                    inputSlotsUsed[i] = true;
                    foundMatch = true;
                    break;
                }
            }

            if (!foundMatch) {
                return false;
            }
        }

        if (!yeastSlot.test(pContainer.getItem(4))) {
            return false;
        }

        if (!tankardSlot.test(pContainer.getItem(5))) {
            return false;
        }

        return true;
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.recipeItems;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    public Ingredient getYeastSlot() {
        return yeastSlot;
    }

    public Ingredient getTankardSlot() {
        return tankardSlot;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<KegRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "keg";
    }

    public static class Serializer implements RecipeSerializer<KegRecipe> {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public KegRecipe fromJson(ResourceLocation id, JsonObject json) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));

            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.create();

            for (int i = 0; i < ingredients.size(); i++) {
                Ingredient ingredient = Ingredient.fromJson(ingredients.get(i));
                inputs.add(ingredient);
            }

            Ingredient yeastSlot = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "yeast_slot"));
            Ingredient tankardSlot = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "tankard_slot"));
            int brewTime = GsonHelper.getAsInt(json, "brew_time", 7200);

            return new KegRecipe(id, output, inputs, yeastSlot, tankardSlot, brewTime);
        }

        @Override
        public KegRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }

            ItemStack output = buf.readItem();
            Ingredient tankardSlot = Ingredient.fromNetwork(buf);
            Ingredient yeastSlot = Ingredient.fromNetwork(buf);
            int brewTime = buf.readVarInt();

            return new KegRecipe(id, output, inputs, tankardSlot, yeastSlot, brewTime);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, KegRecipe recipe) {
            buf.writeInt(recipe.recipeItems.size());
            for (Ingredient ingredient : recipe.recipeItems) {
                ingredient.toNetwork(buf);
            }
            buf.writeItem(recipe.getResultItem(null));
            recipe.tankardSlot.toNetwork(buf);
            recipe.yeastSlot.toNetwork(buf);
            buf.writeVarInt(recipe.getBrewTime());
        }
    }
}