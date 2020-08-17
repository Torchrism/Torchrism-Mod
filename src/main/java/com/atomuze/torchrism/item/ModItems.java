package com.atomuze.torchrism.item;

import com.atomuze.torchrism.Torchrism;
import com.atomuze.torchrism.block.ModBlocks;
import com.atomuze.torchrism.item.staff.Staff;
import com.atomuze.torchrism.item.torchonomicon.ItemTorchonomicon;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {
	
	public static final ItemBase torchwand = (ItemBase) new ItemBase("torch_wand").setMaxStackSize(1).setCreativeTab(Torchrism.creativeTab);
	public static final ItemTorchonomicon torchonomicon = (ItemTorchonomicon) new ItemTorchonomicon("torchonomicon").setMaxStackSize(1).setCreativeTab(Torchrism.creativeTab);
	//public static final ItemBlock poweredStoneSlab = new ItemSlab(ModBlocks.poweredStoneSlabHalf, ModBlocks.poweredStoneSlabHalf, ModBlocks.poweredStoneSlabDouble);
	
	public static void register(IForgeRegistry<Item> registry) {
	//	poweredStoneSlab.setRegistryName(ModBlocks.poweredStoneSlabHalf.getRegistryName());
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