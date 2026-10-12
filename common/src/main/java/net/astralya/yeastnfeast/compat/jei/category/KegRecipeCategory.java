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
import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.recipe.KegRecipe;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class KegRecipeCategory implements IRecipeCategory<KegRecipe> {

    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "keg");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID,
            "textures/gui/keg_gui_2.png");

    public static final RecipeType<KegRecipe> KEG_RECIPE_TYPE =
            new RecipeType<>(UID, KegRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    private static final int WIDTH = 176;
    private static final int HEIGHT = 81;

    public KegRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, WIDTH, HEIGHT);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK,
                new ItemStack(ModBlocks.KEG.get()));
    }

    @Override
    public RecipeType<KegRecipe> getRecipeType() {
        return KEG_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.yeastnfeast.keg");
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
    public void draw(KegRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        background.draw(guiGraphics, 0, 0);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, KegRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 23, 26).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 41, 26).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.INPUT, 59, 26).addIngredients(recipe.getIngredients().get(2));
        builder.addSlot(RecipeIngredientRole.INPUT, 52, 48).addIngredients(recipe.getYeastSlot());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 125, 28).addItemStack(recipe.getResultItem(null));
    }
}
