package blue.heldplayer.mods.mahmeat;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipesManager {

    public static void registerRecipes() {
        // Uncooked walrus meat
        GameRegistry.addShapedRecipe(new ItemStack(Item.getItemFromBlock(ModMahMeat.walrusMeat), 1, 0), "mmm", "mmm", "mmm", 'm', new ItemStack(ModMahMeat.meat, 1, 0));
        GameRegistry.addShapelessRecipe(new ItemStack(ModMahMeat.meat, 9, 0), new ItemStack(Item.getItemFromBlock(ModMahMeat.walrusMeat), 1, 0));
        // Cooked walrus meat
        GameRegistry.addShapedRecipe(new ItemStack(Item.getItemFromBlock(ModMahMeat.walrusMeat), 1, 1), "mmm", "mmm", "mmm", 'm', new ItemStack(ModMahMeat.meat, 1, 1));
        GameRegistry.addShapelessRecipe(new ItemStack(ModMahMeat.meat, 9, 1), new ItemStack(Item.getItemFromBlock(ModMahMeat.walrusMeat), 1, 1));

        Item walrus = Item.getByNameOrId("extracells:walrus");
        if (walrus != null) {
            // Allow for equal conversion rates between walri and walrus meat
            GameRegistry.addShapedRecipe(new ItemStack(Item.getItemFromBlock(ModMahMeat.walrusMeat), 1, 0), " m ", "MMm", " m ", 'm', new ItemStack(ModMahMeat.meat, 1, 0), 'M', (new ItemStack(Item.getItemFromBlock(ModMahMeat.walrusMeat), 1, 0)));
            GameRegistry.addShapelessRecipe(new ItemStack(ModMahMeat.meat, 21, 0), new ItemStack(walrus));
        } else {
            // Add a different recipe for raw walrus
            GameRegistry.addShapedRecipe(new ItemStack(Item.getItemFromBlock(ModMahMeat.walrusMeat), 1, 0), "fff", "fbf", "fff", 'f', Items.fish, 'b', Items.bone);
        }

        // Furnace recipes
        FurnaceRecipes.instance().addSmeltingRecipe(new ItemStack(ModMahMeat.meat, 1, 0), new ItemStack(ModMahMeat.meat, 1, 1), 0.35F);
    }
}
