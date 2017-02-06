package cn.mccraft.chinacraft.block;

import cn.mccraft.chinacraft.init.CCCreativeTabs;
import net.minecraft.block.BlockPane;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

/**
 * Created by Mouse on 2017/2/5.
 */
public class BlockCCPane extends BlockPane{

    public BlockCCPane(Material materialIn, boolean canDrop) {
        super(materialIn, canDrop);
        setCreativeTab(CCCreativeTabs.tabCore);
    }

    public BlockCCPane setHarvestLevelReturnBlock(String toolClass, int level) {
        super.setHarvestLevel(toolClass, level);
        return this;
    }

    @Override
    public BlockCCPane setSoundType(SoundType sound)
    {
        super.setSoundType(sound);
        return this;
    }

}
