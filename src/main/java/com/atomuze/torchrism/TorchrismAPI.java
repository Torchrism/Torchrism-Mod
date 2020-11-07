package com.atomuze.torchrism;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class TorchrismAPI {
	public static BlockPos posOffset(BlockPos pos ,EnumFacing facing) {
		if (facing == EnumFacing.NORTH) {
			return pos.north();
		} else if (facing == EnumFacing.SOUTH) {
			return pos.south();
		} else if (facing == EnumFacing.EAST) {
			return pos.east();
		} else if (facing == EnumFacing.WEST) {
			return pos.west();
		} else if (facing == EnumFacing.UP) {
			return pos.up();
		} else {
			return pos;
		}
	}
}
