package com.yq.uitl;

import com.yq.constant.Constants;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;


/**

 */
@Slf4j
public class SocketUtils {

    public static void sendHello(ChannelHandlerContext ctx, String from, boolean isSecure){
        if( null != ctx){
            send(ctx, Constants.CODE_HELLO_MSG + from + System.getProperty("line.separator"));
        }
    }

    public static void sendLineBaseText(ChannelHandlerContext ctx, String text){
        if( null != ctx){
            send(ctx, text + System.getProperty("line.separator"));
        }
    }

    /*
     *
     */
    private static void send(ChannelHandlerContext ctx, String log){
        if( null != ctx && null != log){
            ByteBuf msgBuf = Unpooled.buffer(log.length());
            msgBuf.writeBytes(log.getBytes(StandardCharsets.UTF_8));
            ctx.writeAndFlush(msgBuf);
        }
    }

}
