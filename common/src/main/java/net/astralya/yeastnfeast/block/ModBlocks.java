package net.astralya.yeastnfeast.block;

import net.minecraft.core.registries.Registries;
import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.block.custom.*;
import net.astralya.yeastnfeast.block.custom.wood.*;
import net.astralya.yeastnfeast.item.ModItems;
import net.astralya.yeastnfeast.util.ModWoodTypes;
import net.astralya.yeastnfeast.worldgen.tree.ModTreeGrower;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.architectury.registry.registries.DeferredRegister;

import java.util.function.Supplier;
import java.util.List;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(YeastNFeastMod.MODID, Registries.BLOCK);
    
    // Crop Blocks
    public static final RegistrySupplier<Block> BARLEY_CROP = BLOCKS.register("barley_crop",
            () -> new BarleyCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POTATOES)));
    public static final RegistrySupplier<Block> RYE_CROP = BLOCKS.register("rye_crop",
            () -> new RyeCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POTATOES)));
    public static final RegistrySupplier<Block> GINGER_CROP = BLOCKS.register("ginger_crop",
            () -> new GingerCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POTATOES)));
    public static final RegistrySupplier<Block> GARLIC_CROP = BLOCKS.register("garlic_crop",
            () -> new GarlicCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POTATOES)));
    public static final RegistrySupplier<Block> MINT_CROP = BLOCKS.register("mint_crop",
            () -> new MintCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POTATOES)));
    public static final RegistrySupplier<Block> ROSE_HIPS_BUSH = BLOCKS.register("rose_hips_bush",
            () -> new RoseHipsBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH).randomTicks()));
    public static final RegistrySupplier<Block> ELDERBERRY_BUSH = BLOCKS.register("elderberry_bush",
            () -> new ElderberryBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH).randomTicks()));

    // Functional Blocks
    public static final RegistrySupplier<Block> TREE_TAP = registerBlock("tree_tap",
            () -> new TreeTapBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noOcclusion()));
    public static final RegistrySupplier<Block> KEG = BLOCKS.register("keg",
            () -> new KegBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noOcclusion()));
    public static final RegistrySupplier<Block> MAPLE_SYRUP_CAULDRON = BLOCKS.register("maple_syrup_cauldron",
            () -> new MapleSyrupCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noOcclusion()));
    public static final RegistrySupplier<Block> CHEESE_PRESS = BLOCKS.register("cheese_press",
            () -> new CheesePressBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noOcclusion()));
    public static final RegistrySupplier<Block> OAK_FEAST_TABLE = registerFeastTable("oak_feast_table", Blocks.OAK_PLANKS);
    public static final RegistrySupplier<Block> SPRUCE_FEAST_TABLE = registerFeastTable("spruce_feast_table", Blocks.SPRUCE_PLANKS);
    public static final RegistrySupplier<Block> BIRCH_FEAST_TABLE = registerFeastTable("birch_feast_table", Blocks.BIRCH_PLANKS);
    public static final RegistrySupplier<Block> JUNGLE_FEAST_TABLE = registerFeastTable("jungle_feast_table", Blocks.JUNGLE_PLANKS);
    public static final RegistrySupplier<Block> FEAST_TABLE = registerFeastTable("feast_table", Blocks.ACACIA_PLANKS);
    public static final RegistrySupplier<Block> DARK_OAK_FEAST_TABLE = registerFeastTable("dark_oak_feast_table", Blocks.DARK_OAK_PLANKS);
    public static final RegistrySupplier<Block> MANGROVE_FEAST_TABLE = registerFeastTable("mangrove_feast_table", Blocks.MANGROVE_PLANKS);
    public static final RegistrySupplier<Block> CHERRY_FEAST_TABLE = registerFeastTable("cherry_feast_table", Blocks.CHERRY_PLANKS);
    public static final RegistrySupplier<Block> BAMBOO_FEAST_TABLE = registerFeastTable("bamboo_feast_table", Blocks.BAMBOO_PLANKS);
    public static final RegistrySupplier<Block> CRIMSON_FEAST_TABLE = registerFeastTable("crimson_feast_table", Blocks.CRIMSON_PLANKS);
    public static final RegistrySupplier<Block> WARPED_FEAST_TABLE = registerFeastTable("warped_feast_table", Blocks.WARPED_PLANKS);
    public static final RegistrySupplier<Block> MAPLE_FEAST_TABLE = registerFeastTable("maple_feast_table", Blocks.OAK_PLANKS);

    public static final List<RegistrySupplier<Block>> FEAST_TABLES = List.of(
            OAK_FEAST_TABLE,
            SPRUCE_FEAST_TABLE,
            BIRCH_FEAST_TABLE,
            JUNGLE_FEAST_TABLE,
            FEAST_TABLE,
            DARK_OAK_FEAST_TABLE,
            MANGROVE_FEAST_TABLE,
            CHERRY_FEAST_TABLE,
            BAMBOO_FEAST_TABLE,
            CRIMSON_FEAST_TABLE,
            WARPED_FEAST_TABLE,
            MAPLE_FEAST_TABLE
    );

    // Food Blocks
    public static final RegistrySupplier<Block> CHEESE_WHEEL = BLOCKS.register("cheese_wheel",
            () -> new LCheeseWheelBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE).noOcclusion(), ModItems.CHEESE_SLICE));
    public static final RegistrySupplier<Block> FRESHWHEEL = BLOCKS.register("freshwheel",
            () -> new MCheeseWheelBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE).noOcclusion(), ModItems.FRESHWHEEL_SLICE));
    public static final RegistrySupplier<Block> DUSKWHEEL = BLOCKS.register("duskwheel",
            () -> new LCheeseWheelBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE).noOcclusion(), ModItems.DUSKWHEEL_SLICE));
    public static final RegistrySupplier<Block> SHARPWHEEL = BLOCKS.register("sharpwheel",
            () -> new MCheeseWheelBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE).noOcclusion(), ModItems.SHARPWHEEL_SLICE));

    // Storage Blocks
    public static final RegistrySupplier<Block> BAG_OF_ELDERBERRIES = registerBlock("bag_of_elderberries",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final RegistrySupplier<Block> BAG_OF_GARLIC = registerBlock("bag_of_garlic",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final RegistrySupplier<Block> BAG_OF_GINGER = registerBlock("bag_of_ginger",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final RegistrySupplier<Block> BAG_OF_HAWTHORN_BERRIES = registerBlock("bag_of_hawthorn_berries",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final RegistrySupplier<Block> BAG_OF_LEMON = registerBlock("bag_of_lemon",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final RegistrySupplier<Block> BAG_OF_MINT = registerBlock("bag_of_mint",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final RegistrySupplier<Block> BAG_OF_ROSE_HIPS = registerBlock("bag_of_rose_hips",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final RegistrySupplier<Block> BAG_OF_THISTLE = registerBlock("bag_of_thistle",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final RegistrySupplier<Block> BARLEY_BLOCK = registerBlock("barley_block",
            () -> new HayBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.HAY_BLOCK)));
    public static final RegistrySupplier<Block> RYE_BLOCK = registerBlock("rye_block",
            () -> new HayBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.HAY_BLOCK)));

    // Wild Crop Blocks
    public static final RegistrySupplier<Block> WILD_BARLEY = registerBlock("wild_barley",
            () -> new FlowerBlock(MobEffects.LUCK, 6, BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY)));
    public static final RegistrySupplier<Block> WILD_RYE = registerBlock("wild_rye",
            () -> new FlowerBlock(MobEffects.LUCK, 6, BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY)));
    public static final RegistrySupplier<Block> WILD_GINGER = registerBlock("wild_ginger",
            () -> new FlowerBlock(MobEffects.LUCK, 6, BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY)));
    public static final RegistrySupplier<Block> WILD_GARLIC = registerBlock("wild_garlic",
            () -> new FlowerBlock(MobEffects.LUCK, 6, BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY)));
    public static final RegistrySupplier<Block> THISTLE = registerBlock("thistle",
            () -> new RenewablePlantBlock(MobEffects.LUCK, 6, BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY)));
    public static final RegistrySupplier<Block> POTTED_THISTLE = BLOCKS.register("potted_thistle",
            () -> new FlowerPotBlock(THISTLE.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM)));

    // Flowering Tree Blocks
    public static final RegistrySupplier<Block> FLOWERING_LEMON_TREE_LEAVES  = registerBlock("flowering_lemon_tree_leaves",
            () -> new LemonLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).strength(0.2f)));
    public static final RegistrySupplier<Block> LEMON_TREE_LEAVES = registerBlock("lemon_tree_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).strength(0.2f)));
    public static final RegistrySupplier<Block> LEMON_SAPLING = registerBlock("lemon_sapling",
            () -> new SaplingBlock(ModTreeGrower.LEMON, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING).strength(0.2f)));
    public static final RegistrySupplier<Block> POTTED_LEMON_SAPLING = BLOCKS.register("potted_lemon_sapling",
            () -> new FlowerPotBlock(LEMON_SAPLING.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM)));
    public static final RegistrySupplier<Block> FLOWERING_HAWTHORN_TREE_LEAVES  = registerBlock("flowering_hawthorn_tree_leaves",
            () -> new HawthornLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).strength(0.2f)));
    public static final RegistrySupplier<Block> HAWTHORN_TREE_LEAVES = registerBlock("hawthorn_tree_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).strength(0.2f)));
    public static final RegistrySupplier<Block> HAWTHORN_SAPLING = registerBlock("hawthorn_sapling",
            () -> new SaplingBlock(ModTreeGrower.HAWTHORN, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING).strength(0.2f)));
    public static final RegistrySupplier<Block> POTTED_HAWTHORN_SAPLING = BLOCKS.register("potted_hawthorn_sapling",
            () -> new FlowerPotBlock(HAWTHORN_SAPLING.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM)));

    // Tree-Related Blocks
    public static final RegistrySupplier<Block> MAPLE_LEAVES = registerBlock("maple_leaves",
            () -> new MapleLeavesBlock(BlockBehaviour.Properties.of().noOcclusion()));
    public static final RegistrySupplier<Block> MAPLE_SAPLING = registerBlock("maple_sapling",
            () -> new SaplingBlock(ModTreeGrower.MAPLE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING).strength(0.2f)));
    public static final RegistrySupplier<Block> POTTED_MAPLE_SAPLING = BLOCKS.register("potted_maple_sapling",
            () -> new FlowerPotBlock(MAPLE_SAPLING.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_ALLIUM)));
    public static final RegistrySupplier<Block> MAPLE_LOG = registerBlock("maple_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final RegistrySupplier<Block> STRIPPED_MAPLE_LOG = registerBlock("stripped_maple_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final RegistrySupplier<Block> MAPLE_WOOD = registerBlock("maple_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final RegistrySupplier<Block> STRIPPED_MAPLE_WOOD = registerBlock("stripped_maple_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final RegistrySupplier<Block> MAPLE_PLANKS = registerBlock("maple_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final RegistrySupplier<Block> MAPLE_STAIRS = registerBlock("maple_stairs",
            () -> new StairBlock(ModBlocks.MAPLE_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final RegistrySupplier<Block> MAPLE_SLAB = registerBlock("maple_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final RegistrySupplier<Block> MAPLE_BUTTON = registerBlock("maple_button",
            () -> new ButtonBlock(BlockSetType.OAK, 10, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)));
    public static final RegistrySupplier<Block> MAPLE_PRESSURE_PLATE = registerBlock("maple_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final RegistrySupplier<Block> MAPLE_FENCE = registerBlock("maple_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final RegistrySupplier<Block> MAPLE_FENCE_GATE = registerBlock("maple_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));
    public static final RegistrySupplier<Block> MAPLE_TRAPDOOR = registerBlock("maple_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));
    public static final RegistrySupplier<Block> MAPLE_DOOR = registerBlock("maple_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR).noOcclusion()));
    public static final RegistrySupplier<Block> MAPLE_SIGN = BLOCKS.register("maple_sign",
            () -> new ModStandingSignBlock(ModWoodTypes.MAPLE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final RegistrySupplier<Block> MAPLE_WALL_SIGN  = BLOCKS.register("maple_wall_sign",
            () -> new ModWallSignBlock(ModWoodTypes.MAPLE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final RegistrySupplier<Block> MAPLE_HANGING_SIGN  = BLOCKS.register("maple_hanging_sign",
            () -> new ModHangingSignBlock(ModWoodTypes.MAPLE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final RegistrySupplier<Block> MAPLE_HANGING_WALL_SIGN = BLOCKS.register("maple_hanging_wall_sign",
            () -> new ModWallHangingSignBlock(ModWoodTypes.MAPLE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));

    private static <T extends Block> RegistrySupplier<T> registerBlock(String name, Supplier<T> block) {
        RegistrySupplier<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static RegistrySupplier<Block> registerFeastTable(String name, Block planks) {
        return registerBlock(name,
                () -> new FeastTableBlock(BlockBehaviour.Properties.ofFullCopy(planks).noOcclusion()));
    }

    private static <T extends Block> void registerBlockItem(String name, RegistrySupplier<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void init() {
        BLOCKS.register();
    }
}
