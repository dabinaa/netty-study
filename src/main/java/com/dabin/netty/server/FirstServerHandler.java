package com.dabin.netty.server;

import com.dabin.netty.command.LoginRequestPacket;
import com.dabin.netty.command.Packet;
import com.dabin.netty.command.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @ClassName:FirstServerHandler
 * @author: dabin
 * @date: 2020/4/60:13
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {
    //接收到客户端发来的数据之后被回调。
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        Packet packet = PacketCodeC.INSTANCE.encode(byteBuf);

        if (packet instanceof LoginRequestPacket) {
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;

            if (loginRequestPacket.isSuccess()) {
                System.out.println(new Date() + "：客户端等人成功");
            } else {
                System.out.println(new Date() + ":登入失敗，原因是:" + loginRequestPacket.getReason());
            }
        }


        String result = byteBuf.toString(Charset.forName("utf-8"));
        System.out.println(new Date() + "服务端读取到的数据为：" + result);
        //返回数据给客户端
        ByteBuf out = getByteBuf(ctx);
        ctx.channel().writeAndFlush(out);
    }


    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        //获取到对应的二进制抽象
        ByteBuf byteBuf = ctx.alloc().buffer();

        //准备好数据，并以指定的格式传输
        byte[] bytes = "你好啊！！！！！".getBytes(Charset.forName("utf-8"));


        //将数据填充到byteBuf
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }
}
