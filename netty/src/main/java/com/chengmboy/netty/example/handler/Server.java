package com.chengmboy.netty.example.handler;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author cheng_mboy
 */
public class Server {

    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap bootstrap = new ServerBootstrap();

        EventLoopGroup group = new NioEventLoopGroup();

        try {
            bootstrap.group(group)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new ServerOutHandler1())
                                    .addLast(new ServerOutHandler2())
                                    .addLast(new ServerInHandler1())
                                    .addLast(new ServerInHandler2())
                            ;
                        }
                    });
            ChannelFuture f= bootstrap.bind(8080).sync();
            f.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully();
        }

    }
}
