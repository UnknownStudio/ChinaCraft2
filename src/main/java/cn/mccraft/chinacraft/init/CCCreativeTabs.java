package cn.mccraft.chinacraft.init;

import cn.mccraft.chinacraft.common.ChinaCraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public interface CCCreativeTabs {
    CreativeTabs tabCore = new CreativeTabs(ChinaCraft.MODID + "_core") {
        @Nonnull
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(Blocks.AIR);
        }
    };
}
