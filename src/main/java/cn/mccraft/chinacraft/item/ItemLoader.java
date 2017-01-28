package cn.mccraft.chinacraft.item;

import cn.mccraft.chinacraft.util.ILoader;
import cn.mccraft.chinacraft.util.loader.Load;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class ItemLoader implements ILoader {
    public Item item = new Item().setUnlocalizedName("itemTest").setRegistryName("item_test");

    @Load
    public void registerItems() {
        GameRegistry.register(item);
    }

    @Load(side = Side.CLIENT)
    public void registerRenders() {

    }
}
