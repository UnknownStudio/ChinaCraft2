package cn.mccraft.chinacraft.init;

import cn.mccraft.chinacraft.item.ItemBase;
import cn.mccraft.chinacraft.item.ItemRedPacket;
import cn.mccraft.chinacraft.util.loader.annotation.RegItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.*;
import net.minecraftforge.common.util.EnumHelper;

/**
 * All items provided by ChinaCraft mod.
 * 所有ChinaCraft模组中提供的物品。
 */
public interface CCItems {
    Item.ToolMaterial BRONZE_TOOL_MATERIAL = EnumHelper.addToolMaterial("BRONZE", 2, 232, 5.0F, 1.75F, 9);

    @RegItem(value = {"copper", "ingot"}, oreDict = {"ingotCopper"})
    ItemBase COPPER_INGOT = new ItemBase();

    @RegItem(value = {"tin", "ingot"}, oreDict = {"ingotTin"})
    ItemBase TIN_INGOT = new ItemBase();

    @RegItem(value = {"bronze", "ingot"}, oreDict = {"ingotBronze"})
    ItemBase BRONZE_INGOT = new ItemBase();

    @RegItem(value = {"silver", "ingot"}, oreDict = {"ingotSilver"})
    ItemBase SILVER_INGOT = new ItemBase();

    @RegItem({"bronze", "pickaxe"})
    Item BRONZE_PICKAXE = new ItemPickaxe(BRONZE_TOOL_MATERIAL){}.setCreativeTab(CreativeTabs.TOOLS);

    @RegItem({"bronze", "axe"})
    Item BRONZE_AXE = new ItemAxe(BRONZE_TOOL_MATERIAL, 8.0F, -3.15F){}.setCreativeTab(CreativeTabs.TOOLS);

    @RegItem({"bronze", "shovel"})
    Item BRONZE_SHOVEL = new ItemSpade(BRONZE_TOOL_MATERIAL){}.setCreativeTab(CreativeTabs.TOOLS);

    @RegItem({"bronze", "sword"})
    Item BRONZE_SWORD = new ItemSword(BRONZE_TOOL_MATERIAL){}.setCreativeTab(CreativeTabs.TOOLS);

    @RegItem({"bronze", "hoe"})
    Item BRONZE_HOE = new ItemHoe(BRONZE_TOOL_MATERIAL){}.setCreativeTab(CreativeTabs.TOOLS);

    @RegItem({"red", "packet"})
    ItemRedPacket RED_PACKET = new ItemRedPacket();
}
