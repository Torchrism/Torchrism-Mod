package com.atomuze.torchrism.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockPowerInfusedStone extends Block {

	Random rand = new Random();

    public BlockPowerInfusedStone() {
        super(Block.Properties.create(Material.ROCK)
                .hardnessAndResistance(3.0f, 4.0f)
                .sound(SoundType.WOOD)
                .harvestLevel(2)
                .harvestTool(ToolType.AXE)
                .lightValue(15));
    }
}