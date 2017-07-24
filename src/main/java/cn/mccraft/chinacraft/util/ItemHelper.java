package cn.mccraft.chinacraft.util;

import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.util.text.translation.LanguageMap;
import org.lwjgl.input.Keyboard;

import java.util.Collections;
import java.util.List;

public class ItemHelper {

    /**
     * 添加可以Shift显示的Lore
     * @param lores addInformation 的arg3 的列表
     * @param shifts 按下shift后显示的内容
     */
    public static void shiftLore(List lores, String ...shifts){
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
            Collections.addAll(lores,shifts);
        else
            lores.add(TextFormatting.WHITE + I18n.translateToLocal("gui.inventory.shiftfordetail"));
    }

    /**
     * 添加可以Shift显示的Lore
     * @param lores addInformation 的arg3 的列表
     * @param itemName 未按下shift时显示的内容
     */
    public static void shiftLoreWithStat(List lores, String itemName){
        if (I18n.canTranslate(itemName + ".lore"))
            lores.add(I18n.translateToLocal(itemName + ".lore"));
        else {
            int i = 1;
            while (I18n.canTranslate(itemName + ".lore." + i)){
                lores.add(I18n.translateToLocal(itemName + ".lore." + i));
                i++;
            }
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            if (I18n.canTranslate(itemName + ".hidelore")) {
                lores.add("");
                lores.add(TextFormatting.WHITE + I18n.translateToLocal(itemName + ".hidelore"));
            } else {
                int i = 1;
                while (I18n.canTranslate(itemName + ".hidelore." + i)) {
                    if (i == 1) lores.add("");
                    lores.add(TextFormatting.WHITE + I18n.translateToLocal(itemName + ".hidelore." + i));
                    i++;
                }
            }
        } else if (I18n.canTranslate(itemName + ".hidelore") || I18n.canTranslate(itemName + ".hidelore.1")){
            lores.add("");
            lores.add(I18n.translateToLocal("gui.inventory.shiftfordetail"));
        }
    }
}
