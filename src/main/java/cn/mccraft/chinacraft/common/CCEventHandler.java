package cn.mccraft.chinacraft.common;

import cn.mccraft.chinacraft.capability.CapabilityCrusherStats;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
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

    /*
     * 阻止铁矿石被玩家用石镐采集。
     */
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onHarvestingIronOre(BlockEvent.BreakEvent event) {
        if (event.getState().getBlock().equals(Blocks.IRON_ORE))
            if (event.getPlayer().getHeldItemMainhand().getItem().equals(Items.STONE_PICKAXE))
                event.setCanceled(true);
    }
}
