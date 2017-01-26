package blue.heldplayer.mods.mahmeat;

import blue.heldplayer.mods.mahmeat.block.BlockMeat;
import blue.heldplayer.mods.mahmeat.item.ItemBlockMeat;
import blue.heldplayer.mods.mahmeat.item.ItemMeatFood;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = "mahmeat", name = "Mah Meat!", certificateFingerprint = "50d7581cf144a9c1c8d2529f263849db7717cdf9")
public class MahMeat {

    @SidedProxy(clientSide = "blue.heldplayer.mods.mahmeat.client.ClientProxy", serverSide = "blue.heldplayer.mods.mahmeat.CommonProxy")
    public static CommonProxy proxy;

    public static BlockMeat walrusMeat;

    public static ItemMeatFood meat;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        GameRegistry.registerBlock(MahMeat.walrusMeat = new BlockMeat(), ItemBlockMeat.class, "walrus_meat");
        MahMeat.walrusMeat.setUnlocalizedName("mahmeat.meat_walrus");

        GameRegistry.registerItem(MahMeat.meat = new ItemMeatFood(), "multi_meat_item");
        MahMeat.meat.setUnlocalizedName("mahmeat.meat");
        MahMeat.meat.registerFood(0, "walrus_uncooked", 2, 0.2F);
        MahMeat.meat.registerFood(1, "walrus_cooked", 6, 0.5F);

        MahMeat.proxy.loadModels();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        RecipesManager.registerRecipes();
    }

    @Mod.EventHandler
    public void onFingerprintViolation(FMLFingerprintViolationEvent event) {
        if (!event.isDirectory) {
            System.err.println("Invalid fingerprint detected!");
            System.err.println("Expected: " + event.expectedFingerprint);
            System.err.println("Actual:   " + event.fingerprints.toString());
        }
    }
}
