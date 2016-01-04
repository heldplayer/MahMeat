package blue.heldplayer.mods.mahmeat;

import blue.heldplayer.mods.mahmeat.block.BlockWalrusMeat;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = ModMahMeat.MOD_ID, name = ModMahMeat.MOD_NAME)
public class ModMahMeat {

    public static final String MOD_ID = "mahmeat";
    public static final String MOD_NAME = "Mah Meat!";
    public static final ModInfo MOD_INFO = new ModInfo(ModMahMeat.MOD_ID, ModMahMeat.MOD_NAME);

    public static BlockWalrusMeat walrusMeat;

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        event.getModMetadata().version = ModMahMeat.MOD_INFO.modVersion;

        GameRegistry.registerBlock(ModMahMeat.walrusMeat = new BlockWalrusMeat(), null, "walrus_meat");
    }
}
