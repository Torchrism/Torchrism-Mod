package net.atomuze.torchrism.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.particle.ParticleTypes;

public class BlockWallWaterTorch extends WallTorchBlock {
	public BlockWallWaterTorch(AbstractBlock.Settings settings) {
		super(settings, ParticleTypes.FLAME);
	}
}
