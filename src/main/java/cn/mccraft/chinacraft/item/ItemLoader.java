package cn.mccraft.chinacraft.item;

import cn.mccraft.chinacraft.common.ChinaCraft;
import cn.mccraft.chinacraft.init.CCBlocks;
import cn.mccraft.chinacraft.init.CCItems;
import cn.mccraft.chinacraft.util.NameBuilder;
import cn.mccraft.chinacraft.util.loader.ILoader;
import cn.mccraft.chinacraft.util.loader.annotation.Load;
import cn.mccraft.chinacraft.util.loader.annotation.RegItem;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;

import java.lang.reflect.Field;
import java.util.Arrays;

public class ItemLoader implements ILoader<RegItem> {

    @Load
    public void registerItems() {
        loadAllFieldsInClass(RegItem.class, CCItems.class);
    }

    @Override
    public void loadForAnnotation(RegItem annotation, Field field) {
        try {
            Item item = (Item) field.get(null);
            field.set(null, item.setRegistryName(NameBuilder.buildRegistryName(annotation.value())).setUnlocalizedName(NameBuilder.buildUnlocalizedName(annotation.value())));
            register(item);
            ModelBakery.registerItemVariants(item, new ModelResourceLocation(ChinaCraft.MODID + ":" + item.getUnlocalizedName(), "inventory"));
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
