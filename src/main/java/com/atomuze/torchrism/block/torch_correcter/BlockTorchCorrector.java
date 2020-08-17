package com.atomuze.torchrism.block.torch_correcter;

import com.atomuze.torchrism.block.BlockTileEntity;
import com.atomuze.torchrism.block.torch_castle.TileEntityGreatWallBuilder;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockTorchCorrector extends BlockTileEntity<TileEntityTorchCorrector> {
	public static EntityPlayer player = null;

	public BlockTorchCorrector(String name) {
		super(Material.WOOD, name);
		setHardness(3f);
		setResistance(4f);
		setLightLevel(1f);
	}

	@Override
	public BlockTorchCorrector setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		this.player = player;
		final TileEntityTorchCorrector tileEntity = (TileEntityTorchCorrector) world.getTileEntity(pos);
		if (tileEntity != null) {

			tileEntity.toggleActive();
		}

		TileEntity tileentity = world.getTileEntity(pos);

		if (tileentity instanceof TileEntityTorchCorrector) {
			((TileEntityTorchCorrector) tileentity).setPos(pos);
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
	public TileEntityTorchCorrector createTileEntity(World world, IBlockState state) {
		return new TileEntityTorchCorrector();
	}
	
	@Override
	public Class<TileEntityTorchCorrector> getTileEntityClass() {
		return TileEntityTorchCorrector.class;
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