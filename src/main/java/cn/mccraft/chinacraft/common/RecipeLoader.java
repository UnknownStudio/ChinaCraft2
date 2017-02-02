package cn.mccraft.chinacraft.common;

import cn.mccraft.chinacraft.util.loader.ILoader;
import cn.mccraft.chinacraft.util.loader.annotation.Load;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import static net.minecraftforge.fml.common.registry.GameRegistry.*;
import static cn.mccraft.chinacraft.init.CCBlocks.*;
import static cn.mccraft.chinacraft.init.CCItems.*;

/**
 * Created by Mouse on 2017/1/28.
 */
public class RecipeLoader implements ILoader{

    @Load(LoaderState.INITIALIZATION)
    public void loadCraftingRecipe() {
        addRecipe(new ShapedOreRecipe(COPPER_BLOCK,"###","###","###",'#',COPPER_INGOT));
        addRecipe(new ShapelessOreRecipe(new ItemStack(COPPER_INGOT,9),COPPER_BLOCK));
        addRecipe(new ShapedOreRecipe(TIN_BLOCK,"###","###","###",'#',TIN_INGOT));
        addRecipe(new ShapelessOreRecipe(new ItemStack(TIN_INGOT,9),TIN_BLOCK));
        addRecipe(new ShapedOreRecipe(SILVER_BLOCK,"###","###","###",'#',SILVER_INGOT));
        addRecipe(new ShapelessOreRecipe(new ItemStack(SILVER_INGOT,9),SILVER_BLOCK));
        addRecipe(new ShapedOreRecipe(BRONZE_BLOCK,"###","###","###",'#',BRONZE_INGOT));
        addRecipe(new ShapelessOreRecipe(new ItemStack(BRONZE_INGOT,9),BRONZE_BLOCK));
    }

    @Load(LoaderState.INITIALIZATION)
    public void loadSmeltingRecipe() {
        addSmelting(COPPER_ORE, new ItemStack(COPPER_INGOT), 0.8f);
        addSmelting(TIN_ORE, new ItemStack(TIN_INGOT), 0.8f);
        addSmelting(SILVER_ORE, new ItemStack(SILVER_INGOT), 1.2f);
    }
}
