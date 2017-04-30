package cn.mccraft.chinacraft.block;

import cn.mccraft.chinacraft.block.machine.BlockCrusher;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nonnull;
import java.util.Optional;

public class BlockCrank extends BlockCCBase {
    public BlockCrank() {
        super(Material.WOOD);
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, @Nonnull BlockPos pos) {
        return worldIn.getBlockState(pos.down()).getBlock() instanceof BlockCrusher;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        Optional<TileEntity> crusherEntity = Optional.ofNullable(worldIn.getTileEntity(pos.down()));
        crusherEntity.ifPresent(entity -> Optional.ofNullable(entity.getCapability(CapabilityEnergy.ENERGY, facing)).ifPresent(storage -> storage.receiveEnergy(10, false))); // 每次点击增加10能量
        return true;
    }
}
