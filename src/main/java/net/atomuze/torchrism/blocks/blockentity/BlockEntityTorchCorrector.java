package net.atomuze.torchrism.blocks.blockentity;

import net.atomuze.torchrism.blocks.BlockTorchCorrector;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.Random;

public class BlockEntityTorchCorrector extends BlockEntity implements Tickable  {

	private boolean check = false;
	int torchCount = 0;
	BlockPos activatePos = pos;
	Random random = new Random();
	int offset = 5; // TorchrismConfig.COMMON.offset.get() + 1;
	int outlay = 1;

	public BlockEntityTorchCorrector() {
		super(ModBlockEntity.TORCH_CORRECTOR_BLOCK_ENTITY);
	}

	@Override
	public void tick() {
		if (!world.isClient) {
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
						BlockTorchCorrector.player.giveItemStack(new ItemStack(Blocks.TORCH, torchCount));
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
			this.activatePos = new BlockPos(pos.getX() + i, pos.getY(), pos.getZ() + outLay);
			removeTorch();
		}
		for (int i = -outLay; i <= outLay; i++) {
			this.activatePos = new BlockPos(pos.getX() + i, pos.getY(), pos.getZ() - outLay);
			removeTorch();
		}
		for (int i = -outLay; i <= outLay; i++) {
			this.activatePos = new BlockPos(pos.getX() + outLay, pos.getY(), pos.getZ() + i);
			removeTorch();
		}
		for (int i = -outLay; i <= outLay; i++) {
			this.activatePos = new BlockPos(pos.getX() - outLay, pos.getY(), pos.getZ() + i);
			removeTorch();
		}
	}

	private void movePos2(int outLay) {
		for (int i = -outLay; i <= outLay; i++) {
			this.activatePos = new BlockPos(pos.getX() + i, pos.getY(), pos.getZ() + outLay);
			placeTorch();
		}
		for (int i = -outLay; i <= outLay; i++) {
			this.activatePos = new BlockPos(pos.getX() + i, pos.getY(), pos.getZ() - outLay);
			placeTorch();
		}
		for (int i = -outLay; i <= outLay; i++) {
			this.activatePos = new BlockPos(pos.getX() + outLay, pos.getY(), pos.getZ() + i);
			placeTorch();
		}
		for (int i = -outLay; i <= outLay; i++) {
			this.activatePos = new BlockPos(pos.getX() - outLay, pos.getY(), pos.getZ() + i);
			placeTorch();
		}
	}

	public void removeTorch() {
		int placePosX = activatePos.getX();
		int placePosZ = activatePos.getZ();
		for (int placePosY = 255; placePosY >= 0; placePosY--) {
			BlockPos PlacePos = new BlockPos(placePosX, placePosY, placePosZ);
			BlockState iblockstate = world.getBlockState(PlacePos);
			Material m = world.getBlockState(PlacePos).getMaterial();

			if (m == Material.LEAVES) {

			} else if (iblockstate == Blocks.TORCH.getDefaultState()) {
				if ((activatePos.getX() - pos.getX()) % offset == 0 && (activatePos.getZ() - pos.getZ()) % offset == 0) {
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
		if ((activatePos.getX() - pos.getX()) % offset == 0 && (activatePos.getZ() - pos.getZ()) % offset == 0
				&& torchCount > 0) {
			int placePosX = activatePos.getX();
			int placePosZ = activatePos.getZ();
			for (int placePosY = 255; placePosY >= 0; placePosY--) {
				BlockPos PlacePos = new BlockPos(placePosX, placePosY + 1, placePosZ);

				Material m = world.getBlockState(PlacePos).getMaterial();
				Material mDown = world.getBlockState(PlacePos.down()).getMaterial();

				if (mDown == Material.WATER) {
					break;
				} else if (mDown.isReplaceable() && mDown != Material.AIR) {
					world.setBlockState(PlacePos.down(), Blocks.AIR.getDefaultState());
					continue;
				} else if (Block.sideCoversSmallSquare(world, PlacePos.down(), Direction.UP) && m == Material.AIR) {
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
