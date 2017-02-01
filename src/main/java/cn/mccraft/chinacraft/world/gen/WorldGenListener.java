package cn.mccraft.chinacraft.world.gen;

import cn.mccraft.chinacraft.init.CCBlocks;
import cn.mccraft.chinacraft.util.loader.ILoader;
import cn.mccraft.chinacraft.util.loader.annotation.Load;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Mouse on 2017/2/1.
 */
public class WorldGenListener implements ILoader{

    private final WorldGenCCOre WORLD_GEN_COPPER_ORE = new WorldGenCCOre(new int[]{0},20,64,0,8, CCBlocks.COPPER_ORE.getDefaultState());
    private final WorldGenCCOre WORLD_GEN_TIN_ORE =new WorldGenCCOre(new int[]{0},10,64,0,8, CCBlocks.TIN_ORE.getDefaultState());

    public WorldGenListener()
    {
        MinecraftForge.ORE_GEN_BUS.register(this);
    }

    @SubscribeEvent
    public void onOreGenPost(OreGenEvent.Post event){
        WORLD_GEN_COPPER_ORE.generate(event.getWorld(),event.getRand(),event.getPos());
        WORLD_GEN_TIN_ORE.generate(event.getWorld(),event.getRand(),event.getPos());
    }
}
