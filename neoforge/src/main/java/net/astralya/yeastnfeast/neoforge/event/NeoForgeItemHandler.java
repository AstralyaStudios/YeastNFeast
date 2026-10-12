package net.astralya.yeastnfeast.neoforge.event;

import net.astralya.yeastnfeast.util.inventory.ModItemHandler;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;

public final class NeoForgeItemHandler implements IItemHandler {
    private final ModItemHandler delegate;

    public NeoForgeItemHandler(ModItemHandler delegate) {
        this.delegate = delegate;
    }

    @Override public int getSlots() { return delegate.getSlots(); }
    @Override public ItemStack getStackInSlot(int slot) { return delegate.getStackInSlot(slot); }
    @Override public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) { return delegate.insertItem(slot, stack, simulate); }
    @Override public ItemStack extractItem(int slot, int amount, boolean simulate) { return delegate.extractItem(slot, amount, simulate); }
    @Override public int getSlotLimit(int slot) { return delegate.getSlotLimit(slot); }
    @Override public boolean isItemValid(int slot, ItemStack stack) { return delegate.isItemValid(slot, stack); }
}
