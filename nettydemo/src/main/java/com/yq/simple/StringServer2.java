package com.yq.simple;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.io.IOException;

/**
 * Simple to Introduction
 * className: StringServer
 *
 * @author EricYang
 * @version 2019/11/23 13:21
 */
public class StringServer2 {

    public void bind(int port) throws Exception {
        // 配置服务端的NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 100)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch)
                            throws IOException {
                        ch.pipeline().addLast("encoder", new StringEncoder());
                        ch.pipeline().addLast("decoder1", new LineBasedFrameDecoder(1024));
                        ch.pipeline().addLast("decoder2", new StringDecoder());
                        ch.pipeline().addLast("decoder3", new ServerStringHandler());
                    }
                });

        // 绑定端口，同步等待成功
        b.bind(port).sync();
        System.out.println("Netty server start ok : " + (port));
    }

    public static void main(String[] args) throws Exception {
        new StringServer2().bind(5001);
    }
}
