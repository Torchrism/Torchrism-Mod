package com.atomuze.torchrism.network;

import java.util.Random;

import com.atomuze.torchrism.block.altar.block.BlockAltarMainPedestal;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.relauncher.Side;

public class PacketModParticle implements IMessage {

	private static World world;
	private static float posX;
	private static float posY;
	private static float posZ;
	private static int particleId;
	private boolean messageValid;

	public PacketModParticle() {
		this.messageValid = false;
	}
	
	public PacketModParticle(int particleId, float posX, float posY, float posZ) {
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
		this.particleId = particleId;
		this.messageValid = true;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		try {
			this.posX = buf.readFloat();
			this.posY = buf.readFloat();
			this.posZ = buf.readFloat();
			this.particleId = buf.readInt();
		} catch (IndexOutOfBoundsException ioe) {
			return;
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		if (!this.messageValid)
			return;
		buf.writeFloat(posX);
		buf.writeFloat(posY);
		buf.writeFloat(posZ);
		buf.writeInt(particleId);
		
	}

	public static class Handler implements IMessageHandler<PacketModParticle, IMessage> {
		@Override
		public IMessage onMessage(PacketModParticle message, net.minecraftforge.fml.common.network.simpleimpl.MessageContext ctx) {
			if (!message.messageValid && ctx.side != Side.CLIENT) {
				return null;
			}
			Minecraft.getMinecraft().addScheduledTask(() -> {
				Random random = new Random();
				double d1 = (double) (posX + random.nextFloat());
				double d2 = (double) (posY + random.nextFloat());
				double d3 = (double) (posZ + random.nextFloat());
				Minecraft.getMinecraft().world.spawnParticle(EnumParticleTypes.getParticleFromId(particleId), d1, d2, d3, 0.0D, 0.0D, 0.0D);
			});
			return null;
		}


	}

}