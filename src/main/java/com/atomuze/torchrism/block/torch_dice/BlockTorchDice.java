package com.atomuze.torchrism.block.torch_dice;

import java.util.Random;

import com.atomuze.torchrism.ModConfig;
import com.atomuze.torchrism.block.BlockTileEntity;
import com.atomuze.torchrism.block.torch_placer.BlockTorchPlacer;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockTorchDice extends BlockTileEntity {
	
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	public BlockTorchDice(String name) {
		super(Material.WOOD, name);

		setHardness(1f);
		setResistance(1f);
		setLightLevel(1f);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));

	}

	@Override
	public BlockTorchDice setCreativeTab(CreativeTabs tab) {
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
	public Class getTileEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		// TODO Auto-generated method stub
		return null;
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
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing) state.getValue(FACING)).getIndex();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) 
	{
		EnumFacing facing = EnumFacing.getFront(meta);
		if(facing.getAxis() == EnumFacing.Axis.Y) facing = EnumFacing.NORTH;
		return this.getDefaultState().withProperty(FACING, facing);
	}
}

