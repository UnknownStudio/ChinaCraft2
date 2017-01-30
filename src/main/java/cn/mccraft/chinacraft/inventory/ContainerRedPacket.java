package cn.mccraft.chinacraft.inventory;

import cn.mccraft.chinacraft.init.CCItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ContainerRedPacket extends Container {
    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer playerIn) {
        return playerIn.getHeldItemMainhand().isItemEqual(new ItemStack(CCItems.RED_PACKET));
    }
}
