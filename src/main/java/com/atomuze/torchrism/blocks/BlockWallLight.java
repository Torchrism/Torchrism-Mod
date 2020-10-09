package com.atomuze.torchrism.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

public class BlockWallLight extends Block {
	public BlockWallLight() {
		super(Block.Properties.create(Material.ROCK)
				.hardnessAndResistance(3.0f, 4.0f)
				.sound(SoundType.WOOD)
				.harvestLevel(2)
				.harvestTool(ToolType.AXE)
				.lightValue(15)
				.noDrops());
	}
	
	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(ModBlocks.illuminateBlock.get());
	}
//
//	@Override
//	public BlockWallLight setCreativeTab(CreativeTabs tab) {
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
//	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
//
//	}
//
//	@Override
//	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
//		return new ItemStack(ModBlocks.illuminateBlock);
//	}
}
