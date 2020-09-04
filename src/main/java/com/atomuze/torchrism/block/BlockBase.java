package com.atomuze.torchrism.block;

import com.atomuze.torchrism.Torchrism;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block {

	protected String name;
	protected String modid = Torchrism.MODID;

	public BlockBase(Material material, String name) {
		super(material);

		this.name = name;

		setUnlocalizedName(modid + "." + name);
		setRegistryName(name);

	}

	public void registerItemModel(Item itemBlock) {
		Torchrism.proxy.registerItemRenderer(itemBlock, 0, name);
	}

	public Item createItemBlock() {
		return new ItemBlock(this).setRegistryName(getRegistryName());
	}

	@Override
	public BlockBase setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

}
