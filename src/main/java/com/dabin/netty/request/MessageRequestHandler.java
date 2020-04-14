package com.dabin.netty.request;

import com.dabin.netty.command.PacketCodeC;
import com.dabin.netty.response.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @ClassName:MessageRequestHandler
 * @author: dabin
 * @date: 2020/4/151:06
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageRequestPacket messageRequestPacket) throws Exception {
        MessageResponsePacket messageResponsePacket = receiveMessage(messageRequestPacket);
        ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(channelHandlerContext.alloc(), messageRequestPacket);
        channelHandlerContext.channel().writeAndFlush(responseByteBuf);
    }
}
