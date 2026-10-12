package net.astralya.yeastnfeast.worldgen;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.BlockFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightmapPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SurfaceWaterDepthFilterPlacementModifier;

import java.util.List;

public class ModPlacedFeatures {

    public static final RegistryKey<PlacedFeature> WILD_BARLEY_PLACED   = registerKey("wild_barley_placed");
    public static final RegistryKey<PlacedFeature> WILD_RYE_PLACED      = registerKey("wild_rye_placed");
    public static final RegistryKey<PlacedFeature> WILD_GINGER_PLACED   = registerKey("wild_ginger_placed");
    public static final RegistryKey<PlacedFeature> WILD_GARLIC_PLACED   = registerKey("wild_garlic_placed");
    public static final RegistryKey<PlacedFeature> THISTLE              = registerKey("thistle_placed");
    public static final RegistryKey<PlacedFeature> ELDERBERRIES_PLACED  = registerKey("elderberries_placed");
    public static final RegistryKey<PlacedFeature> ROSE_HIPS_PLACED     = registerKey("rose_hips_placed");
    public static final RegistryKey<PlacedFeature> MAPLE_PLACED         = registerKey("maple_placed");
    public static final RegistryKey<PlacedFeature> LEMON_PLACED         = registerKey("lemon_placed");
    public static final RegistryKey<PlacedFeature> HAWTHORN_PLACED      = registerKey("hawthorn_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, WILD_BARLEY_PLACED, configuredLookup.getOrThrow(ModConfiguredFeatures.WILD_BARLEY_KEY), rarityPatch(10));
        register(context, WILD_RYE_PLACED, configuredLookup.getOrThrow(ModConfiguredFeatures.WILD_RYE_KEY), rarityPatch(10));
        register(context, WILD_GINGER_PLACED, configuredLookup.getOrThrow(ModConfiguredFeatures.WILD_GINGER_KEY), rarityPatch(10));
        register(context, WILD_GARLIC_PLACED, configuredLookup.getOrThrow(ModConfiguredFeatures.WILD_GARLIC_KEY), rarityPatch(10));
        register(context, THISTLE, configuredLookup.getOrThrow(ModConfiguredFeatures.THISTLE), rarityPatch(10));
        register(context, ELDERBERRIES_PLACED, configuredLookup.getOrThrow(ModConfiguredFeatures.ELDERBERRIES_KEY), rarityPatch(10));
        register(context, ROSE_HIPS_PLACED, configuredLookup.getOrThrow(ModConfiguredFeatures.ROSE_HIPS_KEY), rarityPatch(10));
        register(context, MAPLE_PLACED, configuredLookup.getOrThrow(ModConfiguredFeatures.MAPLE_KEY), rareTreePlacement(ModBlocks.MAPLE_SAPLING, 25));
        register(context, LEMON_PLACED, configuredLookup.getOrThrow(ModConfiguredFeatures.LEMON_TREE_KEY), rareTreePlacement(ModBlocks.LEMON_SAPLING, 25));
        register(context, HAWTHORN_PLACED, configuredLookup.getOrThrow(ModConfiguredFeatures.HAWTHORN_TREE_KEY), rareTreePlacement(ModBlocks.HAWTHORN_SAPLING, 25));
    }

    private static List<PlacementModifier> rarityPatch(int rarity) {
        return List.of(
                RarityFilterPlacementModifier.of(rarity),
                SquarePlacementModifier.of(),
                HeightmapPlacementModifier.of(Heightmap.Type.MOTION_BLOCKING),
                BiomePlacementModifier.of()
        );
    }

    private static List<PlacementModifier> rareTreePlacement(Block sapling, int rarity) {
        return List.of(
                RarityFilterPlacementModifier.of(rarity),
                SquarePlacementModifier.of(),
                SurfaceWaterDepthFilterPlacementModifier.of(0),
                HeightmapPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR),
                BiomePlacementModifier.of(),
                BlockFilterPlacementModifier.of(
                        BlockPredicate.wouldSurvive(sapling.getDefaultState(), BlockPos.ORIGIN)
                )
        );
    }

    private static void register(Registerable<PlacedFeature> context,
                                 RegistryKey<PlacedFeature> key,
                                 RegistryEntry<ConfiguredFeature<?, ?>> configuredFeature,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuredFeature, List.copyOf(modifiers)));
    }

    private static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(YeastNFeastMod.MODID, name));
    }
}