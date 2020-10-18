package net.atomuze.torchrism.items;

import net.atomuze.torchrism.TorchrismMod;
import net.atomuze.torchrism.blocks.ModBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemStaff extends ToolItem {

	public ItemStaff() {
		super(ToolMaterials.WOOD, new Item.Settings().group(TorchrismMod.ITEM_GROUP).maxCount(1).maxDamage(65535).maxDamage(65534));
	}
	
	@Override
	public TypedActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
		
		ItemStack itemstack = playerIn.getStackInHand(handIn);
		HitResult hitResult = raycast(worldIn, playerIn, RaycastContext.FluidHandling.SOURCE_ONLY);
		if (hitResult.getType() == HitResult.Type.MISS) {
			if(playerIn.isSneaking()) {
				for(int i = 0; i <36; i++) {
					if(playerIn.inventory.getStack(i).getItem() == Items.TORCH) {
						if(itemstack.getDamage() + playerIn.inventory.getStack(i).getCount() < 65535) {
							playerIn.getStackInHand(handIn).damage(playerIn.inventory.getStack(i).getCount(), playerIn, null);
						}
						playerIn.inventory.getStack(i).decrement(playerIn.inventory.getStack(i).getCount());
						
					}
				}
			}
			return TypedActionResult.success(itemstack);
			
		} else if (hitResult.getType() != HitResult.Type.BLOCK) {
			return TypedActionResult.pass(itemstack);
		} else {
			BlockHitResult blockHitResult = (BlockHitResult) hitResult;
			Direction direction = blockHitResult.getSide();
			BlockPos blockpos = blockHitResult.getBlockPos();
			BlockPos blockpos1 = blockpos.offset(direction);
			BlockState blockstate = worldIn.getBlockState(blockpos);
			Material material = blockstate.getMaterial();
			
			if(material == Material.WATER && worldIn.getBlockState(blockpos1).getBlock() == Blocks.AIR && itemstack.getDamage() > 0) {
				worldIn.setBlockState(blockpos1, ModBlocks.WATER_TORCH.getDefaultState(), 11);
				playerIn.playSound(SoundEvents.BLOCK_WOOD_PLACE, 1.0F, 1.0F);
				if (!worldIn.isClient) {
					playerIn.getStackInHand(handIn).damage(-1, playerIn, null);
				}
				return TypedActionResult.success(itemstack);
			} else if (material.isReplaceable() && itemstack.getDamage() > 0) {

				if (Block.sideCoversSmallSquare(worldIn, blockpos.down(), Direction.UP)) {
					worldIn.setBlockState(blockpos, Blocks.TORCH.getDefaultState());
					playerIn.playSound(SoundEvents.BLOCK_WOOD_PLACE, 1.0F, 1.0F);
					if (!worldIn.isClient) {
						playerIn.getStackInHand(handIn).damage(-1, playerIn, null);
					}
					return TypedActionResult.success(itemstack);
				}
				return TypedActionResult.pass(itemstack);

			}else if(Block.sideCoversSmallSquare(worldIn, blockpos.down(), direction) && worldIn.getBlockState(blockpos1).getBlock() == Blocks.AIR && itemstack.getDamage() > 0){
				if(direction == Direction.DOWN) {
					return TypedActionResult.pass(itemstack);
				}else if(direction != Direction.UP) {
					worldIn.setBlockState(blockpos1, Blocks.WALL_TORCH.getDefaultState().with(HorizontalFacingBlock.FACING, direction), 11);
				}else {
					worldIn.setBlockState(blockpos1, Blocks.TORCH.getDefaultState());
				}
				
				playerIn.playSound(SoundEvents.BLOCK_WOOD_PLACE, 1.0F, 1.0F);
				if (!worldIn.isClient) {
					playerIn.getStackInHand(handIn).damage(-1, playerIn, null);
				}
				return TypedActionResult.success(itemstack);
			}else if(itemstack.getDamage() <= 0) {
				if (worldIn.isClient) {
					playerIn.sendMessage(Text.of(I18n.translate(TorchrismMod.MOD_ID + "." + "torch_staff.no_enough_item")),false);
				}
				return TypedActionResult.pass(itemstack);
			}else {
				return TypedActionResult.pass(itemstack);
			}
		}
	}

	public ActionResult useOnBlock(ItemUsageContext context) {
		Direction direction = context.getSide();
	    World world = context.getWorld();
	    BlockPos blockpos = context.getBlockPos();
	    BlockPos blockpos1 = blockpos.offset(direction);
	    BlockState blockstate = world.getBlockState(blockpos);
	    PlayerEntity player = context.getPlayer();
	    ItemStack item = context.getStack();
	    Hand hand = context.getHand();
	    
	    if(player.isSneaking() && item.getDamage() > 0 && (blockstate.getBlock() == ModBlocks.WATER_TORCH || blockstate.getBlock() == Blocks.TORCH)) {
	    	world.spawnEntity(new ItemEntity(world, blockpos.getX(), blockpos.getY(), blockpos.getZ(), Items.TORCH.getDefaultStack()));
	    	player.getStackInHand(hand).damage(-1, player, null);    	
	    	return ActionResult.SUCCESS;
	    }else if(blockstate.getBlock() == ModBlocks.WATER_TORCH
	    	|| blockstate.getBlock() == ModBlocks.WALL_WATER_TORCH
	    	|| blockstate.getBlock() == Blocks.TORCH
	    	|| blockstate.getBlock() == Blocks.WALL_TORCH
	    	|| blockstate.getBlock() == ModBlocks.COMPACTED_TORCH
	    	|| blockstate.getBlock() == ModBlocks.DOUBLE_COMPACTED_TORCH
	    	&& item.getDamage() < 65535) {
	    	if(blockstate.getBlock() == ModBlocks.DOUBLE_COMPACTED_TORCH) {
	    		player.getStackInHand(hand).damage(64, player, null);
	    	}else if(blockstate.getBlock() == ModBlocks.COMPACTED_TORCH) {
	    		player.getStackInHand(hand).damage(8, player, null);
	    	}else {
	    		player.getStackInHand(hand).damage(1, player, null);
	    	}
	    	world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 11);
	    	return ActionResult.SUCCESS;
	    }else if(direction == Direction.DOWN) {
			return ActionResult.FAIL;
		}else if(item.getDamage() > 0 && Block.sideCoversSmallSquare(world, blockpos.down(), direction) && world.getBlockState(blockpos1).getBlock() == Blocks.WATER) {

//	    	if(direction == Direction.UP){
//				world.setBlockState(blockpos1, ModBlocks.WATER_TORCH.getDefaultState(), 11);
//			}else if(direction != Direction.DOWN){
//				world.setBlockState(blockpos1, ModBlocks.WALL_WATER_TORCH.getDefaultState().with(HorizontalFacingBlock.FACING, direction), 11);
//			}
//			player.playSound(SoundEvents.BLOCK_WOOD_PLACE, 1.0F, 1.0F);
//			if (!world.isClient) {
//	    		player.getStackInHand(hand).damage(-1, player, null);
//			}
			return ActionResult.PASS;
		}
		return ActionResult.PASS;
	}

	@Environment(EnvType.CLIENT)
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		tooltip.add(Text.of(I18n.translate(TorchrismMod.MOD_ID + "." + "torch_staff.torch_count")  ));
	}
	
//	@Override
//	public boolean showDurabilityBar(ItemStack stack) {
//		return false;
//	}
	
}
