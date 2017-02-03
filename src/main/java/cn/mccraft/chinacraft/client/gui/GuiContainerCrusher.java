package cn.mccraft.chinacraft.client.gui;

import cn.mccraft.chinacraft.block.machine.EnumCrusherMaterial;
import cn.mccraft.chinacraft.block.tileentity.TileEntityCrusher;
import cn.mccraft.chinacraft.common.ChinaCraft;
import cn.mccraft.chinacraft.inventory.ContainerCrusher;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

public class GuiContainerCrusher extends GuiContainer {
    private TileEntityCrusher crusherEntity;

    private static final ResourceLocation TEXTURE = new ResourceLocation(ChinaCraft.MODID, "textures/gui/buhrimill.png");
    public GuiContainerCrusher(InventoryPlayer inventoryPlayer, TileEntityCrusher crusherEntity) {
        super(new ContainerCrusher(inventoryPlayer, crusherEntity));
        this.crusherEntity = crusherEntity;
    }

    @Override
    public boolean doesGuiPauseGame() { // 让GUI在单人模式下不会暂停游戏保存存档
        return false;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        String s = I18n.format("gui.crusher." + crusherEntity.getCrusherMaterial().getName() + ".title"); // 设置Gui标题
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 3, 4210752);
        this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2,
            4210752);

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        this.mc.getTextureManager().bindTexture(TEXTURE);
        GlStateManager.glTexEnvi(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_ADD);
        EnumCrusherMaterial material = crusherEntity.getCrusherMaterial();
        glColor4f(material.getRed(), material.getGreen(), material.getBlue(), material.getAlpha());
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int b = crusherEntity.getSchedule();
        float max = crusherEntity.getMaxSchedule() * 1.0F;
        if (b > 0 && max > 0) {
            this.drawTexturedModalRect(k + 73, l + 25, 176, 0, (int) (24 * (b / max)), 16);
        }
    }
}
