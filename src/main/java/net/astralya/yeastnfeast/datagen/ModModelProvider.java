package net.astralya.yeastnfeast.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.item.ModItems;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.family.BlockFamilies;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        // Generate Block Models
        blockStateModelGenerator.registerLog(ModBlocks.MAPLE_LOG).log(ModBlocks.MAPLE_LOG).wood(ModBlocks.MAPLE_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_MAPLE_LOG).log(ModBlocks.STRIPPED_MAPLE_LOG).wood(ModBlocks.STRIPPED_MAPLE_WOOD);
        BlockStateModelGenerator.BlockTexturePool mapleTexturePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.MAPLE_PLANKS);
        mapleTexturePool.stairs(ModBlocks.MAPLE_STAIRS);
        mapleTexturePool.slab(ModBlocks.MAPLE_SLAB);
        mapleTexturePool.button(ModBlocks.MAPLE_BUTTON);
        mapleTexturePool.pressurePlate(ModBlocks.MAPLE_PRESSURE_PLATE);
        mapleTexturePool.fence(ModBlocks.MAPLE_FENCE);
        mapleTexturePool.fenceGate(ModBlocks.MAPLE_FENCE_GATE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.MAPLE_LEAVES);

        mapleTexturePool.family(BlockFamilies.register(ModBlocks.MAPLE_PLANKS).sign(ModBlocks.MAPLE_SIGN, ModBlocks.MAPLE_WALL_SIGN).build());
        blockStateModelGenerator.registerHangingSign(ModBlocks.STRIPPED_MAPLE_LOG, ModBlocks.MAPLE_HANGING_SIGN, ModBlocks.MAPLE_HANGING_WALL_SIGN);
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.MAPLE_SAPLING, ModBlocks.POTTED_MAPLE_SAPLING,
                BlockStateModelGenerator.TintType.NOT_TINTED);

        blockStateModelGenerator.registerTrapdoor(ModBlocks.MAPLE_TRAPDOOR);
        blockStateModelGenerator.registerDoor(ModBlocks.MAPLE_DOOR);

        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.LEMON_SAPLING, ModBlocks.POTTED_LEMON_SAPLING,
                BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.HAWTHORN_SAPLING, ModBlocks.POTTED_HAWTHORN_SAPLING,
                BlockStateModelGenerator.TintType.NOT_TINTED);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.MAPLE_BOAT, Models.GENERATED);
        itemModelGenerator.register(ModItems.MAPLE_CHEST_BOAT, Models.GENERATED);
        itemModelGenerator.register(ModItems.JAR, Models.GENERATED);
        itemModelGenerator.register(ModItems.APPLE_JAM, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHORUS_FRUIT_JAM, Models.GENERATED);
        itemModelGenerator.register(ModItems.ELDERBERRIES_JAM, Models.GENERATED);
        itemModelGenerator.register(ModItems.GLOW_BERRIES_JAM, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLDEN_APPLE_JAM, Models.GENERATED);
        itemModelGenerator.register(ModItems.HAWTHORN_BERRIES_JAM, Models.GENERATED);
        itemModelGenerator.register(ModItems.LEMON_JAM, Models.GENERATED);
        itemModelGenerator.register(ModItems.MELON_JAM, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROSE_HIPS_JAM, Models.GENERATED);
        itemModelGenerator.register(ModItems.SWEET_BERRIES_JAM, Models.GENERATED);
    }
}
