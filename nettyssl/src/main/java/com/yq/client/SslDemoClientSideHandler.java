
package com.yq.client;


import com.yq.uitl.SocketUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class SslDemoClientSideHandler extends SimpleChannelInboundHandler<String> {


    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        System.out.println("connected");
        log.info("---Connection Created from {}", ctx.channel().remoteAddress());
        //SocketUtils.sendHello(ctx,"Client", false);

        String str20 = "012345678901234567890123456789";
        ctx.writeAndFlush(str20);
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        // Send the received message to all channels but the current one.
        log.info("ip:{}--- msg:{}", ctx.channel().remoteAddress(), msg);
        //String reply = "Client side currentTime:" + LocalDateTime.now().toString();
        //SocketUtils.sendLineBaseText(ctx, reply);

        String str20 = "01234567890123456789";
        ctx.writeAndFlush(str20);
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
                log.info("READER_IDLE");
                // 超时关闭channel
                ctx.close();
            } else if (event.state().equals(IdleState.WRITER_IDLE)) {
                log.info("WRITER_IDLE");
            } else if (event.state().equals(IdleState.ALL_IDLE)) {
                log.info("ALL_IDLE");
                // 发送心跳
                ctx.channel().write("ping\n");
            }
        }
        super.userEventTriggered(ctx, evt);
    }

}