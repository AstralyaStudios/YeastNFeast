package net.astralya.yeastnfeast.item;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.effect.ModMobEffects;
import net.astralya.yeastnfeast.entity.custom.ModBoatEntity;
import net.astralya.yeastnfeast.item.custom.*;
import net.astralya.yeastnfeast.item.custom.wood.ModBoatItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, YeastNFeastMod.MODID);

    // Resources
    public static final RegistryObject<Item> YEAST = ITEMS.register("yeast",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MAPLE_SYRUP = ITEMS.register("maple_syrup",
            () -> new MapleSyrupItem(new Item.Properties().food(ModFoodProperties.MAPLE_SYRUP)));
    public static final RegistryObject<Item> MOLASSES = ITEMS.register("molasses",
            () -> new ConsumableItem(new Item.Properties().food(ModFoodProperties.MOLASSES)));
    public static final RegistryObject<Item> MILK_BOTTLE = ITEMS.register("milk_bottle",
            () -> new MilkBottleItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(16)));
    public static final RegistryObject<Item> RENNET = ITEMS.register("rennet",
            () -> new Item(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(16)));

    // Crops & Seeds
    public static final RegistryObject<Item> BARLEY_SEEDS = ITEMS.register("barley_seeds",
            () -> new ItemNameBlockItem(ModBlocks.BARLEY_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> RYE_SEEDS = ITEMS.register("rye_seeds",
            () -> new ItemNameBlockItem(ModBlocks.RYE_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> MINT_SEEDS = ITEMS.register("mint_seeds",
            () -> new ItemNameBlockItem(ModBlocks.MINT_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> ELDERBERRIES = ITEMS.register("elderberries",
            () -> new BlockItem(ModBlocks.ELDERBERRY_BUSH.get(), new Item.Properties().food(ModFoodProperties.ELDERBERRIES)));
    public static final RegistryObject<Item> HAWTHORN_BERRIES = ITEMS.register("hawthorn_berries",
            () -> new Item(new Item.Properties().food(ModFoodProperties.HAWTHORN_BERRIES)));
    public static final RegistryObject<Item> ROSE_HIPS = ITEMS.register("rose_hips",
            () -> new BlockItem(ModBlocks.ROSE_HIPS_BUSH.get(), new Item.Properties().food(ModFoodProperties.ROSE_HIPS)));
    public static final RegistryObject<Item> LEMON = ITEMS.register("lemon",
            () -> new Item(new Item.Properties().food(ModFoodProperties.LEMON)));
    public static final RegistryObject<Item> MINT = ITEMS.register("mint",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GINGER = ITEMS.register("ginger",
            () -> new ItemNameBlockItem(ModBlocks.GINGER_CROP.get(), new Item.Properties()
                    .food(ModFoodProperties.GINGER)));
    public static final RegistryObject<Item> GARLIC = ITEMS.register("garlic",
            () -> new ItemNameBlockItem(ModBlocks.GARLIC_CROP.get(), new Item.Properties()
                    .food(ModFoodProperties.GARLIC)));
    public static final RegistryObject<Item> BARLEY = ITEMS.register("barley",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RYE = ITEMS.register("rye",
            () -> new Item(new Item.Properties()));

    // Bowl Items
    public static final RegistryObject<Item> SWEET_PORRIDGE = ITEMS.register("sweet_porridge",
            () -> new FeastItem(new Item.Properties().food(ModFoodProperties.SWEET_PORRIDGE), ModMobEffects.OVERFED, 4800, 0,
                    Component.translatable("tooltip.yeastnfeast.feast_item.bowl").withStyle(ChatFormatting.BLUE)));
    public static final RegistryObject<Item> SPICED_PORRIDGE = ITEMS.register("spiced_porridge",
            () -> new FeastItem(new Item.Properties().food(ModFoodProperties.SPICED_PORRIDGE), ModMobEffects.OVERFED, 4800, 0,
                    Component.translatable("tooltip.yeastnfeast.feast_item.bowl").withStyle(ChatFormatting.BLUE)));
    public static final RegistryObject<Item> BARLEY_AND_BEEF_STEW = ITEMS.register("barley_and_beef_stew",
            () -> new FeastItem(new Item.Properties().food(ModFoodProperties.BARLEY_AND_BEEF_STEW), ModMobEffects.OVERFED, 4800, 0,
                    Component.translatable("tooltip.yeastnfeast.feast_item.bowl").withStyle(ChatFormatting.BLUE)));
    public static final RegistryObject<Item> SALMON_CHOWDER = ITEMS.register("salmon_chowder",
            () -> new FeastItem(new Item.Properties().food(ModFoodProperties.SALMON_CHOWDER), ModMobEffects.OVERFED, 4800, 0,
                    Component.translatable("tooltip.yeastnfeast.feast_item.bowl").withStyle(ChatFormatting.BLUE)));
    public static final RegistryObject<Item> CHEESE_SOUP = ITEMS.register("cheese_soup",
            () -> new FeastItem(new Item.Properties().food(ModFoodProperties.CHEESE_SOUP), ModMobEffects.OVERFED, 4800, 0,
                    Component.translatable("tooltip.yeastnfeast.feast_item.bowl").withStyle(ChatFormatting.BLUE)));

    // Plated Food Items
    public static final RegistryObject<Item> MEAD_BRAISED_PORK = ITEMS.register("mead_braised_pork",
            () -> new FeastItem(new Item.Properties().food(ModFoodProperties.MEAD_BRAISED_PORK), ModMobEffects.VIGOROUS, 4800, 0,
                    Component.translatable("tooltip.yeastnfeast.feast_item.plate").withStyle(ChatFormatting.BLUE)));
    public static final RegistryObject<Item> HERBAL_COD = ITEMS.register("herbal_cod",
            () -> new FeastItem(new Item.Properties().food(ModFoodProperties.HERBAL_COD), ModMobEffects.VIGOROUS, 4800, 0,
                    Component.translatable("tooltip.yeastnfeast.feast_item.plate").withStyle(ChatFormatting.BLUE)));
    public static final RegistryObject<Item> LEMON_GLAZED_CHICKEN = ITEMS.register("lemon_glazed_chicken",
            () -> new FeastItem(new Item.Properties().food(ModFoodProperties.LEMON_GLAZED_CHICKEN), ModMobEffects.VIGOROUS, 4800, 0,
                    Component.translatable("tooltip.yeastnfeast.feast_item.plate").withStyle(ChatFormatting.BLUE)));
    public static final RegistryObject<Item> FORAGER_FEAST = ITEMS.register("forager_feast",
            () -> new FeastItem(new Item.Properties().food(ModFoodProperties.FORAGER_FEAST), ModMobEffects.VIGOROUS, 4800, 0,
                    Component.translatable("tooltip.yeastnfeast.feast_item.plate").withStyle(ChatFormatting.BLUE)));
    public static final RegistryObject<Item> MAPLE_GLAZED_RABBIT = ITEMS.register("maple_glazed_rabbit",
            () -> new FeastItem(new Item.Properties().food(ModFoodProperties.MAPLE_GLAZED_RABBIT), ModMobEffects.VIGOROUS, 4800, 0,
                    Component.translatable("tooltip.yeastnfeast.feast_item.plate").withStyle(ChatFormatting.BLUE)));
    public static final RegistryObject<Item> STUFFED_RYE_DUMPLINGS = ITEMS.register("stuffed_rye_dumplings",
            () -> new FeastItem(new Item.Properties().food(ModFoodProperties.STUFFED_RYE_DUMPLINGS), ModMobEffects.VIGOROUS, 4800, 0,
                    Component.translatable("tooltip.yeastnfeast.feast_item.plate").withStyle(ChatFormatting.BLUE)));

    // Other Food Items
    public static final RegistryObject<Item> BARLEY_BREAD = ITEMS.register("barley_bread",
            () -> new Item(new Item.Properties().food(ModFoodProperties.BARLEY_BREAD)));
    public static final RegistryObject<Item> RYE_BREAD = ITEMS.register("rye_bread",
            () -> new Item(new Item.Properties().food(ModFoodProperties.RYE_BREAD)));
    public static final RegistryObject<Item> MOLASSES_BREAD = ITEMS.register("molasses_bread",
            () -> new Item(new Item.Properties().food(ModFoodProperties.MOLASSES_BREAD)));
    public static final RegistryObject<Item> BERRY_ROLL = ITEMS.register("berry_roll",
            () -> new Item(new Item.Properties().food(ModFoodProperties.BERRY_ROLL)));
    public static final RegistryObject<Item> ROSE_TART = ITEMS.register("rose_tart",
            () -> new Item(new Item.Properties().food(ModFoodProperties.ROSE_TART)));
    public static final RegistryObject<Item> ELDERBERRY_PIE = ITEMS.register("elderberry_pie",
            () -> new Item(new Item.Properties().food(ModFoodProperties.ELDERBERRY_PIE)));
    public static final RegistryObject<Item> APPLE_PIE = ITEMS.register("apple_pie",
            () -> new Item(new Item.Properties().food(ModFoodProperties.APPLE_PIE)));

    public static final RegistryObject<Item> MINTED_CHEESE_TART = ITEMS.register("minted_cheese_tart",
            () -> new Item(new Item.Properties().food(ModFoodProperties.MINTED_CHEESE_TART)));
    public static final RegistryObject<Item> DUSKWHEEL_SKEWER = ITEMS.register("duskwheel_skewer",
            () -> new SkewerItem(new Item.Properties().food(ModFoodProperties.DUSKWHEEL_SKEWER)));
    public static final RegistryObject<Item> QUICHE = ITEMS.register("quiche",
            () -> new Item(new Item.Properties().food(ModFoodProperties.QUICHE)));

    // Mead
    public static final RegistryObject<Item> TANKARD = ITEMS.register("tankard", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HONEY_MEAD = ITEMS.register("honey_mead",
            () -> new MeadItem(new Item.Properties().food(ModFoodProperties.MAPLE_GLAZED_RABBIT),
                    () -> MobEffects.DAMAGE_RESISTANCE, 4800, 1,
                    Component.translatable("tooltip.yeastnfeast.mead_item.sweet").withStyle(ChatFormatting.BLUE)));
    public static final RegistryObject<Item> MOLASSES_MEAD = ITEMS.register("molasses_mead",
            () -> new MeadItem(new Item.Properties().food(ModFoodProperties.MAPLE_GLAZED_RABBIT),
                    () -> MobEffects.DAMAGE_RESISTANCE, 4800, 1,
                    Component.translatable("tooltip.yeastnfeast.mead_item.sweet").withStyle(ChatFormatting.BLUE)));
    public static final RegistryObject<Item> SOUR_MEAD = ITEMS.register("sour_mead",
            () -> new MeadItem(new Item.Properties().food(ModFoodProperties.MAPLE_GLAZED_RABBIT),
                    () -> MobEffects.DAMAGE_BOOST, 4800, 1,
                    Component.translatable("tooltip.yeastnfeast.mead_item.sour").withStyle(ChatFormatting.BLUE)));
    public static final RegistryObject<Item> THORNBERRY_MEAD = ITEMS.register("thornberry_mead",
            () -> new MeadItem(new Item.Properties().food(ModFoodProperties.MAPLE_GLAZED_RABBIT),
                    () -> MobEffects.DAMAGE_BOOST, 4800, 1,
                    Component.translatable("tooltip.yeastnfeast.mead_item.sour").withStyle(ChatFormatting.BLUE)));
    public static final RegistryObject<Item> BLOSSOM_MEAD = ITEMS.register("blossom_mead",
            () -> new MeadItem(new Item.Properties().food(ModFoodProperties.MAPLE_GLAZED_RABBIT),
                    () -> MobEffects.REGENERATION, 4800, 1,
                    Component.translatable("tooltip.yeastnfeast.mead_item.floral").withStyle(ChatFormatting.BLUE)));
    public static final RegistryObject<Item> AMBER_MEAD = ITEMS.register("amber_mead",
            () -> new MeadItem(new Item.Properties().food(ModFoodProperties.MAPLE_GLAZED_RABBIT),
                    () -> MobEffects.REGENERATION, 4800, 1,
                    Component.translatable("tooltip.yeastnfeast.mead_item.floral").withStyle(ChatFormatting.BLUE)));

    // Jams
    public static final RegistryObject<Item> JAR = ITEMS.register("jar",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> APPLE_JAM = ITEMS.register("apple_jam",
            () -> new ConsumableItem(new Item.Properties().food(ModFoodProperties.APPLE_JAM)));
    public static final RegistryObject<Item> CHORUS_FRUIT_JAM = ITEMS.register("chorus_fruit_jam",
            () -> new ChorusFruitJamItem(new Item.Properties().food(ModFoodProperties.CHORUS_FRUIT_JAM )));
    public static final RegistryObject<Item> ELDERBERRIES_JAM = ITEMS.register("elderberries_jam",
            () -> new ConsumableItem(new Item.Properties().food(ModFoodProperties.ELDERBERRIES_JAM)));
    public static final RegistryObject<Item> GLOW_BERRIES_JAM  = ITEMS.register("glow_berries_jam",
            () -> new ConsumableItem(new Item.Properties().food(ModFoodProperties.GLOW_BERRIES_JAM)));
    public static final RegistryObject<Item> GOLDEN_APPLE_JAM = ITEMS.register("golden_apple_jam",
            () -> new ConsumableItem(new Item.Properties().food(ModFoodProperties.GOLDEN_APPLE_JAM).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> HAWTHORN_BERRIES_JAM = ITEMS.register("hawthorn_berries_jam",
            () -> new ConsumableItem(new Item.Properties().food(ModFoodProperties.HAWTHORN_BERRIES_JAM)));
    public static final RegistryObject<Item> LEMON_JAM = ITEMS.register("lemon_jam",
            () -> new ConsumableItem(new Item.Properties().food(ModFoodProperties.LEMON_JAM)));
    public static final RegistryObject<Item> MELON_JAM = ITEMS.register("melon_jam",
            () -> new ConsumableItem(new Item.Properties().food(ModFoodProperties.MELON_JAM)));
    public static final RegistryObject<Item> ROSE_HIPS_JAM = ITEMS.register("rose_hips_jam",
            () -> new ConsumableItem(new Item.Properties().food(ModFoodProperties.ROSE_HIPS_JAM)));
    public static final RegistryObject<Item> SWEET_BERRIES_JAM = ITEMS.register("sweet_berries_jam",
            () -> new ConsumableItem(new Item.Properties().food(ModFoodProperties.SWEET_BERRIES_JAM)));

    // Cheeses
    public static final RegistryObject<Item> CHEESE_WHEEL = ITEMS.register("cheese_wheel",
            () -> new BlockItem(ModBlocks.CHEESE_WHEEL.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> FRESHWHEEL = ITEMS.register("freshwheel",
            () -> new BlockItem(ModBlocks.FRESHWHEEL.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> DUSKWHEEL = ITEMS.register("duskwheel",
            () -> new BlockItem(ModBlocks.DUSKWHEEL.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> SHARPWHEEL = ITEMS.register("sharpwheel",
            () -> new BlockItem(ModBlocks.SHARPWHEEL.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> CHEESE_SLICE = ITEMS.register("cheese_slice",
            () -> new Item(new Item.Properties().food(ModFoodProperties.CHEESE_SLICE)));
    public static final RegistryObject<Item> FRESHWHEEL_SLICE = ITEMS.register("freshwheel_slice",
            () -> new Item(new Item.Properties().food(ModFoodProperties.FRESHWHEEL_SLICE)));
    public static final RegistryObject<Item> DUSKWHEEL_SLICE = ITEMS.register("duskwheel_slice",
            () -> new Item(new Item.Properties().food(ModFoodProperties.DUSKWHEEL_SLICE)));
    public static final RegistryObject<Item> SHARPWHEEL_SLICE = ITEMS.register("sharpwheel_slice",
            () -> new Item(new Item.Properties().food(ModFoodProperties.SHARPWHEEL_SLICE)));
    
    // Wood-related Items
    public static final RegistryObject<Item> MAPLE_SIGN = ITEMS.register("maple_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), ModBlocks.MAPLE_SIGN.get(),
                    ModBlocks.MAPLE_WALL_SIGN.get()));
    public static final RegistryObject<Item> MAPLE_HANGING_SIGN = ITEMS.register("maple_hanging_sign",
            () -> new HangingSignItem(ModBlocks.MAPLE_HANGING_SIGN.get(),
                    ModBlocks.MAPLE_HANGING_WALL_SIGN.get(), new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> MAPLE_BOAT = ITEMS.register("maple_boat",
            () -> new ModBoatItem(false, ModBoatEntity.Type.MAPLE, new Item.Properties()));
    public static final RegistryObject<Item> MAPLE_CHEST_BOAT = ITEMS.register("maple_chest_boat",
            () -> new ModBoatItem(true, ModBoatEntity.Type.MAPLE, new Item.Properties()));

    // Block Items
    public static final RegistryObject<Item> KEG = ITEMS.register("keg",
            () -> new BlockItem(ModBlocks.KEG.get(), new Item.Properties()));
    public static final RegistryObject<Item> CHEESE_PRESS = ITEMS.register("cheese_press",
            () -> new BlockItem(ModBlocks.CHEESE_PRESS.get(), new Item.Properties()));

    // Addon/Compat Items
    public static RegistryObject<Item> CHILLBERRIES_JAM;
    public static RegistryObject<Item> FROSTGALE_MEAD;
    public static RegistryObject<Item> SUNFIRE_TOMATO_BRUSCHETTA;
    public static RegistryObject<Item> STUFFED_MANDRAKE;

    static {
        if (ModList.get().isLoaded("hexalia")) {
            CHILLBERRIES_JAM = ITEMS.register("chillberries_jam",
                    () -> new ConsumableItem(new Item.Properties().food(ModFoodProperties.CHILLBERRIES_JAM)));

            STUFFED_MANDRAKE = ITEMS.register("stuffed_mandrake",
                    () -> new FeastItem(new Item.Properties().food(ModFoodProperties.STUFFED_MANDRAKE),
                            ModMobEffects.VIGOROUS,
                            4800,
                            0,
                            Component.translatable("tooltip.yeastnfeast.feast_item.plate").withStyle(ChatFormatting.BLUE)));

            FROSTGALE_MEAD = ITEMS.register("frostgale_mead",
                    () -> new MeadItem(new Item.Properties(),
                            () -> MobEffects.DAMAGE_RESISTANCE,
                            4800,
                            1,
                            Component.translatable("tooltip.yeastnfeast.mead_item.sweet").withStyle(ChatFormatting.BLUE)));

            SUNFIRE_TOMATO_BRUSCHETTA = ITEMS.register("sunfire_tomato_bruschetta",
                    () -> new Item(new Item.Properties().food(ModFoodProperties.SUNFIRE_TOMATO_BRUSCHETTA)));
        }
    }

    public static RegistryObject<Item> GARDEN_SOUP;
    public static RegistryObject<Item> GINGER_TEA;
    public static RegistryObject<Item> SPICED_FLATBREAD;

    static {
        if (ModList.get().isLoaded("farmersdelight")) {
            GARDEN_SOUP = ITEMS.register("garden_soup",
                    () -> new FeastItem(new Item.Properties().food(ModFoodProperties.GARDEN_SOUP),
                            ModMobEffects.OVERFED,
                            4800,
                            0,
                            Component.translatable("tooltip.yeastnfeast.feast_item.bowl").withStyle(ChatFormatting.BLUE)));

            GINGER_TEA = ITEMS.register("ginger_tea",
                    () -> new MilkBottleItem(new Item.Properties().food(ModFoodProperties.GINGER_TEA)));

            SPICED_FLATBREAD = ITEMS.register("spiced_flatbread",
                    () -> new Item(new Item.Properties().food(ModFoodProperties.SPICED_FLATBREAD)));
        }
    }

    public static RegistryObject<Item> STRAWBERRIES_JAM;
    public static RegistryObject<Item> MAPLE_RYE_PANCAKES;
    public static RegistryObject<Item> ELDERBERRIES_OATMEAL;

    static {
        if (ModList.get().isLoaded("farm_and_charm")) {
            STRAWBERRIES_JAM = ITEMS.register("strawberries_jam",
                    () -> new ConsumableItem(new Item.Properties().food(ModFoodProperties.STRAWBERRIES_JAM)));

            MAPLE_RYE_PANCAKES = ITEMS.register("maple_rye_pancakes",
                    () -> new FeastItem(new Item.Properties().food(ModFoodProperties.MAPLE_RYE_PANCAKES),
                            ModMobEffects.VIGOROUS,
                            4800,
                            0,
                            Component.translatable("tooltip.yeastnfeast.feast_item.plate").withStyle(ChatFormatting.BLUE)));

            ELDERBERRIES_OATMEAL = ITEMS.register("elderberries_oatmeal",
                    () -> new FeastItem(new Item.Properties().food(ModFoodProperties.ELDERBERRIES_OATMEAL),
                            ModMobEffects.OVERFED,
                            4800,
                            0,
                            Component.translatable("tooltip.yeastnfeast.feast_item.bowl").withStyle(ChatFormatting.BLUE)));
        }
    }


    /*public static RegistryObject<Item> HOMESTEADERS_HANDBOOK;
    static {
        if (ModList.get().isLoaded("patchouli")) {
            HOMESTEADERS_HANDBOOK = ITEMS.register("homesteaders_handbook",
                    () -> new GuideBookItem(new Item.Properties()));
        }
    }*/

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
