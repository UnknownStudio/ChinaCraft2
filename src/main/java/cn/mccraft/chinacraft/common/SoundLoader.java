package cn.mccraft.chinacraft.common;

import cn.mccraft.chinacraft.init.CCSounds;
import cn.mccraft.chinacraft.util.loader.ILoader;
import cn.mccraft.chinacraft.util.loader.annotation.Load;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Arrays;

public class SoundLoader implements ILoader {
    @Load
    public void loadSounds() {
        Arrays.stream(CCSounds.class.getFields()).map(field -> {
            try {
                return (SoundEvent) field.get(null);
            } catch (IllegalAccessException ignored) {
                return SoundEvents.MUSIC_CREATIVE;
            }
        }).forEach(GameRegistry::register);
    }
}
