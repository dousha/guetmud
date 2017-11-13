package edu.guet.gnuforce;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class DiscardServer {

	public void run() throws Exception{
		EventLoopGroup group = new NioEventLoopGroup();
		EventLoopGroup inferior = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(group, inferior).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch){
					ch.pipeline().addLast(new DiscardHandler());
				}
			}).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);

			ChannelFuture future = b.bind(_port).sync();
			future.channel().closeFuture().sync();
		} finally {
			inferior.shutdownGracefully();
			group.shutdownGracefully();
		}
	}

	private int _port = 25555;
}
