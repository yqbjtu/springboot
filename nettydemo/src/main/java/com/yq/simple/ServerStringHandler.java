package com.yq.simple;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
        log.info("ip:{}--- msg:{}", ctx.channel().remoteAddress(), msgStr);
        log.info("msgStr={}, packetLength={}", msgStr, msgStr.length());
    }

}
