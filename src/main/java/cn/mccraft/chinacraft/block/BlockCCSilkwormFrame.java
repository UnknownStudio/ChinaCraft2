package cn.mccraft.chinacraft.block;

import cn.mccraft.chinacraft.block.tileentity.TileEntitySilkwormFrame;
import cn.mccraft.chinacraft.common.ChinaCraft;
import cn.mccraft.chinacraft.common.gui.EnumGuiType;
import cn.mccraft.chinacraft.init.CCCreativeTabs;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockCCSilkwormFrame extends BlockCCBase {
    public BlockCCSilkwormFrame() {
        super(Material.WOOD);
        setCreativeTab(CCCreativeTabs.tabSilkworm);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntitySilkwormFrame();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (playerIn.isSneaking() || worldIn.isRemote)
            return true;
        else
            playerIn.openGui(ChinaCraft.getInstance(), EnumGuiType.SILKWORM_FRAME.ordinal(), worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }
}
