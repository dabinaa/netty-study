package com.dabin.netty.client;

import com.dabin.netty.command.LoginRequestPacket;
import com.dabin.netty.command.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.UUID;


/**
 * @ClassName:FirstClientHandler
 * @author: dabin
 * @date: 2020/4/523:50
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    //客户端创建连接成功后调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + "：客戶端开始登陆！");

        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(Integer.valueOf(UUID.randomUUID().toString()));
        loginRequestPacket.setUsername("dabin");
        loginRequestPacket.setPassWord("dabin");

        PacketCodeC packetCodeC = new PacketCodeC();
        ByteBuf byteBuf = packetCodeC.encode(ctx.alloc(), loginRequestPacket);

        //写数据
        ctx.channel().writeAndFlush(byteBuf);
    }


    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        //获取到对应的二进制抽象
        ByteBuf byteBuf = ctx.alloc().buffer();

        //准备好数据，并以指定的格式传输
        byte[] bytes = "你好，大斌".getBytes(Charset.forName("utf-8"));


        //将数据填充到byteBuf
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        String result = byteBuf.toString(Charset.forName("utf-8"));
        System.out.println(new Date() + "：服务器返回：" + result);
    }
}
