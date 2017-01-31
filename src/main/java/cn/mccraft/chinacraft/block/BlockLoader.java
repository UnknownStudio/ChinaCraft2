package cn.mccraft.chinacraft.block;

import cn.mccraft.chinacraft.common.ChinaCraft;
import cn.mccraft.chinacraft.init.CCBlocks;
import cn.mccraft.chinacraft.util.NameBuilder;
import cn.mccraft.chinacraft.util.loader.ILoader;
import cn.mccraft.chinacraft.util.loader.annotation.Load;
import cn.mccraft.chinacraft.util.loader.annotation.RegBlock;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * Auto loader of all blocks annotated with {@link RegBlock} in {@link CCBlocks}.
 * 自动加载{@link CCBlocks}中被{@link RegBlock}注释的方块。
 */
@SuppressWarnings("unused")
public class BlockLoader implements ILoader<RegBlock> {
    @Load
    public void registerBlocks() {
        loadAllFieldsInClass(RegBlock.class, CCBlocks.class);
    }

    @Load(value = LoaderState.INITIALIZATION, side = Side.CLIENT)
    public void registerRenders() {
        registerRender(CCBlocks.STONE_CRUSHER, 0, "stone_crusher");
        registerRender(CCBlocks.BRONZE_CRUSHER, 10, "bronze_crusher");
        registerRender(CCBlocks.IRON_CRUSHER, 20, "iron_crusher");
    }

    @Override
    public void loadForAnnotation(RegBlock annotation, Field field) {
        try {
            Block block = (Block) field.get(null);
            List<String> value = Arrays.asList(annotation.value());
            //value.add(0, annotation.prefix());
            register(block.setRegistryName(NameBuilder.buildRegistryName(value.toArray(new String[]{}))).setUnlocalizedName(NameBuilder.buildUnlocalizedName(value.toArray(new String[]{}))));
            Arrays.asList(annotation.oreDict()).forEach(s -> OreDictionary.registerOre(s, block));
        } catch (Exception e) {
            ChinaCraft.getLogger().warn("Un-able to register block " + field.toGenericString(), e);
        }
    }

    private void register(Block block){
        GameRegistry.register(block);
    }

    private void registerRender(Block block, int meta, String name)
    {
        Item item = Item.getItemFromBlock(block);
        ResourceLocation location = new ResourceLocation(ChinaCraft.MODID, name);
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(location, "inventory"));
        ModelLoader.registerItemVariants(item, location);
    }
}
