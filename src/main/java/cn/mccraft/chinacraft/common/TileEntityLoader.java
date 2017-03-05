package cn.mccraft.chinacraft.common;

import cn.mccraft.chinacraft.block.tileentity.TileEntityCrusher;
import cn.mccraft.chinacraft.util.loader.ILoader;
import cn.mccraft.chinacraft.util.loader.annotation.Load;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityLoader implements ILoader {
    @Load(LoaderState.INITIALIZATION)
    public void load() {
        GameRegistry.registerTileEntity(TileEntityCrusher.class, ChinaCraft.MODID + ":crusher");
    }
}