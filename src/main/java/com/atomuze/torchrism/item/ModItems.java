package com.atomuze.torchrism.item;

import com.atomuze.torchrism.Torchrism;
import com.atomuze.torchrism.block.torch_placer.BlockTorchPlacer;
import com.atomuze.torchrism.item.torchonomicon.ItemTorchonomicon;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {
	
	public static ItemBase torchwand = (ItemBase) new ItemBase("torch_wand").setMaxStackSize(1).setCreativeTab(Torchrism.creativeTab);
	public static ItemTorchonomicon torchonomicon = (ItemTorchonomicon) new ItemTorchonomicon("torchonomicon").setMaxStackSize(1).setCreativeTab(Torchrism.creativeTab);
	
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
	
	public static void registerRender(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Torchrism.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
	
}
