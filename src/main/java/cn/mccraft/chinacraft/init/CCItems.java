package cn.mccraft.chinacraft.init;

import cn.mccraft.chinacraft.item.ItemIngot;
import cn.mccraft.chinacraft.item.ItemRedPacket;
import cn.mccraft.chinacraft.util.loader.annotation.RegItem;
import net.minecraft.item.*;
import net.minecraftforge.common.util.EnumHelper;

/**
 * All items provided by ChinaCraft mod.
 * 所有ChinaCraft模组中提供的物品。
 */
public interface CCItems {
    Item.ToolMaterial BRONZE_TOOL_MATERIAL = EnumHelper.addToolMaterial("BRONZE", 2, 232, 5.0F, 1.75F, 9);

    @RegItem({"test", "debug"})
    Item TEST_DEBUG = new Item();

    @RegItem({"red", "packet"})
    ItemRedPacket RED_PACKET = new ItemRedPacket();

    @RegItem(value = {"bronze", "ingot"}, oreDict = {"ingotBronze"})
    ItemIngot BRONZE_INGOT = new ItemIngot();

    @RegItem({"bronze", "pickaxe"})
    ItemPickaxe BRONZE_PICKAXE = new ItemPickaxe(BRONZE_TOOL_MATERIAL){};

    @RegItem({"bronze", "axe"})
    ItemAxe BRONZE_AXE = new ItemAxe(BRONZE_TOOL_MATERIAL){};

    @RegItem({"bronze", "shovel"})
    ItemSpade BRONZE_SHOVEL = new ItemSpade(BRONZE_TOOL_MATERIAL){};

    @RegItem({"bronze", "sword"})
    ItemSword BRONZE_SWORD = new ItemSword(BRONZE_TOOL_MATERIAL){};
}
