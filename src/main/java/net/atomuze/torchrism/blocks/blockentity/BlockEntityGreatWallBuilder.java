package net.atomuze.torchrism.blocks.blockentity;

import net.atomuze.torchrism.blocks.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.Random;

public class BlockEntityGreatWallBuilder extends BlockEntity {

	public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
	private boolean buildCastle = false;
	int buildNum = 0;
	BlockPos posOrigin = pos;
	BlockPos posCrr = pos;
	Random random = new Random();

	public BlockEntityGreatWallBuilder() {
		super(ModBlockEntity.GREAT_WALL_BLOCK_ENTITY);
	}
	
	@Override
	public BlockEntityUpdateS2CPacket toUpdatePacket() {
		if (!world.isClient) {
			if(buildCastle) {
				if(world.getBlockState(posCrr.north()).getBlock() == Blocks.TORCH) {
					posCrr = posCrr.north();
					buildWall(Direction.EAST);
				}else if(world.getBlockState(posCrr.east()).getBlock() == Blocks.TORCH) {
					posCrr = posCrr.east();
					buildWall(Direction.SOUTH);
				}else if(world.getBlockState(posCrr.south()).getBlock() == Blocks.TORCH) {
					posCrr = posCrr.south();
					buildWall(Direction.WEST);
				}else if(world.getBlockState(posCrr.west()).getBlock() == Blocks.TORCH) {
					posCrr = posCrr.west();
					buildWall(Direction.NORTH);
				}else if(world.getBlockState(posCrr.north().up()).getBlock() == Blocks.TORCH) {
					posCrr = posCrr.north().up();
					buildWall(Direction.EAST);
				}else if(world.getBlockState(posCrr.east().up()).getBlock() == Blocks.TORCH) {
					posCrr = posCrr.east().up();
					buildWall(Direction.SOUTH);
				}else if(world.getBlockState(posCrr.south().up()).getBlock() == Blocks.TORCH) {
					posCrr = posCrr.south().up();
					buildWall(Direction.WEST);
				}else if(world.getBlockState(posCrr.west().up()).getBlock() == Blocks.TORCH) {
					posCrr = posCrr.west().up();
					buildWall(Direction.NORTH);
				}else if(world.getBlockState(posCrr.north().down()).getBlock() == Blocks.TORCH) {
					posCrr = posCrr.north().down();
					buildWall(Direction.EAST);
				}else if(world.getBlockState(posCrr.east().down()).getBlock() == Blocks.TORCH) {
					posCrr = posCrr.east().down();
					buildWall(Direction.SOUTH);
				}else if(world.getBlockState(posCrr.south().down()).getBlock() == Blocks.TORCH) {
					posCrr = posCrr.south().down();
					buildWall(Direction.WEST);
				}else if(world.getBlockState(posCrr.west().down()).getBlock() == Blocks.TORCH) {
					posCrr = posCrr.west().down();
					buildWall(Direction.NORTH);
				}else {
					buildCastle = false;
					posCrr = posOrigin;
				}
				
				double d = random.nextFloat();
		        world.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX() + d, pos.getY() + d, pos.getZ() + d, 0.0D, 0.0D, 0.0D);
				
				buildNum++;
			}else {
				posCrr = pos;
				buildNum = 0;
			}
		}
		return null;
	}
	
	public void buildWall(Direction facing) {
		if(world.getBlockState(posCrr.up()).getBlock() == Blocks.AIR && world.getBlockState(posCrr.up().up()).getBlock() == Blocks.AIR && world.getBlockState(posCrr.up().up().up()).getBlock() == Blocks.AIR) {
			world.setBlockState(posCrr, ModBlocks.WALL.getDefaultState().with(FACING, facing));
			world.setBlockState(posCrr.up(), ModBlocks.WALL.getDefaultState().with(FACING, facing));
			world.setBlockState(posCrr.up().up(), ModBlocks.WALL.getDefaultState().with(FACING, facing));
			if(buildNum%2 == 0 ) {
				world.setBlockState(posCrr.up().up().up(), ModBlocks.WALL_LIGHT.getDefaultState());
			}
		}else {
			buildCastle = false;
		}
	}

	public void toggleActive() {
		if (this.buildCastle) {
			this.buildCastle = false;
		}else {
			this.buildCastle = true;
		}
	}
}
