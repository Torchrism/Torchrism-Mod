package com.atomuze.torchrism.entity.flyingTorch;

import javax.annotation.Nullable;

import com.atomuze.torchrism.entity.ModLootTable;
import com.atomuze.torchrism.sound.ModSound;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityFlyingTorch extends EntityBat {
	
	private BlockPos spawnPosition;
	private boolean persistenceRequired = true;
	
	public EntityFlyingTorch(World worldIn) {
		super(worldIn);
		this.persistenceRequired = true;
	}

	@Override
	public SoundEvent getAmbientSound() {
		return null;
	}

	@Override
    protected float getSoundVolume()
    {
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
		BlockPos blockpos = new BlockPos(this);
		BlockPos blockpos1 = blockpos.up();

		if (this.spawnPosition != null && (!this.world.isAirBlock(this.spawnPosition) || this.spawnPosition.getY() < 1)) {
			this.spawnPosition = null;
		}

		if (this.spawnPosition == null || this.rand.nextInt(30) == 0 || this.spawnPosition.distanceSq((double) ((int) this.posX), (double) ((int) this.posY), (double) ((int) this.posZ)) < 4.0D) {
			this.spawnPosition = new BlockPos((int) this.posX + this.rand.nextInt(7) - this.rand.nextInt(7), (int) this.posY + this.rand.nextInt(6) - 2, (int) this.posZ + this.rand.nextInt(7) - this.rand.nextInt(7));
		}

		double d0 = (double) this.spawnPosition.getX() + 0.5D - this.posX;
		double d1 = (double) this.spawnPosition.getY() + 0.1D - this.posY;
		double d2 = (double) this.spawnPosition.getZ() + 0.5D - this.posZ;
		this.motionX += (Math.signum(d0) * 0.5D - this.motionX) * 0.001D;
		this.motionY += (Math.signum(d1) * 0.699999988079071D - this.motionY) * 0.001D;
		this.motionZ += (Math.signum(d2) * 0.5D - this.motionZ) * 0.001D;
		float f = (float) (MathHelper.atan2(this.motionZ, this.motionX)) - 90.0F;
		float f1 = MathHelper.wrapDegrees(f - this.rotationYaw) * 0.01f;
		this.moveForward = 10.0F;
		this.rotationYaw += f1;
		
	}
	
	public boolean getCanSpawnHere()
    {
        BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);
        
            int i = this.world.getLightFromNeighbors(blockpos);
            int j = 4;

            if (this.rand.nextBoolean())
            {
                return false;
            }

            return i > this.rand.nextInt(j) ? false : super.getCanSpawnHere();
        
    }
	
	/**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        compound.setBoolean("PersistenceRequired", this.persistenceRequired );
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
    }
    
    @Nullable
    protected ResourceLocation getLootTable()
    {
        return ModLootTable.FLYINGTORCH;
    }
}
