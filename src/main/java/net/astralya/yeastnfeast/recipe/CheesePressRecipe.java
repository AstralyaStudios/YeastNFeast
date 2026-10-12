package net.astralya.yeastnfeast.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.astralya.yeastnfeast.item.ModItems;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.Objects;

public class CheesePressRecipe implements Recipe<SimpleInventory> {

    public static final int INPUT_SLOTS = 3;

    private final Identifier id;
    private final DefaultedList<Ingredient> ingredients;
    private final Ingredient flavorSlot;
    private final ItemStack output;
    private final float experience;
    private final int pressTime;

    public CheesePressRecipe(Identifier id, DefaultedList<Ingredient> ingredients, Ingredient flavorSlot, ItemStack output, float experience, int pressTime) {
        this.id = id;
        this.ingredients = ingredients;
        this.flavorSlot = flavorSlot;
        this.output = output;
        this.experience = experience;
        this.pressTime = pressTime;
    }

    public DefaultedList<Ingredient> getIngredientList() {
        return ingredients;
    }

    public Ingredient getFlavorSlot() {
        return flavorSlot;
    }

    public ItemStack getOutputStack() {
        return output;
    }

    public float getExperience() {
        return experience;
    }

    public int getPressTime() {
        return pressTime;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (world.isClient()) {
            return false;
        }

        int filled = 0;
        for (int i = 0; i < INPUT_SLOTS; i++) {
            if (!inventory.getStack(i).isEmpty()) {
                filled++;
            }
        }

        int required = ingredients.size() + (flavorSlot.isEmpty() ? 0 : 1);
        if (filled != required) {
            return false;
        }

        boolean[] used = new boolean[INPUT_SLOTS];

        for (Ingredient ing : ingredients) {
            boolean found = false;
            for (int i = 0; i < INPUT_SLOTS; i++) {
                if (!used[i] && ing.test(inventory.getStack(i))) {
                    used[i] = true;
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }

        if (!flavorSlot.isEmpty()) {
            boolean foundFlavor = false;
            for (int i = 0; i < INPUT_SLOTS; i++) {
                if (!used[i] && flavorSlot.test(inventory.getStack(i))) {
                    used[i] = true;
                    foundFlavor = true;
                    break;
                }
            }
            if (!foundFlavor) {
                return false;
            }
        }

        return true;
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager manager) {
        return output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= ingredients.size();
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager manager) {
        return output.copy();
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ModItems.CHEESE_PRESS);
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.CHEESE_PRESS_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.CHEESE_PRESS_TYPE;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CheesePressRecipe that)) return false;
        return Float.compare(that.experience, experience) == 0
                && pressTime == that.pressTime
                && ingredients.equals(that.ingredients)
                && flavorSlot.equals(that.flavorSlot)
                && output.equals(that.output)
                && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ingredients, flavorSlot, output, experience, pressTime);
    }

    public static class Type implements RecipeType<CheesePressRecipe> {
        private Type() {}
        public static final Type INSTANCE = new Type();
    }

    public static class Serializer implements RecipeSerializer<CheesePressRecipe> {

        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "cheese_press";

        @Override
        public CheesePressRecipe read(Identifier id, JsonObject json) {
            JsonArray ingredientsJson = JsonHelper.getArray(json, "ingredients");
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(ingredientsJson.size(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredientsJson.get(i)));
            }

            Ingredient flavor = Ingredient.EMPTY;
            if (JsonHelper.hasElement(json, "flavor_slot")) {
                flavor = Ingredient.fromJson(JsonHelper.getObject(json, "flavor_slot"));
            }

            ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "result"));

            float experience = JsonHelper.getFloat(json, "experience", 0.0F);
            int pressTime = JsonHelper.getInt(json, "pressTime", 1200);

            return new CheesePressRecipe(id, inputs, flavor, output, experience, pressTime);
        }

        @Override
        public CheesePressRecipe read(Identifier id, PacketByteBuf buf) {
            int count = buf.readVarInt();
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(count, Ingredient.EMPTY);

            inputs.replaceAll(ignored -> Ingredient.fromPacket(buf));

            Ingredient flavor = Ingredient.fromPacket(buf);
            ItemStack output = buf.readItemStack();

            float xp = buf.readFloat();
            int time = buf.readVarInt();

            return new CheesePressRecipe(id, inputs, flavor, output, xp, time);
        }

        @Override
        public void write(PacketByteBuf buf, CheesePressRecipe recipe) {
            buf.writeVarInt(recipe.ingredients.size());
            for (Ingredient ing : recipe.ingredients) {
                ing.write(buf);
            }

            recipe.flavorSlot.write(buf);
            buf.writeItemStack(recipe.output);

            buf.writeFloat(recipe.experience);
            buf.writeVarInt(recipe.pressTime);
        }
    }
}
