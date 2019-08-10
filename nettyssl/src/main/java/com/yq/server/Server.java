package com.yq.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslHandler;
import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
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
                        log.info("current dir:{}", System.getProperty("user.dir"));
                        String jksPath = (System.getProperty("user.dir")+ "/nettyssl/src/main/resources/certs/yqServer.jks");
                        SSLContext sslContext =
                                MyServerSslContextFactory.getServerContext(jksPath, "sstorepass456", "skeypass123");
                        //设置为服务器模式
                        SSLEngine sslEngine = sslContext.createSSLEngine();
                        sslEngine.setUseClientMode(false);
                        //是否需要验证客户端
                        sslEngine.setNeedClientAuth(false);
                        ch.pipeline().addLast("ssl", new SslHandler(sslEngine));

                        ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast("processMsg", new SslDemoServerSideHandler());
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

