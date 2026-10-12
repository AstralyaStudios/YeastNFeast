package net.astralya.yeastnfeast.block.entity;

import net.minecraft.core.registries.Registries;
import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.block.entity.custom.CheesePressBlockEntity;
import net.astralya.yeastnfeast.block.entity.custom.FeastTableBlockEntity;
import net.astralya.yeastnfeast.block.entity.custom.KegBlockEntity;
import net.astralya.yeastnfeast.block.entity.custom.TreeTapBlockEntity;
import net.astralya.yeastnfeast.block.entity.wood.ModHangingSignBlockEntity;
import net.astralya.yeastnfeast.block.entity.wood.ModSignBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;
import dev.architectury.registry.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntityTypes {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPE =
            DeferredRegister.create(YeastNFeastMod.MODID, Registries.BLOCK_ENTITY_TYPE);

    public static final Supplier<BlockEntityType<KegBlockEntity>> KEG = BLOCK_ENTITY_TYPE.register("keg",
            () -> BlockEntityType.Builder.of(KegBlockEntity::new, ModBlocks.KEG.get()).build(null));

    public static final Supplier<BlockEntityType<TreeTapBlockEntity>> TREE_TAP = BLOCK_ENTITY_TYPE.register("tree_tap",
            () -> BlockEntityType.Builder.of(TreeTapBlockEntity::new, ModBlocks.TREE_TAP.get()).build(null));

    public static final Supplier<BlockEntityType<CheesePressBlockEntity>> CHEESE_PRESS = BLOCK_ENTITY_TYPE.register("cheese_press",
            () -> BlockEntityType.Builder.of(CheesePressBlockEntity::new, ModBlocks.CHEESE_PRESS.get()).build(null));

    public static final Supplier<BlockEntityType<FeastTableBlockEntity>> FEAST_TABLE = BLOCK_ENTITY_TYPE.register("feast_table",
            () -> BlockEntityType.Builder.of(FeastTableBlockEntity::new,
                    ModBlocks.FEAST_TABLES.stream().map(Supplier::get).toArray(Block[]::new)).build(null));

    // Mod Signs
    public static final Supplier<BlockEntityType<ModSignBlockEntity>> MOD_SIGN = BLOCK_ENTITY_TYPE.register(
            "mod_sign",
            () -> BlockEntityType.Builder.of(
                            ModSignBlockEntity::new,
                            ModBlocks.MAPLE_SIGN.get(),
                            ModBlocks.MAPLE_WALL_SIGN.get()
                    )
                    .build(null)
    );

    public static final Supplier<BlockEntityType<ModHangingSignBlockEntity>> MOD_HANGING_SIGN = BLOCK_ENTITY_TYPE.register(
            "mod_hanging_sign",
            () -> BlockEntityType.Builder.of(
                            ModHangingSignBlockEntity::new,
                            ModBlocks.MAPLE_HANGING_SIGN.get(),
                            ModBlocks.MAPLE_HANGING_WALL_SIGN.get()
                    )
                    .build(null)
    );

    public static void init() {
        BLOCK_ENTITY_TYPE.register();
    }
}
