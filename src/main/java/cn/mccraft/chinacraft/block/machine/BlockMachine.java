package cn.mccraft.chinacraft.block.machine;

import cn.mccraft.chinacraft.block.BlockCCBase;
import cn.mccraft.chinacraft.init.CCMaterials;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class BlockMachine extends BlockCCBase implements ITileEntityProvider {
    public BlockMachine() {
        super(CCMaterials.MACHINE_MATERIAL);
        setHardness(2.5F);
        setSoundType(SoundType.METAL);
    }

    public void breakBlock(World worldIn, BlockPos pos, IBlockState state){
        super.breakBlock(worldIn, pos, state);
        worldIn.removeTileEntity(pos);
    }
}
