package com.yq.ipfilter;


import com.yq.NettyConstant;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ipfilter.IpFilterRule;
import io.netty.handler.ipfilter.IpFilterRuleType;
import io.netty.handler.ipfilter.IpSubnetFilterRule;
import io.netty.handler.ipfilter.RuleBasedIpFilter;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.io.IOException;
import java.net.InetSocketAddress;


public class NettyServer {

    public void bind() throws Exception {

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

                        IpSubnetFilterRule rule1 = new IpSubnetFilterRule("192.168.119.1", 24, IpFilterRuleType.ACCEPT);

                        IpSubnetFilterRule rule3 = new IpSubnetFilterRule("127.0.0.1", 32, IpFilterRuleType.REJECT);

                        IpFilterRule rejectAll = new IpFilterRule() {
                            @Override
                            public boolean matches(InetSocketAddress remoteAddress) {
                                return true;
                            }

                            @Override
                            public IpFilterRuleType ruleType() {
                                return IpFilterRuleType.REJECT;
                            }
                        };
                        RuleBasedIpFilter filter = new RuleBasedIpFilter(rule1, rule3, rejectAll);
                        ch.pipeline().addLast("ipFilter", filter);
                        ch.pipeline().addLast("encoder", new StringEncoder());
                        ch.pipeline().addLast("decoder", new StringDecoder());
                        ch.pipeline().addLast(new StringHandler());

                    }
                });
        //我本机有多个ip，启动时不绑定特定ip， 这样才能方便白名单测试
        b.bind(NettyConstant.PORT).sync();
        System.out.println("Netty server start successfully: " + (NettyConstant.PORT));
    }

    public static void main(String[] args) throws Exception {
        new NettyServer().bind();
    }
}

