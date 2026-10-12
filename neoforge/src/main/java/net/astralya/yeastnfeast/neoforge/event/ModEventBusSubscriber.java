package net.astralya.yeastnfeast.neoforge.event;

import net.astralya.yeastnfeast.block.entity.ModBlockEntityTypes;
import net.astralya.yeastnfeast.block.entity.custom.CheesePressBlockEntity;
import net.astralya.yeastnfeast.block.entity.custom.KegBlockEntity;
import net.astralya.yeastnfeast.entity.layers.ModModelLayers;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class ModEventBusSubscriber {

    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.MAPLE_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(ModModelLayers.MAPLE_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);
    }

    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntityTypes.KEG.get(),
                (blockEntity, direction) -> new net.astralya.yeastnfeast.neoforge.event.NeoForgeItemHandler(blockEntity.getItemHandler(direction)));
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ModBlockEntityTypes.CHEESE_PRESS.get(),
                (blockEntity, direction) -> new net.astralya.yeastnfeast.neoforge.event.NeoForgeItemHandler(blockEntity.getItemHandler(direction)));
    }

}
