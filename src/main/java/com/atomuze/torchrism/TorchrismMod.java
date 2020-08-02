package com.atomuze.torchrism;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RequiresMcRestart;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

import com.atomuze.torchrism.block.BlockTileEntity;
import com.atomuze.torchrism.block.ModBlocks;
import com.atomuze.torchrism.block.torch_castle.TileEntityGreatWallBuilder;
import com.atomuze.torchrism.block.torch_castle.GreatWallBuilder;
import com.atomuze.torchrism.item.ModItems;
import com.atomuze.torchrism.network.PacketRequestUpdateTorchAltar;
import com.atomuze.torchrism.network.PacketUpdateTorchAltar;
import com.atomuze.torchrism.proxy.CommonProxy;

@Mod(modid = TorchrismMod.MODID, name = TorchrismMod.NAME, version = TorchrismMod.VERSION)
public class TorchrismMod
{
    public static final String MODID = "torchrism";
    public static final String NAME = "Torchrism";
    public static final String VERSION = "1.1";
    public static SimpleNetworkWrapper wrapper;

    @Mod.Instance(MODID)
	public static TorchrismMod instance;
    
    @SidedProxy(clientSide = "com.atomuze.torchrism.proxy.ClientProxy",serverSide = "com.atomuze.torchrism.proxy.CommonProxy")
    public static CommonProxy proxy;

    public static final TorchrismTab creativeTab = new TorchrismTab();
	public static SimpleNetworkWrapper network;
    
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		System.out.println(NAME + " is loading!");
		System.out.println();
		
		network = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
		network.registerMessage(new PacketUpdateTorchAltar.Handler(), PacketUpdateTorchAltar.class, 0, Side.CLIENT);
		network.registerMessage(new PacketRequestUpdateTorchAltar.Handler(), PacketRequestUpdateTorchAltar.class, 1, Side.SERVER);
		proxy.registerRenderers();
		
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		
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