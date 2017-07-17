package cn.mccraft.chinacraft.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class CapabilityCrusherStats {
    public static class Storage implements Capability.IStorage<CrusherStats> {
        @Override
        public NBTBase writeNBT(Capability<CrusherStats> capability, CrusherStats instance, EnumFacing side) {
            return new NBTTagFloat(instance.getProgress());
        }

        @Override
        public void readNBT(Capability<CrusherStats> capability, CrusherStats instance, EnumFacing side, NBTBase nbt) {
            if (nbt.getId() == 5)
                instance.setProgress(((NBTTagFloat) nbt).getFloat());
        }
    }

    public static class Implementation implements CrusherStats {
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
}
