package cn.mccraft.chinacraft.init;

import cn.mccraft.chinacraft.block.BlockOre;
import cn.mccraft.chinacraft.block.machine.BlockCrusher;
import cn.mccraft.chinacraft.util.loader.annotation.RegBlock;
import net.minecraft.block.Block;

/**
 * All blocks provided by ChinaCraft mod.
 * 所有ChinaCraft模组中提供的方块。
 */
public interface CCBlocks {

    @RegBlock(value = {"copper", "ore"}, oreDict = {"oreCopper"})
    Block COPPER_ORE = new BlockOre().setHarvestLevelReturnBlock("pickaxe", 1);

    @RegBlock(value = {"tin", "ore"}, oreDict = {"oreTin"})
    Block TIN_ORE = new BlockOre().setHarvestLevelReturnBlock("pickaxe", 1);

    @RegBlock(value = {"silver", "ore"}, oreDict = {"oreSilver"})
    Block SILVER_ORE = new BlockOre().setHarvestLevelReturnBlock("pickaxe", 2);

    @RegBlock({"stone", "crusher"})
    BlockCrusher STONE_CRUSHER = new BlockCrusher();

    @RegBlock({"bronze", "crusher"})
    BlockCrusher BRONZE_CRUSHER = new BlockCrusher();

    @RegBlock({"iron", "crusher"})
    BlockCrusher IRON_CRUSHER = new BlockCrusher();
}
