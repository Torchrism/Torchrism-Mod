package com.atomuze.torchrism.block.slab;

import com.atomuze.torchrism.Torchrism;
import com.atomuze.torchrism.block.BlockBase;
import com.atomuze.torchrism.item.ModItems;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;

public class BlockHalfSlabBase extends BlockSlabBase
{
	public BlockHalfSlabBase(String name, Material material, CreativeTabs tab, BlockSlab half, BlockSlab doubleSlab) 
	{
		super(name, material, half);
		setCreativeTab(tab);
	}
	
	@Override
	public boolean isDouble() 
	{
		return false;
	}
	
}