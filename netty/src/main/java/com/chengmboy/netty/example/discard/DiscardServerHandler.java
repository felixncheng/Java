package com.chengmboy.netty.example.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;

/**
 * @author cheng_mboy
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter { // (1)

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        ChannelFuture f = ctx.writeAndFlush(msg);
        f.addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}