package net.astralya.yeastnfeast.datagen.loot;

import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.block.custom.BarleyCropBlock;
import net.astralya.yeastnfeast.block.custom.BaseCheeseWheelBlock;
import net.astralya.yeastnfeast.block.custom.ElderberryBushBlock;
import net.astralya.yeastnfeast.block.custom.GarlicCropBlock;
import net.astralya.yeastnfeast.block.custom.GingerCropBlock;
import net.astralya.yeastnfeast.block.custom.HawthornLeavesBlock;
import net.astralya.yeastnfeast.block.custom.LemonLeavesBlock;
import net.astralya.yeastnfeast.block.custom.MintCropBlock;
import net.astralya.yeastnfeast.block.custom.RoseHipsBushBlock;
import net.astralya.yeastnfeast.block.custom.RyeCropBlock;
import net.astralya.yeastnfeast.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
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
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {

    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        addPlantsAndFlowers();
        addFunctionalBlocks();
        addCrops();
        addTreeBlocks();
    }

    private void addPlantsAndFlowers() {
        this.add(ModBlocks.POTTED_MAPLE_SAPLING.get(), createPotFlowerItemTable(ModBlocks.MAPLE_SAPLING.get()));
        this.add(ModBlocks.POTTED_LEMON_SAPLING.get(), createPotFlowerItemTable(ModBlocks.LEMON_SAPLING.get()));
        this.add(ModBlocks.POTTED_HAWTHORN_SAPLING.get(), createPotFlowerItemTable(ModBlocks.HAWTHORN_SAPLING.get()));
        this.add(ModBlocks.POTTED_THISTLE.get(), createPotFlowerItemTable(ModBlocks.THISTLE.get()));
    }

    private void addFunctionalBlocks() {
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

        this.add(ModBlocks.MAPLE_SYRUP_CAULDRON.get(), createSingleItemTable(Blocks.CAULDRON));

        this.add(ModBlocks.CHEESE_WHEEL.get(), cheeseWheelSlicesByCuts(ModBlocks.CHEESE_WHEEL.get(), ModItems.CHEESE_SLICE.get(), BaseCheeseWheelBlock.CUTS));
        this.add(ModBlocks.FRESHWHEEL.get(), cheeseWheelSlicesByCuts(ModBlocks.FRESHWHEEL.get(), ModItems.FRESHWHEEL_SLICE.get(), BaseCheeseWheelBlock.CUTS));
        this.add(ModBlocks.DUSKWHEEL.get(), cheeseWheelSlicesByCuts(ModBlocks.DUSKWHEEL.get(), ModItems.DUSKWHEEL_SLICE.get(), BaseCheeseWheelBlock.CUTS));
        this.add(ModBlocks.SHARPWHEEL.get(), cheeseWheelSlicesByCuts(ModBlocks.SHARPWHEEL.get(), ModItems.SHARPWHEEL_SLICE.get(), BaseCheeseWheelBlock.CUTS));
    }

    private void addCrops() {
        this.add(ModBlocks.WILD_BARLEY.get(), block -> createSingleItemTable(ModItems.BARLEY_SEEDS.get()));
        this.add(ModBlocks.WILD_RYE.get(), block -> createSingleItemTable(ModItems.RYE_SEEDS.get()));
        this.add(ModBlocks.WILD_GINGER.get(), block -> createSingleItemTable(ModItems.GINGER.get()));
        this.add(ModBlocks.WILD_GARLIC.get(), block -> createSingleItemTable(ModItems.GARLIC.get()));

        LootItemCondition.Builder gingerMature = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.GINGER_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GingerCropBlock.AGE, 3));
        this.add(ModBlocks.GINGER_CROP.get(), createSimpleCropBlock(ModBlocks.GINGER_CROP.get(), ModItems.GINGER.get(), gingerMature));

        LootItemCondition.Builder garlicMature = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.GARLIC_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GarlicCropBlock.AGE, 3));
        this.add(ModBlocks.GARLIC_CROP.get(), createSimpleCropBlock(ModBlocks.GARLIC_CROP.get(), ModItems.GARLIC.get(), garlicMature));

        LootItemCondition.Builder ryeMature = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.RYE_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(RyeCropBlock.AGE, 3));
        this.add(ModBlocks.RYE_CROP.get(), createCropDrops(ModBlocks.RYE_CROP.get(), ModItems.RYE.get(), ModItems.RYE_SEEDS.get(), ryeMature));

        LootItemCondition.Builder barleyMature = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.BARLEY_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BarleyCropBlock.AGE, 3));
        this.add(ModBlocks.BARLEY_CROP.get(), createCropDrops(ModBlocks.BARLEY_CROP.get(), ModItems.BARLEY.get(), ModItems.BARLEY_SEEDS.get(), barleyMature));

        LootItemCondition.Builder mintMature = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.MINT_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(MintCropBlock.AGE, 3));
        this.add(ModBlocks.MINT_CROP.get(), createCropDrops(ModBlocks.MINT_CROP.get(), ModItems.MINT.get(), ModItems.MINT_SEEDS.get(), mintMature));

        this.add(ModBlocks.ROSE_HIPS_BUSH.get(), createHarvestablePlantBlock(
                ModBlocks.ROSE_HIPS_BUSH.get(), ModItems.ROSE_HIPS.get(), RoseHipsBushBlock.AGE, 3, 2.0f, 3.0f));

        this.add(ModBlocks.ELDERBERRY_BUSH.get(), createHarvestablePlantBlock(
                ModBlocks.ELDERBERRY_BUSH.get(), ModItems.ELDERBERRIES.get(), ElderberryBushBlock.AGE, 3, 2.0f, 3.0f));
    }

    private void addTreeBlocks() {
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

        this.dropSelf(ModBlocks.LEMON_SAPLING.get());
        this.add(ModBlocks.POTTED_LEMON_SAPLING.get(), createPotFlowerItemTable(ModBlocks.LEMON_SAPLING.get()));
        this.add(ModBlocks.LEMON_TREE_LEAVES.get(), createLeavesDrops(ModBlocks.LEMON_TREE_LEAVES.get(), ModBlocks.LEMON_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.add(ModBlocks.FLOWERING_LEMON_TREE_LEAVES.get(), floweringLeavesBlock(ModBlocks.FLOWERING_LEMON_TREE_LEAVES.get(), ModBlocks.LEMON_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.dropSelf(ModBlocks.HAWTHORN_SAPLING.get());
        this.add(ModBlocks.POTTED_HAWTHORN_SAPLING.get(), createPotFlowerItemTable(ModBlocks.HAWTHORN_SAPLING.get()));
        this.add(ModBlocks.HAWTHORN_TREE_LEAVES.get(), createLeavesDrops(ModBlocks.HAWTHORN_TREE_LEAVES.get(), ModBlocks.HAWTHORN_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.add(ModBlocks.FLOWERING_HAWTHORN_TREE_LEAVES.get(), floweringLeavesBlock(ModBlocks.FLOWERING_HAWTHORN_TREE_LEAVES.get(), ModBlocks.HAWTHORN_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
    }

    protected LootTable.Builder createSimpleCropBlock(Block cropBlock, Item grownCropItem, LootItemCondition.Builder dropGrownCropCondition) {
        return applyExplosionDecay(cropBlock, LootTable.lootTable().withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(grownCropItem).when(dropGrownCropCondition)
                        .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3)))));
    }

    protected LootTable.Builder createHarvestablePlantBlock(Block bushBlock, Item harvestedItem, IntegerProperty ageProperty, int maxAge,
                                                            float minDropAtMaxAge, float maxDropAtMaxAge) {
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
                                .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 1, 2))
                        ));
    }

    public LootTable.Builder floweringLeavesBlock(Block leaves, Block drop, float... chance) {
        LootTable.Builder lootTable = LootTable.lootTable()
                .withPool(
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(leaves))
                                .add(
                                        LootItem.lootTableItem(drop)
                                                .when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, chance))
                                                .when(ExplosionCondition.survivesExplosion())
                                )
                ).withPool(
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .when(HAS_NO_SILK_TOUCH)
                                .add(
                                        LootItem.lootTableItem(Items.STICK)
                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                                .when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, NORMAL_LEAVES_SAPLING_CHANCES))
                                                .when(ExplosionCondition.survivesExplosion())
                                )
                );

        if (leaves == ModBlocks.FLOWERING_LEMON_TREE_LEAVES.get()) {
            lootTable.withPool(
                    LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1.0F))
                            .when(HAS_NO_SILK_TOUCH)
                            .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(leaves)
                                    .setProperties(StatePropertiesPredicate.Builder.properties()
                                            .hasProperty(LemonLeavesBlock.AGE, 2)
                                    )
                            )
                            .add(LootItem.lootTableItem(ModItems.LEMON.get()).when(ExplosionCondition.survivesExplosion()))
            );
        } else if (leaves == ModBlocks.FLOWERING_HAWTHORN_TREE_LEAVES.get()) {
            lootTable.withPool(
                    LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1.0F))
                            .when(HAS_NO_SILK_TOUCH)
                            .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(leaves)
                                    .setProperties(StatePropertiesPredicate.Builder.properties()
                                            .hasProperty(HawthornLeavesBlock.AGE, 2)
                                    )
                            )
                            .add(LootItem.lootTableItem(ModItems.HAWTHORN_BERRIES.get()).when(ExplosionCondition.survivesExplosion()))
            );
        }

        return lootTable;
    }

    protected LootTable.Builder cheeseWheelSlicesByCuts(Block wheelBlock, Item sliceItem, IntegerProperty cutsProp) {
        LootItemBlockStatePropertyCondition.Builder cuts0 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(wheelBlock)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(cutsProp, 0));

        LootItemBlockStatePropertyCondition.Builder cuts1 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(wheelBlock)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(cutsProp, 1));

        LootItemBlockStatePropertyCondition.Builder cuts2 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(wheelBlock)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(cutsProp, 2));

        LootItemBlockStatePropertyCondition.Builder cuts3 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(wheelBlock)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(cutsProp, 3));

        LootPool.Builder pool = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))
                .add(AlternativesEntry.alternatives(
                        LootItem.lootTableItem(wheelBlock).when(cuts0),
                        LootItem.lootTableItem(sliceItem).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3))).when(cuts1),
                        LootItem.lootTableItem(sliceItem).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2))).when(cuts2),
                        LootItem.lootTableItem(sliceItem).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))).when(cuts3)
                ));

        return applyExplosionDecay(wheelBlock, LootTable.lootTable().withPool(pool));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}