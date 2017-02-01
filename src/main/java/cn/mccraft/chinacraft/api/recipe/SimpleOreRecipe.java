package cn.mccraft.chinacraft.api.recipe;

import net.minecraftforge.oredict.OreDictionary;

public class SimpleOreRecipe extends SimpleRecipe implements ICCOreRecipe {
    @Override
    public SimpleOreRecipe addInputOre(String oredict) {
        OreDictionary.getOres(oredict).forEach(this::addInputItem);
        return this;
    }

    @Override
    public SimpleOreRecipe addOutputOre(String oredict) {
        OreDictionary.getOres(oredict).forEach(this::addOutputItem);
        return this;
    }
}
