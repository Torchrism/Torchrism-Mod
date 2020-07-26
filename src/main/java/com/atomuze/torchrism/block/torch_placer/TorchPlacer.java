package com.atomuze.torchrism.block.torch_placer;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.atomuze.torchrism.TorchrismMod;
import com.atomuze.torchrism.ModConfig;
import com.atomuze.torchrism.block.BlockTileEntity;

import javax.annotation.Nullable;

public class TorchPlacer extends BlockTileEntity {

	public TorchPlacer(String name) {
		super(Material.ROCK, name);

		setHardness(1f);
		setResistance(5f);
		setLightLevel(1f);

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
