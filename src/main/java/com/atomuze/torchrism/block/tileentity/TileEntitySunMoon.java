package com.atomuze.torchrism.block.tileentity;

import com.atomuze.torchrism.block.BlockSunMoon;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntitySunMoon extends TileEntity implements ITickable {

	public boolean altarT = true;

	@Override
	public void update() {

		if (world.getWorldTime() % 24000 < 12000 && !altarT) {
			BlockSunMoon.setState(true, world, pos);
			altarT = true;
		} else if (world.getWorldTime() % 24000 > 12000 && altarT) {
			BlockSunMoon.setState(false, world, pos);
			altarT = false;
		}

	}

}