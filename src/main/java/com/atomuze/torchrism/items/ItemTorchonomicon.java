package com.atomuze.torchrism.items;

import com.atomuze.torchrism.TorchrismMod;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ItemTorchonomicon extends Item {

	public ItemTorchonomicon() {
		super(new Item.Properties().group(TorchrismMod.TAB));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);

//		playerIn.playSound(ModSound.ITEM_TORCHONOMICON_OPEN, 1F, (float) (0.7 + Math.random() * 0.4));
		
		return ActionResult.resultSuccess(itemstack);
	}
	

}
