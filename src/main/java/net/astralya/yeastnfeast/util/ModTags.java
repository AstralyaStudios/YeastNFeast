package net.astralya.yeastnfeast.util;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Items {

        // Custom Item Tags
        public static final TagKey<Item> MAPLE_LOGS = tag("maple_logs");

        // Conventional Item Tags
        public static final TagKey<Item> BREAD = forgeItemTag("bread");
        public static final TagKey<Item> CROPS = forgeItemTag("crops");
        public static final TagKey<Item> BERRIES = forgeItemTag("berries");
        public static final TagKey<Item> VEGETABLES = forgeItemTag("vegetables");
        public static final TagKey<Item> GRAINS = forgeItemTag("grains");
        public static final TagKey<Item> GRAINS_WHEAT = forgeItemTag("grains/wheat");

        public static final TagKey<Item> COOKED_BEEF = forgeItemTag("cooked_beef");
        public static final TagKey<Item> COOKED_CHICKEN = forgeItemTag("cooked_chicken");
        public static final TagKey<Item> COOKED_MUTTON = forgeItemTag("cooked_mutton");
        public static final TagKey<Item> COOKED_PORK = forgeItemTag("cooked_pork");

        public static final TagKey<Item> COOKED_COD = forgeItemTag("cooked_fishes/cod");
        public static final TagKey<Item> COOKED_SALMON = forgeItemTag("cooked_fishes/salmon");

        public static final TagKey<Item> CHEESE = forgeItemTag("cheese");

        public static final TagKey<Item> MILK = forgeItemTag("milk");
        public static final TagKey<Item> MILK_BOTTLE = forgeItemTag("milk/milk_bottle");

        public static final TagKey<Item> SEEDS = forgeItemTag("seeds");
        public static final TagKey<Item> MUSHROOM = forgeItemTag("mushroom");
    }

    private static TagKey<Item> tag(String name){
        return ItemTags.create(new ResourceLocation(YeastNFeastMod.MODID, name));
    }
    private static TagKey<Item> forgeItemTag(String name){
        return ItemTags.create(new ResourceLocation("forge", name));
    }

    public static class Blocks {

        // Custom Block Tags
        public static final TagKey<Block> MAPLE_LOGS = tag("maple_logs");
        public static final TagKey<Block> STORAGE_BLOCKS = tag("storage_blocks");

        // Conventional Block Tags

        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(YeastNFeastMod.MODID, name));
        }

        private static TagKey<Block> forgeTag(String name){
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class Biomes {
        public static final TagKey<Biome> HAS_ROSE_HIPS = TagKey.create(Registries.BIOME,
                (new ResourceLocation(YeastNFeastMod.MODID, "has_rose_hips")));
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
            return ItemTags.create(new ResourceLocation(modId, path));
        }

        private static TagKey<Block> externalBlockTag(String modId, String path) {
            return BlockTags.create(new ResourceLocation(modId, path));
        }
    }
}
