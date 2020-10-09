package com.atomuze.torchrism.tileentity;

import com.atomuze.torchrism.TorchrismMod;
import com.atomuze.torchrism.blocks.ModBlocks;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntity {
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, TorchrismMod.MODID);

	public static final RegistryObject<TileEntityType<TileEntityGreatWallBuilder>> greatWallBuilder = TILE_ENTITY_TYPES.register("great_wall_builder", () -> TileEntityType.Builder.create(TileEntityGreatWallBuilder::new, ModBlocks.greatWallBuilder.get()).build(null));
	public static final RegistryObject<TileEntityType<TileEntityTorchCorrector>> torchCorrector = TILE_ENTITY_TYPES.register("torch_corrector", () -> TileEntityType.Builder.create(TileEntityTorchCorrector::new, ModBlocks.torchCorrector.get()).build(null));

}
