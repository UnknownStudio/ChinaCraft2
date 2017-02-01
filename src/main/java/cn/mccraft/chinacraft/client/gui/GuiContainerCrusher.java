package cn.mccraft.chinacraft.client.gui;

import cn.mccraft.chinacraft.inventory.ContainerCrusher;
import net.minecraft.client.gui.inventory.GuiContainer;

public class GuiContainerCrusher extends GuiContainer {
    public GuiContainerCrusher() {
        super(new ContainerCrusher());
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

    }
}
