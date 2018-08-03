package com.chengmboy.netty.example.handler;

import java.net.SocketAddress;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cheng_mboy
 */
public class ServerOutHandler2 extends ChannelOutboundHandlerAdapter{

    private static Logger logger  = LoggerFactory.getLogger(ServerOutHandler2.class);

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        logger.info("OutboundHandler2.write" + msg);
        // 执行下一个OutboundHandler
        super.write(ctx, msg, promise);
    }
}
