package com.atomuze.torchrism.block.torch_placer;

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
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.atomuze.torchrism.TorchrismMod;
import com.atomuze.torchrism.ModConfig;
import com.atomuze.torchrism.block.BlockBase;
import com.atomuze.torchrism.block.BlockTileEntity;

import javax.annotation.Nullable;

public class TorchPlacer extends BlockBase{

	public static final PropertyDirection FACING = BlockHorizontal.FACING;

	public TorchPlacer(String name) {
		super(Material.WOOD, name);

		setHardness(1f);
		setResistance(5f);
		setLightLevel(1f);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));

	}

	@Override
	public TorchPlacer setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {

		int offset = ModConfig.getTorchOffset();

		// Relative to Torch Placer
		int placerPosX = pos.getX() - offset * 8;
		int placePosY = 255;
		int placerPosZ = pos.getZ() - offset * 8;

		if (!world.isRemote) {
			int giveBackToPlayerCount = 256;
			world.setBlockState(pos, Blocks.AIR.getDefaultState());
			for (int placePosX = placerPosX; placePosX < placerPosX + 16 * offset; placePosX = placePosX + offset) {
				for (int placePosZ = placerPosZ; placePosZ < placerPosZ + 16 * offset; placePosZ = placePosZ + offset) {
					System.out.println(placePosX);
					System.out.println(placePosZ);
					for (placePosY = 255; placePosY >= 0; placePosY--) {
						BlockPos findPlacePos = new BlockPos(placePosX, placePosY, placePosZ);
						BlockPos PlacePos = new BlockPos(placePosX, placePosY + 1, placePosZ);

						Material m = world.getBlockState(findPlacePos).getMaterial();
						Material m2 = world.getBlockState(PlacePos).getMaterial();
						IBlockState iblockstate = world.getBlockState(findPlacePos);

						if (m == Material.VINE || m == Material.SNOW || m == Material.AIR) {
							world.setBlockState(findPlacePos, Blocks.AIR.getDefaultState());
							continue;
						} else if (iblockstate.isNormalCube() && m2 == Material.AIR) {
							world.setBlockState(PlacePos, Blocks.TORCH.getDefaultState());
							giveBackToPlayerCount--;
							break;
						}
					}
				}
			}
			player.addItemStackToInventory(new ItemStack(Blocks.TORCH, giveBackToPlayerCount));
		}
		return true;
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
}
