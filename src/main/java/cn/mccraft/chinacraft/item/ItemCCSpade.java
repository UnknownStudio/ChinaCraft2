package cn.mccraft.chinacraft.item;

import cn.mccraft.chinacraft.init.CCCreativeTabs;
import net.minecraft.item.ItemSpade;

/**
 * Created by Mouse on 2017/2/4.
 */
public class ItemCCSpade extends ItemSpade{
    public ItemCCSpade(ToolMaterial material) {
        super(material);
        setCreativeTab(CCCreativeTabs.tabCore);
    }
}
