package net.astralya.yeastnfeast.worldgen;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.block.custom.ElderberryBushBlock;
import net.astralya.yeastnfeast.block.custom.HawthornLeavesBlock;
import net.astralya.yeastnfeast.block.custom.LemonLeavesBlock;
import net.astralya.yeastnfeast.block.custom.RoseHipsBushBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.feature.SimpleBlockFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.CherryFoliagePlacer;
import net.minecraft.world.gen.foliage.LargeOakFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;

public class ModConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> WILD_BARLEY_KEY   = registerKey("wild_barley");
    public static final RegistryKey<ConfiguredFeature<?, ?>> WILD_RYE_KEY      = registerKey("wild_rye");
    public static final RegistryKey<ConfiguredFeature<?, ?>> WILD_GINGER_KEY   = registerKey("wild_ginger");
    public static final RegistryKey<ConfiguredFeature<?, ?>> WILD_GARLIC_KEY   = registerKey("wild_garlic");
    public static final RegistryKey<ConfiguredFeature<?, ?>> THISTLE           = registerKey("thistle");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ELDERBERRIES_KEY  = registerKey("elderberries");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ROSE_HIPS_KEY     = registerKey("rose_hips");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MAPLE_KEY         = registerKey("maple");
    public static final RegistryKey<ConfiguredFeature<?, ?>> LEMON_TREE_KEY    = registerKey("lemon_tree");
    public static final RegistryKey<ConfiguredFeature<?, ?>> HAWTHORN_TREE_KEY = registerKey("hawthorn_tree");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        register(context, WILD_BARLEY_KEY, Feature.FLOWER, patchConfig(ModBlocks.WILD_BARLEY, 2, 3, 1));
        register(context, WILD_RYE_KEY, Feature.FLOWER, patchConfig(ModBlocks.WILD_RYE, 2, 3, 1));
        register(context, WILD_GINGER_KEY, Feature.FLOWER, patchConfig(ModBlocks.WILD_GINGER, 2, 3, 1));
        register(context, WILD_GARLIC_KEY, Feature.FLOWER, patchConfig(ModBlocks.WILD_GARLIC, 3, 7, 3));
        register(context, THISTLE, Feature.FLOWER, patchConfig(ModBlocks.THISTLE, 3, 7, 3));

        register(context, ELDERBERRIES_KEY, Feature.RANDOM_PATCH,
                new RandomPatchFeatureConfig(
                        30, 8, 4,
                        simpleEntry(Feature.SIMPLE_BLOCK,
                                new SimpleBlockFeatureConfig(BlockStateProvider.of(
                                        ModBlocks.ELDERBERRY_BUSH.getDefaultState().with(ElderberryBushBlock.AGE, 3)
                                ))
                        )
                ));

        register(context, ROSE_HIPS_KEY, Feature.RANDOM_PATCH,
                new RandomPatchFeatureConfig(
                        30, 8, 4,
                        simpleEntry(Feature.SIMPLE_BLOCK,
                                new SimpleBlockFeatureConfig(BlockStateProvider.of(
                                        ModBlocks.ROSE_HIPS_BUSH.getDefaultState().with(RoseHipsBushBlock.AGE, 3)
                                ))
                        )
                ));

        register(context, MAPLE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.MAPLE_LOG),
                new StraightTrunkPlacer(4, 2, 0),
                BlockStateProvider.of(ModBlocks.MAPLE_LEAVES),
                new LargeOakFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 3) {},
                new TwoLayersFeatureSize(1, 0, 2)
        ).ignoreVines().build());

        register(context, LEMON_TREE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.OAK_LOG),
                new StraightTrunkPlacer(4, 2, 0),
                new WeightedBlockStateProvider(DataPool.<BlockState>builder()
                        .add(ModBlocks.LEMON_TREE_LEAVES.getDefaultState(), 8)
                        .add(ModBlocks.FLOWERING_LEMON_TREE_LEAVES.getDefaultState().with(LemonLeavesBlock.AGE, 2), 1)
                        .build()),
                new LargeOakFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 3),
                new TwoLayersFeatureSize(1, 0, 2)
        ).ignoreVines().build());

        register(context, HAWTHORN_TREE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.OAK_LOG),
                new StraightTrunkPlacer(4, 2, 0),
                new WeightedBlockStateProvider(DataPool.<BlockState>builder()
                        .add(ModBlocks.HAWTHORN_TREE_LEAVES.getDefaultState(), 8)
                        .add(ModBlocks.FLOWERING_HAWTHORN_TREE_LEAVES.getDefaultState().with(HawthornLeavesBlock.AGE, 2), 1)
                        .build()),
                new CherryFoliagePlacer(
                        ConstantIntProvider.create(4), ConstantIntProvider.create(0), ConstantIntProvider.create(5),
                        0.25F, 0.5F, 0.16666667F, 0.33333334F
                ),
                new TwoLayersFeatureSize(1, 0, 2)
        ).ignoreVines().build());
    }

    private static RandomPatchFeatureConfig patchConfig(Block block, int tries, int xzSpread, int ySpread) {
        return new RandomPatchFeatureConfig(
                tries, xzSpread, ySpread,
                simpleEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(block)))
        );
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>>
    RegistryEntry<PlacedFeature> simpleEntry(F feature, FC config) {
        return PlacedFeatures.createEntry(feature, config);
    }

    @SuppressWarnings("unused")
    private static SimpleBlockFeatureConfig simple(BlockState state) {
        return new SimpleBlockFeatureConfig(BlockStateProvider.of(state));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(YeastNFeastMod.MODID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(
            Registerable<ConfiguredFeature<?, ?>> context,
            RegistryKey<ConfiguredFeature<?, ?>> key,
            F feature,
            FC configuration
    ) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}