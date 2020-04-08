package com.dabin.netty.client;

import com.dabin.netty.command.Packet;
import com.dabin.netty.command.PacketCodeC;
import com.dabin.netty.request.LoginRequestPacket;
import com.dabin.netty.response.LoginResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;


/**
 * @ClassName:FirstClientHandler
 * @author: dabin
 * @date: 2020/4/523:50
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    //客户端创建连接成功后调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println(new Date() + "：客戶端开始登陆！");

        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(123);
        loginRequestPacket.setUsername("dabin");
        loginRequestPacket.setPassWord("dabin");

        ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginRequestPacket);

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

        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);
        if (packet instanceof LoginResponsePacket) {
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;
            if (loginResponsePacket.isSuccess()) {
                System.out.println(new Date() + ":客户端登录成功");
            } else {
                System.out.println(new Date() + "客戶端登錄失败，原因是：" + loginResponsePacket.getReason());
            }
//            String result = byteBuf.toString(Charset.forName("utf-8"));
//            System.out.println(new Date() + "：服务器返回：" + result);
        }
    }
}
