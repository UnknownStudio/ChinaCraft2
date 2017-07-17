package cn.mccraft.chinacraft.common;

import cn.mccraft.chinacraft.capability.CapabilityColor;
import cn.mccraft.chinacraft.init.CCAchievements;
import cn.mccraft.chinacraft.init.CCItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class CCEventHandler {
    public CCEventHandler() {
        MinecraftForge.EVENT_BUS.register(this);
    }

//    @SubscribeEvent
//    public void onAttachCapabilitiesEntity(AttachCapabilitiesEvent.TileEntity event) {
//        ICapabilitySerializable<NBTTagCompound> provider = new CapabilityCrusherStats.ProviderTileEntity();
//        event.addCapability(new ResourceLocation(ChinaCraft.MODID, "crusher_stats"), provider);
//    }

    /*
     * 阻止铁矿石被玩家用石镐采集。
     */
    @SubscribeEvent
    public void onHarvestingIronOre(BlockEvent.BreakEvent event) {
        if (event.getState().getBlock().equals(Blocks.IRON_ORE))
            if (event.getPlayer().getHeldItemMainhand().getItem().equals(Items.STONE_PICKAXE))
                event.setCanceled(true);
    }

    @SubscribeEvent
    public void onCraftingBronzeTool(PlayerEvent.ItemCraftedEvent event) {
        if (event.crafting.isItemEqual(new ItemStack(CCItems.BRONZE_INGOT)))
            event.player.addStat(CCAchievements.FIRST_BRONZE);
        else if (event.crafting.isItemEqual(new ItemStack(CCItems.BRONZE_PICKAXE)))
            event.player.addStat(CCAchievements.FIRST_BRONZE_PICKAXE);
    }

    @SubscribeEvent
    public void attachItemStack(AttachCapabilitiesEvent<Item> event) {
        event.addCapability(new ResourceLocation(ChinaCraft.MODID, "colorable"), new CapabilityColor.Serializable());
    }
}