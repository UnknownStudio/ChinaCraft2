package cn.mccraft.chinacraft.block.tileentity;

import cn.mccraft.chinacraft.api.CCRecipeManager;
import cn.mccraft.chinacraft.api.recipe.CrusherRecipe;
import cn.mccraft.chinacraft.block.machine.EnumCrusherMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import java.util.Arrays;

public class TileEntityCrusher extends TileEntity implements IItemHandler, ITickable {
    // 槽1:主原料 槽2:副原料 槽3:主产出 槽4:副产出
    private ItemStackHandler inventory = new ItemStackHandler(4);
    private EnumCrusherMaterial crusherMaterial;

    private int angle;
    private int schedule;

    public TileEntityCrusher(EnumCrusherMaterial crusherMaterial) {
        this.crusherMaterial = crusherMaterial;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        inventory.deserializeNBT(compound.getCompoundTag("inventory"));
        angle = compound.getInteger("angle");
        schedule = compound.getInteger("schedule");
    }

    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound = super.writeToNBT(compound);
        compound.setTag("inventory", inventory.serializeNBT());
        compound.setInteger("angle", angle);
        compound.setInteger("schedule", schedule);
        return compound;
    }

    @Override
    public int getSlots() {
        return 4;
    }

    @Nonnull
    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventory.getStackInSlot(slot);
    }

    public void setStackInSlot(int slot, @Nonnull ItemStack stack) {
        inventory.setStackInSlot(slot, stack);
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        return inventory.insertItem(slot, stack, simulate);
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        return inventory.extractItem(slot, amount, simulate);
    }

    @Override
    public int getSlotLimit(int slot) {
        return 64;
    }

    public EnumCrusherMaterial getCrusherMaterial() {
        return crusherMaterial;
    }

    @Override
    public void update() {
        // 存在主原料
        if (getStackInSlot(0) != ItemStack.EMPTY) {
            CCRecipeManager.getRecipes(CrusherRecipe.class).stream()
                .filter(recipe -> recipe.getMaterial() == crusherMaterial)
                .filter(recipe -> recipe.getInputItems().equals(Arrays.asList(getStackInSlot(0), getStackInSlot(1))))
                .forEach(this::parseRecipe);
        } else {
            schedule = 0;
        }
    }

    private void parseRecipe(CrusherRecipe recipe) {
        if (recipe.getInputItems().get(1) != ItemStack.EMPTY) {
            if ((getStackInSlot(0).getCount() - recipe.getInputItems().get(0).getCount()) >= 0
                && (getStackInSlot(1).getCount() - recipe.getInputItems().get(1).getCount()) >= 0
                && (getStackInSlot(2) == ItemStack.EMPTY || getStackInSlot(2).equals(recipe.getInputItems().get(0)))
                && (recipe.getOutputItems().get(1) == ItemStack.EMPTY || getStackInSlot(3) == ItemStack.EMPTY
                || getStackInSlot(3).equals(recipe.getOutputItems().get(1)))) {
                if (schedule >= recipe.getTime()) {
                    schedule = 0;
                    if (getStackInSlot(2) != ItemStack.EMPTY) {
                        ItemStack output1 = recipe.getInputItems().get(0);
                        output1.setCount(getStackInSlot(2).getCount() + output1.getCount());
                        setStackInSlot(2, output1);
                    } else {
                        setStackInSlot(2, recipe.getInputItems().get(0));
                    }
                    if (recipe.getOutputItems().get(1) != ItemStack.EMPTY) {
                        if (getStackInSlot(3) != ItemStack.EMPTY) {
                            ItemStack output2 = recipe.getOutputItems().get(1);
                            output2.setCount(getStackInSlot(3).getCount() + output2.getCount());
                            setStackInSlot(3, output2);
                        } else {
                            setStackInSlot(3, recipe.getOutputItems().get(1));
                        }
                    }
                    if ((getStackInSlot(0).getCount() - recipe.getInputItems().get(0).getCount()) == 0) {
                        setStackInSlot(0, ItemStack.EMPTY);
                    } else {
                        ItemStack input1 = getStackInSlot(0);
                        input1.setCount(input1.getCount() - recipe.getInputItems().get(0).getCount());
                        setStackInSlot(0, input1);
                    }
                    if ((getStackInSlot(1).getCount() - recipe.getInputItems().get(1).getCount()) == 0) {
                        setStackInSlot(1, ItemStack.EMPTY);
                    } else {
                        ItemStack input2 = getStackInSlot(1);
                        input2.setCount(input2.getCount() - recipe.getInputItems().get(1).getCount());
                        setStackInSlot(1, input2);
                    }
                }
            }
        } else {
            if ((getStackInSlot(0).getCount() - recipe.getInputItems().get(0).getCount()) >= 0
                && (getStackInSlot(2) == ItemStack.EMPTY || getStackInSlot(2).equals(recipe.getOutputItems().get(0)))
                && (recipe.getOutputItems().get(1) == null || getStackInSlot(3) == ItemStack.EMPTY
                || getStackInSlot(3).equals(recipe.getOutputItems().get(1)))) {
                if (schedule >= recipe.getTime()) {
                    schedule = 0;
                    if (getStackInSlot(2) != ItemStack.EMPTY) {
                        ItemStack output1 = recipe.getOutputItems().get(0);
                        output1.setCount(getStackInSlot(2).getCount() + output1.getCount());
                        setStackInSlot(2, output1);
                    } else {
                        setStackInSlot(2, recipe.getOutputItems().get(0));
                    }
                    if (recipe.getOutputItems().get(1) != ItemStack.EMPTY) {
                        if (getStackInSlot(3) != ItemStack.EMPTY) {
                            ItemStack output2 = recipe.getOutputItems().get(1);
                            output2.setCount(getStackInSlot(3).getCount() + output2.getCount());
                            setStackInSlot(3, output2);
                        } else {
                            setStackInSlot(3, recipe.getOutputItems().get(1));
                        }
                    }
                    if ((getStackInSlot(0).getCount() - recipe.getInputItems().get(0).getCount()) == 0) {
                        setStackInSlot(0, ItemStack.EMPTY);
                    } else {
                        ItemStack input1 = getStackInSlot(0);
                        input1.setCount(input1.getCount() - recipe.getInputItems().get(0).getCount());
                        setStackInSlot(0, input1);
                    }
                }
            }
        }
    }

    public int getMaxSchedule() {
        if (getStackInSlot(0) != ItemStack.EMPTY) {
            return CCRecipeManager.getRecipes(CrusherRecipe.class).stream()
                .filter(recipe -> recipe.getMaterial() == crusherMaterial)
                .filter(recipe -> recipe.getInputItems().equals(Arrays.asList(getStackInSlot(0), getStackInSlot(1))))
                .map(CrusherRecipe::getTime).findFirst().orElse(0);
        }
        return 0;
    }

    public void addAngle(int i) {
        if (getMaxSchedule() > 0) {
            schedule = schedule + i;
        }
        if (angle + i >= 360) {
            angle = i + angle - 360;
        } else {
            angle = angle + i;
        }
    }

    public ItemStackHandler getInventory() {
        return inventory;
    }

    public int getAngle() {
        return angle;
    }

    public int getSchedule() {
        return schedule;
    }
}
