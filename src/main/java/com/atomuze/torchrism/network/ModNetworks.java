package com.atomuze.torchrism.network;

import com.atomuze.torchrism.Torchrism;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class ModNetworks {

	public static SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(Torchrism.MODID);;
	
	public static void networkRegister(){
		network.registerMessage(new PacketUpdatePedestal.Handler(), PacketUpdatePedestal.class, 0, Side.CLIENT);
		network.registerMessage(new PacketRequestUpdatePedestal.Handler(), PacketRequestUpdatePedestal.class, 1, Side.SERVER);
		network.registerMessage(new PacketAltarCraftingParticle.Handler(), PacketAltarCraftingParticle.class, 2, Side.CLIENT);
		network.registerMessage(new PacketModParticle.Handler(), PacketModParticle.class, 3, Side.CLIENT);
	}
}
