package net.astralya.yeastnfeast.block;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;

public final class ModFlammables {
    private ModFlammables() {}

    public static void register() {
        FireBlock fire = (FireBlock) Blocks.FIRE;

        fire.setFlammable(ModBlocks.TREE_TAP.get(), 5, 20);
        fire.setFlammable(ModBlocks.KEG.get(), 5, 20);
        fire.setFlammable(ModBlocks.CHEESE_PRESS.get(), 5, 20);

        fire.setFlammable(ModBlocks.BAG_OF_ELDERBERRIES.get(), 30, 60);
        fire.setFlammable(ModBlocks.BAG_OF_GARLIC.get(), 30, 60);
        fire.setFlammable(ModBlocks.BAG_OF_GINGER.get(), 30, 60);
        fire.setFlammable(ModBlocks.BAG_OF_HAWTHORN_BERRIES.get(), 30, 60);
        fire.setFlammable(ModBlocks.BAG_OF_LEMON.get(), 30, 60);
        fire.setFlammable(ModBlocks.BAG_OF_MINT.get(), 30, 60);
        fire.setFlammable(ModBlocks.BAG_OF_ROSE_HIPS.get(), 30, 60);
        fire.setFlammable(ModBlocks.BAG_OF_THISTLE.get(), 30, 60);

        fire.setFlammable(ModBlocks.BARLEY_BLOCK.get(), 60, 20);
        fire.setFlammable(ModBlocks.RYE_BLOCK.get(), 60, 20);

        fire.setFlammable(ModBlocks.WILD_BARLEY.get(), 60, 100);
        fire.setFlammable(ModBlocks.WILD_RYE.get(), 60, 100);
        fire.setFlammable(ModBlocks.WILD_GINGER.get(), 60, 100);
        fire.setFlammable(ModBlocks.WILD_GARLIC.get(), 60, 100);
        fire.setFlammable(ModBlocks.THISTLE.get(), 60, 100);

        fire.setFlammable(ModBlocks.FLOWERING_LEMON_TREE_LEAVES.get(), 30, 60);
        fire.setFlammable(ModBlocks.LEMON_TREE_LEAVES.get(), 30, 60);
        fire.setFlammable(ModBlocks.FLOWERING_HAWTHORN_TREE_LEAVES.get(), 30, 60);
        fire.setFlammable(ModBlocks.HAWTHORN_TREE_LEAVES.get(), 30, 60);
        fire.setFlammable(ModBlocks.MAPLE_LEAVES.get(), 30, 60);

        fire.setFlammable(ModBlocks.LEMON_SAPLING.get(), 60, 100);
        fire.setFlammable(ModBlocks.HAWTHORN_SAPLING.get(), 60, 100);
        fire.setFlammable(ModBlocks.MAPLE_SAPLING.get(), 60, 100);

        fire.setFlammable(ModBlocks.MAPLE_LOG.get(), 5, 5);
        fire.setFlammable(ModBlocks.STRIPPED_MAPLE_LOG.get(), 5, 5);
        fire.setFlammable(ModBlocks.MAPLE_WOOD.get(), 5, 5);
        fire.setFlammable(ModBlocks.STRIPPED_MAPLE_WOOD.get(), 5, 5);

        fire.setFlammable(ModBlocks.MAPLE_PLANKS.get(), 5, 20);
        fire.setFlammable(ModBlocks.MAPLE_STAIRS.get(), 5, 20);
        fire.setFlammable(ModBlocks.MAPLE_SLAB.get(), 5, 20);
        fire.setFlammable(ModBlocks.MAPLE_FENCE.get(), 5, 20);
        fire.setFlammable(ModBlocks.MAPLE_FENCE_GATE.get(), 5, 20);
        fire.setFlammable(ModBlocks.MAPLE_TRAPDOOR.get(), 5, 20);
        fire.setFlammable(ModBlocks.MAPLE_DOOR.get(), 5, 20);
        fire.setFlammable(ModBlocks.MAPLE_BUTTON.get(), 5, 20);
        fire.setFlammable(ModBlocks.MAPLE_PRESSURE_PLATE.get(), 5, 20);
        fire.setFlammable(ModBlocks.MAPLE_SIGN.get(), 5, 20);
        fire.setFlammable(ModBlocks.MAPLE_WALL_SIGN.get(), 5, 20);
        fire.setFlammable(ModBlocks.MAPLE_HANGING_SIGN.get(), 5, 20);
        fire.setFlammable(ModBlocks.MAPLE_HANGING_WALL_SIGN.get(), 5, 20);
    }
}