package com.atomuze.torchrism.entity.flyingTorch;

import javax.annotation.Nullable;

import com.atomuze.torchrism.entity.ModLootTable;
import com.atomuze.torchrism.sound.ModSound;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityFlyingTorch extends EntityBat {

	public static BlockPos spawnPosition;
	private boolean persistenceRequired = true;

	public EntityFlyingTorch(World worldIn) {
		super(worldIn);
	}

	@Override
	public SoundEvent getAmbientSound() {
		return null;
	}

	@Override
	protected float getSoundVolume() {
		return 2.5F;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return ModSound.ENTITY_FLYINGTORCH_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSound.ENTITY_FLYINGTORCH_DEATH;
	}

	@Override
	protected void updateAITasks() {
		super.updateAITasks();
		
		if (this.spawnPosition == null) {
			this.spawnPosition = new BlockPos((int) this.posX, (int) this.posY , (int) this.posZ);
		}
		
		double d0 = (double) this.spawnPosition.getX() + 0.5D - this.posX;
		double d1 = (double) this.spawnPosition.getY() + 0.1D - this.posY;
		double d2 = (double) this.spawnPosition.getZ() + 0.5D - this.posZ;

		if(Math.sqrt(Math.pow(d0, 2) + Math.pow(d2, 2)) > 32) {
			System.out.println("kill entity : spawn pos" + this.spawnPosition.getX() +  " y" + this.posY  +  "z" + this.spawnPosition.getZ());
			this.onKillCommand();
		}
		
		this.motionX += (Math.signum(d0) * 0.001D);
		this.motionY += (Math.signum(d1) * 0.001D);
		this.motionZ += (Math.signum(d2) * 0.001D);
		float f = (float) (MathHelper.atan2(this.motionZ, this.motionX)) - 90.0F;
		float f1 = MathHelper.wrapDegrees(f - this.rotationYaw) * 0.000001f;
		this.rotationYaw += f1;

	}

	@Override
	public boolean getCanSpawnHere() {
		if(!this.world.isAirBlock(new BlockPos(this.posX, this.posY, this.posZ))) {
			return false;
		}
		this.spawnPosition = new BlockPos(this.posX, this.posY, this.posZ);
//		System.out.println("getCanSpawnHere : spawn pos" + this.posX +  " y" + this.posY  + " z" + this.posZ);
//		System.out.println("getCanSpawnHere");
		return true;

	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
	}

	@Nullable
	protected ResourceLocation getLootTable() {
		return ModLootTable.FLYINGTORCH;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1.0D);
	}
}
