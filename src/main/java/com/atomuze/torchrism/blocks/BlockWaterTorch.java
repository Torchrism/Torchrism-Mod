package com.atomuze.torchrism.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.TorchBlock;
import net.minecraft.block.material.Material;

public class BlockWaterTorch extends TorchBlock {
    public BlockWaterTorch() {
        super(Block.Properties.create(Material.WOOD)
                .hardnessAndResistance(0.0f, 0.0f)
                .lightValue(15));
    }
}
