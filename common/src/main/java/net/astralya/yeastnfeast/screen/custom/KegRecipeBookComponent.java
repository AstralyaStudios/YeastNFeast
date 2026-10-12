package net.astralya.yeastnfeast.screen.custom;

import net.astralya.yeastnfeast.recipe.KegRecipe;
import net.astralya.yeastnfeast.recipe.ModRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

final class KegRecipeBookComponent {

    private static final int BUTTON_X = 50;
    private static final int BUTTON_Y = 41;
    private static final int PANEL_X = -84;
    private static final int PANEL_Y = 4;
    private static final int PANEL_WIDTH = 62;
    private static final int PANEL_HEIGHT = 80;
    private static final int RECIPES_PER_PAGE = 9;
    private static final int[] SLOT_X = { 14, 32, 50, 32 };
    private static final int[] SLOT_Y = { 24, 24, 24, 42 };

    private final ResourceLocation texture;
    private List<RecipeHolder<KegRecipe>> recipes = List.of();
    private RecipeHolder<KegRecipe> selected;
    private boolean open;
    private int page;

    KegRecipeBookComponent(ResourceLocation texture) {
        this.texture = texture;
    }

    void init(Minecraft minecraft) {
        if (minecraft.level == null) return;
        recipes = minecraft.level.getRecipeManager().getAllRecipesFor(ModRecipes.KEG_TYPE.get()).stream()
                .sorted(Comparator.comparing(recipe -> recipe.id().toString())).toList();
        page = Math.min(page, maxPage());
        if (selected != null && recipes.stream().noneMatch(recipe -> recipe.id().equals(selected.id()))) selected = null;
    }

    void render(GuiGraphics guiGraphics, KegMenu menu, int leftPos, int topPos, int mouseX, int mouseY) {
        if (open) {
            int x = leftPos + PANEL_X;
            int y = topPos + PANEL_Y;
            guiGraphics.fill(x, y, x + PANEL_WIDTH, y + PANEL_HEIGHT, 0xFF3A2418);
            guiGraphics.fill(x + 2, y + 2, x + PANEL_WIDTH - 2, y + PANEL_HEIGHT - 2, 0xFF8A623E);
            int start = page * RECIPES_PER_PAGE;
            for (int i = 0; i < RECIPES_PER_PAGE && start + i < recipes.size(); i++) {
                int entryX = x + 4 + i % 3 * 18;
                int entryY = y + 5 + i / 3 * 18;
                RecipeHolder<KegRecipe> recipe = recipes.get(start + i);
                if (inside(mouseX, mouseY, entryX, entryY, 16, 16)) guiGraphics.fill(entryX - 1, entryY - 1, entryX + 17, entryY + 17, 0xFFC8B391);
                guiGraphics.renderItem(recipe.value().getResultItem(Minecraft.getInstance().level.registryAccess()), entryX, entryY);
            }
            if (page > 0) renderArrow(guiGraphics, x + 8, y + 67, false);
            if (page < maxPage()) renderArrow(guiGraphics, x + 48, y + 67, true);
        }
        int buttonX = leftPos + BUTTON_X;
        int buttonY = topPos + BUTTON_Y;
        guiGraphics.blit(texture, buttonX, buttonY, 0,
                inside(mouseX, mouseY, buttonX, buttonY, 18, 18) ? 188 : 169, 18, 18);
    }

    void renderGhostIngredients(GuiGraphics guiGraphics, KegMenu menu, int leftPos, int topPos) {
        if (!open || selected == null) return;
        for (int i = 0; i < 4; i++) {
            if (menu.getSlot(36 + i).hasItem()) continue;
            Ingredient ingredient = i < 3 ? selected.value().getIngredients().get(i) : selected.value().getYeastSlot();
            ItemStack[] choices = ingredient.getItems();
            if (choices.length == 0) continue;
            int x = leftPos + SLOT_X[i];
            int y = topPos + SLOT_Y[i];
            guiGraphics.renderItem(choices[0], x, y);
            guiGraphics.fill(x, y, x + 16, y + 16, 0x66FFFFFF);
        }
    }

    boolean mouseClicked(double mouseX, double mouseY, int button, int leftPos, int topPos) {
        if (button != 0) return false;
        if (inside(mouseX, mouseY, leftPos + BUTTON_X, topPos + BUTTON_Y, 18, 18)) {
            open = !open;
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.BOOK_PAGE_TURN, 1.0F));
            return true;
        }
        if (!open) return false;
        int x = leftPos + PANEL_X;
        int y = topPos + PANEL_Y;
        int start = page * RECIPES_PER_PAGE;
        for (int i = 0; i < RECIPES_PER_PAGE && start + i < recipes.size(); i++) {
            if (inside(mouseX, mouseY, x + 4 + i % 3 * 18, y + 5 + i / 3 * 18, 16, 16)) {
                selected = recipes.get(start + i);
                return true;
            }
        }
        if (page > 0 && inside(mouseX, mouseY, x + 4, y + 63, 18, 14)) { page--; return true; }
        if (page < maxPage() && inside(mouseX, mouseY, x + 40, y + 63, 18, 14)) { page++; return true; }
        return inside(mouseX, mouseY, x, y, PANEL_WIDTH, PANEL_HEIGHT);
    }

    boolean renderTooltip(GuiGraphics guiGraphics, Font font, int mouseX, int mouseY, int leftPos, int topPos) {
        if (!open) return false;
        int x = leftPos + PANEL_X + 4;
        int y = topPos + PANEL_Y + 5;
        int start = page * RECIPES_PER_PAGE;
        for (int i = 0; i < RECIPES_PER_PAGE && start + i < recipes.size(); i++) {
            if (inside(mouseX, mouseY, x + i % 3 * 18, y + i / 3 * 18, 16, 16)) {
                guiGraphics.renderTooltip(font, recipes.get(start + i).value().getResultItem(Minecraft.getInstance().level.registryAccess()), mouseX, mouseY);
                return true;
            }
        }
        return false;
    }

    private int maxPage() { return Math.max(0, (recipes.size() - 1) / RECIPES_PER_PAGE); }

    private static boolean isAvailable(RecipeHolder<KegRecipe> recipe, KegMenu menu) {
        List<ItemStack> available = new ArrayList<>();
        for (int slot = 36; slot < 40; slot++) available.add(menu.getSlot(slot).getItem().copy());
        available.addAll(Minecraft.getInstance().player.getInventory().items.stream().map(ItemStack::copy).toList());
        for (Ingredient ingredient : recipe.value().getIngredients()) {
            if (!consume(ingredient, available)) return false;
        }
        return consume(recipe.value().getYeastSlot(), available);
    }

    private static boolean consume(Ingredient ingredient, List<ItemStack> available) {
        for (ItemStack stack : available) {
            if (!stack.isEmpty() && ingredient.test(stack)) {
                stack.shrink(1);
                return true;
            }
        }
        return false;
    }

    private static boolean inside(double mouseX, double mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
    }

    private static void renderArrow(GuiGraphics guiGraphics, int x, int y, boolean right) {
        for (int row = 0; row < 5; row++) {
            int width = row < 3 ? row + 1 : 5 - row;
            int start = right ? x : x + 3 - width;
            guiGraphics.fill(start, y + row, start + width, y + row + 1, 0xFFFFFFFF);
        }
    }
}
