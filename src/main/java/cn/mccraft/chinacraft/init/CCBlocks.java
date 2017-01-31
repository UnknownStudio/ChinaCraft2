package cn.mccraft.chinacraft.init;

import cn.mccraft.chinacraft.block.machine.BlockCrusher;
import cn.mccraft.chinacraft.util.loader.annotation.RegBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;

/**
 * All blocks provided by ChinaCraft mod.
 * 所有ChinaCraft模组中提供的方块。
 */
public interface CCBlocks {

    @RegBlock(value = {"copper", "ore"}, oreDict = {"oreCopper"})
    Block COPPER_ORE = new BlockOre().setHardness(3.0F).setResistance(5.0F);

    @RegBlock(value = {"tin", "ore"}, oreDict = {"oreTin"})
    Block TIN_ORE = new BlockOre().setHardness(3.0F).setResistance(5.0F);

    @RegBlock({"stone", "crusher"})
    BlockCrusher STONE_CRUSHER = new BlockCrusher();

    @RegBlock({"bronze", "crusher"})
    BlockCrusher BRONZE_CRUSHER = new BlockCrusher();

    @RegBlock({"iron", "crusher"})
    BlockCrusher IRON_CRUSHER = new BlockCrusher();
}
