package com.atomuze.torchrism.block.torch_altar;

import javax.annotation.Nullable;

import com.atomuze.torchrism.TorchrismMod;
import com.atomuze.torchrism.network.PacketRequestUpdateTorchAltar;
import com.atomuze.torchrism.network.PacketUpdateTorchAltar;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityTorchAltar extends TileEntity {
	
	public long lastChangeTime;
	
	public ItemStackHandler inventory = new ItemStackHandler(1) {
		@Override
		protected void onContentsChanged(int slot) {
			if (!world.isRemote) {
				lastChangeTime = world.getTotalWorldTime();
				TorchrismMod.network.sendToAllAround(new PacketUpdateTorchAltar(TileEntityTorchAltar.this), new NetworkRegistry.TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 64));
			}
		}
	};
	
	@Override
	public void onLoad() {
		if (world.isRemote) {
			TorchrismMod.network.sendToServer(new PacketRequestUpdateTorchAltar(this));
		}
	}
	
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
}