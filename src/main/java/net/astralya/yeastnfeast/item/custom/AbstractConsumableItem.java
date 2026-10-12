package net.astralya.yeastnfeast.item.custom;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public abstract class AbstractConsumableItem extends Item {
    public AbstractConsumableItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        ItemStack original = stack.copy();
        ItemStack current = super.finishUsingItem(stack, level, livingEntity);

        if (livingEntity instanceof ServerPlayer serverPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, original);
            serverPlayer.awardStat(Stats.ITEM_USED.get(this));
        }

        handleEffects(level, livingEntity, original);

        ItemStack container = getReturnContainer(original);

        if (!level.isClientSide && livingEntity instanceof Player player && !player.getAbilities().instabuild) {
            boolean isFood = original.isEdible();
            if (!isFood) {
                current.shrink(1);
            }

            if (current.isEmpty()) {
                return container;
            }

            if (!container.isEmpty()) {
                if (!player.getInventory().add(container)) {
                    player.drop(container, false);
                }
            }
        }

        return current;
    }

    protected void handleEffects(Level level, LivingEntity entity, ItemStack stack) {
    }

    protected abstract ItemStack getReturnContainer(ItemStack consumedStack);

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        player.startUsingItem(usedHand);
        return InteractionResultHolder.consume(player.getItemInHand(usedHand));
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 32;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        Component line = getTooltip(stack);
        if (line != null) {
            tooltip.add(line);
        }
    }

    @Nullable
    protected Component getTooltip(ItemStack stack) {
        return null;
    }
}