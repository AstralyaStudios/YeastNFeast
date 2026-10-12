package net.astralya.yeastnfeast.villager;

import com.google.common.collect.ImmutableSet;
import net.astralya.yeastnfeast.YeastNFeastMod;
import net.astralya.yeastnfeast.block.ModBlocks;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, YeastNFeastMod.MODID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, YeastNFeastMod.MODID);

    public static final RegistryObject<PoiType> KEG_POI = POI_TYPES.register("keg_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.KEG.get().getStateDefinition().getPossibleStates()),
                    1, 1));

    public static final RegistryObject<VillagerProfession> TAVERN_KEEPER =
            VILLAGER_PROFESSIONS.register("tavernkeeper", () -> new VillagerProfession("tavernkeeper",
                    x -> x.get() == KEG_POI.get(), x -> x.get() == KEG_POI.get(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_CLERIC));

    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}