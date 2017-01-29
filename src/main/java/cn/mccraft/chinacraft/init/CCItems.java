package cn.mccraft.chinacraft.init;

import cn.mccraft.chinacraft.util.loader.annotation.RegItem;
import net.minecraft.item.Item;

/**
 * Created by Mouse on 2017/1/28.
 */
public interface CCItems {
    @RegItem({"item", "test"})
    Item ITEM_TEST = new Item();
}
