package com.yq.simple;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.List;

/**
 * Simple to Introduction
 * className: StringHandler
 *
 * @author EricYang
 * @version 2019/9/4 13:56
 */
@Slf4j
@NoArgsConstructor
public class ServerStringHandler extends SimpleChannelInboundHandler<String> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msgStr) throws Exception {
        SocketAddress remoteAddress = ctx.channel().remoteAddress();
        String host = ((InetSocketAddress) remoteAddress).getHostString();
        int port = ((InetSocketAddress) remoteAddress).getPort();
        log.info("ip:port={}:{},  msg={}", host, port, msgStr);

        ctx.writeAndFlush("reply\r\n");
    }
}
