package cn.mccraft.chinacraft.block;

import cn.mccraft.chinacraft.util.loader.ILoader;
import cn.mccraft.chinacraft.util.loader.annotation.Load;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockLoader implements ILoader {

    @Load
    public void registerBlocks() {
        register(new Block(Material.ROCK).setUnlocalizedName("blockTest").setRegistryName("block_test"));
    }

    private void register(Block block){
        GameRegistry.register(block);
    }
}
