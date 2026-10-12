package net.astralya.yeastnfeast.datagen;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.item.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.ConsumeItemTrigger;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.function.Consumer;

public class ModAdvancementsProvider implements ForgeAdvancementProvider.AdvancementGenerator {

    public static final ResourceLocation BACKGROUND =
            new ResourceLocation(YeastNFeastMod.MODID, "textures/block/maple_log.png");

    @Override
    public void generate(HolderLookup.Provider registries, Consumer<Advancement> saver, ExistingFileHelper existingFileHelper) {

        Advancement rootAdvancement = Advancement.Builder.advancement()
                .display(new DisplayInfo(new ItemStack(ModItems.YEAST.get()),
                        Component.translatable("advancements.yeastnfeast.root.title"),
                        Component.translatable("advancements.yeastnfeast.root.desc"),
                        BACKGROUND, FrameType.TASK,
                        true,true, false))
                .addCriterion("has_yeast", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.YEAST.get()))
                .save(saver, new ResourceLocation(YeastNFeastMod.MODID, "root"), existingFileHelper);

        Advancement kegAdvancement = Advancement.Builder.advancement()
                .display(new DisplayInfo(new ItemStack(ModBlocks.KEG.get()),
                        Component.translatable("advancements.yeastnfeast.keg.title"),
                        Component.translatable("advancements.yeastnfeast.keg.desc"),
                        BACKGROUND, FrameType.TASK,
                        true,true, false))
                .parent(rootAdvancement)
                .addCriterion("has_keg", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.KEG.get()))
                .save(saver, new ResourceLocation(YeastNFeastMod.MODID, "keg"), existingFileHelper);

        Advancement sweetAdvancement = Advancement.Builder.advancement()
                .display(new DisplayInfo(new ItemStack(ModItems.HONEY_MEAD.get()),
                        Component.translatable("advancements.yeastnfeast.sweet.title"),
                        Component.translatable("advancements.yeastnfeast.sweet.desc"),
                        BACKGROUND, FrameType.TASK,
                        true,true, false))
                .parent(kegAdvancement)
                .addCriterion("has_honey_mead", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HONEY_MEAD.get()))
                .addCriterion("has_molasses_mead", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MOLASSES_MEAD.get()))
                .save(saver, new ResourceLocation(YeastNFeastMod.MODID, "sweet"), existingFileHelper);

        Advancement sourAdvancement = Advancement.Builder.advancement()
                .display(new DisplayInfo(new ItemStack(ModItems.SOUR_MEAD.get()),
                        Component.translatable("advancements.yeastnfeast.sour.title"),
                        Component.translatable("advancements.yeastnfeast.sour.desc"),
                        BACKGROUND, FrameType.TASK,
                        true,true, false))
                .parent(kegAdvancement)
                .addCriterion("has_sour_mead", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SOUR_MEAD.get()))
                .addCriterion("has_thornberry_mead", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.THORNBERRY_MEAD.get()))
                .save(saver, new ResourceLocation(YeastNFeastMod.MODID, "sour"), existingFileHelper);

        Advancement floralAdvancement = Advancement.Builder.advancement()
                .display(new DisplayInfo(new ItemStack(ModItems.BLOSSOM_MEAD.get()),
                        Component.translatable("advancements.yeastnfeast.floral.title"),
                        Component.translatable("advancements.yeastnfeast.floral.desc"),
                        BACKGROUND, FrameType.TASK,
                        true,true, false))
                .parent(kegAdvancement)
                .addCriterion("has_blossom_mead", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BLOSSOM_MEAD.get()))
                .addCriterion("has_amber_mead", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.AMBER_MEAD.get()))
                .save(saver, new ResourceLocation(YeastNFeastMod.MODID, "floral"), existingFileHelper);

        Advancement jarAdvancement = Advancement.Builder.advancement()
                .display(new DisplayInfo(new ItemStack(ModItems.JAR.get()),
                        Component.translatable("advancements.yeastnfeast.jar.title"),
                        Component.translatable("advancements.yeastnfeast.jar.desc"),
                        BACKGROUND, FrameType.TASK,
                        true,true, false))
                .parent(kegAdvancement)
                .addCriterion("has_jar", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.JAR.get()))
                .save(saver, new ResourceLocation(YeastNFeastMod.MODID, "jar"), existingFileHelper);

        Advancement jamsAdvancement = Advancement.Builder.advancement()
                .display(new DisplayInfo(new ItemStack(ModItems.APPLE_JAM.get()),
                        Component.translatable("advancements.yeastnfeast.jam.title"),
                        Component.translatable("advancements.yeastnfeast.jam.desc"),
                        BACKGROUND, FrameType.CHALLENGE,
                        true,true, false))
                .parent(jarAdvancement)
                .addCriterion("has_apple_jam", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.APPLE_JAM.get()))
                .addCriterion("has_chorus_fruit_jam", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CHORUS_FRUIT_JAM.get()))
                .addCriterion("has_elderberries_jam", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELDERBERRIES_JAM.get()))
                .addCriterion("has_glow_berries_jam", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GLOW_BERRIES_JAM.get()))
                .addCriterion("has_golden_apple_jam", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GOLDEN_APPLE_JAM.get()))
                .addCriterion("has_hawthorn_berries_jam", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HAWTHORN_BERRIES_JAM.get()))
                .addCriterion("has_lemon_jam", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEMON_JAM.get()))
                .addCriterion("has_melon_jam", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MELON_JAM.get()))
                .addCriterion("has_rose_hips_jam", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ROSE_HIPS_JAM.get()))
                .addCriterion("has_sweet_berries_jam", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SWEET_BERRIES_JAM.get()))
                .save(saver, new ResourceLocation(YeastNFeastMod.MODID, "jam"), existingFileHelper);

        Advancement cropsAdvancement = Advancement.Builder.advancement()
                .display(new DisplayInfo(new ItemStack(ModItems.BARLEY.get()),
                        Component.translatable("advancements.yeastnfeast.crops.title"),
                        Component.translatable("advancements.yeastnfeast.crops.desc"),
                        BACKGROUND, FrameType.TASK,
                        true,true, false))
                .parent(rootAdvancement)
                .addCriterion("has_crop",
                        InventoryChangeTrigger.TriggerInstance.hasItems(
                                        ModItems.ELDERBERRIES.get(), ModItems.HAWTHORN_BERRIES.get(),
                                        ModItems.LEMON.get(), ModItems.ROSE_HIPS.get(), ModItems.GARLIC.get(),
                                        ModItems.BARLEY.get(), ModItems.RYE.get(), ModItems.MINT.get(),
                                        ModItems.GINGER.get()))
                .save(saver, new ResourceLocation(YeastNFeastMod.MODID, "crops.json"), existingFileHelper);

        Advancement berryRollAdvancement = Advancement.Builder.advancement()
                .display(new DisplayInfo(new ItemStack(ModItems.BERRY_ROLL.get()),
                        Component.translatable("advancements.yeastnfeast.berry_roll.title"),
                        Component.translatable("advancements.yeastnfeast.berry_roll.desc"),
                        BACKGROUND, FrameType.TASK,
                        true,true, false))
                .parent(cropsAdvancement)
                .addCriterion("has_berry_roll", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BERRY_ROLL.get()))
                .save(saver, new ResourceLocation(YeastNFeastMod.MODID, "berry_roll"), existingFileHelper);

        Advancement bowlAdvancement = Advancement.Builder.advancement()
                .display(new DisplayInfo(new ItemStack(ModItems.BARLEY_AND_BEEF_STEW.get()),
                        Component.translatable("advancements.yeastnfeast.bowl.title"),
                        Component.translatable("advancements.yeastnfeast.bowl.desc"),
                        BACKGROUND, FrameType.CHALLENGE,
                        true,true, false))
                .parent(cropsAdvancement)
                .addCriterion("eat_sweet_porridge", ConsumeItemTrigger.TriggerInstance.usedItem(ModItems.SWEET_PORRIDGE.get()))
                .addCriterion("eat_spiced_porridge", ConsumeItemTrigger.TriggerInstance.usedItem(ModItems.SPICED_PORRIDGE.get()))
                .addCriterion("eat_barley_stew", ConsumeItemTrigger.TriggerInstance.usedItem(ModItems.BARLEY_AND_BEEF_STEW.get()))
                .addCriterion("eat_salmon_chowder", ConsumeItemTrigger.TriggerInstance.usedItem(ModItems.SALMON_CHOWDER.get()))
                .save(saver, new ResourceLocation(YeastNFeastMod.MODID, "bowl"), existingFileHelper);

        Advancement plateAdvancement = Advancement.Builder.advancement()
                .display(new DisplayInfo(new ItemStack(ModItems.MAPLE_GLAZED_RABBIT.get()),
                        Component.translatable("advancements.yeastnfeast.plate.title"),
                        Component.translatable("advancements.yeastnfeast.plate.desc"),
                        BACKGROUND, FrameType.CHALLENGE,
                        true,true, false))
                .parent(cropsAdvancement)
                .addCriterion("eat_mead_pork", ConsumeItemTrigger.TriggerInstance.usedItem(ModItems.MEAD_BRAISED_PORK.get()))
                .addCriterion("eat_herbal_cod", ConsumeItemTrigger.TriggerInstance.usedItem(ModItems.HERBAL_COD.get()))
                .addCriterion("eat_lemon_chicken", ConsumeItemTrigger.TriggerInstance.usedItem(ModItems.LEMON_GLAZED_CHICKEN.get()))
                .addCriterion("eat_forager_feast", ConsumeItemTrigger.TriggerInstance.usedItem(ModItems.FORAGER_FEAST.get()))
                .addCriterion("eat_maple_rabbit", ConsumeItemTrigger.TriggerInstance.usedItem(ModItems.MAPLE_GLAZED_RABBIT.get()))
                .save(saver, new ResourceLocation(YeastNFeastMod.MODID, "plate"), existingFileHelper);

        Advancement mapleAdvancement = Advancement.Builder.advancement()
                .display(new DisplayInfo(new ItemStack(ModItems.MAPLE_SYRUP.get()),
                        Component.translatable("advancements.yeastnfeast.maple.title"),
                        Component.translatable("advancements.yeastnfeast.maple.desc"),
                        BACKGROUND, FrameType.TASK,
                        true,true, false))
                .parent(rootAdvancement)
                .addCriterion("has_maple_syrup", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MAPLE_SYRUP.get()))
                .save(saver, new ResourceLocation(YeastNFeastMod.MODID, "maple"), existingFileHelper);
    }
}
