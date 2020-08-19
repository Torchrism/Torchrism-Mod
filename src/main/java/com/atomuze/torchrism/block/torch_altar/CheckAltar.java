package com.atomuze.torchrism.block.torch_altar;

import com.atomuze.torchrism.block.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class CheckAltar {
	public static boolean checkingAltar(World world, BlockPos pos, EntityPlayer player) {
		long time = world.getWorldTime();
		if(time % 24000 < 12000) {
			if (
					//center
					check(world, pos, pos.down(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().north(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().north().west(), ModBlocks.cheiseledPoweredStone,player)&&
					check(world, pos, pos.down().north().east(), ModBlocks.cheiseledPoweredStone,player)&&
					check(world, pos, pos.down().east(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().west(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().south(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().south().west(), ModBlocks.cheiseledPoweredStone, player)&&
					check(world, pos, pos.down().south().east(), ModBlocks.cheiseledPoweredStone, player)&&
					
					//branch
					check(world, pos, pos.down().down().north().north(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().north().north().north(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().north().north().north().north(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().north().north().north().north().north(), ModBlocks.illuminateBlock, player)&&
					
					check(world, pos, pos.down().down().west().west(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().west().west().west(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().west().west().west().west(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().west().west().west().west().west(), ModBlocks.illuminateBlock, player)&&
					
					check(world, pos, pos.down().down().east().east(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().east().east().east(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().east().east().east().east(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().east().east().east().east().east(), ModBlocks.illuminateBlock, player)&&
					
					check(world, pos, pos.down().down().south().south(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().south().south().south(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().south().south().south().south(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().south().south().south().south().south(), ModBlocks.illuminateBlock, player)&&
					
					//pillar
					check(world, pos, pos.down().north().north().west().west(), ModBlocks.sunMoonBlock, player)&&
					check(world, pos, pos.north().north().west().west(), ModBlocks.altarPillerBase, player)&&
					
					check(world, pos, pos.down().north().north().east().east(), ModBlocks.sunMoonBlock, player)&&
					check(world, pos, pos.north().north().east().east(), ModBlocks.altarPillerBase, player)&&
					
					check(world, pos, pos.down().south().south().west().west(), ModBlocks.sunMoonBlock, player)&&
					check(world, pos, pos.south().south().west().west(), ModBlocks.altarPillerBase, player)&&
					
					check(world, pos, pos.down().south().south().east().east(), ModBlocks.sunMoonBlock, player)&&
					check(world, pos, pos.south().south().east().east(), ModBlocks.altarPillerBase, player)&&
					
					//out
					check(world, pos, pos.down().down().north().north().north().north().east().east().east(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().north().north().north().east().east().east().east(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().north().north().north().north().east().east().east().east(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().north().north().north().north().east().east().east().east(), ModBlocks.illuminateBlock, player)&&
					
					check(world, pos, pos.down().down().north().north().north().north().west().west().west(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().north().north().north().west().west().west().west(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().north().north().north().north().west().west().west().west(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().north().north().north().north().west().west().west().west(), ModBlocks.illuminateBlock, player)&&
					
					check(world, pos, pos.down().down().south().south().south().south().east().east().east(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().south().south().south().east().east().east().east(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().south().south().south().south().east().east().east().east(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().south().south().south().south().east().east().east().east(), ModBlocks.illuminateBlock, player)&&
					
					check(world, pos, pos.down().down().south().south().south().south().west().west().west(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().south().south().south().west().west().west().west(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().south().south().south().south().west().west().west().west(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().south().south().south().south().west().west().west().west(), ModBlocks.illuminateBlock, player)
					
					//altar
					&&check(world, pos, pos.down().down().north().north().north().north().north().up(),ModBlocks.altarOutsidePedestal, player) 
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
		}else {
			if (
					//center
					check(world, pos, pos.down(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().north(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().north().west(), ModBlocks.cheiseledPoweredStone,player)&&
					check(world, pos, pos.down().north().east(), ModBlocks.cheiseledPoweredStone,player)&&
					check(world, pos, pos.down().east(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().west(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().south(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().south().west(), ModBlocks.cheiseledPoweredStone, player)&&
					check(world, pos, pos.down().south().east(), ModBlocks.cheiseledPoweredStone, player)&&
					
					//branch
					check(world, pos, pos.down().down().north().north(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().north().north().north(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().north().north().north().north(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().north().north().north().north().north(), ModBlocks.illuminateBlock, player)&&
					
					check(world, pos, pos.down().down().west().west(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().west().west().west(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().west().west().west().west(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().west().west().west().west().west(), ModBlocks.illuminateBlock, player)&&
					
					check(world, pos, pos.down().down().east().east(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().east().east().east(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().east().east().east().east(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().east().east().east().east().east(), ModBlocks.illuminateBlock, player)&&
					
					check(world, pos, pos.down().down().south().south(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().south().south().south(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().south().south().south().south(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().south().south().south().south().south(), ModBlocks.illuminateBlock, player)&&
					
					//pillar
					check(world, pos, pos.down().north().north().west().west(), ModBlocks.sunMoonBlock_night, player)&&
					check(world, pos, pos.north().north().west().west(), ModBlocks.altarPillerBase, player)&&
					
					check(world, pos, pos.down().north().north().east().east(), ModBlocks.sunMoonBlock_night, player)&&
					check(world, pos, pos.north().north().east().east(), ModBlocks.altarPillerBase, player)&&
					
					check(world, pos, pos.down().south().south().west().west(), ModBlocks.sunMoonBlock_night, player)&&
					check(world, pos, pos.south().south().west().west(), ModBlocks.altarPillerBase, player)&&
					
					check(world, pos, pos.down().south().south().east().east(), ModBlocks.sunMoonBlock_night, player)&&
					check(world, pos, pos.south().south().east().east(), ModBlocks.altarPillerBase, player)&&
					
					//out
					check(world, pos, pos.down().down().north().north().north().north().east().east().east(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().north().north().north().east().east().east().east(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().north().north().north().north().east().east().east().east(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().north().north().north().north().east().east().east().east(), ModBlocks.illuminateBlock, player)&&
					
					check(world, pos, pos.down().down().north().north().north().north().west().west().west(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().north().north().north().west().west().west().west(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().north().north().north().north().west().west().west().west(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().north().north().north().north().west().west().west().west(), ModBlocks.illuminateBlock, player)&&
					
					check(world, pos, pos.down().down().south().south().south().south().east().east().east(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().south().south().south().east().east().east().east(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().south().south().south().south().east().east().east().east(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().south().south().south().south().east().east().east().east(), ModBlocks.illuminateBlock, player)&&
					
					check(world, pos, pos.down().down().south().south().south().south().west().west().west(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().south().south().south().west().west().west().west(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().down().south().south().south().south().west().west().west().west(), ModBlocks.poweredStone, player)&&
					check(world, pos, pos.down().south().south().south().south().west().west().west().west(), ModBlocks.illuminateBlock, player)
					
					//altar
					&&check(world, pos, pos.down().down().north().north().north().north().north().up(),ModBlocks.altarOutsidePedestal, player) 
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
