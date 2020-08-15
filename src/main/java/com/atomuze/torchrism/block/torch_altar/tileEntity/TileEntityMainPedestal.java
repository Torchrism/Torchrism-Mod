package com.atomuze.torchrism.block.torch_altar.tileEntity;

import java.util.Random;

import javax.annotation.Nullable;
import javax.swing.Icon;

import com.atomuze.torchrism.Torchrism;
import com.atomuze.torchrism.block.ModBlocks;
import com.atomuze.torchrism.block.torch_altar.block.BlockMainPedestal;
import com.atomuze.torchrism.network.AltarCraftingParticlePacket;
import com.atomuze.torchrism.network.PacketUpdatePedestal;
import com.atomuze.torchrism.network.TorchrimNetworkHandler;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class TileEntityMainPedestal extends TileEntityPedestal implements ITickable {
	
	long initTime = 0;
	public boolean altarT = true;
	

	@Override
	public void update() {
		if (BlockMainPedestal.crafting) {
			if (!world.isRemote) {
				TorchrimNetworkHandler.network.sendToAllAround(new AltarCraftingParticlePacket(BlockMainPedestal.crafting), new NetworkRegistry.TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 64));
			}
			long processingTime = world.getTotalWorldTime() - initTime;
			this.spawnParticles1(world, pos);
			if (processingTime > 60.0 && processingTime < 80.0) {
				this.spawnParticles1(world, pos);
				this.spawnParticles2(world, pos);
			} else if (processingTime > 80.0) {
				BlockMainPedestal.crafting = false;
				craftingEffect();
			}
			
		} else {
			initTime = world.getTotalWorldTime();
		}
		
//		System.out.println("BlockMainPedestal.crafting "+ BlockMainPedestal.crafting);
		
		if (world.getWorldTime() % 24000 < 12000 && !altarT) {
			BlockMainPedestal.setState(true, world, pos);
			altarT = true;
		} else if (world.getWorldTime() % 24000 > 12000 && altarT) {
			BlockMainPedestal.setState(false, world, pos);
			altarT = false;
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
		if(!world.isRemote) {
			world.spawnEntity(BlockMainPedestal.craftingResult);
		}
		
	}

	public void extractItem(World world, BlockPos pos) {
		TileEntityPedestal tile = (TileEntityPedestal) world.getTileEntity(pos);
		IItemHandler itemHandler = tile.inventory;
		itemHandler.extractItem(0, 1, false);
		tile.markDirty();
	}
	
	private void spawnParticles1(World worldIn, BlockPos pos) {
		Random random = worldIn.rand;
		
		for (int i = -4; i < 4; i++) {
			double d1 = (double) ((float) pos.getX() + random.nextFloat());
			double d2 = (double) ((float) pos.getY() + random.nextFloat());
			double d3 = (double) ((float) pos.getZ() + random.nextFloat());

			double posX = 2.0D*((double)i/3.0D);
			double posZ = 2.0D*((double)i/3.0D);
			worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, d1 + posX, d2 + 2, d3 + posZ, 0.0D, 0.0D, 0.0D);

		}
		
		for (int i = -4; i < 4; i++) {
			double d1 = (double) ((float) pos.getX() + random.nextFloat());
			double d2 = (double) ((float) pos.getY() + random.nextFloat());
			double d3 = (double) ((float) pos.getZ() + random.nextFloat());

			double posX = 2.0D*((double)i/3.0D);
			double posZ = 2.0D*((double)i/3.0D);
			worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, d1 + posX, d2 + 2, d3 - posZ, 0.0D, 0.0D, 0.0D);

		}
		
	}
	
	private void spawnParticles2(World worldIn, BlockPos pos) {
		Random random = worldIn.rand;
		
		for (int i = 0; i < 6; ++i) {
			double d1 = (double) ((float) pos.getX() + random.nextFloat());
			double d2 = (double) ((float) pos.getY() + random.nextFloat());
			double d3 = (double) ((float) pos.getZ() + random.nextFloat());
			worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, d1, d2 + 1, d3, 0.0D, 0.0D, 0.0D);
		}
		
	}

}