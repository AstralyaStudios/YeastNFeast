package net.astralya.yeastnfeast.datagen;

import net.astralya.yeastnfeast.YeastNFeastMod;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, YeastNFeastMod.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // Placeholder
    }
}