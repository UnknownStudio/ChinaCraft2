package cn.mccraft.chinacraft.block;

import cn.mccraft.chinacraft.init.CCCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

/**
 * Created by Mouse on 2017/2/5.
 */
public class BlockCCFence extends BlockFence{

    public BlockCCFence(Material materialIn) {
        this(materialIn, materialIn.getMaterialMapColor());
    }

    public BlockCCFence(Material materialIn, MapColor mapColorIn) {
        super(materialIn, mapColorIn);
        setCreativeTab(CCCreativeTabs.tabCore);
    }

    public BlockCCFence setHarvestLevelReturnBlock(String toolClass, int level) {
        super.setHarvestLevel(toolClass, level);
        return this;
    }

    @Override
    public BlockCCFence setSoundType(SoundType sound)
    {
        super.setSoundType(sound);
        return this;
    }

    @Override
    public boolean canConnectTo(IBlockAccess worldIn, BlockPos pos) {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        Block block = iblockstate.getBlock();
        return super.canConnectTo(worldIn, pos) || block instanceof BlockCCFenceGate;
    }

    @Override
    public boolean canBeConnectedTo(IBlockAccess world, BlockPos pos, EnumFacing facing) {
        Block connector = world.getBlockState(pos.offset(facing)).getBlock();
        return super.canBeConnectedTo(world, pos, facing);
    }
}
