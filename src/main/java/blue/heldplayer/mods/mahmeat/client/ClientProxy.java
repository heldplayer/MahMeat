package blue.heldplayer.mods.mahmeat.client;

import blue.heldplayer.mods.mahmeat.CommonProxy;
import blue.heldplayer.mods.mahmeat.MahMeat;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void loadModels() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(MahMeat.walrusMeat), 0, new ModelResourceLocation("mahmeat:uncooked_walrus_block"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(MahMeat.walrusMeat), 1, new ModelResourceLocation("mahmeat:cooked_walrus_block"));

        ModelLoader.setCustomModelResourceLocation(MahMeat.meat, 0, new ModelResourceLocation("mahmeat:uncooked_walrus"));
        ModelLoader.setCustomModelResourceLocation(MahMeat.meat, 1, new ModelResourceLocation("mahmeat:cooked_walrus"));
    }
}
