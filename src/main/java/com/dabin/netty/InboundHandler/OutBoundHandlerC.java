package com.dabin.netty.InboundHandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @ClassName:OutBoundHandlerA
 * @author: dabin
 * @date: 2020/4/122:23
 */
public class OutBoundHandlerC extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("OutBoundHandlerC" + msg);
        super.write(ctx, msg, promise);
    }
}
