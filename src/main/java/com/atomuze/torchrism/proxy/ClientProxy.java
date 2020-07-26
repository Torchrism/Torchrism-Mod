package com.atomuze.torchrism.proxy;

import com.atomuze.torchrism.TorchrismMod;
import com.atomuze.torchrism.block.torch_altar.altar.TESRTorchAltar;
import com.atomuze.torchrism.block.torch_altar.altar.TileEntityTorchAltar;
import com.atomuze.torchrism.block.torch_altar.pedestal.TESRTorchPedestal;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;

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
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTorchAltar.class, new TESRTorchAltar());
	}
}
