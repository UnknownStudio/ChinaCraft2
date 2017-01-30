package cn.mccraft.chinacraft.init;

import cn.mccraft.chinacraft.item.ItemRedPacket;
import cn.mccraft.chinacraft.util.loader.annotation.RegItem;
import net.minecraft.item.Item;

/**
 * All items provided by ChinaCraft mod.
 * 所有ChinaCraft模组中提供的物品。
 */
public interface CCItems {
    @RegItem({"testDebug"})
    Item ITEM_TEST = new Item();

    @RegItem({"red", "packet"})
    ItemRedPacket ITEM_RED_PACKET = new ItemRedPacket();
}
