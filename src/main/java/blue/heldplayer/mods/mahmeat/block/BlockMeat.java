package blue.heldplayer.mods.mahmeat.block;

import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMeat extends BlockRotatedPillar {

    public static final PropertyEnum<BlockLog.EnumAxis> MEAT_AXIS = PropertyEnum.create("axis", BlockLog.EnumAxis.class);
    public static final PropertyBool COOKED = PropertyBool.create("cooked");

    public static final SoundType MEAT_SOUND = new SoundType(0.7F, 0.5F, SoundEvents.BLOCK_SLIME_BREAK, SoundEvents.BLOCK_SLIME_STEP, SoundEvents.BLOCK_SLIME_PLACE, SoundEvents.BLOCK_SLIME_HIT, SoundEvents.BLOCK_SLIME_FALL);

    public BlockMeat() {
        super(Material.CLAY, MapColor.BROWN);
        this.setSoundType(BlockMeat.MEAT_SOUND);
        this.setDefaultState(this.blockState.getBaseState().withProperty(BlockMeat.MEAT_AXIS, BlockLog.EnumAxis.Y).withProperty(BlockMeat.COOKED, false));
        this.setCreativeTab(CreativeTabs.DECORATIONS);
        this.setHardness(0.8F);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(@Nonnull Item item, CreativeTabs tab, List<ItemStack> items) {
        items.add(new ItemStack(item, 1, 0));
        items.add(new ItemStack(item, 1, 1));
    }

    @Nonnull
    @Override
    public IBlockState getStateFromMeta(int meta) {
        BlockLog.EnumAxis axis;
        boolean cooked = (meta & 1) == 1; // 0b0001
        int rotation = meta & 12; // 0b1100

        switch (rotation) {
            case 0:
                axis = BlockLog.EnumAxis.Y;
                break;
            case 4:
                axis = BlockLog.EnumAxis.X;
                break;
            case 8:
                axis = BlockLog.EnumAxis.Z;
                break;
            default:
                axis = BlockLog.EnumAxis.NONE;
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
        BlockLog.EnumAxis axis = state.getValue(BlockMeat.MEAT_AXIS);

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

    @Nonnull
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, BlockMeat.COOKED, BlockMeat.MEAT_AXIS);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    protected ItemStack createStackedBlock(IBlockState state) {
        return new ItemStack(Item.getItemFromBlock(this), 1, 0);
    }

    @Nonnull
    @Override
    public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getStateFromMeta(meta).withProperty(BlockMeat.MEAT_AXIS, BlockLog.EnumAxis.fromFacingAxis(facing.getAxis())).withProperty(BlockMeat.COOKED, meta == 1);
    }

    @Override
    public int damageDropped(IBlockState state) {
        return state.getValue(BlockMeat.COOKED) ? 1 : 0;
    }
}
