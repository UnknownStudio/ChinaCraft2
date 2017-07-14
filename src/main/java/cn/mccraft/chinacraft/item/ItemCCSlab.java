package cn.mccraft.chinacraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.ItemSlab;

public class ItemCCSlab extends ItemSlab{
    public ItemCCSlab(BlockSlab singleSlab, BlockSlab doubleSlab) {
        super(singleSlab, singleSlab, doubleSlab);
    }
}
