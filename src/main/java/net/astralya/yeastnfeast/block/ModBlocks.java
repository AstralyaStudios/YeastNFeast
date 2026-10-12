package net.astralya.yeastnfeast.block;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.block.custom.*;
import net.astralya.yeastnfeast.block.custom.wood.*;
import net.astralya.yeastnfeast.item.ModItems;
import net.astralya.yeastnfeast.util.ModWoodTypes;
import net.astralya.yeastnfeast.worldgen.tree.HawthornTreeGrower;
import net.astralya.yeastnfeast.worldgen.tree.LemonTreeGrower;
import net.astralya.yeastnfeast.worldgen.tree.MapleTreeGrower;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, YeastNFeastMod.MODID);

    // Crop Blocks
    public static final RegistryObject<Block> BARLEY_CROP = BLOCKS.register("barley_crop",
            () -> new BarleyCropBlock(BlockBehaviour.Properties.copy(Blocks.POTATOES)));
    public static final RegistryObject<Block> RYE_CROP = BLOCKS.register("rye_crop",
            () -> new RyeCropBlock(BlockBehaviour.Properties.copy(Blocks.POTATOES)));
    public static final RegistryObject<Block> GINGER_CROP = BLOCKS.register("ginger_crop",
            () -> new GingerCropBlock(BlockBehaviour.Properties.copy(Blocks.POTATOES)));
    public static final RegistryObject<Block> GARLIC_CROP = BLOCKS.register("garlic_crop",
            () -> new GarlicCropBlock(BlockBehaviour.Properties.copy(Blocks.POTATOES)));
    public static final RegistryObject<Block> MINT_CROP = BLOCKS.register("mint_crop",
            () -> new MintCropBlock(BlockBehaviour.Properties.copy(Blocks.POTATOES)));
    public static final RegistryObject<Block> ROSE_HIPS_BUSH = registerBlock("rose_hips_bush",
            () -> new RoseHipsBushBlock(BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH)));
    public static final RegistryObject<Block> ELDERBERRY_BUSH = registerBlock("elderberry_bush",
            () -> new ElderberryBushBlock(BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH)));

    // Functional Blocks
    public static final RegistryObject<Block> TREE_TAP = registerBlock("tree_tap",
            () -> new TreeTapBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).noOcclusion()));
    public static final RegistryObject<Block> KEG = BLOCKS.register("keg",
            () -> new KegBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).noOcclusion()));
    public static final RegistryObject<Block> MAPLE_SYRUP_CAULDRON = registerBlock("maple_syrup_cauldron",
            () -> new MapleSyrupCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON).noOcclusion()));
    public static final RegistryObject<Block> CHEESE_PRESS = BLOCKS.register("cheese_press",
            () -> new CheesePressBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).noOcclusion()));

    // Food Blocks
    public static final RegistryObject<Block> CHEESE_WHEEL = BLOCKS.register("cheese_wheel",
            () -> new LCheeseWheelBlock(BlockBehaviour.Properties.copy(Blocks.CAKE), ModItems.CHEESE_SLICE));
    public static final RegistryObject<Block> FRESHWHEEL = BLOCKS.register("freshwheel",
            () -> new MCheeseWheelBlock(BlockBehaviour.Properties.copy(Blocks.CAKE), ModItems.FRESHWHEEL_SLICE));
    public static final RegistryObject<Block> DUSKWHEEL = BLOCKS.register("duskwheel",
            () -> new LCheeseWheelBlock(BlockBehaviour.Properties.copy(Blocks.CAKE), ModItems.DUSKWHEEL_SLICE));
    public static final RegistryObject<Block> SHARPWHEEL = BLOCKS.register("sharpwheel",
            () -> new MCheeseWheelBlock(BlockBehaviour.Properties.copy(Blocks.CAKE), ModItems.SHARPWHEEL_SLICE));

    // Storage Blocks
    public static final RegistryObject<Block> BAG_OF_ELDERBERRIES = registerBlock("bag_of_elderberries",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> BAG_OF_GARLIC = registerBlock("bag_of_garlic",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> BAG_OF_GINGER = registerBlock("bag_of_ginger",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> BAG_OF_HAWTHORN_BERRIES = registerBlock("bag_of_hawthorn_berries",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> BAG_OF_LEMON = registerBlock("bag_of_lemon",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> BAG_OF_MINT = registerBlock("bag_of_mint",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> BAG_OF_ROSE_HIPS = registerBlock("bag_of_rose_hips",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> BAG_OF_THISTLE = registerBlock("bag_of_thistle",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> BARLEY_BLOCK = registerBlock("barley_block",
            () -> new HayBlock(BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK)));
    public static final RegistryObject<Block> RYE_BLOCK = registerBlock("rye_block",
            () -> new HayBlock(BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK)));

    // Wild Crop Blocks
    public static final RegistryObject<Block> WILD_BARLEY = registerBlock("wild_barley",
            () -> new FlowerBlock(() -> MobEffects.LUCK, 6, BlockBehaviour.Properties.copy(Blocks.ALLIUM)));
    public static final RegistryObject<Block> WILD_RYE = registerBlock("wild_rye",
            () -> new FlowerBlock(() -> MobEffects.LUCK, 6, BlockBehaviour.Properties.copy(Blocks.ALLIUM)));
    public static final RegistryObject<Block> WILD_GINGER = registerBlock("wild_ginger",
            () -> new FlowerBlock(() -> MobEffects.LUCK, 6, BlockBehaviour.Properties.copy(Blocks.ALLIUM)));
    public static final RegistryObject<Block> WILD_GARLIC = registerBlock("wild_garlic",
            () -> new FlowerBlock(() -> MobEffects.LUCK, 6, BlockBehaviour.Properties.copy(Blocks.ALLIUM)));
    public static final RegistryObject<Block> THISTLE = registerBlock("thistle",
            () -> new RenewablePlantBlock(() -> MobEffects.LUCK, 6, BlockBehaviour.Properties.copy(Blocks.ALLIUM)));
    public static final RegistryObject<Block> POTTED_THISTLE = BLOCKS.register("potted_thistle",
            () -> new FlowerPotBlock((() -> (FlowerPotBlock) Blocks.FLOWER_POT), THISTLE, BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM)));

    // Flowering Tree Blocks
    public static final RegistryObject<Block> FLOWERING_LEMON_TREE_LEAVES  = registerBlock("flowering_lemon_tree_leaves",
            () -> new LemonLeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).strength(0.2f)));
    public static final RegistryObject<Block> LEMON_TREE_LEAVES = registerBlock("lemon_tree_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).strength(0.2f)));
    public static final RegistryObject<Block> LEMON_SAPLING = registerBlock("lemon_sapling",
            () -> new SaplingBlock(new LemonTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING).strength(0.2f)));
    public static final RegistryObject<Block> POTTED_LEMON_SAPLING = BLOCKS.register("potted_lemon_sapling",
            () -> new FlowerPotBlock((() -> (FlowerPotBlock) Blocks.FLOWER_POT), LEMON_SAPLING, BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM)));
    public static final RegistryObject<Block> FLOWERING_HAWTHORN_TREE_LEAVES  = registerBlock("flowering_hawthorn_tree_leaves",
            () -> new HawthornLeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).strength(0.2f)));
    public static final RegistryObject<Block> HAWTHORN_TREE_LEAVES = registerBlock("hawthorn_tree_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).strength(0.2f)));
    public static final RegistryObject<Block> HAWTHORN_SAPLING = registerBlock("hawthorn_sapling",
            () -> new SaplingBlock(new HawthornTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING).strength(0.2f)));
    public static final RegistryObject<Block> POTTED_HAWTHORN_SAPLING = BLOCKS.register("potted_hawthorn_sapling",
            () -> new FlowerPotBlock((() -> (FlowerPotBlock) Blocks.FLOWER_POT), HAWTHORN_SAPLING, BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM)));

    // Wood-related Blocks
    public static final RegistryObject<Block> MAPLE_LEAVES = registerBlock("maple_leaves",
            () -> new MapleLeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).strength(0.2f)));
    public static final RegistryObject<Block> MAPLE_SAPLING = registerBlock("maple_sapling",
            () -> new SaplingBlock(new MapleTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING).strength(0.2f)));
    public static final RegistryObject<Block> POTTED_MAPLE_SAPLING = BLOCKS.register("potted_maple_sapling",
            () -> new FlowerPotBlock((() -> (FlowerPotBlock) Blocks.FLOWER_POT), MAPLE_SAPLING, BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM)));
    public static final RegistryObject<Block> MAPLE_LOG = registerBlock("maple_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> STRIPPED_MAPLE_LOG = registerBlock("stripped_maple_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final RegistryObject<Block> MAPLE_WOOD = registerBlock("maple_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
    public static final RegistryObject<Block> STRIPPED_MAPLE_WOOD = registerBlock("stripped_maple_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final RegistryObject<Block> MAPLE_PLANKS = registerBlock("maple_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> MAPLE_STAIRS = registerBlock("maple_stairs",
            () -> new StairBlock(() -> ModBlocks.MAPLE_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)));
    public static final RegistryObject<Block> MAPLE_SLAB = registerBlock("maple_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> MAPLE_BUTTON = registerBlock("maple_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS), BlockSetType.OAK, 10, true));
    public static final RegistryObject<Block> MAPLE_PRESSURE_PLATE = registerBlock("maple_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS), BlockSetType.OAK));
    public static final RegistryObject<Block> MAPLE_FENCE = registerBlock("maple_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)));
    public static final RegistryObject<Block> MAPLE_FENCE_GATE = registerBlock("maple_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE), WoodType.OAK));
    public static final RegistryObject<Block> MAPLE_TRAPDOOR = registerBlock("maple_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR).noOcclusion(), BlockSetType.OAK));
    public static final RegistryObject<Block> MAPLE_DOOR = registerBlock("maple_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR).noOcclusion(), BlockSetType.OAK));

    public static final RegistryObject<Block> MAPLE_SIGN = BLOCKS.register("maple_sign",
            () -> new ModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS), ModWoodTypes.MAPLE));
    public static final RegistryObject<Block> MAPLE_WALL_SIGN  = BLOCKS.register("maple_wall_sign",
            () -> new ModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS), ModWoodTypes.MAPLE));
    public static final RegistryObject<Block> MAPLE_HANGING_SIGN  = BLOCKS.register("maple_hanging_sign",
            () -> new ModHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS), ModWoodTypes.MAPLE));
    public static final RegistryObject<Block> MAPLE_HANGING_WALL_SIGN = BLOCKS.register("maple_hanging_wall_sign",
            () -> new ModWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS), ModWoodTypes.MAPLE));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
