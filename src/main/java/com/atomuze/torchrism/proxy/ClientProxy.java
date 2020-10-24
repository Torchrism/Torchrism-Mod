package com.atomuze.torchrism.proxy;

import com.atomuze.torchrism.Torchrism;
import com.atomuze.torchrism.block.TESR.TESRMainPedestal;
import com.atomuze.torchrism.block.TESR.TESROtherPedestal;
import com.atomuze.torchrism.block.tileentity.TileEntityMainPedestal;
import com.atomuze.torchrism.block.tileentity.TileEntityOtherPedestal;
import com.atomuze.torchrism.entity.EntityFlyingTorch;
import com.atomuze.torchrism.entity.flyingTorch.RenderFlyingTorch;
import com.atomuze.torchrism.item.ModItems;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		OBJLoader.INSTANCE.addDomain(Torchrism.MODID);
		registerModel(ModItems.torchStaff, 0);
	}
	
	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Torchrism.MODID + ":" + id, "inventory"));
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
