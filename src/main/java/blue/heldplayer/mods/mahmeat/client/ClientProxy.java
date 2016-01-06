package blue.heldplayer.mods.mahmeat.client;

import blue.heldplayer.mods.mahmeat.CommonProxy;
import blue.heldplayer.mods.mahmeat.ModMahMeat;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void loadModels() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModMahMeat.walrusMeat), 0, new ModelResourceLocation("mahmeat:uncooked_walrus_block"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModMahMeat.walrusMeat), 1, new ModelResourceLocation("mahmeat:cooked_walrus_block"));

        ModelLoader.setCustomModelResourceLocation(ModMahMeat.meat, 0, new ModelResourceLocation("mahmeat:uncooked_walrus"));
        ModelLoader.setCustomModelResourceLocation(ModMahMeat.meat, 1, new ModelResourceLocation("mahmeat:cooked_walrus"));
    }
}
