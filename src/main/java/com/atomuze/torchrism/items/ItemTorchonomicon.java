package com.atomuze.torchrism.items;

import javax.annotation.Nonnull;

import com.atomuze.torchrism.TorchrismMod;
import com.atomuze.torchrism.gui.ScreenTorchonomicon;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class ItemTorchonomicon extends Item {

	WorldServer worldserver;
	Minecraft minecraft;
	
	public ItemTorchonomicon() {
		super(new Item.Properties().group(TorchrismMod.TAB));
	}

	@Nonnull
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		if(worldIn.isRemote) {
			
			try {
				worldserver.addScheduledTask(() -> minecraft.displayGuiScreen(new ScreenTorchonomicon(new StringTextComponent(I18n.format(TorchrismMod.MODID + "." + "bookTitle.menu")), new StringTextComponent(I18n.format(TorchrismMod.MODID + "." + "bookTitle.menu")), "aaa")));
			}catch(NullPointerException e) {
				System.out.println("null");
			}
			

		}
//		playerIn.playSound(ModSound.ITEM_TORCHONOMICON_OPEN, 1F, (float) (0.7 + Math.random() * 0.4));
		return ActionResult.resultSuccess(itemstack);
	}
}
