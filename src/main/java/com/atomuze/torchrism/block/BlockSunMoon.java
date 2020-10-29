package com.atomuze.torchrism.block;

import javax.annotation.Nullable;

import com.atomuze.torchrism.tileentity.TileEntitySunMoon;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSunMoon extends BlockTileEntity<TileEntitySunMoon> {
	public BlockSunMoon(String name) {
		super(Material.ROCK, name);
		setHardness(3f);
		setResistance(4f);
	}

	public static void setState(boolean active, World worldIn, BlockPos pos) {
		IBlockState iblockstate = worldIn.getBlockState(pos);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		
		if (active) {
			worldIn.setBlockState(pos, ModBlocks.sunMoonBlock.getDefaultState(), 3);
			worldIn.setBlockState(pos, ModBlocks.sunMoonBlock.getDefaultState(), 3);
		} else {
			worldIn.setBlockState(pos, ModBlocks.sunMoonBlock_night.getDefaultState(), 3);
			worldIn.setBlockState(pos, ModBlocks.sunMoonBlock_night.getDefaultState(), 3);
		}

		if (tileentity != null) {
			tileentity.validate();
			worldIn.setTileEntity(pos, tileentity);
		}
	}

	@Override
	public BlockSunMoon setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public Class<TileEntitySunMoon> getTileEntityClass() {
		return TileEntitySunMoon.class;
	}

	@Nullable
	@Override
	public TileEntitySunMoon createTileEntity(World world, IBlockState state) {
		return new TileEntitySunMoon();
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return true;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return true;
	}
	
	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		drops.add(new ItemStack(ModBlocks.sunMoonBlock));
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(ModBlocks.sunMoonBlock);
	}
}