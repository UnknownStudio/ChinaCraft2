package cn.mccraft.chinacraft.block;

import cn.mccraft.chinacraft.init.CCCreativeTabs;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

import java.util.Random;

public class BlockCCSapling extends BlockBush implements IGrowable {
    public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);
    protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);
    private WorldGenerator treeGen;

    public BlockCCSapling(WorldGenerator treeGen) {
        this(Material.PLANTS, treeGen);
    }

    public BlockCCSapling(Material material, WorldGenerator treeGen) {
        super(material);
        this.treeGen = treeGen;
        this.setDefaultState(this.blockState.getBaseState().withProperty(STAGE, Integer.valueOf(0)));
        this.setCreativeTab(CCCreativeTabs.tabCore);
    }

    public WorldGenerator getTreeGen() {
        return treeGen;
    }

    public void setTreeGen(WorldGenerator treeGen) {
        this.treeGen = treeGen;
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return SAPLING_AABB;
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if(!worldIn.isRemote) {
            super.updateTick(worldIn, pos, state, rand);
            if(worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0) {
                this.grow(worldIn, pos, state, rand);
            }
        }

    }

    public void grow(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if(state.getValue(STAGE).intValue() == 0) {
            worldIn.setBlockState(pos, state.cycleProperty(STAGE), 4);
        } else {
            this.generateTree(worldIn, pos, state, rand);
        }

    }

    public void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if(!TerrainGen.saplingGrowTree(worldIn, rand, pos)) return; //发出事件

        worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);//删除树苗

        if(treeGen.generate(worldIn, rand, pos))  return;//生成树

        worldIn.setBlockState(pos, state, 4);//如果生成失败，放回树苗

    }

    public int damageDropped(IBlockState state) {
        return 0;
    }

    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return true;
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return (double)worldIn.rand.nextFloat() < 0.45D;
    }

    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        this.grow(worldIn, pos, state, rand);
    }

    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(STAGE, (meta & 8) >> 3);
    }

    public int getMetaFromState(IBlockState state) {
        return state.getValue(STAGE);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, STAGE);
    }
}
