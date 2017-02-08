package cn.mccraft.chinacraft.item;

import cn.mccraft.chinacraft.init.CCCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.item.ItemDoor;

public class ItemCCDoor extends ItemDoor {

	public ItemCCDoor(Block block) {
		super(block);
		setCreativeTab(CCCreativeTabs.tabCore);
	}

}
