package com.atomuze.torchrism.block;

import com.atomuze.torchrism.Torchrism;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemSlab;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks {
	
	public static final BlockTorchPlacer torchPlacer = new BlockTorchPlacer("torch_placer").setCreativeTab(Torchrism.creativeTab);
	public static final BlockCompactedTorch compactedTorch = new BlockCompactedTorch("compacted_torch").setCreativeTab(Torchrism.creativeTab);
	public static final BlockDoubleCompactedTorch doublecompactedTorch = new BlockDoubleCompactedTorch("double_compacted_torch").setCreativeTab(Torchrism.creativeTab);
	public static final BlockWaterTorch waterTorch = new BlockWaterTorch("water_torch");
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
	public static final BlockPoweredStone poweredStone = new BlockPoweredStone("powered_stone");
	public static final BlockPowerInfusedStone powerInfusedStone = new BlockPowerInfusedStone("power_infused_stone").setCreativeTab(Torchrism.creativeTab);
	public static final BlockunPoweredStone unPoweredStone = new BlockunPoweredStone("un_powered_stone").setCreativeTab(Torchrism.creativeTab);
	public static final BlockPoweredStoneSlab poweredStoneSlabHalf = new BlockHalfPoweredStoneSlab("powered_stone_slab_half");
	public static final BlockPoweredStoneSlab poweredStoneSlabDouble = new BlockDoublePoweredStoneSlab("powered_stone_slab_double");
	public static final BlockStairBase poweredStoneStair = new BlockStairBase(new Block(Material.ROCK).setHardness(3f).setResistance(4f).getDefaultState(), "powered_stone_stairs");

	public static final ItemBlock poweredStoneItem = new ItemMultiTexture(ModBlocks.poweredStone, ModBlocks.poweredStone, new String[] {"default", "chiseled"});
	public static final ItemBlock poweredStoneSlab = new ItemSlab(ModBlocks.poweredStoneSlabHalf, ModBlocks.poweredStoneSlabHalf, ModBlocks.poweredStoneSlabDouble);

	public static void register(IForgeRegistry<Block> registry) {
		registry.registerAll(
				torchPlacer,
				compactedTorch,
				doublecompactedTorch,
				waterTorch,
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
				poweredStoneSlabDouble,
				poweredStoneStair
		);
		
		GameRegistry.registerTileEntity(torchCorrecter.getTileEntityClass(), torchCorrecter.getRegistryName().toString());
		GameRegistry.registerTileEntity(altarMainPedestal.getTileEntityClass(), altarMainPedestal.getRegistryName().toString());
		GameRegistry.registerTileEntity(altarInsidePedestal.getTileEntityClass(), altarInsidePedestal.getRegistryName().toString());
		GameRegistry.registerTileEntity(sunMoonBlock.getTileEntityClass(), sunMoonBlock.getRegistryName().toString());
		GameRegistry.registerTileEntity(greatWallBuilder.getTileEntityClass(), greatWallBuilder.getRegistryName().toString());
	}
	
	public static void registerItemBlocks(IForgeRegistry<Item> registry) {
		
		poweredStoneSlab.setRegistryName(ModBlocks.poweredStoneSlabHalf.getRegistryName());
		poweredStoneItem.setRegistryName(ModBlocks.poweredStone.getRegistryName());
		
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
				poweredStoneItem,
				unPoweredStone.createItemBlock(),
				poweredStoneSlab,
				poweredStoneStair.createItemBlock()
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
		poweredStone.registerItemModel(Item.getItemFromBlock(poweredStone), 0);
		poweredStone.registerItemModel(Item.getItemFromBlock(poweredStone), 1);
		unPoweredStone.registerItemModel(Item.getItemFromBlock(unPoweredStone));
		poweredStoneSlabHalf.registerItemModel(Item.getItemFromBlock(poweredStoneSlabHalf));
		poweredStoneStair.registerItemModel(Item.getItemFromBlock(poweredStoneStair));
	}
}