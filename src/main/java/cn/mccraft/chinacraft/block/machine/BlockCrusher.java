package cn.mccraft.chinacraft.block.machine;

import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;

public class BlockCrusher extends BlockMachine {
    private static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    private static final PropertyEnum<EnumMaterial> MATERIAL = PropertyEnum.create("material", EnumMaterial.class);

    public BlockCrusher() {
        setDefaultState(blockState.getBaseState()
            .withProperty(FACING, EnumFacing.NORTH)
            .withProperty(MATERIAL, EnumMaterial.STONE)
        );
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int facing = state.getValue(FACING).getHorizontalIndex();
        int material = state.getValue(MATERIAL).ordinal();
        return facing * 10 + material;
    }

    @Override
    @Nonnull
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing facing = EnumFacing.getHorizontal(meta / 10);
        EnumMaterial material = EnumMaterial.values()[meta - (meta / 10) * 10];
        return getDefaultState().withProperty(FACING, facing).withProperty(MATERIAL, material);
    }

    public enum EnumMaterial implements IStringSerializable {
        STONE, BRONZE, IRON;
        @Override
        public String getName() {
            return this.toString().toLowerCase();
        }
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, MATERIAL);
    }
}
