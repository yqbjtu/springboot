package com.yq.TimerServer;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class Server {
    private static final int PORT = 5566;

    public void bind() throws Exception {
        // 配置服务端的NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 100)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws IOException {
                       // ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO));

                        //1）readerIdleTime：读空闲时间（即一定时间内未接收到被测试端消息）;
                        //2）writerIdleTime：写空闲时间（即一定时间内未向被测试端发送消息）
                        //3）allIdleTime：所有类型空闲时间
                        ch.pipeline().addLast(new IdleStateHandler(10,0,0));
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast("processMsg", new ServerSideHandler());
                    }
                });

        // 绑定端口，同步等待成功
        b.bind(PORT).sync();

        System.out.println("Netty server start on  : " + PORT);
    }

    public static void main(String[] args) throws Exception {
        new Server().bind();
    }
}

