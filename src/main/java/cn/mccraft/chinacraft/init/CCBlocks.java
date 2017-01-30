package cn.mccraft.chinacraft.init;

import cn.mccraft.chinacraft.util.loader.annotation.RegBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * All blocks provided by ChinaCraft mod.
 * 所有ChinaCraft模组中提供的方块。
 */
public interface CCBlocks {
    @RegBlock({"test"})
    Block BLOCK_TEST = new Block(Material.ROCK);
}
