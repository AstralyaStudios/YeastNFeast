package net.astralya.yeastnfeast.block.entity.renderer;

import net.astralya.yeastnfeast.block.custom.CheesePressBlock;
import net.astralya.yeastnfeast.block.entity.custom.CheesePressBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.World;

public class CheesePressBlockEntityRenderer implements BlockEntityRenderer<CheesePressBlockEntity> {

    private final ItemRenderer itemRenderer;

    private static final float TRAY_Y = 3f / 16f + 0.001f;
    private static final float INPUT_SCALE = 0.55f;
    private static final float OUTPUT_SCALE = 0.72f;
    private static final float INPUT_Z_BACK = -0.08f;
    private static final float INPUT_Z_FRONT = 0.08f;
    private static final float INPUT_X_OFFSET = 0.16f;
    private static final float OUTPUT_Y_OFFSET = 0.045f;

    public CheesePressBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        this.itemRenderer = ctx.getItemRenderer();
    }

    @Override
    public void render(CheesePressBlockEntity press, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        World world = press.getWorld();
        if (world == null) {
            return;
        }

        BlockState state = press.getCachedState();
        Direction facing = state.get(CheesePressBlock.FACING);

        DefaultedList<ItemStack> inputs = press.getInputItems();
        ItemStack out = press.getOutputItem();

        boolean hasInputs = false;
        for (ItemStack s : inputs) {
            if (!s.isEmpty()) {
                hasInputs = true;
                break;
            }
        }

        boolean hasOutput = !out.isEmpty();

        if (!hasInputs && !hasOutput) {
            return;
        }

        matrices.push();
        matrices.translate(0.5, 0.0, 0.5);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-facing.asRotation()));

        if (hasInputs) {
            renderInputAt(matrices, vertexConsumers, light, overlay, world, press, inputs.get(0), -INPUT_X_OFFSET, INPUT_Z_BACK, 0);
            renderInputAt(matrices, vertexConsumers, light, overlay, world, press, inputs.get(1), INPUT_X_OFFSET, INPUT_Z_BACK, 1);
            renderInputAt(matrices, vertexConsumers, light, overlay, world, press, inputs.get(2), 0.0f, INPUT_Z_FRONT, 2);
        }

        if (hasOutput) {
            matrices.push();
            matrices.translate(0.0, TRAY_Y + OUTPUT_Y_OFFSET, 0.0);
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90f));
            matrices.scale(OUTPUT_SCALE, OUTPUT_SCALE, OUTPUT_SCALE);

            float jitter = ((press.getPos().getX() ^ press.getPos().getZ()) & 7) * 2f;
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(jitter));

            int seed = (int) (press.getPos().asLong() ^ 31L);
            itemRenderer.renderItem(out, ModelTransformationMode.GROUND, light, overlay, matrices, vertexConsumers, world, seed);

            matrices.pop();
        }

        matrices.pop();
    }

    private void renderInputAt(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, World world, CheesePressBlockEntity press, ItemStack stack, float x, float z, int salt) {
        if (stack.isEmpty()) {
            return;
        }

        matrices.push();
        matrices.translate(x, TRAY_Y, z);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90f));
        matrices.scale(INPUT_SCALE, INPUT_SCALE, INPUT_SCALE);

        float jitter = ((press.getPos().getX() + press.getPos().getZ() + salt * 13) & 7) * 1.5f;
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(jitter));

        int seed = (int) (press.getPos().asLong() + salt);
        itemRenderer.renderItem(stack, ModelTransformationMode.GROUND, light, overlay, matrices, vertexConsumers, world, seed);

        matrices.pop();
    }

    @Override
    public boolean rendersOutsideBoundingBox(CheesePressBlockEntity blockEntity) {
        return true;
    }
}