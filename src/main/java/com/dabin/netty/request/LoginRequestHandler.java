package com.dabin.netty.request;

import com.dabin.netty.command.PacketCodeC;
import com.dabin.netty.response.LoginResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @ClassName:LoginRequestHandler
 * @author: dabin
 * @date: 2020/4/151:03
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequestPacket loginRequestPacket) throws Exception {
        LoginResponsePacket loginResponsePacket = login(loginRequestPacket);
        ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(channelHandlerContext.alloc(), loginRequestPacket);
        channelHandlerContext.channel().writeAndFlush(responseByteBuf);
    }
}
