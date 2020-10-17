package net.atomuze.torchrism.blocks.blockentity;

import net.atomuze.torchrism.TorchrismMod;
import net.atomuze.torchrism.blocks.ModBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

public class ModBlockEntity {
	public static BlockEntityType<BlockEntityGreatWallBuilder> GREAT_WALL_BLOCK_ENTITY;
	public static BlockEntityType<BlockEntityTorchCorrector> TORCH_CORRECTOR_BLOCK_ENTITY;

	public static void register() {
		GREAT_WALL_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, TorchrismMod.MOD_ID + "great_wall_builder", BlockEntityType.Builder.create(BlockEntityGreatWallBuilder::new, ModBlocks.GREAT_WALL_BUILDER).build(null));
		TORCH_CORRECTOR_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, TorchrismMod.MOD_ID + "torch_corrector", BlockEntityType.Builder.create(BlockEntityTorchCorrector::new, ModBlocks.TORCH_CORRECTOR).build(null));
	}
}
