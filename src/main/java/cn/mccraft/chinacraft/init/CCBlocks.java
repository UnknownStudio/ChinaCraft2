package cn.mccraft.chinacraft.init;

import cn.mccraft.chinacraft.block.*;
import cn.mccraft.chinacraft.block.machine.BlockCrusher;
import cn.mccraft.chinacraft.block.machine.EnumCrusherMaterial;
import cn.mccraft.chinacraft.item.ItemCCDoor;
import cn.mccraft.chinacraft.item.ItemCCNewSlab;
import cn.mccraft.chinacraft.util.loader.annotation.RegBlock;
import net.minecraft.block.*;
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

    @RegBlock("marble")
    Block MARBLE = new BlockCCBase(Material.ROCK).setHarvestLevelReturnBlock("pickaxe",0).setSoundType(SoundType.STONE).setHardness(3.0F).setResistance(5.0F);

    @RegBlock({"smooth", "marble"})
    BlockCCBase SMOOTH_MARBLE = (BlockCCBase) new BlockCCBase(Material.ROCK).setHarvestLevelReturnBlock("pickaxe",0).setSoundType(SoundType.STONE).setHardness(3.0F).setResistance(5.0F);

    @RegBlock({"chiseled", "marble"})
    Block CHISELED_MARBLE = new BlockCCBase(Material.ROCK).setHarvestLevelReturnBlock("pickaxe",0).setSoundType(SoundType.STONE).setHardness(3.0F).setResistance(5.0F);

    @RegBlock({"pillar", "marble"})
    Block PILLAR_MARBLE = new BlockCCRotatedPillar(Material.ROCK).setHarvestLevelReturnBlock("pickaxe",0).setSoundType(SoundType.STONE).setHardness(3.0F).setResistance(5.0F);

    @RegBlock({"marble", "stairs"})
    Block MARBLE_STAIRS = new BlockCCStairs(SMOOTH_MARBLE.getDefaultState()).setHarvestLevelReturnBlock("pickaxe",0);

    @RegBlock(value = {"marble","slab"},itemClass = ItemCCNewSlab.class)
    Block MARBLE_SLAB = new BlockCCNewSlab(Material.ROCK).setHarvestLevelReturnBlock("pickaxe",0).setSoundType(SoundType.STONE).setHardness(3.0F).setResistance(5.0F);

    @RegBlock({"marble","wall"})
    Block MARBLE_WALL = new BlockCCWall(Material.ROCK).setHarvestLevelReturnBlock("pickaxe",0).setSoundType(SoundType.STONE).setHardness(3.0F).setResistance(5.0F);

    @RegBlock(value={"marble","door"},itemClass = ItemCCDoor.class)
    Block MARBLE_DOOR = new BlockCCDoor(Material.ROCK).setHarvestLevelReturnBlock("pickaxe",0).setSoundType(SoundType.STONE).setHardness(3.0F).setResistance(5.0F);

    @RegBlock({"black","brick","block"})
    Block BLACK_BRICK_BLOCK = new BlockCCBase(Material.ROCK).setHarvestLevelReturnBlock("pickaxe",0).setSoundType(SoundType.STONE).setHardness(3.0F).setResistance(5.0F);

    @RegBlock({"black","brick", "stairs"})
    Block BLACK_BRICK_STAIRS = new BlockCCStairs(SMOOTH_MARBLE.getDefaultState()).setHarvestLevelReturnBlock("pickaxe",0);

    @RegBlock(value = {"black","brick","slab"},itemClass = ItemCCNewSlab.class)
    Block BLACK_BRICK_SLAB = new BlockCCNewSlab(Material.ROCK).setHarvestLevelReturnBlock("pickaxe",0).setSoundType(SoundType.STONE).setHardness(3.0F).setResistance(5.0F);

    @RegBlock({"black","brick","wall"})
    Block BLACK_BRICK_WALL = new BlockCCWall(Material.ROCK).setHarvestLevelReturnBlock("pickaxe",0).setSoundType(SoundType.STONE).setHardness(3.0F).setResistance(5.0F);

    @RegBlock({"wooden","window"})
    Block WOODEN_WINDOW = new BlockCCPane(Material.WOOD).setCanDrop(true).setSoundType(SoundType.WOOD).setHardness(0.5F);

//    @RegBlock({"stone", "crusher"})
//    BlockCrusher STONE_CRUSHER = new BlockCrusher(EnumCrusherMaterial.STONE);
//
//    @RegBlock({"bronze", "crusher"})
//    BlockCrusher BRONZE_CRUSHER = new BlockCrusher(EnumCrusherMaterial.BRONZE);
//
//    @RegBlock({"iron", "crusher"})
//    BlockCrusher IRON_CRUSHER = new BlockCrusher(EnumCrusherMaterial.IRON);

    @RegBlock({"bamboo","plank"})
    Block BAMBOO_PLANK = new BlockCCBase(Material.WOOD).setSoundType(SoundType.WOOD).setHardness(2.0F).setResistance(5.0F);

    @RegBlock(value = {"bamboo","slab"},itemClass = ItemCCNewSlab.class)
    Block BAMBOO_SLAB = new BlockCCNewSlab(Material.WOOD).setSoundType(SoundType.WOOD).setHardness(2.0F).setResistance(5.0F);

    @RegBlock({"bamboo","stairs"})
    Block BAMBOO_STAIRS = new BlockCCStairs(BAMBOO_PLANK.getDefaultState());

    @RegBlock({"bamboo","fence"})
    Block BAMBOO_FENCE = new BlockCCFence(Material.WOOD).setSoundType(SoundType.WOOD).setHardness(2.0F).setResistance(5.0F);

    @RegBlock({"peeled","bamboo","plank"})
    Block PEELED_BAMBOO_PLANK = new BlockCCBase(Material.WOOD).setSoundType(SoundType.WOOD).setHardness(2.0F).setResistance(5.0F);

    @RegBlock(value = {"peeled","bamboo","slab"},itemClass = ItemCCNewSlab.class)
    Block PEELED_BAMBOO_SLAB = new BlockCCNewSlab(Material.WOOD).setSoundType(SoundType.WOOD).setHardness(2.0F).setResistance(5.0F);

    @RegBlock({"peeled","bamboo","stairs"})
    Block PEELED_BAMBOO_STAIRS = new BlockCCStairs(PEELED_BAMBOO_PLANK.getDefaultState());

    @RegBlock({"peeled","bamboo","fence"})
    Block PEELED_BAMBOO_FENCE = new BlockCCFence(Material.WOOD).setSoundType(SoundType.WOOD).setHardness(2.0F).setResistance(5.0F);
}
