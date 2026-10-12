package net.astralya.yeastnfeast.block.entity;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.block.entity.custom.CheesePressBlockEntity;
import net.astralya.yeastnfeast.block.entity.custom.KegBlockEntity;
import net.astralya.yeastnfeast.block.entity.custom.TreeTapBlockEntity;
import net.astralya.yeastnfeast.block.entity.sign.ModSignBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, YeastNFeastMod.MODID);

    // Block Entities
    public static final RegistryObject<BlockEntityType<KegBlockEntity>> KEG =
            BLOCK_ENTITIES.register("keg", () -> BlockEntityType.Builder.of(KegBlockEntity::new,
                    ModBlocks.KEG.get()).build(null));

    public static final RegistryObject<BlockEntityType<TreeTapBlockEntity>> TREE_TAP =
            BLOCK_ENTITIES.register("tree_tap", () -> BlockEntityType.Builder.of(TreeTapBlockEntity::new,
                    ModBlocks.TREE_TAP.get()).build(null));

    public static final RegistryObject<BlockEntityType<CheesePressBlockEntity>> CHEESE_PRESS =
            BLOCK_ENTITIES.register("cheese_press", () -> BlockEntityType.Builder.of(CheesePressBlockEntity::new,
                    ModBlocks.CHEESE_PRESS.get()).build(null));

    // Sign and Hanging Sign Entities
    public static final RegistryObject<BlockEntityType<ModSignBlockEntity>> MOD_SIGN =
            BLOCK_ENTITIES.register("mod_sign", () -> BlockEntityType.Builder.of(ModSignBlockEntity::new,
                    ModBlocks.MAPLE_SIGN.get(), ModBlocks.MAPLE_WALL_SIGN.get()).build(null));
    public static final RegistryObject<BlockEntityType<HangingSignBlockEntity>> MOD_HANGING_SIGN =
            BLOCK_ENTITIES.register("mod_hanging_sign", () -> BlockEntityType.Builder.of(HangingSignBlockEntity::new,
                    ModBlocks.MAPLE_HANGING_SIGN.get(), ModBlocks.MAPLE_HANGING_WALL_SIGN.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
