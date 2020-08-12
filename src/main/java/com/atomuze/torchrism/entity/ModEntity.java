package com.atomuze.torchrism.entity;

import com.atomuze.torchrism.ModConfig;
import com.atomuze.torchrism.TorchrismMod;
import com.atomuze.torchrism.entity.flyingTorch.EntityFlyingTorch;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntity{

	public static void registerEntities()
	{
		registerEntity("flying_torch", EntityFlyingTorch.class, ModConfig.getflyingTorchId(), 50, 16767030, 7165750);
	}
	
	private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2)
	{
		EntityRegistry.registerModEntity(new ResourceLocation(TorchrismMod.MODID + ":" + name), entity, TorchrismMod.MODID + "." + name, id, TorchrismMod.instance, range, 1, true, color1, color2);
	}
}
