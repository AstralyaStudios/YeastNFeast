package net.astralya.yeastnfeast.util;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {

    public static class Items {
        public static final TagKey<Item> MAPLE_LOGS = modItemTag("maple_logs");
        public static final TagKey<Item> JAMS = modItemTag("jams");
        public static final TagKey<Item> CHEESE_INGREDIENT = modItemTag("cheese_ingredient");

        public static final TagKey<Item> FOODS = cItemTag("foods");
        public static final TagKey<Item> BREAD_FOODS = cItemTag("foods/bread");
        public static final TagKey<Item> CROPS = cItemTag("crops");
        public static final TagKey<Item> BERRY_FOODS = cItemTag("foods/berry");
        public static final TagKey<Item> FRUIT_FOODS = cItemTag("foods/fruit");
        public static final TagKey<Item> SEEDS = cItemTag("seeds");
        public static final TagKey<Item> VEGETABLE_FOODS = cItemTag("foods/vegetable");
        public static final TagKey<Item> GRAINS = cItemTag("grains");
        public static final TagKey<Item> CHEESE_FOODS = cItemTag("foods/cheese");

        public static final TagKey<Item> WHEAT_CROPS = cItemTag("crops/wheat");

        public static final TagKey<Item> COOKED_MEAT_FOODS = cItemTag("foods/cooked_meat");
        public static final TagKey<Item> COOKED_BEEF = cItemTag("foods/cooked_beef");
        public static final TagKey<Item> COOKED_CHICKEN = cItemTag("foods/cooked_chicken");
        public static final TagKey<Item> COOKED_MUTTON = cItemTag("foods/cooked_mutton");
        public static final TagKey<Item> COOKED_PORK = cItemTag("foods/cooked_pork");

        public static final TagKey<Item> COOKED_FISH_SALMON = cItemTag("foods/cooked_salmon");
        public static final TagKey<Item> COOKED_FISH_COD = cItemTag("foods/cooked_cod");

        public static final TagKey<Item> MILKS = cItemTag("foods/milk");
        public static final TagKey<Item> MUSHROOMS = cItemTag("mushrooms");

        public static final TagKey<Item> SOUP_FOODS = cItemTag("foods/soup");
        public static final TagKey<Item> PIE_FOODS = cItemTag("foods/pie");
        public static final TagKey<Item> GOLDEN_FOODS = cItemTag("foods/golden");

        private static TagKey<Item> modItemTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(YeastNFeastMod.MODID, name));
        }

        private static TagKey<Item> cItemTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier("c", name));
        }
    }

    public static final class Blocks {
        private Blocks() {}

        public static final TagKey<Block> MAPLE_LOGS = modBlockTag("maple_logs");
        public static final TagKey<Block> STORAGE_BLOCKS = modBlockTag("storage_blocks");

        private static TagKey<Block> modBlockTag(String path) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(YeastNFeastMod.MODID, path));
        }

        @SuppressWarnings("unused")
        private static TagKey<Block> cBlockTag(String path) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of("c", path));
        }
    }

    public static final class Compat {
        private Compat() {}

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
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(modId, path));
        }

        private static TagKey<Block> externalBlockTag(String modId, String path) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(modId, path));
        }
    }
}
