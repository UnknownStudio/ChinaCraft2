package cn.mccraft.chinacraft.block;

import cn.mccraft.chinacraft.init.CCCreativeTabs;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;

public class BlockCCStairs extends BlockStairs{
    public BlockCCStairs(IBlockState modelState) {
        super(modelState);
        setCreativeTab(CCCreativeTabs.tabCore);
    }

    public BlockCCStairs setHarvestLevelReturnBlock(String toolClass, int level) {
        super.setHarvestLevel(toolClass, level);
        return this;
    }

    @Override
    public BlockCCStairs setSoundType(SoundType sound)
    {
        super.setSoundType(sound);
        return this;
    }
}
