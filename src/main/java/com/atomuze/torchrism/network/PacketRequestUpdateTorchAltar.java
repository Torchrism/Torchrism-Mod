package com.atomuze.torchrism.network;


import com.atomuze.torchrism.block.torch_altar.TileEntityTorchAltar;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketRequestUpdateTorchAltar  implements IMessage {

	private BlockPos pos;
	private int dimension;
	
	public PacketRequestUpdateTorchAltar(BlockPos pos, int dimension) {
		this.pos = pos;
		this.dimension = dimension;
	}
	
	public PacketRequestUpdateTorchAltar(TileEntityTorchAltar te) {
		this(te.getPos(), te.getWorld().provider.getDimension());
	}
	
	public PacketRequestUpdateTorchAltar() {
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(pos.toLong());
		buf.writeInt(dimension);
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		pos = BlockPos.fromLong(buf.readLong());
		dimension = buf.readInt();
	}
	
	public static class Handler implements IMessageHandler<PacketRequestUpdateTorchAltar, PacketUpdateTorchAltar> {
	
		@Override
		public PacketUpdateTorchAltar onMessage(PacketRequestUpdateTorchAltar message, MessageContext ctx) {
			World world = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(message.dimension);
			TileEntityTorchAltar te = (TileEntityTorchAltar)world.getTileEntity(message.pos);
			if (te != null) {
				return new PacketUpdateTorchAltar(te);
			} else {
				return null;
			}
		}
	
	}

}