package com.atomuze.torchrism;

import com.atomuze.torchrism.item.torchonomicon.GuiTorchonomicon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class ModGuiHandler implements IGuiHandler {
	public static final int TORCHONOMICON = 0;
	
	@Override
	public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
			case TORCHONOMICON:
				return new GuiTorchonomicon();
			default:
				return null;
		}
	}
}