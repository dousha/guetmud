package edu.guet.gnuforce;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class MUDHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext context, Object msg){
		try {
			ByteBuf in = (ByteBuf) msg;
			while(in.isReadable()){
				System.out.print(((char) in.readByte()));
				System.out.flush();
			}
		} finally {
			ReferenceCountUtil.release(msg);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext context, Throwable cause){
		cause.printStackTrace();
		context.close();
	}
}
