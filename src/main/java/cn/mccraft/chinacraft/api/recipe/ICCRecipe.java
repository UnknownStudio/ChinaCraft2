package cn.mccraft.chinacraft.api.recipe;

import net.minecraft.item.ItemStack;

import java.util.List;

public interface ICCRecipe {
    List<ItemStack> getInputItems();
    List<ItemStack> getOutputItems();

    ICCRecipe setInputItems(List<ItemStack> stacks);
    ICCRecipe setOutputItems(List<ItemStack> stacks);

    ICCRecipe addInputItem(ItemStack stack);
    ICCRecipe addOutputItem(ItemStack stack);

    default int getInputSlotSize() {
        return getInputItems().size();
    }

    default int getOutputSlotSize() {
        return getOutputItems().size();
    }
}
