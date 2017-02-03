package cn.mccraft.chinacraft.init;

import net.minecraft.stats.Achievement;

/**
 * All achievements provided by ChinaCraft mod.
 * 所有ChinaCraft模组提供的成就。
 */
public interface CCAchievements {
    Achievement FIRST_BRONZE = new Achievement("achievement.firstBronze", "firstBronze", 0, 0, CCItems.BRONZE_INGOT, null);
    Achievement FIRST_BRONZE_PICKAXE = new Achievement("achievement.firstBronzePickaxe", "firstBronzePickaxe", 2, 0, CCItems.BRONZE_PICKAXE, FIRST_BRONZE);
}
