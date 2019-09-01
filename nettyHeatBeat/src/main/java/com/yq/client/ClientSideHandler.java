
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
public class ClientSideHandler extends SimpleChannelInboundHandler<String> {
    private int idleCounter = 0;

    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        System.out.println("connected");
        log.info("---Connection Created from {}", ctx.channel().remoteAddress());
        //SocketUtils.sendHello(ctx,"Client", false);

        String str20 = "012345678901234567890123456789";
        SocketUtils.sendLineBaseText(ctx, str20);
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        // Send the received message to all channels but the current one.
        log.info("ip={}--- msg={}", ctx.channel().remoteAddress(), msg);

        idleCounter = 0;
        String str20 = "01234567890123456789";
        SocketUtils.sendLineBaseText(ctx, str20);
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
                log.warn("5秒没收到服务器端信息了.");
            } else if (event.state().equals(IdleState.WRITER_IDLE)) {
                log.warn("第" + idleCounter +"次没向服务器端发送信息了。ip={}", ctx.channel().remoteAddress());
                if (idleCounter > 1) {
                    log.warn("向服务器端发送一次心跳");
                    // 发送心跳
                    SocketUtils.sendLineBaseText(ctx, "ping");
                    idleCounter = 0;
                } else {
                    idleCounter++;
                }
            } else if (event.state().equals(IdleState.ALL_IDLE)) {
                log.info("ALL_IDLE");
                // 发送心跳
                ctx.channel().write("ping\n");
            }
        }
        super.userEventTriggered(ctx, evt);
    }

}