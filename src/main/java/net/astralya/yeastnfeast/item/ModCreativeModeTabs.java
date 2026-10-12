package net.astralya.yeastnfeast.item;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, YeastNFeastMod.MODID);

    public static final RegistryObject<CreativeModeTab> YEASTNFEAST_TAB = CREATIVE_MODE_TABS.register("yeastnfeast_tab",
            () -> CreativeModeTab.builder().icon(()-> new ItemStack(ModItems.TANKARD.get())).title(Component.translatable("itemGroup.yeastnfeast")).displayItems((displayParameters, output) -> {

                // Functional Blocks
                output.accept(ModBlocks.KEG.get());
                output.accept(ModBlocks.TREE_TAP.get());
                output.accept(ModBlocks.CHEESE_PRESS.get());

                // Resources
                output.accept(ModItems.YEAST.get());
                output.accept(ModItems.MAPLE_SYRUP.get());
                output.accept(ModItems.MOLASSES.get());
                output.accept(ModItems.MILK_BOTTLE.get());
                output.accept(ModItems.RENNET.get());

                // Seeds
                output.accept(ModItems.BARLEY_SEEDS.get());
                output.accept(ModItems.RYE_SEEDS.get());
                output.accept(ModItems.MINT_SEEDS.get());

                // Food Resources
                output.accept(ModItems.ELDERBERRIES.get());
                output.accept(ModItems.GARLIC.get());
                output.accept(ModItems.GINGER.get());
                output.accept(ModItems.HAWTHORN_BERRIES.get());
                output.accept(ModItems.LEMON.get());
                output.accept(ModItems.MINT.get());
                output.accept(ModItems.ROSE_HIPS.get());
                output.accept(ModItems.BARLEY.get());
                output.accept(ModItems.RYE.get());
                output.accept(ModBlocks.THISTLE.get());

                // Storage Bags
                output.accept(ModBlocks.BAG_OF_ELDERBERRIES.get());
                output.accept(ModBlocks.BAG_OF_GARLIC.get());
                output.accept(ModBlocks.BAG_OF_GINGER.get());
                output.accept(ModBlocks.BAG_OF_HAWTHORN_BERRIES.get());
                output.accept(ModBlocks.BAG_OF_LEMON.get());
                output.accept(ModBlocks.BAG_OF_MINT.get());
                output.accept(ModBlocks.BAG_OF_ROSE_HIPS.get());
                output.accept(ModBlocks.BAG_OF_THISTLE.get());
                output.accept(ModBlocks.BARLEY_BLOCK.get());
                output.accept(ModBlocks.RYE_BLOCK.get());

                // Baked
                output.accept(ModItems.BARLEY_BREAD.get());
                output.accept(ModItems.RYE_BREAD.get());
                output.accept(ModItems.MOLASSES_BREAD.get());
                output.accept(ModItems.BERRY_ROLL.get());
                output.accept(ModItems.ROSE_TART.get());
                output.accept(ModItems.MINTED_CHEESE_TART.get());
                output.accept(ModItems.QUICHE.get());
                output.accept(ModItems.ELDERBERRY_PIE.get());
                output.accept(ModItems.APPLE_PIE.get());

                // Food
                output.accept(ModItems.SWEET_PORRIDGE.get());
                output.accept(ModItems.SPICED_PORRIDGE.get());
                output.accept(ModItems.BARLEY_AND_BEEF_STEW.get());
                output.accept(ModItems.SALMON_CHOWDER.get());
                output.accept(ModItems.CHEESE_SOUP.get());
                output.accept(ModItems.MEAD_BRAISED_PORK.get());
                output.accept(ModItems.HERBAL_COD.get());
                output.accept(ModItems.LEMON_GLAZED_CHICKEN.get());
                output.accept(ModItems.FORAGER_FEAST.get());
                output.accept(ModItems.MAPLE_GLAZED_RABBIT.get());
                output.accept(ModItems.STUFFED_RYE_DUMPLINGS.get());
                output.accept(ModItems.DUSKWHEEL_SKEWER.get());

                // Mead
                output.accept(ModItems.TANKARD.get());
                output.accept(ModItems.HONEY_MEAD.get());
                output.accept(ModItems.MOLASSES_MEAD.get());
                output.accept(ModItems.SOUR_MEAD.get());
                output.accept(ModItems.THORNBERRY_MEAD.get());
                output.accept(ModItems.BLOSSOM_MEAD.get());
                output.accept(ModItems.AMBER_MEAD.get());

                // Jams
                output.accept(ModItems.JAR.get());
                output.accept(ModItems.APPLE_JAM.get());
                output.accept(ModItems.CHORUS_FRUIT_JAM.get());
                output.accept(ModItems.ELDERBERRIES_JAM.get());
                output.accept(ModItems.GLOW_BERRIES_JAM.get());
                output.accept(ModItems.GOLDEN_APPLE_JAM.get());
                output.accept(ModItems.HAWTHORN_BERRIES_JAM.get());
                output.accept(ModItems.LEMON_JAM.get());
                output.accept(ModItems.MELON_JAM.get());
                output.accept(ModItems.ROSE_HIPS_JAM.get());
                output.accept(ModItems.SWEET_BERRIES_JAM.get());

                // Cheese
                output.accept(ModItems.CHEESE_WHEEL.get());
                output.accept(ModItems.CHEESE_SLICE.get());
                output.accept(ModItems.DUSKWHEEL.get());
                output.accept(ModItems.DUSKWHEEL_SLICE.get());
                output.accept(ModItems.SHARPWHEEL.get());
                output.accept(ModItems.SHARPWHEEL_SLICE.get());
                output.accept(ModItems.FRESHWHEEL.get());
                output.accept(ModItems.FRESHWHEEL_SLICE.get());

                // Wood-Related Items
                output.accept(ModBlocks.MAPLE_LEAVES.get());
                output.accept(ModBlocks.MAPLE_LOG.get());
                output.accept(ModBlocks.MAPLE_WOOD.get());
                output.accept(ModBlocks.STRIPPED_MAPLE_LOG.get());
                output.accept(ModBlocks.STRIPPED_MAPLE_WOOD.get());
                output.accept(ModBlocks.MAPLE_PLANKS.get());
                output.accept(ModBlocks.MAPLE_STAIRS.get());
                output.accept(ModBlocks.MAPLE_SLAB.get());
                output.accept(ModBlocks.MAPLE_FENCE.get());
                output.accept(ModBlocks.MAPLE_FENCE_GATE.get());
                output.accept(ModBlocks.MAPLE_DOOR.get());
                output.accept(ModBlocks.MAPLE_TRAPDOOR.get());
                output.accept(ModBlocks.MAPLE_PRESSURE_PLATE.get());
                output.accept(ModBlocks.MAPLE_BUTTON.get());
                output.accept(ModItems.MAPLE_BOAT.get());
                output.accept(ModItems.MAPLE_CHEST_BOAT.get());
                output.accept(ModItems.MAPLE_SIGN.get());
                output.accept(ModItems.MAPLE_HANGING_SIGN.get());

                output.accept(ModBlocks.MAPLE_SAPLING.get());
                output.accept(ModBlocks.LEMON_SAPLING.get());
                output.accept(ModBlocks.HAWTHORN_SAPLING.get());


            }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
