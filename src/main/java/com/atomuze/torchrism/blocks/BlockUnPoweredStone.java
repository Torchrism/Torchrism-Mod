package com.atomuze.torchrism.blocks;

import com.atomuze.torchrism.items.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class BlockUnPoweredStone extends Block {
    public BlockUnPoweredStone() {
        super(Block.Properties.create(Material.ROCK)
                .hardnessAndResistance(3.0f, 4.0f)
                .sound(SoundType.STONE)
                .harvestLevel(2)
                .harvestTool(ToolType.PICKAXE));
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {

    	if (player.getHeldItem(handIn).getItem() == ModItems.torchStaff.get()) {
			if(world.getBlockState(pos.down()).getBlock() == Blocks.TORCH) {
				world.setBlockState(pos, ModBlocks.powerInfusedStone.get().getDefaultState());
			}
			return ActionResultType.SUCCESS;
		}
        return ActionResultType.FAIL;
    }
}
