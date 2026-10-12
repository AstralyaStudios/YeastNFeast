package net.astralya.yeastnfeast.item.custom;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;

public class SkewerItem extends AbstractConsumableItem {

    public SkewerItem(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemStack getReturnContainer(ItemStack consumedStack) {
        return new ItemStack(Items.STICK);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.EAT;
    }
}