package com.atomuze.torchrism.items;

import java.util.Random;

import com.atomuze.torchrism.TorchrismMod;
import com.atomuze.torchrism.blocks.ModBlocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ItemStaff extends Item {
	
	Random random = new Random();
	
	public ItemStaff() {
		super(new Item.Properties().group(TorchrismMod.TAB).maxStackSize(1).defaultMaxDamage(65535));
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		System.out.println("ac");
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		RayTraceResult raytraceresult = rayTrace(worldIn, playerIn, RayTraceContext.FluidMode.SOURCE_ONLY);

		if (raytraceresult.getType() == RayTraceResult.Type.MISS) {
			return ActionResult.resultPass(itemstack);
		} else if (raytraceresult.getType() != RayTraceResult.Type.BLOCK) {
			return ActionResult.resultPass(itemstack);
		} else {
			BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult) raytraceresult;
			Direction direction = blockraytraceresult.getFace();
			BlockPos blockpos = blockraytraceresult.getPos();
			BlockPos blockpos1 = blockpos.offset(direction);
			BlockState blockstate = worldIn.getBlockState(blockpos);
			Material material = blockstate.getMaterial();
			
			if(material == Material.WATER && worldIn.getBlockState(blockpos.up()).getBlock() == Blocks.AIR) {
				worldIn.setBlockState(blockpos.up(), ModBlocks.waterTorch.get().getDefaultState(), 11);
				playerIn.playSound(SoundEvents.BLOCK_WOOD_PLACE, 1.0F, 1.0F);
				if (!worldIn.isRemote) {
					playerIn.getHeldItem(handIn).setDamage(-1);
				}
				return ActionResult.resultSuccess(itemstack);
			}else if(blockstate.isSolid() && worldIn.getBlockState(blockpos1).getBlock() == Blocks.AIR){
				if(direction != Direction.UP) {
					worldIn.setBlockState(blockpos1, Blocks.WALL_TORCH.getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, direction), 11);
				}else if(direction != Direction.DOWN) {
					return ActionResult.resultPass(itemstack);
				}else {
					worldIn.setBlockState(blockpos1, Blocks.TORCH.getDefaultState());
				}
				
				playerIn.playSound(SoundEvents.BLOCK_WOOD_PLACE, 1.0F, 1.0F);
				if (!worldIn.isRemote) {
					playerIn.getHeldItem(handIn).setDamage(-1);
				}
				return ActionResult.resultSuccess(itemstack);
			}else {
				return ActionResult.resultPass(itemstack);
			}
		}
	}
//
//	@Override
//	 public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) { 
//		if (worldIn.getBlockState(pos).getBlock() == ModBlocks.waterTorch || worldIn.getBlockState(pos).getBlock() == Blocks.TORCH || worldIn.getBlockState(pos).getBlock() == ModBlocks.compactedTorch || worldIn.getBlockState(pos).getBlock() == ModBlocks.doublecompactedTorch) {
//			if (player.getHeldItem(hand).getMaxDamage() != player.getHeldItem(hand).getItemDamage()) {
//				if (worldIn.getBlockState(pos).getBlock() == ModBlocks.waterTorch) {
//					if (!worldIn.isRemote && player.getHeldItem(hand).getItemDamage() + 1 < player.getHeldItem(hand).getMaxDamage()) {
//						player.getHeldItem(hand).damageItem(1, player);
//					}
//				} else if (worldIn.getBlockState(pos).getBlock() == Blocks.TORCH) {
//					if (!worldIn.isRemote && player.getHeldItem(hand).getItemDamage() + 1 < player.getHeldItem(hand).getMaxDamage()) {
//						player.getHeldItem(hand).damageItem(1, player);
//					}
//				} else if (worldIn.getBlockState(pos).getBlock() == ModBlocks.compactedTorch) {
//					if (!worldIn.isRemote && player.getHeldItem(hand).getItemDamage() + 8 < player.getHeldItem(hand).getMaxDamage()) {
//						player.getHeldItem(hand).damageItem(8, player);
//					}
//				} else if (worldIn.getBlockState(pos).getBlock() == ModBlocks.doublecompactedTorch) {
//					if (!worldIn.isRemote && player.getHeldItem(hand).getItemDamage() + 64 < player.getHeldItem(hand).getMaxDamage()) {
//						player.getHeldItem(hand).damageItem(64, player);
//					}
//				}
//
//				worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
//				for (int i = 0; i < 6; ++i) {
//					double d1 = (double) ((float) pos.getX() + random.nextFloat());
//					double d2 = (double) ((float) pos.getY() + random.nextFloat());
//					double d3 = (double) ((float) pos.getZ() + random.nextFloat());
//					if (worldIn.isRemote) {
//						worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d1, d2, d3, 0.0D, 0.0D, 0.0D);
//					}
//				}
//			} else {
//				if (worldIn.isRemote) {
//					player.sendMessage(new TextComponentString(I18n.format(Torchrism.MODID + "." + "torch_staff.over_capacity")));
//				}
//			}
//			return ActionResult.SUCCESS;
//
//		} else if (worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos) && player.getHeldItem(hand).getItemDamage() > 0) {
//
//			if (worldIn.getBlockState(pos.down()).isNormalCube()) {
//				worldIn.setBlockState(pos, Blocks.TORCH.getDefaultState());
//				if (!worldIn.isRemote) {
//					player.getHeldItem(hand).damageItem(-1, player);
//				}
//				return ActionResult.SUCCESS;
//			}
//			return ActionResult.FAIL;
//
//		} else if (worldIn.getBlockState(pos).isNormalCube() && player.getHeldItem(hand).getItemDamage() > 0) {
//			Material m;
//			
//			if (!worldIn.isRemote) {
//				player.getHeldItem(hand).damageItem(-1, player);
//			}
//			player.playSound(SoundEvents.BLOCK_WOOD_PLACE, 1.0F, 1.0F);
//			if (facing == EnumFacing.NORTH) {
//				m = worldIn.getBlockState(pos.north()).getMaterial();
//			} else if (facing == EnumFacing.SOUTH) {
//				m = worldIn.getBlockState(pos.south()).getMaterial();
//			} else if (facing == EnumFacing.EAST) {
//				m = worldIn.getBlockState(pos.east()).getMaterial();
//			} else if (facing == EnumFacing.WEST) {
//				m = worldIn.getBlockState(pos.west()).getMaterial();
//			} else if (facing == EnumFacing.UP) {
//				m = worldIn.getBlockState(pos.up()).getMaterial();
//			} else {
//				return ActionResult.FAIL;
//			}
//
//			if (facing == EnumFacing.NORTH && (m == Material.VINE || m == Material.SNOW || m == Material.AIR) && m != Material.WATER) {
//				worldIn.setBlockState(pos.north(), Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, facing));
//			} else if (facing == EnumFacing.SOUTH && (m == Material.VINE || m == Material.SNOW || m == Material.AIR) && m != Material.WATER) {
//				worldIn.setBlockState(pos.south(), Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, facing));
//			} else if (facing == EnumFacing.EAST && (m == Material.VINE || m == Material.SNOW || m == Material.AIR) && m != Material.WATER) {
//				worldIn.setBlockState(pos.east(), Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, facing));
//			} else if (facing == EnumFacing.WEST && (m == Material.VINE || m == Material.SNOW || m == Material.AIR) && m != Material.WATER) {
//				worldIn.setBlockState(pos.west(), Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, facing));
//			} else if ((m == Material.VINE || m == Material.SNOW || m == Material.AIR) && m != Material.WATER) {
//				worldIn.setBlockState(pos.up(), Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, facing));
//			} if (facing == EnumFacing.NORTH && m == Material.WATER) {
//				worldIn.setBlockState(pos.north(), ModBlocks.waterTorch.getDefaultState().withProperty(BlockWaterTorch.FACING, facing));
//			} else if (facing == EnumFacing.SOUTH && m == Material.WATER) {
//				worldIn.setBlockState(pos.south(), ModBlocks.waterTorch.getDefaultState().withProperty(BlockWaterTorch.FACING, facing));
//			} else if (facing == EnumFacing.EAST && m == Material.WATER) {
//				worldIn.setBlockState(pos.east(), ModBlocks.waterTorch.getDefaultState().withProperty(BlockWaterTorch.FACING, facing));
//			} else if (facing == EnumFacing.WEST && m == Material.WATER) {
//				worldIn.setBlockState(pos.west(), ModBlocks.waterTorch.getDefaultState().withProperty(BlockWaterTorch.FACING, facing));
//			} else if (m == Material.WATER) {
//				worldIn.setBlockState(pos.up(), ModBlocks.waterTorch.getDefaultState().withProperty(BlockWaterTorch.FACING, facing));
//			} else {
//				return ActionResult.FAIL;
//			}
//
//			return ActionResult.SUCCESS;
//		} else {
//			if (player.getHeldItem(hand).getItemDamage() <= 0) {
//				if (worldIn.isRemote)
//					player.sendMessage(new TextComponentString(I18n.format(Torchrism.MODID + "." + "torch_staff.no_enough_item")));
//			}
//			return ActionResult.FAIL;
//		}
//	}
//
//	@Override
//	@SideOnly(Side.CLIENT)
//	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
//		tooltip.add(I18n.format(Torchrism.MODID + "." + "torch_staff.torch_count") + this.getDamage(stack));
//	}
//
//	@Override
//	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
//		return true;
//	}
//
//	@Override
//	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
//		return true;
//	}
//
//	@Override
//	public float getDestroySpeed(ItemStack stack, IBlockState state) {
//		return 1.0F;
//	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return false;
	}
	
}
