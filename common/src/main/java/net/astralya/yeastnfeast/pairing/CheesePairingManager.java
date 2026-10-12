package net.astralya.yeastnfeast.pairing;

import net.astralya.yeastnfeast.item.ModItems;
import net.astralya.yeastnfeast.util.ModTags;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;

import java.util.Map;
import java.util.WeakHashMap;

public final class CheesePairingManager {
    private static final long DURATION_TICKS = 1200L;
    private static final float SATURATION_BONUS = 0.25F;
    private static final String DATA_KEY = "YeastNFeastCheesePairing";
    private static final String TYPE_KEY = "Type";
    private static final String TICKS_KEY = "RemainingTicks";
    private static final Map<Player, ActivePairing> ACTIVE_PAIRINGS = new WeakHashMap<>();

    private CheesePairingManager() {
    }

    public static void onFoodConsumed(Player player, ItemStack stack, FoodProperties foodProperties) {
        PairingType cheesePairing = PairingType.fromCheese(stack);
        if (cheesePairing != null) {
            ACTIVE_PAIRINGS.put(player, new ActivePairing(cheesePairing, player.level().getGameTime() + DURATION_TICKS));
            return;
        }

        ActivePairing activePairing = ACTIVE_PAIRINGS.get(player);
        if (activePairing == null) {
            return;
        }
        if (player.level().getGameTime() >= activePairing.expiresAt()) {
            ACTIVE_PAIRINGS.remove(player);
            return;
        }
        if (!activePairing.type().matches(stack)) {
            return;
        }

        FoodData foodData = player.getFoodData();
        float baseSaturation = foodProperties.saturation();
        float bonus = baseSaturation * SATURATION_BONUS;
        foodData.setSaturation(Math.min(foodData.getFoodLevel(), foodData.getSaturationLevel() + bonus));
        ACTIVE_PAIRINGS.remove(player);
    }

    public static void tick(Player player) {
        ActivePairing activePairing = ACTIVE_PAIRINGS.get(player);
        if (activePairing != null && player.level().getGameTime() >= activePairing.expiresAt()) {
            ACTIVE_PAIRINGS.remove(player);
        }
    }

    public static void save(Player player, CompoundTag tag) {
        tag.remove(DATA_KEY);
        ActivePairing activePairing = ACTIVE_PAIRINGS.get(player);
        if (activePairing == null) {
            return;
        }
        long remainingTicks = activePairing.expiresAt() - player.level().getGameTime();
        if (remainingTicks <= 0L) {
            ACTIVE_PAIRINGS.remove(player);
            return;
        }

        CompoundTag pairingTag = new CompoundTag();
        pairingTag.putString(TYPE_KEY, activePairing.type().name());
        pairingTag.putLong(TICKS_KEY, remainingTicks);
        tag.put(DATA_KEY, pairingTag);
    }

    public static void load(Player player, CompoundTag tag) {
        ACTIVE_PAIRINGS.remove(player);
        if (!tag.contains(DATA_KEY)) {
            return;
        }

        CompoundTag pairingTag = tag.getCompound(DATA_KEY);
        long remainingTicks = pairingTag.getLong(TICKS_KEY);
        if (remainingTicks <= 0L) {
            return;
        }
        try {
            PairingType type = PairingType.valueOf(pairingTag.getString(TYPE_KEY));
            ACTIVE_PAIRINGS.put(player, new ActivePairing(type,
                    player.level().getGameTime() + Math.min(remainingTicks, DURATION_TICKS)));
        } catch (IllegalArgumentException ignored) {
        }
    }

    public static void clear(Player player) {
        ACTIVE_PAIRINGS.remove(player);
    }

    private record ActivePairing(PairingType type, long expiresAt) {
    }

    private enum PairingType {
        BASIC,
        BOWLS,
        MAINS,
        SWEETS;

        private static PairingType fromCheese(ItemStack stack) {
            if (stack.is(ModItems.CHEESE_SLICE.get())) {
                return BASIC;
            }
            if (stack.is(ModItems.FRESHWHEEL_SLICE.get())) {
                return BOWLS;
            }
            if (stack.is(ModItems.SHARPWHEEL_SLICE.get())) {
                return MAINS;
            }
            if (stack.is(ModItems.DUSKWHEEL_SLICE.get())) {
                return SWEETS;
            }
            return null;
        }

        private boolean matches(ItemStack stack) {
            return switch (this) {
                case BASIC -> stack.is(ModTags.Items.FEAST_BREADS)
                        || stack.is(ModTags.Items.FEAST_DAIRY);
                case BOWLS -> stack.is(ModTags.Items.FEAST_BOWLS);
                case MAINS -> stack.is(ModTags.Items.FEAST_MAINS);
                case SWEETS -> stack.is(ModTags.Items.FEAST_SWEETS);
            };
        }
    }
}
