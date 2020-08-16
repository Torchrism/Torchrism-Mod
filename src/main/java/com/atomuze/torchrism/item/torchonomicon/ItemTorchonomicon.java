package com.atomuze.torchrism.item.torchonomicon;

import com.atomuze.torchrism.Torchrism;
import com.atomuze.torchrism.item.ItemBase;
import com.atomuze.torchrism.sound.ModSound;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemTorchonomicon extends ItemBase {

	public ItemTorchonomicon(String name) {
		super(name);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);

		if (worldIn.isRemote) {
			playerIn.openGui(Torchrism.instance, 0, worldIn, (int) playerIn.posX, (int) playerIn.posY, (int) playerIn.posZ);
			playerIn.playSound(ModSound.ITEM_TORCHONOMICON_OPEN, 1F, (float) (0.7 + Math.random() * 0.4));
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	}

}
