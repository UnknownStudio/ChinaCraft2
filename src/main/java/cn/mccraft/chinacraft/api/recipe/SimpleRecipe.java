package cn.mccraft.chinacraft.api.recipe;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SimpleRecipe implements ICCRecipe {
    private List<ItemStack> inputItems = new ArrayList<>();
    private List<ItemStack> outputItems = new ArrayList<>();

    @Override
    public List<ItemStack> getInputItems() {
        return inputItems;
    }

    @Override
    public List<ItemStack> getOutputItems() {
        return outputItems;
    }

    @Override
    public SimpleRecipe addInputItem(ItemStack stack) {
        inputItems.add(stack);
        return this;
    }

    @Override
    public SimpleRecipe addOutputItem(ItemStack stack) {
        outputItems.add(stack);
        return this;
    }

    @Override
    public SimpleRecipe setInputItems(List<ItemStack> stacks) {
        inputItems = stacks;
        return this;
    }

    @Override
    public SimpleRecipe setOutputItems(List<ItemStack> stacks) {
        outputItems = stacks;
        return this;
    }
}
