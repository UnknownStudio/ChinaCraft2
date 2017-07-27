package cn.mccraft.chinacraft.block;

import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class BlockCCLog extends BlockCCRotatedPillar {

    public static final PropertyEnum<BlockLog.EnumAxis> LOG_AXIS = PropertyEnum.create("axis", BlockLog.EnumAxis.class);

    public BlockCCLog() {
        super(Material.WOOD);
        this.setHardness(2.0F);
        this.setSoundType(SoundType.WOOD);
        this.setDefaultState(this.blockState.getBaseState().withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
    }

    /**
     * Called serverside after this block is replaced with another in Chunk, but before the Tile Entity is updated
     */
    @Override
    public void breakBlock(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
        if (worldIn.isAreaLoaded(pos.add(-5, -5, -5), pos.add(5, 5, 5)))
            for (BlockPos blockpos : BlockPos.getAllInBox(pos.add(-4, -4, -4), pos.add(4, 4, 4))) {
                IBlockState iblockstate = worldIn.getBlockState(blockpos);

                if (iblockstate.getBlock().isLeaves(iblockstate, worldIn, blockpos))
                    iblockstate.getBlock().beginLeavesDecay(iblockstate, worldIn, blockpos);
            }
    }

    /**
     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
     * IBlockstate
     */
    @Nonnull
    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getStateFromMeta(meta).withProperty(LOG_AXIS, BlockLog.EnumAxis.fromFacingAxis(facing.getAxis()));
    }

    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    @Nonnull
    @Override
    public IBlockState withRotation(@Nonnull IBlockState state, Rotation rot) {
        switch (rot) {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:

                switch (state.getValue(LOG_AXIS)) {
                    case X:
                        return state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z);
                    case Z:
                        return state.withProperty(LOG_AXIS, BlockLog.EnumAxis.X);
                    default:
                        return state;
                }

            default:
                return state;
        }
    }

    @Override
    @Nonnull
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, LOG_AXIS);
    }

    @Override
    public boolean canSustainLeaves(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos) {
        return true;
    }

    @Override
    public boolean isWood(net.minecraft.world.IBlockAccess world, BlockPos pos) {
        return true;
    }
}
