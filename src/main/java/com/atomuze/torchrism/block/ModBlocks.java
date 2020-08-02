package com.atomuze.torchrism.block;

import com.atomuze.torchrism.TorchrismMod;
import com.atomuze.torchrism.block.modblocks.IlluminateBlock;
import com.atomuze.torchrism.block.modblocks.Wall;
import com.atomuze.torchrism.block.torch.CompactedTorch;
import com.atomuze.torchrism.block.torch.DoubleCompactedTorch;
import com.atomuze.torchrism.block.torch_altar.TorchAltar;
import com.atomuze.torchrism.block.torch_altar.AltarBase;
import com.atomuze.torchrism.block.torch_altar.AltarPedestal;
import com.atomuze.torchrism.block.torch_altar.AltarPillar;
import com.atomuze.torchrism.block.torch_altar.AltarPurePedestal;
import com.atomuze.torchrism.block.torch_castle.TileEntityGreatWallBuilder;
import com.atomuze.torchrism.block.torch_castle.GreatWallBuilder;
import com.atomuze.torchrism.block.torch_dice.TorchDice;
import com.atomuze.torchrism.block.torch_placer.TorchPlacer;
import com.atomuze.torchrism.item.ItemBase;
import com.atomuze.torchrism.item.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks {
	
	public static TorchPlacer torchPlacer = new TorchPlacer("torch_placer").setCreativeTab(TorchrismMod.creativeTab);
	public static CompactedTorch compactedTorch = new CompactedTorch("compacted_torch").setCreativeTab(TorchrismMod.creativeTab);
	public static DoubleCompactedTorch doublecompactedTorch = new DoubleCompactedTorch("double_compacted_torch").setCreativeTab(TorchrismMod.creativeTab);
	public static TorchDice torchDice = new TorchDice("torch_dice").setCreativeTab(TorchrismMod.creativeTab);
	
	public static TorchAltar torchAltar = new TorchAltar("torch_altar").setCreativeTab(TorchrismMod.creativeTab);
	public static AltarPedestal altarPedestal = new AltarPedestal("altar_pedestal").setCreativeTab(TorchrismMod.creativeTab);
	public static AltarPurePedestal altarPurePedestal = new AltarPurePedestal("altar_pure_pedestal").setCreativeTab(TorchrismMod.creativeTab);
	public static AltarPillar pillar = new AltarPillar("pillar").setCreativeTab(TorchrismMod.creativeTab);
	public static AltarBase altarBase = new AltarBase("altar_base").setCreativeTab(TorchrismMod.creativeTab);
	
	public static GreatWallBuilder greatWallBuilder = new GreatWallBuilder("great_wall_builder").setCreativeTab(TorchrismMod.creativeTab);
	public static IlluminateBlock illuminateBlock = new IlluminateBlock("illuminate_block").setCreativeTab(TorchrismMod.creativeTab);
	public static Wall wall = new Wall("wall").setCreativeTab(TorchrismMod.creativeTab);
	
	
	
	public static void register(IForgeRegistry<Block> registry) {
		registry.registerAll(
				torchPlacer,
				compactedTorch,
				doublecompactedTorch,
				torchDice,
				
				torchAltar,
				altarPedestal,
				altarPurePedestal,
				pillar,
				altarBase,
				
				greatWallBuilder,
				illuminateBlock,
				wall
				
		);
		GameRegistry.registerTileEntity(altarPedestal.getTileEntityClass(), altarPedestal.getRegistryName().toString());
	}
	
	public static void registerItemBlocks(IForgeRegistry<Item> registry) {
		registry.registerAll(
				torchPlacer.createItemBlock(),
				compactedTorch.createItemBlock(),
				doublecompactedTorch.createItemBlock(),
				torchDice.createItemBlock(),
				
				torchAltar.createItemBlock(),
				altarPedestal.createItemBlock(),
				altarPurePedestal.createItemBlock(),
				pillar.createItemBlock(),
				altarBase.createItemBlock(),
				
				greatWallBuilder.createItemBlock(),
				illuminateBlock.createItemBlock(),
				wall.createItemBlock()
				
		);
		
	}
	
	public static void registerModels() {
		torchPlacer.registerItemModel(Item.getItemFromBlock(torchPlacer));
		compactedTorch.registerItemModel(Item.getItemFromBlock(compactedTorch));
		doublecompactedTorch.registerItemModel(Item.getItemFromBlock(doublecompactedTorch));
		torchDice.registerItemModel(Item.getItemFromBlock(torchDice));
		
		torchAltar.registerItemModel(Item.getItemFromBlock(torchAltar));
		altarPedestal.registerItemModel(Item.getItemFromBlock(altarPedestal));
		altarPurePedestal.registerItemModel(Item.getItemFromBlock(altarPurePedestal));
		pillar.registerItemModel(Item.getItemFromBlock(pillar));
		altarBase.registerItemModel(Item.getItemFromBlock(altarBase));
		
		greatWallBuilder.registerItemModel(Item.getItemFromBlock(greatWallBuilder));
		illuminateBlock.registerItemModel(Item.getItemFromBlock(illuminateBlock));
		wall.registerItemModel(Item.getItemFromBlock(wall));
		
	}
}