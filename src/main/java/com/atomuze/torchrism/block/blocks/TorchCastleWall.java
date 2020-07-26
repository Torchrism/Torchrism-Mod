package com.atomuze.torchrism.block.blocks;

import com.atomuze.torchrism.block.BlockBase;
import com.atomuze.torchrism.block.torch.CompactedTorch;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class TorchCastleWall extends BlockBase {
	public TorchCastleWall(String name) {
		super(Material.ROCK, name);
		
		setHardness(3f);
		setResistance(4f);
		
	}

	@Override
	public TorchCastleWall setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
}
