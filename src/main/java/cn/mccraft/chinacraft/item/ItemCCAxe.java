package cn.mccraft.chinacraft.item;

import cn.mccraft.chinacraft.init.CCCreativeTabs;
import net.minecraft.item.ItemAxe;

/**
 * Created by Mouse on 2017/2/4.
 */
public class ItemCCAxe extends ItemAxe{

    public ItemCCAxe(ToolMaterial material) {
        super(material);
        setCreativeTab(CCCreativeTabs.tabCore);
    }

    public ItemCCAxe(ToolMaterial material, float damage, float speed) {
        super(material, damage, speed);
        setCreativeTab(CCCreativeTabs.tabCore);
    }
}
