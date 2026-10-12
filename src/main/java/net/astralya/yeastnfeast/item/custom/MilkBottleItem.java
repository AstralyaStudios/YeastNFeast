package net.astralya.yeastnfeast.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class MilkBottleItem extends AbstractConsumableItem {
    public MilkBottleItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected void handleEffects(Level level, LivingEntity user, ItemStack consumedStack) {
        List<MobEffect> removableEffects = new ArrayList<>();
        for (MobEffectInstance effect : user.getActiveEffects()) {
            if (!effect.getEffect().isBeneficial()) {
                removableEffects.add(effect.getEffect());
            }
        }
        if (!removableEffects.isEmpty()) {
            MobEffect selectedEffect = removableEffects.get(level.random.nextInt(removableEffects.size()));
            user.removeEffect(selectedEffect);
        }
    }

    @Override
    protected ItemStack getReturnContainer(ItemStack stack) {
        return new ItemStack(Items.GLASS_BOTTLE);
    }

    @Override
    protected Component getTooltip(ItemStack stack) {
        return Component.translatable("tooltip.yeastnfeast.milk_bottle").withStyle(ChatFormatting.BLUE);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 40;
    }
}