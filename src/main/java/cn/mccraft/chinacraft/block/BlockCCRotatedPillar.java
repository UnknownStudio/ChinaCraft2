package cn.mccraft.chinacraft.block;

import cn.mccraft.chinacraft.init.CCCreativeTabs;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

/**
 * Created by Mouse on 2017/2/3.
 */
public class BlockCCRotatedPillar extends BlockRotatedPillar{

    public BlockCCRotatedPillar(Material materialIn) {
        super(materialIn);
        setCreativeTab(CCCreativeTabs.tabCore);
    }

    public BlockCCRotatedPillar setHarvestLevelReturnBlock(String toolClass, int level) {
        super.setHarvestLevel(toolClass, level);
        return this;
    }

    @Override
    public BlockCCRotatedPillar setSoundType(SoundType sound)
    {
        super.setSoundType(sound);
        return this;
    }
}
