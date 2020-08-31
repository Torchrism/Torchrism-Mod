package com.atomuze.torchrism.block.stair;

import com.atomuze.torchrism.Torchrism;
import com.atomuze.torchrism.block.BlockBase;
import com.atomuze.torchrism.block.modblocks.BlockPoweredStone;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockStairBase extends BlockStairs {
	protected String name;
	protected String modid = Torchrism.MODID;
	public BlockStairBase(IBlockState modelState, String name) {
		super(modelState);
		this.name = name;

		setUnlocalizedName(modid + "." + name);
		setRegistryName(name);
		setCreativeTab(Torchrism.creativeTab);
	}

	public void registerItemModel(Item itemBlock) {
		Torchrism.proxy.registerItemRenderer(itemBlock, 0, name);
	}

	public void registerModel(Item itemBlock) {
		Torchrism.proxy.registerModel(Item.getItemFromBlock(this), 0);
	}

	public Item createItemBlock() {
		return new ItemBlock(this).setRegistryName(getRegistryName());
	}
}
