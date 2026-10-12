package net.astralya.yeastnfeast.block.custom;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

import java.util.function.Supplier;

public class MCheeseWheelBlock extends BaseCheeseWheelBlock {

    private static final VoxelShape NW = Block.createCuboidShape(2, 0, 2, 8, 5, 8);
    private static final VoxelShape NE = Block.createCuboidShape(8, 0, 2, 14, 5, 8);
    private static final VoxelShape SE = Block.createCuboidShape(8, 0, 8, 14, 5, 14);
    private static final VoxelShape SW = Block.createCuboidShape(2, 0, 8, 8, 5, 14);

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

    public MCheeseWheelBlock(Settings settings, Supplier<Item> sliceItem) {
        super(settings, sliceItem);
    }

    public MCheeseWheelBlock(Settings settings) {
        this(settings, () -> null);
    }

    @Override
    protected VoxelShape shapeForCuts(int cuts) {
        return SHAPES[cuts];
    }
}