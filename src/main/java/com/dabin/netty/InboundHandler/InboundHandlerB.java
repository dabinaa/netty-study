package com.dabin.netty.InboundHandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @ClassName:InboundHandlerA
 * @author: dabin
 * @date: 2020/4/122:17
 */
public class InboundHandlerB extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("InboundHandlerB" + msg);
        super.channelRead(ctx, msg);
    }
}
