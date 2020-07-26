package com.atomuze.torchrism.block.torch_altar.altar;

import java.util.Random;

import javax.annotation.Nullable;

import com.atomuze.torchrism.ModConfig;
import com.atomuze.torchrism.block.BlockTileEntity;
import com.atomuze.torchrism.block.torch_placer.TorchPlacer;
import com.atomuze.torchrism.item.ModItems;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class TorchAltar extends BlockTileEntity<TileEntityTorchAltar> {
	
	public TorchAltar(String name) {
		super(Material.ROCK, name);

		setHardness(3f);
		setResistance(5f);
		setLightLevel(1f);
	}

	@Override
	public TorchAltar setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
	
	@Override
	public Class<TileEntityTorchAltar> getTileEntityClass() {
		return TileEntityTorchAltar.class;
	}
	
	@Nullable
	@Override
	public TileEntityTorchAltar createTileEntity(World world, IBlockState state) {
		return new TileEntityTorchAltar();
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			
			if(player.getHeldItem(hand).getItem() == new ItemStack(ModItems.torchwand).getItem()) {
				player.sendMessage(new TextComponentString("Altar Checking"));
			}else {
				TileEntityTorchAltar tile = getTileEntity(world, pos);
				IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side);
				
				if (player.getHeldItem(hand).isEmpty()) {
					player.setHeldItem(hand, itemHandler.extractItem(0, 64, false));
				} else {
					player.setHeldItem(hand, itemHandler.insertItem(0, player.getHeldItem(hand), false));
				}
				tile.markDirty();
			}
			
		}
		return true;
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntityTorchAltar tile = getTileEntity(world, pos);
		IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
		ItemStack stack = itemHandler.getStackInSlot(0);
		if (!stack.isEmpty()) {
			EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
			world.spawnEntity(item);
		}
		super.breakBlock(world, pos, state);
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
}  
