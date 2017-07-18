package cn.mccraft.chinacraft.world.gen;

import cn.mccraft.chinacraft.init.CCBlocks;
import cn.mccraft.chinacraft.util.loader.Loader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WorldGenListener implements Loader {

    private final WorldGenCCOre WORLD_GEN_COPPER_ORE = new WorldGenCCOre(new int[]{0},20,64,0,8, CCBlocks.COPPER_ORE.getDefaultState());
    private final WorldGenCCOre WORLD_GEN_TIN_ORE = new WorldGenCCOre(new int[]{0},10,64,0,8, CCBlocks.TIN_ORE.getDefaultState());
    private final WorldGenCCOre WORLD_GEN_SILVER_ORE = new WorldGenCCOre(new int[]{0},4,32,0,8, CCBlocks.SILVER_ORE.getDefaultState());
    private final WorldGenCCOre WORLD_GEN_MARBLE = new WorldGenCCOre(new int[]{0},1,128,32,48, CCBlocks.MARBLE.getDefaultState());

    public WorldGenListener()
    {
        MinecraftForge.ORE_GEN_BUS.register(this);
    }

    @SubscribeEvent
    public void onOreGenPost(OreGenEvent.Post event) {
        WORLD_GEN_COPPER_ORE.generate(event.getWorld(), event.getRand(), event.getPos());
        WORLD_GEN_TIN_ORE.generate(event.getWorld(), event.getRand(), event.getPos());
        WORLD_GEN_SILVER_ORE.generate(event.getWorld(), event.getRand(), event.getPos());
        WORLD_GEN_MARBLE.generate(event.getWorld(), event.getRand(), event.getPos());
    }
}
