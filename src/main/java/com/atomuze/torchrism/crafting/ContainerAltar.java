package com.atomuze.torchrism.crafting;

import java.util.List;

import com.atomuze.torchrism.block.ModBlocks;
import com.atomuze.torchrism.block.altar.block.BlockAltarMainPedestal;
import com.atomuze.torchrism.block.altar.tileEntity.TileEntityPedestal;
import com.google.common.collect.Lists;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ContainerAltar extends Container {

    public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
    public InventoryCraftResult craftResult = new InventoryCraftResult();

    private World world = null;
    private BlockPos pos = null;
    
    public ContainerAltar(World worldIn, BlockPos posIn) {
    	this.world = worldIn;
    	this.pos = posIn;
    	System.out.println("addSlotToContainer-----------------------------------------------");
    	this.addSlotToContainer(new SlotCrafting(null, this.craftMatrix, this.craftResult, 0, 0, 0));
    	for(int i =0 ; i<9; i++) {
    		this.addSlotToContainer(new Slot(this.craftMatrix, i, 0, 0));
    	}
    	
    }
    
    @Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		System.out.println("canInteractWith-----------------------------------------------");
		if (this.world.getBlockState(this.pos).getBlock() != ModBlocks.altarMainPedestal && this.world.getBlockState(this.pos).getBlock() != ModBlocks.altarMainPedestal_night) {		
			return false;
		} else {
			return playerIn.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
		}
	}
	
}
