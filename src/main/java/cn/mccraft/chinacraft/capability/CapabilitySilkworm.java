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

public class CapabilitySilkworm {
    public static class Storage implements Capability.IStorage<Silkworm> {
        @Nullable
        @Override
        public NBTBase writeNBT(Capability<Silkworm> capability, Silkworm instance, EnumFacing side) {
            return new NBTTagInt(instance.getState().ordinal());
        }

        @Override
        public void readNBT(Capability<Silkworm> capability, Silkworm instance, EnumFacing side, NBTBase nbt) {
            instance.setState(SilkwormState.values()[((NBTTagInt) nbt).getInt()]);
        }
    }

    public static class Implementation implements Silkworm {
        private SilkwormState state;

        @Override
        public SilkwormState getState() {
            return state;
        }

        @Override
        public void setState(SilkwormState state) {
            this.state = state;
        }
    }

    public static class Serializable implements ICapabilitySerializable<NBTTagCompound> {
        private Silkworm silkworm = new Implementation();

        @Override
        public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
            return capability.equals(CCCapabilities.SILKWORM_CAPABILITY);
        }

        @Nullable
        @Override
        @SuppressWarnings("unchecked")
        public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
            return capability.equals(CCCapabilities.SILKWORM_CAPABILITY) ? (T) silkworm : null;
        }

        @Override
        public NBTTagCompound serializeNBT() {
            NBTTagCompound compound = new NBTTagCompound();
            compound.setTag("silkworm", CCCapabilities.SILKWORM_CAPABILITY.writeNBT(silkworm, null));
            return compound;
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {
            CCCapabilities.SILKWORM_CAPABILITY.readNBT(silkworm, null, nbt.getTag("silkworm"));
        }
    }
}
