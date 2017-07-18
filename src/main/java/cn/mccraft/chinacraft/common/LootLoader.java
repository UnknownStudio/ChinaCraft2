package cn.mccraft.chinacraft.common;

import cn.mccraft.chinacraft.util.loader.Loader;
import cn.mccraft.chinacraft.util.loader.annotation.Load;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.common.LoaderState;

public class LootLoader implements Loader {
    @Load(LoaderState.AVAILABLE)
    public void loadLoots() {
        LootTableList.register(new ResourceLocation(ChinaCraft.MODID, "inject/records"));
    }
}
