package cn.mccraft.chinacraft.inventory;

import cn.mccraft.chinacraft.block.tileentity.TileEntityCrusher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class ContainerCrusher extends Container {
    private TileEntityCrusher crusherEntity;
    private int lastAngle;
    private int lastSchedule;

    public ContainerCrusher(InventoryPlayer inventoryPlayer, TileEntityCrusher crusherEntity) {
        this.crusherEntity = crusherEntity;
        this.addSlotToContainer(new SlotItemHandler(crusherEntity.getInventory(), 0, 43, 25));
        this.addSlotToContainer(new SlotItemHandler(crusherEntity.getInventory(), 1, 43, 50));
        this.addSlotToContainer(new SlotItemHandler(crusherEntity.getInventory(), 2, 112, 25));
        this.addSlotToContainer(new SlotItemHandler(crusherEntity.getInventory(), 3, 112, 50));
//        this.addSlotToContainer(new SlotFurnaceOutput(par1InventoryPlayer.player, tileEntity, 2, 112, 25));
//        this.addSlotToContainer(new SlotFurnaceOutput(par1InventoryPlayer.player, tileEntity, 3, 112, 50));

        // 玩家物品栏
        int var3;
        for (var3 = 0; var3 < 3; ++var3) {
            for (int var4 = 0; var4 < 9; ++var4) {
                this.addSlotToContainer(
                    new Slot(inventoryPlayer, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3) {
            this.addSlotToContainer(new Slot(inventoryPlayer, var3, 8 + var3 * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer playerIn) {
        return !playerIn.isSneaking();
    }

    @Nonnull
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack ret = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack slotStack = slot.getStack();
            ret = slotStack.copy();
            // 点击到Slot的ID为0-3之间的时候，将物品送回玩家的背包中
            if (index >= 0 && index <= 3) {
                if (!this.mergeItemStack(slotStack, 4, 31, false)) {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(slotStack, ret);
            }
            // 点击到玩家的背包的时候将物品送到玩家的快捷栏中
            else if (index > 3 && index < 31) {
                if (!this.mergeItemStack(slotStack, 31, 40, false)) {
                    return ItemStack.EMPTY;
                }
            }
            // 点击到玩家的快捷栏的时候将物品送到背包中
            else if (index >= 31 && index < 40) {
                if (!this.mergeItemStack(slotStack, 4, 31, false)) {
                    return ItemStack.EMPTY;
                }
            }
            if (slotStack.getCount() == 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
            if (slotStack.getCount() == ret.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(player, slotStack);
        }
        return ret;
    }
}
