package com.atomuze.torchrism.network;

import com.atomuze.torchrism.block.altar.block.BlockAltarMainPedestal;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.relauncher.Side;

public class ModParticlePacket implements IMessage {

	private boolean messageValid;

	public ModParticlePacket() {
		this.messageValid = false;
	}

	public ModParticlePacket(boolean crafting) {
		this.messageValid = true;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		try {
			
		} catch (IndexOutOfBoundsException ioe) {
			return;
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		if (!this.messageValid)
			return;
		
	}

//	public static class Handler implements IMessageHandler<AltarCraftingParticlePacket, IMessage> {
//
//		@Override
//		public IMessage onMessage(AltarCraftingParticlePacket message, net.minecraftforge.fml.common.network.simpleimpl.MessageContext ctx) {
//			if (!message.messageValid && ctx.side != Side.CLIENT) {
//				return null;
//			}
//
//			Minecraft minecraft = Minecraft.getMinecraft();
//			final WorldClient worldClient = minecraft.world;
//
//			minecraft.addScheduledTask(() -> processMessage(message, worldClient));
//
//			return null;
//		}
//
//		void processMessage(AltarCraftingParticlePacket message, WorldClient worldClient) {
//			worldClient.spawnParticle(particleType, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed, parameters);
//		}
//
//	}

}