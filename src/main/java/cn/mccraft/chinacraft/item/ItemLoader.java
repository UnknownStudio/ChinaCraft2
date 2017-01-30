package cn.mccraft.chinacraft.item;

import cn.mccraft.chinacraft.common.ChinaCraft;
import cn.mccraft.chinacraft.init.CCBlocks;
import cn.mccraft.chinacraft.init.CCItems;
import cn.mccraft.chinacraft.util.NameBuilder;
import cn.mccraft.chinacraft.util.loader.ILoader;
import cn.mccraft.chinacraft.util.loader.annotation.Load;
import cn.mccraft.chinacraft.util.loader.annotation.RegItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * Auto loader of all items annotated with {@link RegItem} in {@link CCItems}.
 * 自动加载{@link CCItems}中被{@link RegItem}注释的物品。
 */
public class ItemLoader implements ILoader<RegItem> {

    @Load
    public void registerItems() {
        loadAllFieldsInClass(RegItem.class, CCItems.class);
    }

    @Override
    public void loadForAnnotation(RegItem annotation, Field field) {
        try {
            Item item = (Item) field.get(null);
            field.setAccessible(true);
            List<String> value = Arrays.asList(annotation.value());
            value.add(0, annotation.prefix());
            field.set(null, item.setRegistryName(NameBuilder.buildRegistryName(value.toArray(new String[]{}))).setUnlocalizedName(NameBuilder.buildUnlocalizedName(value.toArray(new String[]{}))));
            register(item);
            Arrays.asList(annotation.oreDict()).forEach(s -> OreDictionary.registerOre(s, item));
        } catch (Exception e) {
            ChinaCraft.getLogger().warn("Un-able to register item " + field.toGenericString(), e);
        }
    }

    @Load
    public void registerItemBlocks() {
        for (Field field : CCBlocks.class.getFields())
            try {
                registerItemBlock((Block) field.get(null));
            } catch (Exception e) {
                ChinaCraft.getLogger().warn("Un-able to register ItemBlock " + field.toGenericString(), e);
            }
    }

    @Load(side = Side.CLIENT)
    public void registerRenders() {
    }

    private void register(Item item) {
        GameRegistry.register(item);
    }

    private void registerItemBlock(Block block) {
        GameRegistry.register(new ItemBlock(block));
    }
}
