package com.atomuze.torchrism.entity.flyingTorch;

import com.atomuze.torchrism.Torchrism;
import com.atomuze.torchrism.entity.EntityFlyingTorch;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderFlyingTorch extends RenderLiving<EntityFlyingTorch>{
public static final ResourceLocation TEXTURES = new ResourceLocation(Torchrism.MODID + ":textures/entity/flying_torch.png");
	
	public RenderFlyingTorch(RenderManager manager) 
	{
		super(manager, new ModelFlyingTorch(), 0.5F);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityFlyingTorch entity) {
		return TEXTURES;
	}

	@Override
	protected void applyRotations(EntityFlyingTorch entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
	{
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
	}

	
}
