package cn.mccraft.chinacraft.common;

import cn.mccraft.chinacraft.api.CCRecipeManager;
import cn.mccraft.chinacraft.api.recipe.CrusherRecipe;
import cn.mccraft.chinacraft.util.loader.ILoader;
import cn.mccraft.chinacraft.util.loader.annotation.Load;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Arrays;

import static cn.mccraft.chinacraft.init.CCBlocks.*;
import static cn.mccraft.chinacraft.init.CCItems.*;

/**
 * Created by Mouse on 2017/1/28.
 */
public class RecipeLoader implements ILoader{

    @Load(LoaderState.INITIALIZATION)
    public void loadCraftingRecipe() {
    }

    @Load(LoaderState.INITIALIZATION)
    public void loadCrusherRecipe() {
        CCRecipeManager.addRecipe(new CrusherRecipe().setInputItems(Arrays.asList(new ItemStack(BRONZE_INGOT))));
    }

    @Load(LoaderState.INITIALIZATION)
    public void loadSmeltingRecipe() {
        GameRegistry.addSmelting(COPPER_ORE, new ItemStack(COPPER_INGOT), 0.5f);
        GameRegistry.addSmelting(TIN_ORE, new ItemStack(TIN_INGOT), 0.5f);
    }
}
