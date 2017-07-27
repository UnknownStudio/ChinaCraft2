package cn.mccraft.chinacraft.common;

import biomesoplenty.common.world.generator.tree.GeneratorBasicTree;
import biomesoplenty.common.world.generator.tree.GeneratorTreeBase;
import cn.mccraft.chinacraft.block.BlockCCLeaf;
import cn.mccraft.chinacraft.block.BlockCCLog;
import cn.mccraft.chinacraft.block.BlockCCSapling;
import cn.mccraft.util.loader.Load;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.HashSet;
import java.util.Set;

public class TreeLoader {
    public static final Set<GeneratorTreeBase> GENERATOR_TREES = new HashSet<>();

    @Load
    public void load() {
        BlockCCLog cherryLog = new BlockCCLog();
        BlockCCSapling cherrySapling = new BlockCCSapling(null);
        BlockCCLeaf cherryLeaf = new BlockCCLeaf(cherrySapling);
        GeneratorBasicTree generatorCherry = new GeneratorBasicTree.Builder().log(cherryLog.getDefaultState().withProperty(BlockCCLog.LOG_AXIS, BlockLog.EnumAxis.Y))
        .leaves(cherryLeaf.getDefaultState()).amountPerChunk(0.7f).placeOn(Material.GROUND, Material.GRASS).create();
        cherrySapling.setTreeGen(generatorCherry);
        GameRegistry.register(cherryLog.setUnlocalizedName("cherryLog").setRegistryName("cherry_log"));
        GameRegistry.register(cherrySapling.setUnlocalizedName("cherrySapling").setRegistryName("cherry_sapling"));
        GameRegistry.register(cherryLeaf.setUnlocalizedName("cherryLeaf").setRegistryName("cherry_leaf"));
        GENERATOR_TREES.add(generatorCherry);
    }
}
