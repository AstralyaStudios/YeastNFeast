package net.astralya.yeastnfeast.datagen.loot;

import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.block.custom.*;
import net.astralya.yeastnfeast.item.ModItems;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {

    public ModBlockLootTableProvider(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    protected void generate() {
        generatePlantsAndFlowers();
        generateFunctionalBlocks();
        generateCrops();
        generateTreeBlocks();
    }

    private void generatePlantsAndFlowers() {
        // Potted plants
        this.add(ModBlocks.POTTED_MAPLE_SAPLING.get(), createPotFlowerItemTable(ModBlocks.MAPLE_SAPLING.get()));
        this.add(ModBlocks.POTTED_LEMON_SAPLING.get(), createPotFlowerItemTable(ModBlocks.LEMON_SAPLING.get()));
        this.add(ModBlocks.POTTED_HAWTHORN_SAPLING.get(), createPotFlowerItemTable(ModBlocks.HAWTHORN_SAPLING.get()));
        this.add(ModBlocks.POTTED_HAWTHORN_SAPLING.get(), createPotFlowerItemTable(ModBlocks.HAWTHORN_SAPLING.get()));
        this.add(ModBlocks.POTTED_THISTLE.get(), createPotFlowerItemTable(ModBlocks.THISTLE.get()));
    }

    private void generateFunctionalBlocks() {
        // Simple drops
        ModBlocks.FEAST_TABLES.forEach(table -> this.dropSelf(table.get()));
        this.dropSelf(ModBlocks.KEG.get());
        this.dropSelf(ModBlocks.TREE_TAP.get());
        this.dropSelf(ModBlocks.CHEESE_PRESS.get());
        this.dropSelf(ModBlocks.BAG_OF_ELDERBERRIES.get());
        this.dropSelf(ModBlocks.BAG_OF_GARLIC.get());
        this.dropSelf(ModBlocks.BAG_OF_GINGER.get());
        this.dropSelf(ModBlocks.BAG_OF_HAWTHORN_BERRIES.get());
        this.dropSelf(ModBlocks.BAG_OF_LEMON.get());
        this.dropSelf(ModBlocks.BAG_OF_MINT.get());
        this.dropSelf(ModBlocks.BAG_OF_ROSE_HIPS.get());
        this.dropSelf(ModBlocks.BAG_OF_THISTLE.get());
        this.dropSelf(ModBlocks.BARLEY_BLOCK.get());
        this.dropSelf(ModBlocks.RYE_BLOCK.get());
        this.dropSelf(ModBlocks.THISTLE.get());
        this.add(ModBlocks.MAPLE_SYRUP_CAULDRON.get(), this.createSingleItemTable(Blocks.CAULDRON));
        this.add(ModBlocks.CHEESE_WHEEL.get(), cheeseWheelSlicesByCuts(ModBlocks.CHEESE_WHEEL.get(), ModItems.CHEESE_SLICE.get(), BaseCheeseWheelBlock.CUTS));
        this.add(ModBlocks.FRESHWHEEL.get(), cheeseWheelSlicesByCuts(ModBlocks.FRESHWHEEL.get(), ModItems.FRESHWHEEL_SLICE.get(), BaseCheeseWheelBlock.CUTS));
        this.add(ModBlocks.DUSKWHEEL.get(), cheeseWheelSlicesByCuts(ModBlocks.DUSKWHEEL.get(), ModItems.DUSKWHEEL_SLICE.get(), BaseCheeseWheelBlock.CUTS));
        this.add(ModBlocks.SHARPWHEEL.get(), cheeseWheelSlicesByCuts(ModBlocks.SHARPWHEEL.get(), ModItems.SHARPWHEEL_SLICE.get(), BaseCheeseWheelBlock.CUTS));
    }

    private void generateCrops() {
        // Wild Crops
        this.add(ModBlocks.WILD_RYE.get(), this.createSingleItemTable(ModItems.RYE_SEEDS.get()));
        this.add(ModBlocks.WILD_BARLEY.get(), this.createSingleItemTable(ModItems.BARLEY_SEEDS.get()));
        this.add(ModBlocks.WILD_GINGER.get(), this.createSingleItemTable(ModItems.GINGER.get()));
        this.add(ModBlocks.WILD_GARLIC.get(), this.createSingleItemTable(ModItems.GARLIC.get()));

        // Main Crops
        LootItemCondition.Builder builder0 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.GINGER_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GingerCropBlock.AGE, 3));
        this.add(ModBlocks.GINGER_CROP.get(), this.createSimpleCropBlock(ModBlocks.GINGER_CROP.get(),
                ModItems.GINGER.get(), builder0));

        LootItemCondition.Builder builder1 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.GARLIC_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GarlicCropBlock.AGE, 3));
        this.add(ModBlocks.GARLIC_CROP.get(), this.createSimpleCropBlock(ModBlocks.GARLIC_CROP.get(),
                ModItems.GARLIC.get(), builder1));

        LootItemCondition.Builder builder2 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.RYE_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(RyeCropBlock.AGE, 3));
        this.add(ModBlocks.RYE_CROP.get(), this.createCropDrops(ModBlocks.RYE_CROP.get(),
                ModItems.RYE.get(), ModItems.RYE_SEEDS.get(), builder2));

        LootItemCondition.Builder builder3 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.BARLEY_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BarleyCropBlock.AGE, 3));
        this.add(ModBlocks.BARLEY_CROP.get(), this.createCropDrops(ModBlocks.BARLEY_CROP.get(),
                ModItems.BARLEY.get(), ModItems.BARLEY_SEEDS.get(), builder3));

        LootItemCondition.Builder builder4 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.MINT_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(MintCropBlock.AGE, 3));
        this.add(ModBlocks.MINT_CROP.get(), this.createCropDrops(ModBlocks.MINT_CROP.get(),
                ModItems.MINT.get(), ModItems.MINT_SEEDS.get(), builder4));

        // Special Crops
        this.add(ModBlocks.ROSE_HIPS_BUSH.get(), createHarvestablePlantBlock(
                ModBlocks.ROSE_HIPS_BUSH.get(), ModItems.ROSE_HIPS.get(),
                RoseHipsBushBlock.AGE, 3, 2.0f, 3.0f));

        this.add(ModBlocks.ELDERBERRY_BUSH.get(), createHarvestablePlantBlock(
                ModBlocks.ELDERBERRY_BUSH.get(), ModItems.ELDERBERRIES.get(),
                ElderberryBushBlock.AGE, 3, 2.0f, 3.0f));

        this.add(ModBlocks.FLOWERING_HAWTHORN_TREE_LEAVES.get(),
                floweringLeavesWithFruit(
                        ModBlocks.FLOWERING_HAWTHORN_TREE_LEAVES.get(),
                        ModBlocks.HAWTHORN_SAPLING.get().asItem(),
                        ModItems.HAWTHORN_BERRIES.get(),
                        HawthornLeavesBlock.AGE));

        this.add(ModBlocks.FLOWERING_LEMON_TREE_LEAVES.get(),
                floweringLeavesWithFruit(
                        ModBlocks.FLOWERING_LEMON_TREE_LEAVES.get(),
                        ModBlocks.LEMON_SAPLING.get().asItem(),
                        ModItems.LEMON.get(),
                        LemonLeavesBlock.AGE));

        this.dropSelf(ModBlocks.LEMON_SAPLING.get());
        this.add(ModBlocks.POTTED_LEMON_SAPLING.get(),
                createPotFlowerItemTable(ModBlocks.LEMON_SAPLING.get()));
        this.add(ModBlocks.LEMON_TREE_LEAVES.get(), createLeavesDrops(ModBlocks.LEMON_TREE_LEAVES.get(),
                ModBlocks.LEMON_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.dropSelf(ModBlocks.HAWTHORN_SAPLING.get());
        this.add(ModBlocks.POTTED_HAWTHORN_SAPLING.get(),
                createPotFlowerItemTable(ModBlocks.POTTED_HAWTHORN_SAPLING.get()));
        this.add(ModBlocks.HAWTHORN_TREE_LEAVES.get(), createLeavesDrops(ModBlocks.HAWTHORN_TREE_LEAVES.get(),
                ModBlocks.HAWTHORN_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

    }

    private void generateTreeBlocks() {
        this.add(ModBlocks.MAPLE_LEAVES.get(), createLeavesDrops(ModBlocks.MAPLE_LEAVES.get(), ModBlocks.MAPLE_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.dropSelf(ModBlocks.MAPLE_LOG.get());
        this.dropSelf(ModBlocks.MAPLE_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_MAPLE_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_MAPLE_WOOD.get());
        this.dropSelf(ModBlocks.MAPLE_PLANKS.get());
        this.dropSelf(ModBlocks.MAPLE_SAPLING.get());
        this.add(ModBlocks.POTTED_MAPLE_SAPLING.get(), createPotFlowerItemTable(ModBlocks.MAPLE_SAPLING.get()));
        this.dropSelf(ModBlocks.MAPLE_STAIRS.get());
        this.dropSelf(ModBlocks.MAPLE_PRESSURE_PLATE.get());
        this.add(ModBlocks.MAPLE_SLAB.get(), createSlabItemTable(ModBlocks.MAPLE_SLAB.get()));
        this.dropSelf(ModBlocks.MAPLE_BUTTON.get());
        this.dropSelf(ModBlocks.MAPLE_FENCE.get());
        this.dropSelf(ModBlocks.MAPLE_FENCE_GATE.get());
        this.dropSelf(ModBlocks.MAPLE_TRAPDOOR.get());
        this.add(ModBlocks.MAPLE_DOOR.get(), createDoorTable(ModBlocks.MAPLE_DOOR.get()));
        this.add(ModBlocks.MAPLE_SIGN.get(), createSingleItemTable(ModBlocks.MAPLE_SIGN.get()));
        this.add(ModBlocks.MAPLE_WALL_SIGN.get(), createSingleItemTable(ModBlocks.MAPLE_SIGN.get()));
        this.add(ModBlocks.MAPLE_HANGING_SIGN.get(), createSingleItemTable(ModBlocks.MAPLE_HANGING_SIGN.get()));
        this.add(ModBlocks.MAPLE_HANGING_WALL_SIGN.get(), createSingleItemTable(ModBlocks.MAPLE_HANGING_SIGN.get()));
    }

    protected LootTable.Builder createSimpleCropBlock(Block cropBlock, Item grownCropItem, LootItemCondition.Builder pDropGrownCropCondition) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.applyExplosionDecay(cropBlock, LootTable.lootTable().withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(grownCropItem).when(pDropGrownCropCondition)
                        .apply(ApplyBonusCount.addBonusBinomialDistributionCount(registrylookup.getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3)))));
    }

    protected LootTable.Builder createHarvestablePlantBlock(Block bushBlock, Item harvestedItem, IntegerProperty ageProperty, int maxAge,
                                                            float minDropAtMaxAge, float maxDropAtMaxAge) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        LootItemCondition.Builder maxAgeCondition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(bushBlock)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(ageProperty, maxAge));

        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(harvestedItem))
                )
                .withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(harvestedItem)
                                .when(maxAgeCondition)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDropAtMaxAge, maxDropAtMaxAge)))
                                .apply(ApplyBonusCount.addBonusBinomialDistributionCount(
                                        registrylookup.getOrThrow(Enchantments.FORTUNE), 1, 2))
                        )
                );
    }

    protected LootTable.Builder floweringLeavesWithFruit(Block leaves, Item sapling, Item fruit, IntegerProperty ageProp) {
        var enchLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        net.minecraft.core.Holder<net.minecraft.world.item.enchantment.Enchantment> FORTUNE = enchLookup.getOrThrow(Enchantments.FORTUNE);
        net.minecraft.core.Holder<net.minecraft.world.item.enchantment.Enchantment> SILK_TOUCH = enchLookup.getOrThrow(Enchantments.SILK_TOUCH);

        net.minecraft.advancements.critereon.EnchantmentPredicate silkOne =
                new net.minecraft.advancements.critereon.EnchantmentPredicate(SILK_TOUCH, net.minecraft.advancements.critereon.MinMaxBounds.Ints.atLeast(1));


        net.minecraft.world.level.storage.loot.predicates.LootItemCondition.Builder toolHasSilkTouch =
                net.minecraft.world.level.storage.loot.predicates.MatchTool.toolMatches(
                        net.minecraft.advancements.critereon.ItemPredicate.Builder.item()
                                .withSubPredicate(
                                        net.minecraft.advancements.critereon.ItemSubPredicates.ENCHANTMENTS,
                                        net.minecraft.advancements.critereon.ItemEnchantmentsPredicate.enchantments(java.util.List.of(silkOne))
                                )
                );

        net.minecraft.world.level.storage.loot.predicates.LootItemCondition.Builder noSilkTouch =
                net.minecraft.world.level.storage.loot.predicates.InvertedLootItemCondition.invert(toolHasSilkTouch);

        net.minecraft.world.level.storage.loot.predicates.LootItemCondition.Builder fullyGrown =
                net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition
                        .hasBlockStateProperties(leaves)
                        .setProperties(net.minecraft.advancements.critereon.StatePropertiesPredicate.Builder.properties()
                                .hasProperty(ageProp, 2));

        net.minecraft.world.level.storage.loot.LootPool.Builder saplingPool =
                net.minecraft.world.level.storage.loot.LootPool.lootPool()
                        .setRolls(net.minecraft.world.level.storage.loot.providers.number.ConstantValue.exactly(1))
                        .add(net.minecraft.world.level.storage.loot.entries.LootItem.lootTableItem(sapling)
                                .when(net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition
                                        .bonusLevelFlatChance(FORTUNE, 0.05F, 0.0625F, 0.083333336F, 0.1F)));

        net.minecraft.world.level.storage.loot.LootPool.Builder sticksPool =
                net.minecraft.world.level.storage.loot.LootPool.lootPool()
                        .setRolls(net.minecraft.world.level.storage.loot.providers.number.ConstantValue.exactly(1))
                        .when(noSilkTouch)
                        .add(net.minecraft.world.level.storage.loot.entries.LootItem.lootTableItem(net.minecraft.world.item.Items.STICK)
                                .apply(net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
                                        .setCount(net.minecraft.world.level.storage.loot.providers.number.UniformGenerator.between(1.0F, 2.0F)))
                                .when(net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition
                                        .bonusLevelFlatChance(FORTUNE, 0.05F, 0.0625F, 0.083333336F, 0.1F)));

        net.minecraft.world.level.storage.loot.LootPool.Builder fruitPool =
                net.minecraft.world.level.storage.loot.LootPool.lootPool()
                        .setRolls(net.minecraft.world.level.storage.loot.providers.number.ConstantValue.exactly(1))
                        .when(noSilkTouch)
                        .when(fullyGrown)
                        .add(net.minecraft.world.level.storage.loot.entries.LootItem.lootTableItem(fruit));

        return this.applyExplosionDecay(leaves,
                net.minecraft.world.level.storage.loot.LootTable.lootTable()
                        .withPool(saplingPool)
                        .withPool(sticksPool)
                        .withPool(fruitPool));
    }

    protected LootTable.Builder cheeseWheelSlicesByCuts(Block wheelBlock, Item sliceItem, IntegerProperty cutsProp) {
        LootItemBlockStatePropertyCondition.Builder cuts0 =
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(wheelBlock)
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(cutsProp, 0));

        LootItemBlockStatePropertyCondition.Builder cuts1 =
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(wheelBlock)
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(cutsProp, 1));

        LootItemBlockStatePropertyCondition.Builder cuts2 =
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(wheelBlock)
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(cutsProp, 2));

        LootItemBlockStatePropertyCondition.Builder cuts3 =
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(wheelBlock)
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(cutsProp, 3));

        LootPool.Builder pool = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))
                .add(AlternativesEntry.alternatives(
                        LootItem.lootTableItem(wheelBlock).when(cuts0),
                        LootItem.lootTableItem(sliceItem)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(3)))
                                .when(cuts1),
                        LootItem.lootTableItem(sliceItem)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2)))
                                .when(cuts2),
                        LootItem.lootTableItem(sliceItem)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                                .when(cuts3)
                ));

        return applyExplosionDecay(wheelBlock, LootTable.lootTable().withPool(pool));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return java.util.stream.StreamSupport.stream(ModBlocks.BLOCKS.spliterator(), false).map(Holder::value)::iterator;
    }
}
