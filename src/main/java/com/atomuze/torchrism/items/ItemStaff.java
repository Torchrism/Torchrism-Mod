package com.atomuze.torchrism.items;

import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.Nullable;

import com.atomuze.torchrism.TorchrismMod;
import com.atomuze.torchrism.blocks.ModBlocks;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.item.ToolItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemStaff extends ToolItem {
	
	Random random = new Random();
	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.AIR);

	public ItemStaff() {
		super(1, 1, ItemTier.WOOD, EFFECTIVE_ON, new Item.Properties().group(TorchrismMod.TAB).maxStackSize(1).maxDamage(65535));
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		RayTraceResult raytraceresult = rayTrace(worldIn, playerIn, RayTraceContext.FluidMode.SOURCE_ONLY);
		if (raytraceresult.getType() == RayTraceResult.Type.MISS) {
			
			if(playerIn.isSneaking()) {
				for(int i = 0; i <36; i++) {
					if(playerIn.inventory.getStackInSlot(i).getItem() == Items.TORCH) {
						if(itemstack.getDamage() + playerIn.inventory.getStackInSlot(i).getCount() < 65535) {
							playerIn.getHeldItem(handIn).damageItem(playerIn.inventory.getStackInSlot(i).getCount(), playerIn, null);
						}
						playerIn.inventory.getStackInSlot(i).shrink(playerIn.inventory.getStackInSlot(i).getCount());
						
					}
				}
			}
			return ActionResult.resultSuccess(itemstack);
			
		} else if (raytraceresult.getType() != RayTraceResult.Type.BLOCK) {
			return ActionResult.resultPass(itemstack);
		} else {
			BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult) raytraceresult;
			Direction direction = blockraytraceresult.getFace();
			BlockPos blockpos = blockraytraceresult.getPos();
			BlockPos blockpos1 = blockpos.offset(direction);
			BlockState blockstate = worldIn.getBlockState(blockpos);
			Material material = blockstate.getMaterial();
			
			if(material == Material.WATER && worldIn.getBlockState(blockpos1).getBlock() == Blocks.AIR && itemstack.getDamage() > 0) {
				worldIn.setBlockState(blockpos1, ModBlocks.waterTorch.get().getDefaultState(), 11);
				playerIn.playSound(SoundEvents.BLOCK_WOOD_PLACE, 1.0F, 1.0F);
				if (!worldIn.isRemote) {
					playerIn.getHeldItem(handIn).damageItem(-1, playerIn, null);
				}
				return ActionResult.resultSuccess(itemstack);
			} else if (material.isReplaceable() && itemstack.getDamage() > 0) {

				if (worldIn.getBlockState(blockpos.down()).isSolid()) {
					worldIn.setBlockState(blockpos, Blocks.TORCH.getDefaultState());
					playerIn.playSound(SoundEvents.BLOCK_WOOD_PLACE, 1.0F, 1.0F);
					if (!worldIn.isRemote) {
						playerIn.getHeldItem(handIn).damageItem(-1, playerIn, null);
					}
					return ActionResult.resultSuccess(itemstack);
				}
				return ActionResult.resultPass(itemstack);

			}else if(blockstate.isSolid() && worldIn.getBlockState(blockpos1).getBlock() == Blocks.AIR && itemstack.getDamage() > 0){
				if(direction == Direction.DOWN) {
					return ActionResult.resultPass(itemstack);
				}else if(direction != Direction.UP) {
					worldIn.setBlockState(blockpos1, Blocks.WALL_TORCH.getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, direction), 11); 
				}else {
					worldIn.setBlockState(blockpos1, Blocks.TORCH.getDefaultState());
				}
				
				playerIn.playSound(SoundEvents.BLOCK_WOOD_PLACE, 1.0F, 1.0F);
				if (!worldIn.isRemote) {
					playerIn.getHeldItem(handIn).damageItem(-1, playerIn, null);
				}
				return ActionResult.resultSuccess(itemstack);
			}else if(itemstack.getDamage() <= 0) {
				if (worldIn.isRemote) {
					playerIn.sendMessage(new StringTextComponent(I18n.format(TorchrismMod.MODID + "." + "torch_staff.no_enough_item")));
				}
				return ActionResult.resultPass(itemstack);
			}else {
				return ActionResult.resultPass(itemstack);
			}
		}
	}

	public ActionResultType onItemUse(ItemUseContext context) {
		Direction direction = context.getFace();
	    World world = context.getWorld();
	    BlockPos blockpos = context.getPos();
	    BlockPos blockpos1 = blockpos.offset(direction);
	    BlockState blockstate = world.getBlockState(blockpos);
	    PlayerEntity player = context.getPlayer();
	    ItemStack item = context.getItem();
	    Hand hand = context.getHand();
	    
	    if(player.isSneaking() && item.getDamage() > 0 && (blockstate.getBlock() == ModBlocks.waterTorch.get() || blockstate.getBlock() == Blocks.TORCH)) {
	    	world.addEntity(new ItemEntity(world, blockpos.getX(), blockpos.getY(), blockpos.getZ(), Items.TORCH.getDefaultInstance()));
	    	player.getHeldItem(hand).damageItem(-1, player, null);    	
	    	return ActionResultType.SUCCESS;
	    }else if(blockstate.getBlock() == ModBlocks.waterTorch.get()
	    	|| blockstate.getBlock() == ModBlocks.wallWaterTorch.get()
	    	|| blockstate.getBlock() == Blocks.TORCH
	    	|| blockstate.getBlock() == Blocks.WALL_TORCH
	    	|| blockstate.getBlock() == ModBlocks.compactedTorch.get()
	    	|| blockstate.getBlock() == ModBlocks.doubleCompactedTorch.get()	
	    	&& item.getDamage() < 65535) {
	    	if(blockstate.getBlock() == ModBlocks.doubleCompactedTorch.get()) {
	    		player.getHeldItem(hand).damageItem(64, player, null);
	    	}else if(blockstate.getBlock() == ModBlocks.compactedTorch.get()) {
	    		player.getHeldItem(hand).damageItem(8, player, null);
	    	}else {
	    		player.getHeldItem(hand).damageItem(1, player, null);
	    	}
	    	world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 11);
	    	return ActionResultType.SUCCESS;
	    }else if(direction == Direction.DOWN) {
			return ActionResultType.FAIL;
		}else if(item.getDamage() > 0 && blockstate.isSolid() && world.getBlockState(blockpos1).getBlock() == Blocks.WATER && direction == Direction.UP) {
			world.setBlockState(blockpos1, ModBlocks.waterTorch.get().getDefaultState(), 11);
			player.playSound(SoundEvents.BLOCK_WOOD_PLACE, 1.0F, 1.0F);
			if (!world.isRemote) {
	    		player.getHeldItem(hand).damageItem(-1, player, null);
			}
			return ActionResultType.SUCCESS;
		}else if(item.getDamage() > 0 && blockstate.isSolid() && world.getBlockState(blockpos1).getBlock() == Blocks.WATER) {
			world.setBlockState(blockpos1, ModBlocks.wallWaterTorch.get().getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, direction), 11); 
			player.playSound(SoundEvents.BLOCK_WOOD_PLACE, 1.0F, 1.0F);
			if (!world.isRemote) {
	    		player.getHeldItem(hand).damageItem(-1, player, null);
			}
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.PASS;
	}
	
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new StringTextComponent  (I18n.format(TorchrismMod.MODID + "." + "torch_staff.torch_count") + this.getDamage(stack)));
	}
	
	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return false;
	}
	
}
