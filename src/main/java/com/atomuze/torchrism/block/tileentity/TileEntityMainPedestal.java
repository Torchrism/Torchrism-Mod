package com.atomuze.torchrism.block.tileentity;

import java.util.Random;

import com.atomuze.torchrism.Torchrism;
import com.atomuze.torchrism.block.BlockAltarMainPedestal;
import com.atomuze.torchrism.block.ModBlocks;
import com.atomuze.torchrism.block.altar.AltarBuilder;
import com.atomuze.torchrism.block.altar.CheckAltar;
import com.atomuze.torchrism.block.altar.ModMobSpawnerBaseLogic;
import com.atomuze.torchrism.entity.EntityFlyingTorch;
import com.atomuze.torchrism.network.PacketAltarCraftingParticle;
import com.atomuze.torchrism.network.ModNetworks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.WeightedSpawnerEntity;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.IDataFixer;
import net.minecraft.util.datafix.IDataWalker;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.items.IItemHandler;

public class TileEntityMainPedestal extends TileEntityPedestal implements ITickable {

	long initTime = 0;
	private boolean isDay = true;
	EntityFlyingTorch flyingTorch = new EntityFlyingTorch(world);
	Random ran = new Random();
	public static int altarBuilder = 0;

	
	@Override
	public void update() {
		if (this.pos.equals(BlockAltarMainPedestal.pos)) {
			if(!world.isRemote) {
				if(altarBuilder != 0) {
					AltarBuilder.builder(world, pos, BlockAltarMainPedestal.player, altarBuilder);
					altarBuilder++;
					if(altarBuilder > 11) {
						altarBuilder = 0;
					}
				}
			}
			
			if (BlockAltarMainPedestal.crafting) {
				if (!world.isRemote) {
					ModNetworks.network.sendToAllAround(new PacketAltarCraftingParticle(BlockAltarMainPedestal.crafting), new NetworkRegistry.TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 64));
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

		if (world.getWorldTime() % 24000 < 12000 && !isDay) {
			BlockAltarMainPedestal.setState(true, world, pos);
			isDay = true;
		} else if (world.getWorldTime() % 24000 > 12000 && isDay) {
			BlockAltarMainPedestal.setState(false, world, pos);
			isDay = false;
		}

		CheckAltar check = new CheckAltar(world, pos);
		if (check.checkingAltar(world, pos)) {
			this.spawnerLogic.setEntityId(new ResourceLocation(Torchrism.MODID + ":" + "flying_torch"));
			this.spawnerLogic.updateSpawner();
		}

	}

	public void craftingEffect() {
		extractItem(world, pos);
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

	// spawner
	private final MobSpawnerBaseLogic spawnerLogic = new ModMobSpawnerBaseLogic() {
		public void broadcastEvent(int id) {
			TileEntityMainPedestal.this.world.addBlockEvent(TileEntityMainPedestal.this.pos, ModBlocks.altarMainPedestal, id, 0);
		}

		public World getSpawnerWorld() {
			return TileEntityMainPedestal.this.world;
		}

		public BlockPos getSpawnerPosition() {
			return TileEntityMainPedestal.this.pos.up().up().up();
		}

		public void setNextSpawnData(WeightedSpawnerEntity ent) {
			super.setNextSpawnData(ent);
			if (this.getSpawnerWorld() != null) {
				IBlockState iblockstate = this.getSpawnerWorld().getBlockState(this.getSpawnerPosition());
				this.getSpawnerWorld().notifyBlockUpdate(TileEntityMainPedestal.this.pos, iblockstate, iblockstate, 4);
			}
		}

	};

	public static void registerFixesMobSpawner(DataFixer fixer) {
		fixer.registerWalker(FixTypes.BLOCK_ENTITY, new IDataWalker() {
			public NBTTagCompound process(IDataFixer fixer, NBTTagCompound compound, int versionIn) {
				if (TileEntity.getKey(TileEntityMainPedestal.class).equals(new ResourceLocation(compound.getString("id")))) {
					if (compound.hasKey("SpawnPotentials", 9)) {
						NBTTagList nbttaglist = compound.getTagList("SpawnPotentials", 10);
						for (int i = 0; i < nbttaglist.tagCount(); ++i) {
							NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
							nbttagcompound.setTag("Entity", fixer.process(FixTypes.ENTITY, nbttagcompound.getCompoundTag("Entity"), versionIn));
						}
					}
					compound.setTag("SpawnData", fixer.process(FixTypes.ENTITY, compound.getCompoundTag("SpawnData"), versionIn));
				}
				return compound;
			}
		});
	}
}