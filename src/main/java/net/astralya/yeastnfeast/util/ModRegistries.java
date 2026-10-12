package net.astralya.yeastnfeast.util;

import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.item.ModItems;
import net.minecraft.world.level.block.ComposterBlock;

public class ModRegistries {
    public static void registerCompostables() {
        ComposterBlock.COMPOSTABLES.put(ModItems.BARLEY_SEEDS.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ModItems.RYE_SEEDS.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ModItems.MINT_SEEDS.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ModItems.ELDERBERRIES.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ModItems.HAWTHORN_BERRIES.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ModItems.ROSE_HIPS.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ModItems.LEMON.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ModBlocks.HAWTHORN_SAPLING.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ModBlocks.LEMON_SAPLING.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ModBlocks.FLOWERING_LEMON_TREE_LEAVES.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ModBlocks.LEMON_TREE_LEAVES.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ModBlocks.FLOWERING_HAWTHORN_TREE_LEAVES.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(ModBlocks.HAWTHORN_TREE_LEAVES.get(), 0.3f);

        ComposterBlock.COMPOSTABLES.put(ModItems.GARLIC.get(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(ModItems.MINT.get(), 0.5f);

        ComposterBlock.COMPOSTABLES.put(ModItems.GINGER.get(), 0.6f);
        ComposterBlock.COMPOSTABLES.put(ModItems.BARLEY.get(), 0.6f);
        ComposterBlock.COMPOSTABLES.put(ModItems.RYE.get(), 0.6f);

        ComposterBlock.COMPOSTABLES.put(ModBlocks.BARLEY_BLOCK.get(), 0.85f);
        ComposterBlock.COMPOSTABLES.put(ModBlocks.RYE_BLOCK.get(), 0.85f);
        ComposterBlock.COMPOSTABLES.put(ModItems.BARLEY_BREAD.get(), 0.85f);
        ComposterBlock.COMPOSTABLES.put(ModItems.RYE_BREAD.get(), 0.85f);
        ComposterBlock.COMPOSTABLES.put(ModItems.MOLASSES_BREAD.get(), 0.85f);

        ComposterBlock.COMPOSTABLES.put(ModItems.BERRY_ROLL.get(), 1f);
        ComposterBlock.COMPOSTABLES.put(ModItems.ROSE_TART.get(), 1f);
        ComposterBlock.COMPOSTABLES.put(ModItems.ELDERBERRY_PIE.get(), 1f);
        ComposterBlock.COMPOSTABLES.put(ModItems.APPLE_PIE.get(), 1f);
    }
}
