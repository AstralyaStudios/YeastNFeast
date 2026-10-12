package net.astralya.yeastnfeast.fabric;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;

public final class YeastNFeastFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        YeastNFeastMod.init();

        FlammableBlockRegistry flammables = FlammableBlockRegistry.getDefaultInstance();
        flammables.add(ModBlocks.MAPLE_LOG.get(), 5, 5);
        flammables.add(ModBlocks.STRIPPED_MAPLE_LOG.get(), 5, 5);
        flammables.add(ModBlocks.MAPLE_WOOD.get(), 5, 5);
        flammables.add(ModBlocks.STRIPPED_MAPLE_WOOD.get(), 5, 5);
        flammables.add(ModBlocks.MAPLE_LEAVES.get(), 30, 60);
        flammables.add(ModBlocks.MAPLE_PLANKS.get(), 5, 20);

        StrippableBlockRegistry.register(ModBlocks.MAPLE_LOG.get(), ModBlocks.STRIPPED_MAPLE_LOG.get());
        StrippableBlockRegistry.register(ModBlocks.MAPLE_WOOD.get(), ModBlocks.STRIPPED_MAPLE_WOOD.get());
    }
}
