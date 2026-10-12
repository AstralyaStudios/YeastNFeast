package net.astralya.yeastnfeast.block;

import net.astralya.yeastnfeast.item.ModItems;
import net.astralya.yeastnfeast.worldgen.tree.ModSaplingGenerators;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.block.custom.*;
import net.astralya.yeastnfeast.block.custom.signs.ModHangingSignBlock;
import net.astralya.yeastnfeast.block.custom.signs.ModStandingSignBlock;
import net.astralya.yeastnfeast.block.custom.signs.ModWallHangingSignBlock;
import net.astralya.yeastnfeast.block.custom.signs.ModWallSignBlock;
import net.astralya.yeastnfeast.util.ModWoodTypes;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    // Crop Blocks
    public static final Block BARLEY_CROP = registerBlockWithoutBlockItem("barley_crop",
            new BarleyCropBlock(AbstractBlock.Settings.copy(Blocks.POTATOES)));
    public static final Block RYE_CROP = registerBlockWithoutBlockItem("rye_crop",
            new RyeCropBlock(AbstractBlock.Settings.copy(Blocks.POTATOES)));
    public static final Block GINGER_CROP = registerBlockWithoutBlockItem("ginger_crop",
            new GingerCropBlock(AbstractBlock.Settings.copy(Blocks.POTATOES)));
    public static final Block GARLIC_CROP = registerBlockWithoutBlockItem("garlic_crop",
            new GarlicCropBlock(AbstractBlock.Settings.copy(Blocks.POTATOES)));
    public static final Block MINT_CROP = registerBlockWithoutBlockItem("mint_crop",
            new MintCropBlock(AbstractBlock.Settings.copy(Blocks.POTATOES)));
    public static final Block ROSE_HIPS_BUSH = registerBlockWithoutBlockItem("rose_hips_bush",
            new RoseHipsBushBlock(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH)));
    public static final Block ELDERBERRY_BUSH = registerBlockWithoutBlockItem("elderberry_bush",
            new ElderberryBushBlock(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH)));

    // Functional Blocks
    public static final Block TREE_TAP = registerBlock("tree_tap",
            new TreeTapBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));
    public static final Block KEG = registerBlockWithoutBlockItem("keg",
            new KegBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));
    public static final Block MAPLE_SYRUP_CAULDRON = registerBlockWithoutBlockItem("maple_syrup_cauldron",
            new MapleSyrupCauldronBlock(AbstractBlock.Settings.copy(Blocks.CAULDRON).nonOpaque()));
    public static final Block CHEESE_PRESS = registerBlockWithoutBlockItem("cheese_press",
            new CheesePressBlock(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    // Food Blocks
    public static final Block CHEESE_WHEEL = registerBlockWithoutBlockItem("cheese_wheel",
            new LCheeseWheelBlock(AbstractBlock.Settings.copy(Blocks.CAKE).nonOpaque(), () -> ModItems.CHEESE_SLICE));
    public static final Block FRESHWHEEL = registerBlockWithoutBlockItem("freshwheel",
            new MCheeseWheelBlock(AbstractBlock.Settings.copy(Blocks.CAKE).nonOpaque(), () -> ModItems.FRESHWHEEL_SLICE));
    public static final Block DUSKWHEEL = registerBlockWithoutBlockItem("duskwheel",
            new LCheeseWheelBlock(AbstractBlock.Settings.copy(Blocks.CAKE).nonOpaque(), () -> ModItems.DUSKWHEEL_SLICE));
    public static final Block SHARPWHEEL = registerBlockWithoutBlockItem("sharpwheel",
            new MCheeseWheelBlock(AbstractBlock.Settings.copy(Blocks.CAKE).nonOpaque(), () -> ModItems.SHARPWHEEL_SLICE));

    // Storage Blocks
    public static final Block BAG_OF_ELDERBERRIES = registerBlock("bag_of_elderberries",
            new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL)));
    public static final Block BAG_OF_GARLIC = registerBlock("bag_of_garlic",
            new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL)));
    public static final Block BAG_OF_GINGER = registerBlock("bag_of_ginger",
            new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL)));
    public static final Block BAG_OF_HAWTHORN_BERRIES = registerBlock("bag_of_hawthorn_berries",
            new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL)));
    public static final Block BAG_OF_LEMON = registerBlock("bag_of_lemon",
            new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL)));
    public static final Block BAG_OF_MINT = registerBlock("bag_of_mint",
            new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL)));
    public static final Block BAG_OF_ROSE_HIPS = registerBlock("bag_of_rose_hips",
            new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL)));
    public static final Block BAG_OF_THISTLE = registerBlock("bag_of_thistle",
            new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL)));
    public static final Block BARLEY_BLOCK = registerBlock("barley_block",
            new HayBlock(AbstractBlock.Settings.copy(Blocks.HAY_BLOCK)));
    public static final Block RYE_BLOCK = registerBlock("rye_block",
            new HayBlock(AbstractBlock.Settings.copy(Blocks.HAY_BLOCK)));

    // Wild Crops
    public static final Block WILD_BARLEY = registerBlock("wild_barley",
            new FlowerBlock(StatusEffects.LUCK, 6, AbstractBlock.Settings.copy(Blocks.POPPY)));
    public static final Block WILD_RYE = registerBlock("wild_rye",
            new FlowerBlock(StatusEffects.LUCK, 6, AbstractBlock.Settings.copy(Blocks.POPPY)));
    public static final Block WILD_GINGER = registerBlock("wild_ginger",
            new FlowerBlock(StatusEffects.LUCK, 6, AbstractBlock.Settings.copy(Blocks.POPPY)));
    public static final Block WILD_GARLIC = registerBlock("wild_garlic",
            new FlowerBlock(StatusEffects.LUCK, 6, AbstractBlock.Settings.copy(Blocks.POPPY)));
    public static final Block THISTLE = registerBlock("thistle",
            new RenewablePlantBlock(StatusEffects.LUCK, 6, AbstractBlock.Settings.copy(Blocks.POPPY)));
    public static final Block POTTED_THISTLE = registerBlockWithoutBlockItem("potted_thistle",
            new FlowerPotBlock(THISTLE, AbstractBlock.Settings.copy(Blocks.POTTED_ALLIUM)));

    // Flowering Trees
    public static final Block FLOWERING_LEMON_TREE_LEAVES = registerBlock("flowering_lemon_tree_leaves",
            new LemonLeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).strength(0.2f)));
    public static final Block LEMON_TREE_LEAVES = registerBlock("lemon_tree_leaves",
            new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).strength(0.2f)));
    public static final Block LEMON_SAPLING = registerBlock("lemon_sapling",
            new SaplingBlock(ModSaplingGenerators.LEMON, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING).strength(0.2f)));
    public static final Block POTTED_LEMON_SAPLING = registerBlockWithoutBlockItem("potted_lemon_sapling",
            new FlowerPotBlock(LEMON_SAPLING, AbstractBlock.Settings.copy(Blocks.POTTED_ALLIUM)));
    public static final Block FLOWERING_HAWTHORN_TREE_LEAVES = registerBlock("flowering_hawthorn_tree_leaves",
            new HawthornLeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).strength(0.2f)));
    public static final Block HAWTHORN_TREE_LEAVES = registerBlock("hawthorn_tree_leaves",
            new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).strength(0.2f)));
    public static final Block HAWTHORN_SAPLING = registerBlock("hawthorn_sapling",
            new SaplingBlock(ModSaplingGenerators.HAWTHORN, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING).strength(0.2f)));
    public static final Block POTTED_HAWTHORN_SAPLING = registerBlockWithoutBlockItem("potted_hawthorn_sapling",
            new FlowerPotBlock(HAWTHORN_SAPLING, AbstractBlock.Settings.copy(Blocks.POTTED_ALLIUM)));

    // Wooden-Related Blocks
    public static final Block MAPLE_LOG = registerBlock("maple_log",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)));
    public static final Block MAPLE_WOOD = registerBlock("maple_wood",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)));
    public static final Block STRIPPED_MAPLE_LOG = registerBlock("stripped_maple_log",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)));
    public static final Block STRIPPED_MAPLE_WOOD = registerBlock("stripped_maple_wood",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)));
    public static final Block MAPLE_PLANKS = registerBlock("maple_planks",
            new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)));
    public static final Block MAPLE_LEAVES = registerBlock("maple_leaves",
            new MapleLeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES).strength(0.2f)));
    public static final Block MAPLE_STAIRS = registerBlock("maple_stairs",
            new StairsBlock(ModBlocks.MAPLE_PLANKS.getDefaultState(),
                    FabricBlockSettings.copyOf(Blocks.OAK_STAIRS)));
    public static final Block MAPLE_SLAB = registerBlock("maple_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.OAK_SLAB)));
    public static final Block MAPLE_BUTTON = registerBlock("maple_button",
            new ButtonBlock(FabricBlockSettings.copyOf(Blocks.OAK_BUTTON), BlockSetType.OAK, 10, true));
    public static final Block MAPLE_PRESSURE_PLATE = registerBlock("maple_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,
                    FabricBlockSettings.copyOf(Blocks.OAK_PRESSURE_PLATE), BlockSetType.OAK));
    public static final Block MAPLE_FENCE = registerBlock("maple_fence",
            new FenceBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE)));
    public static final Block MAPLE_FENCE_GATE = registerBlock("maple_fence_gate",
            new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE_GATE), WoodType.OAK));
    public static final Block MAPLE_SIGN = registerBlockWithoutBlockItem("maple_sign",
            new ModStandingSignBlock(FabricBlockSettings.copyOf(Blocks.OAK_SIGN).nonOpaque(), ModWoodTypes.MAPLE));
    public static final Block MAPLE_WALL_SIGN = registerBlockWithoutBlockItem("maple_wall_sign",
            new ModWallSignBlock(FabricBlockSettings.copyOf(Blocks.OAK_WALL_SIGN), ModWoodTypes.MAPLE));
    public static final Block MAPLE_HANGING_SIGN = registerBlockWithoutBlockItem("maple_hanging_sign",
            new ModHangingSignBlock(FabricBlockSettings.copyOf(Blocks.OAK_HANGING_SIGN), ModWoodTypes.MAPLE));
    public static final Block MAPLE_HANGING_WALL_SIGN = registerBlockWithoutBlockItem("maple_hanging_wall_sign",
            new ModWallHangingSignBlock(FabricBlockSettings.copyOf(Blocks.OAK_WALL_HANGING_SIGN), ModWoodTypes.MAPLE));
    public static final Block MAPLE_SAPLING = registerBlock("maple_sapling",
            new SaplingBlock(ModSaplingGenerators.MAPLE, FabricBlockSettings.copyOf(Blocks.OAK_SAPLING)));
    public static final Block POTTED_MAPLE_SAPLING = registerBlock("potted_maple_sapling",
            new FlowerPotBlock(LEMON_SAPLING, FabricBlockSettings.copyOf(Blocks.POTTED_OAK_SAPLING)));
    public static final Block MAPLE_TRAPDOOR = registerBlock("maple_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.copyOf(Blocks.OAK_TRAPDOOR).nonOpaque(), BlockSetType.OAK));
    public static final Block MAPLE_DOOR = registerBlock("maple_door",
            new DoorBlock(FabricBlockSettings.copyOf(Blocks.OAK_DOOR).nonOpaque(), BlockSetType.OAK));

    private static Block registerBlockWithoutBlockItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(YeastNFeastMod.MODID, name), block);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(YeastNFeastMod.MODID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, new Identifier(YeastNFeastMod.MODID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        YeastNFeastMod.LOGGER.info("Registering Blocks for " + YeastNFeastMod.MODID);
    }
}
