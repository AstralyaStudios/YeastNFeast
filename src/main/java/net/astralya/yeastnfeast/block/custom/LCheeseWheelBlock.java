package net.astralya.yeastnfeast.block.custom;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Supplier;
import net.minecraft.world.item.Item;

public class LCheeseWheelBlock extends BaseCheeseWheelBlock {

    private static final VoxelShape NW = Block.box(1, 0, 1, 8, 5, 8);
    private static final VoxelShape NE = Block.box(8, 0, 1, 15, 5, 8);
    private static final VoxelShape SE = Block.box(8, 0, 8, 15, 5, 15);
    private static final VoxelShape SW = Block.box(1, 0, 8, 8, 5, 15);

    private static VoxelShape union(VoxelShape... shapes) {
        VoxelShape out = Shapes.empty();
        for (VoxelShape s : shapes) out = Shapes.or(out, s);
        return out;
    }

    private static final VoxelShape SHAPE_FULL = union(NW, NE, SE, SW);
    private static final VoxelShape SHAPE_3_4 = union(NE, SE, SW);
    private static final VoxelShape SHAPE_2_4 = union(SE, SW);
    private static final VoxelShape SHAPE_1_4 = SW;

    private static final VoxelShape[] SHAPES = new VoxelShape[] {
            SHAPE_FULL, SHAPE_3_4, SHAPE_2_4, SHAPE_1_4
    };

    public LCheeseWheelBlock(BlockBehaviour.Properties properties, Supplier<Item> sliceItem) {
        super(properties.noOcclusion(), sliceItem);
    }

    public LCheeseWheelBlock(BlockBehaviour.Properties properties) {
        this(properties, () -> null);
    }

    @Override
    protected VoxelShape shapeForCuts(int cuts) {
        return SHAPES[cuts];
    }
}