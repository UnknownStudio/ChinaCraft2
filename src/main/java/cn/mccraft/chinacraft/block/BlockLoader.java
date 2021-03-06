package cn.mccraft.chinacraft.block;

import cn.mccraft.chinacraft.common.ChinaCraft;
import cn.mccraft.chinacraft.init.CCBlocks;
import cn.mccraft.chinacraft.util.NameBuilder;
import cn.mccraft.util.loader.Load;
import cn.mccraft.chinacraft.util.loader.annotation.RegBlock;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Auto loader of all blocks annotated with {@link RegBlock} in {@link CCBlocks}.
 * 自动加载{@link CCBlocks}中被{@link RegBlock}注释的方块。
 */
@SuppressWarnings("unused")
public class BlockLoader {
    @Load
    public void registerBlocks() {
        for (Field field : CCBlocks.class.getFields()) {
            field.setAccessible(true);
            RegBlock anno = field.getAnnotation(RegBlock.class);
            if (anno==null) continue;

            try {
                Block block = (Block) field.get(null);
                GameRegistry.register(block.setRegistryName(NameBuilder.buildRegistryName(anno.value())).setUnlocalizedName(NameBuilder.buildUnlocalizedName(anno.value())));

                //Register item block.
                if(anno.isRegisterItemBlock()) {
                    Class<? extends Item> itemClass = anno.itemClass();
                    Constructor<? extends Item> con = itemClass.getDeclaredConstructor(Block.class);
                    con.setAccessible(true);
                    GameRegistry.register(con.newInstance(block).setRegistryName(block.getRegistryName()).setUnlocalizedName(block.getUnlocalizedName()));
                }

                Arrays.asList(anno.oreDict()).forEach(s -> OreDictionary.registerOre(s, block));
            } catch (Exception e) {
                ChinaCraft.getLogger().warn("Un-able to register block " + field.toGenericString(), e);
            }
        }
    }

    @Load(side = Side.CLIENT)
    @SideOnly(Side.CLIENT)
    public void registerRenders() {
        for (Field field : CCBlocks.class.getFields()) {
            field.setAccessible(true);
            RegBlock anno = field.getAnnotation(RegBlock.class);
            if (anno==null) continue;

            if(!anno.isRegisterRender()||!anno.isRegisterItemBlock()) continue;

            try {
                Block block = (Block) field.get(null);
                registerRender(block,0);
            } catch (Exception e) {
                ChinaCraft.getLogger().warn("Un-able to register block " + field.toGenericString(), e);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    private void registerRender(Block block, int meta)
    {
        Item item = Item.getItemFromBlock(block);
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }
}
