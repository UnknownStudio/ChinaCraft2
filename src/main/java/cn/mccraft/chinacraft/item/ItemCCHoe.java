package cn.mccraft.chinacraft.item;

import cn.mccraft.chinacraft.init.CCCreativeTabs;
import net.minecraft.item.ItemHoe;

/**
 * Created by Mouse on 2017/2/4.
 */
public class ItemCCHoe extends ItemHoe{
    public ItemCCHoe(ToolMaterial material) {
        super(material);
        setCreativeTab(CCCreativeTabs.tabCore);
    }
}
