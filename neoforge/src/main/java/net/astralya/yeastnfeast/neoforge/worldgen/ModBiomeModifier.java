package net.astralya.yeastnfeast.neoforge.worldgen;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.worldgen.ModPlacedFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Optional;

public class ModBiomeModifier {

    public static final ResourceKey<BiomeModifier> ADD_WILD_BARLEY = registerKey("add_wild_barley");
    public static final ResourceKey<BiomeModifier> ADD_WILD_RYE = registerKey("add_wild_rye");
    public static final ResourceKey<BiomeModifier> ADD_WILD_GARLIC = registerKey("add_wild_garlic");
    public static final ResourceKey<BiomeModifier> ADD_WILD_GINGER = registerKey("add_wild_ginger");
    public static final ResourceKey<BiomeModifier> ADD_THISTLE = registerKey("add_thistle");

    public static final ResourceKey<BiomeModifier> ADD_ELDERBERRIES_BUSH = registerKey("add_elderberries_bush");
    public static final ResourceKey<BiomeModifier> ADD_ROSE_HIPS = registerKey("add_rose_hips");

    public static final ResourceKey<BiomeModifier> ADD_MAPLE_TREE = registerKey("add_maple_tree");
    public static final ResourceKey<BiomeModifier> ADD_LEMON_TREE = registerKey("add_lemon_tree");
    public static final ResourceKey<BiomeModifier> ADD_HAWTHORN_TREE = registerKey("add_hawthorn_tree");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);
        Optional<Holder.Reference<Biome>> darkForestBiome = biomes.get(Biomes.DARK_FOREST);

        // Crops
        context.register(ADD_WILD_BARLEY, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_PLAINS),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.WILD_BARLEY_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(ADD_WILD_RYE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_BIRCH_FOREST),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.WILD_RYE_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(ADD_WILD_GARLIC, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_PLAINS),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.WILD_GARLIC_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(ADD_WILD_GINGER, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_TAIGA),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.WILD_GINGER_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(ADD_THISTLE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_FOREST),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.THISTLE_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        // Bushes
        context.register(ADD_ELDERBERRIES_BUSH, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(darkForestBiome.get()),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ELDERBERRIES_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(ADD_ROSE_HIPS, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_JUNGLE),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ROSE_HIPS_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        // Trees
        context.register(ADD_LEMON_TREE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_PLAINS),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.LEMON_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(ADD_MAPLE_TREE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_FOREST),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.MAPLE_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(ADD_HAWTHORN_TREE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_FOREST),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.HAWTHORN_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, name));
    }
}
