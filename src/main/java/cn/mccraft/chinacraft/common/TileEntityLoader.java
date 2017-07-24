package cn.mccraft.chinacraft.common;

import cn.mccraft.chinacraft.block.tileentity.TileEntityCrusher;
import cn.mccraft.util.loader.Load;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityLoader {
    @Load(LoaderState.INITIALIZATION)
    public void load() {
        GameRegistry.registerTileEntity(TileEntityCrusher.class, ChinaCraft.MODID + ":crusher");
    }
}
