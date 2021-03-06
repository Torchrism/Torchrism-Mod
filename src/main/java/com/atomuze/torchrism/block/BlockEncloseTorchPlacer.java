package com.atomuze.torchrism.block;

import com.atomuze.torchrism.tileentity.TileEntityEncloseTorchPlacer;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockEncloseTorchPlacer extends BlockTileEntity<TileEntityEncloseTorchPlacer> {
	public static EntityPlayer player = null;

	public BlockEncloseTorchPlacer(String name) {
		super(Material.WOOD, name);
		setHardness(3f);
		setResistance(4f);
		setHarvestLevel("axe", 1);
		setLightLevel(1f);
	}

	@Override
	public BlockEncloseTorchPlacer setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		this.player = player;
		final TileEntityEncloseTorchPlacer tileEntity = (TileEntityEncloseTorchPlacer) world.getTileEntity(pos);
		if (tileEntity != null) {

			tileEntity.toggleActive();
		}

		TileEntity tileentity = world.getTileEntity(pos);

		if (tileentity instanceof TileEntityEncloseTorchPlacer) {
			((TileEntityEncloseTorchPlacer) tileentity).setPos(pos);
			
		}

		return true;
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {

	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	@Override
	public TileEntityEncloseTorchPlacer createTileEntity(World world, IBlockState state) {
		return new TileEntityEncloseTorchPlacer();
	}
	
	@Override
	public Class<TileEntityEncloseTorchPlacer> getTileEntityClass() {
		return TileEntityEncloseTorchPlacer.class;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return true;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return true;
	}
}