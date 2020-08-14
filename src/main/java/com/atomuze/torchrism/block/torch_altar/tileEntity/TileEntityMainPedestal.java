package com.atomuze.torchrism.block.torch_altar.tileEntity;

import java.util.Random;

import javax.annotation.Nullable;
import javax.swing.Icon;

import com.atomuze.torchrism.block.ModBlocks;
import com.atomuze.torchrism.block.torch_altar.block.BlockMainPedestal;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class TileEntityMainPedestal extends TileEntityPedestal implements ITickable {
	
	public long lastChangeTime;
	long initTime = 0;
	public static double Offset = 0;
	public boolean altarT = true;
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setTag("inventory", inventory.serializeNBT());
		compound.setLong("lastChangeTime", lastChangeTime);
		return super.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		inventory.deserializeNBT(compound.getCompoundTag("inventory"));
		lastChangeTime = compound.getLong("lastChangeTime");
		super.readFromNBT(compound);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}
	
	@Nullable
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T)inventory : super.getCapability(capability, facing);
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(getPos(), getPos().add(1, 2, 1));
	}

	@Override
	public void update() {

		if (BlockMainPedestal.crafting) {

			long processingTime = world.getTotalWorldTime() - initTime;

			Offset = (processingTime / 60.0) * 3;
			if (processingTime > 60.0 && processingTime < 80.0) {
				Offset = 3;
				BlockMainPedestal.isCraftingFinfish = true;
			} else if (processingTime > 80.0) {
				BlockMainPedestal.crafting = false;
				BlockMainPedestal.isCraftingFinfish = false;
				craftingEffect();
				Offset = 0;
			}

		} else {
			initTime = world.getTotalWorldTime();
		}
		
		if(world.getWorldTime()%24000<12000 &&!altarT) {
			BlockMainPedestal.setState(true, world, pos);
			altarT = true;
		}else if(world.getWorldTime()%24000>12000 && altarT){
			BlockMainPedestal.setState(false, world, pos);
			altarT = false;
		}
		
		if(BlockMainPedestal.isCraftingFinfish) {
			this.spawnParticles(world,pos);
		}
		
	}
	
	public void craftingEffect() {
		
		extractItem(world, pos);
		extractItem(world, pos.down().north().north().west().west().up().up());
		extractItem(world, pos.down().north().north().east().east().up().up());
		extractItem(world, pos.down().south().south().west().west().up().up());
		extractItem(world, pos.down().south().south().east().east().up().up());
		extractItem(world, pos.down().down().north().north().north().north().north().up());
		extractItem(world, pos.down().down().west().west().west().west().west().up());
		extractItem(world, pos.down().down().east().east().east().east().east().up());
		extractItem(world, pos.down().down().south().south().south().south().south().up());
		extractItem(world, pos.north().north().north().north().east().east().east().east());
		extractItem(world, pos.north().north().north().north().west().west().west().west());
		extractItem(world, pos.south().south().south().south().east().east().east().east());
		extractItem(world, pos.south().south().south().south().west().west().west().west());
		world.spawnEntity(BlockMainPedestal.craftingResult);
	}

	public void extractItem(World world, BlockPos pos) {
		TileEntityPedestal tile = (TileEntityPedestal) world.getTileEntity(pos);
		IItemHandler itemHandler = tile.inventory;
		itemHandler.extractItem(0, 1, false);
		tile.markDirty();
	}
	
	private void spawnParticles(World worldIn, BlockPos pos)
    {
        Random random = worldIn.rand;
        double d0 = 0.0625D;

        for (int i = 0; i < 6; ++i)
        {
            double d1 = (double)((float)pos.getX() + random.nextFloat());
            double d2 = (double)((float)pos.getY() + random.nextFloat());
            double d3 = (double)((float)pos.getZ() + random.nextFloat());

            if (i == 0 && !worldIn.getBlockState(pos.up()).isOpaqueCube())
            {
                d2 = (double)pos.getY() + 0.0625D + 1.0D;
            }

            if (i == 1 && !worldIn.getBlockState(pos.down()).isOpaqueCube())
            {
                d2 = (double)pos.getY() - 0.0625D;
            }

            if (i == 2 && !worldIn.getBlockState(pos.south()).isOpaqueCube())
            {
                d3 = (double)pos.getZ() + 0.0625D + 1.0D;
            }

            if (i == 3 && !worldIn.getBlockState(pos.north()).isOpaqueCube())
            {
                d3 = (double)pos.getZ() - 0.0625D;
            }

            if (i == 4 && !worldIn.getBlockState(pos.east()).isOpaqueCube())
            {
                d1 = (double)pos.getX() + 0.0625D + 1.0D;
            }

            if (i == 5 && !worldIn.getBlockState(pos.west()).isOpaqueCube())
            {
                d1 = (double)pos.getX() - 0.0625D;
            }

            if (d1 < (double)pos.getX() || d1 > (double)(pos.getX() + 1) || d2 < 0.0D || d2 > (double)(pos.getY() + 1) || d3 < (double)pos.getZ() || d3 > (double)(pos.getZ() + 1))
            {
                worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, d1, d2+3, d3, 0.0D, 0.0D, 0.0D);
            }
        }
    }

}