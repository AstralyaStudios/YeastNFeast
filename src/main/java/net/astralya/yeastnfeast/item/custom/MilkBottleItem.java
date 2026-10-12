package net.astralya.yeastnfeast.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MilkBottleItem extends AbstractConsumableItem {
    public MilkBottleItem(Settings settings) {
        super(settings.maxCount(16));
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        List<StatusEffect> removableEffects = new ArrayList<>();

        for (StatusEffectInstance effect : user.getStatusEffects()) {
            if (effect.getEffectType().isBeneficial()) {
                removableEffects.add(effect.getEffectType());
            }
        }

        if (!removableEffects.isEmpty()) {
            StatusEffect selectedEffect = removableEffects.get(world.random.nextInt(removableEffects.size()));
            user.removeStatusEffect(selectedEffect);
        }

        return super.finishUsing(stack, world, user);
    }

    @Override
    protected ItemStack getReturnContainer(ItemStack stack) {
        return new ItemStack(Items.GLASS_BOTTLE);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.yeastnfeast.milk_bottle").formatted(Formatting.BLUE));
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 40;
    }
}
