package cn.mccraft.chinacraft.block.machine;

import net.minecraft.util.IStringSerializable;

public enum EnumCrusherMaterial implements IStringSerializable {
    STONE, BRONZE, IRON;
    @Override
    public String getName() {
        return this.toString().toLowerCase();
    }
}
