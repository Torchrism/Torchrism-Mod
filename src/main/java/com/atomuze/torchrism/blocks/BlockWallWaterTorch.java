package com.atomuze.torchrism.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.particles.ParticleTypes;

public class BlockWallWaterTorch extends WallTorchBlock {

	protected BlockWallWaterTorch() {
		super(Block.Properties.from(ModBlocks.waterTorch.get())
				.func_235838_a_((blockState) -> 15), ParticleTypes.FLAME);
	}

}
