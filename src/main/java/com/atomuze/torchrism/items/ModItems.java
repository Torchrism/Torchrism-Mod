package com.atomuze.torchrism.items;

import com.atomuze.torchrism.TorchrismMod;

import com.atomuze.torchrism.blocks.ModBlocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, TorchrismMod.MODID);

	// Items

	// Block Items
	public static final RegistryObject<Item> torchPlacer_Item = ITEMS.register("torch_placer", () -> new BlockItem(ModBlocks.torchPlacer.get(), new Item.Properties().group(TorchrismMod.TAB)));
	public static final RegistryObject<Item> compactedTorch_Item = ITEMS.register("compacted_torch", () -> new BlockItem(ModBlocks.compactedTorch.get(), new Item.Properties().group(TorchrismMod.TAB)));
	public static final RegistryObject<Item> doublecompactedTorch_Item = ITEMS.register("double_compacted_torch", () -> new BlockItem(ModBlocks.doubleCompactedTorch.get(), new Item.Properties().group(TorchrismMod.TAB)));

}
