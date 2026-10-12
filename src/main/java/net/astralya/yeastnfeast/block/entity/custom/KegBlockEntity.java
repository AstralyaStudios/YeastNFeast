package net.astralya.yeastnfeast.block.entity.custom;

import net.astralya.yeastnfeast.block.entity.ModBlockEntityTypes;
import net.astralya.yeastnfeast.item.ModItems;
import net.astralya.yeastnfeast.recipe.custom.KegRecipe;
import net.astralya.yeastnfeast.screen.KegMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class KegBlockEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemHandler = new ItemStackHandler(6) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            Item item = stack.getItem();

            return switch (slot) {
                case 3 -> false;
                case 4 -> item == ModItems.YEAST.get() || item == Items.SUGAR;
                case 5 -> item == ModItems.TANKARD.get() || item == ModItems.JAR.get();
                default -> item != ModItems.YEAST.get()
                        && item != ModItems.JAR.get()
                        && item != ModItems.TANKARD.get()
                        && item != Items.SUGAR
                        && super.isItemValid(slot, stack);
            };
        }


    };

    public static final int INPUT_SLOT_1 = 0;
    public static final int INPUT_SLOT_2 = 1;
    public static final int INPUT_SLOT_3 = 2;
    public static final int OUTPUT_SLOT = 3;
    public static final int YEAST_SLOT = 4;
    public static final int TANKARD_SLOT = 5;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 7200;

    public KegBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntityTypes.KEG.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> KegBlockEntity.this.progress;
                    case 1 -> KegBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> KegBlockEntity.this.progress = pValue;
                    case 1 -> KegBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.yeastnfeast.keg");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new KegMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return  lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
    }

    public void tick(Level level, BlockPos pPos, BlockState pState) {
        if (isOutputSlotEmptyOrReceivable() && hasRecipe()) {
            Optional<KegRecipe> recipe = getCurrentRecipe();
            if (recipe.isPresent()) {
                this.maxProgress = recipe.get().getBrewTime();
            }

            increaseCraftingProgress();
            setChanged(level, pPos, pState);
            if (hasProgressFinished()){
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }


    private void craftItem() {
        Optional<KegRecipe> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return;

        ItemStack resultItem = recipe.get().getResultItem(getLevel().registryAccess());

        NonNullList<Ingredient> ingredients = recipe.get().getIngredients();
        boolean[] slotUsed = new boolean[3];

        for (Ingredient ingredient : ingredients) {
            for (int i = 0; i < 3; i++) {
                if (!slotUsed[i] && ingredient.test(itemHandler.getStackInSlot(INPUT_SLOT_1 + i))) {
                    slotUsed[i] = true;
                    break;
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            if (slotUsed[i]) {
                itemHandler.extractItem(INPUT_SLOT_1 + i, 1, false);
            }
        }

        itemHandler.extractItem(TANKARD_SLOT, 1, false);
        itemHandler.extractItem(YEAST_SLOT, 1, false);

        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(resultItem.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + resultItem.getCount()));

        if (!level.isClientSide()) {
            level.playSound(null, getBlockPos(), SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 1.0f, 1.0f);
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private boolean hasProgressFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean hasRecipe() {
        Optional<KegRecipe> recipe = getCurrentRecipe();

        if (recipe.isEmpty()){
            return false;
        }

        ItemStack resultItem = recipe.get().getResultItem(getLevel().registryAccess());

        boolean canInsert = canInsertAmountIntoOutputSlot(resultItem.getCount());
        boolean canInsertItem = canInsertItemIntoOutputSlot(resultItem.getItem());
        boolean hasIngredients = hasRequiredIngredients(recipe.get());

        return canInsert && canInsertItem && hasIngredients;
    }

    private Optional<KegRecipe> getCurrentRecipe() {
        SimpleContainer inventory = new SimpleContainer(6);

        for (int i = 0; i < 3; i++) {
            ItemStack stack = this.itemHandler.getStackInSlot(i);
            inventory.setItem(i, stack);
        }

        inventory.setItem(3, ItemStack.EMPTY);

        ItemStack yeastStack = this.itemHandler.getStackInSlot(YEAST_SLOT);
        ItemStack tankardStack = this.itemHandler.getStackInSlot(TANKARD_SLOT);

        inventory.setItem(4, yeastStack);
        inventory.setItem(5, tankardStack);

        return this.level.getRecipeManager().getRecipeFor(KegRecipe.Type.INSTANCE, inventory, level);
    }

    private boolean hasRequiredIngredients(KegRecipe recipe) {

        boolean yeastMatches = recipe.getYeastSlot()
                .test(this.itemHandler.getStackInSlot(YEAST_SLOT));
        if (!yeastMatches) return false;

        boolean tankardMatches = recipe.getTankardSlot()
                .test(this.itemHandler.getStackInSlot(TANKARD_SLOT));
        if (!tankardMatches) return false;

        int[] availableSlots = {INPUT_SLOT_1, INPUT_SLOT_2, INPUT_SLOT_3};
        boolean[] used = new boolean[3];
        NonNullList<Ingredient> ingredients = recipe.getIngredients();

        for (int i = 0; i < ingredients.size(); i++) {
            Ingredient ingredient = ingredients.get(i);
            boolean matched = false;

            for (int j = 0; j < availableSlots.length; j++) {
                if (used[j]) continue;

                ItemStack slotStack = this.itemHandler.getStackInSlot(availableSlots[j]);
                boolean ingredientMatches = ingredient.test(slotStack);

                if (ingredientMatches) {
                    used[j] = true;
                    matched = true;
                    break;
                }
            }

            if (!matched) {
                return false;
            }
        }

        return true;
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() ||
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize() >=
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count;
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() ||
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() < this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }
}
