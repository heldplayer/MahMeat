package blue.heldplayer.mods.mahmeat.item;

import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMeatFood extends ItemFood {

    private ItemMeatFood.SubFood[] subFoods = new ItemMeatFood.SubFood[0];

    public ItemMeatFood() {
        super(0, 0.0F, true);
        this.setHasSubtypes(true);
    }

    public void registerFood(int meta, ItemMeatFood.SubFood food) {
        if (meta >= this.subFoods.length) {
            ItemMeatFood.SubFood[] old = this.subFoods;
            this.subFoods = new ItemMeatFood.SubFood[old.length + 1];
            System.arraycopy(old, 0, this.subFoods, 0, old.length);
        }
        this.subFoods[meta] = food;
    }

    public void registerFood(int meta, String name, int healAmount, float saturationModifier) {
        this.registerFood(meta, new ItemMeatFood.SubFood(name, healAmount, saturationModifier));
    }

    public ItemMeatFood.SubFood getFood(int meta) {
        if (meta < this.subFoods.length) {
            return this.subFoods[meta];
        } else {
            return null;
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(@Nonnull Item item, CreativeTabs tab, NonNullList<ItemStack> items) {
        for (int i = 0; i < this.subFoods.length; i++) {
            if (this.subFoods[i] != null) {
                items.add(new ItemStack(item, 1, i));
            }
        }
    }

    @Nonnull
    @Override
    public String getUnlocalizedName(ItemStack stack) {
        ItemMeatFood.SubFood food = this.getFood(stack.getItemDamage());
        return this.getUnlocalizedName() + "." + (food == null ? "null" : food.getName());
    }

    @Nonnull
    @Override
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        return I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name").trim();
    }

    @Override
    public int getHealAmount(ItemStack stack) {
        ItemMeatFood.SubFood food = this.getFood(stack.getItemDamage());
        return food == null ? 0 : food.getHealAmount();
    }

    @Override
    public float getSaturationModifier(ItemStack stack) {
        ItemMeatFood.SubFood food = this.getFood(stack.getItemDamage());
        return food == null ? 0.0F : food.getSaturationModifier();
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (stack == ItemStack.EMPTY) {
            return new ActionResult<>(EnumActionResult.PASS, stack);
        }
        ItemMeatFood.SubFood food = this.getFood(stack.getItemDamage());
        if (food != null) {
            if (player.canEat(food.alwaysEdible)) {
                player.setActiveHand(hand);
                return new ActionResult<>(EnumActionResult.SUCCESS, stack);
            }
        }
        return new ActionResult<>(EnumActionResult.PASS, stack);
    }

    public static class SubFood {

        protected String name;
        protected int healAmount;
        protected float saturationModifier;
        protected boolean alwaysEdible;

        public SubFood(String name, int healAmount, float saturationModifier) {
            this.name = name;
            this.healAmount = healAmount;
            this.saturationModifier = saturationModifier;
        }

        public String getName() {
            return this.name;
        }

        public float getSaturationModifier() {
            return this.saturationModifier;
        }

        public int getHealAmount() {
            return this.healAmount;
        }

        public void setAlwaysEdible() {
            this.alwaysEdible = true;
        }
    }
}
