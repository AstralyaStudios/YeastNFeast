package net.astralya.yeastnfeast.item;

import net.astralya.yeastnfeast.effect.ModEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {

    // Berries
    public static final FoodComponent ROSE_HIPS = new FoodComponent.Builder().hunger(2).saturationModifier(0.3f).build();
    public static final FoodComponent ELDERBERRIES = new FoodComponent.Builder().hunger(2).saturationModifier(0.1f).build();
    public static final FoodComponent HAWTHORN_BERRIES = new FoodComponent.Builder().hunger(2).saturationModifier(0.1f).build();

    // Fruits & Others
    public static final FoodComponent GARLIC = new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build();
    public static final FoodComponent GINGER = new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build();
    public static final FoodComponent LEMON = new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).build();
    public static final FoodComponent MAPLE_SYRUP = new FoodComponent.Builder().hunger(6).saturationModifier(0.1f).build();
    public static final FoodComponent MOLASSES = new FoodComponent.Builder().hunger(6).saturationModifier(0.1f).build();

    // Bread
    public static final FoodComponent BARLEY_BREAD = new FoodComponent.Builder().hunger(5).saturationModifier(0.8f).build();
    public static final FoodComponent RYE_BREAD = new FoodComponent.Builder().hunger(5).saturationModifier(0.8f).build();
    public static final FoodComponent MOLASSES_BREAD = new FoodComponent.Builder().hunger(5).saturationModifier(0.8f).build();

    // Baked Goods
    public static final FoodComponent BERRY_ROLL = new FoodComponent.Builder().hunger(8).saturationModifier(0.6f).build();
    public static final FoodComponent ROSE_TART = new FoodComponent.Builder().hunger(10).saturationModifier(0.8f).build();
    public static final FoodComponent ELDERBERRY_PIE = new FoodComponent.Builder().hunger(10).saturationModifier(0.8f).build();
    public static final FoodComponent APPLE_PIE = new FoodComponent.Builder().hunger(10).saturationModifier(0.8f).build();
    public static final FoodComponent MINTED_CHEESE_TART = new FoodComponent.Builder().hunger(11).saturationModifier(0.85f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 120, 0), 1.0F).build();
    public static final FoodComponent QUICHE = new FoodComponent.Builder().hunger(12).saturationModifier(0.8f).build();

    // Feasts
    public static final FoodComponent SWEET_PORRIDGE = new FoodComponent.Builder().hunger(10).saturationModifier(0.6f).build();
    public static final FoodComponent SPICED_PORRIDGE = new FoodComponent.Builder().hunger(10).saturationModifier(0.6f).build();
    public static final FoodComponent BARLEY_AND_BEEF_STEW = new FoodComponent.Builder().hunger(14).saturationModifier(0.75f).build();
    public static final FoodComponent SALMON_CHOWDER = new FoodComponent.Builder().hunger(12).saturationModifier(0.8f).build();
    public static final FoodComponent MEAD_BRAISED_PORK = new FoodComponent.Builder().hunger(14).saturationModifier(0.75f).build();
    public static final FoodComponent HERBAL_COD = new FoodComponent.Builder().hunger(10).saturationModifier(0.6f).build();
    public static final FoodComponent LEMON_GLAZED_CHICKEN = new FoodComponent.Builder().hunger(14).saturationModifier(0.75f).build();
    public static final FoodComponent FORAGER_FEAST = new FoodComponent.Builder().hunger(12).saturationModifier(0.8f).build();
    public static final FoodComponent MAPLE_GLAZED_RABBIT = new FoodComponent.Builder().hunger(10).saturationModifier(0.6f).build();
    public static final FoodComponent DUSKWHEEL_SKEWER = new FoodComponent.Builder().hunger(12).saturationModifier(0.75f)
            .statusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 300, 0), 1.0f).build();
    public static final FoodComponent STUFFED_RYE_DUMPLINGS = new FoodComponent.Builder().hunger(11).saturationModifier(0.8f)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 0), 1.0f).build();
    public static final FoodComponent CHEESE_SOUP = new FoodComponent.Builder().hunger(12).saturationModifier(0.8f).build();

    // Jams
    public static final FoodComponent APPLE_JAM = new FoodComponent.Builder().hunger(6).saturationModifier(0.8f).build();
    public static final FoodComponent CHORUS_FRUIT_JAM = new FoodComponent.Builder().hunger(7).saturationModifier(0.8f).build();
    public static final FoodComponent ELDERBERRIES_JAM = new FoodComponent.Builder().hunger(6).saturationModifier(0.8f).build();
    public static final FoodComponent GLOW_BERRIES_JAM = new FoodComponent.Builder().hunger(6).saturationModifier(0.8f).build();
    public static final FoodComponent GOLDEN_APPLE_JAM = new FoodComponent.Builder().hunger(8).saturationModifier(0.8f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 1200, 1), 1.0F)
            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 0), 1.0F)
            .alwaysEdible().build();
    public static final FoodComponent HAWTHORN_BERRIES_JAM = new FoodComponent.Builder().hunger(6).saturationModifier(0.8f).build();
    public static final FoodComponent LEMON_JAM = new FoodComponent.Builder().hunger(6).saturationModifier(0.8f).build();
    public static final FoodComponent ROSE_HIPS_JAM = new FoodComponent.Builder().hunger(6).saturationModifier(0.8f).build();
    public static final FoodComponent SWEET_BERRIES_JAM = new FoodComponent.Builder().hunger(6).saturationModifier(0.8f).build();
    public static final FoodComponent MELON_JAM = new FoodComponent.Builder().hunger(6).saturationModifier(0.8f).build();

    // Cheese Slices
    public static final FoodComponent CHEESE_SLICE = new FoodComponent.Builder().hunger(3).saturationModifier(0.6f).build();
    public static final FoodComponent DUSKWHEEL_SLICE = new FoodComponent.Builder().hunger(3).saturationModifier(0.6f)
            .statusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 200, 0), 1.0f).build();
    public static final FoodComponent SHARPWHEEL_SLICE = new FoodComponent.Builder().hunger(3).saturationModifier(0.6f)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 120, 0), 1.0f).build();
    public static final FoodComponent FRESHWHEEL_SLICE = new FoodComponent.Builder().hunger(3).saturationModifier(0.6f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 80, 0), 1.0f).build();

    // Compat Foods
    public static final FoodComponent CHILLBERRIES_JAM = new FoodComponent.Builder().hunger(6).saturationModifier(0.8f).build();
    public static final FoodComponent STUFFED_MANDRAKE = new FoodComponent.Builder().hunger(12).saturationModifier(0.8f)
            .statusEffect(new StatusEffectInstance(ModEffects.OVERFED, 1200, 0), 1.0F).build();
    public static final FoodComponent SUNFIRE_TOMATO_BRUSCHETTA = new FoodComponent.Builder().hunger(8).saturationModifier(0.8f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 400, 0), 1.0F).build();

    public static final FoodComponent GARDEN_SOUP = new FoodComponent.Builder().hunger(13).saturationModifier(0.8f).build();
    public static final FoodComponent GINGER_TEA = new FoodComponent.Builder().hunger(6).saturationModifier(0.1f).build();
    public static final FoodComponent SPICED_FLATBREAD = new FoodComponent.Builder().hunger(10).saturationModifier(0.8f).build();

    public static final FoodComponent STRAWBERRIES_JAM = new FoodComponent.Builder().hunger(6).saturationModifier(0.8f).build();
    public static final FoodComponent ELDERBERRIES_OATMEAL = new FoodComponent.Builder().hunger(12).saturationModifier(0.8f).build();
    public static final FoodComponent MAPLE_RYE_PANCAKES = new FoodComponent.Builder().hunger(12).saturationModifier(0.8f).build();

}