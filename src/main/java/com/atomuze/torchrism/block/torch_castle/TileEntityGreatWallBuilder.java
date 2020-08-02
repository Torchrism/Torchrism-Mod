package com.atomuze.torchrism.block.torch_castle;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityGreatWallBuilder extends TileEntity implements ITickable {
	
	private boolean buildCastle = false;
	
	BlockPos pos2 = pos;
	
	@Override
	public void update() {
		if (!world.isRemote) {
			if(buildCastle) {
				if(world.getBlockState(pos.north()).getBlock() == Blocks.TORCH) {
					pos = pos.north();
					buildWall();
				}else if(world.getBlockState(pos.east()).getBlock() == Blocks.TORCH) {
					pos = pos.east();
					buildWall();
				}else if(world.getBlockState(pos.south()).getBlock() == Blocks.TORCH) {
					pos = pos.south();
					buildWall();
				}else if(world.getBlockState(pos.west()).getBlock() == Blocks.TORCH) {
					pos = pos.west();
					buildWall();
				}else {
					buildCastle = false;
					pos = pos2;
				}
			}
		}
	}	
	
	public void buildWall() {
		world.setBlockState(pos, Blocks.STONE.getDefaultState());
		world.setBlockState(pos.up(), Blocks.STONE.getDefaultState());
		world.setBlockState(pos.up().up(), Blocks.STONE.getDefaultState());
	}
	
	public boolean isUsableByPlayer(EntityPlayer player) {
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}
	
	public void toggleActive() {
		if (this.buildCastle) {
			System.out.println("toggleActive to false");
			this.buildCastle = false;
		}else {
			System.out.println("toggleActive");
			this.buildCastle = true;
		}
	}
}
