package com.chengmboy.netty.example.websocket;

import java.io.*;
import java.util.Objects;
import java.util.zip.GZIPInputStream;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ChannelHandler.Sharable
public class WebSocketClientHandler extends SimpleChannelInboundHandler<Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketClientHandler.class);
    private final WebSocketClientHandshaker handshaker;
    private ChannelPromise handshakeFuture;

    public WebSocketClientHandler(WebSocketClientHandshaker handshaker) {
        this.handshaker = handshaker;
    }

    public ChannelFuture handshakeFuture() {
        return handshakeFuture;
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        handshakeFuture = ctx.newPromise();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        handshaker.handshake(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        LOGGER.error("WebSocket Client disconnected!");
        WebSocketClient.connect();
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel ch = ctx.channel();
        if (msg instanceof FullHttpResponse) {
            try {
                handshaker.finishHandshake(ch, (FullHttpResponse) msg);
                LOGGER.info("WebSocket Client connected!");
                handshakeFuture.setSuccess();
                ch.writeAndFlush(new TextWebSocketFrame("{\"id\":\"dtausdt\",\"pick\":[\"bids.60\",\"asks.60\"],\"sub\":\"market.dtausdt.depth.step0\"}"));
                ch.writeAndFlush(new TextWebSocketFrame("{\"id\":\"reqdtausdt\",\"sub\":\"market.dtausdt.trade.detail\"}"));
                ch.writeAndFlush(new TextWebSocketFrame("{\"id\":\"subdtausdt\",\"sub\":\"market.dtausdt.kline.1min\"}"));
                ch.writeAndFlush(new TextWebSocketFrame("{\"id\":\"subdtausdt\",\"sub\":\"market.dtausdt.kline.5min\"}"));
                ch.writeAndFlush(new TextWebSocketFrame("{\"id\":\"subdtausdt\",\"sub\":\"market.dtausdt.kline.30min\"}"));
                ch.writeAndFlush(new TextWebSocketFrame("{\"id\":\"subdtausdt\",\"sub\":\"market.dtausdt.kline.60min\"}"));
                ch.writeAndFlush(new TextWebSocketFrame("{\"id\":\"subdtausdt\",\"sub\":\"market.dtausdt.kline.1day\"}"));
                ch.writeAndFlush(new TextWebSocketFrame("{\"id\":\"subdtausdt\",\"sub\":\"market.dtausdt.kline.1week\"}"));
                ch.writeAndFlush(new TextWebSocketFrame("{\"id\":\"subdtausdt\",\"sub\":\"market.dtausdt.kline.1mon\"}"));
                ch.writeAndFlush(new TextWebSocketFrame("{\"id\":\"subdtausdt\",\"sub\":\"market.dtausdt.kline.1year\"}"));
            } catch (WebSocketHandshakeException e) {
                System.out.println("WebSocket Client failed to connect");
                handshakeFuture.setFailure(e);
            }
            return;
        }

        WebSocketFrame frame = (WebSocketFrame) msg;
        if (frame instanceof TextWebSocketFrame) {
            TextWebSocketFrame textFrame = (TextWebSocketFrame) frame;
            LOGGER.info("WebSocket Client received message: " + textFrame.text());
        } else if (frame instanceof PongWebSocketFrame) {
            LOGGER.info("WebSocket Client received pong");
        } else if (frame instanceof CloseWebSocketFrame) {
            LOGGER.info("WebSocket Client received closing");
            ch.close();
            WebSocketClient.connect();
        } else if (frame instanceof BinaryWebSocketFrame) {
            ByteBuf content = frame.content();
            byte[] bytes = new byte[content.readableBytes()];
            content.getBytes(0, bytes);
            GZIPInputStream inputStream = new GZIPInputStream(new ByteArrayInputStream(bytes));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            this.copy(inputStream, outputStream);
            String data = new String(outputStream.toByteArray());
            int i = data.length() / 3;
            LOGGER.info(data.substring(0, i > 60 ? 40 : i));
            JSONObject jsonObject = JSONObject.parseObject(data);
            String ping = jsonObject.getString("ping");
            if (Objects.nonNull(ping)) {
                this.holdHeartBeat(ch, ping);
            }
        }
    }

    private void holdHeartBeat(Channel ch, String ping) {
        String pong = "{\"pong\":" + ping + "}";
        LOGGER.info(pong);
        WebSocketFrame frame = new TextWebSocketFrame(pong);
        ch.writeAndFlush(frame);
    }

    public int copy(InputStream in, OutputStream out) throws IOException {
        int byteCount = 0;
        byte[] buffer = new byte[8192];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
            byteCount += bytesRead;
        }
        out.flush();
        return byteCount;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        if (!handshakeFuture.isDone()) {
            handshakeFuture.setFailure(cause);
        }
        ctx.close();
    }
}
