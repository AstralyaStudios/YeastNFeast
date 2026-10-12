package net.astralya.yeastnfeast.compat.jei.category;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.item.ModItems;
import net.astralya.yeastnfeast.recipe.CheesePressRecipe;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault

public class CheesePressRecipeCategory implements IRecipeCategory<CheesePressRecipe> {

    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "cheese_press");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(
            YeastNFeastMod.MODID,
            "textures/gui/cheese_press_gui.png"
    );

    public static final RecipeType<CheesePressRecipe> CHEESE_PRESS_RECIPE_TYPE =
            new RecipeType<>(UID, CheesePressRecipe.class);

    private static final int WIDTH = 176;
    private static final int HEIGHT = 81;

    private final IDrawable background;
    private final IDrawable icon;

    public CheesePressRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, WIDTH, HEIGHT);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModItems.CHEESE_PRESS.get()));
    }

    @Override
    public RecipeType<CheesePressRecipe> getRecipeType() {
        return CHEESE_PRESS_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.yeastnfeast.cheese_press");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    @Override
    public void draw(CheesePressRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        background.draw(guiGraphics, 0, 0);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CheesePressRecipe recipe, IFocusGroup focuses) {
        int[][] inputSlots = new int[][]{
                {23, 26},
                {41, 26},
                {59, 26}
        };

        int count = Math.min(CheesePressRecipe.INPUT_SLOTS, recipe.getIngredients().size());
        for (int i = 0; i < count; i++) {
            builder.addSlot(RecipeIngredientRole.INPUT, inputSlots[i][0], inputSlots[i][1])
                    .addIngredients(recipe.getIngredients().get(i));
        }

        if (!recipe.getFlavorSlot().isEmpty()) {
            builder.addSlot(RecipeIngredientRole.INPUT, 59, 26)
                    .addIngredients(recipe.getFlavorSlot());
        }

        builder.addSlot(RecipeIngredientRole.OUTPUT, 125, 28)
                .addItemStack(recipe.getResultItem(null));
    }
}
