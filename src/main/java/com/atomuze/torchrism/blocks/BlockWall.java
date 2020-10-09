package com.atomuze.torchrism.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraftforge.common.ToolType;

public class BlockWall extends Block {

    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    public BlockWall() {
        super(Block.Properties.create(Material.ROCK)
                .hardnessAndResistance(3.0f, 4.0f)
                .sound(SoundType.WOOD)
                .harvestLevel(2)
                .harvestTool(ToolType.AXE)
                .notSolid()
                .noDrops());
    }
//
//	@Override
//	public BlockWall setCreativeTab(CreativeTabs tab) {
//		super.setCreativeTab(tab);
//		return this;
//	}
//
//	@Override
//	public boolean isOpaqueCube(IBlockState state) {
//		return true;
//	}
//
//	@Override
//	public boolean isFullCube(IBlockState state) {
//		return true;
//	}
//
//	@Override
//	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
//		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
//	}

//
//	@Override
//	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune){
//	}

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
