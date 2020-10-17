package net.atomuze.torchrism.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.TorchBlock;
import net.minecraft.particle.ParticleTypes;

public class BlockWaterTorch extends TorchBlock {
    public BlockWaterTorch(AbstractBlock.Settings settings) {
        super(settings, ParticleTypes.FLAME);
    }
}
