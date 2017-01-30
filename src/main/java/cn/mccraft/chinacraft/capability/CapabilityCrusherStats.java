package cn.mccraft.chinacraft.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapabilityCrusherStats {
    public static class Storage implements Capability.IStorage<ICrusherStats> {
        @Override
        public NBTBase writeNBT(Capability<ICrusherStats> capability, ICrusherStats instance, EnumFacing side) {
            return new NBTTagFloat(instance.getProgress());
        }

        @Override
        public void readNBT(Capability<ICrusherStats> capability, ICrusherStats instance, EnumFacing side, NBTBase nbt) {
            if (nbt.getId() == 5)
                instance.setProgress(((NBTTagFloat) nbt).getFloat());
        }
    }

    public static class Implementation implements ICrusherStats {
        private float progress;

        @Override
        public float getProgress() {
            return progress;
        }

        @Override
        public void setProgress(float progress) {
            this.progress = progress;
        }

        @Override
        public void addProgress(float progress) {
            this.progress += progress;
        }
    }

    public static class ProviderTileEntity implements ICapabilitySerializable<NBTTagCompound> {
        private ICrusherStats crusherStats = new Implementation();
        private Capability.IStorage<ICrusherStats> storage = CapabilityLoader.getStatsCapability().getStorage();

        @Override
        public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
            return CapabilityLoader.getStatsCapability().equals(capability);
        }

        @Nullable
        @Override
        public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
            if (CapabilityLoader.getStatsCapability().equals(capability))
                return (T) capability;
            return null;
        }

        @Override
        public NBTTagCompound serializeNBT() {
            NBTTagCompound nbtTagCompound = new NBTTagCompound();
            nbtTagCompound.setTag("progress", storage.writeNBT(CapabilityLoader.getStatsCapability(), crusherStats, null));
            return nbtTagCompound;
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {
            storage.readNBT(CapabilityLoader.getStatsCapability(), crusherStats, null, nbt.getTag("progress"));
        }
    }
}
