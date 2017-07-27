package cn.mccraft.chinacraft.common;

import cn.mccraft.chinacraft.block.BlockLoader;
import cn.mccraft.chinacraft.capability.CapabilityLoader;
import cn.mccraft.chinacraft.item.ItemLoader;
import cn.mccraft.chinacraft.world.gen.WorldGenListener;
import cn.mccraft.util.loader.Proxy;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Common proxy of ChinaCraft.
 * ChinaCraft公共代理。
 */
public class CommonProxy implements Proxy {

    private final Collection<Class<?>> loaders =
            Arrays.asList(BlockLoader.class, ItemLoader.class, RecipeLoader.class, WorldGenListener.class,
                ModificationLoader.class, AchievementsLoader.class, CapabilityLoader.class ,
                    TileEntityLoader.class, SoundLoader.class, LootLoader.class, TreeLoader.class);

    private final Map<Class<?>, Object> loaderInstanceMap = new HashMap<>();
    private final Map<LoaderState, Collection<Method>> stateLoaderMap = new HashMap<>();

    public void preInit(FMLPreInitializationEvent event) {
        invoke(event, LoaderState.PREINITIALIZATION, Side.SERVER);
    }

    public void init(FMLInitializationEvent event) {
        invoke(event, LoaderState.INITIALIZATION, Side.SERVER);
    }

    public void postInit(FMLPostInitializationEvent event) {
        invoke(event, LoaderState.POSTINITIALIZATION, Side.SERVER);
    }

    public void loadComplete(FMLLoadCompleteEvent event) {
        invoke(event, LoaderState.AVAILABLE, Side.SERVER);
    }

    public CommonProxy() {
        for (Class<?> loaderClass : loaders)
            addLoader(loaderClass);
    }

    @Override
    public Map<Class<?>, Object> getLoaderInstanceMap() {
        return loaderInstanceMap;
    }

    public Collection<Class<?>> getLoaders() {
        return loaders;
    }

    @Override
    public Map<LoaderState, Collection<Method>> getStateLoaderMap() {
        return stateLoaderMap;
    }
}
