package net.astralya.yeastnfeast.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.astralya.yeastnfeast.YeastNFeastMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class KegScreen extends AbstractContainerScreen<KegMenu> {

    public static final Rect2i PROGRESS = new Rect2i(81, 26, 0, 15);

    private static final ResourceLocation TEXTURE = new ResourceLocation(YeastNFeastMod.MODID,
            "textures/gui/keg_gui.png");

    public KegScreen(KegMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        titleLabelX = 5;
        titleLabelY = 5;
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        pGuiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
        renderProgressArrow(pGuiGraphics, x, y);

    }

    private void renderProgressArrow(GuiGraphics pGuiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            int h = menu.getScaledProgress();
            pGuiGraphics.blit(TEXTURE, x + PROGRESS.getX(), y + PROGRESS.getY(),
                    176, 15, h + 4, PROGRESS.getHeight());
        }
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }
}
