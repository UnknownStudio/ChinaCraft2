package cn.mccraft.chinacraft.block.machine;

import cn.mccraft.chinacraft.block.tileentity.TileEntityCrusher;
import cn.mccraft.chinacraft.common.ChinaCraft;
import cn.mccraft.chinacraft.common.gui.EnumGuiType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockCrusher extends BlockMachine {
    private EnumCrusherMaterial crusherMaterial;

    public BlockCrusher(EnumCrusherMaterial crusherMaterial) {
        this.crusherMaterial = crusherMaterial;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityCrusher(crusherMaterial);
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
                                ItemStack stack) {
        int l = MathHelper.floor(placer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        if (l == 0) {
            worldIn.setBlockState(pos, state.getBlock().getStateFromMeta(4));
        }

        if (l == 1) {
            worldIn.setBlockState(pos, state.getBlock().getStateFromMeta(5));
        }

        if (l == 2) {
            worldIn.setBlockState(pos, state.getBlock().getStateFromMeta(2));
        }

        if (l == 3) {
            worldIn.setBlockState(pos, state.getBlock().getStateFromMeta(3));
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float side, float hitX, float hitY) {
        if (playerIn.isSneaking() || worldIn.isRemote)
            return true;
        else if (facing == EnumFacing.UP)
            if (worldIn.getTileEntity(pos) instanceof TileEntityCrusher)
                ((TileEntityCrusher) worldIn.getTileEntity(pos)).addAngle(10);
        else
            playerIn.openGui(ChinaCraft.getInstance(), EnumGuiType.CRUSHER.ordinal(), worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }
}
