package com.atomuze.torchrism.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

public class BlockSunMoon extends Block {

	public BlockSunMoon() {
		super(Block.Properties
				.create(Material.ROCK)
				.hardnessAndResistance(3.0f, 4.0f)
				.sound(SoundType.STONE)
				.harvestLevel(2)
				.harvestTool(ToolType.PICKAXE));
	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if (worldIn.getDayTime() % 24000 > 12000) {
			worldIn.setBlockState(pos, ModBlocks.sunMoonBlock_night.get().getDefaultState(), 3);
		} else {
			worldIn.setBlockState(pos, ModBlocks.sunMoonBlock.get().getDefaultState(), 3);
		}
	}

	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(ModBlocks.sunMoonBlock.get());
	}
}