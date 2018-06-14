package com.chengmboy.reactive;

import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;
import reactor.ipc.netty.NettyContext;
import reactor.ipc.netty.http.server.HttpServer;

/**
 * @author cheng_mboy
 */
public class Server {

    public static void main(String[] args) {
        HttpHandler handler = new HttpHandler() {
            @Override
            public Mono<Void> handle(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
                return null;
            }
        };
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(handler);
        NettyContext block = HttpServer.create(8081).newHandler(adapter).block();
    }
}
