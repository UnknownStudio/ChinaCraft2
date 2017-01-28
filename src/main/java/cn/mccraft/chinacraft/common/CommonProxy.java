package cn.mccraft.chinacraft.common;

import cn.mccraft.chinacraft.block.BlockLoader;
import cn.mccraft.chinacraft.item.ItemLoader;
import cn.mccraft.chinacraft.util.ILoader;
import cn.mccraft.chinacraft.util.loader.Load;
import com.google.common.collect.Lists;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.relauncher.Side;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mouse on 2017/1/28.
 */
public class CommonProxy {

    public Collection<Class<? extends ILoader>> loaders =
            Lists.newArrayList(BlockLoader.class, ItemLoader.class);

    public Map<Class<? extends ILoader>, ILoader> loaderInstanceMap = new HashMap<>();
    protected Map<LoaderState, Collection<Method>> stateLoaderMap = new HashMap<>();

    public void preInit(FMLPreInitializationEvent event) {
        invoke(event,LoaderState.PREINITIALIZATION, Side.SERVER);
    }

    public void init(FMLInitializationEvent event) {
        invoke(event,LoaderState.INITIALIZATION, Side.SERVER);
    }

    public void postInit(FMLPostInitializationEvent event) {
        invoke(event,LoaderState.POSTINITIALIZATION, Side.SERVER);
    }

    public void loadComplete(FMLLoadCompleteEvent event) {
        invoke(event,LoaderState.AVAILABLE, Side.SERVER);
    }

    protected <T extends FMLStateEvent> void invoke (T event,LoaderState state, Side side) {
        stateLoaderMap.values().forEach(methods -> methods.forEach(method -> {
            if (method.getAnnotation(Load.class).side().equals(side))
                if (method.getParameterCount() == 0 && method.getAnnotation(Load.class).value().equals(state))
                    try {
                        method.invoke(loaderInstanceMap.get(method.getDeclaringClass()));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        ChinaCraft.instance.getLogger().warn("Un-able to invoke method " + method.getName(), e);
                    }
                else if (method.getParameterCount() == 1 && method.getParameterTypes()[1].equals(event.getClass()))
                    try {
                        method.invoke(loaderInstanceMap.get(method.getDeclaringClass()), event);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        ChinaCraft.instance.getLogger().warn("Un-able to invoke method " + method.getName(), e);
                    }
        }));
    }

    public CommonProxy() {
        for (Class<? extends ILoader> loaderClass : loaders)
            try {
                for (Method method : loaderClass.getMethods())
                    for (Annotation annotation : method.getDeclaredAnnotations())
                        if (annotation.annotationType().equals(Load.class))
                            if (method.getParameterCount() == 0 || (FMLStateEvent.class.isAssignableFrom(method.getParameterTypes()[0]) && method.getParameterTypes()[0].equals(((Load) annotation).value().getEvent().getClass()))) {
                                Collection<Method> methods = stateLoaderMap.getOrDefault(((Load) annotation).value(), new ArrayList<>());
                                if (!methods.contains(method))
                                    methods.add(method);
                                stateLoaderMap.put(((Load) annotation).value(), methods);
                            }
                loaderInstanceMap.put(loaderClass, loaderClass.newInstance());
            } catch (Exception e) {
                ChinaCraft.instance.getLogger().warn("Un-able to register loader " + loaderClass.getName(), e);
            }
    }
}
