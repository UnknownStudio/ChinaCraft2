package cn.mccraft.chinacraft.capability;

import cn.mccraft.chinacraft.init.CCCapabilities;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapabilityColor {
    public static class Storage implements Capability.IStorage<ItemStackColorable> {
        @Nullable
        @Override
        public NBTBase writeNBT(Capability<ItemStackColorable> capability, ItemStackColorable instance, EnumFacing side) {
            return new NBTTagInt(instance.getColor());
        }

        @Override
        public void readNBT(Capability<ItemStackColorable> capability, ItemStackColorable instance, EnumFacing side, NBTBase nbt) {
            instance.setColor(((NBTTagInt) nbt).getInt());
        }
    }

    public static class Implementation implements ItemStackColorable {
        private int color;

        @Override
        public int getColor() {
            return color;
        }

        @Override
        public void setColor(int color) {
            this.color = color;
        }
    }

    public static class Serializable implements ICapabilitySerializable<NBTTagCompound> {
        private ItemStackColorable colorable = new CapabilityColor.Implementation();

        @Override
        public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
            return capability.equals(CCCapabilities.ITEM_STACK_COLORABLE_CAPABILITY);
        }

        @Nullable
        @Override
        @SuppressWarnings("unchecked")
        public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
            return hasCapability(capability, facing) ? (T) colorable : null;
        }

        @Override
        public NBTTagCompound serializeNBT() {
            NBTTagCompound compound = new NBTTagCompound();
            compound.setTag("color", CCCapabilities.ITEM_STACK_COLORABLE_CAPABILITY.writeNBT(colorable, null));
            return compound;
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {
            CCCapabilities.ITEM_STACK_COLORABLE_CAPABILITY.readNBT(colorable, null, nbt.getTag("color"));
        }
    }
}
