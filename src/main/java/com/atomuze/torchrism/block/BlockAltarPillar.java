package com.atomuze.torchrism.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;

public class BlockAltarPillar extends BlockBase {
	public BlockAltarPillar(String name) {
		super(Material.ROCK, name);

		setHardness(3f);
		setResistance(4f);

	}

	@Override
	public BlockAltarPillar setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
}
