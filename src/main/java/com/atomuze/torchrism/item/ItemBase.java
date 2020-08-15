package com.atomuze.torchrism.item;

import com.atomuze.torchrism.Torchrism;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item {

	protected String name;
	protected String modid = Torchrism.MODID;

	public ItemBase(String name) {
		this.name = name;
		setUnlocalizedName(modid + "." + name);
		setRegistryName(name);
	}
	
	public void registerItemModel() {
		Torchrism.proxy.registerItemRenderer(this, 0, name);
	}
	
	@Override
	public ItemBase setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
}