package cn.mccraft.chinacraft.common;

import cn.mccraft.chinacraft.util.loader.ILoader;
import cn.mccraft.chinacraft.util.loader.annotation.Load;
import net.minecraftforge.fml.common.LoaderState;
import static net.minecraftforge.fml.common.registry.GameRegistry.*;
/**
 * Created by Mouse on 2017/1/28.
 */
public class RecipeLoader implements ILoader{

    @Load(LoaderState.INITIALIZATION)
    public void loadCraftingRecipe(){
    }

    @Load(LoaderState.INITIALIZATION)
    public void loadSmeltingRecipe(){
    }
}
