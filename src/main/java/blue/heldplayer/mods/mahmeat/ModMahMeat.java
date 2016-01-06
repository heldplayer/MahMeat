package blue.heldplayer.mods.mahmeat;

import blue.heldplayer.mods.mahmeat.block.BlockMeat;
import blue.heldplayer.mods.mahmeat.item.ItemBlockMeat;
import blue.heldplayer.mods.mahmeat.item.ItemMeatFood;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = ModMahMeat.MOD_ID, name = ModMahMeat.MOD_NAME)
public class ModMahMeat {

    public static final String MOD_ID = "mahmeat";
    public static final String MOD_NAME = "Mah Meat!";
    public static final ModInfo MOD_INFO = new ModInfo(ModMahMeat.MOD_ID, ModMahMeat.MOD_NAME);

    @SidedProxy(clientSide = "blue.heldplayer.mods.mahmeat.client.ClientProxy", serverSide = "blue.heldplayer.mods.mahmeat.CommonProxy")
    public static CommonProxy proxy;

    public static BlockMeat walrusMeat;

    public static ItemMeatFood meat;

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        event.getModMetadata().version = ModMahMeat.MOD_INFO.modVersion;

        GameRegistry.registerBlock(ModMahMeat.walrusMeat = new BlockMeat(), ItemBlockMeat.class, "walrus_meat");
        ModMahMeat.walrusMeat.setUnlocalizedName("mahmeat.meat_walrus");

        GameRegistry.registerItem(ModMahMeat.meat = new ItemMeatFood(), "multi_meat_item");
        ModMahMeat.meat.setUnlocalizedName("mahmeat.meat");
        ModMahMeat.meat.registerFood(0, "walrus_uncooked", 2, 0.2F);
        ModMahMeat.meat.registerFood(1, "walrus_cooked", 6, 0.5F);

        ModMahMeat.proxy.loadModels();
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        RecipesManager.registerRecipes();
    }
}
