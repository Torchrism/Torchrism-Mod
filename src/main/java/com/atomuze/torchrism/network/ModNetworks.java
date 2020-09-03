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
		network.registerMessage(new AltarCraftingParticlePacket.Handler(), AltarCraftingParticlePacket.class, 2, Side.CLIENT);
//		network.registerMessage(new ModParticlePacket.Handler(), ModParticlePacket.class, 3, Side.CLIENT);
	}
}
