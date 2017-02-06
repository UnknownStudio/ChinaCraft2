package cn.mccraft.chinacraft.inventory;

import cn.mccraft.chinacraft.init.CCItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nonnull;

public class ContainerRedPacket extends Container {

    private ItemStack itemStack;

    public ContainerRedPacket(EntityPlayer player) {
        this.itemStack = player.getHeldItemMainhand();
        Slot slot0 = new Slot(new InventoryBasic("redpacket", false, 1), 0, 80, 25);
        this.addSlotToContainer(slot0);

        load(player);

        int var3;
        for (var3 = 0; var3 < 3; ++var3) {
            for (int var4 = 0; var4 < 9; ++var4) {
                this.addSlotToContainer(new Slot(player.inventory, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3) {
            if (var3 == player.inventory.currentItem) {
                this.addSlotToContainer(new Slot(player.inventory, var3, 8 + var3 * 18, 142) {
                    @Override
                    public boolean canTakeStack(EntityPlayer p_82869_1_) {
                        return false;
                    }

                    @Override
                    public boolean isItemValid(ItemStack p_75214_1_) {
                        return false;
                    }
                });
            } else {
                this.addSlotToContainer(new Slot(player.inventory, var3, 8 + var3 * 18, 142));
            }
        }
    }

    private void load(EntityPlayer player){
        //不知道有没有用的防刷物品措施
        if(player.getEntityWorld().isRemote) return;

        if (itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("item")) {
            NBTTagCompound itemnbt = itemStack.getTagCompound().getCompoundTag("item");
            //不知道有没有用的防刷物品措施
            itemStack.getTagCompound().setTag("item",new NBTTagCompound());
            ItemStack item = new ItemStack(itemnbt);
            /*if (item != null && FeatureConfig.ItemBombInRedPackerExplosion && item.getItem() == ChinaCraft.bomb) {
                //炸弹自爆
                player.worldObj.createExplosion(player, player.posX, player.posY, player.posZ, 1.5F, true);
                getSlot(0).putStack(null);
            } else*/
            getSlot(0).putStack(item);
        }
    }

    @Override
    public void onContainerClosed(EntityPlayer p_75134_1_) {
        super.onContainerClosed(p_75134_1_);
        if (p_75134_1_.getHeldItemMainhand() == ItemStack.EMPTY || !p_75134_1_.getHeldItemMainhand().getItem().equals(CCItems.RED_PACKET)) return;

        NBTTagCompound nbtitem = new NBTTagCompound();
        if (getSlot(0).getStack() != ItemStack.EMPTY) getSlot(0).getStack().writeToNBT(nbtitem);
        p_75134_1_.getHeldItemMainhand().setTagInfo("item", nbtitem);
    }

    @Nonnull
    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack var3 = ItemStack.EMPTY;
        Slot var4 = this.inventorySlots.get(par2);
        if (var4 != null && var4.getHasStack()) {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();
            // 点击到Slot的ID为0的时候，将物品送回玩家的背包中
            if (par2 == 0) {
                if (!this.mergeItemStack(var5, 1, 28, false)) {
                    return ItemStack.EMPTY;
                }
                var4.onSlotChange(var5, var3);
            }
            // 点击到玩家的背包的时候将物品送到玩家的快捷栏中
            else if (par2 > 0 && par2 < 28) {
                if (!this.mergeItemStack(var5, 28, 37, false)) {
                    return ItemStack.EMPTY;
                }
            }
            // 点击到玩家的快捷栏的时候将物品送到背包中
            else if (par2 >= 28 && par2 < 37) {
                if (!this.mergeItemStack(var5, 1, 28, false)) {
                    return ItemStack.EMPTY;
                }
            }
            if (var5.getMaxStackSize() == 0) {
                var4.putStack(ItemStack.EMPTY);
            } else {
                var4.onSlotChanged();
            }
            if (var5.getMaxStackSize() == var3.getMaxStackSize()) {
                return ItemStack.EMPTY;
            }
            var4.onTake(par1EntityPlayer, var5);
        }

        return var3;
    }

    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer playerIn) {
        return playerIn.getHeldItemMainhand().getItem().equals(CCItems.RED_PACKET);
    }
}
