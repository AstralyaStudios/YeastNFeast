package net.astralya.yeastnfeast.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import net.astralya.yeastnfeast.YeastNFeastMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.inventory.InventoryMenu;

public class KegScreen extends AbstractContainerScreen<KegMenu> {

    public static final Rect2i PROGRESS = new Rect2i(84, 30, 27, 14);
    private static final Rect2i GAUGE = new Rect2i(130, 31, 18, 38);

    private static final ResourceLocation GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID,
            "textures/gui/keg.png");
    private final KegRecipeBookComponent recipeBook = new KegRecipeBookComponent(GUI_TEXTURE);

    public KegScreen(KegMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void init() {
        super.init();
        this.titleLabelX = 129;
        this.titleLabelY = 5;
        recipeBook.init(minecraft);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);
        int x = leftPos;
        int y = topPos;

        guiGraphics.blit(GUI_TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
        renderElements(guiGraphics, x, y);
        recipeBook.render(guiGraphics, menu, x, y, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
    }

    private void renderElements(GuiGraphics guiGraphics, int x, int y) {
        renderLiquid(guiGraphics, x + GAUGE.getX(), y + GAUGE.getY());
        if (menu.isCrafting() || menu.isReady()) {
            int progress = menu.getScaledProgress();
            guiGraphics.blit(GUI_TEXTURE, x + PROGRESS.getX(), y + PROGRESS.getY(), 176, 0,
                    progress, PROGRESS.getHeight());
        }
    }

    private void renderLiquid(GuiGraphics guiGraphics, int x, int y) {
        if (!menu.isReady() || menu.getMaxServings() <= 0 || menu.getServings() <= 0) return;
        int height = (int) Math.clamp((long) menu.getServings() * GAUGE.getHeight() / menu.getMaxServings(), 0, GAUGE.getHeight());
        TextureAtlasSprite sprite = minecraft.getTextureAtlas(InventoryMenu.BLOCK_ATLAS)
                .apply(ResourceLocation.withDefaultNamespace("block/water_still"));
        int color = menu.getLiquidColor();
        guiGraphics.enableScissor(x, y + GAUGE.getHeight() - height, x + GAUGE.getWidth(), y + GAUGE.getHeight());
        RenderSystem.enableBlend();
        guiGraphics.setColor((color >> 16 & 255) / 255.0F, (color >> 8 & 255) / 255.0F, (color & 255) / 255.0F, 1.0F);
        try {
            for (int row = 0; row < GAUGE.getHeight(); row += 16) {
                for (int col = 0; col < GAUGE.getWidth(); col += 16) {
                    guiGraphics.blit(x + col, y + GAUGE.getHeight() - row - 16, 0, 16, 16, sprite);
                }
            }
        } finally {
            guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.disableBlend();
            guiGraphics.disableScissor();
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        recipeBook.renderGhostIngredients(guiGraphics, menu, leftPos, topPos);
        ItemStack preview = menu.getResultPreview();
        if (recipeBook.renderTooltip(guiGraphics, font, mouseX, mouseY, leftPos, topPos)) {
        } else if (isHovering(32, 42, 16, 16, mouseX, mouseY)) {
            guiGraphics.renderTooltip(font, Component.translatable("gui.yeastnfeast.keg.fermenter_hint"), mouseX, mouseY);
        } else if (menu.isReady() && !preview.isEmpty()
                && isHovering(GAUGE.getX(), GAUGE.getY(), GAUGE.getWidth(), GAUGE.getHeight(), mouseX, mouseY)) {
            guiGraphics.renderTooltip(font, Component.translatable(menu.getServings() == 1
                            ? "gui.yeastnfeast.keg.serving" : "gui.yeastnfeast.keg.servings",
                    preview.getHoverName(), menu.getServings(), menu.getMaxServings()), mouseX, mouseY);
        } else {
            renderTooltip(guiGraphics, mouseX, mouseY);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return recipeBook.mouseClicked(mouseX, mouseY, button, leftPos, topPos)
                || super.mouseClicked(mouseX, mouseY, button);
    }
}
