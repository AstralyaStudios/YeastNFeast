package net.astralya.yeastnfeast;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class Configuration {

    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;

    public static final String FUNCTIONAL_BLOCKS = "functional_blocks";
    public static ForgeConfigSpec.IntValue TREE_TAP_DRIP_DELAY;

    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

        // --- CATEGORY: Tree Tap
        COMMON_BUILDER.comment("Functional Blocks Settings").push(FUNCTIONAL_BLOCKS);
        COMMON_BUILDER.comment("Tree Tap Settings").push(FUNCTIONAL_BLOCKS);
        TREE_TAP_DRIP_DELAY = COMMON_BUILDER
                .comment("Ticks required before the Tree Tap begins dripping (default: 7200 ticks = 6 minutes)")
                .defineInRange("dripDelayTicks", 7200, 20, 24000);
        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

    public static void register() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, COMMON_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CLIENT_CONFIG);
    }
}