package com.atomuze.torchrism.block.torch;

import com.atomuze.torchrism.block.BlockBase;
import com.atomuze.torchrism.block.BlockTileEntity;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class DoubleCompactedTorch extends BlockBase {
	
	public static final AxisAlignedBB DoubleCompactedTorchAABB = new AxisAlignedBB(0.3125, 0, 0.3125, 0.6875, 1.0, 0.6875);
	
	public DoubleCompactedTorch(String name) {
		super(Material.WOOD, name);

		setHardness(1f);
		setResistance(5f);
		setLightLevel(1f);

	}

	@Override
	public DoubleCompactedTorch setCreativeTab(CreativeTabs tab) {
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
