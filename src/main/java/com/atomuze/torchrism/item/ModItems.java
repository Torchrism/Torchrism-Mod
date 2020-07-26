package com.atomuze.torchrism.item;

import com.atomuze.torchrism.TorchrismMod;
import com.atomuze.torchrism.block.torch_placer.TorchPlacer;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {
	
	public static ItemBase torchwand = new ItemBase("torch_wand").setCreativeTab(TorchrismMod.creativeTab);
	
	public static void register(IForgeRegistry<Item> registry) {
		registry.registerAll(
				torchwand
		);
	}
	
	public static void registerModels() {
		torchwand.registerItemModel();
	}
	
}
