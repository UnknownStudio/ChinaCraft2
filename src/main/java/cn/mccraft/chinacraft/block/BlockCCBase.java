package cn.mccraft.chinacraft.block;

import cn.mccraft.chinacraft.init.CCCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

/**
 * Base block of ChinaCraft.
 * Extend this block in your own blocks.
 * ChinaCraft基础方块类，请在你的自定义方块中继承该类。
 */
public class BlockCCBase extends Block{

    public BlockCCBase(Material materialIn) {
        this(materialIn, materialIn.getMaterialMapColor());
    }

    public BlockCCBase(Material materialIn, MapColor blockMapColorIn) {
        super(materialIn,blockMapColorIn);
        setCreativeTab(CCCreativeTabs.tabCore);
    }

    public BlockCCBase setHarvestLevelReturnBlock(String toolClass, int level) {
        super.setHarvestLevel(toolClass, level);
        return this;
    }

    @Override
    public BlockCCBase setSoundType(SoundType sound)
    {
        super.setSoundType(sound);
        return this;
    }

    public float getResistance(){
        return blockResistance / 3.0F;
    }

    public float getHardness(){
        return blockHardness / 5.0F;
    }
}
