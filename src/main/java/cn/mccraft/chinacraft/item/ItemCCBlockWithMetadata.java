package cn.mccraft.chinacraft.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemCCBlockWithMetadata extends ItemBlock{
    private String[] subtypeNames;

    public ItemCCBlockWithMetadata(Block block)
    {
        super(block);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    public int getMetadata(int damage)
    {
        return damage;
    }

    public ItemCCBlockWithMetadata setSubtypeNames(String ...names)
    {
        this.subtypeNames = names;
        return this;
    }

    public String getUnlocalizedName(ItemStack stack)
    {
        if (this.subtypeNames == null)
        {
            return super.getUnlocalizedName(stack);
        }
        else
        {
            int i = stack.getMetadata();
            return i >= 0 && i < this.subtypeNames.length ? super.getUnlocalizedName(stack) + "." + this.subtypeNames[i] : super.getUnlocalizedName(stack);
        }
    }
}
