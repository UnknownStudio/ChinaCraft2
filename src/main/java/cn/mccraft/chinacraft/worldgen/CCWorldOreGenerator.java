package cn.mccraft.chinacraft.worldgen;

import cn.mccraft.chinacraft.init.CCBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CCWorldOreGenerator {
    private static WorldGenerator copperGenerator = new WorldGenMinable(CCBlocks.COPPER_ORE.getDefaultState(), 10);
    private static WorldGenerator tinGenerator = new WorldGenMinable(CCBlocks.TIN_ORE.getDefaultState(), 6);

    private BlockPos pos;

    public CCWorldOreGenerator() {
        MinecraftForge.ORE_GEN_BUS.register(this);
    }

    @SubscribeEvent
    public void onOreGenPost(OreGenEvent.Post event)
    {
        if (!event.getPos().equals(this.pos))
        {
            this.pos = event.getPos();
            copperGenerator.generate(event.getWorld(), event.getRand(), event.getPos());
            tinGenerator.generate(event.getWorld(), event.getRand(), event.getPos());
        }
    }

    @SubscribeEvent
    public void onOreGenGenerateMinable(OreGenEvent.GenerateMinable event)
    {
        if (event.getType() == OreGenEvent.GenerateMinable.EventType.ANDESITE)
        {
            event.setResult(Event.Result.DENY);
        }
    }
}
