package cn.mccraft.chinacraft.block;

import cn.mccraft.chinacraft.common.ChinaCraft;
import cn.mccraft.chinacraft.init.CCBlocks;
import cn.mccraft.chinacraft.util.NameBuilder;
import cn.mccraft.chinacraft.util.loader.ILoader;
import cn.mccraft.chinacraft.util.loader.annotation.Load;
import cn.mccraft.chinacraft.util.loader.annotation.RegBlock;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * Auto loader of all blocks annotated with {@link RegBlock} in {@link CCBlocks}.
 * 自动加载{@link CCBlocks}中被{@link RegBlock}注释的方块。
 */
public class BlockLoader implements ILoader<RegBlock> {
    @Load
    public void registerBlocks() {
        loadAllFieldsInClass(RegBlock.class, CCBlocks.class);
    }

    @Override
    public void loadForAnnotation(RegBlock annotation, Field field) {
        try {
            Block block = (Block) field.get(null);
            field.setAccessible(true);
            List<String> value = Arrays.asList(annotation.value());
            value.add(0, annotation.prefix());
            field.set(null, block.setRegistryName(NameBuilder.buildRegistryName(value.toArray(new String[]{}))).setUnlocalizedName(NameBuilder.buildUnlocalizedName(value.toArray(new String[]{}))));
            register(block);
            Arrays.asList(annotation.oreDict()).forEach(s -> OreDictionary.registerOre(s, block));
        } catch (Exception e) {
            ChinaCraft.getLogger().warn("Un-able to register block " + field.toGenericString(), e);
        }
    }

    private void register(Block block){
        GameRegistry.register(block);
    }
}
