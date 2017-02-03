package cn.mccraft.chinacraft.api;

import cn.mccraft.chinacraft.api.recipe.ICCRecipe;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

public class CCRecipeManager {
    private static final Map<Class<? extends ICCRecipe>, Set<ICCRecipe>> RECIPES = new HashMap<>();

    public static <T extends ICCRecipe> void addRecipe(Class<T> recipeClass, T recipe) {
        Set<ICCRecipe> recipes = RECIPES.getOrDefault(recipeClass, new HashSet<>());
        recipes.add(recipe);
        RECIPES.put(recipeClass, recipes);
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public static <T extends ICCRecipe> Set<T> removeRecipe(Class<T> recipeClass) {
        return (Set<T>) RECIPES.remove(recipeClass);
    }

    @SuppressWarnings("unchecked")
    public static <T extends ICCRecipe> void addAllRecipes(Class<T> recipeClass, Set<T> recipeSet) {
        RECIPES.put(recipeClass, recipeSet.stream().collect(Collectors.toSet()));
    }

    @SuppressWarnings("unchecked")
    public static <T extends ICCRecipe> Optional<T> getRecipe(Class<T> recipeClass, List<ItemStack> inputs) {
        return RECIPES.getOrDefault(recipeClass, new HashSet<>()).stream().filter(recipe -> recipe.getInputItems().equals(inputs)).findFirst().map(recipe -> (T) recipe);
    }

    @SuppressWarnings("unchecked")
    public static <T extends ICCRecipe> Set<T> getRecipes(Class<T> recipeClass) {
        return (Set<T>) RECIPES.getOrDefault(recipeClass, new HashSet<>());
    }
}
