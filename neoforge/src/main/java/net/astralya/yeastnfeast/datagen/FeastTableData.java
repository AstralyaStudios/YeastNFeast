package net.astralya.yeastnfeast.datagen;

import dev.architectury.registry.registries.RegistrySupplier;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.function.Supplier;

record FeastTableData(
        String wood,
        RegistrySupplier<Block> table,
        ResourceLocation logTexture,
        ResourceLocation planksTexture,
        Supplier<? extends ItemLike> logIngredient,
        Supplier<? extends ItemLike> planksIngredient
) {
    static final List<FeastTableData> VALUES = List.of(
            vanilla("oak", ModBlocks.OAK_FEAST_TABLE, Blocks.OAK_LOG, Blocks.OAK_PLANKS),
            vanilla("spruce", ModBlocks.SPRUCE_FEAST_TABLE, Blocks.SPRUCE_LOG, Blocks.SPRUCE_PLANKS),
            vanilla("birch", ModBlocks.BIRCH_FEAST_TABLE, Blocks.BIRCH_LOG, Blocks.BIRCH_PLANKS),
            vanilla("jungle", ModBlocks.JUNGLE_FEAST_TABLE, Blocks.JUNGLE_LOG, Blocks.JUNGLE_PLANKS),
            vanilla("acacia", ModBlocks.FEAST_TABLE, Blocks.ACACIA_LOG, Blocks.ACACIA_PLANKS),
            vanilla("dark_oak", ModBlocks.DARK_OAK_FEAST_TABLE, Blocks.DARK_OAK_LOG, Blocks.DARK_OAK_PLANKS),
            vanilla("mangrove", ModBlocks.MANGROVE_FEAST_TABLE, Blocks.MANGROVE_LOG, Blocks.MANGROVE_PLANKS),
            vanilla("cherry", ModBlocks.CHERRY_FEAST_TABLE, Blocks.CHERRY_LOG, Blocks.CHERRY_PLANKS),
            vanilla("bamboo", ModBlocks.BAMBOO_FEAST_TABLE, Blocks.BAMBOO_BLOCK, Blocks.BAMBOO_PLANKS),
            vanilla("crimson", ModBlocks.CRIMSON_FEAST_TABLE, Blocks.CRIMSON_STEM, Blocks.CRIMSON_PLANKS),
            vanilla("warped", ModBlocks.WARPED_FEAST_TABLE, Blocks.WARPED_STEM, Blocks.WARPED_PLANKS),
            new FeastTableData(
                    "maple",
                    ModBlocks.MAPLE_FEAST_TABLE,
                    ResourceLocation.fromNamespaceAndPath("yeastnfeast", "block/maple_log"),
                    ResourceLocation.fromNamespaceAndPath("yeastnfeast", "block/maple_planks"),
                    ModBlocks.MAPLE_LOG,
                    ModBlocks.MAPLE_PLANKS
            )
    );

    private static FeastTableData vanilla(
            String wood,
            RegistrySupplier<Block> table,
            Block log,
            Block planks
    ) {
        return new FeastTableData(
                wood,
                table,
                ResourceLocation.withDefaultNamespace("block/" + woodTextureName(wood, "log")),
                ResourceLocation.withDefaultNamespace("block/" + woodTextureName(wood, "planks")),
                () -> log,
                () -> planks
        );
    }

    private static String woodTextureName(String wood, String material) {
        if (material.equals("log")) {
            return switch (wood) {
                case "bamboo" -> "bamboo_block";
                case "crimson", "warped" -> wood + "_stem";
                default -> wood + "_log";
            };
        }
        return wood + "_planks";
    }

    String modelPrefix() {
        return wood + "_feast_table";
    }
}
