package cn.mccraft.chinacraft.api;

import cn.mccraft.chinacraft.api.recipe.ICCRecipe;
import net.minecraft.item.ItemStack;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CCRecipeManager {
    private static final Set<ICCRecipe> RECIPES = new HashSet<>();

    public static boolean addRecipe(ICCRecipe recipe) {
        return !RECIPES.stream().anyMatch(recipe1 -> recipe1.getInputItems().equals(recipe.getInputItems())) && RECIPES.add(recipe);
    }

    public static Optional<ICCRecipe> getRecipe(List<ItemStack> inputItems) {
        return RECIPES.stream().filter(recipe -> recipe.getInputItems().equals(inputItems)).findAny();
    }

    public static Optional<List<ItemStack>> getRecipeOutput(List<ItemStack> inputItems) {
        return RECIPES.stream().filter(recipe -> recipe.getInputItems().equals(inputItems)).findAny().map(ICCRecipe::getOutputItems);
    }

    public static boolean removeRecipe(List<ItemStack> inputItems) {
        return RECIPES.removeIf(recipe -> recipe.getInputItems().equals(inputItems));
    }

    public static Set<ICCRecipe> getRecipes() {
        return RECIPES;
    }
}
