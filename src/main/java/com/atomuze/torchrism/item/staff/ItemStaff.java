package com.atomuze.torchrism.item.staff;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.atomuze.torchrism.Torchrism;
import com.atomuze.torchrism.block.ModBlocks;
import com.atomuze.torchrism.block.torches.BlockWaterTorch;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, true);

		if (raytraceresult == null) {
			return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);
		} else if (raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK) {
			System.out.println("BLOCK");
			return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);
		} else if (raytraceresult.typeOfHit != RayTraceResult.Type.ENTITY) {

			BlockPos blockpos = raytraceresult.getBlockPos();
			IBlockState iblockstate = worldIn.getBlockState(blockpos);
			Material material = iblockstate.getMaterial();

			if (material == Material.WATER && ((Integer) iblockstate.getValue(BlockLiquid.LEVEL)).intValue() == 0 && worldIn.getBlockState(blockpos.up()).getBlock() == Blocks.AIR) {
				worldIn.setBlockState(blockpos.up(), ModBlocks.waterTorch.getDefaultState(), 11);
				playerIn.addStat(StatList.getObjectUseStats(this));
				playerIn.playSound(SoundEvents.BLOCK_STONE_PLACE, 1.0F, 1.0F);
				if (!worldIn.isRemote) {
					playerIn.getHeldItem(handIn).damageItem(-1, playerIn);
				}
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
			} else {
				return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
			}
		} else {
			System.out.println("false");
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
		}
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (worldIn.getBlockState(pos).getBlock() == ModBlocks.waterTorch || worldIn.getBlockState(pos).getBlock() == Blocks.TORCH || worldIn.getBlockState(pos).getBlock() == ModBlocks.compactedTorch || worldIn.getBlockState(pos).getBlock() == ModBlocks.doublecompactedTorch) {
			if (player.getHeldItem(hand).getMaxDamage() != player.getHeldItem(hand).getItemDamage()) {
				if (worldIn.getBlockState(pos).getBlock() == ModBlocks.waterTorch) {
					if (!worldIn.isRemote && player.getHeldItem(hand).getItemDamage() + 1 < player.getHeldItem(hand).getMaxDamage()) {
						player.getHeldItem(hand).damageItem(1, player);
					}
				} else if (worldIn.getBlockState(pos).getBlock() == Blocks.TORCH) {
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
					if (worldIn.isRemote) {
						worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d1, d2, d3, 0.0D, 0.0D, 0.0D);
					}
				}
			} else {
				if (worldIn.isRemote) {
					player.sendMessage(new TextComponentString(I18n.format(Torchrism.MODID + "." + "torch_staff.over_capacity")));
				}
			}
			return EnumActionResult.SUCCESS;

		} else if (worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos) && player.getHeldItem(hand).getItemDamage() > 0) {

			if (worldIn.getBlockState(pos.down()).isNormalCube()) {
				worldIn.setBlockState(pos, Blocks.TORCH.getDefaultState());
				if (!worldIn.isRemote) {
					player.getHeldItem(hand).damageItem(-1, player);
				}
				return EnumActionResult.SUCCESS;
			}
			return EnumActionResult.FAIL;

		} else if (worldIn.getBlockState(pos).isNormalCube() && player.getHeldItem(hand).getItemDamage() > 0) {
			Material m;
			if (facing == EnumFacing.NORTH) {
				m = worldIn.getBlockState(pos.north()).getMaterial();
			} else if (facing == EnumFacing.SOUTH) {
				m = worldIn.getBlockState(pos.south()).getMaterial();
			} else if (facing == EnumFacing.EAST) {
				m = worldIn.getBlockState(pos.east()).getMaterial();
			} else if (facing == EnumFacing.WEST) {
				m = worldIn.getBlockState(pos.west()).getMaterial();
			} else if (facing == EnumFacing.UP) {
				m = worldIn.getBlockState(pos.up()).getMaterial();
			} else {
				return EnumActionResult.FAIL;
			}

			if (facing == EnumFacing.NORTH && (m == Material.VINE || m == Material.SNOW || m == Material.AIR) && m != Material.WATER) {
				worldIn.setBlockState(pos.north(), Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, facing));
			} else if (facing == EnumFacing.SOUTH && (m == Material.VINE || m == Material.SNOW || m == Material.AIR) && m != Material.WATER) {
				worldIn.setBlockState(pos.south(), Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, facing));
			} else if (facing == EnumFacing.EAST && (m == Material.VINE || m == Material.SNOW || m == Material.AIR) && m != Material.WATER) {
				worldIn.setBlockState(pos.east(), Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, facing));
			} else if (facing == EnumFacing.WEST && (m == Material.VINE || m == Material.SNOW || m == Material.AIR) && m != Material.WATER) {
				worldIn.setBlockState(pos.west(), Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, facing));
			} else if ((m == Material.VINE || m == Material.SNOW || m == Material.AIR) && m != Material.WATER) {
				worldIn.setBlockState(pos.up(), Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, facing));
			} if (facing == EnumFacing.NORTH && m == Material.WATER) {
				worldIn.setBlockState(pos.north(), ModBlocks.waterTorch.getDefaultState().withProperty(BlockWaterTorch.FACING, facing));
			} else if (facing == EnumFacing.SOUTH && m == Material.WATER) {
				worldIn.setBlockState(pos.south(), ModBlocks.waterTorch.getDefaultState().withProperty(BlockWaterTorch.FACING, facing));
			} else if (facing == EnumFacing.EAST && m == Material.WATER) {
				worldIn.setBlockState(pos.east(), ModBlocks.waterTorch.getDefaultState().withProperty(BlockWaterTorch.FACING, facing));
			} else if (facing == EnumFacing.WEST && m == Material.WATER) {
				worldIn.setBlockState(pos.west(), ModBlocks.waterTorch.getDefaultState().withProperty(BlockWaterTorch.FACING, facing));
			} else if (m == Material.WATER) {
				worldIn.setBlockState(pos.up(), ModBlocks.waterTorch.getDefaultState().withProperty(BlockWaterTorch.FACING, facing));
			} else {
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
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format(Torchrism.MODID + "." + "torch_staff.torch_count") + this.getDamage(stack));
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
