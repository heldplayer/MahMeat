package blue.heldplayer.mods.mahmeat.block;

import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
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

public class BlockWalrusMeat extends BlockRotatedPillar {

    public static final PropertyEnum<BlockWalrusMeat.Axis> MEAT_AXIS = PropertyEnum.create("axis", BlockWalrusMeat.Axis.class);

    public BlockWalrusMeat() {
        super(Material.cactus, MapColor.brownColor);
        this.setDefaultState(this.blockState.getBaseState().withProperty(BlockWalrusMeat.MEAT_AXIS, BlockWalrusMeat.Axis.Y));
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setUnlocalizedName("mahmeat.meat_walrus");
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        BlockWalrusMeat.Axis axis = BlockWalrusMeat.Axis.Y;
        int i = meta & 12;

        if (i == 4) {
            axis = BlockWalrusMeat.Axis.X;
        } else if (i == 8) {
            axis = BlockWalrusMeat.Axis.Z;
        }

        return this.getDefaultState().withProperty(BlockWalrusMeat.MEAT_AXIS, axis);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;
        BlockWalrusMeat.Axis axis = state.getValue(BlockWalrusMeat.MEAT_AXIS);

        if (axis == BlockWalrusMeat.Axis.X) {
            i |= 4;
        } else if (axis == BlockWalrusMeat.Axis.Z) {
            i |= 8;
        }

        return i;
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, BlockWalrusMeat.MEAT_AXIS);
    }

    @Override
    protected ItemStack createStackedBlock(IBlockState state) {
        return new ItemStack(Item.getItemFromBlock(this), 1, 0);
    }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(BlockWalrusMeat.MEAT_AXIS, BlockWalrusMeat.Axis.fromFacingAxis(facing.getAxis()));
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

        public static BlockWalrusMeat.Axis fromFacingAxis(EnumFacing.Axis axis) {
            switch (axis) {
                case X:
                    return BlockWalrusMeat.Axis.X;
                case Y:
                    return BlockWalrusMeat.Axis.Y;
                case Z:
                    return BlockWalrusMeat.Axis.Z;
                default:
                    return BlockWalrusMeat.Axis.NONE;
            }
        }

        @Override
        public String getName() {
            return this.name;
        }
    }
}
