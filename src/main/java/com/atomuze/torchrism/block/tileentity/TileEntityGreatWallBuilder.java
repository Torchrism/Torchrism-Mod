package com.atomuze.torchrism.block.tileentity;

import com.atomuze.torchrism.block.ModBlocks;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TileEntityGreatWallBuilder extends TileEntity implements ITickable {
	
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	private boolean buildCastle = false;
	int buildNum = 0;
	BlockPos pos2 = pos;
	
	@Override
	public void update() {
		if (!world.isRemote) {
			if(buildCastle) {
				if(world.getBlockState(pos.north()).getBlock() == Blocks.TORCH) {
					pos = pos.north();
					buildWall(EnumFacing.EAST);
				}else if(world.getBlockState(pos.east()).getBlock() == Blocks.TORCH) {
					pos = pos.east();
					buildWall(EnumFacing.SOUTH);
				}else if(world.getBlockState(pos.south()).getBlock() == Blocks.TORCH) {
					pos = pos.south();
					buildWall(EnumFacing.WEST);
				}else if(world.getBlockState(pos.west()).getBlock() == Blocks.TORCH) {
					pos = pos.west();
					buildWall(EnumFacing.NORTH);
				}else if(world.getBlockState(pos.north().up()).getBlock() == Blocks.TORCH) {
					pos = pos.north().up();
					buildWall(EnumFacing.EAST);
				}else if(world.getBlockState(pos.east().up()).getBlock() == Blocks.TORCH) {
					pos = pos.east().up();
					buildWall(EnumFacing.SOUTH);
				}else if(world.getBlockState(pos.south().up()).getBlock() == Blocks.TORCH) {
					pos = pos.south().up();
					buildWall(EnumFacing.WEST);
				}else if(world.getBlockState(pos.west().up()).getBlock() == Blocks.TORCH) {
					pos = pos.west().up();
					buildWall(EnumFacing.NORTH);
				}else if(world.getBlockState(pos.north().down()).getBlock() == Blocks.TORCH) {
					pos = pos.north().down();
					buildWall(EnumFacing.EAST);
				}else if(world.getBlockState(pos.east().down()).getBlock() == Blocks.TORCH) {
					pos = pos.east().down();
					buildWall(EnumFacing.SOUTH);
				}else if(world.getBlockState(pos.south().down()).getBlock() == Blocks.TORCH) {
					pos = pos.south().down();
					buildWall(EnumFacing.WEST);
				}else if(world.getBlockState(pos.west().down()).getBlock() == Blocks.TORCH) {
					pos = pos.west().down();
					buildWall(EnumFacing.NORTH);
				}else {
					buildCastle = false;
					pos = pos2;
				}
				buildNum++;
			}
		}
	}	
	
	public void buildWall(EnumFacing facing) {
		if(world.getBlockState(pos.up()).getBlock() == Blocks.AIR && world.getBlockState(pos.up().up()).getBlock() == Blocks.AIR && world.getBlockState(pos.up().up().up()).getBlock() == Blocks.AIR) {
			world.setBlockState(pos, ModBlocks.wall.getDefaultState().withProperty(FACING, facing));
			world.setBlockState(pos.up(), ModBlocks.wall.getDefaultState().withProperty(FACING, facing));
			world.setBlockState(pos.up().up(), ModBlocks.wall.getDefaultState().withProperty(FACING, facing));
			if(buildNum%2 == 0 ) {
				world.setBlockState(pos.up().up().up(), ModBlocks.wallLight.getDefaultState());
			}
		}else {
			buildCastle = false;
		}
		
		
	}
	
	public boolean isUsableByPlayer(EntityPlayer player) {
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}
	
	public void toggleActive() {
		if (this.buildCastle) {
			this.buildCastle = false;
		}else {
			this.buildCastle = true;
		}
	}
}
