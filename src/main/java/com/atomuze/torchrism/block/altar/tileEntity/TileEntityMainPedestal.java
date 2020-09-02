package com.atomuze.torchrism.block.altar.tileEntity;

import java.util.Random;

import com.atomuze.torchrism.block.altar.block.BlockAltarMainPedestal;
import com.atomuze.torchrism.entity.flyingTorch.EntityFlyingTorch;
import com.atomuze.torchrism.network.AltarCraftingParticlePacket;
import com.atomuze.torchrism.network.ModNetworks;

import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.items.IItemHandler;

public class TileEntityMainPedestal extends TileEntityPedestal implements ITickable {

	long initTime = 0;
	private boolean altarT = true;
	EntityFlyingTorch flyingTorch = new EntityFlyingTorch(world);
	Random ran = new Random();

	@Override
	public void update() {
		if (this.pos.equals(BlockAltarMainPedestal.pos)) {
			if (BlockAltarMainPedestal.crafting) {
				if (!world.isRemote) {
					ModNetworks.network.sendToAllAround(new AltarCraftingParticlePacket(BlockAltarMainPedestal.crafting), new NetworkRegistry.TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 64));
				}
				long processingTime = world.getTotalWorldTime() - initTime;
				this.spawnParticles1(world, pos);
				if (processingTime > 60.0 && processingTime < 80.0) {
					this.spawnParticles1(world, pos);
					this.spawnParticles2(world, pos);
				} else if (processingTime > 80.0) {
					BlockAltarMainPedestal.crafting = false;
					craftingEffect();
					BlockAltarMainPedestal.stackResult = null;
				}
			} else {
				initTime = world.getTotalWorldTime();
			}
		} else {
			initTime = world.getTotalWorldTime();
		}

//		System.out.println("BlockMainPedestal.crafting "+ BlockMainPedestal.crafting);

		if (world.getWorldTime() % 24000 < 12000 && !altarT) {
			BlockAltarMainPedestal.setState(true, world, pos);
			altarT = true;
		} else if (world.getWorldTime() % 24000 > 12000 && altarT) {
			BlockAltarMainPedestal.setState(false, world, pos);
			altarT = false;
		}
// 		this.spawnerLogic.updateSpawner();
	}

	public void craftingEffect() {
		extractItem(world, pos);
		extractItem(world, pos.down().north().north().west().west().up().up());
		extractItem(world, pos.down().north().north().east().east().up().up());
		extractItem(world, pos.down().south().south().west().west().up().up());
		extractItem(world, pos.down().south().south().east().east().up().up());
		extractItem(world, pos.down().down().north().north().north().north().north().up());
		extractItem(world, pos.down().down().west().west().west().west().west().up());
		extractItem(world, pos.down().down().east().east().east().east().east().up());
		extractItem(world, pos.down().down().south().south().south().south().south().up());
		extractItem(world, pos.north().north().north().north().east().east().east().east());
		extractItem(world, pos.north().north().north().north().west().west().west().west());
		extractItem(world, pos.south().south().south().south().east().east().east().east());
		extractItem(world, pos.south().south().south().south().west().west().west().west());

		if (!world.isRemote) {
			world.spawnEntity(BlockAltarMainPedestal.craftingResult);
		}

	}

	public void extractItem(World world, BlockPos pos) {
		TileEntityPedestal tile = (TileEntityPedestal) world.getTileEntity(pos);
		IItemHandler itemHandler = tile.inventory;
		itemHandler.extractItem(0, 1, false);
		tile.markDirty();
	}

	private void spawnParticles1(World worldIn, BlockPos pos) {
		Random random = worldIn.rand;

		for (int i = -4; i < 4; i++) {
			double d1 = (double) ((float) pos.getX() + random.nextFloat());
			double d2 = (double) ((float) pos.getY() + random.nextFloat());
			double d3 = (double) ((float) pos.getZ() + random.nextFloat());

			double posX = 2.0D * ((double) i / 3.0D);
			double posZ = 2.0D * ((double) i / 3.0D);
			worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, d1 + posX, d2 + 2, d3 + posZ, 0.0D, 0.0D, 0.0D);

		}

		for (int i = -4; i < 4; i++) {
			double d1 = (double) ((float) pos.getX() + random.nextFloat());
			double d2 = (double) ((float) pos.getY() + random.nextFloat());
			double d3 = (double) ((float) pos.getZ() + random.nextFloat());

			double posX = 2.0D * ((double) i / 3.0D);
			double posZ = 2.0D * ((double) i / 3.0D);
			worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, d1 + posX, d2 + 2, d3 - posZ, 0.0D, 0.0D, 0.0D);

		}

	}

	private void spawnParticles2(World worldIn, BlockPos pos) {
		Random random = worldIn.rand;

		for (int i = 0; i < 6; ++i) {
			double d1 = (double) ((float) pos.getX() + random.nextFloat());
			double d2 = (double) ((float) pos.getY() + random.nextFloat());
			double d3 = (double) ((float) pos.getZ() + random.nextFloat());
			worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, d1, d2 + 1, d3, 0.0D, 0.0D, 0.0D);
		}

	}
//
//	//spawner
//	private final MobSpawnerBaseLogic spawnerLogic = new MobSpawnerBaseLogic() {
//		public void broadcastEvent(int id) {
//			TileEntityMainPedestal.this.world.addBlockEvent(TileEntityMainPedestal.this.pos, ModBlocks.altarMainPedestal, ModConfig.flyingTorchId, 0);
//		}
//
//		public World getSpawnerWorld() {
//			return TileEntityMainPedestal.this.world;
//		}
//
//		public BlockPos getSpawnerPosition() {
//			return TileEntityMainPedestal.this.pos.up().up().up();
//		}
//
//		public void setNextSpawnData(WeightedSpawnerEntity ent) {
//			super.setNextSpawnData(ent);
//
//			if (this.getSpawnerWorld() != null) {
//				IBlockState iblockstate = this.getSpawnerWorld().getBlockState(this.getSpawnerPosition());
//				this.getSpawnerWorld().notifyBlockUpdate(TileEntityMainPedestal.this.pos, iblockstate, iblockstate, 4);
//			}
//		}
//	};
//
//	public static void registerFixesMobSpawner(DataFixer fixer) {
//		fixer.registerWalker(FixTypes.BLOCK_ENTITY, new IDataWalker() {
//			public NBTTagCompound process(IDataFixer fixer, NBTTagCompound compound, int versionIn) {
//				if (TileEntity.getKey(TileEntityMainPedestal.class).equals(new ResourceLocation(compound.getString("id")))) {
//					if (compound.hasKey("SpawnPotentials", 9)) {
//						NBTTagList nbttaglist = compound.getTagList("SpawnPotentials", 10);
//
//						for (int i = 0; i < nbttaglist.tagCount(); ++i) {
//							NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
//							nbttagcompound.setTag("Entity", fixer.process(FixTypes.ENTITY, nbttagcompound.getCompoundTag("Entity"), versionIn));
//						}
//					}
//
//					compound.setTag("SpawnData", fixer.process(FixTypes.ENTITY, compound.getCompoundTag("SpawnData"), versionIn));
//				}
//
//				return compound;
//			}
//		});
//	}
//
//	public void readFromNBT(NBTTagCompound compound) {
//		super.readFromNBT(compound);
//		this.spawnerLogic.readFromNBT(compound);
//	}
//
//	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
//		super.writeToNBT(compound);
//		this.spawnerLogic.writeToNBT(compound);
//		return compound;
//	}
//
//	@Nullable
//	public SPacketUpdateTileEntity getUpdatePacket() {
//		return new SPacketUpdateTileEntity(this.pos, 1, this.getUpdateTag());
//	}
//
//	public NBTTagCompound getUpdateTag() {
//		NBTTagCompound nbttagcompound = this.writeToNBT(new NBTTagCompound());
//		nbttagcompound.removeTag("SpawnPotentials");
//		return nbttagcompound;
//	}
//
//	public boolean receiveClientEvent(int id, int type) {
//		return this.spawnerLogic.setDelayToMin(id) ? true : super.receiveClientEvent(id, type);
//	}
//
//	public boolean onlyOpsCanSetNbt() {
//		return true;
//	}
//
//	public MobSpawnerBaseLogic getSpawnerBaseLogic() {
//		return this.spawnerLogic;
//	}
}