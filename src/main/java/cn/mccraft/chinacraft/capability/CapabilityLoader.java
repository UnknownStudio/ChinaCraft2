package cn.mccraft.chinacraft.capability;

import cn.mccraft.chinacraft.common.ChinaCraft;
import cn.mccraft.chinacraft.init.CCCapabilities;
import cn.mccraft.chinacraft.util.loader.Loader;
import cn.mccraft.chinacraft.util.loader.annotation.Load;
import cn.mccraft.chinacraft.util.loader.annotation.RegCapability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.lang.reflect.Field;

public class CapabilityLoader implements Loader {
    @Load
    public void load(FMLPreInitializationEvent event) {
        for (Field field : CCCapabilities.class.getFields()) {
            try {
                register(field.getAnnotation(CapabilityInject.class).value(), field.getAnnotation(RegCapability.class));
            } catch (Exception e) {
                ChinaCraft.getLogger().warn("Un-able to register capability " + field.toGenericString(), e);
            }
        }
    }

    public <T> void register(Class<T> tClass, RegCapability annotation) throws IllegalAccessException, InstantiationException {
        CapabilityManager.INSTANCE.<T>register(tClass, annotation.storageClass().newInstance(), (Class<? extends T>) annotation.implClass());
    }
}
