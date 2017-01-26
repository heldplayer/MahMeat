package blue.heldplayer.mods.mahmeat.item;

import javax.annotation.Nonnull;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

public class ItemBlockMeat extends ItemBlock {

    public ItemBlockMeat(Block block) {
        super(block);
        this.setHasSubtypes(true);
        this.setRegistryName(block.getRegistryName());
    }

    @Nonnull
    @Override
    public String getUnlocalizedName(ItemStack stack) {
        switch (stack.getItemDamage()) {
            case 0:
                return this.block.getUnlocalizedName();
            case 1:
                return this.block.getUnlocalizedName() + ".cooked";
            default:
                return this.block.getUnlocalizedName() + ".unknown";
        }
    }

    @Nonnull
    @Override
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        return I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name").trim();
    }

    @Override
    public int getMetadata(int damage) {
        return damage & 1;
    }
}
