package cn.mccraft.chinacraft.block;

import cn.mccraft.chinacraft.init.CCCreativeTabs;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockCCSlab extends BlockSlab{

    public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.<EnumType>create("variant", EnumType.class);
    private final boolean isDouble;
    private final BlockCCSlab singleSlab;

    public BlockCCSlab(Material materialIn,boolean isDouble,BlockCCSlab singleSlab) {
        super(materialIn);
        this.isDouble = isDouble;
        this.singleSlab = singleSlab==null?this:singleSlab;

        IBlockState iblockstate = this.blockState.getBaseState();

        if (!this.isDouble())
        {
            iblockstate = iblockstate.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
            setCreativeTab(CCCreativeTabs.tabCore);
        }

        this.setDefaultState(iblockstate.withProperty(VARIANT, EnumType.DEFAULT));
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(singleSlab);
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(singleSlab);
    }

    @Override
    public String getUnlocalizedName(int meta) {
        return getUnlocalizedName();
    }

    @Override
    public boolean isDouble() {
        return isDouble;
    }

    public IProperty<?> getVariantProperty()
    {
        return VARIANT;
    }

    public Comparable<?> getTypeForItem(ItemStack stack)
    {
        return EnumType.DEFAULT;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, EnumType.DEFAULT);

        if (!this.isDouble())
        {
            iblockstate = iblockstate.withProperty(HALF, meta == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
        }

        return iblockstate;
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return !this.isDouble() && state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP?1:0;
    }

    protected BlockStateContainer createBlockState()
    {
        return this.isDouble() ? new BlockStateContainer(this, new IProperty[] {VARIANT}): new BlockStateContainer(this, new IProperty[] {HALF, VARIANT});
    }

    public BlockCCSlab setHarvestLevelReturnBlock(String toolClass, int level) {
        super.setHarvestLevel(toolClass, level);
        return this;
    }

    @Override
    public BlockCCSlab setSoundType(SoundType sound)
    {
        super.setSoundType(sound);
        return this;
    }

    public static enum EnumType implements IStringSerializable
    {
        DEFAULT;

        public String getName()
        {
            return "default";
        }
    }
}
