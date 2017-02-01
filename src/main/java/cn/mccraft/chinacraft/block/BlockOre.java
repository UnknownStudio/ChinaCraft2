package cn.mccraft.chinacraft.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

/**
 * Created by Mouse on 2017/1/31.
 */
public class BlockOre extends BlockBase{

    public BlockOre() {
        super(Material.ROCK);
        setHardness(3.0F);
        setResistance(5.0F);
        setSoundType(SoundType.STONE);
    }
}
