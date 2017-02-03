package cn.mccraft.chinacraft.common.gui;

import cn.mccraft.chinacraft.block.tileentity.TileEntityCrusher;
import cn.mccraft.chinacraft.client.gui.GuiContainerCrusher;
import cn.mccraft.chinacraft.client.gui.GuiContainerRedPacket;
import cn.mccraft.chinacraft.inventory.ContainerCrusher;
import cn.mccraft.chinacraft.inventory.ContainerRedPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Created by Mouse on 2017/1/28.
 */
public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        switch (EnumGuiType.values()[id]) {
            case RED_PACKET:
                return new ContainerRedPacket(player);
            case CRUSHER:
                return new ContainerCrusher(player.inventory, (TileEntityCrusher) world.getTileEntity(new BlockPos(x, y, z)));
            default:
                return null;
        }
       /* try {
            return EnumGuiType.values()[id].getContainerClass().newInstance();
        } catch (Exception e) {
            ChinaCraft.getLogger().error("Un-able to create a container with id " + id, e);
        }
        return null;*/
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        switch (EnumGuiType.values()[id]) {
            case RED_PACKET:
                return new GuiContainerRedPacket(player);
            case CRUSHER:
                return new GuiContainerCrusher(player.inventory, (TileEntityCrusher) world.getTileEntity(new BlockPos(x, y, z)));
            default:
                return null;
        }
        /*try {
            Class<? extends GuiScreen> guiClass = EnumGuiType.values()[id].getGuiClass();
            for (Constructor<?> constructor : guiClass.getConstructors()) {
                if (constructor.getParameterCount() == 0)
                    return constructor.newInstance();
                else if (Container.class.isAssignableFrom(constructor.getParameterTypes()[0]))
                    return constructor.newInstance(EnumGuiType.values()[id].getContainerClass().newInstance());
            }
        } catch (Exception e) {
            ChinaCraft.getLogger().error("Un-able to create a gui with id " + id, e);
        }
        return null;*/
    }
}
