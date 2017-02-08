package cn.mccraft.chinacraft.block;

import cn.mccraft.chinacraft.init.CCCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockCCDoor extends BlockDoor {

	public BlockCCDoor(Material materialIn) {
		super(materialIn);
		setCreativeTab(CCCreativeTabs.tabCore);
	}

	public BlockCCDoor setHarvestLevelReturnBlock(String toolClass, int level) {
		super.setHarvestLevel(toolClass, level);
		return this;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER ? Items.AIR : Item.getItemFromBlock(this);
	}

	@Override
	public Block setSoundType(SoundType sound) {
		return super.setSoundType(sound);
	}
}
