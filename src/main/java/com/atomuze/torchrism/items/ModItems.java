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
	public static final RegistryObject<Item> torchDice_Item = ITEMS.register("torch_dice", () -> new BlockItem(ModBlocks.torchDice.get(), new Item.Properties().group(TorchrismMod.TAB)));
	public static final RegistryObject<Item> torchCorrecter_Item = ITEMS.register("torch_corrector", () -> new BlockItem(ModBlocks.torchCorrector.get(), new Item.Properties().group(TorchrismMod.TAB)));

	public static final RegistryObject<Item> pillarTop_Item = ITEMS.register("pillar_top", () -> new BlockItem(ModBlocks.pillarTop.get(), new Item.Properties().group(TorchrismMod.TAB)));
	public static final RegistryObject<Item> pillar_Item = ITEMS.register("pillar", () -> new BlockItem(ModBlocks.pillar.get(), new Item.Properties().group(TorchrismMod.TAB)));
	public static final RegistryObject<Item> pillarBottom_Item = ITEMS.register("pillar_bottom", () -> new BlockItem(ModBlocks.pillarBottom.get(), new Item.Properties().group(TorchrismMod.TAB)));
	public static final RegistryObject<Item> sunMoonBlock_Item = ITEMS.register("sun_moon_block", () -> new BlockItem(ModBlocks.sunMoonBlock.get(), new Item.Properties().group(TorchrismMod.TAB)));

	public static final RegistryObject<Item> greatWallBuilder_Item = ITEMS.register("great_wall_builder", () -> new BlockItem(ModBlocks.greatWallBuilder.get(), new Item.Properties().group(TorchrismMod.TAB)));
	public static final RegistryObject<Item> illuminateBlock_Item = ITEMS.register("wall_light", () -> new BlockItem(ModBlocks.illuminateBlock.get(), new Item.Properties().group(TorchrismMod.TAB)));
	public static final RegistryObject<Item> wall_Item = ITEMS.register("wall", () -> new BlockItem(ModBlocks.wall.get(), new Item.Properties().group(TorchrismMod.TAB)));
	public static final RegistryObject<Item> poweredStone_Item = ITEMS.register("powered_stone", () -> new BlockItem(ModBlocks.poweredStone.get(), new Item.Properties().group(TorchrismMod.TAB)));
	public static final RegistryObject<Item> chiseledPoweredStone_Item = ITEMS.register("chiseled_powered_stone", () -> new BlockItem(ModBlocks.chiseledPoweredStone.get(), new Item.Properties().group(TorchrismMod.TAB)));
	public static final RegistryObject<Item> unPoweredStone_Item = ITEMS.register("un_powered_stone", () -> new BlockItem(ModBlocks.unPoweredStone.get(), new Item.Properties().group(TorchrismMod.TAB)));
	public static final RegistryObject<Item> poweredStoneStair_Item = ITEMS.register("powered_stone_stairs", () -> new BlockItem(ModBlocks.poweredStoneStair.get(), new Item.Properties().group(TorchrismMod.TAB)));
	public static final RegistryObject<Item> poweredStoneSlab_Item = ITEMS.register("powered_stone_slab", () -> new BlockItem(ModBlocks.poweredStoneSlab.get(), new Item.Properties().group(TorchrismMod.TAB)));
	
	public static final RegistryObject<Item> torchStaff = ITEMS.register("torch_staff", ItemStaff ::new);
	public static final RegistryObject<Item> torchonomicon = ITEMS.register("torchonomicon", ItemTorchonomicon::new);
	
}
