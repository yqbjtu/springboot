
package com.yq.simple;

import com.yq.MessageType;
import com.yq.struct.Header;
import com.yq.struct.NettyMessage;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ScheduleStringHandler extends SimpleChannelInboundHandler<String> {
    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    private int count = 0;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                ctx.writeAndFlush(count + "th" + "\r\n");
                log.info("send msg at fixed rate");
            }
        },
                0, 1, TimeUnit.SECONDS);
    }

    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.fireExceptionCaught(cause);
    }
}
