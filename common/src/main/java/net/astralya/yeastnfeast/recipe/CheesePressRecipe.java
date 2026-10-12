package net.astralya.yeastnfeast.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.astralya.yeastnfeast.item.ModItems;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.astralya.yeastnfeast.util.inventory.ModItemStackHandler;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class CheesePressRecipe implements Recipe<ModItemStackHandler> {

    public static final int INPUT_SLOTS = 3;

    private final NonNullList<Ingredient> ingredients;
    private final Ingredient flavorSlot;
    private final ItemStack output;
    private final float experience;
    private final int pressTime;

    public CheesePressRecipe(NonNullList<Ingredient> ingredients, Ingredient flavorSlot,
                             ItemStack output, float experience, int pressTime) {
        this.ingredients = ingredients;
        this.flavorSlot = flavorSlot;
        this.output = output;
        this.experience = experience;
        this.pressTime = pressTime;
    }

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
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return output;
    }

    @Override
    public boolean matches(ModItemStackHandler inv, Level level) {
        int filled = 0;
        for (int i = 0; i < INPUT_SLOTS; i++) if (!inv.getItem(i).isEmpty()) filled++;
        int required = ingredients.size() + (flavorSlot.isEmpty() ? 0 : 1);
        if (filled != required) return false;

        boolean[] used = new boolean[INPUT_SLOTS];

        for (Ingredient ing : ingredients) {
            boolean found = false;
            for (int i = 0; i < INPUT_SLOTS; i++) {
                if (!used[i] && ing.test(inv.getItem(i))) {
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
                if (!used[i] && flavorSlot.test(inv.getItem(i))) {
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
    public ItemStack assemble(ModItemStackHandler inv, HolderLookup.Provider provider) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= ingredients.size();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.CHEESE_PRESS_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.CHEESE_PRESS_TYPE.get();
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ModItems.CHEESE_PRESS.get());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CheesePressRecipe other)) return false;
        return Float.compare(other.experience, experience) == 0 &&
                pressTime == other.pressTime &&
                Objects.equals(ingredients, other.ingredients) &&
                Objects.equals(output, other.output) &&
                Objects.equals(flavorSlot, other.flavorSlot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredients, flavorSlot, output, experience, pressTime);
    }

    public static class Serializer implements RecipeSerializer<CheesePressRecipe> {

        private static final MapCodec<CheesePressRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.listOf().fieldOf("ingredients").xmap(list -> {
                    NonNullList<Ingredient> res = NonNullList.create();
                    res.addAll(list);
                    return res;
                }, list -> list).forGetter(CheesePressRecipe::getIngredients),
                Ingredient.CODEC.optionalFieldOf("flavor_slot", Ingredient.EMPTY).forGetter(CheesePressRecipe::getFlavorSlot),
                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(r -> r.output),
                Codec.FLOAT.optionalFieldOf("experience", 0.0F).forGetter(CheesePressRecipe::getExperience),
                Codec.INT.optionalFieldOf("pressTime", 1200).forGetter(CheesePressRecipe::getPressTime)
        ).apply(inst, CheesePressRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, CheesePressRecipe> STREAM_CODEC = StreamCodec.of(
                Serializer::toNetwork, Serializer::fromNetwork
        );

        @Override
        public MapCodec<CheesePressRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, CheesePressRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static CheesePressRecipe fromNetwork(RegistryFriendlyByteBuf buf) {
            int size = buf.readVarInt();
            NonNullList<Ingredient> ingredients = NonNullList.withSize(size, Ingredient.EMPTY);
            ingredients.replaceAll(i -> Ingredient.CONTENTS_STREAM_CODEC.decode(buf));
            Ingredient flavor = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
            ItemStack result = ItemStack.STREAM_CODEC.decode(buf);
            float xp = buf.readFloat();
            int time = buf.readVarInt();
            return new CheesePressRecipe(ingredients, flavor, result, xp, time);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buf, CheesePressRecipe recipe) {
            buf.writeVarInt(recipe.ingredients.size());
            for (Ingredient i : recipe.ingredients) {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buf, i);
            }
            Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.flavorSlot);
            ItemStack.STREAM_CODEC.encode(buf, recipe.output);
            buf.writeFloat(recipe.experience);
            buf.writeVarInt(recipe.pressTime);
        }
    }
}
