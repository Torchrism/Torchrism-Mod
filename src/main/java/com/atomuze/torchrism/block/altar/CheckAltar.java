package com.atomuze.torchrism.block.altar;

import com.atomuze.torchrism.block.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class CheckAltar {
	
	private World world;
	private BlockPos pos;
	private EntityPlayer player;
	
	public CheckAltar(World world, BlockPos pos, EntityPlayer player){
		this.world = world;
		this.pos = pos;
		this.player = player;
	}
	
	public CheckAltar(World world, BlockPos pos){
		this.world = world;
		this.pos = pos;
	}
	
	public boolean checkingAltar(World world, BlockPos pos) {
		long time = world.getWorldTime();
		if(time % 24000 < 12000) {
			if (
					//center
					check(world, pos, pos.down(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().north(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().north().west(), ModBlocks.cheiseledPoweredStone)&&
					check(world, pos, pos.down().north().east(), ModBlocks.cheiseledPoweredStone)&&
					check(world, pos, pos.down().east(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().west(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().south(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().south().west(), ModBlocks.cheiseledPoweredStone)&&
					check(world, pos, pos.down().south().east(), ModBlocks.cheiseledPoweredStone)&&
					
					//branch
					check(world, pos, pos.down().down().north().north(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().north().north().north(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().north().north().north().north(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().north().north().north().north().north(), ModBlocks.illuminateBlock)&&
					
					check(world, pos, pos.down().down().west().west(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().west().west().west(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().west().west().west().west(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().west().west().west().west().west(), ModBlocks.illuminateBlock)&&
					
					check(world, pos, pos.down().down().east().east(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().east().east().east(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().east().east().east().east(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().east().east().east().east().east(), ModBlocks.illuminateBlock)&&
					
					check(world, pos, pos.down().down().south().south(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().south().south().south(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().south().south().south().south(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().south().south().south().south().south(), ModBlocks.illuminateBlock)&&
					
					//pillar
					check(world, pos, pos.down().north().north().west().west(), ModBlocks.sunMoonBlock)&&
					check(world, pos, pos.north().north().west().west(), ModBlocks.altarPillerBase)&&
					
					check(world, pos, pos.down().north().north().east().east(), ModBlocks.sunMoonBlock)&&
					check(world, pos, pos.north().north().east().east(), ModBlocks.altarPillerBase)&&
					
					check(world, pos, pos.down().south().south().west().west(), ModBlocks.sunMoonBlock)&&
					check(world, pos, pos.south().south().west().west(), ModBlocks.altarPillerBase)&&
					
					check(world, pos, pos.down().south().south().east().east(), ModBlocks.sunMoonBlock)&&
					check(world, pos, pos.south().south().east().east(), ModBlocks.altarPillerBase)&&
					
					//out
					check(world, pos, pos.down().down().north().north().north().north().east().east().east(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().north().north().north().east().east().east().east(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().north().north().north().north().east().east().east().east(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().north().north().north().north().east().east().east().east(), ModBlocks.illuminateBlock)&&
					
					check(world, pos, pos.down().down().north().north().north().north().west().west().west(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().north().north().north().west().west().west().west(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().north().north().north().north().west().west().west().west(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().north().north().north().north().west().west().west().west(), ModBlocks.illuminateBlock)&&
					
					check(world, pos, pos.down().down().south().south().south().south().east().east().east(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().south().south().south().east().east().east().east(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().south().south().south().south().east().east().east().east(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().south().south().south().south().east().east().east().east(), ModBlocks.illuminateBlock)&&
					
					check(world, pos, pos.down().down().south().south().south().south().west().west().west(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().south().south().south().west().west().west().west(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().south().south().south().south().west().west().west().west(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().south().south().south().south().west().west().west().west(), ModBlocks.illuminateBlock)
					
					//altar
					&&check(world, pos, pos.down().down().north().north().north().north().north().up(),ModBlocks.altarOutsidePedestal) 
					&& check(world, pos, pos.down().down().west().west().west().west().west().up(), ModBlocks.altarOutsidePedestal) 
					&& check(world, pos, pos.down().down().east().east().east().east().east().up(), ModBlocks.altarOutsidePedestal) 
					&& check(world, pos, pos.down().down().south().south().south().south().south().up(), ModBlocks.altarOutsidePedestal) 
					&& check(world, pos, pos.down().north().north().west().west().up().up(), ModBlocks.altarInsidePedestal) 
					&& check(world, pos, pos.down().north().north().east().east().up().up(), ModBlocks.altarInsidePedestal) 
					&& check(world, pos, pos.down().south().south().west().west().up().up(), ModBlocks.altarInsidePedestal) 
					&& check(world, pos, pos.down().south().south().east().east().up().up(), ModBlocks.altarInsidePedestal)
					&& check(world, pos, pos.north().north().north().north().east().east().east().east(), ModBlocks.altarOutsidePedestal) 
					&& check(world, pos, pos.north().north().north().north().west().west().west().west(), ModBlocks.altarOutsidePedestal) 
					&& check(world, pos, pos.south().south().south().south().east().east().east().east(), ModBlocks.altarOutsidePedestal) 
					&& check(world, pos, pos.south().south().south().south().west().west().west().west(), ModBlocks.altarOutsidePedestal)) {
				return true;
			} else {
				return false;
			}
		}else {
			if (
					//center
					check(world, pos, pos.down(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().north(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().north().west(), ModBlocks.cheiseledPoweredStone)&&
					check(world, pos, pos.down().north().east(), ModBlocks.cheiseledPoweredStone)&&
					check(world, pos, pos.down().east(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().west(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().south(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().south().west(), ModBlocks.cheiseledPoweredStone)&&
					check(world, pos, pos.down().south().east(), ModBlocks.cheiseledPoweredStone)&&
					
					//branch
					check(world, pos, pos.down().down().north().north(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().north().north().north(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().north().north().north().north(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().north().north().north().north().north(), ModBlocks.illuminateBlock)&&
					
					check(world, pos, pos.down().down().west().west(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().west().west().west(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().west().west().west().west(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().west().west().west().west().west(), ModBlocks.illuminateBlock)&&
					
					check(world, pos, pos.down().down().east().east(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().east().east().east(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().east().east().east().east(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().east().east().east().east().east(), ModBlocks.illuminateBlock)&&
					
					check(world, pos, pos.down().down().south().south(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().south().south().south(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().south().south().south().south(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().south().south().south().south().south(), ModBlocks.illuminateBlock)&&
					
					//pillar
					check(world, pos, pos.down().north().north().west().west(), ModBlocks.sunMoonBlock_night)&&
					check(world, pos, pos.north().north().west().west(), ModBlocks.altarPillerBase)&&
					
					check(world, pos, pos.down().north().north().east().east(), ModBlocks.sunMoonBlock_night)&&
					check(world, pos, pos.north().north().east().east(), ModBlocks.altarPillerBase)&&
					
					check(world, pos, pos.down().south().south().west().west(), ModBlocks.sunMoonBlock_night)&&
					check(world, pos, pos.south().south().west().west(), ModBlocks.altarPillerBase)&&
					
					check(world, pos, pos.down().south().south().east().east(), ModBlocks.sunMoonBlock_night)&&
					check(world, pos, pos.south().south().east().east(), ModBlocks.altarPillerBase)&&
					
					//out
					check(world, pos, pos.down().down().north().north().north().north().east().east().east(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().north().north().north().east().east().east().east(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().north().north().north().north().east().east().east().east(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().north().north().north().north().east().east().east().east(), ModBlocks.illuminateBlock)&&
					
					check(world, pos, pos.down().down().north().north().north().north().west().west().west(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().north().north().north().west().west().west().west(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().north().north().north().north().west().west().west().west(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().north().north().north().north().west().west().west().west(), ModBlocks.illuminateBlock)&&
					
					check(world, pos, pos.down().down().south().south().south().south().east().east().east(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().south().south().south().east().east().east().east(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().south().south().south().south().east().east().east().east(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().south().south().south().south().east().east().east().east(), ModBlocks.illuminateBlock)&&
					
					check(world, pos, pos.down().down().south().south().south().south().west().west().west(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().south().south().south().west().west().west().west(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().down().south().south().south().south().west().west().west().west(), ModBlocks.poweredStone)&&
					check(world, pos, pos.down().south().south().south().south().west().west().west().west(), ModBlocks.illuminateBlock)
					
					//altar
					&&check(world, pos, pos.down().down().north().north().north().north().north().up(),ModBlocks.altarOutsidePedestal) 
					&& check(world, pos, pos.down().down().west().west().west().west().west().up(), ModBlocks.altarOutsidePedestal) 
					&& check(world, pos, pos.down().down().east().east().east().east().east().up(), ModBlocks.altarOutsidePedestal) 
					&& check(world, pos, pos.down().down().south().south().south().south().south().up(), ModBlocks.altarOutsidePedestal) 
					&& check(world, pos, pos.down().north().north().west().west().up().up(), ModBlocks.altarInsidePedestal) 
					&& check(world, pos, pos.down().north().north().east().east().up().up(), ModBlocks.altarInsidePedestal) 
					&& check(world, pos, pos.down().south().south().west().west().up().up(), ModBlocks.altarInsidePedestal) 
					&& check(world, pos, pos.down().south().south().east().east().up().up(), ModBlocks.altarInsidePedestal)
					&& check(world, pos, pos.north().north().north().north().east().east().east().east(), ModBlocks.altarOutsidePedestal) 
					&& check(world, pos, pos.north().north().north().north().west().west().west().west(), ModBlocks.altarOutsidePedestal) 
					&& check(world, pos, pos.south().south().south().south().east().east().east().east(), ModBlocks.altarOutsidePedestal) 
					&& check(world, pos, pos.south().south().south().south().west().west().west().west(), ModBlocks.altarOutsidePedestal)) {
				return true;
			} else {
				return false;
			}
		}
	}

	private boolean check(World world, BlockPos initPos, BlockPos checkPos, Block toCheckBlock) {
		if (!world.getBlockState(checkPos).getBlock().equals(toCheckBlock)) {
			if(player != null) {
				this.player.sendMessage(new TextComponentString("Altar Invalid at x:" + (checkPos.getX() - initPos.getX()) + " y:" + (checkPos.getY() - initPos.getY()) + " z:" + (checkPos.getZ() - initPos.getZ())));
				player = null;
			}
			return false;
		}
		return true;
	}
}