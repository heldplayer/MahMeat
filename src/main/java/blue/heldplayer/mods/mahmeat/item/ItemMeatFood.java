package blue.heldplayer.mods.mahmeat.item;

import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
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
            this.subFoods = new ItemMeatFood.SubFood[old.length + 2]; // Increase with 2 as we'll usually do raw and cooked versions
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
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> items) {
        for (int i = 0; i < this.subFoods.length; i++) {
            if (this.subFoods[i] != null) {
                items.add(new ItemStack(item, 1, i));
            }
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        ItemMeatFood.SubFood food = this.getFood(stack.getItemDamage());
        return this.getUnlocalizedName() + "." + (food == null ? "null" : food.getName());
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name").trim();
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

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        ItemMeatFood.SubFood food = this.getFood(stack.getItemDamage());
        if (food != null) {
            if (player.canEat(food.alwaysEdible)) {
                player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
            }
        }
        return stack;
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
