package cn.mccraft.chinacraft.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.UUID;

/**
 * Red packet message class.
 * 红包信息类。
 */
public class RedPacketMessage implements IMessage {
    /**
     * Packet sender
     * 红包发送人
     */
    private UUID sender;

    /**
     * Packet wish message
     * 红包祝福语
     */
    private String wish;

    /**
     * Packet receiver
     * 红包接收者
     */
    private UUID receiver;

    /**
     * Packet send status
     * 红包发送状态
     */
    private boolean isSend;

    public RedPacketMessage() {}

    public RedPacketMessage(UUID sender, String wish, UUID receiver, boolean isSend) {
        this.sender = sender;
        this.wish = wish;
        this.receiver = receiver;
        this.isSend = isSend;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        PacketBuffer pb = new PacketBuffer(buf);
        sender = UUID.fromString(pb.readStringFromBuffer(pb.readInt()));
        wish = pb.readStringFromBuffer(pb.readInt());
        receiver = UUID.fromString(pb.readStringFromBuffer(pb.readInt()));
        isSend = pb.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        PacketBuffer pb = new PacketBuffer(buf);
        pb.writeInt(sender == null ? 0 : sender.toString().length());
        pb.writeString(sender.toString());
        pb.writeInt(wish == null ? 0 : wish.length());
        pb.writeString(wish);
        pb.writeInt(receiver == null ? 0 : receiver.toString().length());
        pb.writeString(receiver.toString());
        pb.writeBoolean(isSend);
    }

    public boolean isSend() {
        return isSend;
    }

    public UUID getReceiver() {
        return receiver;
    }

    public UUID getSender() {
        return sender;
    }

    public String getWish() {
        return wish;
    }

    public static class Handler implements IMessageHandler<RedPacketMessage, IMessage> {

        @Override
        public IMessage onMessage(RedPacketMessage message, MessageContext ctx) {
            EntityPlayer player = ctx.getServerHandler().playerEntity;
            ItemStack itemStack = player.inventory.getCurrentItem();

//          FIXME  if (itemStack == null || itemStack.getItem() != ChinaCraft.redPacket)return null;

            player.inventory.setInventorySlotContents(player.inventory.currentItem, null);

            itemStack = itemStack.copy();
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setTag("wish", new NBTTagString(message.wish));
            nbt.setTag("sender", new NBTTagString(message.sender.toString()));
            itemStack.setTagInfo("redpacket", nbt);

            UUID sendee = message.receiver;
            if (!message.isSend || sendee == null || sendee.toString().isEmpty()) {
                player.inventory.setInventorySlotContents(player.inventory.currentItem, itemStack);
                return null;
            }

            EntityPlayer sendeePlayer = getPlayer(sendee);
            if (sendeePlayer == null) {
                player.inventory.setInventorySlotContents(player.inventory.currentItem, itemStack);
                player.addChatMessage(new TextComponentString(I18n.format("redpacket.not_found_player", sendee)));
                return null;
            }

            if (sendeePlayer.inventory.addItemStackToInventory(itemStack)) {
                player.addChatMessage(new TextComponentString(I18n.format("redpacket.success", sendee)));
                sendeePlayer.addChatMessage(new TextComponentString(I18n.format("redpacket.received", message.sender)));
                return null;
            } else {
                player.inventory.setInventorySlotContents(player.inventory.currentItem, itemStack);
                player.addChatMessage(new TextComponentString(I18n.format("redpacket.backpack_full", sendee)));
                return null;
            }
        }

        private EntityPlayer getPlayer(UUID name) {
            for (WorldServer world : FMLCommonHandler.instance().getMinecraftServerInstance().worldServers) {
                EntityPlayer player = world.getPlayerEntityByUUID(name);
                if (player != null) return player;
            }
            return null;
        }
    }
}
