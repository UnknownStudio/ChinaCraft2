package cn.mccraft.chinacraft.block;

import cn.mccraft.chinacraft.init.CCCreativeTabs;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.event.terraingen.TerrainGen;

import java.util.Random;

/**
 * Created by Mouse on 2017/2/5.
 */
public class BlockCCSapling extends BlockBush implements IGrowable {
    public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);
    protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);

    public BlockCCSapling() {
        this.setDefaultState(this.blockState.getBaseState().withProperty(STAGE, Integer.valueOf(0)));
        this.setCreativeTab(CCCreativeTabs.tabCore);
    }

    public BlockCCSapling(Material material) {
        super(material);
        this.setDefaultState(this.blockState.getBaseState().withProperty(STAGE, Integer.valueOf(0)));
        this.setCreativeTab(CCCreativeTabs.tabCore);
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
        if(((Integer)state.getValue(STAGE)).intValue() == 0) {
            worldIn.setBlockState(pos, state.cycleProperty(STAGE), 4);
        } else {
            this.generateTree(worldIn, pos, state, rand);
        }

    }

    //TODO:
    public void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if(TerrainGen.saplingGrowTree(worldIn, rand, pos)) {
            Object worldgenerator = rand.nextInt(10) == 0?new WorldGenBigTree(true):new WorldGenTrees(true);
            int i = 0;
            int j = 0;
            boolean flag = false;
            IBlockState iblockstate2;
            iblockstate2 = Blocks.AIR.getDefaultState();
            if(flag) {
                worldIn.setBlockState(pos.add(i, 0, j), iblockstate2, 4);
                worldIn.setBlockState(pos.add(i + 1, 0, j), iblockstate2, 4);
                worldIn.setBlockState(pos.add(i, 0, j + 1), iblockstate2, 4);
                worldIn.setBlockState(pos.add(i + 1, 0, j + 1), iblockstate2, 4);
            } else {
                worldIn.setBlockState(pos, iblockstate2, 4);
            }

            if(!((WorldGenerator)worldgenerator).generate(worldIn, rand, pos.add(i, 0, j))) {
                if(flag) {
                    worldIn.setBlockState(pos.add(i, 0, j), state, 4);
                    worldIn.setBlockState(pos.add(i + 1, 0, j), state, 4);
                    worldIn.setBlockState(pos.add(i, 0, j + 1), state, 4);
                    worldIn.setBlockState(pos.add(i + 1, 0, j + 1), state, 4);
                } else {
                    worldIn.setBlockState(pos, state, 4);
                }
            }

        }
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
        return this.getDefaultState().withProperty(STAGE, Integer.valueOf((meta & 8) >> 3));
    }

    public int getMetaFromState(IBlockState state) {
        return ((Integer)state.getValue(STAGE)).intValue();
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{STAGE});
    }
}
