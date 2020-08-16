package com.atomuze.torchrism.block;

import com.atomuze.torchrism.Torchrism;
import com.atomuze.torchrism.block.modblocks.BlockIlluminate;
import com.atomuze.torchrism.block.modblocks.BlockWall;
import com.atomuze.torchrism.block.modblocks.BlockWallLight;
import com.atomuze.torchrism.block.torch.BlockCompactedTorch;
import com.atomuze.torchrism.block.torch.BlockDoubleCompactedTorch;
import com.atomuze.torchrism.block.torch_altar.block.BlockAltarPillerBase;
import com.atomuze.torchrism.block.torch_altar.block.BlockAltarInsidePedestal;
import com.atomuze.torchrism.block.torch_altar.block.BlockAltarPillar;
import com.atomuze.torchrism.block.torch_altar.block.BlockAltarOutsidePedestal;
import com.atomuze.torchrism.block.torch_altar.block.BlockAltarMainPedestal;
import com.atomuze.torchrism.block.torch_altar.block.BlockSunMoon;
import com.atomuze.torchrism.block.torch_castle.TileEntityGreatWallBuilder;
import com.atomuze.torchrism.block.torch_correcter.BlockTorchCorrector;
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
	public static BlockTorchCorrector torchCorrecter = new BlockTorchCorrector("torch_correcter").setCreativeTab(Torchrism.creativeTab);
	
	public static BlockAltarMainPedestal altarMainPedestal = new BlockAltarMainPedestal("altar_main_pedestal").setCreativeTab(Torchrism.creativeTab);
	public static BlockAltarMainPedestal altarMainPedestal_night = new BlockAltarMainPedestal("altar_main_pedestal_night").setCreativeTab(Torchrism.creativeTab);
	public static BlockAltarInsidePedestal altarInsidePedestal = new BlockAltarInsidePedestal("altar_inside_pedestal").setCreativeTab(Torchrism.creativeTab);
	public static BlockAltarOutsidePedestal altarOutsidePedestal = new BlockAltarOutsidePedestal("altar_outside_pedestal").setCreativeTab(Torchrism.creativeTab);
	public static BlockAltarPillar altarPillar = new BlockAltarPillar("altar_pillar").setCreativeTab(Torchrism.creativeTab);
	public static BlockAltarPillerBase altarPillerBase = new BlockAltarPillerBase("altar_piller_base").setCreativeTab(Torchrism.creativeTab);
	public static BlockSunMoon sunMoonBlock = new BlockSunMoon("sun_moon_block").setCreativeTab(Torchrism.creativeTab);
	public static BlockSunMoon sunMoonBlock_night = new BlockSunMoon("sun_moon_block_moon").setCreativeTab(Torchrism.creativeTab);
	
	public static BlockGreatWallBuilder greatWallBuilder = new BlockGreatWallBuilder("great_wall_builder").setCreativeTab(Torchrism.creativeTab);
	public static BlockIlluminate illuminateBlock = new BlockIlluminate("illuminate_block").setCreativeTab(Torchrism.creativeTab);
	public static BlockWall wall = new BlockWall("wall").setCreativeTab(Torchrism.creativeTab);
	public static BlockWallLight wallLight = new BlockWallLight("illuminate_block_fake").setCreativeTab(Torchrism.creativeTab);
	
	public static void register(IForgeRegistry<Block> registry) {
		registry.registerAll(
				torchPlacer,
				compactedTorch,
				doublecompactedTorch,
				torchDice,
				torchCorrecter,
				
				altarMainPedestal,
				altarMainPedestal_night,
				altarInsidePedestal,
				altarOutsidePedestal,
				altarPillar,
				altarPillerBase,
				sunMoonBlock,
				sunMoonBlock_night,
				
				greatWallBuilder,
				illuminateBlock,
				wallLight,
				wall
				
		);
		GameRegistry.registerTileEntity(torchCorrecter.getTileEntityClass(), torchCorrecter.getRegistryName().toString());
		GameRegistry.registerTileEntity(altarMainPedestal.getTileEntityClass(), altarMainPedestal.getRegistryName().toString());
		GameRegistry.registerTileEntity(altarInsidePedestal.getTileEntityClass(), altarInsidePedestal.getRegistryName().toString());
		GameRegistry.registerTileEntity(sunMoonBlock.getTileEntityClass(), sunMoonBlock.getRegistryName().toString());
		GameRegistry.registerTileEntity(greatWallBuilder.getTileEntityClass(), greatWallBuilder.getRegistryName().toString());
	}
	
	public static void registerItemBlocks(IForgeRegistry<Item> registry) {
		registry.registerAll(
				torchPlacer.createItemBlock(),
				compactedTorch.createItemBlock(),
				doublecompactedTorch.createItemBlock(),
				torchDice.createItemBlock(),
				torchCorrecter.createItemBlock(),
				
				altarMainPedestal.createItemBlock(),
				altarInsidePedestal.createItemBlock(),
				altarOutsidePedestal.createItemBlock(),
				altarPillar.createItemBlock(),
				altarPillerBase.createItemBlock(),
				sunMoonBlock.createItemBlock(),
				
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
		torchCorrecter.registerItemModel(Item.getItemFromBlock(torchCorrecter));
		
		altarMainPedestal.registerItemModel(Item.getItemFromBlock(altarMainPedestal));
		altarInsidePedestal.registerItemModel(Item.getItemFromBlock(altarInsidePedestal));
		altarOutsidePedestal.registerItemModel(Item.getItemFromBlock(altarOutsidePedestal));
		altarPillar.registerItemModel(Item.getItemFromBlock(altarPillar));
		altarPillerBase.registerItemModel(Item.getItemFromBlock(altarPillerBase));
		sunMoonBlock.registerItemModel(Item.getItemFromBlock(sunMoonBlock));
		
		greatWallBuilder.registerItemModel(Item.getItemFromBlock(greatWallBuilder));
		illuminateBlock.registerItemModel(Item.getItemFromBlock(illuminateBlock));
		wall.registerItemModel(Item.getItemFromBlock(wall));
		
	}
}