package com.atomuze.torchrism.block;

import com.atomuze.torchrism.TorchrismMod;
import com.atomuze.torchrism.block.blocks.IlluminateBlock;
import com.atomuze.torchrism.block.blocks.TorchCastleWall;
import com.atomuze.torchrism.block.torch.CompactedTorch;
import com.atomuze.torchrism.block.torch.DoubleCompactedTorch;
import com.atomuze.torchrism.block.torch_altar.altar.TorchAltar;
import com.atomuze.torchrism.block.torch_altar.pedestal.TorchPedestal;
import com.atomuze.torchrism.block.torch_altar.pedestal.TorchPurePedestal;
import com.atomuze.torchrism.block.torch_castle.TileEntityTorchCastle;
import com.atomuze.torchrism.block.torch_castle.TorchCastle;
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
	public static TorchDice torchDice = new TorchDice("torch_dice").setCreativeTab(TorchrismMod.creativeTab);
	public static CompactedTorch compactedTorch = new CompactedTorch("compacted_torch").setCreativeTab(TorchrismMod.creativeTab);
	public static DoubleCompactedTorch doublecompactedTorch = new DoubleCompactedTorch("double_compacted_torch").setCreativeTab(TorchrismMod.creativeTab);
	public static TorchAltar torchAltar = new TorchAltar("torch_altar").setCreativeTab(TorchrismMod.creativeTab);
	public static TorchPedestal torchPedestal = new TorchPedestal("torch_pedestal").setCreativeTab(TorchrismMod.creativeTab);
	public static TorchPurePedestal torchPurePedestal = new TorchPurePedestal("torch_pure_pedestal").setCreativeTab(TorchrismMod.creativeTab);
	public static TorchCastle torchCastle = new TorchCastle("torch_castle").setCreativeTab(TorchrismMod.creativeTab);
	public static IlluminateBlock illuminateBlock = new IlluminateBlock("illuminate_block").setCreativeTab(TorchrismMod.creativeTab);
	public static TorchCastleWall torchCastleWall = new TorchCastleWall("torch_castle_wall").setCreativeTab(TorchrismMod.creativeTab);
	
	public static void register(IForgeRegistry<Block> registry) {
		registry.registerAll(
				torchPlacer,
				torchDice,
				compactedTorch,
				doublecompactedTorch,
				torchAltar,
				torchPedestal,
				torchPurePedestal,
				torchCastle,
				illuminateBlock,
				torchCastleWall
		);
	}
	
	public static void registerItemBlocks(IForgeRegistry<Item> registry) {
		registry.registerAll(
				torchPlacer.createItemBlock(),
				torchDice.createItemBlock(),
				compactedTorch.createItemBlock(),
				doublecompactedTorch.createItemBlock(),
				torchAltar.createItemBlock(),
				torchPedestal.createItemBlock(),
				torchPurePedestal.createItemBlock(),
				torchCastle.createItemBlock(),
				illuminateBlock.createItemBlock(),
				torchCastleWall.createItemBlock()
		);
		
	}
	
	public static void registerModels() {
		torchPlacer.registerItemModel(Item.getItemFromBlock(torchPlacer));
		torchDice.registerItemModel(Item.getItemFromBlock(torchDice));
		compactedTorch.registerItemModel(Item.getItemFromBlock(compactedTorch));
		doublecompactedTorch.registerItemModel(Item.getItemFromBlock(doublecompactedTorch));
		torchAltar.registerItemModel(Item.getItemFromBlock(torchAltar));
		torchPedestal.registerItemModel(Item.getItemFromBlock(torchPedestal));
		torchPurePedestal.registerItemModel(Item.getItemFromBlock(torchPurePedestal));
		torchCastle.registerItemModel(Item.getItemFromBlock(torchCastle));
		illuminateBlock.registerItemModel(Item.getItemFromBlock(illuminateBlock));
		torchCastleWall.registerItemModel(Item.getItemFromBlock(torchCastleWall));
	}
}