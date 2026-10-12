package net.astralya.yeastnfeast.block.entity.custom;

import net.astralya.yeastnfeast.block.entity.ModBlockEntityTypes;
import net.astralya.yeastnfeast.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.tags.TagKey;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FeastTableBlockEntity extends SyncBlockEntity {
    public static final int SERVING_SLOTS = 4;
    public static final List<TagKey<Item>> FEAST_CATEGORIES = List.of(
            ModTags.Items.FEAST_BREADS,
            ModTags.Items.FEAST_MAINS,
            ModTags.Items.FEAST_BOWLS,
            ModTags.Items.FEAST_DAIRY,
            ModTags.Items.FEAST_SWEETS,
            ModTags.Items.FEAST_DRINKS
    );

    private final NonNullList<ItemStack> servings = NonNullList.withSize(SERVING_SLOTS, ItemStack.EMPTY);

    public FeastTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.FEAST_TABLE.get(), pos, state);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    public List<ItemStack> getServings() {
        return List.copyOf(servings);
    }

    public ItemStack getServing(int slot) {
        return servings.get(slot);
    }

    public int getFoodCount() {
        return (int) servings.stream().filter(stack -> !stack.isEmpty()).count();
    }

    public Set<TagKey<Item>> getDetectedCategories() {
        return FEAST_CATEGORIES.stream()
                .filter(category -> servings.stream().anyMatch(stack -> stack.is(category)))
                .collect(Collectors.toUnmodifiableSet());
    }

    public int getVarietyCount() {
        return getDetectedCategories().size();
    }

    public boolean serve(ItemStack heldStack, Player player) {
        for (int slot = 0; slot < servings.size(); slot++) {
            if (servings.get(slot).isEmpty()) {
                servings.set(slot, heldStack.copyWithCount(1));
                if (!player.getAbilities().instabuild) heldStack.shrink(1);
                inventoryChanged();
                return true;
            }
        }
        return false;
    }

    public boolean removeLast(Player player) {
        for (int slot = servings.size() - 1; slot >= 0; slot--) {
            ItemStack serving = servings.get(slot);
            if (!serving.isEmpty()) {
                servings.set(slot, ItemStack.EMPTY);
                if (!player.getInventory().add(serving)) player.drop(serving, false);
                inventoryChanged();
                return true;
            }
        }
        return false;
    }

    public void dropContents() {
        if (level == null) return;
        servings.forEach(stack -> Containers.dropItemStack(level, worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), stack));
        servings.clear();
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        ContainerHelper.saveAllItems(tag, servings, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        servings.clear();
        ContainerHelper.loadAllItems(tag, servings, registries);
    }
}
