package cn.mccraft.chinacraft.common;

import cn.mccraft.chinacraft.capability.CapabilityCrusherStats;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CCEventHandler {
    public CCEventHandler() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onAttachCapabilitiesEntity(AttachCapabilitiesEvent.TileEntity event) {
        ICapabilitySerializable<NBTTagCompound> provider = new CapabilityCrusherStats.ProviderTileEntity();
        event.addCapability(new ResourceLocation(ChinaCraft.MODID, "crusher_stats"), provider);
    }
}
