package cn.mccraft.chinacraft.common;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Mouse on 2017/1/28.
 */
@Mod(modid = ChinaCraft.MODID,name = ChinaCraft.NAME,version = ChinaCraft.VERSION)
public final class ChinaCraft {
    public static final String MODID = "chinacraft";
    public static final String NAME = "ChinaCraft2";
    public static final String VERSION = "0.0.1";

    @SidedProxy(clientSide = "cn.mccraft.chinacraft.client.ClientProxy", serverSide = "cn.mccraft.chinacraft.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance(ChinaCraft.MODID)
    public static ChinaCraft instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
