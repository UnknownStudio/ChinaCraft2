package cn.mccraft.chinacraft.init;

import cn.mccraft.chinacraft.util.loader.annotation.RegBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created by Mouse on 2017/1/28.
 */
public interface CCBlocks {
    @RegBlock({"block", "test"})
    Block BLOCK_TEST = new Block(Material.ROCK);
}
