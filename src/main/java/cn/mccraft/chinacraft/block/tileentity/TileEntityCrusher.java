package cn.mccraft.chinacraft.block.tileentity;

import cn.mccraft.chinacraft.api.CCRecipeManager;
import cn.mccraft.chinacraft.api.recipe.CrusherRecipe;
import cn.mccraft.chinacraft.block.machine.EnumCrusherMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Optional;

public class TileEntityCrusher extends TileEntity implements ITickable {
    // 槽1:主原料 槽2:副原料 槽3:主产出 槽4:副产出
    private ItemStackHandler inventory = new ItemStackHandler(4);
    private EnumCrusherMaterial crusherMaterial;
    private EnergyStorage energyStorage = new EnergyStorage(10000);

    private int progress = 0;
    private CrusherRecipe currentRecipe;

    public TileEntityCrusher(EnumCrusherMaterial crusherMaterial) {
        this.crusherMaterial = crusherMaterial;
        FMLCommonHandler.instance().getMinecraftServerInstance().registerTickable(this);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        inventory.deserializeNBT(compound.getCompoundTag("inventory"));
        CapabilityEnergy.ENERGY.getStorage().readNBT(CapabilityEnergy.ENERGY, energyStorage, null, compound.getTag("energy"));
        CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.getStorage().readNBT(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, inventory, null, compound.getTag("inventory"));
    }

    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound = super.writeToNBT(compound);
        compound.setTag("inventory", inventory.serializeNBT());
        compound.setTag("energy", CapabilityEnergy.ENERGY.getStorage().writeNBT(CapabilityEnergy.ENERGY, energyStorage, null));
        compound.setTag("inventory", CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.getStorage().writeNBT(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, inventory, null));
        return compound;
    }

    public EnumCrusherMaterial getCrusherMaterial() {
        return crusherMaterial;
    }

    @Override
    public void update() {
        if (currentRecipe != null && progress != 0) {
            if (progress == currentRecipe.getTime()) { // 达到时间, 输出
                inventory.setStackInSlot(0, ItemStack.EMPTY);
                inventory.setStackInSlot(1, ItemStack.EMPTY);
                inventory.setStackInSlot(2, currentRecipe.getOutputItems().get(0));
                inventory.setStackInSlot(3, currentRecipe.getOutputItems().get(1));
                currentRecipe = null;
                progress = 0;
            } else {
                // 继续运转
                ++progress;
            }
        } else if (progress != 0)
            // 清理
            progress = 0;
        else {
            progress = 0;
            Optional<CrusherRecipe> recipe = CCRecipeManager.getRecipe(CrusherRecipe.class, Arrays.asList(inventory.getStackInSlot(0), inventory.getStackInSlot(1)));
            recipe.ifPresent(crusherRecipe -> currentRecipe = crusherRecipe);
            if (energyStorage.getEnergyStored() >= currentRecipe.getEnergy() && energyStorage.canExtract())
                energyStorage.extractEnergy(currentRecipe.getEnergy(), false);
        }
    }

    public int getProgress() {
        return progress;
    }

    public int getCurrentMaxProgress() {
        return currentRecipe != null ? currentRecipe.getTime() : 0;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public ItemStackHandler getInventory() {
        return inventory;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityEnergy.ENERGY || capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @SuppressWarnings("unchecked")
    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityEnergy.ENERGY ? (T) energyStorage : capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T) inventory : super.getCapability(capability, facing);
    }
}
