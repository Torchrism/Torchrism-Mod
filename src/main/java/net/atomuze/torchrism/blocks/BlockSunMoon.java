package net.atomuze.torchrism.blocks;

import net.atomuze.torchrism.blocks.blockentity.BlockEntityTorchCorrector;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class BlockSunMoon extends Block {

	public BlockSunMoon(AbstractBlock.Settings settings) {
		super(settings);
	}

	@Environment(EnvType.CLIENT)
	@Override
	public void randomDisplayTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if (worldIn.getTimeOfDay() % 24000 < 12000) {
			worldIn.setBlockState(pos, ModBlocks.SUN_MOON_BLOCK.getDefaultState(), 3);
		} else {
			worldIn.setBlockState(pos, ModBlocks.SUN_MOON_BLOCK_NIGHT.getDefaultState(), 3);
		}
	}

	@Environment(EnvType.CLIENT)
	public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
		return new ItemStack(ModBlocks.SUN_MOON_BLOCK);
	}
}