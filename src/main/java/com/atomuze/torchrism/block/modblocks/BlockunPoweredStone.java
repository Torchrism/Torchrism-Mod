package com.atomuze.torchrism.block.modblocks;

import java.util.Random;

import com.atomuze.torchrism.block.BlockBase;
import com.atomuze.torchrism.block.ModBlocks;
import com.atomuze.torchrism.item.ModItems;

import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockunPoweredStone extends BlockBase {
	public BlockunPoweredStone(String name) {
		super(Material.ROCK, name);

		setHardness(1.5F);
		setResistance(10.0F);
		setHarvestLevel("pickaxe", 2);
		setLightLevel(15f);

	}

	@Override
	public BlockunPoweredStone setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
	
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		if (playerIn.getHeldItem(hand).getItem() == new ItemStack(ModItems.torchwand).getItem()) {
			if(worldIn.getBlockState(pos.down()).getBlock() == Blocks.TORCH) {
				worldIn.setBlockState(pos, ModBlocks.powerInfusedStone.getDefaultState());
			}
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return true;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return true;
	}
}
