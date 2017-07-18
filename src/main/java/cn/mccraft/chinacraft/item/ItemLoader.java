package cn.mccraft.chinacraft.item;

import cn.mccraft.chinacraft.common.ChinaCraft;
import cn.mccraft.chinacraft.init.CCCapabilities;
import cn.mccraft.chinacraft.init.CCItems;
import cn.mccraft.chinacraft.util.NameBuilder;
import cn.mccraft.chinacraft.util.loader.Loader;
import cn.mccraft.chinacraft.util.loader.annotation.Load;
import cn.mccraft.chinacraft.util.loader.annotation.RegItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Auto loader of all items annotated with {@link RegItem} in {@link CCItems}.
 * 自动加载{@link CCItems}中被{@link RegItem}注释的物品。
 */
public class ItemLoader implements Loader {

    @Load
    public void registerItems() {
        for (Field field : CCItems.class.getFields()) {
            field.setAccessible(true);
            RegItem anno = field.getAnnotation(RegItem.class);
            if (anno==null) continue;

            try {
                Item item = (Item) field.get(null);
                if (item instanceof ItemRecord)
                    GameRegistry.register(item.setRegistryName(NameBuilder.buildRegistryName(anno.value()))).setUnlocalizedName("record");
                else
                    GameRegistry.register(item.setRegistryName(NameBuilder.buildRegistryName(anno.value())).setUnlocalizedName(NameBuilder.buildUnlocalizedName(anno.value())));

                Arrays.asList(anno.oreDict()).forEach(s -> OreDictionary.registerOre(s, item));
            } catch (Exception e) {
                ChinaCraft.getLogger().warn("Un-able to register item " + field.toGenericString(), e);
            }
        }
    }

    @Load(side = Side.CLIENT)
    @SideOnly(Side.CLIENT)
    public void registerRenders() {
        for (Field field : CCItems.class.getFields()) {
            field.setAccessible(true);
            RegItem anno = field.getAnnotation(RegItem.class);
            if (anno==null) continue;

            if(!anno.isRegisterRender()) continue;

            try {
                Item item = (Item) field.get(null);
                registerRender(item,0);
            } catch (Exception e) {
                ChinaCraft.getLogger().warn("Un-able to register item " + field.toGenericString(), e);
            }
        }
    }

    @Load(value = LoaderState.POSTINITIALIZATION, side = Side.CLIENT)
    @SideOnly(Side.CLIENT)
    public void registerItemColors() {
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> tintIndex == 0 ? stack.getCapability(CCCapabilities.ITEM_STACK_COLORABLE_CAPABILITY, null).getColor() : -1, CCItems.SILK);
    }

    @SideOnly(Side.CLIENT)
    private void registerRender(Item item, int meta) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
