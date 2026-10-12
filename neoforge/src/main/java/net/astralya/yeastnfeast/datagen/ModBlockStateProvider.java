package net.astralya.yeastnfeast.datagen;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.block.custom.BarleyCropBlock;
import net.astralya.yeastnfeast.block.custom.FeastTableBlock;
import net.astralya.yeastnfeast.block.custom.GarlicCropBlock;
import net.astralya.yeastnfeast.block.custom.GingerCropBlock;
import net.astralya.yeastnfeast.block.custom.RyeCropBlock;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import dev.architectury.registry.registries.RegistrySupplier;

public class ModBlockStateProvider extends BlockStateProvider {

    private String blockName(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }

    public ResourceLocation resourceBlock(String path) {
        return ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "block/" + path);
    }


    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, YeastNFeastMod.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        FeastTableData.VALUES.forEach(this::feastTable);

        blockWithItem(ModBlocks.MAPLE_PLANKS);
        blockItem(ModBlocks.MAPLE_LOG);
        blockItem(ModBlocks.MAPLE_WOOD);
        blockItem(ModBlocks.STRIPPED_MAPLE_LOG);
        blockItem(ModBlocks.STRIPPED_MAPLE_WOOD);

        simpleCrossBlock(ModBlocks.WILD_BARLEY.get());
        simpleCrossBlock(ModBlocks.WILD_RYE.get());
        simpleCrossBlock(ModBlocks.WILD_GINGER.get());
        simpleCrossBlock(ModBlocks.WILD_GARLIC.get());
        simplePottedCrossBlockWithItemTexture(ModBlocks.THISTLE.get(), ModBlocks.POTTED_THISTLE.get());

        leavesBlock(ModBlocks.LEMON_TREE_LEAVES);
        leavesBlock(ModBlocks.MAPLE_LEAVES);
        leavesBlock(ModBlocks.HAWTHORN_TREE_LEAVES);

        bagBlock(ModBlocks.BAG_OF_ELDERBERRIES.get(), "bag_elderberries");
        bagBlock(ModBlocks.BAG_OF_GARLIC.get(), "bag_garlic");
        bagBlock(ModBlocks.BAG_OF_GINGER.get(), "bag_ginger");
        bagBlock(ModBlocks.BAG_OF_HAWTHORN_BERRIES.get(), "bag_hawthorn_berries");
        bagBlock(ModBlocks.BAG_OF_LEMON.get(), "bag_lemon");
        bagBlock(ModBlocks.BAG_OF_MINT.get(), "bag_mint");
        bagBlock(ModBlocks.BAG_OF_ROSE_HIPS.get(), "bag_rose_hips");
        bagBlock(ModBlocks.BAG_OF_THISTLE.get(), "bag_thistle");

        grainBlockWithAxis(ModBlocks.BARLEY_BLOCK.get(), "barley_block_side", "grain_top");
        grainBlockWithAxis(ModBlocks.RYE_BLOCK.get(), "rye_block_side", "grain_top");

        flowerWithPotBlock(ModBlocks.LEMON_SAPLING.get(), ModBlocks.POTTED_LEMON_SAPLING.get());
        flowerWithPotBlock(ModBlocks.HAWTHORN_SAPLING.get(), ModBlocks.POTTED_HAWTHORN_SAPLING.get());
        flowerWithPotBlock(ModBlocks.MAPLE_SAPLING.get(), ModBlocks.POTTED_MAPLE_SAPLING.get());

        stageBlock(ModBlocks.BARLEY_CROP.get(), BarleyCropBlock.AGE);
        stageBlock(ModBlocks.RYE_CROP.get(), RyeCropBlock.AGE);
        stageBlock(ModBlocks.GINGER_CROP.get(), GingerCropBlock.AGE);
        stageBlock(ModBlocks.GARLIC_CROP.get(), GarlicCropBlock.AGE);

        logBlock(((RotatedPillarBlock) ModBlocks.MAPLE_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.MAPLE_WOOD.get()), blockTexture(ModBlocks.MAPLE_LOG.get()), blockTexture(ModBlocks.MAPLE_LOG.get()));
        logBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_MAPLE_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_MAPLE_WOOD.get()), blockTexture(ModBlocks.STRIPPED_MAPLE_LOG.get()), blockTexture(ModBlocks.STRIPPED_MAPLE_LOG.get()));

        stairsBlock(((StairBlock) ModBlocks.MAPLE_STAIRS.get()), blockTexture(ModBlocks.MAPLE_PLANKS.get()));
        slabBlock(((SlabBlock) ModBlocks.MAPLE_SLAB.get()), blockTexture(ModBlocks.MAPLE_PLANKS.get()), blockTexture(ModBlocks.MAPLE_PLANKS.get()));

        buttonBlock(((ButtonBlock) ModBlocks.MAPLE_BUTTON.get()), blockTexture(ModBlocks.MAPLE_PLANKS.get()));
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.MAPLE_PRESSURE_PLATE.get()), blockTexture(ModBlocks.MAPLE_PLANKS.get()));
        fenceBlock((FenceBlock) ModBlocks.MAPLE_FENCE.get(), blockTexture(ModBlocks.MAPLE_PLANKS.get()));
        fenceGateBlock((FenceGateBlock) ModBlocks.MAPLE_FENCE_GATE.get(), blockTexture(ModBlocks.MAPLE_PLANKS.get()));
        doorBlockWithRenderType(((DoorBlock) ModBlocks.MAPLE_DOOR.get()), modLoc("block/maple_door_bottom"), modLoc("block/maple_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.MAPLE_TRAPDOOR.get()), modLoc("block/maple_trapdoor"), true, "cutout");

    }

    private void feastTable(FeastTableData data) {
        String[] geometries = {
                "single",
                "east_end",
                "west_end",
                "north_end",
                "south_end",
                "east_west_middle",
                "north_south_middle"
        };

        for (String geometry : geometries) {
            models().withExistingParent(data.modelPrefix() + "_" + geometry, resourceBlock("feast_table_" + geometry))
                    .texture("log", data.logTexture())
                    .texture("planks", data.planksTexture())
                    .texture("particle", data.logTexture());
        }

        simpleBlockItem(data.table().get(), models().getBuilder(data.modelPrefix() + "_single"));

        getVariantBuilder(data.table().get()).forAllStates(state -> {
            FeastTableBlock.Connection connection = state.getValue(FeastTableBlock.CONNECTION);
            Direction facing = state.getValue(FeastTableBlock.FACING);
            String geometry = feastTableGeometry(connection, facing);
            return ConfiguredModel.builder()
                    .modelFile(models().getExistingFile(resourceBlock(data.modelPrefix() + "_" + geometry)))
                    .build();
        });
    }

    private String feastTableGeometry(FeastTableBlock.Connection connection, Direction facing) {
        return switch (connection) {
            case SINGLE -> "single";
            case MIDDLE -> facing.getAxis() == Direction.Axis.Z
                    ? "east_west_middle"
                    : "north_south_middle";
            case END_LEFT -> switch (facing) {
                case NORTH -> "west_end";
                case EAST -> "north_end";
                case SOUTH -> "east_end";
                case WEST -> "south_end";
                default -> throw new IllegalStateException("Unexpected vertical Feast Table facing: " + facing);
            };
            case END_RIGHT -> switch (facing) {
                case NORTH -> "east_end";
                case EAST -> "south_end";
                case SOUTH -> "west_end";
                case WEST -> "north_end";
                default -> throw new IllegalStateException("Unexpected vertical Feast Table facing: " + facing);
            };
        };
    }

    public void flowerWithPotBlock(Block flower, Block flowerPot) {
        String flowerName = blockName(flower);
        String flowerPotName = blockName(flowerPot);

        ModelFile flowerModel = models().cross(flowerName, resourceBlock(flowerName)).renderType("cutout");

        this.simpleBlock(flower, flowerModel);

        itemModels().getBuilder(flowerName)
                .parent(models().getExistingFile(mcLoc("item/generated")))
                .texture("layer0", resourceBlock(flowerName));

        this.simpleBlock(flowerPot,
                models().withExistingParent(flowerPotName, mcLoc("block/flower_pot_cross"))
                        .texture("plant", resourceBlock(flowerName))
                        .renderType("cutout"));
    }

    public void simpleCrossBlock(Block block) {
        String name = blockName(block);

        ModelFile model = models().cross(name, resourceBlock(name)).renderType("cutout");

        this.simpleBlock(block, model);

        itemModels().getBuilder(name)
                .parent(models().getExistingFile(mcLoc("item/generated")))
                .texture("layer0", resourceBlock(name));
    }

    public void simpleCrossBlockWithItemTexture(Block block) {
        String name = blockName(block);

        ModelFile model = models().cross(name, resourceBlock(name)).renderType("cutout");
        simpleBlock(block, model);

        itemModels().getBuilder(name)
                .parent(models().getExistingFile(mcLoc("item/generated")))
                .texture("layer0", resourceItem(name));
    }

    public void simplePottedCrossBlockWithItemTexture(Block flower, Block flowerPot) {
        String flowerName = blockName(flower);
        String flowerPotName = blockName(flowerPot);

        ModelFile flowerModel = models().cross(flowerName, resourceBlock(flowerName)).renderType("cutout");

        this.simpleBlock(flower, flowerModel);

        itemModels().getBuilder(flowerName)
                .parent(models().getExistingFile(mcLoc("item/generated")))
                .texture("layer0", resourceItem(flowerName));

        this.simpleBlock(flowerPot,
                models().withExistingParent(flowerPotName, mcLoc("block/flower_pot_cross"))
                        .texture("plant", resourceBlock(flowerName))
                        .renderType("cutout"));
    }

    private void leavesBlock(RegistrySupplier<Block> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(),
                models().singleTexture(deferredBlock.getId().getPath(), ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(deferredBlock.get())).renderType("cutout"));
    }

    private void blockWithItem(RegistrySupplier<Block> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    public void stageBlock(Block block, IntegerProperty ageProperty, Property<?>... ignored) {
        getVariantBuilder(block)
                .forAllStatesExcept(state -> {
                    int ageSuffix = state.getValue(ageProperty);
                    String stageName = blockName(block) + "_stage" + ageSuffix;
                    return ConfiguredModel.builder()
                            .modelFile(models().cross(stageName, resourceBlock(stageName)).renderType("cutout")).build();
                }, ignored);
    }

    public void bagBlock(Block block, String textureTopName) {
        String name = blockName(block);

        ModelFile model = models().withExistingParent(name, mcLoc("block/cube"))
                .texture("down", resourceBlock("bag_bottom"))
                .texture("up", resourceBlock(textureTopName))
                .texture("north", resourceBlock("bag_side"))
                .texture("east", resourceBlock("bag_side"))
                .texture("south", resourceBlock("bag_side"))
                .texture("west", resourceBlock("bag_side"))
                .texture("particle", resourceBlock("bag_side"))
                .renderType("solid");

        simpleBlock(block, model);

        itemModels().getBuilder(name)
                .parent(model);
    }

    public void grainBlockWithAxis(Block block, String sideTexture, String endTexture) {
        String name = blockName(block);

        ModelFile vertical = models().cubeColumn(name, resourceBlock(sideTexture), resourceBlock(endTexture));

        ModelFile horizontal = models().cubeColumn(name + "_horizontal", resourceBlock(sideTexture), resourceBlock(endTexture));

        getVariantBuilder(block)
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y)
                .modelForState().modelFile(vertical).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.X)
                .modelForState().modelFile(horizontal).rotationX(90).rotationY(90).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z)
                .modelForState().modelFile(horizontal).rotationX(90).addModel();

        itemModels().getBuilder(name)
                .parent(vertical);
    }


    private void blockItem(RegistrySupplier<Block> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("yeastnfeast:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(RegistrySupplier<Block> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("yeastnfeast:block/" + deferredBlock.getId().getPath() + appendix));
    }

    private ResourceLocation resourceItem(String path) {
        return modLoc("item/" + path);
    }
}
