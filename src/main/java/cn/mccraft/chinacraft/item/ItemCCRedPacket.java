package cn.mccraft.chinacraft.item;

import cn.mccraft.chinacraft.common.ChinaCraft;
import cn.mccraft.chinacraft.common.gui.EnumGuiType;import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemCCRedPacket extends ItemCCBase {

    public ItemCCRedPacket(){
        setMaxStackSize(1);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        BlockPos pos = playerIn.getPosition();
        playerIn.openGui(ChinaCraft.getInstance(), EnumGuiType.RED_PACKET.ordinal(), worldIn, pos.getX(), pos.getY(), pos.getZ());
        return ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
}
