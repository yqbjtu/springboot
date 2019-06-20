package com.yq.myserver;


import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by yangqian on 2019/6/20.
 */

public class MyChannelHandlerAdapter extends ChannelHandlerAdapter {
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        System.out.println(msg);
    }
}
