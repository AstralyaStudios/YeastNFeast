package net.astralya.yeastnfeast.recipe.custom;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;

public class CheesePressRecipe implements Recipe<SimpleContainer> {

    public static final int INPUT_SLOTS = 3;

    private final ResourceLocation id;
    private final NonNullList<Ingredient> ingredients;
    private final Ingredient flavorSlot;
    private final ItemStack output;
    private final float experience;
    private final int pressTime;

    public CheesePressRecipe(ResourceLocation id, NonNullList<Ingredient> ingredients, Ingredient flavorSlot, ItemStack output, float experience, int pressTime) {
        this.id = id;
        this.ingredients = ingredients;
        this.flavorSlot = flavorSlot;
        this.output = output;
        this.experience = experience;
        this.pressTime = pressTime;
    }

    @Override
    public boolean matches(SimpleContainer container, Level level) {
        if (level.isClientSide()) return false;

        int filled = 0;
        for (int i = 0; i < INPUT_SLOTS; i++) {
            if (!container.getItem(i).isEmpty()) filled++;
        }

        int required = ingredients.size() + (flavorSlot.isEmpty() ? 0 : 1);
        if (filled != required) return false;

        boolean[] used = new boolean[INPUT_SLOTS];

        for (Ingredient ing : ingredients) {
            boolean found = false;
            for (int i = 0; i < INPUT_SLOTS; i++) {
                if (!used[i] && ing.test(container.getItem(i))) {
                    used[i] = true;
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }

        if (!flavorSlot.isEmpty()) {
            boolean foundFlavor = false;
            for (int i = 0; i < INPUT_SLOTS; i++) {
                if (!used[i] && flavorSlot.test(container.getItem(i))) {
                    used[i] = true;
                    foundFlavor = true;
                    break;
                }
            }
            if (!foundFlavor) return false;
        }

        return true;
    }

    @Override
    public ItemStack assemble(SimpleContainer container, net.minecraft.core.RegistryAccess registryAccess) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(net.minecraft.core.RegistryAccess registryAccess) {
        return output.copy();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return ingredients;
    }

    public Ingredient getFlavorSlot() {
        return flavorSlot;
    }

    public float getExperience() {
        return experience;
    }

    public int getPressTime() {
        return pressTime;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<CheesePressRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "cheese_press";
    }

    public static class Serializer implements RecipeSerializer<CheesePressRecipe> {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public CheesePressRecipe fromJson(ResourceLocation id, JsonObject json) {
            JsonArray ingredientsJson = GsonHelper.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.create();
            for (int i = 0; i < ingredientsJson.size(); i++) {
                inputs.add(Ingredient.fromJson(ingredientsJson.get(i)));
            }

            Ingredient flavor = Ingredient.EMPTY;
            if (json.has("flavor_slot")) {
                flavor = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "flavor_slot"));
            }

            ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
            float xp = GsonHelper.getAsFloat(json, "experience", 0.0F);
            int time = GsonHelper.getAsInt(json, "pressTime", 1200);

            return new CheesePressRecipe(id, inputs, flavor, result, xp, time);
        }

        @Override
        public CheesePressRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            int size = buf.readVarInt();
            NonNullList<Ingredient> inputs = NonNullList.withSize(size, Ingredient.EMPTY);
            for (int i = 0; i < size; i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }

            Ingredient flavor = Ingredient.fromNetwork(buf);
            ItemStack result = buf.readItem();
            float xp = buf.readFloat();
            int time = buf.readVarInt();

            return new CheesePressRecipe(id, inputs, flavor, result, xp, time);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, CheesePressRecipe recipe) {
            buf.writeVarInt(recipe.ingredients.size());
            for (Ingredient ing : recipe.ingredients) {
                ing.toNetwork(buf);
            }
            recipe.flavorSlot.toNetwork(buf);
            buf.writeItem(recipe.output);
            buf.writeFloat(recipe.experience);
            buf.writeVarInt(recipe.pressTime);
        }
    }
}