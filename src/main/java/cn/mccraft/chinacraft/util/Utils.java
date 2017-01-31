package cn.mccraft.chinacraft.util;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.UUID;

/**
 * Created by Mouse on 2017/1/31.
 */
public final class Utils {

    private Utils(){}

    public static EntityPlayer getPlayerByUUID(UUID uuid) {
        return FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUUID(uuid);
    }

    public static EntityPlayer getPlayerByName(String name) {
        return FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(name);
    }

    public static GameProfile getOfflinePlayerByName(String name) {
       for(GameProfile player:FMLCommonHandler.instance().getMinecraftServerInstance().getServerStatusResponse().getPlayers().getPlayers())
           if(player.getName().equalsIgnoreCase(name))
               return player;
       return null;
    }

    public static GameProfile getOfflinePlayerByUUID(UUID uuid) {
        for(GameProfile player:FMLCommonHandler.instance().getMinecraftServerInstance().getServerStatusResponse().getPlayers().getPlayers())
            if(player.getId().equals(uuid))
                return player;
        return null;
    }
}
