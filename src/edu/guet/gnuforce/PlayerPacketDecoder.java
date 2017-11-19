package edu.guet.gnuforce;

import edu.guet.gnuforce.packet.player.Heartbeat;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.List;

public class PlayerPacketDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
		if(byteBuf.readableBytes() < 2) return;
		PlayerPacketType type = _types[byteBuf.readByte()];
		int dataLen = byteBuf.readByte();
		InetAddress addr = ((InetSocketAddress) channelHandlerContext.channel().remoteAddress()).getAddress();
		switch (type){
			case HEARTBEAT:
				if(byteBuf.readableBytes() < 4) return;
				list.add(type);
				list.add(dataLen);
				list.add(new Heartbeat(addr, byteBuf.readInt()));
				break;
			case VELOCITY_CHANGE:
				if(byteBuf.readableBytes() < 19) return;
				list.add(type);
				list.add(dataLen);

				break;
			case CHAT:
				break;
		}
	}

	private static PlayerPacketType[] _types = PlayerPacketType.values();
}
