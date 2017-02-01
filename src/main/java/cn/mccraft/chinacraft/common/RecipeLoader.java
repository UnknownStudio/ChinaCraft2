package cn.mccraft.chinacraft.common;

import cn.mccraft.chinacraft.api.CCRecipeManager;
import cn.mccraft.chinacraft.api.recipe.CrusherRecipe;
import cn.mccraft.chinacraft.init.CCItems;
import cn.mccraft.chinacraft.util.loader.ILoader;
import cn.mccraft.chinacraft.util.loader.annotation.Load;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.LoaderState;

import java.util.Arrays;

/**
 * Created by Mouse on 2017/1/28.
 */
public class RecipeLoader implements ILoader{

    @Load(LoaderState.INITIALIZATION)
    public void loadCraftingRecipe() {
    }

    @Load(LoaderState.INITIALIZATION)
    public void loadCrusherRecipe() {
        CCRecipeManager.addRecipe(new CrusherRecipe().setInputItems(Arrays.asList(new ItemStack(CCItems.BRONZE_INGOT))));
    }

    @Load(LoaderState.INITIALIZATION)
    public void loadSmeltingRecipe() {
    }
}
