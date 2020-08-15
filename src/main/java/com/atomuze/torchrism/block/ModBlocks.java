package com.atomuze.torchrism.block;

import com.atomuze.torchrism.Torchrism;
import com.atomuze.torchrism.block.modblocks.BlockIlluminate;
import com.atomuze.torchrism.block.modblocks.BlockWall;
import com.atomuze.torchrism.block.modblocks.BlockWallLight;
import com.atomuze.torchrism.block.torch.BlockCompactedTorch;
import com.atomuze.torchrism.block.torch.BlockDoubleCompactedTorch;
import com.atomuze.torchrism.block.torch_altar.block.BlockAltarBase;
import com.atomuze.torchrism.block.torch_altar.block.BlockAltarPedestal;
import com.atomuze.torchrism.block.torch_altar.block.BlockAltarPillar;
import com.atomuze.torchrism.block.torch_altar.block.BlockAltarPurePedestal;
import com.atomuze.torchrism.block.torch_altar.block.BlockMainPedestal;
import com.atomuze.torchrism.block.torch_altar.block.BlockSunMoon;
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
	
	public static BlockTorchPlacer torchPlacer = new BlockTorchPlacer("torch_placer").setCreativeTab(Torchrism.creativeTab);
	public static BlockCompactedTorch compactedTorch = new BlockCompactedTorch("compacted_torch").setCreativeTab(Torchrism.creativeTab);
	public static BlockDoubleCompactedTorch doublecompactedTorch = new BlockDoubleCompactedTorch("double_compacted_torch").setCreativeTab(Torchrism.creativeTab);
	public static BlockTorchDice torchDice = new BlockTorchDice("torch_dice").setCreativeTab(Torchrism.creativeTab);
	
	public static BlockMainPedestal torchAltar = new BlockMainPedestal("torch_altar").setCreativeTab(Torchrism.creativeTab);
	public static BlockMainPedestal torchAltar_night = new BlockMainPedestal("torch_altar_night").setCreativeTab(Torchrism.creativeTab);
	public static BlockAltarPedestal altarPedestal = new BlockAltarPedestal("altar_pedestal").setCreativeTab(Torchrism.creativeTab);
	public static BlockAltarPurePedestal altarPurePedestal = new BlockAltarPurePedestal("altar_pure_pedestal").setCreativeTab(Torchrism.creativeTab);
	public static BlockAltarPillar pillar = new BlockAltarPillar("pillar").setCreativeTab(Torchrism.creativeTab);
	public static BlockAltarBase altarBase = new BlockAltarBase("altar_base").setCreativeTab(Torchrism.creativeTab);
	public static BlockSunMoon sunMoonBlock = new BlockSunMoon("sun_moon_block").setCreativeTab(Torchrism.creativeTab);
	public static BlockSunMoon sunMoonBlock_night = new BlockSunMoon("sun_moon_block_moon").setCreativeTab(Torchrism.creativeTab);
	
	public static BlockGreatWallBuilder greatWallBuilder = new BlockGreatWallBuilder("great_wall_builder").setCreativeTab(Torchrism.creativeTab);
	public static BlockIlluminate illuminateBlock = new BlockIlluminate("illuminate_block").setCreativeTab(Torchrism.creativeTab);
	public static BlockWall wall = new BlockWall("wall").setCreativeTab(Torchrism.creativeTab);
	public static BlockWallLight wallLight = new BlockWallLight("fake_illuminate_block").setCreativeTab(Torchrism.creativeTab);
	
	public static void register(IForgeRegistry<Block> registry) {
		registry.registerAll(
				torchPlacer,
				compactedTorch,
				doublecompactedTorch,
				torchDice,
				
				torchAltar,
				torchAltar_night,
				altarPedestal,
				altarPurePedestal,
				pillar,
				altarBase,
				sunMoonBlock,
				sunMoonBlock_night,
				
				greatWallBuilder,
				illuminateBlock,
				wallLight,
				wall
				
		);
		GameRegistry.registerTileEntity(torchAltar.getTileEntityClass(), torchAltar.getRegistryName().toString());
		GameRegistry.registerTileEntity(altarPedestal.getTileEntityClass(), altarPedestal.getRegistryName().toString());
		GameRegistry.registerTileEntity(sunMoonBlock.getTileEntityClass(), sunMoonBlock.getRegistryName().toString());
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
				sunMoonBlock.createItemBlock(),
				
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
		sunMoonBlock.registerItemModel(Item.getItemFromBlock(sunMoonBlock));
		
		greatWallBuilder.registerItemModel(Item.getItemFromBlock(greatWallBuilder));
		illuminateBlock.registerItemModel(Item.getItemFromBlock(illuminateBlock));
		wallLight.registerItemModel(Item.getItemFromBlock(wallLight));
		wall.registerItemModel(Item.getItemFromBlock(wall));
		
	}
}