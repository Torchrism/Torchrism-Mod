package com.atomuze.torchrism.block.torch_dice;

import java.util.Random;

import com.atomuze.torchrism.ModConfig;
import com.atomuze.torchrism.block.BlockTileEntity;
import com.atomuze.torchrism.block.torch_placer.TorchPlacer;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TorchDice extends BlockTileEntity {
	
	public TorchDice(String name) {
		super(Material.ROCK, name);

		setHardness(1f);
		setResistance(1f);
		setLightLevel(1f);

	}

	@Override
	public TorchDice setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		
		Random random = new Random();
		int rand = random.nextInt(5);
		
		
		if (!world.isRemote) {
			player.addItemStackToInventory(new ItemStack(Blocks.TORCH, rand + 1));
			world.setBlockState(pos, Blocks.AIR.getDefaultState());
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
	public Class getTileEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		// TODO Auto-generated method stub
		return null;
	}
}

