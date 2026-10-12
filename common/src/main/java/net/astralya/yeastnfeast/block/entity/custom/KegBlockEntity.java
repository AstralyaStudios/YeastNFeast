package net.astralya.yeastnfeast.block.entity.custom;

import dev.architectury.registry.menu.ExtendedMenuProvider;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.astralya.yeastnfeast.block.entity.ModBlockEntityTypes;
import net.astralya.yeastnfeast.recipe.KegRecipe;
import net.astralya.yeastnfeast.util.ModTags;
import net.astralya.yeastnfeast.particle.ModParticleType;
import net.astralya.yeastnfeast.recipe.ModRecipes;
import net.astralya.yeastnfeast.screen.custom.KegMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.astralya.yeastnfeast.util.inventory.ModItemHandler;
import net.astralya.yeastnfeast.util.inventory.ModItemStackHandler;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Optional;

@ParametersAreNonnullByDefault
public class KegBlockEntity extends SyncBlockEntity implements MenuProvider, ExtendedMenuProvider {

    public final ModItemStackHandler inventory = new ModItemStackHandler(6) {
        @Override
        public boolean canPlaceItem(int slot, ItemStack stack) {
            return slot != OUTPUT_SLOT && (slot != TANKARD_SLOT || isValidServingContainer(stack));
        }

        @Override
        protected void onContentsChanged(int slot) {
            inventoryChanged();
        }
    };

    private final ModItemHandler southInputHandler = new SlotsView(inventory,
            new int[] { INPUT_SLOT_1, INPUT_SLOT_2, INPUT_SLOT_3, YEAST_SLOT },
            true,
            false
    );

    public static final int INPUT_SLOT_1 = 0;
    public static final int INPUT_SLOT_2 = 1;
    public static final int INPUT_SLOT_3 = 2;
    public static final int YEAST_SLOT = 3;
    public static final int TANKARD_SLOT = 4;
    public static final int OUTPUT_SLOT = 5;

    private final ContainerData data;
    private int progress = 0;
    private int maxProgress = 200;
    private boolean ready = false;
    private ItemStack batchResult = ItemStack.EMPTY;
    private Ingredient extractionContainer = Ingredient.EMPTY;
    private int servings = 0;
    private int maxServings = 1;
    private int liquidColor = 0;

    @Nullable private Player lastInteractedPlayer;

    private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();

    public KegBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.KEG.get(), pos, state);
        this.data = new ContainerData() {
            @Override public int get(int index) {
                return switch (index) {
                    case 0 -> progress;
                    case 1 -> maxProgress;
                    case 2 -> ready ? 1 : 0;
                    case 3 -> servings;
                    case 4 -> maxServings;
                    default -> 0;
                };
            }
            @Override public void set(int index, int value) {
                if (index == 0) progress = value;
                else if (index == 1) maxProgress = value;
                else if (index == 2) ready = value != 0;
                else if (index == 3) servings = value;
                else if (index == 4) maxServings = value;
            }
            @Override public int getCount() { return 5; }
        };
    }

    public ModItemHandler getItemHandler(Direction direction) {

        if (direction == Direction.SOUTH) {
            return southInputHandler;
        }
        return EmptyHandler.INSTANCE;
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        if (level.isClientSide()) return;
        if (ready && !batchResult.isEmpty()) {
            dispenseServing();
            return;
        }
        if (hasRecipe()) {
            if (ready) {
                finishBatch();
                return;
            }
            progress++;
            setChanged(level, pos, state);
            if (progress >= maxProgress) {
                progress = maxProgress;
                finishBatch();
                inventoryChanged();
            } else if (level instanceof ServerLevel serverLevel) {
                if (level.random.nextInt(40) == 0) {
                    serverLevel.sendParticles(ModParticleType.FERMENTATION_BUBBLES.get(),
                            pos.getX() + 0.5 + (level.random.nextDouble() - 0.5) * 0.5, pos.getY() + 1.02,
                            pos.getZ() + 0.5 + (level.random.nextDouble() - 0.5) * 0.5, 1, 0, 0, 0, 0);
                }
                if (progress % 100 == 0 && level.random.nextBoolean()) {
                    level.playSound(null, pos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS,
                            0.35F, 0.9F + level.random.nextFloat() * 0.2F);
                }
            }
        } else {
            resetProgress();
        }
    }

    private void finishBatch() {
        level.getRecipeManager().getRecipeFor(ModRecipes.KEG_TYPE.get(), inventory, level).ifPresent(holder -> {
            KegRecipe recipe = holder.value();
            batchResult = recipe.getResultItem(level.registryAccess()).copy();
            servings = maxServings = recipe.getServings();
            batchResult.setCount(1);
            liquidColor = recipe.getLiquidColor();
            extractionContainer = recipe.getExtractionContainer();
            ready = true;
            for (int slot = 0; slot <= YEAST_SLOT; slot++) inventory.removeItem(slot, 1);
            inventoryChanged();
        });
    }

    public boolean isValidServingContainer(ItemStack stack) {
        return stack.is(ModTags.Items.KEG_CONTAINERS);
    }

    private void dispenseServing() {
        if (!ready || batchResult.isEmpty() || servings <= 0
                || !isValidServingContainer(inventory.getItem(TANKARD_SLOT))) return;
        if (extractionContainer.isEmpty()) {
            // Recover batches saved before extraction requirements were stored, only when unambiguous.
            List<RecipeHolder<KegRecipe>> matches = level.getRecipeManager().getAllRecipesFor(ModRecipes.KEG_TYPE.get())
                    .stream().filter(holder -> ItemStack.isSameItemSameComponents(
                            holder.value().getResultItem(level.registryAccess()), batchResult)).toList();
            if (matches.size() != 1) return;
            extractionContainer = matches.getFirst().value().getExtractionContainer();
            setChanged();
        }
        if (!extractionContainer.test(inventory.getItem(TANKARD_SLOT))) return;
        ItemStack output = inventory.getItem(OUTPUT_SLOT);
        if (!output.isEmpty() && (!ItemStack.isSameItemSameComponents(output, batchResult)
                || output.getCount() >= Math.min(output.getMaxStackSize(), inventory.getSlotLimit(OUTPUT_SLOT)))) return;
        inventory.removeItem(TANKARD_SLOT, 1);
        inventory.setItem(OUTPUT_SLOT, batchResult.copyWithCount(output.getCount() + 1));
        servings--;
        if (servings == 0) {
            batchResult = ItemStack.EMPTY;
            extractionContainer = Ingredient.EMPTY;
            liquidColor = 0;
            resetProgress();
        }
        inventoryChanged();
    }

    public ItemStack getBatchResult() { return batchResult.copy(); }

    public int getLiquidColor() { return liquidColor; }

    private void resetProgress() {
        progress = 0;
        maxProgress = 200;
        ready = false;
    }

    private void addRecipeUsed(RecipeHolder<?> holder) {
        recipesUsed.addTo(holder.id(), 1);
    }

    private boolean hasRecipe() {
        Level level = getLevel();
        ModItemStackHandler wrapper = inventory;
        Optional<RecipeHolder<KegRecipe>> match =
                level.getRecipeManager().getRecipeFor(ModRecipes.KEG_TYPE.get(), wrapper, level);

        if (match.isEmpty()) return false;

        KegRecipe recipe = match.get().value();
        maxProgress = recipe.getBrewTime();
        return true;
    }

    public void dropExperienceForRecipesUsed(ServerPlayer player) {
        Level level = getLevel();
        if (!(level instanceof ServerLevel server)) return;

        recipesUsed.object2IntEntrySet().forEach(e -> {
            ResourceLocation id = e.getKey();
            int crafts = e.getIntValue();
            server.getRecipeManager().byKey(id).ifPresent(rec -> {
                if (rec.value() instanceof KegRecipe keg) {
                    float total = crafts * keg.getExperience();
                    int whole = Mth.floor(total);
                    float frac = total - whole;
                    if (frac > 0 && server.random.nextFloat() < frac) whole++;
                    if (whole > 0) ExperienceOrb.award(server, player.position(), whole);
                    player.awardRecipes(List.of(rec));
                }
            });
        });

        recipesUsed.clear();
    }

    public void dropExperienceForRecipesUsed() {
        if (lastInteractedPlayer instanceof ServerPlayer sp) {
            dropExperienceForRecipesUsed(sp);
        }
    }

    @Override public Component getDisplayName() {
        return Component.translatable("block.yeastnfeast.keg");
    }

    @Override public @Nullable AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new KegMenu(id, inv, this, data);
    }

    @Override
    public void saveExtraData(net.minecraft.network.FriendlyByteBuf buf) {
        buf.writeBlockPos(this.worldPosition);
    }

    @Override protected void saveAdditional(CompoundTag tag, HolderLookup.Provider regs) {
        tag.put("inventory", inventory.serializeNBT(regs));
        tag.putInt("keg.progress", progress);
        tag.putInt("keg.max_progress", maxProgress);
        tag.putBoolean("keg.ready", ready);
        if (!batchResult.isEmpty()) tag.put("keg.batch_result", batchResult.save(regs));
        if (!extractionContainer.isEmpty()) Ingredient.CODEC_NONEMPTY.encodeStart(regs.createSerializationContext(NbtOps.INSTANCE),
                extractionContainer).result().ifPresent(value -> tag.put("keg.extraction_container", value));
        tag.putInt("keg.servings", servings);
        tag.putInt("keg.max_servings", maxServings);
        tag.putInt("keg.liquid_color", liquidColor);
        CompoundTag used = new CompoundTag();
        recipesUsed.forEach((rl, count) -> used.putInt(rl.toString(), count));
        tag.put("RecipesUsed", used);
        super.saveAdditional(tag, regs);
    }

    @Override protected void loadAdditional(CompoundTag tag, HolderLookup.Provider regs) {
        super.loadAdditional(tag, regs);
        inventory.deserializeNBT(regs, tag.getCompound("inventory"));
        progress = tag.getInt("keg.progress");
        maxProgress = tag.getInt("keg.max_progress");
        ready = tag.getBoolean("keg.ready");
        batchResult = ItemStack.parseOptional(regs, tag.getCompound("keg.batch_result"));
        extractionContainer = tag.contains("keg.extraction_container")
                ? Ingredient.CODEC_NONEMPTY.parse(regs.createSerializationContext(NbtOps.INSTANCE),
                        tag.get("keg.extraction_container")).result().orElse(Ingredient.EMPTY) : Ingredient.EMPTY;
        if (!batchResult.isEmpty()) batchResult.setCount(1);
        maxServings = Math.max(1, tag.getInt("keg.max_servings"));
        servings = ready && !batchResult.isEmpty() ? Mth.clamp(tag.getInt("keg.servings"), 0, maxServings) : 0;
        liquidColor = tag.getInt("keg.liquid_color") & 0xFFFFFF;
        recipesUsed.clear();
        CompoundTag used = tag.getCompound("RecipesUsed");
        for (String k : used.getAllKeys()) {
            recipesUsed.put(ResourceLocation.parse(k), used.getInt(k));
        }
    }

    @Override public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override public CompoundTag getUpdateTag(HolderLookup.Provider regs) {
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
