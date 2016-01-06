package blue.heldplayer.mods.mahmeat.block;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMeat extends BlockRotatedPillar {

    public static final PropertyEnum<BlockMeat.Axis> MEAT_AXIS = PropertyEnum.create("axis", BlockMeat.Axis.class);
    public static final PropertyBool COOKED = PropertyBool.create("cooked");

    public static final Block.SoundType MEAT_SOUND = new Block.SoundType("slime", 0.7F, 0.5F) {
        @Override
        public String getBreakSound() {
            return "mob.slime.big";
        }

        @Override
        public String getPlaceSound() {
            return "mob.slime.big";
        }

        @Override
        public String getStepSound() {
            return "mob.slime.small";
        }
    };

    public BlockMeat() {
        super(Material.clay, MapColor.brownColor);
        this.setStepSound(BlockMeat.MEAT_SOUND);
        this.setDefaultState(this.blockState.getBaseState().withProperty(BlockMeat.MEAT_AXIS, BlockMeat.Axis.Y).withProperty(BlockMeat.COOKED, false));
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> items) {
        items.add(new ItemStack(item, 1, 0));
        items.add(new ItemStack(item, 1, 1));
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        BlockMeat.Axis axis;
        boolean cooked = (meta & 1) == 1; // 0b0001
        int i = meta & 12; // 0b1100

        switch (meta & 12) {
            case 0:
                axis = BlockMeat.Axis.Y;
                break;
            case 4:
                axis = BlockMeat.Axis.X;
                break;
            case 8:
                axis = BlockMeat.Axis.Z;
                break;
            default:
                axis = BlockMeat.Axis.NONE;
                break;
        }

        return this.getDefaultState().withProperty(BlockMeat.MEAT_AXIS, axis).withProperty(BlockMeat.COOKED, cooked);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;
        if (state.getValue(BlockMeat.COOKED)) {
            i |= 1;
        }
        BlockMeat.Axis axis = state.getValue(BlockMeat.MEAT_AXIS);

        switch (axis) {
            case X:
                i |= 4;
                break;
            case Z:
                i |= 8;
                break;
            case NONE:
                i |= 12;
                break;
        }
        return i;
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, BlockMeat.COOKED, BlockMeat.MEAT_AXIS);
    }

    @Override
    protected ItemStack createStackedBlock(IBlockState state) {
        return new ItemStack(Item.getItemFromBlock(this), 1, 0);
    }

    @Override
    public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(BlockMeat.MEAT_AXIS, BlockMeat.Axis.fromFacingAxis(facing.getAxis())).withProperty(BlockMeat.COOKED, meta == 1);
    }

    @Override
    public int damageDropped(IBlockState state) {
        return state.getValue(BlockMeat.COOKED) ? 1 : 0;
    }

    public enum Axis implements IStringSerializable {
        X("x"),
        Y("y"),
        Z("z"),
        NONE("none");

        private final String name;

        Axis(String name) {
            this.name = name;
        }

        public String toString() {
            return this.name;
        }

        public static BlockMeat.Axis fromFacingAxis(EnumFacing.Axis axis) {
            switch (axis) {
                case X:
                    return BlockMeat.Axis.X;
                case Y:
                    return BlockMeat.Axis.Y;
                case Z:
                    return BlockMeat.Axis.Z;
                default:
                    return BlockMeat.Axis.NONE;
            }
        }

        @Override
        public String getName() {
            return this.name;
        }
    }
}
