package cn.mccraft.chinacraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Mouse on 2017/2/2.
 * TODO:
 */
public class BlockCCLeaf extends BlockCCBase implements IShearable {
    public static final PropertyBool DECAYABLE = PropertyBool.create("decayable");
    public static final PropertyBool CHECK_DECAY = PropertyBool.create("check_decay");
    protected boolean leavesFancy;
    private int[] surroundings;
    private final Block sapling;

    public BlockCCLeaf(Block sapling) {
        super(Material.LEAVES);
        this.sapling = sapling;
        this.setTickRandomly(true);
        this.setHardness(0.2F);
        this.setLightOpacity(1);
        this.setSoundType(SoundType.PLANT);
    }

    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        boolean i = true;
        boolean j = true;
        int k = pos.getX();
        int l = pos.getY();
        int i1 = pos.getZ();
        if(worldIn.isAreaLoaded(new BlockPos(k - 2, l - 2, i1 - 2), new BlockPos(k + 2, l + 2, i1 + 2))) {
            for(int j1 = -1; j1 <= 1; ++j1) {
                for(int k1 = -1; k1 <= 1; ++k1) {
                    for(int l1 = -1; l1 <= 1; ++l1) {
                        BlockPos blockpos = pos.add(j1, k1, l1);
                        IBlockState iblockstate = worldIn.getBlockState(blockpos);
                        if(iblockstate.getBlock().isLeaves(iblockstate, worldIn, blockpos)) {
                            iblockstate.getBlock().beginLeavesDecay(iblockstate, worldIn, blockpos);
                        }
                    }
                }
            }
        }
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if(!worldIn.isRemote && ((Boolean)state.getValue(CHECK_DECAY)).booleanValue() && ((Boolean)state.getValue(DECAYABLE)).booleanValue()) {
            boolean i = true;
            boolean j = true;
            int k = pos.getX();
            int l = pos.getY();
            int i1 = pos.getZ();
            boolean j1 = true;
            boolean k1 = true;
            boolean l1 = true;
            if(this.surroundings == null) {
                this.surroundings = new int[32768];
            }

            if(worldIn.isAreaLoaded(new BlockPos(k - 5, l - 5, i1 - 5), new BlockPos(k + 5, l + 5, i1 + 5))) {
                BlockPos.MutableBlockPos l2 = new BlockPos.MutableBlockPos();
                int i3 = -4;

                label110:
                while(true) {
                    int j3;
                    int k3;
                    if(i3 > 4) {
                        i3 = 1;

                        while(true) {
                            if(i3 > 4) {
                                break label110;
                            }

                            for(j3 = -4; j3 <= 4; ++j3) {
                                for(k3 = -4; k3 <= 4; ++k3) {
                                    for(int var20 = -4; var20 <= 4; ++var20) {
                                        if(this.surroundings[(j3 + 16) * 1024 + (k3 + 16) * 32 + var20 + 16] == i3 - 1) {
                                            if(this.surroundings[(j3 + 16 - 1) * 1024 + (k3 + 16) * 32 + var20 + 16] == -2) {
                                                this.surroundings[(j3 + 16 - 1) * 1024 + (k3 + 16) * 32 + var20 + 16] = i3;
                                            }

                                            if(this.surroundings[(j3 + 16 + 1) * 1024 + (k3 + 16) * 32 + var20 + 16] == -2) {
                                                this.surroundings[(j3 + 16 + 1) * 1024 + (k3 + 16) * 32 + var20 + 16] = i3;
                                            }

                                            if(this.surroundings[(j3 + 16) * 1024 + (k3 + 16 - 1) * 32 + var20 + 16] == -2) {
                                                this.surroundings[(j3 + 16) * 1024 + (k3 + 16 - 1) * 32 + var20 + 16] = i3;
                                            }

                                            if(this.surroundings[(j3 + 16) * 1024 + (k3 + 16 + 1) * 32 + var20 + 16] == -2) {
                                                this.surroundings[(j3 + 16) * 1024 + (k3 + 16 + 1) * 32 + var20 + 16] = i3;
                                            }

                                            if(this.surroundings[(j3 + 16) * 1024 + (k3 + 16) * 32 + (var20 + 16 - 1)] == -2) {
                                                this.surroundings[(j3 + 16) * 1024 + (k3 + 16) * 32 + (var20 + 16 - 1)] = i3;
                                            }

                                            if(this.surroundings[(j3 + 16) * 1024 + (k3 + 16) * 32 + var20 + 16 + 1] == -2) {
                                                this.surroundings[(j3 + 16) * 1024 + (k3 + 16) * 32 + var20 + 16 + 1] = i3;
                                            }
                                        }
                                    }
                                }
                            }

                            ++i3;
                        }
                    }

                    for(j3 = -4; j3 <= 4; ++j3) {
                        for(k3 = -4; k3 <= 4; ++k3) {
                            IBlockState l3 = worldIn.getBlockState(l2.setPos(k + i3, l + j3, i1 + k3));
                            Block block = l3.getBlock();
                            if(!block.canSustainLeaves(l3, worldIn, l2.setPos(k + i3, l + j3, i1 + k3))) {
                                if(block.isLeaves(l3, worldIn, l2.setPos(k + i3, l + j3, i1 + k3))) {
                                    this.surroundings[(i3 + 16) * 1024 + (j3 + 16) * 32 + k3 + 16] = -2;
                                } else {
                                    this.surroundings[(i3 + 16) * 1024 + (j3 + 16) * 32 + k3 + 16] = -1;
                                }
                            } else {
                                this.surroundings[(i3 + 16) * 1024 + (j3 + 16) * 32 + k3 + 16] = 0;
                            }
                        }
                    }

                    ++i3;
                }
            }

            int var19 = this.surroundings[16912];
            if(var19 >= 0) {
                worldIn.setBlockState(pos, state.withProperty(CHECK_DECAY, Boolean.valueOf(false)), 4);
            } else {
                this.destroy(worldIn, pos);
            }
        }

    }

    private void destroy(World worldIn, BlockPos pos) {
        this.dropBlockAsItem(worldIn, pos, worldIn.getBlockState(pos), 0);
        worldIn.setBlockToAir(pos);
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        // FIXME Check isOpaqueCube()
        if(worldIn.isRainingAt(pos.up()) && !worldIn.getBlockState(pos.down()).isOpaqueCube() && rand.nextInt(15) == 1) {
            double d0 = (double)((float)pos.getX() + rand.nextFloat());
            double d1 = (double)pos.getY() - 0.05D;
            double d2 = (double)((float)pos.getZ() + rand.nextFloat());
            worldIn.spawnParticle(EnumParticleTypes.DRIP_WATER, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
        }

    }

    public int quantityDropped(Random random) {
        return random.nextInt(20) == 0?1:0;
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(sapling);
    }

    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
        super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
    }

    protected int getSaplingDropChance(IBlockState state) {
        return 20;
    }

    public boolean isOpaqueCube(IBlockState state) {
        return !this.leavesFancy;
    }

    @SideOnly(Side.CLIENT)
    public void setGraphicsLevel(boolean fancy) {
        this.leavesFancy = fancy;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return this.leavesFancy?BlockRenderLayer.CUTOUT_MIPPED:BlockRenderLayer.SOLID;
    }

    public boolean causesSuffocation(IBlockState state) {
        return false;
    }

    //public abstract BlockPlanks.EnumType getWoodType(int var1);

    public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
        return true;
    }

    @Override
    public List<ItemStack> onSheared(@Nonnull ItemStack itemStack, IBlockAccess iBlockAccess, BlockPos blockPos, int i) {
        return NonNullList.withSize(1, new ItemStack(this, 1));
    }

    public boolean isLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
        return true;
    }

    public void beginLeavesDecay(IBlockState state, World world, BlockPos pos) {
        if(!((Boolean)state.getValue(CHECK_DECAY)).booleanValue()) {
            world.setBlockState(pos, state.withProperty(CHECK_DECAY, Boolean.valueOf(true)), 4);
        }
    }

    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        ArrayList ret = new ArrayList();
        Random rand = world instanceof World?((World)world).rand:new Random();
        int chance = this.getSaplingDropChance(state);
        if(fortune > 0) {
            chance -= 2 << fortune;
            if(chance < 10) {
                chance = 10;
            }
        }

        if(rand.nextInt(chance) == 0) {
            ItemStack drop = new ItemStack(this.getItemDropped(state, rand, fortune), 1, this.damageDropped(state));
            if(!drop.isEmpty()) {
                ret.add(drop);
            }
        }

        chance = 200;
        if(fortune > 0) {
            chance -= 10 << fortune;
            if(chance < 40) {
                chance = 40;
            }
        }

        this.captureDrops(true);

        ret.addAll(this.captureDrops(false));
        return ret;
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return !this.leavesFancy && blockAccess.getBlockState(pos.offset(side)).getBlock() == this?false:super.shouldSideBeRendered(blockState, blockAccess, pos, side);
    }
}
