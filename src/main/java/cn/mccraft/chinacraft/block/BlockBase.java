package cn.mccraft.chinacraft.block;

import cn.mccraft.chinacraft.common.ChinaCraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created by Mouse on 2017/1/28.
 */
public class BlockBase extends Block{

    public BlockBase(Material materialIn) {
        super(materialIn);
        setCreativeTab(ChinaCraft.tabCore);
    }
}
