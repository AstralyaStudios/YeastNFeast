package net.astralya.yeastnfeast;

import net.astralya.yeastnfeast.block.ModBlocks;
import net.astralya.yeastnfeast.block.entity.ModBlockEntityTypes;
import net.astralya.yeastnfeast.effect.ModMobEffects;
import net.astralya.yeastnfeast.entity.ModEntities;
import net.astralya.yeastnfeast.item.ModCreativeModeTabs;
import net.astralya.yeastnfeast.item.ModItems;
import net.astralya.yeastnfeast.particle.ModParticleType;
import net.astralya.yeastnfeast.recipe.ModRecipes;
import net.astralya.yeastnfeast.screen.ModMenuTypes;
import net.astralya.yeastnfeast.villager.ModVillagers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class YeastNFeastMod {
    public static final String MODID = "yeastnfeast";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    private YeastNFeastMod() {
    }

    public static void init() {
        ModBlocks.init();
        ModItems.init();
        ModCreativeModeTabs.init();
        ModBlockEntityTypes.init();
        ModEntities.init();
        ModParticleType.init();
        ModMobEffects.init();
        ModVillagers.init();
        ModRecipes.init();
        ModMenuTypes.init();

    }
}
