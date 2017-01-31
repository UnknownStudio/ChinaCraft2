package cn.mccraft.chinacraft.client.gui;

import cn.mccraft.chinacraft.common.ChinaCraft;
import cn.mccraft.chinacraft.inventory.ContainerRedPacket;
import cn.mccraft.chinacraft.network.RedPacketMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

public class GuiContainerRedPacket extends GuiContainer {


    private GuiTextField wishTextBox;
    private GuiTextField receiverTextBox;
    private GuiButton buttonSend;

    private int wishColor = Integer.MAX_VALUE;
    private String sender="",wish="";
    private int currentItem;
    private EntityPlayer player;

    public GuiContainerRedPacket(EntityPlayer player) {
        super(new ContainerRedPacket(player));
        this.player = player;
        ItemStack itemStack = player.getHeldItemMainhand();
        currentItem = player.inventory.currentItem;
        if (itemStack.hasTagCompound()&&itemStack.getTagCompound().hasKey("redpacket")) {
            NBTTagCompound redpacket = itemStack.getTagCompound().getCompoundTag("redpacket");
            sender = redpacket.getString("sender");
            wish = redpacket.getString("wish");
        }
        if (wish == null || wish.isEmpty()) wish= I18n.format("gui.redpacket.wash");
    }

    @Override
    public void initGui() {
        this.buttonList.clear();
        Keyboard.enableRepeatEvents(true);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        wishTextBox = new GuiTextField(0,Minecraft.getMinecraft().fontRendererObj, this.xSize / 2 - 80, 65, 160, 16);
        wishTextBox.setFocused(true);
        wishTextBox.setMaxStringLength(64);
        wishTextBox.setText(wish);
        receiverTextBox = new GuiTextField(1,Minecraft.getMinecraft().fontRendererObj, this.xSize / 2 + 96, 65, 64, 16);
        receiverTextBox.setMaxStringLength(32);
        this.buttonList.add(this.buttonSend = new GuiButton(0, k + this.xSize / 2 + 96, l + 89, 64, 20,
                I18n.format("gui.redpacket.send")));
        buttonSend.enabled = false;
        updateButton();
        super.initGui();
    }

    @Override
    public boolean doesGuiPauseGame() { // 让GUI在单人模式下不会暂停游戏保存存档
        return false;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (sender == null || sender.isEmpty() || sender.equalsIgnoreCase(player.getDisplayName().getFormattedText())) {
            wishTextBox.drawTextBox();
            receiverTextBox.drawTextBox();
            String head = I18n.format("item.redpacket.name");
            this.fontRendererObj.drawString(head, this.xSize / 2 - this.fontRendererObj.getStringWidth(head) / 2, 5,
                    Integer.MAX_VALUE);
        } else {
            receiverTextBox.setEnabled(false);
            receiverTextBox.setVisible(false);
            wishTextBox.setEnabled(false);
            wishTextBox.setVisible(false);
            buttonSend.visible = false;
            this.fontRendererObj.drawString(getWish(), this.xSize / 2 - this.fontRendererObj.getStringWidth(getWish()) / 2, 68,
                    Integer.MAX_VALUE);
            String head = I18n.format("gui.redpacket.from",sender);
            this.fontRendererObj.drawString(head, this.xSize / 2 - this.fontRendererObj.getStringWidth(head) / 2, 5,
                    Integer.MAX_VALUE);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        this.mc.getTextureManager().bindTexture(new ResourceLocation("chinacraft", "textures/gui/red_packet.png"));
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected boolean checkHotbarKeys(int p_146983_1_) {
        if (p_146983_1_ == currentItem + 2)
            return false;
        else
            return super.checkHotbarKeys(p_146983_1_);
    }

    @Override
    protected void keyTyped(char par1, int par2) {
        wishTextBox.textboxKeyTyped(par1, par2);
        receiverTextBox.textboxKeyTyped(par1, par2);
        if (par1 == '\r') {
            if(wishTextBox.isFocused()) receiverTextBox.setFocused(true);
        }if (par2 == 1) {
            player.closeScreen();
            sendRedPacketToServer(false);
        }

        updateButton();
    }

    public String getWish() {
        return wishTextBox.getText();
    }

    public String getReceiver(){
        return receiverTextBox.getText();
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
        super.onGuiClosed();
    }

    @SideOnly(Side.CLIENT)
    private void sendRedPacketToServer(boolean isSend) {
        ChinaCraft.getNetwork().sendToServer(new RedPacketMessage(isSend?player.getName():sender,getWish(),receiverTextBox.getText(),isSend));
    }

    @Override
    protected void mouseClicked(int par1, int par2, int par3) throws IOException {
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        wishTextBox.mouseClicked(par1 - k, par2 - l, par3);
        receiverTextBox.mouseClicked(par1 - k, par2 - l, par3);
        super.mouseClicked(par1, par2, par3);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if(!button.enabled) return;
        switch (button.id){
            case 0:{
                sendRedPacketToServer(true);
                player.closeScreen();
                break;
            }
            default:
                break;
        }
    }

    private void updateButton() {
        buttonSend.enabled = receiverTextBox.getText() != null && !receiverTextBox.getText().isEmpty();
    }
}
