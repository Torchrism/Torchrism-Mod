package com.atomuze.torchrism.tileentity;

import com.atomuze.torchrism.ModConfig;
import com.atomuze.torchrism.TorchrismAPI;
import com.atomuze.torchrism.block.BlockEncloseTorchPlacer;

import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class TileEntityEncloseTorchPlacer extends TileEntity implements ITickable {
	
	private boolean checking = false;
	int torchCount = 64;
	BlockPos activatepos = pos;
	int offset = ModConfig.offset + 1;
	int degree = 0;
	
	@Override
	public void update() {
		
//		float x = MathHelper.cos(degree * 0.017453292F);
//		float y = MathHelper.sin(degree * 0.017453292F);
//		degree++;
//		System.out.println("degree" + degree + "x " + x + " y " + y);
		
		if (!world.isRemote) {
			if (checking) {
				if(degree < 360) {
					moveAround(degree);
					degree++;
				}else {
					checking = false;
					degree = 0;
					if(BlockEncloseTorchPlacer.player != null) {
						BlockEncloseTorchPlacer.player.addItemStackToInventory(new ItemStack(Blocks.TORCH, torchCount));	
					}
				}
			}
		}
	}
	
	private void moveAround(int degree) {
		
		for(int i = 0; i<64; i = i + ModConfig.offset + 1) {
			BlockPos tempPos = new BlockPos(pos.getX(), pos.getY() + i, pos.getZ());
			RayTraceResult raytraceresult = rayTrace(world, tempPos, degree);
			placeTorch(raytraceresult);
		}
	}
	
	private void placeTorch(RayTraceResult raytraceresult) {
		int offset = ModConfig.offset+1;
		try {
			BlockPos blockpos = raytraceresult.getBlockPos();
			IBlockState iblockstate = world.getBlockState(blockpos);
			EnumFacing facing = raytraceresult.sideHit;
			BlockPos placepos = TorchrismAPI.posOffset(blockpos, facing);
			Material material = world.getBlockState(placepos).getMaterial();
			
			int offsetx = pos.getX() - placepos.getX();
			int offsetz = pos.getZ() - placepos.getZ();
			
			System.out.println(offsetx);
			System.out.println(offsetz);
			
			if(offsetx%offset == 0  ||  offsetz%offset == 0 ) {
				if(iblockstate.isFullBlock() && material == Material.AIR)
					world.setBlockState(placepos, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, facing));
			}
			
			System.out.println(iblockstate.toString());
			System.out.println("d:" + degree + " " + raytraceresult.toString());
		}catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
	
	protected RayTraceResult rayTrace(World worldIn, BlockPos pos, int degree) {
        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();
        Vec3d vec3d = new Vec3d(x, y, z);
        float vx = MathHelper.cos(degree * 0.017453292F);
        float vz = MathHelper.sin(degree * 0.017453292F);
        float distance = 64.0f;
        Vec3d vec3d1 = vec3d.addVector(Math.signum(vx), 0.5, Math.signum(vz));
        System.out.println(vec3d1.toString());
        Vec3d vec3d2 = vec3d.addVector((double)vx * distance, 0.5, (double)vz * distance);
        return worldIn.rayTraceBlocks(vec3d1, vec3d2, true, true, false);
    }
	
	public boolean isUsableByPlayer(EntityPlayer player) {
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}
	
	public void toggleActive() {
		if (this.checking) {
			this.checking = false;
		}else if(this.world.getChunkFromBlockCoords(getPos()).canSeeSky(getPos())) {
			this.checking = false;
		}else {
			this.checking = true;
		}
	}
}
