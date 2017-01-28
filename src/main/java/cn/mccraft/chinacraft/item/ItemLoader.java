package cn.mccraft.chinacraft.item;

import cn.mccraft.chinacraft.util.loader.ILoader;
import cn.mccraft.chinacraft.util.loader.annotation.Load;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class ItemLoader implements ILoader {

    @Load
    public void registerItems() {
        register(new Item().setUnlocalizedName("itemTest").setRegistryName("item_test"));
    }

    @Load(side = Side.CLIENT)
    public void registerRenders() {

    }

    private void register(Item item){
        GameRegistry.register(item);
    }
}
