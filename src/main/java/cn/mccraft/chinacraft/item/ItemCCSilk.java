package cn.mccraft.chinacraft.item;

import cn.mccraft.chinacraft.init.CCCapabilities;
import cn.mccraft.chinacraft.init.CCCreativeTabs;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemCCSilk extends ItemCCBase {
    public ItemCCSilk() {
        setCreativeTab(CCCreativeTabs.tabSilkworm);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer worldIn, List<String> tooltip, boolean flagIn) {
        tooltip.add(I18n.format("item.silk.lore", stack.getCapability(CCCapabilities.ITEM_STACK_COLORABLE_CAPABILITY, null).getColor()));
    }

    @Override
    public void getSubItems(@Nonnull Item item, CreativeTabs tab, NonNullList<ItemStack> stacks) {
        try {
            for (EnumDyeColor color : EnumDyeColor.values()) {
                ItemStack stack = new ItemStack(item);
                stack.getCapability(CCCapabilities.ITEM_STACK_COLORABLE_CAPABILITY, null).setColor(color.func_176768_e().colorValue);
                stacks.add(stack);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
