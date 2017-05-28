package cn.mccraft.chinacraft.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockCCOre extends BlockCCBase {

    public BlockCCOre() {
        super(Material.ROCK);
        setHardness(3.0F);
        setResistance(5.0F);
        setSoundType(SoundType.STONE);
    }
}
