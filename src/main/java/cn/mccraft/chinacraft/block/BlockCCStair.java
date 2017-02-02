package cn.mccraft.chinacraft.block;

import cn.mccraft.chinacraft.init.CCCreativeTabs;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;

/**
 * Created by Mouse on 2017/2/2.
 */
public class BlockCCStair extends BlockStairs{
    public BlockCCStair(IBlockState modelState) {
        super(modelState);
        setCreativeTab(CCCreativeTabs.tabCore);
    }

    public BlockCCStair setHarvestLevelReturnBlock(String toolClass, int level) {
        super.setHarvestLevel(toolClass, level);
        return this;
    }
}
