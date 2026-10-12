package net.astralya.yeastnfeast.item;

import net.astralya.yeastnfeast.effect.ModMobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {

    // Berries
    public static final FoodProperties ROSE_HIPS = new FoodProperties.Builder().nutrition(2).saturationModifier(0.3f).build();
    public static final FoodProperties ELDERBERRIES = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1f).build();
    public static final FoodProperties HAWTHORN_BERRIES = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1f).build();

    // Fruits & Others
    public static final FoodProperties GARLIC = new FoodProperties.Builder().nutrition(1).saturationModifier(0.1f).build();
    public static final FoodProperties GINGER = new FoodProperties.Builder().nutrition(1).saturationModifier(0.1f).build();
    public static final FoodProperties LEMON = new FoodProperties.Builder().nutrition(1).saturationModifier(0.1f).build();
    public static final FoodProperties MAPLE_SYRUP = new FoodProperties.Builder().nutrition(6).saturationModifier(0.1f).build();
    public static final FoodProperties MOLASSES = new FoodProperties.Builder().nutrition(6).saturationModifier(0.1f).build();

    // Bread
    public static final FoodProperties BARLEY_BREAD = new FoodProperties.Builder().nutrition(5).saturationModifier(0.8f).build();
    public static final FoodProperties RYE_BREAD = new FoodProperties.Builder().nutrition(5).saturationModifier(0.8f).build();
    public static final FoodProperties MOLASSES_BREAD = new FoodProperties.Builder().nutrition(5).saturationModifier(0.8f).build();

    // Baked Goods
    public static final FoodProperties BERRY_ROLL = new FoodProperties.Builder().nutrition(8).saturationModifier(0.6f).build();
    public static final FoodProperties ROSE_TART = new FoodProperties.Builder().nutrition(10).saturationModifier(0.8f).build();
    public static final FoodProperties ELDERBERRY_PIE = new FoodProperties.Builder().nutrition(10).saturationModifier(0.8f).build();
    public static final FoodProperties APPLE_PIE = new FoodProperties.Builder().nutrition(10).saturationModifier(0.8f).build();
    public static final FoodProperties MINTED_CHEESE_TART = new FoodProperties.Builder().nutrition(11).saturationModifier(0.85f)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 120, 0), 1.0f).build();
    public static final FoodProperties QUICHE = new FoodProperties.Builder().nutrition(12).saturationModifier(0.8f).build();

    // Feasts
    public static final FoodProperties SWEET_PORRIDGE = new FoodProperties.Builder().nutrition(10).saturationModifier(0.6f).build();
    public static final FoodProperties SPICED_PORRIDGE = new FoodProperties.Builder().nutrition(10).saturationModifier(0.6f).build();
    public static final FoodProperties BARLEY_AND_BEEF_STEW = new FoodProperties.Builder().nutrition(14).saturationModifier(0.75f).build();
    public static final FoodProperties SALMON_CHOWDER = new FoodProperties.Builder().nutrition(12).saturationModifier(0.8f).build();
    public static final FoodProperties MEAD_BRAISED_PORK = new FoodProperties.Builder().nutrition(14).saturationModifier(0.75f).build();
    public static final FoodProperties HERBAL_COD = new FoodProperties.Builder().nutrition(10).saturationModifier(0.6f).build();
    public static final FoodProperties LEMON_GLAZED_CHICKEN = new FoodProperties.Builder().nutrition(14).saturationModifier(0.75f).build();
    public static final FoodProperties FORAGER_FEAST = new FoodProperties.Builder().nutrition(12).saturationModifier(0.8f).build();
    public static final FoodProperties MAPLE_GLAZED_RABBIT = new FoodProperties.Builder().nutrition(10).saturationModifier(0.6f).build();
    public static final FoodProperties DUSKWHEEL_SKEWER = new FoodProperties.Builder().nutrition(12).saturationModifier(0.75f)
            .effect(new MobEffectInstance(MobEffects.NIGHT_VISION, 300, 0), 1.0f).build();
    public static final FoodProperties STUFFED_RYE_DUMPLINGS = new FoodProperties.Builder().nutrition(11).saturationModifier(0.8f)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 0), 1.0f).build();
    public static final FoodProperties CHEESE_SOUP = new FoodProperties.Builder().nutrition(12).saturationModifier(0.8f).build();

    // Jams
    public static final FoodProperties APPLE_JAM = new FoodProperties.Builder().nutrition(6).saturationModifier(0.8f).build();
    public static final FoodProperties CHORUS_FRUIT_JAM = new FoodProperties.Builder().nutrition(7).saturationModifier(0.8f).build();
    public static final FoodProperties ELDERBERRIES_JAM = new FoodProperties.Builder().nutrition(6).saturationModifier(0.8f).build();
    public static final FoodProperties GLOW_BERRIES_JAM = new FoodProperties.Builder().nutrition(6).saturationModifier(0.8f).build();
    public static final FoodProperties GOLDEN_APPLE_JAM = new FoodProperties.Builder().nutrition(8).saturationModifier(0.8f)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 1200, 1), 1.0F)
            .effect(new MobEffectInstance(MobEffects.ABSORPTION, 2400, 0), 1.0F)
            .alwaysEdible().build();
    public static final FoodProperties HAWTHORN_BERRIES_JAM = new FoodProperties.Builder().nutrition(6).saturationModifier(0.8f).build();
    public static final FoodProperties LEMON_JAM = new FoodProperties.Builder().nutrition(6).saturationModifier(0.8f).build();
    public static final FoodProperties ROSE_HIPS_JAM = new FoodProperties.Builder().nutrition(6).saturationModifier(0.8f).build();
    public static final FoodProperties SWEET_BERRIES_JAM = new FoodProperties.Builder().nutrition(6).saturationModifier(0.8f).build();
    public static final FoodProperties MELON_JAM = new FoodProperties.Builder().nutrition(6).saturationModifier(0.8f).build();

    // Cheese Slices
    public static final FoodProperties CHEESE_SLICE = new FoodProperties.Builder().nutrition(1).saturationModifier(0.4f).build();
    public static final FoodProperties DUSKWHEEL_SLICE = new FoodProperties.Builder().nutrition(1).saturationModifier(0.4f).build();
    public static final FoodProperties SHARPWHEEL_SLICE = new FoodProperties.Builder().nutrition(1).saturationModifier(0.4f).build();
    public static final FoodProperties FRESHWHEEL_SLICE = new FoodProperties.Builder().nutrition(1).saturationModifier(0.4f).build();

    // Compat Foods
    public static final FoodProperties CHILLBERRIES_JAM = new FoodProperties.Builder().nutrition(6).saturationModifier(0.8f).build();
    public static final FoodProperties STUFFED_MANDRAKE = new FoodProperties.Builder().nutrition(12).saturationModifier(0.8f)
            .effect(new MobEffectInstance(ModMobEffects.OVERFED, 1200, 0), 1.0F).build();
    public static final FoodProperties SUNFIRE_TOMATO_BRUSCHETTA = new FoodProperties.Builder().nutrition(8).saturationModifier(0.8f)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 400, 0), 1.0F).build();

    public static final FoodProperties GARDEN_SOUP = new FoodProperties.Builder().nutrition(13).saturationModifier(0.8f).build();
    public static final FoodProperties GINGER_TEA = new FoodProperties.Builder().nutrition(6).saturationModifier(0.1f).build();
    public static final FoodProperties SPICED_FLATBREAD = new FoodProperties.Builder().nutrition(10).saturationModifier(0.8f).build();

    public static final FoodProperties STRAWBERRIES_JAM = new FoodProperties.Builder().nutrition(6).saturationModifier(0.8f).build();
    public static final FoodProperties ELDERBERRIES_OATMEAL = new FoodProperties.Builder().nutrition(12).saturationModifier(0.8f).build();
    public static final FoodProperties MAPLE_RYE_PANCAKES = new FoodProperties.Builder().nutrition(12).saturationModifier(0.8f).build();
}
