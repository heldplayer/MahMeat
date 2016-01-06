package blue.heldplayer.mods.mahmeat.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BlockMeat extends BlockRotatedPillar {

    public static final Block.SoundType MEAT_SOUND = new Block.SoundType("slime", 0.7F, 0.5F) {
        @Override
        public String getBreakSound() {
            return "mob.slime.big";
        }

        @Override
        public String func_150496_b() {
            return "mob.slime.big";
        }

        @Override
        public String getStepResourcePath() {
            return "mob.slime.small";
        }
    };

    @SideOnly(Side.CLIENT)
    private IIcon[] topTextures = new IIcon[2];
    @SideOnly(Side.CLIENT)
    private IIcon[] sideTextures = new IIcon[2];

    public BlockMeat() {
        super(Material.clay);
        this.setStepSound(BlockMeat.MEAT_SOUND);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setHardness(0.8F);
    }

    @Override
    public void registerBlockIcons(IIconRegister textureMap) {
        // Uncooked variant
        this.sideTextures[0] = textureMap.registerIcon(this.textureName + "_uncooked");
        this.topTextures[0] = textureMap.registerIcon(this.textureName + "_uncooked_top");
        // Cooked variant
        this.sideTextures[1] = textureMap.registerIcon(this.textureName + "_cooked");
        this.topTextures[1] = textureMap.registerIcon(this.textureName + "_cooked_top");
    }

    @Override
    public MapColor getMapColor(int meta) {
        return MapColor.brownColor;
    }

    @SuppressWarnings("unchecked")
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List items) {
        items.add(new ItemStack(item, 1, 0));
        items.add(new ItemStack(item, 1, 1));
    }

    @Override
    protected IIcon getTopIcon(int meta) {
        return this.topTextures[meta & 1];
    }

    @Override
    protected IIcon getSideIcon(int meta) {
        return this.sideTextures[meta & 1];
    }
}