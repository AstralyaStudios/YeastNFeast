package net.astralya.yeastnfeast.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.astralya.yeastnfeast.block.custom.CheesePressBlock;
import net.astralya.yeastnfeast.block.entity.custom.CheesePressBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class CheesePressBlockEntityRenderer implements BlockEntityRenderer<CheesePressBlockEntity> {

    private final ItemRenderer itemRenderer;

    private static final float TRAY_Y = 3f / 16f + 0.001f;
    private static final float INPUT_SCALE = 0.55f;
    private static final float OUTPUT_SCALE = 0.72f;
    private static final float INPUT_Z_BACK = -0.08f;
    private static final float INPUT_Z_FRONT = 0.08f;
    private static final float INPUT_X_OFFSET = 0.16f;
    private static final float OUTPUT_Y_OFFSET = 0.045f;

    public CheesePressBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        this.itemRenderer = ctx.getItemRenderer();
    }

    @Override
    public void render(CheesePressBlockEntity press, float partialTick, PoseStack pose, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        Level level = press.getLevel();
        if (level == null) return;

        BlockState state = press.getBlockState();
        Direction facing = state.getValue(CheesePressBlock.FACING);

        NonNullList<ItemStack> inputs = press.getInputItems();
        ItemStack out = press.getOutputItem();

        boolean hasInputs = false;
        for (ItemStack s : inputs) { if (!s.isEmpty()) { hasInputs = true; break; } }
        boolean hasOutput = !out.isEmpty();

        if (!hasInputs && !hasOutput) return;

        pose.pushPose();
        pose.translate(0.5, 0.0, 0.5);
        pose.mulPose(Axis.YP.rotationDegrees(-facing.toYRot()));

        if (hasInputs) {
            renderInputAt(pose, buffer, packedLight, packedOverlay, level, press, inputs.get(0), -INPUT_X_OFFSET, INPUT_Z_BACK, 0);
            renderInputAt(pose, buffer, packedLight, packedOverlay, level, press, inputs.get(1),  INPUT_X_OFFSET, INPUT_Z_BACK, 1);
            renderInputAt(pose, buffer, packedLight, packedOverlay, level, press, inputs.get(2),  0.0f,            INPUT_Z_FRONT, 2);
        }

        if (hasOutput) {
            pose.pushPose();
            pose.translate(0.0, TRAY_Y + OUTPUT_Y_OFFSET, 0.0);
            pose.mulPose(Axis.XP.rotationDegrees(90f));
            pose.scale(OUTPUT_SCALE, OUTPUT_SCALE, OUTPUT_SCALE);
            float jitter = ((press.getBlockPos().getX() ^ press.getBlockPos().getZ()) & 7) * 2f;
            pose.mulPose(Axis.ZP.rotationDegrees(jitter));
            itemRenderer.renderStatic(out, ItemDisplayContext.GROUND, packedLight, packedOverlay, pose, buffer, level, (int)(press.getBlockPos().asLong() ^ 31L));
            pose.popPose();
        }

        pose.popPose();
    }

    private void renderInputAt(PoseStack pose, MultiBufferSource buffer, int packedLight, int packedOverlay, Level level, CheesePressBlockEntity press, ItemStack stack, float x, float z, int salt) {
        if (stack == null || stack.isEmpty()) return;
        pose.pushPose();
        pose.translate(x, TRAY_Y, z);
        pose.mulPose(Axis.XP.rotationDegrees(90f));
        pose.scale(INPUT_SCALE, INPUT_SCALE, INPUT_SCALE);
        float jitter = ((press.getBlockPos().getX() + press.getBlockPos().getZ() + salt * 13) & 7) * 1.5f;
        pose.mulPose(Axis.ZP.rotationDegrees(jitter));
        itemRenderer.renderStatic(stack, ItemDisplayContext.GROUND, packedLight, packedOverlay, pose, buffer, level, (int)(press.getBlockPos().asLong() + salt));
        pose.popPose();
    }
}
