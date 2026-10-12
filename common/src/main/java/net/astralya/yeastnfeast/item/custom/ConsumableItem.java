package net.astralya.yeastnfeast.item.custom;

import net.astralya.yeastnfeast.item.ModItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ConsumableItem extends AbstractConsumableItem {

    public ConsumableItem(Properties props) {
        super(props.stacksTo(16));
    }

    @Override
    protected void handleEffects(Level level, LivingEntity entity, ItemStack stack) {
        if (!level.isClientSide && (stack.is(ModItems.MOLASSES.get()) || stack.is(ModItems.MAPLE_SYRUP.get()))) {
            entity.removeEffect(MobEffects.DIG_SLOWDOWN);
        }
    }

    @Override
    public ItemStack getReturnContainer(ItemStack stack) {
        return stack.is(ModItems.MOLASSES.get()) || stack.is(ModItems.MAPLE_SYRUP.get())
                ? new ItemStack(Items.GLASS_BOTTLE) : new ItemStack(ModItems.JAR.get());
    }

    @Override
    public @NotNull SoundEvent getDrinkingSound() {
        return SoundEvents.HONEY_DRINK;
    }

    @Override
    public @NotNull SoundEvent getEatingSound() {
        return SoundEvents.HONEY_DRINK;
    }
}

