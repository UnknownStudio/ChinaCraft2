package cn.mccraft.chinacraft.item;

import cn.mccraft.chinacraft.block.BlockCCNewSlab;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCCNewSlab extends ItemBlock
{
    public ItemCCNewSlab(Block block){
        super(block);
    }

    @Override
    public BlockCCNewSlab getBlock() {
        return (BlockCCNewSlab) super.getBlock();
    }

    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemstack = player.getHeldItem(hand);

        if (!itemstack.isEmpty() && player.canPlayerEdit(pos.offset(facing), facing, itemstack)) {
            IBlockState iblockstate = worldIn.getBlockState(pos);

            if (isSingle(iblockstate)) {
                BlockCCNewSlab.EnumBlockSlab type = iblockstate.getValue(BlockCCNewSlab.SLAB_TYPE);

                if ((facing == EnumFacing.UP && type == BlockCCNewSlab.EnumBlockSlab.BOTTOM || facing == EnumFacing.DOWN && type == BlockCCNewSlab.EnumBlockSlab.TOP)) {
                    IBlockState iblockstate1 = getBlock().getDefaultState().withProperty(BlockCCNewSlab.SLAB_TYPE, BlockCCNewSlab.EnumBlockSlab.DOUBLE);
                    AxisAlignedBB axisalignedbb = iblockstate1.getCollisionBoundingBox(worldIn, pos);

                    if (axisalignedbb != Block.NULL_AABB && worldIn.checkNoEntityCollision(axisalignedbb.offset(pos)) && worldIn.setBlockState(pos, iblockstate1, 11)) {
                        SoundType soundtype = block.getSoundType(iblockstate1, worldIn, pos, player);
                        worldIn.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                        itemstack.shrink(1);
                    }

                    return EnumActionResult.SUCCESS;
                }
            }

            return this.tryPlace(player, itemstack, worldIn, pos.offset(facing)) ? EnumActionResult.SUCCESS : super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
        } else {
            return EnumActionResult.FAIL;
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side, EntityPlayer player, ItemStack stack) {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        if (isSingle(iblockstate)) {
            boolean flag = iblockstate.getValue(BlockCCNewSlab.SLAB_TYPE) == BlockCCNewSlab.EnumBlockSlab.TOP;
            if (side == EnumFacing.UP && !flag || side == EnumFacing.DOWN && flag)
                return true;
        }

        return isSingle(iblockstate) ? true : super.canPlaceBlockOnSide(worldIn, pos, side, player, stack);
    }

    private boolean tryPlace(EntityPlayer player, ItemStack stack, World worldIn, BlockPos pos) {
        IBlockState iblockstate = worldIn.getBlockState(pos);

        if (isSingle(iblockstate)) {
            IBlockState iblockstate1 = getBlock().getDefaultState().withProperty(BlockCCNewSlab.SLAB_TYPE, BlockCCNewSlab.EnumBlockSlab.DOUBLE);
            AxisAlignedBB axisalignedbb = iblockstate1.getCollisionBoundingBox(worldIn, pos);

            if (axisalignedbb != Block.NULL_AABB && worldIn.checkNoEntityCollision(axisalignedbb.offset(pos)) && worldIn.setBlockState(pos, iblockstate1, 11)) {
                SoundType soundtype = block.getSoundType(iblockstate1, worldIn, pos, player);
                worldIn.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                stack.shrink(1);
            }
            return true;
        }
        return false;
    }

    private boolean isSingle(IBlockState state){
        return state.getBlock() instanceof BlockCCNewSlab && state.getValue(BlockCCNewSlab.SLAB_TYPE) != BlockCCNewSlab.EnumBlockSlab.DOUBLE;
    }
}
