package net.astralya.yeastnfeast.screen.custom;

import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.block.entity.custom.KegBlockEntity;
import net.astralya.yeastnfeast.util.ModTags;
import net.astralya.yeastnfeast.recipe.ModRecipes;
import net.astralya.yeastnfeast.screen.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class KegMenu extends AbstractContainerMenu {

    public final KegBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    public KegMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(5));
    }

    public KegMenu(int containerId, Inventory inv, BlockEntity blockEntity, ContainerData data) {
        super(ModMenuTypes.KEG_MENU.get(), containerId);
        this.blockEntity = (KegBlockEntity) blockEntity;
        this.level = inv.player.level();
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.addSlot(new Slot(this.blockEntity.inventory, 0, 14, 24) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return isValidIngredient(stack);
            }
        });
        this.addSlot(new Slot(this.blockEntity.inventory, 1, 32, 24) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return isValidIngredient(stack);
            }
        });
        this.addSlot(new Slot(this.blockEntity.inventory, 2, 50, 24) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return isValidIngredient(stack);
            }
        });

        this.addSlot(new Slot(this.blockEntity.inventory, 3, 32, 42) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return isValidFermentationIngredient(stack);
            }
        });

        this.addSlot(new Slot(this.blockEntity.inventory, KegBlockEntity.TANKARD_SLOT, 131, 12) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return KegMenu.this.blockEntity.isValidServingContainer(stack);
            }
        });
        this.addSlot(new Slot(this.blockEntity.inventory, KegBlockEntity.OUTPUT_SLOT, 151, 32) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });

        addDataSlots(data);
    }

    private boolean isValidIngredient(ItemStack stack) {
        if (stack.is(ModTags.Items.KEG_CONTAINERS)) return false;
        return !isValidFermentationIngredient(stack);
    }

    private boolean isValidFermentationIngredient(ItemStack stack) {
        return level.getRecipeManager().getAllRecipesFor(ModRecipes.KEG_TYPE.get()).stream()
                .anyMatch(recipe -> recipe.value().getYeastSlot().test(stack));
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        blockEntity.setLastInteractedPlayer(player);
    }

    public boolean isCrafting() {
        return data.get(0) > 0 && !isReady();
    }

    public boolean isReady() {
        return data.get(2) != 0;
    }

    public ItemStack getResultPreview() {
        return blockEntity.getBatchResult();
    }

    public int getServings() { return data.get(3); }

    public int getMaxServings() { return data.get(4); }

    public int getLiquidColor() { return blockEntity.getLiquidColor(); }

    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int progressBarSize = 27;
        return maxProgress > 0 ? (int) Math.clamp((long) progress * progressBarSize / maxProgress, 0, progressBarSize) : 0;
    }

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
    private static final int TE_INVENTORY_SLOT_COUNT = 6;

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot sourceSlot = slots.get(index);
        if (!sourceSlot.hasItem()) return ItemStack.EMPTY;
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            if (sourceStack.is(ModTags.Items.KEG_CONTAINERS)) {
                if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX + KegBlockEntity.TANKARD_SLOT,
                        TE_INVENTORY_FIRST_SLOT_INDEX + KegBlockEntity.TANKARD_SLOT + 1, false))
                    return ItemStack.EMPTY;
            } else if (isValidFermentationIngredient(sourceStack)) {
                if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX + 3, TE_INVENTORY_FIRST_SLOT_INDEX + 4, false))
                    return ItemStack.EMPTY;
            } else {
                if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX + 3, false))
                    return ItemStack.EMPTY;
            }
        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false))
                return ItemStack.EMPTY;
        } else {
            return ItemStack.EMPTY;
        }

        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }

        sourceSlot.onTake(player, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                player, ModBlocks.KEG.get());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
