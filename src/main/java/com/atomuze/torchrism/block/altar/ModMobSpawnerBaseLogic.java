package com.atomuze.torchrism.block.altar;

import java.util.List;

import javax.annotation.Nullable;

import com.atomuze.torchrism.ModConfig;
import com.google.common.collect.Lists;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.WeightedSpawnerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;

public abstract class ModMobSpawnerBaseLogic extends MobSpawnerBaseLogic {
	/** The delay to spawn. */
	private int spawnDelay = 5;
	/** List of potential entities to spawn */
	private final List<WeightedSpawnerEntity> potentialSpawns = Lists.<WeightedSpawnerEntity>newArrayList();
	private WeightedSpawnerEntity spawnData = new WeightedSpawnerEntity();
	/** The rotation of the mob inside the mob spawner */
	private double mobRotation;
	/** the previous rotation of the mob inside the mob spawner */
	private double prevMobRotation;
	private int minSpawnDelay = 25;
	private int maxSpawnDelay = 45;
	private int spawnCount = 1;
	/** Cached instance of the entity to render inside the spawner. */
	private Entity cachedEntity;
	private int maxNearbyEntities = 3;
	/** The distance from which a player activates the spawner. */
	private int activatingRangeFromPlayer = 6;
	/** The range coefficient for spawning entities around. */
	private int spawnRange = 1;

	@Nullable
	private ResourceLocation getEntityId() {
		String s = this.spawnData.getNbt().getString("id");
		return StringUtils.isNullOrEmpty(s) ? null : new ResourceLocation(s);
	}

	public void setEntityId(@Nullable ResourceLocation id) {
		if (id != null) {
			this.spawnData.getNbt().setString("id", id.toString());
		}
	}

	/**
	 * Returns true if there's a player close enough to this mob spawner to activate
	 * it.
	 */
	private boolean isActivated() {
		BlockPos blockpos = this.getSpawnerPosition();
		return this.getSpawnerWorld().isAnyPlayerWithinRangeAt((double) blockpos.getX() + 0.5D, (double) blockpos.getY() + 0.5D, (double) blockpos.getZ() + 0.5D, (double) this.activatingRangeFromPlayer);
	}

	public void updateSpawner() {
		if (!this.isActivated()) {
			this.prevMobRotation = this.mobRotation;
		} else {
			BlockPos blockpos = this.getSpawnerPosition();

			if (this.getSpawnerWorld().isRemote) {
				double d3 = (double) ((float) blockpos.getX() + 2 + this.getSpawnerWorld().rand.nextFloat());
				double d4 = (double) ((float) blockpos.getY() + this.getSpawnerWorld().rand.nextFloat());
				double d5 = (double) ((float) blockpos.getZ() + 2 + this.getSpawnerWorld().rand.nextFloat());
				this.getSpawnerWorld().spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, d3, d4, d5, 0.0D, 0.0D, 0.0D);

				d3 = (double) ((float) blockpos.getX() + 2 + this.getSpawnerWorld().rand.nextFloat());
				d4 = (double) ((float) blockpos.getY() + this.getSpawnerWorld().rand.nextFloat());
				d5 = (double) ((float) blockpos.getZ() - 2 + this.getSpawnerWorld().rand.nextFloat());
				this.getSpawnerWorld().spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, d3, d4, d5, 0.0D, 0.0D, 0.0D);

				d3 = (double) ((float) blockpos.getX() - 2 + this.getSpawnerWorld().rand.nextFloat());
				d4 = (double) ((float) blockpos.getY() + this.getSpawnerWorld().rand.nextFloat());
				d5 = (double) ((float) blockpos.getZ() - 2 + this.getSpawnerWorld().rand.nextFloat());
				this.getSpawnerWorld().spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, d3, d4, d5, 0.0D, 0.0D, 0.0D);

				d3 = (double) ((float) blockpos.getX() - 2 + this.getSpawnerWorld().rand.nextFloat());
				d4 = (double) ((float) blockpos.getY() + this.getSpawnerWorld().rand.nextFloat());
				d5 = (double) ((float) blockpos.getZ() + 2 + this.getSpawnerWorld().rand.nextFloat());
				this.getSpawnerWorld().spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, d3, d4, d5, 0.0D, 0.0D, 0.0D);

				if (this.spawnDelay > 0) {
					--this.spawnDelay;
				}

				this.prevMobRotation = this.mobRotation;
				this.mobRotation = (this.mobRotation + (double) (1000.0F / ((float) this.spawnDelay + 200.0F))) % 360.0D;
			} else {
				if (this.spawnDelay == -1) {
					this.resetTimer();
				}

				if (this.spawnDelay > 0) {
					--this.spawnDelay;
					return;
				}

				boolean flag = false;

				for (int i = 0; i < this.spawnCount; ++i) {
					NBTTagCompound nbttagcompound = this.spawnData.getNbt();
					NBTTagList nbttaglist = nbttagcompound.getTagList("Pos", 6);
					World world = this.getSpawnerWorld();
					int j = nbttaglist.tagCount();
					double d0 = j >= 1 ? nbttaglist.getDoubleAt(0) : (double) blockpos.getX() + (world.rand.nextDouble() - world.rand.nextDouble()) * (double) this.spawnRange + 0.5D;
					double d1 = j >= 2 ? nbttaglist.getDoubleAt(1) : (double) (blockpos.getY() + world.rand.nextInt(3) - 1);
					double d2 = j >= 3 ? nbttaglist.getDoubleAt(2) : (double) blockpos.getZ() + (world.rand.nextDouble() - world.rand.nextDouble()) * (double) this.spawnRange + 0.5D;
					Entity entity = AnvilChunkLoader.readWorldEntityPos(nbttagcompound, world, d0, d1, d2, false);

					if (entity == null) {
						return;
					}

					int k = world.getEntitiesWithinAABB(entity.getClass(), (new AxisAlignedBB((double) blockpos.getX(), (double) blockpos.getY(), (double) blockpos.getZ(), (double) (blockpos.getX() + 1), (double) (blockpos.getY() + 1), (double) (blockpos.getZ() + 1))).grow((double) this.spawnRange)).size();

					if (k >= this.maxNearbyEntities) {
						this.resetTimer();
						return;
					}

					EntityLiving entityliving = entity instanceof EntityLiving ? (EntityLiving) entity : null;
					entity.setLocationAndAngles(entity.posX, entity.posY, entity.posZ, world.rand.nextFloat() * 360.0F, 0.0F);

					if (entityliving == null || net.minecraftforge.event.ForgeEventFactory.canEntitySpawnSpawner(entityliving, getSpawnerWorld(), (float) entity.posX, (float) entity.posY, (float) entity.posZ, this) && ModConfig.altarSpawnEntity) {
						if (this.spawnData.getNbt().getSize() == 1 && this.spawnData.getNbt().hasKey("id", 8) && entity instanceof EntityLiving) {
							if (!net.minecraftforge.event.ForgeEventFactory.doSpecialSpawn(entityliving, this.getSpawnerWorld(), (float) entity.posX, (float) entity.posY, (float) entity.posZ, this))
								((EntityLiving) entity).onInitialSpawn(world.getDifficultyForLocation(new BlockPos(entity)), (IEntityLivingData) null);
						}

						AnvilChunkLoader.spawnEntity(entity, world);
						world.playEvent(2004, blockpos, 0);

						if (entityliving != null) {
							entityliving.spawnExplosionParticle();
						}

						flag = true;
					}
				}

				if (flag) {
					this.resetTimer();
				}
			}
		}
	}

	private void resetTimer() {
		if (this.maxSpawnDelay <= this.minSpawnDelay) {
			this.spawnDelay = this.minSpawnDelay;
		} else {
			int i = this.maxSpawnDelay - this.minSpawnDelay;
			this.spawnDelay = this.minSpawnDelay + this.getSpawnerWorld().rand.nextInt(i);
		}

		if (!this.potentialSpawns.isEmpty()) {
			this.setNextSpawnData((WeightedSpawnerEntity) WeightedRandom.getRandomItem(this.getSpawnerWorld().rand, this.potentialSpawns));
		}

		this.broadcastEvent(1);
	}

	public abstract void broadcastEvent(int id);

	public abstract World getSpawnerWorld();

	public abstract BlockPos getSpawnerPosition();
}