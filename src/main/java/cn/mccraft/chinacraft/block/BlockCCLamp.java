package cn.mccraft.chinacraft.block;

import cn.mccraft.chinacraft.block.tileentity.TileEntityLamp;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockCCLamp extends BlockCCBase {
	protected static final AxisAlignedBB LAMP_AABB = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.75D, 0.75D);

	protected static final PropertyInteger LEVEL = PropertyInteger.create("level", 0, 3);
	protected static final PropertyBool ON = PropertyBool.create("on");

	private boolean isBurning;

	public BlockCCLamp() {
		super(Material.CIRCUITS);
		setDefaultState(blockState.getBaseState().withProperty(LEVEL, 0));
		isBurning = false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityLamp();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, LEVEL, ON);
	}

	@Override
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		if (isBurning) {
			isBurning = false;
		} else {
			ItemStack stack = null;
			if (playerIn.getHeldItemMainhand().getItem() == Items.FLINT_AND_STEEL)
				stack = playerIn.getHeldItemMainhand();
			else if (playerIn.getHeldItemOffhand().getItem() == Items.FLINT_AND_STEEL)
				stack = playerIn.getHeldItemOffhand();
			if (stack != null) {
				stack.damageItem(1, playerIn);
				isBurning = true;
			}
		}
	}

	@Nullable
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return LAMP_AABB;
	}
}
