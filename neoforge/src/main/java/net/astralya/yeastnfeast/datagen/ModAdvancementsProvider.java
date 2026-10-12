package net.astralya.yeastnfeast.datagen;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.item.ModItems;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.ConsumeItemTrigger;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;
import java.util.function.Consumer;

public class ModAdvancementsProvider implements AdvancementSubProvider {

    @Override
    public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> writer) {
        ResourceLocation BG = ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "textures/block/maple_log.png");

        AdvancementHolder root = Advancement.Builder.advancement()
                .display(new DisplayInfo(
                        new ItemStack(ModItems.YEAST.get()),
                        Component.translatable("advancements.yeastnfeast.root.title"),
                        Component.translatable("advancements.yeastnfeast.root.desc"),
                        Optional.of(BG),
                        AdvancementType.TASK,
                        true, true, false))
                .addCriterion("has_yeast", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.YEAST.get()))
                .save(writer, String.valueOf(ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "root")));

        AdvancementHolder kegStand = Advancement.Builder.advancement()
                .display(new DisplayInfo(
                        new ItemStack(ModBlocks.KEG.get()),
                        Component.translatable("advancements.yeastnfeast.keg.title"),
                        Component.translatable("advancements.yeastnfeast.keg.desc"),
                        Optional.empty(),
                        AdvancementType.TASK,
                        true, true, false))
                .parent(root)
                .addCriterion("has_keg", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.KEG.get()))
                .save(writer, String.valueOf(ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "keg_stand")));

        AdvancementHolder sweetVictory = Advancement.Builder.advancement()
                .display(new DisplayInfo(
                        new ItemStack(ModItems.HONEY_MEAD.get()),
                        Component.translatable("advancements.yeastnfeast.sweet.title"),
                        Component.translatable("advancements.yeastnfeast.sweet.desc"),
                        Optional.empty(),
                        AdvancementType.TASK,
                        true, true, false))
                .parent(kegStand)
                .addCriterion("has_honey_mead", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HONEY_MEAD.get()))
                .addCriterion("has_molasses_mead", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MOLASSES_MEAD.get()))
                .save(writer, String.valueOf(ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "sweet_victory")));

        AdvancementHolder sourPower = Advancement.Builder.advancement()
                .display(new DisplayInfo(
                        new ItemStack(ModItems.SOUR_MEAD.get()),
                        Component.translatable("advancements.yeastnfeast.sour.title"),
                        Component.translatable("advancements.yeastnfeast.sour.desc"),
                        Optional.empty(),
                        AdvancementType.TASK,
                        true, true, false))
                .parent(kegStand)
                .addCriterion("has_sour_mead", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SOUR_MEAD.get()))
                .addCriterion("has_thornberry_mead", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.THORNBERRY_MEAD.get()))
                .save(writer, String.valueOf(ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "sour_power")));

        AdvancementHolder floralFinesse = Advancement.Builder.advancement()
                .display(new DisplayInfo(
                        new ItemStack(ModItems.BLOSSOM_MEAD.get()),
                        Component.translatable("advancements.yeastnfeast.floral.title"),
                        Component.translatable("advancements.yeastnfeast.floral.desc"),
                        Optional.empty(),
                        AdvancementType.TASK,
                        true, true, false))
                .parent(kegStand)
                .addCriterion("has_blossom_mead", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BLOSSOM_MEAD.get()))
                .addCriterion("has_amber_mead", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.AMBER_MEAD.get()))
                .save(writer, String.valueOf(ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "floral_finesse")));

        AdvancementHolder jarForSake = Advancement.Builder.advancement()
                .display(new DisplayInfo(
                        new ItemStack(ModItems.JAR.get()),
                        Component.translatable("advancements.yeastnfeast.jar.title"),
                        Component.translatable("advancements.yeastnfeast.jar.desc"),
                        Optional.empty(),
                        AdvancementType.TASK,
                        true, true, false))
                .parent(kegStand)
                .addCriterion("has_jar", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.JAR.get()))
                .save(writer, String.valueOf(ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "jar_for_sake")));

        AdvancementHolder jamSession = Advancement.Builder.advancement()
                .display(new DisplayInfo(
                        new ItemStack(ModItems.APPLE_JAM.get()),
                        Component.translatable("advancements.yeastnfeast.jam.title"),
                        Component.translatable("advancements.yeastnfeast.jam.desc"),
                        Optional.empty(),
                        AdvancementType.CHALLENGE,
                        true, true, false))
                .parent(jarForSake)
                .addCriterion("apple", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.APPLE_JAM.get()))
                .addCriterion("chorus", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CHORUS_FRUIT_JAM.get()))
                .addCriterion("elderberries", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELDERBERRIES_JAM.get()))
                .addCriterion("glow_berries", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GLOW_BERRIES_JAM.get()))
                .addCriterion("golden_apple", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GOLDEN_APPLE_JAM.get()))
                .addCriterion("hawthorn_berries", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HAWTHORN_BERRIES_JAM.get()))
                .addCriterion("lemon", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEMON_JAM.get()))
                .addCriterion("melon", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MELON_JAM.get()))
                .addCriterion("rose_hips", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ROSE_HIPS_JAM.get()))
                .addCriterion("sweet_berries", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SWEET_BERRIES_JAM.get()))
                .save(writer, String.valueOf(ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "jam_session")));

        AdvancementHolder mapleStory = Advancement.Builder.advancement()
                .display(new DisplayInfo(
                        new ItemStack(ModItems.MAPLE_SYRUP.get()),
                        Component.translatable("advancements.yeastnfeast.maple.title"),
                        Component.translatable("advancements.yeastnfeast.maple.desc"),
                        Optional.empty(),
                        AdvancementType.TASK,
                        true, true, false))
                .parent(root)
                .addCriterion("has_maple_syrup", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MAPLE_SYRUP.get()))
                .save(writer, String.valueOf(ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "maple_story")));

        AdvancementHolder harvestSeason = Advancement.Builder.advancement()
                .display(new DisplayInfo(
                        new ItemStack(ModItems.BARLEY.get()),
                        Component.translatable("advancements.yeastnfeast.crops.title"),
                        Component.translatable("advancements.yeastnfeast.crops.desc"),
                        Optional.empty(),
                        AdvancementType.TASK,
                        true, true, false))
                .parent(root)
                .addCriterion("elderberries", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ELDERBERRIES.get()))
                .addCriterion("hawthorn_berries", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HAWTHORN_BERRIES.get()))
                .addCriterion("lemon", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LEMON.get()))
                .addCriterion("rose_hips", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ROSE_HIPS.get()))
                .addCriterion("garlic", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GARLIC.get()))
                .addCriterion("barley", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BARLEY.get()))
                .addCriterion("rye", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.RYE.get()))
                .addCriterion("mint", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MINT.get()))
                .addCriterion("ginger", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.GINGER.get()))
                .requirements(AdvancementRequirements.Strategy.OR)
                .save(writer, String.valueOf(ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "harvest_season")));

        AdvancementHolder berryRoll = Advancement.Builder.advancement()
                .display(new DisplayInfo(
                        new ItemStack(ModItems.BERRY_ROLL.get()),
                        Component.translatable("advancements.yeastnfeast.berry_roll.title"),
                        Component.translatable("advancements.yeastnfeast.berry_roll.desc"),
                        Optional.empty(),
                        AdvancementType.TASK,
                        true, true, false))
                .parent(harvestSeason)
                .addCriterion("has_berry_roll", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BERRY_ROLL.get()))
                .save(writer, String.valueOf(ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "berry_roll")));

        AdvancementHolder meatAndGreet = Advancement.Builder.advancement()
                .display(new DisplayInfo(
                        new ItemStack(ModItems.BARLEY_AND_BEEF_STEW.get()),
                        Component.translatable("advancements.yeastnfeast.bowl.title"),
                        Component.translatable("advancements.yeastnfeast.bowl.desc"),
                        Optional.empty(),
                        AdvancementType.CHALLENGE,
                        true, true, false))
                .parent(harvestSeason)
                .addCriterion("sweet_porridge", ConsumeItemTrigger.TriggerInstance.usedItem(ModItems.SWEET_PORRIDGE.get()))
                .addCriterion("spiced_porridge", ConsumeItemTrigger.TriggerInstance.usedItem(ModItems.SPICED_PORRIDGE.get()))
                .addCriterion("barley_stew", ConsumeItemTrigger.TriggerInstance.usedItem(ModItems.BARLEY_AND_BEEF_STEW.get()))
                .addCriterion("salmon_chowder", ConsumeItemTrigger.TriggerInstance.usedItem(ModItems.SALMON_CHOWDER.get()))
                .save(writer, String.valueOf(ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "meat_and_greet")));

        AdvancementHolder feastYourEyes = Advancement.Builder.advancement()
                .display(new DisplayInfo(
                        new ItemStack(ModItems.MAPLE_GLAZED_RABBIT.get()),
                        Component.translatable("advancements.yeastnfeast.plate.title"),
                        Component.translatable("advancements.yeastnfeast.plate.desc"),
                        Optional.empty(),
                        AdvancementType.CHALLENGE,
                        true, true, false))
                .parent(harvestSeason)
                .addCriterion("mead_pork", ConsumeItemTrigger.TriggerInstance.usedItem(ModItems.MEAD_BRAISED_PORK.get()))
                .addCriterion("herbal_cod", ConsumeItemTrigger.TriggerInstance.usedItem(ModItems.HERBAL_COD.get()))
                .addCriterion("lemon_chicken", ConsumeItemTrigger.TriggerInstance.usedItem(ModItems.LEMON_GLAZED_CHICKEN.get()))
                .addCriterion("forager_feast", ConsumeItemTrigger.TriggerInstance.usedItem(ModItems.FORAGER_FEAST.get()))
                .addCriterion("maple_rabbit", ConsumeItemTrigger.TriggerInstance.usedItem(ModItems.MAPLE_GLAZED_RABBIT.get()))
                .save(writer, String.valueOf(ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "feast_your_eyes")));
    }
}
