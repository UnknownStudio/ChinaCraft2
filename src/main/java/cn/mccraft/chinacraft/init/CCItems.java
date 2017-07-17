package cn.mccraft.chinacraft.init;

import cn.mccraft.chinacraft.item.*;
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
    Item BRONZE_PICKAXE = new ItemCCPickaxe(BRONZE_TOOL_MATERIAL);

    @RegItem({"bronze", "axe"})
    Item BRONZE_AXE = new ItemCCAxe(BRONZE_TOOL_MATERIAL, 8.0F, -3.15F);

    @RegItem({"bronze", "shovel"})
    Item BRONZE_SHOVEL = new ItemCCSpade(BRONZE_TOOL_MATERIAL);

    @RegItem({"bronze", "sword"})
    Item BRONZE_SWORD = new ItemCCSword(BRONZE_TOOL_MATERIAL);

    @RegItem({"bronze", "hoe"})
    Item BRONZE_HOE = new ItemCCHoe(BRONZE_TOOL_MATERIAL);

    @RegItem({"black","brick"})
    ItemCCBase BLACK_BRICK = new ItemCCBase();

    @RegItem({"red", "packet"})
    ItemCCRedPacket RED_PACKET = new ItemCCRedPacket();

    @RegItem({"bamboo","item"})
    ItemCCBase BAMBOO_ITEM = new ItemCCBase();

    @RegItem({"peeled","bamboo","item"})
    ItemCCBase PEELED_BAMBOO_ITEM = new ItemCCBase();

    @RegItem({"art","knife"})
    ItemCCArtKnife ART_KNIFE = new ItemCCArtKnife();

    @RegItem({"silk"})
    ItemCCSilk SILK = new ItemCCSilk();


}
