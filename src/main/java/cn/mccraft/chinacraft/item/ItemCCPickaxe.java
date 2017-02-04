package cn.mccraft.chinacraft.item;

import cn.mccraft.chinacraft.init.CCCreativeTabs;
import net.minecraft.item.ItemPickaxe;

/**
 * Created by Mouse on 2017/2/4.
 */
public class ItemCCPickaxe extends ItemPickaxe{
    public ItemCCPickaxe(ToolMaterial material) {
        super(material);
        setCreativeTab(CCCreativeTabs.tabCore);
    }
}
