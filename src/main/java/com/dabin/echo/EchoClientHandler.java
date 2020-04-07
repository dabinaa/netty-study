package com.dabin.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;


public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    /**
     * 客户端读取到数据以后会干什么
     *
     * @param channelHandlerContext
     * @param byteBuf
     * @throws Exception
     */
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf)
            throws Exception {

        System.out.println("Client accetp: " + byteBuf.toString(CharsetUtil.UTF_8));

    }

    /**
     * 当客户端被通知channel活跃以后做事。
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //往服务器写数据
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty！", CharsetUtil.UTF_8));
    }

    /**
     * netty中统一的异常处理
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //将异常打印
        cause.printStackTrace();
        //将通道关闭
        ctx.close();
    }
}
