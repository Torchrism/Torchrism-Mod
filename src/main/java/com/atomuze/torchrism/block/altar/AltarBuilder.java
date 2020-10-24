package com.atomuze.torchrism.block.altar;

import com.atomuze.torchrism.block.BlockAltarMainPedestal;
import com.atomuze.torchrism.block.ModBlocks;
import com.atomuze.torchrism.sound.ModSound;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AltarBuilder {
	public static void builder(World world, BlockPos pos, EntityPlayer player, int i){
		switch(i) {
		case 1:
//			world.playSound((double)pos.getX(), (double)pos.getY(),(double) pos.getZ(), SoundEvents.BLOCK_STONE_BREAK, SoundCategory.BLOCKS, 10f, 10f, false);
			setBlock(world, pos.down(), ModBlocks.poweredStone.getDefaultState(), player);
			setBlock(world, pos.down().north(), ModBlocks.poweredStone.getDefaultState(), player);
			setBlock(world, pos.down().north().west(), ModBlocks.poweredStone.getStateFromMeta(1), player);
			setBlock(world, pos.down().north().east(), ModBlocks.poweredStone.getStateFromMeta(1), player);
			setBlock(world, pos.down().east(), ModBlocks.poweredStone.getDefaultState(), player);
			setBlock(world, pos.down().west(), ModBlocks.poweredStone.getDefaultState(), player);
			setBlock(world, pos.down().south(), ModBlocks.poweredStone.getDefaultState(), player);
			setBlock(world, pos.down().south().west(), ModBlocks.poweredStone.getStateFromMeta(1), player);
			setBlock(world, pos.down().south().east(), ModBlocks.poweredStone.getStateFromMeta(1), player);
			break;
		case 2:
			//branch
//			player.playSound(SoundEvents.BLOCK_STONE_PLACE, 1f, 1.0f);
			setBlock(world, pos.down().down().north().north(), ModBlocks.poweredStone.getDefaultState(), player);
			setBlock(world, pos.down().down().north().north().north(), ModBlocks.poweredStone.getDefaultState(), player);
			setBlock(world, pos.down().down().north().north().north().north(), ModBlocks.poweredStone.getDefaultState(), player);
			setBlock(world, pos.down().down().north().north().north().north().north(), ModBlocks.illuminateBlock.getDefaultState(), player);
			break;
		case 3:
//			player.playSound(SoundEvents.BLOCK_STONE_PLACE, 1f, 1.0f);
			setBlock(world, pos.down().down().west().west(), ModBlocks.poweredStone.getDefaultState(), player);
			setBlock(world, pos.down().down().west().west().west(), ModBlocks.poweredStone.getDefaultState(), player);
			setBlock(world, pos.down().down().west().west().west().west(), ModBlocks.poweredStone.getDefaultState(), player);
			setBlock(world, pos.down().down().west().west().west().west().west(), ModBlocks.illuminateBlock.getDefaultState(), player);
			break;
		case 4:
//			player.playSound(SoundEvents.BLOCK_STONE_PLACE, 1f, 1.0f);
			setBlock(world, pos.down().down().east().east(), ModBlocks.poweredStone.getDefaultState(), player);
			setBlock(world, pos.down().down().east().east().east(), ModBlocks.poweredStone.getDefaultState(), player);
			setBlock(world, pos.down().down().east().east().east().east(), ModBlocks.poweredStone.getDefaultState(), player);
			setBlock(world, pos.down().down().east().east().east().east().east(), ModBlocks.illuminateBlock.getDefaultState(), player);
			break;
		case 5:
//			player.playSound(SoundEvents.BLOCK_STONE_PLACE, 1f, 1.0f);
			setBlock(world, pos.down().down().south().south(), ModBlocks.poweredStone.getDefaultState(), player);
			setBlock(world, pos.down().down().south().south().south(), ModBlocks.poweredStone.getDefaultState(), player);
			setBlock(world, pos.down().down().south().south().south().south(), ModBlocks.poweredStone.getDefaultState(), player);
			setBlock(world, pos.down().down().south().south().south().south().south(), ModBlocks.illuminateBlock.getDefaultState(), player);
			break;
		case 6:
//			player.playSound(SoundEvents.BLOCK_STONE_PLACE, 1f, 1.0f);
			//pillar
			setBlock(world, pos.down().north().north().west().west(), ModBlocks.sunMoonBlock.getDefaultState(), player);
			setBlock(world, pos.north().north().west().west(), ModBlocks.altarPillerBase.getDefaultState(), player);
			
			setBlock(world, pos.down().north().north().east().east(), ModBlocks.sunMoonBlock.getDefaultState(), player);
			setBlock(world, pos.north().north().east().east(), ModBlocks.altarPillerBase.getDefaultState(), player);
			
			setBlock(world, pos.down().south().south().west().west(), ModBlocks.sunMoonBlock.getDefaultState(), player);
			setBlock(world, pos.south().south().west().west(), ModBlocks.altarPillerBase.getDefaultState(), player);
			
			setBlock(world, pos.down().south().south().east().east(), ModBlocks.sunMoonBlock.getDefaultState(), player);
			setBlock(world, pos.south().south().east().east(), ModBlocks.altarPillerBase.getDefaultState(), player);
			break;
		case 7:
			//out
//			player.playSound(SoundEvents.BLOCK_STONE_PLACE, 1f, 1.0f);
			setBlock(world, pos.down().down().north().north().north().north().east().east().east(), ModBlocks.poweredStone.getDefaultState(), player);
			setBlock(world, pos.down().down().north().north().north().east().east().east().east(), ModBlocks.poweredStone.getDefaultState(), player);
			setBlock(world, pos.down().down().north().north().north().north().east().east().east().east(), ModBlocks.poweredStone.getDefaultState(), player);
			setBlock(world, pos.down().north().north().north().north().east().east().east().east(), ModBlocks.illuminateBlock.getDefaultState(), player);
			
			break;
		case 8:
//			player.playSound(SoundEvents.BLOCK_STONE_PLACE, 1f, 1.0f);
			setBlock(world, pos.down().down().north().north().north().north().west().west().west(), ModBlocks.poweredStone.getDefaultState(), player);
			setBlock(world, pos.down().down().north().north().north().west().west().west().west(), ModBlocks.poweredStone.getDefaultState(), player);
			setBlock(world, pos.down().down().north().north().north().north().west().west().west().west(), ModBlocks.poweredStone.getDefaultState(), player);
			setBlock(world, pos.down().north().north().north().north().west().west().west().west(), ModBlocks.illuminateBlock.getDefaultState(), player);
			break;
		case 9:
//			player.playSound(SoundEvents.BLOCK_STONE_PLACE, 1f, 1.0f);
			setBlock(world, pos.down().down().south().south().south().south().east().east().east(), ModBlocks.poweredStone.getDefaultState(), player);
			setBlock(world, pos.down().down().south().south().south().east().east().east().east(), ModBlocks.poweredStone.getDefaultState(), player);
			setBlock(world, pos.down().down().south().south().south().south().east().east().east().east(), ModBlocks.poweredStone.getDefaultState(), player);
			setBlock(world, pos.down().south().south().south().south().east().east().east().east(), ModBlocks.illuminateBlock.getDefaultState(), player);
			
			break;
		case 10:
//			player.playSound(SoundEvents.BLOCK_STONE_PLACE, 1f, 1.0f);
			setBlock(world, pos.down().down().south().south().south().south().west().west().west(), ModBlocks.poweredStone.getDefaultState(), player);
			setBlock(world, pos.down().down().south().south().south().west().west().west().west(), ModBlocks.poweredStone.getDefaultState(), player);
			setBlock(world, pos.down().down().south().south().south().south().west().west().west().west(), ModBlocks.poweredStone.getDefaultState(), player);
			setBlock(world, pos.down().south().south().south().south().west().west().west().west(), ModBlocks.illuminateBlock.getDefaultState(), player);
			break;
		case 11:
			//altar
//			player.playSound(SoundEvents.BLOCK_STONE_PLACE, 1f, 1.0f);
			setBlock(world, pos.down().down().north().north().north().north().north().up(),ModBlocks.altarOutsidePedestal.getDefaultState(), player); 
			setBlock(world, pos.down().down().west().west().west().west().west().up(), ModBlocks.altarOutsidePedestal.getDefaultState(), player); 
			setBlock(world, pos.down().down().east().east().east().east().east().up(), ModBlocks.altarOutsidePedestal.getDefaultState(), player); 
			setBlock(world, pos.down().down().south().south().south().south().south().up(), ModBlocks.altarOutsidePedestal.getDefaultState(), player); 
			setBlock(world, pos.down().north().north().west().west().up().up(), ModBlocks.altarInsidePedestal.getDefaultState(), player); 
			setBlock(world, pos.down().north().north().east().east().up().up(), ModBlocks.altarInsidePedestal.getDefaultState(), player); 
			setBlock(world, pos.down().south().south().west().west().up().up(), ModBlocks.altarInsidePedestal.getDefaultState(), player); 
			setBlock(world, pos.down().south().south().east().east().up().up(), ModBlocks.altarInsidePedestal.getDefaultState(), player);
			setBlock(world, pos.north().north().north().north().east().east().east().east(), ModBlocks.altarOutsidePedestal.getDefaultState(), player); 
			setBlock(world, pos.north().north().north().north().west().west().west().west(), ModBlocks.altarOutsidePedestal.getDefaultState(), player); 
			setBlock(world, pos.south().south().south().south().east().east().east().east(), ModBlocks.altarOutsidePedestal.getDefaultState(), player); 
			setBlock(world, pos.south().south().south().south().west().west().west().west(), ModBlocks.altarOutsidePedestal.getDefaultState(), player);
		}
	}
	

	private static void setBlock(World world, BlockPos pos, IBlockState blockState, EntityPlayer player) {
		if(player.isCreative()) {
			world.setBlockState(pos, blockState);
		}else {
			for(int i = 0; i<36; i++) {
				if(player.inventory.getStackInSlot(i).getItem() == new ItemStack(blockState.getBlock()).getItem() && world.getBlockState(pos) != Blocks.BEDROCK.getDefaultState()) {
//					world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY() + 1, pos.getZ(), world.getBlockState(pos).getBlock()));
					world.setBlockState(pos, blockState);
					player.inventory.getStackInSlot(i).shrink(1);
					break;
				}
			}
		}
		
		
	}
}
