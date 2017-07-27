package cn.mccraft.chinacraft.common;

import biomesoplenty.common.world.generator.tree.GeneratorTreeBase;
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
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class CCEventHandler {
    public CCEventHandler() {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.TERRAIN_GEN_BUS.register(this);
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

    @SubscribeEvent
    public void addLoots(LootTableLoadEvent event) {
        if (event.getName().toString().equals("minecraft:chests/simple_dungeon") || event.getName().toString().equals("minecraft:chests/woodland_mansion")) {
//            TODO
//            LootEntry entry = new LootEntryTable(new ResourceLocation(ChinaCraft.MODID, "inject/records"), 15, <quality>, <conditions>, <entryName>); // weight doesn't matter since it's the only entry in the pool. Other params set as you wish.
//
//            LootPool pool = new LootPool(new LootEntry[] {entry}, <conditions>, <rolls>, <bonusRolls>, <name>); // Other params set as you wish.
//
//            event.getTable().addPool(pool);
        }
    }

    @SubscribeEvent
    public void generateTree(DecorateBiomeEvent.Decorate event) {
        if (event.getType() == DecorateBiomeEvent.Decorate.EventType.TREE)
            if (event.getRand().nextBoolean()) {
                event.setResult(Event.Result.DENY);
                TreeLoader.GENERATOR_TREES.toArray(new GeneratorTreeBase[0])[event.getRand().nextInt(TreeLoader.GENERATOR_TREES.size() - 1)].generate(event.getWorld(), event.getRand(), event.getPos());
            }
    }
}