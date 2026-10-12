package net.astralya.yeastnfeast.item;

import net.minecraft.core.registries.Registries;
import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.effect.ModMobEffects;
import net.astralya.yeastnfeast.entity.boat.ModBoatEntity;
import net.astralya.yeastnfeast.item.custom.*;
import net.astralya.yeastnfeast.item.custom.wood.ModBoatItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import dev.architectury.platform.Platform;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.architectury.registry.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(YeastNFeastMod.MODID, Registries.ITEM);

    // Resources
    public static final RegistrySupplier<Item> YEAST = ITEMS.register("yeast", () -> new Item(new Item.Properties()));
    public static final RegistrySupplier<Item> MAPLE_SYRUP = ITEMS.register("maple_syrup", () -> new MapleSyrupItem(new Item.Properties().food(ModFoodProperties.MAPLE_SYRUP).stacksTo(16)));
    public static final RegistrySupplier<Item> MOLASSES = ITEMS.register("molasses", () -> new ConsumableItem(new Item.Properties().food(ModFoodProperties.MOLASSES).stacksTo(16)));
    public static final RegistrySupplier<Item> MILK_BOTTLE = ITEMS.register("milk_bottle", () -> new MilkBottleItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(16)));
    public static final RegistrySupplier<Item> RENNET = ITEMS.register("rennet", () -> new Item(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(16)));
    
    // Crops & Seeds
    public static final RegistrySupplier<Item> MINT_SEEDS = ITEMS.register("mint_seeds",
            () -> new ItemNameBlockItem(ModBlocks.MINT_CROP.get(), new Item.Properties()));
    public static final RegistrySupplier<Item> BARLEY_SEEDS = ITEMS.register("barley_seeds",
            () -> new ItemNameBlockItem(ModBlocks.BARLEY_CROP.get(), new Item.Properties()));
    public static final RegistrySupplier<Item> RYE_SEEDS = ITEMS.register("rye_seeds",
            () -> new ItemNameBlockItem(ModBlocks.RYE_CROP.get(), new Item.Properties()));
    public static final RegistrySupplier<Item> ELDERBERRIES = ITEMS.register("elderberries",
            () -> new ItemNameBlockItem(ModBlocks.ELDERBERRY_BUSH.get(), new Item.Properties().food(ModFoodProperties.ELDERBERRIES)));
    public static final RegistrySupplier<Item> ROSE_HIPS = ITEMS.register("rose_hips",
            () -> new ItemNameBlockItem(ModBlocks.ROSE_HIPS_BUSH.get(), new Item.Properties().food(ModFoodProperties.ROSE_HIPS)));
    public static final RegistrySupplier<Item> LEMON = ITEMS.register("lemon", () -> new Item(new Item.Properties().food(ModFoodProperties.LEMON)));
    public static final RegistrySupplier<Item> HAWTHORN_BERRIES = ITEMS.register("hawthorn_berries", () -> new Item(new Item.Properties().food(ModFoodProperties.HAWTHORN_BERRIES)));
    public static final RegistrySupplier<Item> MINT = ITEMS.register("mint", () -> new Item(new Item.Properties()));
    public static final RegistrySupplier<Item> GINGER = ITEMS.register("ginger",
            () -> new ItemNameBlockItem(ModBlocks.GINGER_CROP.get(), new Item.Properties().food(ModFoodProperties.GINGER)));
    public static final RegistrySupplier<Item> GARLIC = ITEMS.register("garlic",
            () -> new ItemNameBlockItem(ModBlocks.GARLIC_CROP.get(), new Item.Properties().food(ModFoodProperties.GARLIC)));
    public static final RegistrySupplier<Item> BARLEY = ITEMS.register("barley", () -> new Item(new Item.Properties()));
    public static final RegistrySupplier<Item> RYE = ITEMS.register("rye", () -> new Item(new Item.Properties()));

    // Bowl Food Items
    public static final RegistrySupplier<Item> SWEET_PORRIDGE = ITEMS.register("sweet_porridge",
            () -> new FeastItem(new Item.Properties().food(ModFoodProperties.SWEET_PORRIDGE), () -> ModMobEffects.OVERFED, 4800, 0,
                    Component.translatable("tooltip.yeastnfeast.feast_item.bowl").withStyle(ChatFormatting.BLUE)));
    public static final RegistrySupplier<Item> SPICED_PORRIDGE = ITEMS.register("spiced_porridge",
            () -> new FeastItem(new Item.Properties().food(ModFoodProperties.SPICED_PORRIDGE), () -> ModMobEffects.OVERFED, 4800, 0,
                    Component.translatable("tooltip.yeastnfeast.feast_item.bowl").withStyle(ChatFormatting.BLUE)));
    public static final RegistrySupplier<Item> BARLEY_AND_BEEF_STEW = ITEMS.register("barley_and_beef_stew",
            () -> new FeastItem(new Item.Properties().food(ModFoodProperties.BARLEY_AND_BEEF_STEW), () -> ModMobEffects.OVERFED, 4800, 0,
                    Component.translatable("tooltip.yeastnfeast.feast_item.bowl").withStyle(ChatFormatting.BLUE)));
    public static final RegistrySupplier<Item> SALMON_CHOWDER = ITEMS.register("salmon_chowder",
            () -> new FeastItem(new Item.Properties().food(ModFoodProperties.SALMON_CHOWDER), () -> ModMobEffects.OVERFED, 4800, 0,
                    Component.translatable("tooltip.yeastnfeast.feast_item.bowl").withStyle(ChatFormatting.BLUE)));
    public static final RegistrySupplier<Item> CHEESE_SOUP = ITEMS.register("cheese_soup",
            () -> new FeastItem(new Item.Properties().food(ModFoodProperties.CHEESE_SOUP), () -> ModMobEffects.OVERFED, 4800, 0,
                    Component.translatable("tooltip.yeastnfeast.feast_item.bowl").withStyle(ChatFormatting.BLUE)));
    
    // Plated Food Items
    public static final RegistrySupplier<Item> MEAD_BRAISED_PORK = ITEMS.register("mead_braised_pork",
            () -> new FeastItem(new Item.Properties().food(ModFoodProperties.MEAD_BRAISED_PORK), () -> ModMobEffects.VIGOROUS, 4800, 0,
                    Component.translatable("tooltip.yeastnfeast.feast_item.plate").withStyle(ChatFormatting.BLUE)));
    public static final RegistrySupplier<Item> HERBAL_COD = ITEMS.register("herbal_cod",
            () -> new FeastItem(new Item.Properties().food(ModFoodProperties.HERBAL_COD), () -> ModMobEffects.VIGOROUS, 4800, 0,
                    Component.translatable("tooltip.yeastnfeast.feast_item.plate").withStyle(ChatFormatting.BLUE)));
    public static final RegistrySupplier<Item> LEMON_GLAZED_CHICKEN = ITEMS.register("lemon_glazed_chicken",
            () -> new FeastItem(new Item.Properties().food(ModFoodProperties.LEMON_GLAZED_CHICKEN), () -> ModMobEffects.VIGOROUS, 4800, 0,
                    Component.translatable("tooltip.yeastnfeast.feast_item.plate").withStyle(ChatFormatting.BLUE)));
    public static final RegistrySupplier<Item> FORAGER_FEAST = ITEMS.register("forager_feast",
            () -> new FeastItem(new Item.Properties().food(ModFoodProperties.FORAGER_FEAST), () -> ModMobEffects.VIGOROUS, 4800, 0,
                    Component.translatable("tooltip.yeastnfeast.feast_item.plate").withStyle(ChatFormatting.BLUE)));
    public static final RegistrySupplier<Item> MAPLE_GLAZED_RABBIT = ITEMS.register("maple_glazed_rabbit",
            () -> new FeastItem(new Item.Properties().food(ModFoodProperties.MAPLE_GLAZED_RABBIT), () -> ModMobEffects.VIGOROUS, 4800, 0,
                    Component.translatable("tooltip.yeastnfeast.feast_item.plate").withStyle(ChatFormatting.BLUE)));
    public static final RegistrySupplier<Item> STUFFED_RYE_DUMPLINGS = ITEMS.register("stuffed_rye_dumplings",
            () -> new FeastItem(new Item.Properties().food(ModFoodProperties.STUFFED_RYE_DUMPLINGS), () -> ModMobEffects.VIGOROUS, 4800, 0,
                    Component.translatable("tooltip.yeastnfeast.feast_item.plate").withStyle(ChatFormatting.BLUE)));

    // Other Food Items
    public static final RegistrySupplier<Item> BARLEY_BREAD = ITEMS.register("barley_bread", () -> new Item(new Item.Properties().food(ModFoodProperties.BARLEY_BREAD)));
    public static final RegistrySupplier<Item> RYE_BREAD = ITEMS.register("rye_bread", () -> new Item(new Item.Properties().food(ModFoodProperties.RYE_BREAD)));
    public static final RegistrySupplier<Item> MOLASSES_BREAD = ITEMS.register("molasses_bread", () -> new Item(new Item.Properties().food(ModFoodProperties.MOLASSES_BREAD)));
    public static final RegistrySupplier<Item> BERRY_ROLL = ITEMS.register("berry_roll", () -> new Item(new Item.Properties().food(ModFoodProperties.BERRY_ROLL)));
    public static final RegistrySupplier<Item> ROSE_TART = ITEMS.register("rose_tart", () -> new Item(new Item.Properties().food(ModFoodProperties.ROSE_TART)));
    public static final RegistrySupplier<Item> ELDERBERRY_PIE = ITEMS.register("elderberry_pie", () -> new Item(new Item.Properties().food(ModFoodProperties.ELDERBERRY_PIE)));
    public static final RegistrySupplier<Item> APPLE_PIE = ITEMS.register("apple_pie", () -> new Item(new Item.Properties().food(ModFoodProperties.APPLE_PIE)));
    public static final RegistrySupplier<Item> MINTED_CHEESE_TART = ITEMS.register("minted_cheese_tart", () -> new Item(new Item.Properties().food(ModFoodProperties.MINTED_CHEESE_TART)));
    public static final RegistrySupplier<Item> DUSKWHEEL_SKEWER = ITEMS.register("duskwheel_skewer", () -> new SkewerItem(new Item.Properties().food(ModFoodProperties.DUSKWHEEL_SKEWER).stacksTo(16).craftRemainder(Items.STICK)));
    public static final RegistrySupplier<Item> QUICHE = ITEMS.register("quiche", () -> new Item(new Item.Properties().food(ModFoodProperties.QUICHE)));

    // Mead Items
    public static final RegistrySupplier<Item> TANKARD = ITEMS.register("tankard", () -> new Item(new Item.Properties()));
    public static final RegistrySupplier<Item> HONEY_MEAD = ITEMS.register("honey_mead", () -> new MeadItem(new Item.Properties(), 
            () -> ModMobEffects.RESILIENCE, 3600, 0,
            Component.translatable("tooltip.yeastnfeast.mead_item.sweet").withStyle(ChatFormatting.BLUE)));
    public static final RegistrySupplier<Item> MOLASSES_MEAD = ITEMS.register("molasses_mead", () -> new MeadItem(new Item.Properties(), 
            () -> ModMobEffects.RESILIENCE, 3600, 0,
            Component.translatable("tooltip.yeastnfeast.mead_item.sweet").withStyle(ChatFormatting.BLUE)));
    public static final RegistrySupplier<Item> SOUR_MEAD = ITEMS.register("sour_mead", () -> new MeadItem(new Item.Properties(), 
            () -> ModMobEffects.INVIGORATION, 3600, 0,
            Component.translatable("tooltip.yeastnfeast.mead_item.sour").withStyle(ChatFormatting.BLUE)));
    public static final RegistrySupplier<Item> THORNBERRY_MEAD = ITEMS.register("thornberry_mead", () -> new MeadItem(new Item.Properties(), 
            () -> ModMobEffects.INVIGORATION, 3600, 0,
            Component.translatable("tooltip.yeastnfeast.mead_item.sour").withStyle(ChatFormatting.BLUE)));
    public static final RegistrySupplier<Item> BLOSSOM_MEAD = ITEMS.register("blossom_mead", () -> new MeadItem(new Item.Properties(), 
            () -> ModMobEffects.RECOVERY, 3600, 0,
            Component.translatable("tooltip.yeastnfeast.mead_item.floral").withStyle(ChatFormatting.BLUE)));
    public static final RegistrySupplier<Item> AMBER_MEAD = ITEMS.register("amber_mead", () -> new MeadItem(new Item.Properties(), 
            () -> ModMobEffects.RECOVERY, 3600, 0,
            Component.translatable("tooltip.yeastnfeast.mead_item.floral").withStyle(ChatFormatting.BLUE)));

    // Jams
    public static final RegistrySupplier<Item> JAR = ITEMS.register("jar", () -> new Item(new Item.Properties()));
    public static final RegistrySupplier<Item> APPLE_JAM = ITEMS.register("apple_jam", () -> new ConsumableItem(new Item.Properties().food(ModFoodProperties.APPLE_JAM)));
    public static final RegistrySupplier<Item> CHORUS_FRUIT_JAM = ITEMS.register("chorus_fruit_jam", () -> new ChorusFruitJamItem(new Item.Properties().food(ModFoodProperties.CHORUS_FRUIT_JAM)));
    public static final RegistrySupplier<Item> ELDERBERRIES_JAM = ITEMS.register("elderberries_jam", () -> new ConsumableItem(new Item.Properties().food(ModFoodProperties.ELDERBERRIES_JAM)));
    public static final RegistrySupplier<Item> GLOW_BERRIES_JAM = ITEMS.register("glow_berries_jam", () -> new ConsumableItem(new Item.Properties().food(ModFoodProperties.GLOW_BERRIES_JAM)));
    public static final RegistrySupplier<Item> GOLDEN_APPLE_JAM = ITEMS.register("golden_apple_jam", () -> new ConsumableItem(new Item.Properties().food(ModFoodProperties.GOLDEN_APPLE_JAM)));
    public static final RegistrySupplier<Item> HAWTHORN_BERRIES_JAM = ITEMS.register("hawthorn_berries_jam", () -> new ConsumableItem(new Item.Properties().food(ModFoodProperties.HAWTHORN_BERRIES_JAM)));
    public static final RegistrySupplier<Item> LEMON_JAM = ITEMS.register("lemon_jam", () -> new ConsumableItem(new Item.Properties().food(ModFoodProperties.LEMON_JAM)));
    public static final RegistrySupplier<Item> MELON_JAM = ITEMS.register("melon_jam", () -> new ConsumableItem(new Item.Properties().food(ModFoodProperties.MELON_JAM)));
    public static final RegistrySupplier<Item> ROSE_HIPS_JAM = ITEMS.register("rose_hips_jam", () -> new ConsumableItem(new Item.Properties().food(ModFoodProperties.ROSE_HIPS_JAM)));
    public static final RegistrySupplier<Item> SWEET_BERRIES_JAM = ITEMS.register("sweet_berries_jam", () -> new ConsumableItem(new Item.Properties().food(ModFoodProperties.SWEET_BERRIES_JAM)));

    // Cheeses
    public static final RegistrySupplier<Item> CHEESE_WHEEL = ITEMS.register("cheese_wheel",
            () -> new BlockItem(ModBlocks.CHEESE_WHEEL.get(), new Item.Properties().stacksTo(1)));
    public static final RegistrySupplier<Item> FRESHWHEEL = ITEMS.register("freshwheel",
            () -> new BlockItem(ModBlocks.FRESHWHEEL.get(), new Item.Properties().stacksTo(1)));
    public static final RegistrySupplier<Item> DUSKWHEEL = ITEMS.register("duskwheel",
            () -> new BlockItem(ModBlocks.DUSKWHEEL.get(), new Item.Properties().stacksTo(1)));
    public static final RegistrySupplier<Item> SHARPWHEEL = ITEMS.register("sharpwheel",
            () -> new BlockItem(ModBlocks.SHARPWHEEL.get(), new Item.Properties().stacksTo(1)));
    public static final RegistrySupplier<Item> CHEESE_SLICE = ITEMS.register("cheese_slice", () -> new CheeseSliceItem(new Item.Properties().food(ModFoodProperties.CHEESE_SLICE), "tooltip.yeastnfeast.cheese_pairing.basic"));
    public static final RegistrySupplier<Item> FRESHWHEEL_SLICE = ITEMS.register("freshwheel_slice", () -> new CheeseSliceItem(new Item.Properties().food(ModFoodProperties.FRESHWHEEL_SLICE), "tooltip.yeastnfeast.cheese_pairing.bowls"));
    public static final RegistrySupplier<Item> DUSKWHEEL_SLICE = ITEMS.register("duskwheel_slice", () -> new CheeseSliceItem(new Item.Properties().food(ModFoodProperties.DUSKWHEEL_SLICE), "tooltip.yeastnfeast.cheese_pairing.sweets"));
    public static final RegistrySupplier<Item> SHARPWHEEL_SLICE = ITEMS.register("sharpwheel_slice", () -> new CheeseSliceItem(new Item.Properties().food(ModFoodProperties.SHARPWHEEL_SLICE), "tooltip.yeastnfeast.cheese_pairing.mains"));

    // Wood-Related Items
    public static final RegistrySupplier<Item> MAPLE_BOAT = ITEMS.register("maple_boat",
            () -> new ModBoatItem(false, ModBoatEntity.Type.MAPLE, new Item.Properties()));
    public static final RegistrySupplier<Item> MAPLE_CHEST_BOAT = ITEMS.register("maple_chest_boat",
            () -> new ModBoatItem(true, ModBoatEntity.Type.MAPLE, new Item.Properties()));
    public static final RegistrySupplier<Item> MAPLE_SIGN = ITEMS.register("maple_sign",
            () -> new SignItem(new Item.Properties(), ModBlocks.MAPLE_SIGN.get(), ModBlocks.MAPLE_WALL_SIGN.get()));
    public static final RegistrySupplier<Item> MAPLE_HANGING_SIGN = ITEMS.register("maple_hanging_sign",
            () -> new SignItem(new Item.Properties(), ModBlocks.MAPLE_HANGING_SIGN.get(), ModBlocks.MAPLE_HANGING_WALL_SIGN.get()));

    // Block Items
    public static final RegistrySupplier<Item> KEG = ITEMS.register("keg",
            () -> new BlockItem(ModBlocks.KEG.get(), new Item.Properties()));
    public static final RegistrySupplier<Item> CHEESE_PRESS = ITEMS.register("cheese_press",
            () -> new BlockItem(ModBlocks.CHEESE_PRESS.get(), new Item.Properties()));

    // Addon or Compat Items
    public static RegistrySupplier<Item> CHILLBERRIES_JAM;
    public static RegistrySupplier<Item> FROSTGALE_MEAD;
    public static RegistrySupplier<Item> SUNFIRE_TOMATO_BRUSCHETTA;
    public static RegistrySupplier<Item> STUFFED_MANDRAKE;
    static {
        if (Platform.isModLoaded("hexalia")) {
            CHILLBERRIES_JAM = ITEMS.register("chillberries_jam", () -> new ConsumableItem(new Item.Properties().food(ModFoodProperties.CHILLBERRIES_JAM)));
            STUFFED_MANDRAKE = ITEMS.register("stuffed_mandrake", () -> new FeastItem(new Item.Properties().food(ModFoodProperties.STUFFED_MANDRAKE),
                    () -> ModMobEffects.VIGOROUS, 4800, 0, Component.translatable("tooltip.yeastnfeast.feast_item.plate").withStyle(ChatFormatting.BLUE)));
            FROSTGALE_MEAD =  ITEMS.register("frostgale_mead", () -> new MeadItem(new Item.Properties(),
                    () -> ModMobEffects.RESILIENCE, 3600, 0, Component.translatable("tooltip.yeastnfeast.mead_item.sweet").withStyle(ChatFormatting.BLUE)));
            SUNFIRE_TOMATO_BRUSCHETTA = ITEMS.register("sunfire_tomato_bruschetta", () -> new Item(new Item.Properties().food(ModFoodProperties.SUNFIRE_TOMATO_BRUSCHETTA)));
        }
    }

    public static RegistrySupplier<Item> GARDEN_SOUP;
    public static RegistrySupplier<Item> GINGER_TEA;
    public static RegistrySupplier<Item> SPICED_FLATBREAD;
    static {
        if (Platform.isModLoaded("farmersdelight")) {
            GARDEN_SOUP = ITEMS.register("garden_soup", () -> new FeastItem(new Item.Properties().food(ModFoodProperties.GARDEN_SOUP),
                    () -> ModMobEffects.OVERFED, 4800, 0, Component.translatable("tooltip.yeastnfeast.feast_item.bowl").withStyle(ChatFormatting.BLUE)));
            GINGER_TEA =  ITEMS.register("ginger_tea", () -> new MilkBottleItem(new Item.Properties().food(ModFoodProperties.GINGER_TEA)));
            SPICED_FLATBREAD = ITEMS.register("spiced_flatbread", () -> new Item(new Item.Properties().food(ModFoodProperties.SPICED_FLATBREAD)));
        }
    }

    public static RegistrySupplier<Item> STRAWBERRIES_JAM;
    public static RegistrySupplier<Item> MAPLE_RYE_PANCAKES;
    public static RegistrySupplier<Item> ELDERBERRIES_OATMEAL;
    static {
        if (Platform.isModLoaded("farm_and_charm")) {
            STRAWBERRIES_JAM = ITEMS.register("strawberries_jam", () -> new ConsumableItem(new Item.Properties().food(ModFoodProperties.STRAWBERRIES_JAM)));
            MAPLE_RYE_PANCAKES =  ITEMS.register("maple_rye_pancakes",  () -> new FeastItem(new Item.Properties().food(ModFoodProperties.MAPLE_RYE_PANCAKES),
                    () -> ModMobEffects.VIGOROUS, 4800, 0, Component.translatable("tooltip.yeastnfeast.feast_item.plate").withStyle(ChatFormatting.BLUE)));
            ELDERBERRIES_OATMEAL = ITEMS.register("elderberries_oatmeal", () -> new FeastItem(new Item.Properties().food(ModFoodProperties.ELDERBERRIES_OATMEAL),
                    () -> ModMobEffects.OVERFED, 4800, 0, Component.translatable("tooltip.yeastnfeast.feast_item.bowl").withStyle(ChatFormatting.BLUE)));
        }
    }

    /*public static RegistrySupplier<Item> HOMESTEADERS_HANDBOOK;
    static {
        if (Platform.isModLoaded("patchouli")) {
            HOMESTEADERS_HANDBOOK = ITEMS.register("homesteaders_handbook",
                    () -> new GuideBookItem(new Item.Properties()));
        }
    }*/

    public static void init() {
        ITEMS.register();
    }
}
