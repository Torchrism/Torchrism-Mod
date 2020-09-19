package com.atomuze.torchrism.crafting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ContainerAltar extends Container {

	public InventoryCrafting craftMatrix = new InventoryCrafting(this, 9, 0);
	public InventoryCraftResult craftResult = new InventoryCraftResult();

	private final World world;
	private final BlockPos pos;
	private final EntityPlayer player;

	public ContainerAltar(InventoryPlayer playerInventory, World worldIn, BlockPos posIn) {
		this.world = worldIn;
		this.pos = posIn;
		this.player = playerInventory.player;
//		this.addSlotToContainer(new SlotCrafting(playerInventory.player, this.craftMatrix, this.craftResult, 0, 0, 0));

//		System.out.println("slotChangedCraftingGrid*--------------------------------------------------");
		this.slotChangedCraftingGrid(this.world, this.player, this.craftMatrix, this.craftResult);

	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return false;
	}

}
