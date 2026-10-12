package net.astralya.yeastnfeast.worldgen;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> WILD_BARLEY_PLACED = registerKey("wild_barley_placed");
    public static final ResourceKey<PlacedFeature> WILD_RYE_PLACED = registerKey("wild_rye_placed");
    public static final ResourceKey<PlacedFeature> WILD_GINGER_PLACED = registerKey("wild_ginger_placed");
    public static final ResourceKey<PlacedFeature> WILD_GARLIC_PLACED = registerKey("wild_garlic_placed");
    public static final ResourceKey<PlacedFeature> THISTLE_PLACED = registerKey("thistle_placed");
    public static final ResourceKey<PlacedFeature> ELDERBERRIES_PLACED = registerKey("elderberries_placed");
    public static final ResourceKey<PlacedFeature> ROSE_HIPS_PLACED = registerKey("rose_hips_placed");
    public static final ResourceKey<PlacedFeature> MAPLE_PLACED = registerKey("maple_placed");
    public static final ResourceKey<PlacedFeature> LEMON_PLACED = registerKey("lemon_placed");
    public static final ResourceKey<PlacedFeature> HAWTHORN_PLACED = registerKey("hawthorn_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configured = context.lookup(Registries.CONFIGURED_FEATURE);

        // Crops
        register(context, WILD_BARLEY_PLACED, configured.getOrThrow(ModConfiguredFeatures.WILD_BARLEY), rarityPatch(10));
        register(context, WILD_RYE_PLACED, configured.getOrThrow(ModConfiguredFeatures.WILD_RYE), rarityPatch(10));
        register(context, WILD_GINGER_PLACED, configured.getOrThrow(ModConfiguredFeatures.WILD_GINGER), rarityPatch(10));
        register(context, WILD_GARLIC_PLACED, configured.getOrThrow(ModConfiguredFeatures.WILD_GARLIC), rarityPatch(10));
        register(context, THISTLE_PLACED, configured.getOrThrow(ModConfiguredFeatures.THISTLE), rarityPatch(10));

        // Bushes
        register(context, ELDERBERRIES_PLACED, configured.getOrThrow(ModConfiguredFeatures.ELDERBERRIES), rarityPatch(10));
        register(context, ROSE_HIPS_PLACED, configured.getOrThrow(ModConfiguredFeatures.ROSE_HIPS), rarityPatch(10));

        // Trees
        register(context, MAPLE_PLACED,
                configured.getOrThrow(ModConfiguredFeatures.MAPLE_TREE),
                rareTreePlacement(ModBlocks.MAPLE_SAPLING.get(), 25));

        register(context, LEMON_PLACED,
                configured.getOrThrow(ModConfiguredFeatures.LEMON_TREE),
                rareTreePlacement(ModBlocks.LEMON_SAPLING.get(), 25));

        register(context, HAWTHORN_PLACED,
                configured.getOrThrow(ModConfiguredFeatures.HAWTHORN_TREE),
                rareTreePlacement(ModBlocks.HAWTHORN_SAPLING.get(), 25));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static List<PlacementModifier> rarityPatch(int rarity) {
        return List.of(
                RarityFilter.onAverageOnceEvery(rarity),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                BiomeFilter.biome()
        );
    }

    private static List<PlacementModifier> rareTreePlacement(net.minecraft.world.level.block.Block sapling, int rarity) {
        return List.of(
                RarityFilter.onAverageOnceEvery(rarity),
                InSquarePlacement.spread(),
                SurfaceWaterDepthFilter.forMaxDepth(0),
                PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BiomeFilter.biome(),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(sapling.defaultBlockState(), BlockPos.ZERO))
        );
    }
}