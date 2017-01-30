package cn.mccraft.chinacraft.block.machine;

import cn.mccraft.chinacraft.block.tileentity.TileEntityCrusher;
import cn.mccraft.chinacraft.capability.CapabilityLoader;
import cn.mccraft.chinacraft.common.ChinaCraft;
import cn.mccraft.chinacraft.common.gui.EnumGuiType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockCrusher extends BlockMachine {
    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityCrusher();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float side, float hitX, float hitY) {
        try {
            if (facing.equals(EnumFacing.UP) && worldIn.getTileEntity(pos).hasCapability(CapabilityLoader.getStatsCapability(), null))
                worldIn.getTileEntity(pos).getCapability(CapabilityLoader.getStatsCapability(), null).addProgress(0.5f);
            else
                playerIn.openGui(ChinaCraft.getInstance(), EnumGuiType.CRUSHER.ordinal(), worldIn, pos.getX(), pos.getY(), pos.getZ());
            return true;
        } catch (NullPointerException ignored) {
            return false;
        }
    }
}
