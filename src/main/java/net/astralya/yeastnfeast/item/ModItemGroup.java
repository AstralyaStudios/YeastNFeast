package net.astralya.yeastnfeast.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.util.ModUtils;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static final ItemGroup YEAST_N_FEAST_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(YeastNFeastMod.MODID, "yeast_n_feast_group"),
            FabricItemGroup.builder().displayName(Text.translatable("itemGroup.yeastnfeast"))
                    .icon(() -> new ItemStack(ModItems.TANKARD)).entries((displayContext, entries) -> {
                        // Functional Blocks
                        entries.add(ModBlocks.KEG);
                        entries.add(ModBlocks.TREE_TAP);
                        entries.add(ModBlocks.CHEESE_PRESS);

                        // Resources
                        entries.add(ModItems.YEAST);
                        entries.add(ModItems.MAPLE_SYRUP);
                        entries.add(ModItems.MOLASSES);
                        entries.add(ModItems.MILK_BOTTLE);
                        entries.add(ModItems.RENNET);

                        // Seeds
                        entries.add(ModItems.BARLEY_SEEDS);
                        entries.add(ModItems.RYE_SEEDS);
                        entries.add(ModItems.MINT_SEEDS);

                        // Food Resources
                        entries.add(ModItems.ELDERBERRIES);
                        entries.add(ModItems.GARLIC);
                        entries.add(ModItems.GINGER);
                        entries.add(ModItems.HAWTHORN_BERRIES);
                        entries.add(ModItems.LEMON);
                        entries.add(ModItems.MINT);
                        entries.add(ModItems.ROSE_HIPS);
                        entries.add(ModItems.BARLEY);
                        entries.add(ModItems.RYE);
                        entries.add(ModBlocks.THISTLE);

                        // Storage Bags
                        entries.add(ModBlocks.BAG_OF_ELDERBERRIES);
                        entries.add(ModBlocks.BAG_OF_GARLIC);
                        entries.add(ModBlocks.BAG_OF_GINGER);
                        entries.add(ModBlocks.BAG_OF_HAWTHORN_BERRIES);
                        entries.add(ModBlocks.BAG_OF_LEMON);
                        entries.add(ModBlocks.BAG_OF_MINT);
                        entries.add(ModBlocks.BAG_OF_ROSE_HIPS);
                        entries.add(ModBlocks.BAG_OF_THISTLE);
                        entries.add(ModBlocks.BARLEY_BLOCK);
                        entries.add(ModBlocks.RYE_BLOCK);

                        // Baked
                        entries.add(ModItems.BARLEY_BREAD);
                        entries.add(ModItems.RYE_BREAD);
                        entries.add(ModItems.MOLASSES_BREAD);
                        entries.add(ModItems.BERRY_ROLL);
                        entries.add(ModItems.ROSE_TART);
                        entries.add(ModItems.MINTED_CHEESE_TART);
                        entries.add(ModItems.QUICHE);
                        entries.add(ModItems.ELDERBERRY_PIE);
                        entries.add(ModItems.APPLE_PIE);

                        // Food
                        entries.add(ModItems.SWEET_PORRIDGE);
                        entries.add(ModItems.SPICED_PORRIDGE);
                        entries.add(ModItems.BARLEY_AND_BEEF_STEW);
                        entries.add(ModItems.SALMON_CHOWDER);
                        entries.add(ModItems.CHEESE_SOUP);
                        entries.add(ModItems.MEAD_BRAISED_PORK);
                        entries.add(ModItems.HERBAL_COD);
                        entries.add(ModItems.LEMON_GLAZED_CHICKEN);
                        entries.add(ModItems.FORAGER_FEAST);
                        entries.add(ModItems.MAPLE_GLAZED_RABBIT);
                        entries.add(ModItems.STUFFED_RYE_DUMPLINGS);
                        entries.add(ModItems.DUSKWHEEL_SKEWER);

                        // Mead
                        entries.add(ModItems.TANKARD);
                        entries.add(ModItems.HONEY_MEAD);
                        entries.add(ModItems.MOLASSES_MEAD);
                        entries.add(ModItems.SOUR_MEAD);
                        entries.add(ModItems.THORNBERRY_MEAD);
                        entries.add(ModItems.BLOSSOM_MEAD);
                        entries.add(ModItems.AMBER_MEAD);

                        // Jams
                        entries.add(ModItems.JAR);
                        entries.add(ModItems.APPLE_JAM);
                        entries.add(ModItems.CHORUS_FRUIT_JAM);
                        entries.add(ModItems.ELDERBERRIES_JAM);
                        entries.add(ModItems.GLOW_BERRIES_JAM);
                        entries.add(ModItems.GOLDEN_APPLE_JAM);
                        entries.add(ModItems.HAWTHORN_BERRIES_JAM);
                        entries.add(ModItems.LEMON_JAM);
                        entries.add(ModItems.MELON_JAM);
                        entries.add(ModItems.ROSE_HIPS_JAM);
                        entries.add(ModItems.SWEET_BERRIES_JAM);

                        // Cheese
                        entries.add(ModItems.CHEESE_WHEEL);
                        entries.add(ModItems.CHEESE_SLICE);
                        entries.add(ModItems.DUSKWHEEL);
                        entries.add(ModItems.DUSKWHEEL_SLICE);
                        entries.add(ModItems.SHARPWHEEL);
                        entries.add(ModItems.SHARPWHEEL_SLICE);
                        entries.add(ModItems.FRESHWHEEL);
                        entries.add(ModItems.FRESHWHEEL_SLICE);

                        // Wood-Related Items
                        entries.add(ModBlocks.MAPLE_LEAVES);
                        entries.add(ModBlocks.MAPLE_LOG);
                        entries.add(ModBlocks.MAPLE_WOOD);
                        entries.add(ModBlocks.STRIPPED_MAPLE_LOG);
                        entries.add(ModBlocks.STRIPPED_MAPLE_WOOD);
                        entries.add(ModBlocks.MAPLE_PLANKS);
                        entries.add(ModBlocks.MAPLE_STAIRS);
                        entries.add(ModBlocks.MAPLE_SLAB);
                        entries.add(ModBlocks.MAPLE_FENCE);
                        entries.add(ModBlocks.MAPLE_FENCE_GATE);
                        entries.add(ModBlocks.MAPLE_DOOR);
                        entries.add(ModBlocks.MAPLE_TRAPDOOR);
                        entries.add(ModBlocks.MAPLE_PRESSURE_PLATE);
                        entries.add(ModBlocks.MAPLE_BUTTON);
                        entries.add(ModItems.MAPLE_BOAT);
                        entries.add(ModItems.MAPLE_CHEST_BOAT);
                        entries.add(ModItems.MAPLE_SIGN);
                        entries.add(ModItems.MAPLE_HANGING_SIGN);

                        entries.add(ModBlocks.MAPLE_SAPLING);
                        entries.add(ModBlocks.LEMON_SAPLING);
                        entries.add(ModBlocks.HAWTHORN_SAPLING);

                        // Compat Items
                        /*if (ModUtils.isModLoaded("patchouli")) {
                            entries.add(ModItems.HOMESTEADERS_HANDBOOK);
                        }*/

                        if (ModUtils.isModLoaded("hexalia")) {
                            entries.add(ModItems.CHILLBERRIES_JAM);
                            entries.add(ModItems.FROSTGALE_MEAD);
                            entries.add(ModItems.SUNFIRE_TOMATO_BRUSCHETTA);
                            entries.add(ModItems.STUFFED_MANDRAKE);
                        }

                        if (ModUtils.isModLoaded("farmersdelight")) {
                            entries.add(ModItems.GARDEN_SOUP);
                            entries.add(ModItems.GINGER_TEA);
                            entries.add(ModItems.SPICED_FLATBREAD);
                        }

                        if (ModUtils.isModLoaded("farm_and_charm")) {
                            entries.add(ModItems.STRAWBERRIES_JAM);
                            entries.add(ModItems.MAPLE_RYE_PANCAKES);
                            entries.add(ModItems.ELDERBERRIES_OATMEAL);
                        }

                    }).build());

    public static void registerItemGroups() {

    }
}
