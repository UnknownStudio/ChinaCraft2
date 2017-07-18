package cn.mccraft.chinacraft.common;

import cn.mccraft.chinacraft.init.CCSounds;
import cn.mccraft.chinacraft.util.loader.Loader;
import cn.mccraft.chinacraft.util.loader.annotation.Load;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Arrays;

public class SoundLoader implements Loader {
    @Load
    public void loadSounds() {
        Arrays.stream(CCSounds.class.getFields()).map(field -> {
            try {
                return ((SoundEvent) field.get(null)).setRegistryName(((SoundEvent) field.get(null)).getSoundName());
            } catch (IllegalAccessException ignored) {
                return SoundEvents.MUSIC_CREATIVE;
            }
        }).forEach(GameRegistry::register);
    }
}
