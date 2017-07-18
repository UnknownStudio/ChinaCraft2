package cn.mccraft.chinacraft.common;

import cn.mccraft.chinacraft.block.BlockLoader;
import cn.mccraft.chinacraft.capability.CapabilityLoader;
import cn.mccraft.chinacraft.item.ItemLoader;
import cn.mccraft.chinacraft.util.loader.Loader;
import cn.mccraft.chinacraft.util.loader.annotation.Load;
import cn.mccraft.chinacraft.world.gen.WorldGenListener;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.relauncher.Side;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Common proxy of ChinaCraft.
 * ChinaCraft公共代理。
 */
public class CommonProxy {

    private final Collection<Class<? extends Loader>> loaders =
            Arrays.asList(BlockLoader.class, ItemLoader.class, RecipeLoader.class, WorldGenListener.class,
                ModificationLoader.class, AchievementsLoader.class, CapabilityLoader.class ,
                    TileEntityLoader.class, SoundLoader.class, LootLoader.class);

    private final Map<Class<? extends Loader>, Loader> loaderInstanceMap = new HashMap<>();
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

    protected <T extends FMLStateEvent> void invoke (T event, LoaderState state, Side side) {
        stateLoaderMap.values().forEach(methods -> methods.forEach(method -> {
            if (method.getAnnotation(Load.class).side().equals(side))
                if (method.getParameterCount() == 0 && method.getAnnotation(Load.class).value().equals(state))
                    try {
                        method.invoke(loaderInstanceMap.get(method.getDeclaringClass()));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        ChinaCraft.getLogger().warn("Un-able to invoke method " + method.getName(), e);
                    }
                else if (method.getParameterCount() == 1 && method.getParameterTypes()[0].equals(event.getClass()))
                    try {
                        method.invoke(loaderInstanceMap.get(method.getDeclaringClass()), event);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        ChinaCraft.getLogger().warn("Un-able to invoke method " + method.getName(), e);
                    }
        }));
    }

    public CommonProxy() {
        for (Class<? extends Loader> loaderClass : loaders)
            try {
                for (Method method : loaderClass.getMethods())
                    for (Annotation annotation : method.getDeclaredAnnotations())
                        if (annotation.annotationType().equals(Load.class))
                            if (method.getParameterCount() <= 1) {
                                Collection<Method> methods = stateLoaderMap.getOrDefault(((Load) annotation).value(), new ArrayList<>());
                                if (!methods.contains(method))
                                    methods.add(method);
                                stateLoaderMap.put(((Load) annotation).value(), methods);
                            }
                loaderInstanceMap.put(loaderClass, loaderClass.newInstance());
            } catch (Exception e) {
                ChinaCraft.getLogger().warn("Un-able to register loader " + loaderClass.getName(), e);
            }
    }

    public Map<Class<? extends Loader>, ? extends Loader> getLoaderInstanceMap() {
        return loaderInstanceMap;
    }

    public <T extends Loader> Optional<T> getLoader(Class<T> loaderClass) {
        try {
            return Optional.of((T) loaderInstanceMap.get(loaderClass));
        } catch (ClassCastException ignored) {
            return Optional.empty();
        }
    }

    public Collection<Class<? extends Loader>> getLoaders() {
        return loaders;
    }

    public Map<LoaderState, Collection<Method>> getStateLoaderMap() {
        return stateLoaderMap;
    }
}
