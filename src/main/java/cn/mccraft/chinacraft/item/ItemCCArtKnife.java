package cn.mccraft.chinacraft.item;

import net.minecraft.item.ItemStack;

/**
 * Created by winston_wang on 2017/7/16.
 */
public class ItemCCArtKnife extends ItemCCBase {
    public ItemCCArtKnife() {
        setMaxStackSize(1);
        setMaxDamage(6);
        setHasSubtypes(false);
        setContainerItem(this);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        itemStack.setItemDamage(itemStack.getItemDamage()+1);
        if(itemStack.getItemDamage()>=getMaxDamage()) {
            itemStack.setCount(0);
        }
        System.out.println("1");
        return itemStack;
    }
}
