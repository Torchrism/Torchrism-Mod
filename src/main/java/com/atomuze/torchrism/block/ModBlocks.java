package com.atomuze.torchrism.block;

import com.atomuze.torchrism.TorchrismMod;
import com.atomuze.torchrism.block.modblocks.BlockIlluminate;
import com.atomuze.torchrism.block.modblocks.BlockWall;
import com.atomuze.torchrism.block.modblocks.BlockWallLight;
import com.atomuze.torchrism.block.torch.BlockCompactedTorch;
import com.atomuze.torchrism.block.torch.BlockDoubleCompactedTorch;
import com.atomuze.torchrism.block.torch_altar.BlockTorchAltar;
import com.atomuze.torchrism.block.torch_altar.BlockAltarBase;
import com.atomuze.torchrism.block.torch_altar.BlockAltarPedestal;
import com.atomuze.torchrism.block.torch_altar.BlockAltarPillar;
import com.atomuze.torchrism.block.torch_altar.BlockAltarPurePedestal;
import com.atomuze.torchrism.block.torch_castle.TileEntityGreatWallBuilder;
import com.atomuze.torchrism.block.torch_castle.BlockGreatWallBuilder;
import com.atomuze.torchrism.block.torch_dice.BlockTorchDice;
import com.atomuze.torchrism.block.torch_placer.BlockTorchPlacer;
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
	
	public static BlockTorchPlacer torchPlacer = new BlockTorchPlacer("torch_placer").setCreativeTab(TorchrismMod.creativeTab);
	public static BlockCompactedTorch compactedTorch = new BlockCompactedTorch("compacted_torch").setCreativeTab(TorchrismMod.creativeTab);
	public static BlockDoubleCompactedTorch doublecompactedTorch = new BlockDoubleCompactedTorch("double_compacted_torch").setCreativeTab(TorchrismMod.creativeTab);
	public static BlockTorchDice torchDice = new BlockTorchDice("torch_dice").setCreativeTab(TorchrismMod.creativeTab);
	
	public static BlockTorchAltar torchAltar = new BlockTorchAltar("torch_altar").setCreativeTab(TorchrismMod.creativeTab);
	public static BlockAltarPedestal altarPedestal = new BlockAltarPedestal("altar_pedestal").setCreativeTab(TorchrismMod.creativeTab);
	public static BlockAltarPurePedestal altarPurePedestal = new BlockAltarPurePedestal("altar_pure_pedestal").setCreativeTab(TorchrismMod.creativeTab);
	public static BlockAltarPillar pillar = new BlockAltarPillar("pillar").setCreativeTab(TorchrismMod.creativeTab);
	public static BlockAltarBase altarBase = new BlockAltarBase("altar_base").setCreativeTab(TorchrismMod.creativeTab);
	
	public static BlockGreatWallBuilder greatWallBuilder = new BlockGreatWallBuilder("great_wall_builder").setCreativeTab(TorchrismMod.creativeTab);
	public static BlockIlluminate illuminateBlock = new BlockIlluminate("illuminate_block").setCreativeTab(TorchrismMod.creativeTab);
	public static BlockWall wall = new BlockWall("wall").setCreativeTab(TorchrismMod.creativeTab);
	public static BlockWallLight wallLight = new BlockWallLight("fake_illuminate_block").setCreativeTab(TorchrismMod.creativeTab);
	
	
	
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
				wallLight,
				wall
				
		);
		GameRegistry.registerTileEntity(altarPedestal.getTileEntityClass(), altarPedestal.getRegistryName().toString());
		GameRegistry.registerTileEntity(greatWallBuilder.getTileEntityClass(), greatWallBuilder.getRegistryName().toString());
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
				wallLight.createItemBlock(),
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
		wallLight.registerItemModel(Item.getItemFromBlock(wallLight));
		wall.registerItemModel(Item.getItemFromBlock(wall));
		
	}
}