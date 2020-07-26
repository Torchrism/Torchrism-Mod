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

public class CompactedTorch extends BlockBase{
	
	public static final AxisAlignedBB CompactedTorchAABB = new AxisAlignedBB(0.375, 0, 0.375, 0.625, 0.8125, 0.625);
	
	public CompactedTorch(String name) {
		super(Material.WOOD, name);

		setHardness(1f);
		setResistance(5f);
		setLightLevel(1f);

	}

	@Override
	public CompactedTorch setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return CompactedTorchAABB;
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
