package net.astralya.yeastnfeast.block;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;

public class ModFlammables {

    public static void registerFlammables() {

        FlammableBlockRegistry instance = FlammableBlockRegistry.getDefaultInstance();

        instance.add(ModBlocks.TREE_TAP, 5, 20);
        instance.add(ModBlocks.KEG, 5, 20);
        instance.add(ModBlocks.CHEESE_PRESS, 5, 20);

        instance.add(ModBlocks.BAG_OF_ELDERBERRIES, 30, 60);
        instance.add(ModBlocks.BAG_OF_GARLIC, 30, 60);
        instance.add(ModBlocks.BAG_OF_GINGER, 30, 60);
        instance.add(ModBlocks.BAG_OF_HAWTHORN_BERRIES, 30, 60);
        instance.add(ModBlocks.BAG_OF_LEMON, 30, 60);
        instance.add(ModBlocks.BAG_OF_MINT, 30, 60);
        instance.add(ModBlocks.BAG_OF_ROSE_HIPS, 30, 60);
        instance.add(ModBlocks.BAG_OF_THISTLE, 30, 60);

        instance.add(ModBlocks.BARLEY_BLOCK, 60, 20);
        instance.add(ModBlocks.RYE_BLOCK, 60, 20);

        instance.add(ModBlocks.WILD_BARLEY, 60, 100);
        instance.add(ModBlocks.WILD_RYE, 60, 100);
        instance.add(ModBlocks.WILD_GINGER, 60, 100);
        instance.add(ModBlocks.WILD_GARLIC, 60, 100);
        instance.add(ModBlocks.THISTLE, 60, 100);

        instance.add(ModBlocks.FLOWERING_LEMON_TREE_LEAVES, 30, 60);
        instance.add(ModBlocks.LEMON_TREE_LEAVES, 30, 60);
        instance.add(ModBlocks.FLOWERING_HAWTHORN_TREE_LEAVES, 30, 60);
        instance.add(ModBlocks.HAWTHORN_TREE_LEAVES, 30, 60);
        instance.add(ModBlocks.MAPLE_LEAVES, 30, 60);

        instance.add(ModBlocks.LEMON_SAPLING, 60, 100);
        instance.add(ModBlocks.HAWTHORN_SAPLING, 60, 100);
        instance.add(ModBlocks.MAPLE_SAPLING, 60, 100);

        instance.add(ModBlocks.MAPLE_LOG, 5, 5);
        instance.add(ModBlocks.STRIPPED_MAPLE_LOG, 5, 5);
        instance.add(ModBlocks.MAPLE_WOOD, 5, 5);
        instance.add(ModBlocks.STRIPPED_MAPLE_WOOD, 5, 5);

        instance.add(ModBlocks.MAPLE_PLANKS, 5, 20);
        instance.add(ModBlocks.MAPLE_STAIRS, 5, 20);
        instance.add(ModBlocks.MAPLE_SLAB, 5, 20);
        instance.add(ModBlocks.MAPLE_FENCE, 5, 20);
        instance.add(ModBlocks.MAPLE_FENCE_GATE, 5, 20);
        instance.add(ModBlocks.MAPLE_TRAPDOOR, 5, 20);
        instance.add(ModBlocks.MAPLE_DOOR, 5, 20);
        instance.add(ModBlocks.MAPLE_BUTTON, 5, 20);
        instance.add(ModBlocks.MAPLE_PRESSURE_PLATE, 5, 20);
        instance.add(ModBlocks.MAPLE_SIGN, 5, 20);
        instance.add(ModBlocks.MAPLE_WALL_SIGN, 5, 20);
        instance.add(ModBlocks.MAPLE_HANGING_SIGN, 5, 20);
        instance.add(ModBlocks.MAPLE_HANGING_WALL_SIGN, 5, 20);
    }
}