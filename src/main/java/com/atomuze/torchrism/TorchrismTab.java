package com.atomuze.torchrism;

import com.atomuze.torchrism.item.ModItems;
import com.atomuze.torchrism.block.ModBlocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TorchrismTab extends CreativeTabs {
	public TorchrismTab() {
		super(Torchrism.MODID);
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModBlocks.torchPlacer);
	}
}