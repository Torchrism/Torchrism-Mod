package com.atomuze.torchrism.particle;

import java.util.Random;

import com.atomuze.torchrism.Torchrism;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ParticleAltar extends Particle {
	Random ran = new Random();
//	public static final ResourceLocation particles = new ResourceLocation(Torchrism.MODID + ":" + "textures/particle/mod_particles.png");
	
	protected ParticleAltar(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double par8, double par10, double par12) {
		this(worldIn, xCoordIn, yCoordIn, zCoordIn, 1.0F, par8, par10, par12);
	}

	protected ParticleAltar(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, float scale, double par8, double par10, double par12) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0.0D, 0.0D, 0.0D);
		
		this.motionY *= 0.3D;

		if (par8 == 0.0F) {
			par8 = 1.0F;
		}
		
		float fa = ran.nextFloat();
		
		this.particleRed = 0.89F + fa/10f;
		this.particleGreen = 0.90F + fa/10f;
		this.particleBlue = 0.76F;
		
		this.particleScale *= scale;
		this.setParticleTextureIndex(225 + ran.nextInt(15));
		
	}

	public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
		float f = ((float) this.particleAge + partialTicks) / (float) this.particleMaxAge * 32.0F;
		f = MathHelper.clamp(f, 0.0F, 1.0F);
		super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
	}

	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if (this.particleAge++ >= this.particleMaxAge) {
			this.setExpired();
		}
		
		this.move(this.motionX, this.motionY, this.motionZ);

		this.motionY *= 0.9599999785423279D;
	}

	@SideOnly(Side.CLIENT)
	public static class Factory implements IParticleFactory {
		public Particle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
			return new ParticleAltar(worldIn, xCoordIn, yCoordIn, zCoordIn, (float) xSpeedIn, (float) ySpeedIn, (float) zSpeedIn);
		}
	}
}
