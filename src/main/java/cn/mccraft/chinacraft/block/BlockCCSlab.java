package cn.mccraft.chinacraft.block;

import cn.mccraft.chinacraft.init.CCCreativeTabs;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.ItemStack;

/**
 * Created by Mouse on 2017/2/2.
 */
public class BlockCCSlab extends BlockSlab{

    private final boolean isDouble;

    public BlockCCSlab(Material materialIn,boolean isDouble) {
        super(materialIn);
        this.isDouble = isDouble;
        if(!isDouble)setCreativeTab(CCCreativeTabs.tabCore);
    }

    @Override
    public String getUnlocalizedName(int meta) {
        return null;
    }

    @Override
    public boolean isDouble() {
        return isDouble;
    }

    @Override
    public IProperty<?> getVariantProperty() {
        return null;
    }

    @Override
    public Comparable<?> getTypeForItem(ItemStack stack) {
        return null;
    }
}
