package com.atomuze.torchrism.block;

import com.atomuze.torchrism.Torchrism;
import com.atomuze.torchrism.block.modblocks.BlockIlluminate;
import com.atomuze.torchrism.block.modblocks.BlockPoweredStone;
import com.atomuze.torchrism.block.modblocks.BlockPowerInfusedStone;
import com.atomuze.torchrism.block.modblocks.BlockunPoweredStone;
import com.atomuze.torchrism.block.modblocks.BlockWall;
import com.atomuze.torchrism.block.modblocks.BlockWallLight;
import com.atomuze.torchrism.block.slab.BlockDoubleSlabBase;
import com.atomuze.torchrism.block.slab.BlockHalfSlabBase;
import com.atomuze.torchrism.block.slab.BlockSlabBase;
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
import net.minecraft.block.BlockDoubleStoneSlabNew;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStone;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks {
	
	public static final BlockTorchPlacer torchPlacer = new BlockTorchPlacer("torch_placer").setCreativeTab(Torchrism.creativeTab);
	public static final BlockCompactedTorch compactedTorch = new BlockCompactedTorch("compacted_torch").setCreativeTab(Torchrism.creativeTab);
	public static final BlockDoubleCompactedTorch doublecompactedTorch = new BlockDoubleCompactedTorch("double_compacted_torch").setCreativeTab(Torchrism.creativeTab);
	public static final BlockTorchDice torchDice = new BlockTorchDice("torch_dice").setCreativeTab(Torchrism.creativeTab);
	public static final BlockTorchCorrector torchCorrecter = new BlockTorchCorrector("torch_correcter").setCreativeTab(Torchrism.creativeTab);
	
	public static final BlockAltarMainPedestal altarMainPedestal = new BlockAltarMainPedestal("altar_main_pedestal").setCreativeTab(Torchrism.creativeTab);
	public static final BlockAltarMainPedestal altarMainPedestal_night = new BlockAltarMainPedestal("altar_main_pedestal_night").setCreativeTab(Torchrism.creativeTab);
	public static final BlockAltarInsidePedestal altarInsidePedestal = new BlockAltarInsidePedestal("altar_inside_pedestal").setCreativeTab(Torchrism.creativeTab);
	public static final BlockAltarOutsidePedestal altarOutsidePedestal = new BlockAltarOutsidePedestal("altar_outside_pedestal").setCreativeTab(Torchrism.creativeTab);
	public static final BlockAltarPillar altarPillar = new BlockAltarPillar("altar_pillar").setCreativeTab(Torchrism.creativeTab);
	public static final BlockAltarPillerBase altarPillerBase = new BlockAltarPillerBase("altar_piller_base").setCreativeTab(Torchrism.creativeTab);
	public static final BlockSunMoon sunMoonBlock = new BlockSunMoon("sun_moon_block").setCreativeTab(Torchrism.creativeTab);
	public static final BlockSunMoon sunMoonBlock_night = new BlockSunMoon("sun_moon_block_moon").setCreativeTab(Torchrism.creativeTab);
	
	public static final BlockGreatWallBuilder greatWallBuilder = new BlockGreatWallBuilder("great_wall_builder").setCreativeTab(Torchrism.creativeTab);
	public static final BlockIlluminate illuminateBlock = new BlockIlluminate("illuminate_block").setCreativeTab(Torchrism.creativeTab);
	public static final BlockWall wall = new BlockWall("wall").setCreativeTab(Torchrism.creativeTab);
	public static final BlockWallLight wallLight = new BlockWallLight("illuminate_block_fake").setCreativeTab(Torchrism.creativeTab);
	public static final BlockPoweredStone poweredStone = new BlockPoweredStone("powered_stone").setCreativeTab(Torchrism.creativeTab);
	public static final BlockPowerInfusedStone powerInfusedStone = new BlockPowerInfusedStone("power_infused_stone").setCreativeTab(Torchrism.creativeTab);
	public static final BlockunPoweredStone unPoweredStone = new BlockunPoweredStone("un_powered_stone").setCreativeTab(Torchrism.creativeTab);
	public static final BlockDoubleSlabBase poweredStoneSlabDouble = (BlockDoubleSlabBase) new BlockDoubleSlabBase("powered_stone_slab_double", Material.ROCK, Torchrism.creativeTab, ModBlocks.poweredStoneSlabHalf).setHardness(1.5F).setResistance(10.0F);
	public static final BlockHalfSlabBase poweredStoneSlabHalf = (BlockHalfSlabBase) new BlockHalfSlabBase("powered_stone_slab_half", Material.ROCK, Torchrism.creativeTab, ModBlocks.poweredStoneSlabHalf, ModBlocks.poweredStoneSlabDouble).setHardness(1.5F).setResistance(10.0F);

	public static final ItemBlock poweredStoneSlab = new ItemSlab(ModBlocks.poweredStoneSlabHalf, ModBlocks.poweredStoneSlabHalf, ModBlocks.poweredStoneSlabDouble);
	
	public static void register(IForgeRegistry<Block> registry) {
		registry.registerAll(
				torchPlacer,
				compactedTorch,
				doublecompactedTorch,
				torchDice,
				greatWallBuilder,
				torchCorrecter,
				
				altarMainPedestal,
				altarMainPedestal_night,
				altarInsidePedestal,
				altarOutsidePedestal,
				altarPillar,
				altarPillerBase,
				sunMoonBlock,
				sunMoonBlock_night,
					
				illuminateBlock,
				wallLight,
				wall,
				poweredStone,
				powerInfusedStone,
				unPoweredStone,
				poweredStoneSlabHalf,
				poweredStoneSlabDouble
				
		);
		
		
		GameRegistry.registerTileEntity(torchCorrecter.getTileEntityClass(), torchCorrecter.getRegistryName().toString());
		GameRegistry.registerTileEntity(altarMainPedestal.getTileEntityClass(), altarMainPedestal.getRegistryName().toString());
		GameRegistry.registerTileEntity(altarInsidePedestal.getTileEntityClass(), altarInsidePedestal.getRegistryName().toString());
		GameRegistry.registerTileEntity(sunMoonBlock.getTileEntityClass(), sunMoonBlock.getRegistryName().toString());
		GameRegistry.registerTileEntity(greatWallBuilder.getTileEntityClass(), greatWallBuilder.getRegistryName().toString());
	}
	
	public static void registerItemBlocks(IForgeRegistry<Item> registry) {
		
		poweredStoneSlab.setRegistryName(ModBlocks.poweredStoneSlabHalf.getRegistryName());
		System.out.println(ModBlocks.poweredStoneSlabHalf.getRegistryName());
		
		registry.registerAll(
				torchPlacer.createItemBlock(),
				compactedTorch.createItemBlock(),
				doublecompactedTorch.createItemBlock(),
				torchDice.createItemBlock(),
				greatWallBuilder.createItemBlock(),
				torchCorrecter.createItemBlock(),
				
				altarMainPedestal.createItemBlock(),
				altarInsidePedestal.createItemBlock(),
				altarOutsidePedestal.createItemBlock(),
				altarPillar.createItemBlock(),
				altarPillerBase.createItemBlock(),
				sunMoonBlock.createItemBlock(),
							
				illuminateBlock.createItemBlock(),
				wall.createItemBlock(),
				poweredStone.createItemBlock(),
				powerInfusedStone.createItemBlock(),
				unPoweredStone.createItemBlock(),
				poweredStoneSlab
		);
		
		
	}
	
	public static void registerModels() {
		torchPlacer.registerItemModel(Item.getItemFromBlock(torchPlacer));
		compactedTorch.registerItemModel(Item.getItemFromBlock(compactedTorch));
		doublecompactedTorch.registerItemModel(Item.getItemFromBlock(doublecompactedTorch));
		torchDice.registerItemModel(Item.getItemFromBlock(torchDice));
		greatWallBuilder.registerItemModel(Item.getItemFromBlock(greatWallBuilder));
		torchCorrecter.registerItemModel(Item.getItemFromBlock(torchCorrecter));
		
		altarMainPedestal.registerItemModel(Item.getItemFromBlock(altarMainPedestal));
		altarInsidePedestal.registerItemModel(Item.getItemFromBlock(altarInsidePedestal));
		altarOutsidePedestal.registerItemModel(Item.getItemFromBlock(altarOutsidePedestal));
		altarPillar.registerItemModel(Item.getItemFromBlock(altarPillar));
		altarPillerBase.registerItemModel(Item.getItemFromBlock(altarPillerBase));
		sunMoonBlock.registerItemModel(Item.getItemFromBlock(sunMoonBlock));
			
		illuminateBlock.registerItemModel(Item.getItemFromBlock(illuminateBlock));
		wall.registerItemModel(Item.getItemFromBlock(wall));
		poweredStone.registerItemModel(Item.getItemFromBlock(poweredStone));
		powerInfusedStone.registerItemModel(Item.getItemFromBlock(powerInfusedStone));
		unPoweredStone.registerItemModel(Item.getItemFromBlock(unPoweredStone));
		poweredStoneSlabHalf.registerItemModel(Item.getItemFromBlock(poweredStoneSlabHalf));
	}
}