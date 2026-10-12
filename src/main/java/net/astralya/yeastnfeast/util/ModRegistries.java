package net.astralya.yeastnfeast.util;

import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.item.ModItems;
import net.minecraft.block.ComposterBlock;

public class ModRegistries {

    public static void registerModStuff() {
        registerCompostables();
        registerStrippables();
    }

    public static final float SMALL = 0.3f;
    public static final float MEDIUM = 0.6f;
    public static final float LARGE = 0.8f;
    public static final float VERY_LARGE = 1.0f;

    private static void registerCompostables() {
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.BARLEY_SEEDS, SMALL);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.RYE_SEEDS, SMALL);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.MINT_SEEDS, SMALL);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.ELDERBERRIES, SMALL);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.HAWTHORN_BERRIES, SMALL);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.ROSE_HIPS, SMALL);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.LEMON, SMALL);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModBlocks.HAWTHORN_SAPLING, SMALL);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModBlocks.LEMON_SAPLING, SMALL);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModBlocks.FLOWERING_LEMON_TREE_LEAVES, SMALL);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModBlocks.LEMON_TREE_LEAVES, SMALL);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModBlocks.FLOWERING_HAWTHORN_TREE_LEAVES, SMALL);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModBlocks.HAWTHORN_TREE_LEAVES, SMALL);

        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.GARLIC, MEDIUM);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.MINT, MEDIUM);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.GINGER, MEDIUM);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.BARLEY, MEDIUM);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.RYE, MEDIUM);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModBlocks.THISTLE, MEDIUM);

        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModBlocks.BARLEY_BLOCK, LARGE);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModBlocks.RYE_BLOCK, LARGE);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.BARLEY_BREAD, LARGE);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.RYE_BREAD, LARGE);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.MOLASSES_BREAD, LARGE);

        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.BERRY_ROLL, VERY_LARGE);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.ROSE_TART, VERY_LARGE);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.ELDERBERRY_PIE, VERY_LARGE);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.APPLE_PIE, VERY_LARGE);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.MINTED_CHEESE_TART, VERY_LARGE);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.QUICHE, VERY_LARGE);
    }

    private static void registerStrippables() {
        StrippableBlockRegistry.register(ModBlocks.MAPLE_LOG, ModBlocks.STRIPPED_MAPLE_LOG);
        StrippableBlockRegistry.register(ModBlocks.MAPLE_WOOD, ModBlocks.STRIPPED_MAPLE_WOOD);
    }
}
