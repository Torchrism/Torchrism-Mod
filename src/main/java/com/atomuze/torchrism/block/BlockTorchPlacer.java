package com.atomuze.torchrism.block;

import java.util.Random;

import com.atomuze.torchrism.ModConfig;

import net.minecraft.block.Block;
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
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockTorchPlacer extends BlockBase {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	private Random rand;

	public BlockTorchPlacer(String name) {
		super(Material.WOOD, name);

		setHardness(1f);
		setResistance(4f);
		setLightLevel(1f);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));

	}

	@Override
	public BlockTorchPlacer setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		Block block = new Block(Material.CIRCUITS);
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
                    for (placePosY = 255; placePosY >= 0; placePosY--) {
                        BlockPos PlacePos = new BlockPos(placePosX, placePosY, placePosZ);

                        Material m = world.getBlockState(PlacePos).getMaterial();
                        Material mDown = world.getBlockState(PlacePos.down()).getMaterial();
                        state = world.getBlockState(PlacePos.down());

                        if(mDown == Material.WATER){
                            break;
                        }else if ((mDown.isReplaceable() || mDown == Material.LEAVES) && mDown != Material.AIR){
                            world.setBlockState(PlacePos.down(), Blocks.AIR.getDefaultState());
                            continue;
                        }else if (mDown.isSolid() && m == Material.AIR) {
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
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
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
