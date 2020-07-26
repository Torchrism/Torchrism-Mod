package com.atomuze.torchrism.block.blocks;

import com.atomuze.torchrism.block.BlockBase;
import com.atomuze.torchrism.block.torch.CompactedTorch;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class IlluminateBlock extends BlockBase {
	public IlluminateBlock(String name) {
		super(Material.GLASS, name);

		setHardness(3f);
		setResistance(4f);
		setLightLevel(1f);

	}

	@Override
	public IlluminateBlock setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
}
