package cn.mccraft.chinacraft.common;

import cn.mccraft.chinacraft.init.CCBlocks;
import cn.mccraft.chinacraft.init.CCItems;
import cn.mccraft.chinacraft.util.loader.ILoader;
import cn.mccraft.chinacraft.util.loader.annotation.Load;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.LoaderState;
import static net.minecraftforge.fml.common.registry.GameRegistry.*;

/**
 * Created by Mouse on 2017/1/28.
 */
public class RecipeLoader implements ILoader{

    @Load(LoaderState.INITIALIZATION)
    public void loadCraftingRecipe() {

    }

    @Load(LoaderState.INITIALIZATION)
    public void loadSmeltingRecipe() {
        addSmelting(CCBlocks.COPPER_ORE, new ItemStack(CCItems.COPPER_INGOT), 0.8f);
        addSmelting(CCBlocks.TIN_ORE, new ItemStack(CCItems.TIN_INGOT), 0.8f);
        addSmelting(CCBlocks.SILVER_ORE, new ItemStack(CCItems.SILVER_INGOT), 1.2f);
    }
}
