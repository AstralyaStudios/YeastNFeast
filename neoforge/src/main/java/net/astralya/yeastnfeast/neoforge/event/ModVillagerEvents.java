package net.astralya.yeastnfeast.neoforge.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.item.ModItems;
import net.astralya.yeastnfeast.villager.ModVillagers;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.BasicItemListing;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
public class ModVillagerEvents {

    public static void onVillagerTrades(VillagerTradesEvent event) {
        if (event.getType() != ModVillagers.TAVERNKEEPER.value()) {
            return;
        }
        Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

        // Level 1 trades - Seeds
        addLevel1Trades(trades.get(1));

        // Level 2 trades - Tools
        trades.get(2).add(itemForEmeralds(ModBlocks.TREE_TAP.get(), 1, 8, 2, 12));
        trades.get(2).add(itemForEmeralds(ModItems.TANKARD.get(), 1, 4, 4, 8));

        // Level 3 trades - Add ALL mead options
        addAllMeadTrades(trades.get(3));
    }

    private static void addLevel1Trades(List<VillagerTrades.ItemListing> level1Trades) {
        level1Trades.add(itemForEmeralds(ModItems.MINT_SEEDS.get(), 3, 3, 6, 3));

        level1Trades.add(itemForEmeralds(ModItems.BARLEY_SEEDS.get(), 3, 3, 6, 3));
        level1Trades.add(itemForEmeralds(ModItems.RYE_SEEDS.get(), 3, 3, 6, 3));
        level1Trades.add(itemForEmeralds(ModItems.GINGER.get(), 3, 3, 6, 3));
        level1Trades.add(itemForEmeralds(ModItems.GARLIC.get(), 3, 3, 6, 3));
    }

    private static void addAllMeadTrades(List<VillagerTrades.ItemListing> level3Trades) {
        level3Trades.add(itemForEmeralds(ModItems.BLOSSOM_MEAD.get(), 1, 2, 2, 8));
        level3Trades.add(itemForEmeralds(ModItems.AMBER_MEAD.get(), 1, 2, 2, 8));
        level3Trades.add(itemForEmeralds(ModItems.MOLASSES_MEAD.get(), 1, 2, 2, 8));
        level3Trades.add(itemForEmeralds(ModItems.SOUR_MEAD.get(), 1, 2, 2, 8));
        level3Trades.add(itemForEmeralds(ModItems.HONEY_MEAD.get(), 1, 2, 2, 8));
        level3Trades.add(itemForEmeralds(ModItems.THORNBERRY_MEAD.get(), 1, 2, 2, 8));
    }

    public static BasicItemListing emeraldForItems(ItemLike item, int emeraldCost, int itemCount, int maxTrades, int xp) {
        return new BasicItemListing(new ItemStack(item, itemCount), new ItemStack(Items.EMERALD, emeraldCost), maxTrades, xp, 0.05F);
    }

    public static BasicItemListing itemForEmeralds(ItemLike item, int itemCount, int emeraldCost, int maxTrades, int xp) {
        return new BasicItemListing(new ItemStack(Items.EMERALD, emeraldCost), new ItemStack(item, itemCount), maxTrades, xp, 0.05F);
    }
}
