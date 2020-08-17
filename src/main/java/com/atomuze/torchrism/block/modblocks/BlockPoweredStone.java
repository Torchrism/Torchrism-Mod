package com.atomuze.torchrism.block.modblocks;

import com.atomuze.torchrism.block.BlockBase;
import com.atomuze.torchrism.item.ModItems;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockPoweredStone extends BlockBase {
	public BlockPoweredStone(String name) {
		super(Material.ROCK, name);

		setHardness(1.5F);
		setResistance(10.0F);
		setHarvestLevel("pickaxe", 2);
	}

	@Override
	public BlockPoweredStone setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
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
