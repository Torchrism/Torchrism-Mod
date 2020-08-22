package com.atomuze.torchrism.item.staff;

import java.util.Random;

import com.atomuze.torchrism.Torchrism;
import com.atomuze.torchrism.block.ModBlocks;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemStaff extends net.minecraft.item.ItemTool {
	private String name;
	Random random = new Random();

	public ItemStaff(ToolMaterial material, String name) {
		super(0, 0, material, null);
		setRegistryName(name);
		setUnlocalizedName(Torchrism.MODID + "." + name);
		setCreativeTab(Torchrism.creativeTab);
		setMaxStackSize(1);
		this.name = name;
	}

	public void registerItemModel(Item item) {
		Torchrism.proxy.registerItemRenderer(this, 0, name);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		if (player.isSneaking()) {
			if (worldIn.isRemote)
				player.sendMessage(new TextComponentString(I18n.format(Torchrism.MODID + "." + "torch_staff.torch_count") + player.getHeldItem(hand).getItemDamage()));
			return EnumActionResult.SUCCESS;
		} else if (worldIn.getBlockState(pos).getBlock() == Blocks.TORCH || worldIn.getBlockState(pos).getBlock() == ModBlocks.compactedTorch || worldIn.getBlockState(pos).getBlock() == ModBlocks.doublecompactedTorch) {
			if (player.getHeldItem(hand).getMaxDamage() != player.getHeldItem(hand).getItemDamage()) {
				if (worldIn.getBlockState(pos).getBlock() == Blocks.TORCH) {
					if (!worldIn.isRemote && player.getHeldItem(hand).getItemDamage() + 1 < player.getHeldItem(hand).getMaxDamage()) {
						player.getHeldItem(hand).damageItem(1, player);
					}
				} else if (worldIn.getBlockState(pos).getBlock() == ModBlocks.compactedTorch) {
					if (!worldIn.isRemote && player.getHeldItem(hand).getItemDamage() + 8 < player.getHeldItem(hand).getMaxDamage()) {
						player.getHeldItem(hand).damageItem(8, player);
					}
				} else if (worldIn.getBlockState(pos).getBlock() == ModBlocks.doublecompactedTorch) {
					if (!worldIn.isRemote && player.getHeldItem(hand).getItemDamage() + 64 < player.getHeldItem(hand).getMaxDamage()) {
						player.getHeldItem(hand).damageItem(64, player);
					}
				}

				worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
				for (int i = 0; i < 6; ++i) {
					double d1 = (double) ((float) pos.getX() + random.nextFloat());
					double d2 = (double) ((float) pos.getY() + random.nextFloat());
					double d3 = (double) ((float) pos.getZ() + random.nextFloat());
					worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d1, d2, d3, 0.0D, 0.0D, 0.0D);
				}
			} else {
				if (worldIn.isRemote) {
					player.sendMessage(new TextComponentString(I18n.format(Torchrism.MODID + "." + "torch_staff.over_capacity")));
				}
			}
			return EnumActionResult.SUCCESS;

		} else if (worldIn.getBlockState(pos).isNormalCube() && player.getHeldItem(hand).getItemDamage() > 0) {
			
			Material m = worldIn.getBlockState(pos.up()).getMaterial();
			if(facing == EnumFacing.NORTH) {
				m = worldIn.getBlockState(pos.north()).getMaterial();
			}else if(facing == EnumFacing.SOUTH ) {
				m = worldIn.getBlockState(pos.south()).getMaterial();
			}else if(facing == EnumFacing.EAST ) {
				m = worldIn.getBlockState(pos.east()).getMaterial();
			}else if(facing == EnumFacing.WEST ) {
				m = worldIn.getBlockState(pos.west()).getMaterial();
			}
			
			if(facing == EnumFacing.NORTH && (m == Material.VINE || m == Material.SNOW || m == Material.AIR) &&  m != Material.WATER) {
				worldIn.setBlockState(pos.north(), Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING , facing));
			}else if(facing == EnumFacing.SOUTH && (m == Material.VINE || m == Material.SNOW || m == Material.AIR)&&  m != Material.WATER) {
				worldIn.setBlockState(pos.south(), Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING , facing));
			}else if(facing == EnumFacing.EAST && (m == Material.VINE || m == Material.SNOW || m == Material.AIR)&&  m != Material.WATER) {
				worldIn.setBlockState(pos.east(), Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING , facing));
			}else if(facing == EnumFacing.WEST && (m == Material.VINE || m == Material.SNOW || m == Material.AIR)&&  m != Material.WATER) {
				worldIn.setBlockState(pos.west(), Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING , facing));
			}else if((m == Material.VINE || m == Material.SNOW || m == Material.AIR) &&  m != Material.WATER){
				worldIn.setBlockState(pos.up(), Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING , facing));
			}else {
				return EnumActionResult.FAIL;
			}
			
			if (!worldIn.isRemote) {
				player.getHeldItem(hand).damageItem(-1, player);
			}
			return EnumActionResult.SUCCESS;
		} else {
			if (player.getHeldItem(hand).getItemDamage() <= 0) {
				if (worldIn.isRemote)
					player.sendMessage(new TextComponentString(I18n.format(Torchrism.MODID + "." + "torch_staff.no_enough_item")));
			}
			return EnumActionResult.FAIL;
		}
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		return true;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
	        return true;
	}

	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		return 1.0F;
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return false;
	}
}
