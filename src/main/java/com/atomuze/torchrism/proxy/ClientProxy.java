package com.atomuze.torchrism.proxy;

import com.atomuze.torchrism.TorchrismMod;
import com.atomuze.torchrism.block.torch_altar.TESR.TESRMainPedestal;
import com.atomuze.torchrism.block.torch_altar.TESR.TESROtherPedestal;
import com.atomuze.torchrism.block.torch_altar.tileEntity.TileEntityMainPedestal;
import com.atomuze.torchrism.block.torch_altar.tileEntity.TileEntityOtherPedestal;
import com.atomuze.torchrism.entity.flyingTorch.EntityFlyingTorch;
import com.atomuze.torchrism.entity.flyingTorch.RenderFlyingTorch;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(TorchrismMod.MODID + ":" + id, "inventory"));
	}

	@Override
	public String localize(String unlocalized, Object... args) {
		return I18n.format(unlocalized, args);
	}

	@Override
	public void registerRenderers() {
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMainPedestal.class, new TESRMainPedestal());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOtherPedestal.class, new TESROtherPedestal());
		
		RenderingRegistry.registerEntityRenderingHandler(EntityFlyingTorch.class, new IRenderFactory<EntityFlyingTorch>()
		{
			@Override
			public Render<? super EntityFlyingTorch> createRenderFor(RenderManager manager) 
			{
				return new RenderFlyingTorch(manager);
			}
		});
	}

	@Override
	public void registerModel(Item item, int metadata) {
		ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
