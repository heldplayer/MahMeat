package blue.heldplayer.mods.mahmeat;

import blue.heldplayer.mods.mahmeat.item.ItemMeatFood;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipesManager {

    public static void registerRecipes() {
        // Uncooked walrus meat
        Item walrusMeat = Item.getItemFromBlock(MahMeat.walrusMeat);
        ItemMeatFood meat = MahMeat.meat;
        if (walrusMeat == null || meat == null) {
            throw new IllegalStateException();
        }

        GameRegistry.addShapedRecipe(new ItemStack(walrusMeat, 1, 0), "mmm", "mmm", "mmm", 'm', new ItemStack(meat, 1, 0));
        GameRegistry.addShapelessRecipe(new ItemStack(meat, 9, 0), new ItemStack(walrusMeat, 1, 0));
        // Cooked walrus meat
        GameRegistry.addShapedRecipe(new ItemStack(walrusMeat, 1, 1), "mmm", "mmm", "mmm", 'm', new ItemStack(meat, 1, 1));
        GameRegistry.addShapelessRecipe(new ItemStack(meat, 9, 1), new ItemStack(walrusMeat, 1, 1));

        Item walrus = Item.getByNameOrId("extracells:walrus");
        if (walrus != null) {
            // Allow for equal conversion rates between walruses and walrus meat
            GameRegistry.addShapedRecipe(new ItemStack(walrus), " m ", "MMm", " m ", 'm', new ItemStack(meat, 1, 0), 'M', (new ItemStack(walrusMeat, 1, 0)));
            GameRegistry.addShapelessRecipe(new ItemStack(meat, 21, 0), new ItemStack(walrus));
        } else {
            // Add a different recipe for raw walrus
            GameRegistry.addShapedRecipe(new ItemStack(walrusMeat, 1, 0), "fff", "fbf", "fff", 'f', Items.FISH, 'b', Items.BONE);
        }

        // Furnace recipes
        FurnaceRecipes.instance().addSmeltingRecipe(new ItemStack(meat, 1, 0), new ItemStack(meat, 1, 1), 0.35F);
    }
}
