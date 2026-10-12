package net.astralya.yeastnfeast.item.custom;

import net.astralya.yeastnfeast.item.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class ConsumableItem extends AbstractConsumableItem {

    public ConsumableItem(Settings settings) {
        super(settings.maxCount(16));
    }

    @Override
    protected void handleEffects(World world, LivingEntity entity, ItemStack stack) {
        if (!world.isClient && (stack.isOf(ModItems.MOLASSES) || stack.isOf(ModItems.MAPLE_SYRUP))) {
            entity.removeStatusEffect(StatusEffects.MINING_FATIGUE);
        }
    }

    @Override
    protected ItemStack getReturnContainer(ItemStack stack) {
        return stack.isOf(ModItems.MOLASSES) || stack.isOf(ModItems.MAPLE_SYRUP)
                ? new ItemStack(Items.GLASS_BOTTLE) : new ItemStack(ModItems.JAR);
    }

    @Override
    public SoundEvent getDrinkSound() {
        return SoundEvents.ITEM_HONEY_BOTTLE_DRINK;
    }

    @Override
    public SoundEvent getEatSound() {
        return SoundEvents.ITEM_HONEY_BOTTLE_DRINK;
    }
}