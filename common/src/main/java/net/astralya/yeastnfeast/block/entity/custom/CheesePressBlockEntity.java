package net.astralya.yeastnfeast.block.entity.custom;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.astralya.yeastnfeast.block.custom.CheesePressBlock;
import net.astralya.yeastnfeast.block.entity.ModBlockEntityTypes;
import net.astralya.yeastnfeast.item.ModItems;
import net.astralya.yeastnfeast.recipe.CheesePressRecipe;
import net.astralya.yeastnfeast.recipe.ModRecipes;
import net.astralya.yeastnfeast.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.astralya.yeastnfeast.util.inventory.ModItemHandler;
import net.astralya.yeastnfeast.util.inventory.ModItemStackHandler;
import org.joml.Vector3f;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;

@ParametersAreNonnullByDefault
public class CheesePressBlockEntity extends SyncBlockEntity {

    public final ModItemStackHandler inventory = new ModItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) {
            inventoryChanged();
        }

        @Override
        public int getSlotLimit(int slot) {
            if (slot == MILK_SLOT || slot == RENNET_SLOT || slot == FLAVOR_SLOT) return 1;
            return super.getSlotLimit(slot);
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            if (pressing) return false;

            if (slot == MILK_SLOT) {
                return isMilk(stack);
            }
            if (slot == RENNET_SLOT) {
                return isRennet(stack);
            }
            if (slot == FLAVOR_SLOT) {
                return isFlavor(stack);
            }
            return false;
        }

        @Override
        public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
            if (stack.isEmpty()) return ItemStack.EMPTY;

            if (slot == MILK_SLOT || slot == RENNET_SLOT || slot == FLAVOR_SLOT) {
                if (!isItemValid(slot, stack)) return stack;
                if (!getStackInSlot(slot).isEmpty()) return stack;

                ItemStack one = stack.copy();
                one.setCount(1);

                ItemStack remainder = stack.copy();
                remainder.shrink(1);

                if (!simulate) {
                    setStackInSlot(slot, one);
                }

                return remainder;
            }

            if (slot == OUTPUT_SLOT) {
                return stack;
            }

            return super.insertItem(slot, stack, simulate);
        }
    };

    private final ModItemHandler topInputHandler = new SlotsView(inventory,
            new int[] { MILK_SLOT, RENNET_SLOT, FLAVOR_SLOT },
            true,
            false
    );

    private final ModItemHandler bottomOutputHandler = new SlotsView(inventory,
            new int[] { OUTPUT_SLOT },
            false,
            true
    );

    public static final int MILK_SLOT = 0;
    public static final int RENNET_SLOT = 1;
    public static final int FLAVOR_SLOT = 2;
    public static final int OUTPUT_SLOT = 3;

    private int progress = 0;
    private int maxProgress = 1200;
    private boolean pressing = false;

    @Nullable private Player lastInteractedPlayer;

    public ModItemHandler getItemHandler(Direction direction) {
        if (direction == null) return inventory;

        if (direction == Direction.UP) {
            return topInputHandler;
        }
        if (direction == Direction.DOWN) {
            return bottomOutputHandler;
        }

        return EmptyHandler.INSTANCE;
    }

    private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();

    public CheesePressBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.CHEESE_PRESS.get(), pos, state);
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        if (level.isClientSide()) return;

        if (pressing) {
            if (!hasRecipe()) {
                setPressing(false);
                resetProgress();
                return;
            }

            progress++;
            setChanged(level, pos, state);

            if (level instanceof ServerLevel server && progress % 4 == 0) {
                float t = (progress % 40) / 40.0f;
                double ang = t * Math.PI * 2.0;
                double r = 0.22 + 0.06 * Math.sin(progress * 0.15);
                double cx = pos.getX() + 0.5;
                double cy = pos.getY() + 0.85;
                double cz = pos.getZ() + 0.5;
                double px = cx + r * Math.cos(ang);
                double pz = cz + r * Math.sin(ang);
                server.sendParticles(new DustParticleOptions(new Vector3f(1.0f, 0.9f, 0.2f), 1.0f),
                        px, cy, pz, 1, 0.0, 0.01, 0.0, 0.0);
            }

            if (progress >= maxProgress) {
                craftItem();
                resetProgress();
                setPressing(false);
            }
        }
    }

    public boolean tryStartPressing() {
        if (pressing) return false;
        if (!hasRecipe()) return false;
        progress = 0;
        setPressing(true);
        if (level != null && !level.isClientSide()) {
            level.playSound(null, worldPosition, SoundEvents.PISTON_CONTRACT, SoundSource.BLOCKS, 0.6f, 0.9f);
        }
        return true;
    }

    private void stopPressing() {
        pressing = false;
        progress = 0;
        if (level != null && !level.isClientSide()) {
            BlockState st = getBlockState();
            if (st.hasProperty(CheesePressBlock.PRESSING) && st.getValue(CheesePressBlock.PRESSING)) {
                level.setBlockAndUpdate(worldPosition, st.setValue(CheesePressBlock.PRESSING, false));
            }
            inventoryChanged();
        }
    }

    public boolean insertOne(Player player, InteractionHand hand) {
        if (pressing) return false;
        ItemStack held = player.getItemInHand(hand);
        if (held.isEmpty()) return false;
        for (int slot = 0; slot <= FLAVOR_SLOT; slot++) {
            if (inventory.getStackInSlot(slot).isEmpty()) {
                ItemStack one = held.copy();
                one.setCount(1);
                inventory.setStackInSlot(slot, one);
                if (!player.getAbilities().instabuild) held.shrink(1);
                if (level != null) level.playSound(null, worldPosition, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS, 0.5f, 1.0f);
                if (level != null && !level.isClientSide()) inventoryChanged();
                return true;
            }
        }
        return false;
    }

    public ItemStack extractOne(Player player) {
        ItemStack out = inventory.getStackInSlot(OUTPUT_SLOT);
        if (!out.isEmpty() && !pressing) {
            ItemStack give = out.copy();
            give.setCount(1);
            inventory.extractItem(OUTPUT_SLOT, 1, false);
            if (!player.getInventory().add(give)) player.drop(give, false);
            if (level != null) level.playSound(null, worldPosition, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 0.5f, 1.0f);
            if (level != null && !level.isClientSide()) inventoryChanged();
            return give;
        }
        if (!pressing) {
            for (int slot = FLAVOR_SLOT; slot >= MILK_SLOT; slot--) {
                ItemStack s = inventory.getStackInSlot(slot);
                if (!s.isEmpty()) {
                    ItemStack give = s.copy();
                    give.setCount(1);
                    inventory.extractItem(slot, 1, false);
                    if (!player.getInventory().add(give)) player.drop(give, false);
                    if (level != null) level.playSound(null, worldPosition, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 0.5f, 1.0f);
                    if (level != null && !level.isClientSide()) inventoryChanged();
                    return give;
                }
            }
        }
        return ItemStack.EMPTY;
    }

    private void resetProgress() {
        progress = 0;
        if (!hasRecipe()) {
            maxProgress = 0;
        }
    }

    public void setPressing(boolean value) {
        if (pressing == value) return;
        pressing = value;
        if (level != null && !level.isClientSide()) {
            BlockState st = getBlockState();
            if (st.hasProperty(CheesePressBlock.PRESSING)) {
                level.setBlockAndUpdate(worldPosition, st.setValue(CheesePressBlock.PRESSING, value));
            }
            inventoryChanged();
        }
    }

    public NonNullList<ItemStack> getInputItems() {
        NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);
        items.set(0, inventory.getStackInSlot(MILK_SLOT));
        items.set(1, inventory.getStackInSlot(RENNET_SLOT));
        items.set(2, inventory.getStackInSlot(FLAVOR_SLOT));
        return items;
    }

    public ItemStack getOutputItem() {
        return inventory.getStackInSlot(OUTPUT_SLOT);
    }

    public boolean isPressing() {
        return pressing;
    }

    protected void craftItem() {
        Level lvl = getLevel();
        ModItemStackHandler wrapper = inventory;
        Optional<RecipeHolder<CheesePressRecipe>> match = lvl.getRecipeManager().getRecipeFor(ModRecipes.CHEESE_PRESS_TYPE.get(), wrapper, lvl);
        if (match.isEmpty()) return;

        CheesePressRecipe recipe = match.get().value();
        ItemStack result = recipe.getResultItem(lvl.registryAccess());
        ItemStack current = inventory.getStackInSlot(OUTPUT_SLOT);

        if (current.isEmpty()) {
            inventory.setStackInSlot(OUTPUT_SLOT, result.copy());
        } else if (ItemStack.isSameItemSameComponents(current, result) && current.getCount() + result.getCount() <= current.getMaxStackSize()) {
            ItemStack merged = current.copy();
            merged.grow(result.getCount());
            inventory.setStackInSlot(OUTPUT_SLOT, merged);
        } else {
            Containers.dropItemStack(lvl, worldPosition.getX() + 0.5, worldPosition.getY() + 1.0, worldPosition.getZ() + 0.5, result.copy());
        }

        boolean[] used = new boolean[3];

        for (Ingredient ing : recipe.getIngredients()) {
            for (int i = 0; i < 3; i++) {
                if (!used[i] && ing.test(inventory.getStackInSlot(i))) {
                    inventory.extractItem(i, 1, false);
                    used[i] = true;
                    break;
                }
            }
        }

        Ingredient flavor = recipe.getFlavorSlot();
        if (!flavor.isEmpty()) {
            for (int i = 0; i < 3; i++) {
                if (!used[i] && flavor.test(inventory.getStackInSlot(i))) {
                    inventory.extractItem(i, 1, false);
                    used[i] = true;
                    break;
                }
            }
        }

        addRecipeUsed(match.get());
        lvl.playSound(null, worldPosition, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 0.8f, 0.9f);
        setChanged(lvl, worldPosition, getBlockState());
        if (!lvl.isClientSide()) inventoryChanged();
    }

    private void addRecipeUsed(RecipeHolder<?> holder) {
        recipesUsed.addTo(holder.id(), 1);
    }

    private boolean hasRecipe() {
        Level level = getLevel();
        ModItemStackHandler wrapper = inventory;
        Optional<RecipeHolder<CheesePressRecipe>> match =
                level.getRecipeManager().getRecipeFor(ModRecipes.CHEESE_PRESS_TYPE.get(), wrapper, level);
        if (match.isEmpty()) return false;
        CheesePressRecipe recipe = match.get().value();
        ItemStack result = recipe.getResultItem(level.registryAccess());
        maxProgress = recipe.getPressTime();
        return canInsertItemIntoOutputSlot(result) && canInsertAmountIntoOutputSlot(result.getCount());
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        ItemStack out = inventory.getStackInSlot(OUTPUT_SLOT);
        return out.isEmpty() || ItemStack.isSameItemSameComponents(out, output);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        ItemStack out = inventory.getStackInSlot(OUTPUT_SLOT);
        int max = out.isEmpty() ? 64 : out.getMaxStackSize();
        return out.getCount() + count <= max;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider regs) {
        tag.put("inventory", inventory.serializeNBT(regs));
        tag.putInt("press.progress", progress);
        tag.putInt("press.max_progress", maxProgress);
        tag.putBoolean("press.active", pressing);
        super.saveAdditional(tag, regs);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider regs) {
        super.loadAdditional(tag, regs);
        inventory.deserializeNBT(regs, tag.getCompound("inventory"));
        progress = tag.getInt("press.progress");
        maxProgress = tag.getInt("press.max_progress");
        pressing = tag.getBoolean("press.active");
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider regs) {
        return saveWithoutMetadata(regs);
    }

    public void drops() {
        if (level == null) return;
        SimpleContainer tmp = new SimpleContainer(inventory.getSlots());
        for (int i = 0; i < inventory.getSlots(); i++) tmp.setItem(i, inventory.getStackInSlot(i));
        Containers.dropContents(level, worldPosition, tmp);
    }

    public void clearContents() {
        for (int i = 0; i < inventory.getSlots(); i++) inventory.setStackInSlot(i, ItemStack.EMPTY);
    }

    public void setLastInteractedPlayer(Player p) { lastInteractedPlayer = p; }

    private static final class SlotsView implements ModItemHandler {
        private final ModItemStackHandler backing;
        private final int[] slots;
        private final boolean allowInsert;
        private final boolean allowExtract;

        private SlotsView(ModItemStackHandler backing, int[] slots, boolean allowInsert, boolean allowExtract) {
            this.backing = backing;
            this.slots = slots;
            this.allowInsert = allowInsert;
            this.allowExtract = allowExtract;
        }

        @Override
        public int getSlots() {
            return slots.length;
        }

        @Override
        public ItemStack getStackInSlot(int slot) {
            return backing.getStackInSlot(map(slot));
        }

        @Override
        public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
            if (!allowInsert) return stack;
            return backing.insertItem(map(slot), stack, simulate);
        }

        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            if (!allowExtract) return ItemStack.EMPTY;
            return backing.extractItem(map(slot), amount, simulate);
        }

        @Override
        public int getSlotLimit(int slot) {
            return backing.getSlotLimit(map(slot));
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            if (!allowInsert) return false;
            return backing.isItemValid(map(slot), stack);
        }

        private int map(int localSlot) {
            return slots[localSlot];
        }
    }

    private boolean isMilk(ItemStack stack) {
        return stack.is(ModTags.Items.FOODS_MILK);
    }

    private boolean isRennet(ItemStack stack) {
        return stack.is(ModItems.RENNET.get());
    }

    private boolean isFlavor(ItemStack stack) {
        return stack.is(ModTags.Items.CHEESE_INGREDIENT);
    }


    private enum EmptyHandler implements ModItemHandler {
        INSTANCE;

        @Override public int getSlots() { return 0; }
        @Override public ItemStack getStackInSlot(int slot) { return ItemStack.EMPTY; }
        @Override public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) { return stack; }
        @Override public ItemStack extractItem(int slot, int amount, boolean simulate) { return ItemStack.EMPTY; }
        @Override public int getSlotLimit(int slot) { return 0; }
        @Override public boolean isItemValid(int slot, ItemStack stack) { return false; }
    }
}
