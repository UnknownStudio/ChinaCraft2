package cn.mccraft.chinacraft.init;

import cn.mccraft.chinacraft.item.ItemCCBase;
import cn.mccraft.chinacraft.item.ItemCCRedPacket;
import cn.mccraft.chinacraft.util.loader.annotation.RegItem;
import net.minecraft.item.*;
import net.minecraftforge.common.util.EnumHelper;

/**
 * All items provided by ChinaCraft mod.
 * 所有ChinaCraft模组中提供的物品。
 */
public interface CCItems {
    Item.ToolMaterial BRONZE_TOOL_MATERIAL = EnumHelper.addToolMaterial("BRONZE", 2, 232, 5.0F, 1.75F, 9);

    @RegItem(value = {"copper", "ingot"}, oreDict = {"ingotCopper"})
    ItemCCBase COPPER_INGOT = new ItemCCBase();

    @RegItem(value = {"tin", "ingot"}, oreDict = {"ingotTin"})
    ItemCCBase TIN_INGOT = new ItemCCBase();

    @RegItem(value = {"bronze", "ingot"}, oreDict = {"ingotBronze"})
    ItemCCBase BRONZE_INGOT = new ItemCCBase();

    @RegItem(value = {"silver", "ingot"}, oreDict = {"ingotSilver"})
    ItemCCBase SILVER_INGOT = new ItemCCBase();

    @RegItem({"bronze", "pickaxe"})
    ItemPickaxe BRONZE_PICKAXE = new ItemPickaxe(BRONZE_TOOL_MATERIAL){};

    @RegItem({"bronze", "axe"})
    ItemAxe BRONZE_AXE = new ItemAxe(BRONZE_TOOL_MATERIAL, 8.0F, -3.15F){};

    @RegItem({"bronze", "shovel"})
    ItemSpade BRONZE_SHOVEL = new ItemSpade(BRONZE_TOOL_MATERIAL){};

    @RegItem({"bronze", "sword"})
    ItemSword BRONZE_SWORD = new ItemSword(BRONZE_TOOL_MATERIAL){};

    @RegItem({"red", "packet"})
    ItemCCRedPacket RED_PACKET = new ItemCCRedPacket();
}
