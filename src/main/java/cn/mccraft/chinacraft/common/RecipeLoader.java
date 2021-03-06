package cn.mccraft.chinacraft.common;

import cn.mccraft.chinacraft.init.CCItems;
import cn.mccraft.chinacraft.item.crafting.RecipesSilkDyes;
import cn.mccraft.util.loader.Load;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import static cn.mccraft.chinacraft.init.CCBlocks.*;
import static cn.mccraft.chinacraft.init.CCItems.*;
import static net.minecraft.init.Items.BRICK;
import static net.minecraft.init.Items.WATER_BUCKET;
import static net.minecraftforge.fml.common.registry.GameRegistry.*;
import static net.minecraftforge.oredict.RecipeSorter.Category.SHAPELESS;

/**
 * Created by Mouse on 2017/1/28.
 */
public class RecipeLoader {

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
        addRecipe(new ShapedOreRecipe(ART_KNIFE, " # ", " X ", '#', "ingotIron", 'X', "stickWood"));

        // 大理石
        addRecipe(new ItemStack(CHISELED_MARBLE, 4), "##", "##", '#', PILLAR_MARBLE);
        addRecipe(new ItemStack(SMOOTH_MARBLE, 4), "##", "##", '#', CHISELED_MARBLE);
        addRecipe(new ItemStack(PILLAR_MARBLE, 4), "##", "##", '#', SMOOTH_MARBLE);
        addRecipe(new ItemStack(SMOOTH_MARBLE, 4), "##", "##", '#', MARBLE);
        addRecipe(new ItemStack(MARBLE_SLAB, 6), "   ", "   ", "###", '#', SMOOTH_MARBLE);
        addRecipe(new ItemStack(MARBLE_STAIRS, 4), "#  ", "## ", "###", '#', SMOOTH_MARBLE);
        addRecipe(new ItemStack(MARBLE_WALL, 6), "   ", "###", "###", '#', SMOOTH_MARBLE);
        addRecipe(new ItemStack(MARBLE_DOOR, 3), "## ", "## ", "## ", '#', SMOOTH_MARBLE);
        addRecipe(new ItemStack(MARBLE_DOOR, 3), " ##", " ##", " ##", '#', SMOOTH_MARBLE);

        // 竹制品
        addRecipe(new ItemStack(BAMBOO_PLANK), "## ", "## ", '#', BAMBOO_ITEM);
        addRecipe(new ItemStack(BAMBOO_SLAB, 6), "###", '#', BAMBOO_PLANK);
        addRecipe(new ItemStack(BAMBOO_STAIRS, 4), "#  ", "## ","###", '#', BAMBOO_PLANK);
        addRecipe(new ItemStack(BAMBOO_DOOR, 3), " ##", " ##", " ##", BAMBOO_ITEM);

        // 杀青竹制品
        for(int i = 0;i<6;i++){
            addRecipe(new ItemStack(PEELED_BAMBOO_PLANK, 8), "###", "#A#", "###", '#', BAMBOO_PLANK, 'A', new ItemStack(ART_KNIFE,1,i));
        }
        addRecipe(new ItemStack(PEELED_BAMBOO_PLANK), "## ", "## ", '#', PEELED_BAMBOO_ITEM);
        addRecipe(new ItemStack(PEELED_BAMBOO_SLAB, 6), "###", '#', PEELED_BAMBOO_PLANK);
        addRecipe(new ItemStack(PEELED_BAMBOO_STAIRS, 4), "#  ", "## ","###", '#', PEELED_BAMBOO_PLANK);
        // 青砖
        for (int i = 1; i <= 8; i++) {
            Object objs[] = new Object[i + 1];
            objs[0] = WATER_BUCKET;
            for (int j = 1; j <= i; j++) objs[j] = BRICK;
            addShapelessRecipe(new ItemStack(BLACK_BRICK, i), objs);
        }
        // 木窗
        for(int i = 0;i<6;i++){
            addRecipe(new ItemStack(WOODEN_WINDOW), " # ", "#A#", " # ", '#', Items.STICK, 'A', new ItemStack(ART_KNIFE,1,i));
        }

        // 黑砖
        addRecipe(new ItemStack(BLACK_BRICK_BLOCK), "##", "##", '#', BLACK_BRICK);
        addRecipe(new ItemStack(BLACK_BRICK_SLAB, 6), "   ", "   ", "###", '#', BLACK_BRICK_BLOCK);
        addRecipe(new ItemStack(BLACK_BRICK_STAIRS, 4), "#  ", "## ", "###", '#', BLACK_BRICK_BLOCK);
        addRecipe(new ItemStack(BLACK_BRICK_WALL, 6), "   ", "###", "###", '#', BLACK_BRICK_BLOCK);

        // 丝绸
        RecipeSorter.register("chinacraft:silkdyes", RecipesSilkDyes.class, SHAPELESS, "after:minecraft:shapeless");
        addRecipe(new RecipesSilkDyes());
    }

    @Load(LoaderState.INITIALIZATION)
    public void loadSmeltingRecipe() {
        addSmelting(COPPER_ORE, new ItemStack(COPPER_INGOT), 0.8f);
        addSmelting(TIN_ORE, new ItemStack(TIN_INGOT), 0.8f);
        addSmelting(SILVER_ORE, new ItemStack(SILVER_INGOT), 1.2f);
        addSmelting(BAMBOO_ITEM,new ItemStack(PEELED_BAMBOO_ITEM),0.8f);
    }
}
