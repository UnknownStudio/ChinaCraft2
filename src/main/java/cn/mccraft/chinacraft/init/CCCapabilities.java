package cn.mccraft.chinacraft.init;

import cn.mccraft.chinacraft.capability.CapabilityCrusherStats;
import cn.mccraft.chinacraft.capability.ICrusherStats;
import cn.mccraft.chinacraft.util.loader.annotation.RegCapability;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public interface CCCapabilities {
    @RegCapability(storageClass = CapabilityCrusherStats.Storage.class, implClass = CapabilityCrusherStats.Implementation.class)
    @CapabilityInject(ICrusherStats.class)
    Capability<ICrusherStats> CRUSHER_STATS_CAPABILITY = null;
}
