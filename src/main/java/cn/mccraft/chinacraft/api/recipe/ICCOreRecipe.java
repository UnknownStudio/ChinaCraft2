package cn.mccraft.chinacraft.api.recipe;

public interface ICCOreRecipe extends ICCRecipe {
    ICCOreRecipe addInputOre(String oredict);
    ICCOreRecipe addOutputOre(String oredict);
}
