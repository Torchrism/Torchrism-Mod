package com.atomuze.torchrism;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.atomuze.torchrism.block.ModBlocks;
import com.atomuze.torchrism.entity.ModEntity;
import com.atomuze.torchrism.gui.ModGui;
import com.atomuze.torchrism.item.ModItems;
import com.atomuze.torchrism.network.ModNetworks;
import com.atomuze.torchrism.proxy.CommonProxy;
import com.atomuze.torchrism.sound.ModSound;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

@Mod(modid = Torchrism.MODID, name = Torchrism.NAME, version = Torchrism.VERSION)
public class Torchrism {
	public static final String MODID = "torchrism";
	public static final String NAME = "Torchrism";
	public static final String VERSION = "1.2.6";
	public static SimpleNetworkWrapper wrapper;
	private static final Logger LOGGER = LogManager.getLogger(NAME);

	@Mod.Instance(MODID)
	public static Torchrism instance;

	@SidedProxy(clientSide = "com.atomuze.torchrism.proxy.ClientProxy", serverSide = "com.atomuze.torchrism.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static final TorchrismTab creativeTab = new TorchrismTab();

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		LOGGER.info(NAME + " is loading!");
		ModEntity.registerEntities();

		NetworkRegistry.INSTANCE.registerGuiHandler(this, new ModGui());
		ModNetworks.networkRegister();
		proxy.registerRenderers();
		proxy.preInit(event);

	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		ModSound.registerSounds();
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

	@Mod.EventBusSubscriber
	public static class RegistrationHandler {

		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			ModItems.register(event.getRegistry());
			ModBlocks.registerItemBlocks(event.getRegistry());
		}

		@SubscribeEvent
		public static void registerModels(ModelRegistryEvent event) {
			ModItems.registerModels();
			ModBlocks.registerModels();
		}

		@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
			ModBlocks.register(event.getRegistry());
		}
	}

}