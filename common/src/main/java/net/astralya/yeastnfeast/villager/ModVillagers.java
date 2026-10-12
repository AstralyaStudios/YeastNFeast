package net.astralya.yeastnfeast.villager;

import net.minecraft.core.registries.Registries;
import com.google.common.collect.ImmutableSet;
import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import dev.architectury.registry.registries.DeferredRegister;

public class ModVillagers {
    
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(YeastNFeastMod.MODID, Registries.POINT_OF_INTEREST_TYPE);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(YeastNFeastMod.MODID, Registries.VILLAGER_PROFESSION);

    public static final Holder<PoiType> KEG_POI = POI_TYPES.register("keg_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.KEG.get().getStateDefinition().getPossibleStates()),
                    1, 1));

    public static final Holder<VillagerProfession> TAVERNKEEPER = VILLAGER_PROFESSIONS.register("tavernkeeper",
            () -> new VillagerProfession("tavernkeeper", holder -> holder.value() == KEG_POI.value(),
                    holder -> holder.value() == KEG_POI.value(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_CLERIC));

    public static void init() {
        POI_TYPES.register();
        VILLAGER_PROFESSIONS.register();
    }
}

