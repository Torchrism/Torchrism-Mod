package com.atomuze.torchrism.block.torch_altar;

import com.atomuze.torchrism.block.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class CheckAltar {
	public static boolean checkingAltar(World world, BlockPos pos, EntityPlayer player) {
		if (check(world, pos, pos.down().down().north().north().north().north().north().up(),ModBlocks.altarOutsidePedestal, player) 
				&& check(world, pos, pos.down().down().west().west().west().west().west().up(), ModBlocks.altarOutsidePedestal, player) 
				&& check(world, pos, pos.down().down().east().east().east().east().east().up(), ModBlocks.altarOutsidePedestal, player) 
				&& check(world, pos, pos.down().down().south().south().south().south().south().up(), ModBlocks.altarOutsidePedestal, player) 
				&& check(world, pos, pos.down().north().north().west().west().up().up(), ModBlocks.altarInsidePedestal, player) 
				&& check(world, pos, pos.down().north().north().east().east().up().up(), ModBlocks.altarInsidePedestal, player) 
				&& check(world, pos, pos.down().south().south().west().west().up().up(), ModBlocks.altarInsidePedestal, player) 
				&& check(world, pos, pos.down().south().south().east().east().up().up(), ModBlocks.altarInsidePedestal, player)
				&& check(world, pos, pos.north().north().north().north().east().east().east().east(), ModBlocks.altarOutsidePedestal, player) 
				&& check(world, pos, pos.north().north().north().north().west().west().west().west(), ModBlocks.altarOutsidePedestal, player) 
				&& check(world, pos, pos.south().south().south().south().east().east().east().east(), ModBlocks.altarOutsidePedestal, player) 
				&& check(world, pos, pos.south().south().south().south().west().west().west().west(), ModBlocks.altarOutsidePedestal, player)) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean check(World world, BlockPos initPos, BlockPos checkPos, Block toCheckBlock, EntityPlayer player) {
		if (!world.getBlockState(checkPos).getBlock().equals(toCheckBlock)) {
			System.out.println(world.getBlockState(checkPos).getBlock().getUnlocalizedName());
			System.out.println(toCheckBlock.getUnlocalizedName());
			player.sendMessage(new TextComponentString("Altar Invalid at x:" + (checkPos.getX() - initPos.getX()) + " y:" + (checkPos.getY() - initPos.getY()) + " z:" + (checkPos.getZ() - initPos.getZ())));
			return false;
		}

		return true;
	}
}

//check(world, pos, pos.down(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().north(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().north().west(), Blocks.QUARTZ_BLOCK,player)&&
//check(world, pos, pos.down().north().east(), Blocks.QUARTZ_BLOCK,player)&&
//check(world, pos, pos.down().east(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().west(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().south(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().south().west(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().south().east(), Blocks.QUARTZ_BLOCK, player)&&
//
//check(world, pos, pos.down().down().north().north(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().north().north().north(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().north().north().north().north(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().north().north().north().north().north(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().north().north().north().north().north().up(), ModBlocks.altarPurePedestal, player)&&
//
//check(world, pos, pos.down().down().west().west(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().west().west().west(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().west().west().west().west(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().west().west().west().west().west(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().west().west().west().west().west().up(), ModBlocks.altarPurePedestal, player)&&
//
//check(world, pos, pos.down().down().east().east(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().east().east().east(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().east().east().east().east(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().east().east().east().east().east(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().east().east().east().east().east().up(), ModBlocks.altarPurePedestal, player)&&
//
//check(world, pos, pos.down().down().south().south(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().south().south().south(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().south().south().south().south(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().south().south().south().south().south(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().south().south().south().south().south().up(), ModBlocks.altarPurePedestal, player)&&
//
//check(world, pos, pos.down().north().north().west().west(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().north().north().west().west().up(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().north().north().west().west().up().up(), ModBlocks.altarPedestal, player)&&
//
//check(world, pos, pos.down().north().north().east().east(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().north().north().east().east().up(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().north().north().east().east().up().up(), ModBlocks.altarPedestal, player)&&
//
//check(world, pos, pos.down().south().south().west().west(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().south().south().west().west().up(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().south().south().west().west().up().up(), ModBlocks.altarPedestal, player)&&
//
//check(world, pos, pos.down().south().south().east().east(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().south().south().east().east().up(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().south().south().east().east().up().up(), ModBlocks.altarPedestal, player)&&
//
//check(world, pos, pos.down().down().north().north().north().north().east().east().east(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().north().north().north().east().east().east().east(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().north().north().north().north().east().east().east().east(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().north().north().north().north().east().east().east().east(), ModBlocks.illuminateBlock, player)&&
//check(world, pos, pos.north().north().north().north().east().east().east().east(), ModBlocks.altarPurePedestal, player)&&
//
//check(world, pos, pos.down().down().north().north().north().north().west().west().west(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().north().north().north().west().west().west().west(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().north().north().north().north().west().west().west().west(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().north().north().north().north().west().west().west().west(), ModBlocks.illuminateBlock, player)&&
//check(world, pos, pos.north().north().north().north().west().west().west().west(), ModBlocks.altarPurePedestal, player)&&
//
//check(world, pos, pos.down().down().south().south().south().south().east().east().east(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().south().south().south().east().east().east().east(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().south().south().south().south().east().east().east().east(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().south().south().south().south().east().east().east().east(), ModBlocks.illuminateBlock, player)&&
//check(world, pos, pos.south().south().south().south().east().east().east().east(), ModBlocks.altarPurePedestal, player)&&
//
//check(world, pos, pos.down().down().south().south().south().south().west().west().west(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().south().south().south().west().west().west().west(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().down().south().south().south().south().west().west().west().west(), Blocks.QUARTZ_BLOCK, player)&&
//check(world, pos, pos.down().south().south().south().south().west().west().west().west(), ModBlocks.illuminateBlock, player)&&
//check(world, pos, pos.south().south().south().south().west().west().west().west(), ModBlocks.altarPurePedestal, player)
