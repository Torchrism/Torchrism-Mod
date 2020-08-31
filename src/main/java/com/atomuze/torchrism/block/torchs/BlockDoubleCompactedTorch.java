package com.atomuze.torchrism.block.torchs;

import com.atomuze.torchrism.block.BlockBase;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockDoubleCompactedTorch extends BlockBase {
	
	public static final AxisAlignedBB DoubleCompactedTorchAABB = new AxisAlignedBB(0.3125, 0, 0.3125, 0.6875, 1.0, 0.6875);
	
	public BlockDoubleCompactedTorch(String name) {
		super(Material.WOOD, name);

		setHardness(1f);
		setResistance(5f);
		setLightLevel(1f);

	}

	@Override
	public BlockDoubleCompactedTorch setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return DoubleCompactedTorchAABB;
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
