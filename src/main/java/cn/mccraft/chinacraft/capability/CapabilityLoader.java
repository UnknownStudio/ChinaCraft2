package cn.mccraft.chinacraft.capability;

import cn.mccraft.chinacraft.util.loader.annotation.Load;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CapabilityLoader {
    @CapabilityInject(ICrusherStats.class)
    private static Capability<ICrusherStats> statsCapability;

    @Load
    public void load(FMLPreInitializationEvent event) {
        CapabilityManager.INSTANCE.register(ICrusherStats.class, new CapabilityCrusherStats.Storage(),
            CapabilityCrusherStats.Implementation.class);
    }

    public static Capability<ICrusherStats> getStatsCapability() {
        return statsCapability;
    }
}
