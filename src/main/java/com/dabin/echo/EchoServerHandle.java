package com.dabin.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @ClassName:EchoServerHandle
 * @author: dabin
 * @date: 2020/3/90:35
 */
//netty提供的注解，这里可以指明这个handler必须是线程安全的，即对于服务器来讲，无论有多少的socket我都可以直接用这一个。(无状态)
@ChannelHandler.Sharable
public class EchoServerHandle extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //netty实现的缓冲区
        ByteBuf in = (ByteBuf) msg;
        System.out.println("server Accept:" + in.toString(CharsetUtil.UTF_8));
        ctx.write(in);
    }


    /**
     * 服务端读取完网络数据后的处理
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)//flush掉所有的数据
                .addListener(ChannelFutureListener.CLOSE);//当flush完成以后，关闭连接
    }

    /**
     * 发生异常后的处理
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
