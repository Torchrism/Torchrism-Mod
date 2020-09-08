package com.atomuze.torchrism.item;

import com.atomuze.torchrism.block.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ItemWaterTorch extends ItemBlock {

	Block block;

	public ItemWaterTorch(Block block) {
		super(block);
		this.block = block;
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		boolean flag = this.block == Blocks.AIR;
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, true);

		if (raytraceresult == null) {
			return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);
		} else if (raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK) {
			return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);
		} else if (raytraceresult.typeOfHit != RayTraceResult.Type.ENTITY) {

			BlockPos blockpos = raytraceresult.getBlockPos();
			IBlockState iblockstate = worldIn.getBlockState(blockpos);
			Material material = iblockstate.getMaterial();

			if (material == Material.WATER && ((Integer) iblockstate.getValue(BlockLiquid.LEVEL)).intValue() == 0 && worldIn.getBlockState(blockpos.up()).getBlock() == Blocks.AIR) {
				worldIn.setBlockState(blockpos.up(), ModBlocks.waterTorch.getDefaultState(), 11);
				playerIn.addStat(StatList.getObjectUseStats(this));
				playerIn.playSound(SoundEvents.BLOCK_STONE_PLACE, 1.0F, 1.0F);
				playerIn.getHeldItem(handIn).shrink(1);
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
			} else {
				return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
			}
		} else {
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
		}
	}
}