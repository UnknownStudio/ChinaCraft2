package cn.mccraft.chinacraft.common;

import cn.mccraft.chinacraft.init.CCAchievements;
import cn.mccraft.chinacraft.util.loader.Loader;
import cn.mccraft.chinacraft.util.loader.annotation.Load;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.fml.common.LoaderState;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class AchievementsLoader implements Loader {
    private AchievementPage ccAchievementPage;

    @Load(LoaderState.POSTINITIALIZATION)
    public void registerAchievements() {
        Set<Achievement> achievements = new HashSet<>();
        for (Field field : CCAchievements.class.getFields()) {
            try {
                Achievement achievement = (Achievement) field.get(null);
                achievement.registerStat();
                achievements.add(achievement);
            } catch (Exception e) {
                ChinaCraft.getLogger().warn("Un-able to register achievement", e);
            }
        }
        ccAchievementPage = new AchievementPage(ChinaCraft.NAME, achievements.toArray(new Achievement[0]));
        AchievementPage.registerAchievementPage(ccAchievementPage);
    }

    public AchievementPage getCCAchievementPage() {
        return ccAchievementPage;
    }
}
