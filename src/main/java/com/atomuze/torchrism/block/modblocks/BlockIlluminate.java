package com.atomuze.torchrism.block.modblocks;

import com.atomuze.torchrism.block.BlockBase;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;

public class BlockIlluminate extends BlockBase {
	public BlockIlluminate(String name) {
		super(Material.ROCK, name);

		setHardness(3f);
		setResistance(4f);
		setHarvestLevel("pickaxe", 1);
		setLightLevel(1f);

	}

	@Override
	public BlockIlluminate setCreativeTab(CreativeTabs tab) {
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
