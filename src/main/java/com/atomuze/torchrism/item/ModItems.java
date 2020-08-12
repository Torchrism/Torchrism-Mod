package com.atomuze.torchrism.item;

import com.atomuze.torchrism.TorchrismMod;
import com.atomuze.torchrism.block.torch_placer.BlockTorchPlacer;
import com.atomuze.torchrism.item.torchonomicon.ItemTorchonomicon;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {
	
	public static ItemBase torchwand = (ItemBase) new ItemBase("torch_wand").setMaxStackSize(1).setCreativeTab(TorchrismMod.creativeTab);
	public static ItemTorchonomicon torchonomicon = (ItemTorchonomicon) new ItemTorchonomicon("torchonomicon").setMaxStackSize(1).setCreativeTab(TorchrismMod.creativeTab);
	
	public static void register(IForgeRegistry<Item> registry) {
		registry.registerAll(
				torchwand,
				torchonomicon
		);
	}
	
	public static void registerModels() {
		torchwand.registerItemModel();
		torchonomicon.registerItemModel();
	}
	
}
