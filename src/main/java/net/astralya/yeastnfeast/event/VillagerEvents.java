package net.astralya.yeastnfeast.event;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.item.ModItems;
import net.astralya.yeastnfeast.villager.ModVillagers;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import java.util.List;

public class VillagerEvents
{
    public static void init() {
        ServerLifecycleEvents.SERVER_STARTING.register(server -> addTrades());
    }

    public static void addTrades() {
        TradeOfferHelper.registerVillagerOffers(ModVillagers.TAVERN_KEEPER, 1, VillagerEvents::addLevel1Trades);

        TradeOfferHelper.registerVillagerOffers(ModVillagers.TAVERN_KEEPER, 2, trades -> {
            trades.add(itemForEmeralds(ModBlocks.TREE_TAP, 1, 8, 2, 12));
            trades.add(itemForEmeralds(ModItems.TANKARD, 1, 4, 4, 8));
        });

        TradeOfferHelper.registerVillagerOffers(ModVillagers.TAVERN_KEEPER, 3, VillagerEvents::addAllMeadTrades);
    }

    private static void addLevel1Trades(List<TradeOffers.Factory> level1Trades) {
        level1Trades.add(itemForEmeralds(ModItems.MINT_SEEDS, 3, 3, 6, 3));

        level1Trades.add(itemForEmeralds(ModItems.BARLEY_SEEDS, 3, 3, 6, 3));
        level1Trades.add(itemForEmeralds(ModItems.RYE_SEEDS, 3, 3, 6, 3));
        level1Trades.add(itemForEmeralds(ModItems.GINGER, 3, 3, 6, 3));
        level1Trades.add(itemForEmeralds(ModItems.GARLIC, 3, 3, 6, 3));
    }

    private static void addAllMeadTrades(List<TradeOffers.Factory> level3Trades) {
        level3Trades.add(itemForEmeralds(ModItems.BLOSSOM_MEAD, 1, 2, 2, 8));
        level3Trades.add(itemForEmeralds(ModItems.AMBER_MEAD, 1, 2, 2, 8));
        level3Trades.add(itemForEmeralds(ModItems.MOLASSES_MEAD, 1, 2, 2, 8));
        level3Trades.add(itemForEmeralds(ModItems.SOUR_MEAD, 1, 2, 2, 8));
        level3Trades.add(itemForEmeralds(ModItems.HONEY_MEAD, 1, 2, 2, 8));
        level3Trades.add(itemForEmeralds(ModItems.THORNBERRY_MEAD, 1, 2, 2, 8));
    }

    public static TradeOffers.Factory emeraldForItems(ItemConvertible item, int emeraldCost, int itemCount, int maxTrades, int xp) {
        return (entity, random) -> new TradeOffer(
                new ItemStack(item, itemCount),
                new ItemStack(Items.EMERALD, emeraldCost),
                maxTrades, xp, 0.05F
        );
    }

    public static TradeOffers.Factory itemForEmeralds(ItemConvertible item, int itemCount, int emeraldCost, int maxTrades, int xp) {
        return (entity, random) -> new TradeOffer(
                new ItemStack(Items.EMERALD, emeraldCost),
                new ItemStack(item, itemCount),
                maxTrades, xp, 0.05F
        );
    }
}