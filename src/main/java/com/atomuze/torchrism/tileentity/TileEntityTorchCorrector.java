package com.atomuze.torchrism.tileentity;

import java.util.Random;

import com.atomuze.torchrism.blocks.BlockTorchCorrector;
import com.atomuze.torchrism.config.TorchrismConfig;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

public class TileEntityTorchCorrector extends TileEntity implements ITickableTileEntity {

	private boolean check = false;
	int torchCount = 0;
	BlockPos activatepos = pos;
	Random random = new Random();
	int offset = TorchrismConfig.COMMON.offset.get() + 1;
	int outlay = 1;

	public TileEntityTorchCorrector(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}

	public TileEntityTorchCorrector() {
		this(ModTileEntity.torchCorrector.get());
	}

	@Override
	public void tick() {
		if (!world.isRemote) {
			if (check) {
				if (outlay <= 64) {
					movePos1(outlay);
					outlay++;
				} else if (outlay >= 64 && outlay <= 128) {
					movePos2(outlay - 64);
					outlay++;
				} else {
					check = false;
					outlay = 0;
					if (BlockTorchCorrector.player != null) {
						BlockTorchCorrector.player.addItemStackToInventory(new ItemStack(Blocks.TORCH, torchCount));
					}
					torchCount = 0;
				}
				double d = random.nextFloat();
		        world.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX() + d, pos.getY() + d, pos.getZ() + d, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	private void movePos1(int outLay) {
		for (int i = -outLay; i <= outLay; i++) {
			this.activatepos = new BlockPos(pos.getX() + i, pos.getY(), pos.getZ() + outLay);
			removeTorch();
		}
		for (int i = -outLay; i <= outLay; i++) {
			this.activatepos = new BlockPos(pos.getX() + i, pos.getY(), pos.getZ() - outLay);
			removeTorch();
		}
		for (int i = -outLay; i <= outLay; i++) {
			this.activatepos = new BlockPos(pos.getX() + outLay, pos.getY(), pos.getZ() + i);
			removeTorch();
		}
		for (int i = -outLay; i <= outLay; i++) {
			this.activatepos = new BlockPos(pos.getX() - outLay, pos.getY(), pos.getZ() + i);
			removeTorch();
		}
	}

	private void movePos2(int outLay) {
		for (int i = -outLay; i <= outLay; i++) {
			this.activatepos = new BlockPos(pos.getX() + i, pos.getY(), pos.getZ() + outLay);
			placeTorch();
		}
		for (int i = -outLay; i <= outLay; i++) {
			this.activatepos = new BlockPos(pos.getX() + i, pos.getY(), pos.getZ() - outLay);
			placeTorch();
		}
		for (int i = -outLay; i <= outLay; i++) {
			this.activatepos = new BlockPos(pos.getX() + outLay, pos.getY(), pos.getZ() + i);
			placeTorch();
		}
		for (int i = -outLay; i <= outLay; i++) {
			this.activatepos = new BlockPos(pos.getX() - outLay, pos.getY(), pos.getZ() + i);
			placeTorch();
		}
	}

	public void removeTorch() {
		int placePosX = activatepos.getX();
		int placePosZ = activatepos.getZ();
		for (int placePosY = 255; placePosY >= 0; placePosY--) {
			BlockPos PlacePos = new BlockPos(placePosX, placePosY, placePosZ);

			BlockState iblockstate = world.getBlockState(PlacePos);
			Material m = world.getBlockState(PlacePos).getMaterial();

			if (m == Material.LEAVES) {

			} else if (iblockstate == Blocks.TORCH.getDefaultState()) {

				if ((activatepos.getX() - pos.getX()) % offset == 0
						&& (activatepos.getZ() - pos.getZ()) % offset == 0) {
					break;
				}
				world.setBlockState(PlacePos, Blocks.AIR.getDefaultState());
				torchCount++;
				break;
			} else if (m != Material.AIR) {
				break;
			}
		}
	}

	public void placeTorch() {
		if ((activatepos.getX() - pos.getX()) % offset == 0 && (activatepos.getZ() - pos.getZ()) % offset == 0
				&& torchCount > 0) {
			int placePosX = activatepos.getX();
			int placePosZ = activatepos.getZ();
			for (int placePosY = 255; placePosY >= 0; placePosY--) {
				BlockPos PlacePos = new BlockPos(placePosX, placePosY + 1, placePosZ);

				Material m = world.getBlockState(PlacePos).getMaterial();
				Material mDown = world.getBlockState(PlacePos.down()).getMaterial();
				BlockState state = world.getBlockState(PlacePos.down());

				if (mDown == Material.WATER) {
					break;
				} else if (mDown.isReplaceable() && mDown != Material.AIR) {
					world.setBlockState(PlacePos.down(), Blocks.AIR.getDefaultState());
					continue;
				} else if (state.isSolid() && m == Material.AIR) {
					world.setBlockState(PlacePos, Blocks.TORCH.getDefaultState());
					torchCount--;
					break;
				}
			}
		}
	}

	public void toggleActive() {
		if (this.check) {
			this.check = false;
		} else {
			this.check = true;
		}
	}
}
