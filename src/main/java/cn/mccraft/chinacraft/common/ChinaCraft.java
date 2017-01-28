package cn.mccraft.chinacraft.common;

import cn.mccraft.chinacraft.network.RedPacketMessage;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    public static SimpleNetworkWrapper network;

    private final Logger LOGGER = LogManager.getLogger(MODID);

    public static final CreativeTabs tabCore = new CreativeTabs(ChinaCraft.MODID+"_core") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(Blocks.AIR);
        }
    };

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        network = new SimpleNetworkWrapper(MODID);
        network.registerMessage(new RedPacketMessage.Handler(), RedPacketMessage.class,0, Side.SERVER);

        NetworkRegistry.INSTANCE.registerGuiHandler(ChinaCraft.instance, new GuiHandler());

        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @EventHandler
    public void loadComplete(FMLLoadCompleteEvent event) {
        proxy.loadComplete(event);
    }

    public Logger getLogger() {
        return LOGGER;
    }
}
