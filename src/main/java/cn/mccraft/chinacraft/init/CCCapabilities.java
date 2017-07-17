package cn.mccraft.chinacraft.init;

import cn.mccraft.chinacraft.capability.CapabilityColor;
import cn.mccraft.chinacraft.capability.CapabilityCrusherStats;
import cn.mccraft.chinacraft.capability.CrusherStats;
import cn.mccraft.chinacraft.capability.ItemStackColorable;
import cn.mccraft.chinacraft.util.loader.annotation.RegCapability;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public interface CCCapabilities {
    @RegCapability(storageClass = CapabilityCrusherStats.Storage.class, implClass = CapabilityCrusherStats.Implementation.class)
    @CapabilityInject(CrusherStats.class)
    Capability<CrusherStats> CRUSHER_STATS_CAPABILITY = null;

    @RegCapability(storageClass = CapabilityColor.Storage.class, implClass = CapabilityColor.Implementation.class)
    @CapabilityInject(ItemStackColorable.class)
    Capability<ItemStackColorable> ITEM_STACK_COLORABLE_CAPABILITY = null;
}
