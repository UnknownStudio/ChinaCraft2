package cn.mccraft.chinacraft.api.recipe;

import cn.mccraft.chinacraft.block.machine.EnumCrusherMaterial;

public class CrusherRecipe extends SimpleOreRecipe {
    private int time;
    private EnumCrusherMaterial material = EnumCrusherMaterial.STONE;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public EnumCrusherMaterial getMaterial() {
        return material;
    }
}
