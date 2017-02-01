package cn.mccraft.chinacraft.world.gen;

import com.google.common.base.Predicate;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.Random;

/**
 * Created by Mouse on 2017/2/1.
 */
public class WorldGenCCOre implements IWorldGenerator{

    private Set<Integer> dimensionID = new HashSet<>();
    private Set<Integer> biomeID = new HashSet<>();
    private int frequency;
    private int highest,lowest;
    private int size;
    private IBlockState oreBlock;
    private Predicate<IBlockState> predicate = null;

    public WorldGenCCOre(){}

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (!dimensionID.contains(world.provider.getDimension())) return;

        for (int i = 0; i < frequency; i++) {
            int x= chunkX * 16 + random.nextInt(16);
            int y = random.nextInt(highest - lowest) + lowest;
            int z = chunkZ * 16 + random.nextInt(16);
            BlockPos pos = new BlockPos(x,y,z);

            if(!biomeID.isEmpty() && !biomeID.contains(Biome.getIdForBiome(world.getBiome(pos)))) return;

            if(predicate==null)new WorldGenMinable(oreBlock, size).generate(world, random, pos);
            else new WorldGenMinable(oreBlock, size, predicate).generate(world, random, pos);
        }
    }

    static class CustomPredicate implements Predicate<IBlockState> {
        private final IBlockState block;

        private CustomPredicate(IBlockState block) {
            this.block = block;
        }

        public boolean apply(IBlockState p_apply_1_) {
            return p_apply_1_ != null && p_apply_1_.getBlock() == block.getBlock();
        }
    }
}
