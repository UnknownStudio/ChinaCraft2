package cn.mccraft.chinacraft.block.machine;

import net.minecraft.util.IStringSerializable;

import java.awt.*;

public enum EnumCrusherMaterial implements IStringSerializable {
    // Grey
    STONE(189, 189, 189, 1),

    // Deep orange
    BRONZE(255, 171, 145, 1),

    // Grey lighten
    IRON(245, 245, 245, 1);

    private float r, g, b, a;

    EnumCrusherMaterial(int r, int g, int b, int a) {
        Color color = new Color(r, g, b, a);
        this.r = color.getRGBColorComponents(null)[0];
        this.g = color.getRGBColorComponents(null)[1];
        this.b = color.getRGBColorComponents(null)[2];
        this.a = color.getRGBColorComponents(null)[3];
    }

    public float getRed() {
        return r;
    }

    public float getGreen() {
        return g;
    }

    public float getBlue() {
        return b;
    }

    public float getAlpha() {
        return a;
    }

    @Override
    public String getName() {
        return this.toString().toLowerCase();
    }
}
