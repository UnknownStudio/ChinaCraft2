package cn.mccraft.chinacraft.block.tileentity;

import cn.mccraft.chinacraft.capability.CapabilitySilkworm;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileEntitySilkwormFrame extends TileEntity {
    private IItemHandlerModifiable inventory = new ItemStackHandler(3);
    private ICapabilitySerializable<NBTTagCompound> capabilitySerializable = new CapabilitySilkworm.Serializable();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || capabilitySerializable.hasCapability(capability, facing) || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    @SuppressWarnings("unchecked")
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return (T) inventory;
        else if (capabilitySerializable.hasCapability(capability, facing))
            return capabilitySerializable.getCapability(capability, facing);
        else
            return super.getCapability(capability, facing);
    }
}
