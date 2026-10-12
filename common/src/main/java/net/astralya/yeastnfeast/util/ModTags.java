package net.astralya.yeastnfeast.util;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Items {

        public static final TagKey<Item> KEG_CONTAINERS = createItemTag("keg_containers");

        public static final TagKey<Item> CHEESE_INGREDIENT = createItemTag("cheese_ingredient");

        public static final TagKey<Item> FEAST_BREADS = createItemTag("feast/breads");
        public static final TagKey<Item> FEAST_MAINS = createItemTag("feast/mains");
        public static final TagKey<Item> FEAST_BOWLS = createItemTag("feast/bowls");
        public static final TagKey<Item> FEAST_DAIRY = createItemTag("feast/dairy");
        public static final TagKey<Item> FEAST_SWEETS = createItemTag("feast/sweets");
        public static final TagKey<Item> FEAST_DRINKS = createItemTag("feast/drinks");

        public static final TagKey<Item> MAPLE_LOGS = createCommonItemTag("maple_logs");

        // Common Item Tags
        public static final TagKey<Item> FOODS = createCommonItemTag("foods");
        public static final TagKey<Item> FOODS_MILK = createCommonItemTag("foods/milk");
        public static final TagKey<Item> FOODS_FRUIT = createCommonItemTag("foods/fruit");
        public static final TagKey<Item> FOODS_VEGETABLE = createCommonItemTag("foods/vegetable");
        public static final TagKey<Item> FOODS_BERRY = createCommonItemTag("foods/berry");
        public static final TagKey<Item> FOODS_BREAD = createCommonItemTag("foods/bread");
        public static final TagKey<Item> FOODS_COOKED_MEAT = createCommonItemTag("foods/cooked_meat");
        public static final TagKey<Item> FOODS_CHEESE = createCommonItemTag("foods/cheese");

        public static final TagKey<Item> COOKED_CHICKEN = createCommonItemTag("foods/cooked_chicken");
        public static final TagKey<Item> COOKED_PORK = createCommonItemTag("foods/cooked_pork");
        public static final TagKey<Item> COOKED_BEEF = createCommonItemTag("foods/cooked_beef");
        public static final TagKey<Item> COOKED_MUTTON = createCommonItemTag("foods/cooked_mutton");
        public static final TagKey<Item> COOKED_COD = createCommonItemTag("foods/cooked_cod");
        public static final TagKey<Item> COOKED_SALMON = createCommonItemTag("foods/cooked_salmon");

        public static final TagKey<Item> SOUP = createCommonItemTag("foods/soup");
        public static final TagKey<Item> PIE = createCommonItemTag("foods/pie");

        public static final TagKey<Item> CROPS = createCommonItemTag("crops");
        public static final TagKey<Item> CROPS_GRAIN = createCommonItemTag("crops/grain");

        public static final TagKey<Item> BERRY = createCommonItemTag("berry");
        public static final TagKey<Item> SEEDS = createCommonItemTag("seeds");
        public static final TagKey<Item> MUSHROOMS = createCommonItemTag("mushrooms");


        private static TagKey<Item> createItemTag(String name) {
            return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, name));
        }

        private static TagKey<Item> createCommonItemTag(String name) {
            return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", name));
        }
    }

    public static class Blocks {

        public static final TagKey<Block> MAPLE_LOGS = createBlockTag("heating_block");
        public static final TagKey<Block> STORAGE_BLOCKS = createBlockTag("cocoon_logs");

        private static TagKey<Block> createBlockTag(String name) {
            return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, name));
        }

        private static TagKey<Block> createCommonBlockTag(String name) {
            return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", name));
        }
    }

    public static class Compat {

        // Serene Seasons
        public static final String SERENE_SEASONS = "sereneseasons";
        public static final TagKey<Block> SERENE_SEASONS_AUTUMN_CROPS_BLOCK = externalBlockTag(SERENE_SEASONS, "autumn_crops");
        public static final TagKey<Block> SERENE_SEASONS_SPRING_CROPS_BLOCK = externalBlockTag(SERENE_SEASONS, "spring_crops");
        public static final TagKey<Block> SERENE_SEASONS_SUMMER_CROPS_BLOCK = externalBlockTag(SERENE_SEASONS, "summer_crops");
        public static final TagKey<Block> SERENE_SEASONS_WINTER_CROPS_BLOCK = externalBlockTag(SERENE_SEASONS, "winter_crops");
        public static final TagKey<Block> SERENE_SEASONS_UNBREAKABLE_FERTILE_CROPS = externalBlockTag(SERENE_SEASONS, "unbreakable_infertile_crops");
        public static final TagKey<Item> SERENE_SEASONS_AUTUMN_CROPS = externalItemTag(SERENE_SEASONS, "autumn_crops");
        public static final TagKey<Item> SERENE_SEASONS_SPRING_CROPS = externalItemTag(SERENE_SEASONS, "spring_crops");
        public static final TagKey<Item> SERENE_SEASONS_SUMMER_CROPS = externalItemTag(SERENE_SEASONS, "summer_crops");
        public static final TagKey<Item> SERENE_SEASONS_WINTER_CROPS = externalItemTag(SERENE_SEASONS, "winter_crops");

        private static TagKey<Item> externalItemTag(String modId, String path) {
            return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(modId, path));
        }

        private static TagKey<Block> externalBlockTag(String modId, String path) {
            return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(modId, path));
        }
    }
}
