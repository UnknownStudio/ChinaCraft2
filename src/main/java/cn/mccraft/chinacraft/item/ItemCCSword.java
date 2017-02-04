package cn.mccraft.chinacraft.item;

import cn.mccraft.chinacraft.init.CCCreativeTabs;
import net.minecraft.item.ItemSword;

/**
 * Created by Mouse on 2017/2/4.
 */
public class ItemCCSword extends ItemSword{
    public ItemCCSword(ToolMaterial material) {
        super(material);
        setCreativeTab(CCCreativeTabs.tabCore);
    }
}
