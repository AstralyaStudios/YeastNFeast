package net.astralya.yeastnfeast.item;

import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.effect.ModEffects;
import net.astralya.yeastnfeast.entity.ModBoats;
import net.astralya.yeastnfeast.item.custom.*;
import net.astralya.yeastnfeast.util.ModUtils;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;


public class ModItems {

    // Resources
    public static final Item YEAST = registerItem("yeast",
            new Item(new FabricItemSettings()));
    public static final Item MAPLE_SYRUP = registerItem("maple_syrup",
            new MapleSyrupItem(new FabricItemSettings().food(ModFoodComponents.MAPLE_SYRUP)));
    public static final Item MOLASSES = registerItem("molasses",
            new ConsumableItem(new FabricItemSettings().food(ModFoodComponents.MOLASSES)));
    public static final Item MILK_BOTTLE = registerItem("milk_bottle",
            new MilkBottleItem(new FabricItemSettings().recipeRemainder(Items.GLASS_BOTTLE)));
    public static final Item RENNET = registerItem("rennet",
            new Item(new FabricItemSettings().recipeRemainder(Items.GLASS_BOTTLE).maxCount(16)));

    // Crops & Seeds
    public static final Item MINT_SEEDS = registerItem("mint_seeds",
            new AliasedBlockItem(ModBlocks.MINT_CROP, new Item.Settings()));
    public static final Item BARLEY_SEEDS = registerItem("barley_seeds",
            new AliasedBlockItem(ModBlocks.BARLEY_CROP, new Item.Settings()));
    public static final Item RYE_SEEDS = registerItem("rye_seeds",
            new AliasedBlockItem(ModBlocks.RYE_CROP, new Item.Settings()));
    public static final Item ELDERBERRIES = registerItem("elderberries",
            new BlockItem(ModBlocks.ELDERBERRY_BUSH, new Item.Settings().food(ModFoodComponents.ELDERBERRIES)));
    public static final Item ROSE_HIPS = registerItem("rose_hips",
            new BlockItem(ModBlocks.ROSE_HIPS_BUSH, new Item.Settings().food(ModFoodComponents.ROSE_HIPS)));
    public static final Item LEMON = registerItem("lemon",
            new Item(new Item.Settings().food(ModFoodComponents.LEMON)));
    public static final Item HAWTHORN_BERRIES = registerItem("hawthorn_berries",
            new Item(new Item.Settings().food(ModFoodComponents.HAWTHORN_BERRIES)));
    public static final Item MINT = registerItem("mint",
            new Item(new Item.Settings()));
    public static final Item GINGER = registerItem("ginger",
            new AliasedBlockItem(ModBlocks.GINGER_CROP, new Item.Settings().food(ModFoodComponents.GINGER)));
    public static final Item GARLIC = registerItem("garlic",
            new AliasedBlockItem(ModBlocks.GARLIC_CROP, new Item.Settings().food(ModFoodComponents.GARLIC)));
    public static final Item BARLEY = registerItem("barley",
            new Item(new Item.Settings()));
    public static final Item RYE = registerItem("rye",
            new Item(new Item.Settings()));

    // Bowl Food Items
    public static final Item SWEET_PORRIDGE = registerItem("sweet_porridge",
            new FeastItem(new Item.Settings().food(ModFoodComponents.SWEET_PORRIDGE), ()-> ModEffects.OVERFED, 4800, 0,
                    Text.translatable("tooltip.yeastnfeast.feast_item.bowl").formatted(Formatting.BLUE)));
    public static final Item SPICED_PORRIDGE = registerItem("spiced_porridge",
            new FeastItem(new Item.Settings().food(ModFoodComponents.SPICED_PORRIDGE), ()-> ModEffects.OVERFED, 4800, 0,
                    Text.translatable("tooltip.yeastnfeast.feast_item.bowl").formatted(Formatting.BLUE)));
    public static final Item BARLEY_AND_BEEF_STEW = registerItem("barley_and_beef_stew",
            new FeastItem(new Item.Settings().food(ModFoodComponents.BARLEY_AND_BEEF_STEW), ()-> ModEffects.OVERFED, 4800, 0,
                    Text.translatable("tooltip.yeastnfeast.feast_item.bowl").formatted(Formatting.BLUE)));
    public static final Item SALMON_CHOWDER = registerItem("salmon_chowder",
            new FeastItem(new Item.Settings().food(ModFoodComponents.SALMON_CHOWDER), ()-> ModEffects.OVERFED, 4800, 0,
                    Text.translatable("tooltip.yeastnfeast.feast_item.bowl").formatted(Formatting.BLUE)));
    public static final Item CHEESE_SOUP = registerItem("cheese_soup",
            new FeastItem(new Item.Settings().food(ModFoodComponents.CHEESE_SOUP), ()-> ModEffects.OVERFED, 4800, 0,
                    Text.translatable("tooltip.yeastnfeast.feast_item.bowl").formatted(Formatting.BLUE)));

    // Plated Food Items
    public static final Item MEAD_BRAISED_PORK = registerItem("mead_braised_pork",
            new FeastItem(new Item.Settings().food(ModFoodComponents.MEAD_BRAISED_PORK), ()-> ModEffects.VIGOROUS, 4800, 0,
                    Text.translatable("tooltip.yeastnfeast.feast_item.plate").formatted(Formatting.BLUE)));
    public static final Item HERBAL_COD = registerItem("herbal_cod",
            new FeastItem(new Item.Settings().food(ModFoodComponents.HERBAL_COD), ()-> ModEffects.VIGOROUS, 4800, 0,
                    Text.translatable("tooltip.yeastnfeast.feast_item.plate").formatted(Formatting.BLUE)));
    public static final Item LEMON_GLAZED_CHICKEN = registerItem("lemon_glazed_chicken",
            new FeastItem(new Item.Settings().food(ModFoodComponents.LEMON_GLAZED_CHICKEN), ()-> ModEffects.VIGOROUS, 4800, 0,
                    Text.translatable("tooltip.yeastnfeast.feast_item.plate").formatted(Formatting.BLUE)));
    public static final Item FORAGER_FEAST = registerItem("forager_feast",
            new FeastItem(new Item.Settings().food(ModFoodComponents.FORAGER_FEAST), ()-> ModEffects.VIGOROUS, 4800, 0,
                    Text.translatable("tooltip.yeastnfeast.feast_item.plate").formatted(Formatting.BLUE)));
    public static final Item MAPLE_GLAZED_RABBIT = registerItem("maple_glazed_rabbit",
            new FeastItem(new Item.Settings().food(ModFoodComponents.MAPLE_GLAZED_RABBIT), ()-> ModEffects.VIGOROUS, 4800, 0,
                    Text.translatable("tooltip.yeastnfeast.feast_item.plate").formatted(Formatting.BLUE)));
    public static final Item STUFFED_RYE_DUMPLINGS = registerItem("stuffed_rye_dumplings",
            new FeastItem(new Item.Settings().food(ModFoodComponents.STUFFED_RYE_DUMPLINGS), ()-> ModEffects.VIGOROUS, 4800, 0,
                    Text.translatable("tooltip.yeastnfeast.feast_item.plate").formatted(Formatting.BLUE)));

    // Other Food Items
    public static final Item BARLEY_BREAD = registerItem("barley_bread",
            new Item(new Item.Settings().food(ModFoodComponents.BARLEY_BREAD)));
    public static final Item RYE_BREAD = registerItem("rye_bread",
            new Item(new Item.Settings().food(ModFoodComponents.RYE_BREAD)));
    public static final Item MOLASSES_BREAD = registerItem("molasses_bread",
            new Item(new Item.Settings().food(ModFoodComponents.MOLASSES_BREAD)));
    public static final Item BERRY_ROLL = registerItem("berry_roll",
            new Item(new Item.Settings().food(ModFoodComponents.BERRY_ROLL)));
    public static final Item ROSE_TART = registerItem("rose_tart",
            new Item(new Item.Settings().food(ModFoodComponents.ROSE_TART)));
    public static final Item ELDERBERRY_PIE = registerItem("elderberry_pie",
            new Item(new Item.Settings().food(ModFoodComponents.ELDERBERRY_PIE)));
    public static final Item APPLE_PIE = registerItem("apple_pie",
            new Item(new Item.Settings().food(ModFoodComponents.APPLE_PIE)));
    public static final Item MINTED_CHEESE_TART = registerItem("minted_cheese_tart",
            new Item(new Item.Settings().food(ModFoodComponents.MINTED_CHEESE_TART)));
    public static final Item DUSKWHEEL_SKEWER = registerItem("duskwheel_skewer",
            new SkewerItem(new Item.Settings().food(ModFoodComponents.DUSKWHEEL_SKEWER)));
    public static final Item QUICHE = registerItem("quiche",
            new Item(new Item.Settings().food(ModFoodComponents.QUICHE)));

    // Mead Items
    public static final Item TANKARD = registerItem("tankard",
            new Item(new Item.Settings()));
    public static final Item HONEY_MEAD = registerItem("honey_mead",
            new MeadItem(new Item.Settings(), ()-> StatusEffects.RESISTANCE, 7200, 1,
                    Text.translatable("tooltip.yeastnfeast.mead_item.sweet").formatted(Formatting.BLUE)));
    public static final Item MOLASSES_MEAD = registerItem("molasses_mead",
            new MeadItem(new Item.Settings(), ()-> StatusEffects.RESISTANCE, 7200, 1,
                    Text.translatable("tooltip.yeastnfeast.mead_item.sweet").formatted(Formatting.BLUE)));
    public static final Item SOUR_MEAD = registerItem("sour_mead",
            new MeadItem(new Item.Settings(), ()-> StatusEffects.STRENGTH, 7200, 1,
                    Text.translatable("tooltip.yeastnfeast.mead_item.sour").formatted(Formatting.BLUE)));
    public static final Item THORNBERRY_MEAD = registerItem("thornberry_mead",
            new MeadItem(new Item.Settings(), ()-> StatusEffects.STRENGTH, 7200, 1,
                    Text.translatable("tooltip.yeastnfeast.mead_item.sour").formatted(Formatting.BLUE)));
    public static final Item BLOSSOM_MEAD = registerItem("blossom_mead",
            new MeadItem(new Item.Settings(), ()-> StatusEffects.REGENERATION, 4800, 1,
                    Text.translatable("tooltip.yeastnfeast.mead_item.floral").formatted(Formatting.BLUE)));
    public static final Item AMBER_MEAD = registerItem("amber_mead",
            new MeadItem(new Item.Settings(), ()-> StatusEffects.REGENERATION, 4800, 1,
                    Text.translatable("tooltip.yeastnfeast.mead_item.floral").formatted(Formatting.BLUE)));

    // Jams
    public static final Item JAR = registerItem("jar",
            new Item(new Item.Settings()));
    public static final Item APPLE_JAM = registerItem("apple_jam",
            new ConsumableItem(new Item.Settings().food(ModFoodComponents.APPLE_JAM)));
    public static final Item CHORUS_FRUIT_JAM = registerItem("chorus_fruit_jam",
            new ChorusFruitJamItem(new Item.Settings().food(ModFoodComponents.CHORUS_FRUIT_JAM)));
    public static final Item ELDERBERRIES_JAM = registerItem("elderberries_jam",
            new ConsumableItem(new Item.Settings().food(ModFoodComponents.ELDERBERRIES_JAM)));
    public static final Item GLOW_BERRIES_JAM = registerItem("glow_berries_jam",
            new ConsumableItem(new Item.Settings().food(ModFoodComponents.GLOW_BERRIES_JAM)));
    public static final Item GOLDEN_APPLE_JAM = registerItem("golden_apple_jam",
            new ConsumableItem(new Item.Settings().food(ModFoodComponents.GOLDEN_APPLE_JAM)));
    public static final Item HAWTHORN_BERRIES_JAM = registerItem("hawthorn_berries_jam",
            new ConsumableItem(new Item.Settings().food(ModFoodComponents.HAWTHORN_BERRIES_JAM)));
    public static final Item LEMON_JAM = registerItem("lemon_jam",
            new ConsumableItem(new Item.Settings().food(ModFoodComponents.LEMON_JAM)));
    public static final Item MELON_JAM = registerItem("melon_jam",
            new ConsumableItem(new Item.Settings().food(ModFoodComponents.MELON_JAM)));
    public static final Item ROSE_HIPS_JAM = registerItem("rose_hips_jam",
            new ConsumableItem(new Item.Settings().food(ModFoodComponents.ROSE_HIPS_JAM)));
    public static final Item SWEET_BERRIES_JAM = registerItem("sweet_berries_jam",
            new ConsumableItem(new Item.Settings().food(ModFoodComponents.SWEET_BERRIES_JAM)));

    // Cheeses
    public static final Item CHEESE_WHEEL = registerItem("cheese_wheel",
            new BlockItem(ModBlocks.CHEESE_WHEEL, new Item.Settings().maxCount(1)));
    public static final Item FRESHWHEEL = registerItem("freshwheel",
            new BlockItem(ModBlocks.FRESHWHEEL, new Item.Settings().maxCount(1)));
    public static final Item DUSKWHEEL = registerItem("duskwheel",
            new BlockItem(ModBlocks.DUSKWHEEL, new Item.Settings().maxCount(1)));
    public static final Item SHARPWHEEL = registerItem("sharpwheel",
            new BlockItem(ModBlocks.SHARPWHEEL, new Item.Settings().maxCount(1)));
    public static final Item CHEESE_SLICE = registerItem("cheese_slice",
            new Item(new Item.Settings().food(ModFoodComponents.CHEESE_SLICE)));
    public static final Item FRESHWHEEL_SLICE = registerItem("freshwheel_slice",
            new Item(new Item.Settings().food(ModFoodComponents.FRESHWHEEL_SLICE)));
    public static final Item DUSKWHEEL_SLICE = registerItem("duskwheel_slice",
            new Item(new Item.Settings().food(ModFoodComponents.DUSKWHEEL_SLICE)));
    public static final Item SHARPWHEEL_SLICE = registerItem("sharpwheel_slice",
            new Item(new Item.Settings().food(ModFoodComponents.SHARPWHEEL_SLICE)));

    // Block Items
    public static final Item KEG = registerItem("keg",
            new BlockItem(ModBlocks.KEG, new Item.Settings()));
    public static final Item CHEESE_PRESS = registerItem("cheese_press",
            new BlockItem(ModBlocks.CHEESE_PRESS, new Item.Settings()));

    // Wooden-related Items
    public static final Item MAPLE_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.MAPLE_BOAT_ID,
            ModBoats.MAPLE_BOAT_KEY, false);
    public static final Item MAPLE_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.MAPLE_CHEST_BOAT_ID,
            ModBoats.MAPLE_BOAT_KEY, true);
    public static final Item MAPLE_SIGN = registerItem("maple_sign",
            new SignItem(new Item.Settings().maxCount(16), ModBlocks.MAPLE_SIGN, ModBlocks.MAPLE_WALL_SIGN));
    public static final Item MAPLE_HANGING_SIGN = registerItem("maple_hanging_sign",
            new HangingSignItem(ModBlocks.MAPLE_HANGING_SIGN, ModBlocks.MAPLE_HANGING_WALL_SIGN, new Item.Settings().maxCount(16)));

    // Addon/Compat Items
    public static Item CHILLBERRIES_JAM;
    public static Item FROSTGALE_MEAD;
    public static Item SUNFIRE_TOMATO_BRUSCHETTA;
    public static Item STUFFED_MANDRAKE;
    static {
        if (ModUtils.isModLoaded("hexalia")) {
            CHILLBERRIES_JAM = registerItem("chillberries_jam", new ConsumableItem(new Item.Settings().food(ModFoodComponents.CHILLBERRIES_JAM)));
            STUFFED_MANDRAKE = registerItem("stuffed_mandrake", new FeastItem(new Item.Settings().food(ModFoodComponents.STUFFED_MANDRAKE),
                    ()-> ModEffects.VIGOROUS, 4800, 0, Text.translatable("tooltip.yeastnfeast.feast_item.plate").formatted(Formatting.BLUE)));
            FROSTGALE_MEAD = registerItem("frostgale_mead", new MeadItem(new Item.Settings(), ()-> StatusEffects.RESISTANCE,
                    4800, 1, Text.translatable("tooltip.yeastnfeast.mead_item.sweet").formatted(Formatting.BLUE)));
            SUNFIRE_TOMATO_BRUSCHETTA = registerItem("sunfire_tomato_bruschetta", new Item(new Item.Settings().food(ModFoodComponents.SUNFIRE_TOMATO_BRUSCHETTA)));
        }
    }

    public static Item GARDEN_SOUP;
    public static Item GINGER_TEA;
    public static Item SPICED_FLATBREAD;
    static {
        if (ModUtils.isModLoaded("farmersdelight")) {
            GARDEN_SOUP = registerItem("garden_soup", new FeastItem(new Item.Settings().food(ModFoodComponents.GARDEN_SOUP),
                    ()-> ModEffects.OVERFED, 4800, 0, Text.translatable("tooltip.yeastnfeast.feast_item.bowl").formatted(Formatting.BLUE)));
            GINGER_TEA = registerItem("ginger_tea", new MilkBottleItem(new Item.Settings().food(ModFoodComponents.GINGER_TEA)));
            SPICED_FLATBREAD = registerItem("spiced_flatbread", new Item(new Item.Settings().food(ModFoodComponents.SPICED_FLATBREAD)));
        }
    }

    public static Item STRAWBERRIES_JAM;
    public static Item MAPLE_RYE_PANCAKES;
    public static Item ELDERBERRIES_OATMEAL;
    static {
        if (ModUtils.isModLoaded("farm_and_charm")) {

            STRAWBERRIES_JAM = registerItem("strawberries_jam", new ConsumableItem(new Item.Settings().food(ModFoodComponents.STRAWBERRIES_JAM)));
            MAPLE_RYE_PANCAKES = registerItem("maple_rye_pancakes", new FeastItem(new Item.Settings().food(ModFoodComponents.MAPLE_RYE_PANCAKES),
                    ()-> ModEffects.VIGOROUS, 4800, 0, Text.translatable("tooltip.yeastnfeast.feast_item.plate").formatted(Formatting.BLUE)));
            ELDERBERRIES_OATMEAL = registerItem("elderberries_oatmeal", new FeastItem(new Item.Settings().food(ModFoodComponents.ELDERBERRIES_OATMEAL),
                    ()-> ModEffects.OVERFED, 4800, 0, Text.translatable("tooltip.yeastnfeast.feast_item.bowl").formatted(Formatting.BLUE)));
        }
    }

    /*public static Item HOMESTEADERS_HANDBOOK;
    static {
        if (ModUtils.isModLoaded("patchouli")) {
            HOMESTEADERS_HANDBOOK = registerItem("homesteaders_handbook",
                    new GuideBookItem(new Item.Settings()));
        }
    }*/

    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(YeastNFeastMod.MODID, name), item);
    }

    public static void registerModItems() {
        YeastNFeastMod.LOGGER.info("Registering Mod Items for " + YeastNFeastMod.MODID);
    }
}
