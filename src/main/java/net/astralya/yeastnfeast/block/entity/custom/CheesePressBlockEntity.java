package net.astralya.yeastnfeast.block.entity.custom;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.astralya.yeastnfeast.block.custom.CheesePressBlock;
import net.astralya.yeastnfeast.block.entity.ModBlockEntityTypes;
import net.astralya.yeastnfeast.item.ModItems;
import net.astralya.yeastnfeast.recipe.CheesePressRecipe;
import net.astralya.yeastnfeast.recipe.ModRecipes;
import net.astralya.yeastnfeast.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.Collections;
import java.util.Optional;

public class CheesePressBlockEntity extends SyncBlockEntity implements SidedInventory {

    private static final DustParticleEffect PRESS_DUST = new DustParticleEffect(new Vector3f(1.0f, 0.9f, 0.2f), 1.0f);

    private final Object2IntOpenHashMap<Identifier> recipesUsed = new Object2IntOpenHashMap<>();

    public static final int MILK_SLOT = 0;
    public static final int RENNET_SLOT = 1;
    public static final int FLAVOR_SLOT = 2;
    public static final int OUTPUT_SLOT = 3;

    private static final int SIZE = 4;

    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(SIZE, ItemStack.EMPTY);

    private int progress;
    private int maxProgress = 1200;
    private boolean pressing;

    @Nullable
    private PlayerEntity lastInteractedPlayer;

    public CheesePressBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.CHEESE_PRESS, pos, state);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient) {
            return;
        }

        if (!pressing) {
            return;
        }

        Optional<CheesePressRecipe> match = getMatchingRecipe(world);
        if (match.isEmpty()) {
            setPressing(false);
            resetProgress();
            return;
        }

        CheesePressRecipe recipe = match.get();
        maxProgress = MathHelper.clamp(recipe.getPressTime(), 0, Integer.MAX_VALUE);

        progress++;
        markDirtyAndSync();

        if (world instanceof ServerWorld sw && progress % 4 == 0) {
            float t = (progress % 40) / 40.0f;
            double ang = t * Math.PI * 2.0;
            double r = 0.22 + 0.06 * Math.sin(progress * 0.15);
            double cx = pos.getX() + 0.5;
            double cy = pos.getY() + 0.85;
            double cz = pos.getZ() + 0.5;
            double px = cx + r * Math.cos(ang);
            double pz = cz + r * Math.sin(ang);

            sw.spawnParticles(PRESS_DUST, px, cy, pz, 1, 0.0, 0.01, 0.0, 0.0);
        }

        if (progress >= maxProgress) {
            craftItem(world, recipe);
            resetProgress();
            setPressing(false);
        }
    }

    public boolean tryStartPressing() {
        if (pressing) {
            return false;
        }

        World w = getWorld();
        if (w == null) {
            return false;
        }

        if (!hasRecipe(w)) {
            return false;
        }

        progress = 0;
        setPressing(true);

        if (!w.isClient) {
            w.playSound(null, pos, SoundEvents.BLOCK_PISTON_CONTRACT, SoundCategory.BLOCKS, 0.6f, 0.9f);
        }

        return true;
    }

    public boolean insertOne(PlayerEntity player, Hand hand) {
        if (pressing) {
            return false;
        }

        ItemStack held = player.getStackInHand(hand);
        if (held.isEmpty()) {
            return false;
        }

        for (int slot = MILK_SLOT; slot <= FLAVOR_SLOT; slot++) {
            if (items.get(slot).isEmpty()) {
                ItemStack one = held.copyWithCount(1);
                setStack(slot, one);

                if (!player.getAbilities().creativeMode) {
                    held.decrement(1);
                }

                World w = getWorld();
                if (w != null) {
                    w.playSound(null, pos, SoundEvents.ENTITY_ITEM_FRAME_ADD_ITEM, SoundCategory.BLOCKS, 0.5f, 1.0f);
                }

                markDirtyAndSync();
                return true;
            }
        }

        return false;
    }

    public ItemStack extractOne(PlayerEntity player) {
        if (pressing) {
            return ItemStack.EMPTY;
        }

        ItemStack out = items.get(OUTPUT_SLOT);
        if (!out.isEmpty()) {
            ItemStack give = out.copyWithCount(1);
            removeStack(OUTPUT_SLOT, 1);

            if (!player.getInventory().insertStack(give)) {
                player.dropItem(give, false);
            }

            World w = getWorld();
            if (w != null) {
                w.playSound(null, pos, SoundEvents.ENTITY_ITEM_FRAME_REMOVE_ITEM, SoundCategory.BLOCKS, 0.5f, 1.0f);
            }

            markDirtyAndSync();
            return give;
        }

        for (int slot = FLAVOR_SLOT; slot >= MILK_SLOT; slot--) {
            ItemStack s = items.get(slot);
            if (!s.isEmpty()) {
                ItemStack give = s.copyWithCount(1);
                removeStack(slot, 1);

                if (!player.getInventory().insertStack(give)) {
                    player.dropItem(give, false);
                }

                World w = getWorld();
                if (w != null) {
                    w.playSound(null, pos, SoundEvents.ENTITY_ITEM_FRAME_REMOVE_ITEM, SoundCategory.BLOCKS, 0.5f, 1.0f);
                }

                markDirtyAndSync();
                return give;
            }
        }

        return ItemStack.EMPTY;
    }

    private void resetProgress() {
        progress = 0;
        markDirtyAndSync();
    }

    public void setPressing(boolean value) {
        if (pressing == value) {
            return;
        }

        pressing = value;

        World w = getWorld();
        if (w != null && !w.isClient) {
            BlockState st = getCachedState();
            if (st.contains(CheesePressBlock.PRESSING)) {
                BlockState updated = st.with(CheesePressBlock.PRESSING, value);
                w.setBlockState(pos, updated, Block.NOTIFY_ALL);
                w.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(updated));
            }
        }

        markDirtyAndSync();
    }

    public boolean isPressing() {
        return pressing;
    }

    public void drops() {
        World w = getWorld();
        if (w == null) {
            return;
        }
        ItemScatterer.spawn(w, pos, this);
    }

    public void clearContents() {
        clear();
    }

    public void setLastInteractedPlayer(@Nullable PlayerEntity player) {
        lastInteractedPlayer = player;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, items);
        nbt.putInt("press.progress", progress);
        nbt.putInt("press.max_progress", maxProgress);
        nbt.putBoolean("press.active", pressing);

        NbtCompound used = new NbtCompound();
        recipesUsed.forEach((id, count) -> used.putInt(id.toString(), count));
        nbt.put("RecipesUsed", used);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Collections.fill(items, ItemStack.EMPTY);
        Inventories.readNbt(nbt, items);
        progress = nbt.getInt("press.progress");
        maxProgress = nbt.getInt("press.max_progress");
        pressing = nbt.getBoolean("press.active");

        recipesUsed.clear();
        NbtCompound used = nbt.getCompound("RecipesUsed");
        for (String key : used.getKeys()) {
            recipesUsed.put(new Identifier(key), used.getInt(key));
        }

        super.readNbt(nbt);
    }

    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    private Optional<CheesePressRecipe> getMatchingRecipe(World world) {
        SimpleInventory inv = new SimpleInventory(3);
        inv.setStack(0, items.get(MILK_SLOT).copy());
        inv.setStack(1, items.get(RENNET_SLOT).copy());
        inv.setStack(2, items.get(FLAVOR_SLOT).copy());

        return world.getRecipeManager().getFirstMatch(ModRecipes.CHEESE_PRESS_TYPE, inv, world);
    }

    private boolean hasRecipe(World world) {
        Optional<CheesePressRecipe> match = getMatchingRecipe(world);
        if (match.isEmpty()) {
            return false;
        }

        CheesePressRecipe recipe = match.get();

        ItemStack result = recipe.getOutput(null);
        maxProgress = MathHelper.clamp(recipe.getPressTime(), 0, Integer.MAX_VALUE);

        return canInsertItemIntoOutputSlot(result) && canInsertAmountIntoOutputSlot(result.getCount());
    }

    private void craftItem(World world, CheesePressRecipe recipe) {
        ItemStack result = recipe.getOutput(null);
        Ingredient flavor = recipe.getFlavorSlot();

        ItemStack current = items.get(OUTPUT_SLOT);

        if (current.isEmpty()) {
            items.set(OUTPUT_SLOT, result.copy());
        } else if (ItemStack.canCombine(current, result) && current.getCount() + result.getCount() <= current.getMaxCount()) {
            items.set(OUTPUT_SLOT, current.copyWithCount(current.getCount() + result.getCount()));
        } else {
            ItemScatterer.spawn(world, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, result.copy());
        }

        boolean[] used = new boolean[3];

        for (Ingredient ing : recipe.getIngredients()) {
            for (int i = 0; i < 3; i++) {
                if (!used[i] && ing.test(items.get(i))) {
                    decrementSlot(i);
                    used[i] = true;
                    break;
                }
            }
        }

        if (flavor != null && !flavor.isEmpty()) {
            for (int i = 0; i < 3; i++) {
                if (!used[i] && flavor.test(items.get(i))) {
                    decrementSlot(i);
                    used[i] = true;
                    break;
                }
            }
        }

        addRecipeUsed(recipe);

        world.playSound(null, pos, SoundEvents.BLOCK_BREWING_STAND_BREW, SoundCategory.BLOCKS, 0.8f, 0.9f);

        markDirtyAndSync();
    }

    private void addRecipeUsed(CheesePressRecipe recipe) {
        Identifier id = recipe.getId();
        recipesUsed.addTo(id, 1);
    }

    private void decrementSlot(int slot) {
        ItemStack s = items.get(slot);
        if (s.isEmpty()) {
            return;
        }
        int remain = s.getCount() - 1;
        items.set(slot, remain > 0 ? s.copyWithCount(remain) : ItemStack.EMPTY);
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        ItemStack out = items.get(OUTPUT_SLOT);
        return out.isEmpty() || ItemStack.canCombine(out, output);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        ItemStack out = items.get(OUTPUT_SLOT);
        int max = out.isEmpty() ? 64 : out.getMaxCount();
        return out.getCount() + count <= max;
    }

    private void markDirtyAndSync() {
        markDirty();
        World w = getWorld();
        if (w instanceof ServerWorld sw) {
            sw.getChunkManager().markForUpdate(pos);
            w.updateListeners(pos, getCachedState(), getCachedState(), 3);
        }
    }

    @Override
    public int size() {
        return SIZE;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack s : items) {
            if (!s.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getStack(int slot) {
        return slot >= 0 && slot < SIZE ? items.get(slot) : ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        if (slot < 0 || slot >= SIZE || amount <= 0) {
            return ItemStack.EMPTY;
        }
        ItemStack current = items.get(slot);
        if (current.isEmpty()) {
            return ItemStack.EMPTY;
        }
        ItemStack taken = current.split(amount);
        if (current.isEmpty()) {
            items.set(slot, ItemStack.EMPTY);
        }
        markDirtyAndSync();
        return taken;
    }

    @Override
    public ItemStack removeStack(int slot) {
        if (slot < 0 || slot >= SIZE) {
            return ItemStack.EMPTY;
        }
        ItemStack out = items.get(slot);
        items.set(slot, ItemStack.EMPTY);
        markDirtyAndSync();
        return out;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        if (slot < 0 || slot >= SIZE) {
            return;
        }

        ItemStack toSet = stack.isEmpty() ? ItemStack.EMPTY : stack.copy();

        if (slot <= FLAVOR_SLOT && !toSet.isEmpty()) {
            toSet = toSet.copyWithCount(1);
        }

        items.set(slot, toSet);

        ItemStack s = items.get(slot);
        if (!s.isEmpty() && s.getCount() > s.getMaxCount()) {
            items.set(slot, s.copyWithCount(s.getMaxCount()));
        }

        markDirtyAndSync();
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        World w = getWorld();
        if (w == null) {
            return false;
        }
        if (w.getBlockEntity(pos) != this) {
            return false;
        }
        return player.squaredDistanceTo(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5) <= 64.0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < SIZE; i++) {
            items.set(i, ItemStack.EMPTY);
        }
        markDirtyAndSync();
    }

    public DefaultedList<ItemStack> getInputItems() {
        DefaultedList<ItemStack> list = DefaultedList.ofSize(3, ItemStack.EMPTY);
        list.set(0, items.get(MILK_SLOT));
        list.set(1, items.get(RENNET_SLOT));
        list.set(2, items.get(FLAVOR_SLOT));
        return list;
    }

    public ItemStack getOutputItem() {
        return items.get(OUTPUT_SLOT);
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        if (side == Direction.UP) {
            return new int[]{MILK_SLOT, RENNET_SLOT, FLAVOR_SLOT};
        }
        if (side == Direction.DOWN) {
            return new int[]{OUTPUT_SLOT};
        }
        return new int[0];
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        if (dir != Direction.UP) {
            return false;
        }
        if (pressing) {
            return false;
        }
        if (stack.isEmpty()) {
            return false;
        }

        if (slot == MILK_SLOT) {
            return items.get(MILK_SLOT).isEmpty() && isMilk(stack);
        }

        if (slot == RENNET_SLOT) {
            return items.get(RENNET_SLOT).isEmpty() && isRennet(stack);
        }

        if (slot == FLAVOR_SLOT) {
            return items.get(FLAVOR_SLOT).isEmpty() && isFlavor(stack);
        }

        return false;
    }

    private boolean isMilk(ItemStack stack) {
        return stack.isIn(ModTags.Items.MILKS);
    }

    private boolean isRennet(ItemStack stack) {
        return stack.isOf(ModItems.RENNET);
    }

    private boolean isFlavor(ItemStack stack) {
        return stack.isIn(ModTags.Items.CHEESE_INGREDIENT);
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return dir == Direction.DOWN && slot == OUTPUT_SLOT && !pressing;
    }
}