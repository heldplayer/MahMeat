package blue.heldplayer.mods.mahmeat;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class RecipesManager {

    public static void registerRecipes() {
        // Uncooked walrus meat
        GameRegistry.addShapedRecipe(new ItemStack(Item.getItemFromBlock(MahMeat.walrusMeat), 1, 0), "mmm", "mmm", "mmm", 'm', new ItemStack(MahMeat.meat, 1, 0));
        GameRegistry.addShapelessRecipe(new ItemStack(MahMeat.meat, 9, 0), new ItemStack(Item.getItemFromBlock(MahMeat.walrusMeat), 1, 0));
        // Cooked walrus meat
        GameRegistry.addShapedRecipe(new ItemStack(Item.getItemFromBlock(MahMeat.walrusMeat), 1, 1), "mmm", "mmm", "mmm", 'm', new ItemStack(MahMeat.meat, 1, 1));
        GameRegistry.addShapelessRecipe(new ItemStack(MahMeat.meat, 9, 1), new ItemStack(Item.getItemFromBlock(MahMeat.walrusMeat), 1, 1));

        Item walrus = Item.getItemFromBlock(Block.getBlockFromName("extracells:walrus"));
        if (walrus != null) {
            // Allow for equal conversion rates between walruses and walrus meat
            GameRegistry.addShapedRecipe(new ItemStack(walrus), " m ", "MMm", " m ", 'm', new ItemStack(MahMeat.meat, 1, 0), 'M', (new ItemStack(Item.getItemFromBlock(MahMeat.walrusMeat), 1, 0)));
            GameRegistry.addShapelessRecipe(new ItemStack(MahMeat.meat, 21, 0), new ItemStack(walrus));
        } else {
            // Add a different recipe for raw walrus
            GameRegistry.addShapedRecipe(new ItemStack(Item.getItemFromBlock(MahMeat.walrusMeat), 1, 0), "fff", "fbf", "fff", 'f', Items.fish, 'b', Items.bone);
        }

        // Furnace recipes
        FurnaceRecipes.smelting().func_151394_a(new ItemStack(MahMeat.meat, 1, 0), new ItemStack(MahMeat.meat, 1, 1), 0.35F);
    }
}
