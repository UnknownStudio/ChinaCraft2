package cn.mccraft.chinacraft.util.loader.annotation;

import net.minecraft.item.ItemBlock;

public @interface RegBlock {
    /**
     * The params to build registryName and unlocalizedName.
     * @see cn.mccraft.chinacraft.util.NameBuilder
     */
    String[] value();

    /**
     * All {@link net.minecraftforge.oredict.OreDictionary} values to be registered.
     */
    String[] oreDict() default {};


    /**
     * Add prefix on value
     * Example: gold -> blockGold
     */
    String prefix() default "block";

    /**
     *
     */
    Class<? extends ItemBlock> itemClass() default ItemBlock.class;
}
