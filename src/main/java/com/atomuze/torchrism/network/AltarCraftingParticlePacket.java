package com.atomuze.torchrism.network;

import javax.xml.ws.handler.MessageContext;

import com.atomuze.torchrism.block.altar.block.BlockAltarMainPedestal;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.relauncher.Side;

public class AltarCraftingParticlePacket implements IMessage {

	private boolean messageValid;

	private static boolean crafting;

	public AltarCraftingParticlePacket() {
		this.messageValid = false;
	}

	public AltarCraftingParticlePacket(boolean crafting) {
		this.crafting = crafting;

		this.messageValid = true;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		try {
			this.crafting = buf.readBoolean();
		} catch (IndexOutOfBoundsException ioe) {
			return;
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		if (!this.messageValid)
			return;
		
		buf.writeBoolean(crafting);
	}

	public static class Handler implements IMessageHandler<AltarCraftingParticlePacket, IMessage> {

		@Override
		public IMessage onMessage(AltarCraftingParticlePacket message, net.minecraftforge.fml.common.network.simpleimpl.MessageContext ctx) {
			if (!message.messageValid && ctx.side != Side.CLIENT) {
				return null;
			}

			Minecraft minecraft = Minecraft.getMinecraft();
			final WorldClient worldClient = minecraft.world;

			minecraft.addScheduledTask(() -> processMessage(message, worldClient));

			return null;
		}

		void processMessage(AltarCraftingParticlePacket message, WorldClient worldClient) {
			BlockAltarMainPedestal.crafting = crafting;
		}

	}

}