package com.atomuze.torchrism.block.slab;

import com.atomuze.torchrism.Torchrism;
import com.atomuze.torchrism.block.ModBlocks;
import com.atomuze.torchrism.block.slab.BlockSlabBase.Variant;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockSlab.EnumBlockHalf;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockDoubleSlabBase extends BlockSlabBase {

	public BlockDoubleSlabBase(String name) {
		super(name, Material.ROCK);
		setCreativeTab(Torchrism.creativeTab);
		setHardness(1.5F);
		setResistance(10.0F);
	}

	@Override
	public boolean isDouble() {
		return true;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return this.getItem(world, pos, state);
	}
}