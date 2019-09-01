
package com.yq.server;


import com.yq.uitl.SocketUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@NoArgsConstructor
public class ServerSideHandler extends SimpleChannelInboundHandler<String> {
    private int idleCounter = 0;

    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        log.info("---Connection Created from {}", ctx.channel().remoteAddress());
        SocketUtils.sendHello(ctx, "server", false);
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        // Send the received message to all channels but the current one.
        log.info("ip:{}--- msg:{}", ctx.channel().remoteAddress(), msg);
        idleCounter = 0;
        //String reply = "Server counter " + counter.getAndAdd(1);
        //SocketUtils.sendLineBaseText(ctx, reply);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.warn("Unexpected exception from downstream.", cause);
        ctx.close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
            throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state().equals(IdleState.READER_IDLE)) {
                log.warn("第" + idleCounter +"次没收到客户端信息了。ip={}", ctx.channel().remoteAddress());
                if (idleCounter > 3) {
                    // 超时关闭channel
                    log.warn("已经连续三次没收到客户端信息了， 关闭不活跃的连接={}", ctx);
                    ctx.close();
                } else {
                    idleCounter++;
                }
            } else if (event.state().equals(IdleState.WRITER_IDLE)) {
                log.info("写空闲");
            } else if (event.state().equals(IdleState.ALL_IDLE)) {
                log.info("ALL_IDLE");
                // 发送心跳
                ctx.channel().write("ping\n");
            }
        }
        super.userEventTriggered(ctx, evt);
    }

}