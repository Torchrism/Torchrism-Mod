package com.atomuze.torchrism.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockPowerInfusedStone extends Block {

    public BlockPowerInfusedStone() {
        super(AbstractBlock.Properties.create(Material.ROCK)
                .hardnessAndResistance(3.0f, 4.0f)
                .harvestLevel(2)
                .harvestTool(ToolType.PICKAXE)
                .func_235838_a_((blockState) -> 15));
    }
}
