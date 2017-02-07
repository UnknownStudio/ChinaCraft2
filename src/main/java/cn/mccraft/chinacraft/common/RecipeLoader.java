package cn.mccraft.chinacraft.common;

import cn.mccraft.chinacraft.init.CCItems;
import cn.mccraft.chinacraft.util.loader.ILoader;
import cn.mccraft.chinacraft.util.loader.annotation.Load;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import static cn.mccraft.chinacraft.init.CCBlocks.*;
import static cn.mccraft.chinacraft.init.CCItems.*;
import static net.minecraft.init.Items.*;
import static net.minecraft.init.Blocks.*;
import static net.minecraftforge.fml.common.registry.GameRegistry.*;

/**
 * Created by Mouse on 2017/1/28.
 */
public class RecipeLoader implements ILoader {

    @Load(LoaderState.INITIALIZATION)
    public void loadCraftingRecipe() {
        // 方块合成
        addRecipe(new ShapedOreRecipe(COPPER_BLOCK, "###", "###", "###", '#', "ingotCopper"));
        addRecipe(new ShapelessOreRecipe(new ItemStack(COPPER_INGOT, 9), "blockCopper"));
        addRecipe(new ShapedOreRecipe(TIN_BLOCK, "###", "###", "###", '#', "ingotTin"));
        addRecipe(new ShapelessOreRecipe(new ItemStack(TIN_INGOT, 9), "blockTin"));
        addRecipe(new ShapedOreRecipe(SILVER_BLOCK, "###", "###", "###", '#', "ingotSilver"));
        addRecipe(new ShapelessOreRecipe(new ItemStack(SILVER_INGOT, 9), "blockSilver"));
        addRecipe(new ShapedOreRecipe(BRONZE_BLOCK, "###", "###", "###", '#', "ingotBronze"));
        addRecipe(new ShapelessOreRecipe(new ItemStack(BRONZE_INGOT, 9), "blockBronze"));

        // 锭合成
        addRecipe(new ShapelessOreRecipe(new ItemStack(BRONZE_INGOT, 3), "ingotCopper", "ingotCopper", "ingotCopper", "ingotTin"));

        // 工具合成
        addRecipe(new ShapedOreRecipe(CCItems.BRONZE_PICKAXE, "###", " X ", " X ", '#', "ingotBronze", 'X', "stickWood"));
        addRecipe(new ShapedOreRecipe(CCItems.BRONZE_AXE, "## ", "#X ", " X ", '#', "ingotBronze", 'X', "stickWood"));
        addRecipe(new ShapedOreRecipe(CCItems.BRONZE_AXE, " ##", " X#", " X ", '#', "ingotBronze", 'X', "stickWood"));
        addRecipe(new ShapedOreRecipe(CCItems.BRONZE_SHOVEL, " # ", " X ", " X ", '#', "ingotBronze", 'X', "stickWood"));
        addRecipe(new ShapedOreRecipe(CCItems.BRONZE_SWORD, " # ", " # ", " X ", '#', "ingotBronze", 'X', "stickWood"));
        addRecipe(new ShapedOreRecipe(CCItems.BRONZE_HOE, "## ", " X ", " X ", '#', "ingotBronze", 'X', "stickWood"));
        addRecipe(new ShapedOreRecipe(CCItems.BRONZE_HOE, " ##", " X ", " X ", '#', "ingotBronze", 'X', "stickWood"));

        // 大理石
        addRecipe(new ItemStack(CHISELED_MARBLE, 4), "## ", "## ", "   ", '#', PILLAR_MARBLE);
        addRecipe(new ItemStack(SMOOTH_MARBLE, 4), "## ", "## ", "   ", '#', CHISELED_MARBLE);
        addRecipe(new ItemStack(PILLAR_MARBLE, 4), "## ", "## ", "   ", '#', SMOOTH_MARBLE);
        addRecipe(new ItemStack(SMOOTH_MARBLE, 4), "## ", "## ", "   ", '#', MARBLE);
        addRecipe(new ItemStack(MARBLE_SLAB, 6), new Object[]{"   ", "   ", "###", '#', SMOOTH_MARBLE});
        addRecipe(new ItemStack(MARBLE_STAIRS, 4), new Object[]{"#  ", "## ", "###", '#', SMOOTH_MARBLE});
        addRecipe(new ItemStack(MARBLE_STAIRS, 4), new Object[]{"  #", " ##", "###", '#', SMOOTH_MARBLE});
        addRecipe(new ItemStack(MARBLE_WALL, 6), new Object[]{"   ", "###", "###", '#', SMOOTH_MARBLE});

        //青砖
        {
            Object objs[] = new Object[9];
            objs[0] = WATER_BUCKET;
            for (int i = 1; i <= 8; i++) {
                objs[i] = BRICK;
                addShapelessRecipe(new ItemStack(BLACK_BRICK, i), objs);
            }
        }
        addRecipe(new ItemStack(BLACK_BRICK_BLOCK), new Object[]{"## ", "## ", " ", '#', BLACK_BRICK});
        addRecipe(new ItemStack(BLACK_BRICK_SLAB, 6), new Object[]{"   ", "   ", "###", '#', BLACK_BRICK_BLOCK});
        addRecipe(new ItemStack(BLACK_BRICK_STAIRS, 4), new Object[]{"#  ", "## ", "###", '#', BLACK_BRICK_BLOCK});
        addRecipe(new ItemStack(BLACK_BRICK_STAIRS, 4), new Object[]{"  #", " ##", "###", '#', BLACK_BRICK_BLOCK});
        addRecipe(new ItemStack(BLACK_BRICK_WALL, 6), new Object[]{"   ", "###", "###", '#', BLACK_BRICK_BLOCK});
    }

    @Load(LoaderState.INITIALIZATION)
    public void loadSmeltingRecipe() {
        addSmelting(COPPER_ORE, new ItemStack(COPPER_INGOT), 0.8f);
        addSmelting(TIN_ORE, new ItemStack(TIN_INGOT), 0.8f);
        addSmelting(SILVER_ORE, new ItemStack(SILVER_INGOT), 1.2f);
    }
}
