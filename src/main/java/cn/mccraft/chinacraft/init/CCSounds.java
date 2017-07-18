package cn.mccraft.chinacraft.init;

import cn.mccraft.chinacraft.common.ChinaCraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

/**
 * SoundEvents provided by ChinaCraft mod.
 * ChinaCraft中提供的音效.
 * @author Lasm_Gratel
 */
public interface CCSounds {
    SoundEvent VOLUNTEER_MARCH = new SoundEvent(new ResourceLocation(ChinaCraft.MODID, "volunteer_march"));
    SoundEvent SPRING_FESTIVAL = new SoundEvent(new ResourceLocation(ChinaCraft.MODID, "spring_festival"));
    SoundEvent MOUNTAIN_FALL = new SoundEvent(new ResourceLocation(ChinaCraft.MODID, "mountain_fall"));
    SoundEvent PLUM_BLOSSOM = new SoundEvent(new ResourceLocation(ChinaCraft.MODID, "plum_blossom"));
}
