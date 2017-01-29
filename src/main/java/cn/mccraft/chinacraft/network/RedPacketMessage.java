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

/**
 * Created by Mouse on 2017/1/28.
 */
public class RedPacketMessage implements IMessage { // 包类

    public String sender;
    public String wish;
    public String sendee;
    public boolean issend;

    public RedPacketMessage() {}

    public RedPacketMessage(String sender,String wish,String sendee,boolean issend) {
        this.sender=sender;
        this.wish=wish;
        this.sendee=sendee;
        this.issend=issend;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        PacketBuffer pb= new PacketBuffer(buf);
        sender=pb.readStringFromBuffer(pb.readInt());
        wish=pb.readStringFromBuffer(pb.readInt());
        sendee=pb.readStringFromBuffer(pb.readInt());
        issend=pb.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        PacketBuffer pb = new PacketBuffer(buf);
        pb.writeInt(sender == null ? 0 : sender.length());
        pb.writeString(sender);
        pb.writeInt(wish == null ? 0 : wish.length());
        pb.writeString(wish);
        pb.writeInt(sendee == null ? 0 : sendee.length());
        pb.writeString(sendee);
        pb.writeBoolean(issend);
    }

    public static class Handler implements IMessageHandler<RedPacketMessage,IMessage>{

        @Override
        public IMessage onMessage(RedPacketMessage message, MessageContext ctx) {
            EntityPlayer player = ctx.getServerHandler().playerEntity;
            ItemStack itemStack = player.inventory.getCurrentItem();

//          FIXME  if (itemStack == null || itemStack.getItem() != ChinaCraft.redPacket)return null;

            player.inventory.setInventorySlotContents(player.inventory.currentItem, null);

            itemStack = itemStack.copy();
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setTag("wish",new NBTTagString(message.wish));
            nbt.setTag("sender",new NBTTagString(message.sender));
            itemStack.setTagInfo("redpacket", nbt);

            String sendee = message.sendee;
            if (!message.issend || sendee == null || sendee.isEmpty()) {
                player.inventory.setInventorySlotContents(player.inventory.currentItem, itemStack);
                return null;
            }

            EntityPlayer sendeePlayer = getPlayer(sendee);
            if (sendeePlayer == null) {
                player.inventory.setInventorySlotContents(player.inventory.currentItem, itemStack);
                player.addChatMessage(new TextComponentString(I18n.format("redpacket.not_found_player",sendee)));
                return null;
            }

            if (sendeePlayer.inventory.addItemStackToInventory(itemStack)) {
                player.addChatMessage(new TextComponentString(I18n.format("redpacket.success",sendee)));
                sendeePlayer.addChatMessage(new TextComponentString(I18n.format("redpacket.received",message.sender)));
                return null;
            } else {
                player.inventory.setInventorySlotContents(player.inventory.currentItem, itemStack);
                player.addChatMessage(new TextComponentString(I18n.format("redpacket.backpack_full",sendee)));
                return null;
            }
        }

        private EntityPlayer getPlayer(String name) {
            for (WorldServer world : FMLCommonHandler.instance().getMinecraftServerInstance().worldServers) {
                EntityPlayer player = world.getPlayerEntityByName(name);
                if (player != null) return player;
            }
            return null;
        }
    }
}
