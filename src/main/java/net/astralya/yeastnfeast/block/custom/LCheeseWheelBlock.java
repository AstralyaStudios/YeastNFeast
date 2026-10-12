package net.astralya.yeastnfeast.block.custom;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

import java.util.function.Supplier;

public class LCheeseWheelBlock extends BaseCheeseWheelBlock {

    private static final VoxelShape NW = Block.createCuboidShape(1, 0, 1, 8, 5, 8);
    private static final VoxelShape NE = Block.createCuboidShape(8, 0, 1, 15, 5, 8);
    private static final VoxelShape SE = Block.createCuboidShape(8, 0, 8, 15, 5, 15);
    private static final VoxelShape SW = Block.createCuboidShape(1, 0, 8, 8, 5, 15);

    private static VoxelShape union(VoxelShape... shapes) {
        VoxelShape out = VoxelShapes.empty();
        for (VoxelShape s : shapes) {
            out = VoxelShapes.union(out, s);
        }
        return out;
    }

    private static final VoxelShape SHAPE_FULL = union(NW, NE, SE, SW);
    private static final VoxelShape SHAPE_3_4 = union(NE, SE, SW);
    private static final VoxelShape SHAPE_2_4 = union(SE, SW);
    private static final VoxelShape SHAPE_1_4 = SW;

    private static final VoxelShape[] SHAPES = new VoxelShape[]{
            SHAPE_FULL, SHAPE_3_4, SHAPE_2_4, SHAPE_1_4
    };

    public LCheeseWheelBlock(Settings settings, Supplier<Item> sliceItem) {
        super(settings, sliceItem);
    }

    public LCheeseWheelBlock(Settings settings) {
        this(settings, () -> null);
    }

    @Override
    protected VoxelShape shapeForCuts(int cuts) {
        return SHAPES[cuts];
    }
}