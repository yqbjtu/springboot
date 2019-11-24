
package com.yq.simple;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ScheduleStringHandler extends SimpleChannelInboundHandler<String> {
    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    private int count = 0;
    private Future future;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Future future = executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                ctx.writeAndFlush(count + "th" + "\r\n");
                count++;
                SocketAddress remoteAddress = ctx.channel().remoteAddress();
                String host = ((InetSocketAddress) remoteAddress).getHostString();
                int port = ((InetSocketAddress) remoteAddress).getPort();
                log.info("send msg at fixed rate to ip:prot={}:{}", host, port);
            }
        },
                0, 1, TimeUnit.SECONDS);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msgStr) {
        SocketAddress remoteAddress = ctx.channel().remoteAddress();
        String host = ((InetSocketAddress) remoteAddress).getHostString();
        int port = ((InetSocketAddress) remoteAddress).getPort();
        log.info("Client receive. ip:port={}:{},  msg={}", host, port, msgStr);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.info("ctx exception", cause);
        ctx.close();
        if (future != null) {
            future.cancel(true);
        }
    }
}
