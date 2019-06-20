package com.yq.mycodec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by yangqian on 2019/6/20.
 */
public class ToIntegerDecoder extends ByteToMessageDecoder {
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        if(in.readableBytes() >= 4) {  // int是4字节
          out.add(in.readInt());
         }
    }
}
