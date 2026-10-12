package net.astralya.yeastnfeast.block.entity.custom;

import net.astralya.yeastnfeast.block.entity.ImplementedInventory;
import net.astralya.yeastnfeast.block.entity.ModBlockEntityTypes;
import net.astralya.yeastnfeast.recipe.ModRecipes;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.astralya.yeastnfeast.item.ModItems;
import net.astralya.yeastnfeast.recipe.KegRecipe;
import net.astralya.yeastnfeast.screen.KegScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;

public class KegBlockEntity extends SyncBlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(8, ItemStack.EMPTY);

    public static final int INPUT_SLOT_1 = 0;
    public static final int INPUT_SLOT_2 = 1;
    public static final int INPUT_SLOT_3 = 2;
    public static final int OUTPUT_SLOT = 3;
    public static final int YEAST_SLOT = 4;
    public static final int TANKARD_SLOT = 5;

    private int progress = 0;

    protected final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> KegBlockEntity.this.progress;
                case 1 -> getCurrentRecipe().map(KegRecipe::getBrewTime).orElse(0);
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> KegBlockEntity.this.progress = value;
            }
        }

        @Override
        public int size() {
            return 2;
        }
    };

    public KegBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.KEG, pos, state);
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketByteBuf packetByteBuf) {
        packetByteBuf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.yeastnfeast.keg");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new KegScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("keg.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("keg.progress");
        super.readNbt(nbt);
    }

    public void brewingTick(World world, BlockPos pos, BlockState state) {
        if (canInsertOutputSlot() && hasRecipe()) {
            increaseCraftingProgress();
            markDirty(world, pos, state);
            if (hasCraftingFinished()) {
                craftItem(world, pos);
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private void craftItem(World world, BlockPos pos) {
        getCurrentRecipe().ifPresent(recipe -> {
            removeStack(INPUT_SLOT_1, 1);
            removeStack(INPUT_SLOT_2, 1);
            removeStack(INPUT_SLOT_3, 1);
            removeStack(YEAST_SLOT, 1);
            removeStack(TANKARD_SLOT, 1);

            ItemStack output = recipe.getOutput(null);
            setStack(OUTPUT_SLOT, new ItemStack(output.getItem(), getStack(OUTPUT_SLOT).getCount() + output.getCount()));

            if (!world.isClient) {
                world.playSound(null, pos, SoundEvents.BLOCK_BREWING_STAND_BREW, SoundCategory.BLOCKS, 1.0f, 1.0f);
            }
        });
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private boolean hasCraftingFinished() {
        return this.progress >= getCurrentRecipe().map(KegRecipe::getBrewTime).orElse(0);
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean hasRecipe() {
        return getCurrentRecipe().map(recipe -> {
            ItemStack output = recipe.getOutput(null);
            return canInsertAmountIntoOutputSlot(output.getCount())
                    && canInsertItemIntoOutputSlot(output)
                    && hasRequiredIngredients(recipe);
        }).orElse(false);
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.getStack(OUTPUT_SLOT).getMaxCount() >= this.getStack(OUTPUT_SLOT).getCount() + count;
    }

    private Optional<KegRecipe> getCurrentRecipe() {
        SimpleInventory inventory = new SimpleInventory(this.size());
        for (int i = 0; i < this.size(); i++) {
            inventory.setStack(i, this.getStack(i));
        }
        return Objects.requireNonNull(this.getWorld()).getRecipeManager().getFirstMatch(ModRecipes.KEG_TYPE, inventory, this.getWorld());
    }

    private boolean hasRequiredIngredients(KegRecipe recipe) {
        boolean hasYeast = recipe.getYeastSlot().test(this.getStack(YEAST_SLOT));
        boolean hasTankard = recipe.getTankardSlot().test(this.getStack(TANKARD_SLOT));
        if (!hasYeast || !hasTankard) {
            return false;
        }

        DefaultedList<Ingredient> ingredients = recipe.getIngredients();
        for (Ingredient ingredient : ingredients) {
            boolean foundIngredient = false;
            for (int i = INPUT_SLOT_1; i <= INPUT_SLOT_3; i++) {
                if (ingredient.test(this.getStack(i))) {
                    foundIngredient = true;
                    break;
                }
            }
            if (!foundIngredient) {
                return false;
            }
        }

        return true;
    }

    private boolean canInsertOutputSlot() {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getCount() < this.getStack(OUTPUT_SLOT).getMaxCount();
    }

    public static class YeastSlot extends Slot {
        public YeastSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        @Override
        public int getMaxItemCount(ItemStack stack) {
            return 64;
        }

        @Override
        public boolean canInsert(ItemStack stack) {
            return stack.getItem() == ModItems.YEAST || stack.getItem() == Items.SUGAR;
        }
    }

    public static class TankardSlot extends Slot {
        public TankardSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        @Override
        public int getMaxItemCount(ItemStack stack) {
            return 16;
        }

        @Override
        public boolean canInsert(ItemStack stack) {
            return stack.getItem() == ModItems.TANKARD || stack.getItem() == ModItems.JAR;
        }
    }

    public static class IngredientSlot extends Slot {
        public IngredientSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        @Override
        public boolean canInsert(ItemStack stack) {
            return stack.getItem() != ModItems.YEAST && stack.getItem() != ModItems.TANKARD
                    && stack.getItem() != ModItems.JAR && stack.getItem() != Items.SUGAR;
        }
    }
}