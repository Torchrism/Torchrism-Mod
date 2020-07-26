package com.atomuze.torchrism.block.torch_castle;

import java.util.Random;

import com.atomuze.torchrism.block.BlockBase;
import com.atomuze.torchrism.block.BlockTileEntity;
import com.atomuze.torchrism.block.torch_dice.TorchDice;
import com.google.common.cache.CacheBuilder;

import net.minecraft.block.ITileEntityProvider;
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
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;

public class TorchCastle extends BlockBase {
	BlockPos pos;
	
	public TorchCastle(String name) {
		super(Material.ROCK, name);

		setHardness(3f);
		setResistance(4f);
		setLightLevel(1f);

	}

	@Override
	public TorchCastle setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		final TileEntityTorchCastle tileEntity = (TileEntityTorchCastle) world.getTileEntity(pos);  
        if(tileEntity != null) {
        	 
        	tileEntity.toggleActive();
        }
        
        TileEntity tileentity = world.getTileEntity(pos);

		if (tileentity instanceof TileEntityTorchCastle){
			((TileEntityTorchCastle)tileentity).setPos(pos);	
		}

		return true;
	}
		
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		
		TileEntityTorchCastle tileEntity = (TileEntityTorchCastle)world.getTileEntity(pos);
		ItemStack stack = ((IItemHandler) tileEntity).getStackInSlot(0);
		EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
		world.spawnEntity(item);
		
		super.breakBlock(world, pos, state);
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityTorchCastle();
	}
}