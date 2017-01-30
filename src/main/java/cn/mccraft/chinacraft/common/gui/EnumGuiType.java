package cn.mccraft.chinacraft.common.gui;

import cn.mccraft.chinacraft.client.gui.GuiContainerCrusher;
import cn.mccraft.chinacraft.client.gui.GuiContainerRedPacket;
import cn.mccraft.chinacraft.inventory.ContainerCrusher;
import cn.mccraft.chinacraft.inventory.ContainerRedPacket;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.inventory.Container;

public enum EnumGuiType {
    RED_PACKET(ContainerRedPacket.class, GuiContainerRedPacket.class),
    CRUSHER(ContainerCrusher.class, GuiContainerCrusher.class);

    private final Class<? extends Container> containerClass;
    private final Class<? extends GuiScreen> guiClass;

    EnumGuiType(Class<? extends Container> containerClass, Class<? extends GuiScreen> guiClass) {
        this.containerClass = containerClass;
        this.guiClass = guiClass;
    }

    public Class<? extends Container> getContainerClass() {
        return containerClass;
    }

    public Class<? extends GuiScreen> getGuiClass() {
        return guiClass;
    }
}
