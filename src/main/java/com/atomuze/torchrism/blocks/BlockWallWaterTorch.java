package com.atomuze.torchrism.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.WallTorchBlock;

public class BlockWallWaterTorch extends WallTorchBlock {

	protected BlockWallWaterTorch() {
		super(Block.Properties.from(ModBlocks.waterTorch.get()));
	}

}
