package net.astralya.yeastnfeast.worldgen.tree;

import net.astralya.yeastnfeast.worldgen.ModConfiguredFeatures;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public final class ModSaplingGenerators {

    public static final SaplingGenerator HAWTHORN = new Simple(ModConfiguredFeatures.HAWTHORN_TREE_KEY);
    public static final SaplingGenerator LEMON = new Simple(ModConfiguredFeatures.LEMON_TREE_KEY);
    public static final SaplingGenerator MAPLE = new Simple(ModConfiguredFeatures.MAPLE_KEY);

    private ModSaplingGenerators() {
    }

    private static final class Simple extends SaplingGenerator {
        private final RegistryKey<ConfiguredFeature<?, ?>> treeFeature;

        private Simple(RegistryKey<ConfiguredFeature<?, ?>> treeFeature) {
            this.treeFeature = treeFeature;
        }

        @Override
        protected @Nullable RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
            return this.treeFeature;
        }
    }
}