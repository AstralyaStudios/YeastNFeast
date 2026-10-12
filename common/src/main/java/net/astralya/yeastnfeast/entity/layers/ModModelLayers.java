package net.astralya.yeastnfeast.entity.layers;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    public static final ModelLayerLocation MAPLE_BOAT_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "boat/maple"), "main");
    public static final ModelLayerLocation MAPLE_CHEST_BOAT_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(YeastNFeastMod.MODID, "chest_boat/maple"), "main");
}
