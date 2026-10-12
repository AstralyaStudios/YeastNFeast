package net.astralya.yeastnfeast.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.block.entity.custom.FeastTableBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FeastTableBlockEntityRenderer implements BlockEntityRenderer<FeastTableBlockEntity> {
    public static final ResourceLocation PLATE_MODEL = ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "block/feast_serving_plate");

    private static final double[] SLOT_X = {0.25, 0.75, 0.25, 0.75};
    private static final double[] SLOT_Z = {0.25, 0.25, 0.75, 0.75};
    private static final float[] PLATE_ROTATION = {-9.0f, 4.0f, 11.0f, -3.0f};
    private static final float[] FOOD_ROTATION = {2.0f, -3.0f, -2.0f, 3.0f};
    private static final double TABLETOP_Y = 14.0 / 16.0;
    private static final double FOOD_Y = 15.0 / 16.0 + 0.002;
    private static final float FOOD_SCALE = 0.58f;

    private final ItemRenderer itemRenderer;

    public FeastTableBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(FeastTableBlockEntity table, float partialTick, PoseStack pose, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        Level level = table.getLevel();
        if (level == null) return;

        BakedModel plateModel = Minecraft.getInstance().getModelManager().getModel(new ModelResourceLocation(PLATE_MODEL, "standalone"));
        for (int slot = 0; slot < FeastTableBlockEntity.SERVING_SLOTS; slot++) {
            ItemStack stack = table.getServing(slot);
            if (stack.isEmpty()) continue;

            pose.pushPose();
            pose.translate(SLOT_X[slot], TABLETOP_Y, SLOT_Z[slot]);
            pose.mulPose(Axis.YP.rotationDegrees(PLATE_ROTATION[slot]));
            pose.translate(-0.5, 0.0, -0.5);
            VertexConsumer plateBuffer = buffer.getBuffer(RenderType.solid());
            Minecraft.getInstance().getBlockRenderer().getModelRenderer().tesselateBlock(
                    level, plateModel, table.getBlockState(), table.getBlockPos(), pose, plateBuffer, false,
                    RandomSource.create(table.getBlockPos().asLong() + slot), 0L, packedOverlay);
            pose.popPose();

            pose.pushPose();
            pose.translate(SLOT_X[slot], FOOD_Y, SLOT_Z[slot]);
            pose.mulPose(Axis.YP.rotationDegrees(FOOD_ROTATION[slot]));
            pose.mulPose(Axis.XP.rotationDegrees(90.0f));
            pose.scale(FOOD_SCALE, FOOD_SCALE, FOOD_SCALE);
            itemRenderer.renderStatic(stack, ItemDisplayContext.GROUND, packedLight, packedOverlay, pose, buffer, level,
                    (int) (table.getBlockPos().asLong() + slot));
            pose.popPose();
        }
    }
}
