package cn.mccraft.chinacraft.init;

import cn.mccraft.chinacraft.common.ChinaCraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

/**
 * All creative tabs provided by ChinaCraft mod.
 * 所有ChinaCraft模组中提供的创造物品栏。
 */
public interface CCCreativeTabs {
    CreativeTabs tabCore = new CreativeTabs(ChinaCraft.MODID + "Core") {
        @Nonnull
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(CCBlocks.COPPER_ORE);
        }
    };

    CreativeTabs tabMaterials = new CreativeTabs(ChinaCraft.MODID + "Materials") {
        @Nonnull
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(CCItems.BRONZE_INGOT);
        }
    };

    CreativeTabs tabSilkworm = new CreativeTabs(ChinaCraft.MODID + "Silkworm") {
        @Nonnull
        @Override
        public ItemStack getTabIconItem() {
            ItemStack stack = new ItemStack(CCItems.SILK);
            stack.getCapability(CCCapabilities.ITEM_STACK_COLORABLE_CAPABILITY, null).setColor(EnumDyeColor.WHITE.func_176768_e().colorValue);
            return stack;
        }
    };
}
