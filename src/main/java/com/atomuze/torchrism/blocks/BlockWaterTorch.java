package com.atomuze.torchrism.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockWaterTorch extends Block {
    public BlockWaterTorch() {
        super(Block.Properties.create(Material.MISCELLANEOUS)
                .hardnessAndResistance(0.0f, 0.0f)
                .sound(SoundType.WOOD)
                .harvestLevel(2)
                .harvestTool(ToolType.AXE)
                .lightValue(15));
    }
}
