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
public class KegRecipe implements Recipe<ModItemStackHandler> {

    public static final int INPUT_SLOTS = 3;

    private final NonNullList<Ingredient> ingredients;
    private final Ingredient yeastSlot;
    private final Ingredient extractionContainer;
    private final ItemStack output;
    private final float experience;
    private final int brewTime;
    private final int liquidColor;
    private final int servings;

    public KegRecipe(NonNullList<Ingredient> ingredients, Ingredient yeastSlot, ItemStack output, float experience, int brewTime, int liquidColor, Ingredient extractionContainer, int servings) {
        this.ingredients = ingredients;
        this.yeastSlot = yeastSlot;
        this.output = output;
        this.experience = experience;
        this.brewTime = brewTime;
        this.liquidColor = liquidColor;
        this.extractionContainer = extractionContainer;
        this.servings = servings;
    }

    public NonNullList<Ingredient> getIngredients() {
        return ingredients;
    }

    public Ingredient getYeastSlot() {
        return yeastSlot;
    }

    public Ingredient getExtractionContainer() { return extractionContainer; }

    public int getServings() { return servings; }

    public float getExperience() {
        return experience;
    }

    public int getLiquidColor() {
        return liquidColor;
    }

    public int getBrewTime() {
        return brewTime;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return output;
    }

    @Override
    public boolean matches(ModItemStackHandler inv, Level level) {
        if (ingredients.size() != INPUT_SLOTS) return false;
        int inputsCount = 0;
        for (int i = 0; i < 3; i++) {
            if (!inv.getItem(i).isEmpty()) inputsCount++;
        }

        if (inputsCount != ingredients.size()) return false;

        boolean[] used = new boolean[3];
        for (Ingredient ingredient : ingredients) {
            boolean found = false;
            for (int i = 0; i < 3; i++) {
                if (!used[i] && ingredient.test(inv.getItem(i))) {
                    used[i] = true;
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }

        return yeastSlot.test(inv.getItem(3));
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
        return ModRecipes.KEG_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.KEG_TYPE.get();
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ModItems.KEG.get());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof KegRecipe other)) return false;
        return Float.compare(other.experience, experience) == 0 &&
                brewTime == other.brewTime &&
                liquidColor == other.liquidColor &&
                servings == other.servings &&
                Objects.equals(ingredients, other.ingredients) &&
                Objects.equals(output, other.output) &&
                Objects.equals(yeastSlot, other.yeastSlot) &&
                Objects.equals(extractionContainer, other.extractionContainer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredients, yeastSlot, output, experience, brewTime, liquidColor, extractionContainer, servings);
    }

    public static class Serializer implements RecipeSerializer<KegRecipe> {

        private static final Codec<Integer> LIQUID_COLOR_CODEC = Codec.STRING.comapFlatMap(
                value -> value.matches("#[0-9a-fA-F]{6}")
                        ? com.mojang.serialization.DataResult.success(Integer.parseInt(value.substring(1), 16))
                        : com.mojang.serialization.DataResult.error(() -> "liquid_color must be #RRGGBB"),
                value -> String.format("#%06X", value));

        private static final MapCodec<KegRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.listOf().fieldOf("ingredients").xmap(list -> {
                    NonNullList<Ingredient> result = NonNullList.create();
                    result.addAll(list);
                    return result;
                }, list -> list).forGetter(KegRecipe::getIngredients),
                Ingredient.CODEC_NONEMPTY.fieldOf("yeast_slot").forGetter(KegRecipe::getYeastSlot),
                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(r -> r.output),
                Codec.FLOAT.optionalFieldOf("experience", 0.0F).forGetter(KegRecipe::getExperience),
                Codec.INT.optionalFieldOf("brewtime", 200).forGetter(KegRecipe::getBrewTime),
                LIQUID_COLOR_CODEC.fieldOf("liquid_color").forGetter(KegRecipe::getLiquidColor),
                Ingredient.CODEC_NONEMPTY.fieldOf("extraction_container").forGetter(KegRecipe::getExtractionContainer),
                Codec.intRange(1, Integer.MAX_VALUE).fieldOf("servings").forGetter(KegRecipe::getServings)
        ).apply(inst, KegRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, KegRecipe> STREAM_CODEC = StreamCodec.of(
                Serializer::toNetwork, Serializer::fromNetwork
        );

        @Override
        public MapCodec<KegRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, KegRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static KegRecipe fromNetwork(RegistryFriendlyByteBuf buf) {
            int size = buf.readVarInt();
            NonNullList<Ingredient> ingredients = NonNullList.withSize(size, Ingredient.EMPTY);
            ingredients.replaceAll(i -> Ingredient.CONTENTS_STREAM_CODEC.decode(buf));
            Ingredient yeast = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
            ItemStack result = ItemStack.STREAM_CODEC.decode(buf);
            float experience = buf.readFloat();
            int brewTime = buf.readVarInt();
            return new KegRecipe(ingredients, yeast, result, experience, brewTime, buf.readInt(), Ingredient.CONTENTS_STREAM_CODEC.decode(buf), buf.readVarInt());
        }

        private static void toNetwork(RegistryFriendlyByteBuf buf, KegRecipe recipe) {
            buf.writeVarInt(recipe.ingredients.size());
            for (Ingredient i : recipe.ingredients) {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buf, i);
            }
            Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.yeastSlot);
            ItemStack.STREAM_CODEC.encode(buf, recipe.output);
            buf.writeFloat(recipe.experience);
            buf.writeVarInt(recipe.brewTime);
            buf.writeInt(recipe.liquidColor);
            Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.extractionContainer);
            buf.writeVarInt(recipe.servings);
        }
    }
}
