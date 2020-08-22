package com.atomuze.torchrism.item;

import com.atomuze.torchrism.Torchrism;
import com.atomuze.torchrism.item.staff.ItemStaff;
import com.atomuze.torchrism.item.torchonomicon.ItemTorchonomicon;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {
	
	public static final ItemStaff torchStaff = (ItemStaff) new ItemStaff(Torchrism.testMaterial, "torch_staff");
	public static final ItemTorchonomicon torchonomicon = (ItemTorchonomicon) new ItemTorchonomicon("torchonomicon");	
	
	public static void register(IForgeRegistry<Item> registry) {
		registry.registerAll(
				torchStaff,
				torchonomicon
		);
	}
	
	public static void registerModels() {
		torchonomicon.registerItemModel();
	}
	
	public static void registerRender(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Torchrism.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
	
}