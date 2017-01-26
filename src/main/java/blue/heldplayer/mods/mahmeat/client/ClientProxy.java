package blue.heldplayer.mods.mahmeat.client;

import blue.heldplayer.mods.mahmeat.CommonProxy;
import blue.heldplayer.mods.mahmeat.MahMeat;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void loadModels() {
        Item walrusMeat = Item.getItemFromBlock(MahMeat.walrusMeat);
        ModelLoader.setCustomModelResourceLocation(walrusMeat, 0, new ModelResourceLocation("mahmeat:uncooked_walrus_block"));
        ModelLoader.setCustomModelResourceLocation(walrusMeat, 1, new ModelResourceLocation("mahmeat:cooked_walrus_block"));

        ModelLoader.setCustomModelResourceLocation(MahMeat.meat, 0, new ModelResourceLocation("mahmeat:uncooked_walrus"));
        ModelLoader.setCustomModelResourceLocation(MahMeat.meat, 1, new ModelResourceLocation("mahmeat:cooked_walrus"));
    }
}
