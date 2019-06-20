
package com.yq.client;

import com.yq.MessageType;
import com.yq.struct.Header;
import com.yq.struct.NettyMessage;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;



public class HeartBeatReqHandler extends ChannelHandlerAdapter {

    private volatile ScheduledFuture<?> heartBeat;


    public void channelRead(ChannelHandlerContext ctx, Object msg)
	    throws Exception {
	NettyMessage message = (NettyMessage) msg;
	// 握手成功，主动发送心跳消息
	if (message.getHeader() != null
		&& message.getHeader().getType() == MessageType.LOGIN_RESP
			.value()) {
	    heartBeat = ctx.executor().scheduleAtFixedRate(
		    new HeartBeatTask(ctx), 0, 5000,
		    TimeUnit.MILLISECONDS);
	} else if (message.getHeader() != null
		&& message.getHeader().getType() == MessageType.HEARTBEAT_RESP
			.value()) {
	    System.out
		    .println("Client receive server heart beat message : ---> "
			    + message);
	} else
	    ctx.fireChannelRead(msg);
    }

    private class HeartBeatTask implements Runnable {
	private final ChannelHandlerContext ctx;

	public HeartBeatTask(final ChannelHandlerContext ctx) {
	    this.ctx = ctx;
	}


	public void run() {
	    NettyMessage heatBeat = buildHeatBeat();
	    System.out
		    .println("Client send heart beat messsage to server : ---> "
			    + heatBeat);
	    ctx.writeAndFlush(heatBeat);
	}

	private NettyMessage buildHeatBeat() {
	    NettyMessage message = new NettyMessage();
	    Header header = new Header();
	    header.setType(MessageType.HEARTBEAT_REQ.value());
	    message.setHeader(header);
	    return message;
	}
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
	    throws Exception {
	cause.printStackTrace();
	if (heartBeat != null) {
	    heartBeat.cancel(true);
	    heartBeat = null;
	}
	ctx.fireExceptionCaught(cause);
    }
}
