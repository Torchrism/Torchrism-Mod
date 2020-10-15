package com.atomuze.torchrism.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.TorchBlock;
import net.minecraft.block.material.Material;
import net.minecraft.particles.ParticleTypes;

public class BlockWaterTorch extends TorchBlock {
    public BlockWaterTorch() {
        super(AbstractBlock.Properties.create(Material.WOOD)
                .func_235838_a_((blockState) -> 15), ParticleTypes.FLAME);
    }
    
}
