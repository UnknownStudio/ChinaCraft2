package cn.mccraft.chinacraft.init;

import cn.mccraft.chinacraft.block.BlockCCBase;
import cn.mccraft.chinacraft.block.BlockCCOre;
import cn.mccraft.chinacraft.block.machine.BlockCrusher;
import cn.mccraft.chinacraft.util.loader.annotation.RegBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

/**
 * All blocks provided by ChinaCraft mod.
 * 所有ChinaCraft模组中提供的方块。
 */
public interface CCBlocks {

    @RegBlock(value = {"copper", "ore"}, oreDict = {"oreCopper"})
    Block COPPER_ORE = new BlockCCOre().setHarvestLevelReturnBlock("pickaxe", 1);

    @RegBlock(value = {"tin", "ore"}, oreDict = {"oreTin"})
    Block TIN_ORE = new BlockCCOre().setHarvestLevelReturnBlock("pickaxe", 1);

    @RegBlock(value = {"silver", "ore"}, oreDict = {"oreSilver"})
    Block SILVER_ORE = new BlockCCOre().setHarvestLevelReturnBlock("pickaxe", 2);

    @RegBlock(value = {"copper", "block"}, oreDict = {"blockCopper"})
    Block COPPER_BLOCK = new BlockCCBase(Material.IRON).setHarvestLevelReturnBlock("pickaxe", 1).setSoundType(SoundType.STONE).setHardness(3.0F).setResistance(5.0F);

    @RegBlock(value = {"tin", "block"}, oreDict = {"blockTin"})
    Block TIN_BLOCK = new BlockCCBase(Material.IRON).setHarvestLevelReturnBlock("pickaxe", 1).setSoundType(SoundType.STONE).setHardness(3.0F).setResistance(5.0F);

    @RegBlock(value = {"bronze", "block"}, oreDict = {"blockBronze"})
    Block BRONZE_BLOCK = new BlockCCBase(Material.IRON).setHarvestLevelReturnBlock("pickaxe", 1).setSoundType(SoundType.STONE).setHardness(3.0F).setResistance(5.0F);

    @RegBlock(value = {"silver", "block"}, oreDict = {"blockSilver"})
    Block SILVER_BLOCK = new BlockCCBase(Material.IRON).setHarvestLevelReturnBlock("pickaxe", 2).setSoundType(SoundType.STONE).setHardness(3.0F).setResistance(5.0F);

    @RegBlock({"stone", "crusher"})
    BlockCrusher STONE_CRUSHER = new BlockCrusher();

    @RegBlock({"bronze", "crusher"})
    BlockCrusher BRONZE_CRUSHER = new BlockCrusher();

    @RegBlock({"iron", "crusher"})
    BlockCrusher IRON_CRUSHER = new BlockCrusher();
}
