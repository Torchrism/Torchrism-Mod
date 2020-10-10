package com.atomuze.torchrism.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockUnPoweredStone extends Block {
    public BlockUnPoweredStone() {
        super(Block.Properties.create(Material.ROCK)
                .hardnessAndResistance(3.0f, 4.0f)
                .sound(SoundType.STONE)
                .harvestLevel(2)
                .harvestTool(ToolType.PICKAXE));
    }


//	@Override
//	public BlockunPoweredStone setCreativeTab(CreativeTabs tab) {
//		super.setCreativeTab(tab);
//		return this;
//	}
//
//	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
//
//		if (playerIn.getHeldItem(hand).getItem() == new ItemStack(ModItems.torchStaff).getItem()) {
//			if(worldIn.getBlockState(pos.down()).getBlock() == Blocks.TORCH) {
//				worldIn.setBlockState(pos, ModBlocks.powerInfusedStone.getDefaultState());
//			}
//			return true;
//		}
//		return false;
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
}
