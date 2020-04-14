package com.dabin.netty.server;

import com.dabin.netty.InboundHandler.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.Date;

/**
 * @ClassName:NettyServer
 * @author: dabin
 * @date: 2020/4/413:33
 */
public class NettyServer {
    private static final int POST = 8000;

    public static void main(String[] args) {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        final AttributeKey<Object> clientKey = AttributeKey.newInstance("clientKey");
        serverBootstrap
                .group(boss, worker)
                .channel(NioServerSocketChannel.class)
                //表示为这个服务端添加一个特别的属性
                .attr(AttributeKey.newInstance("ServerName"), "dabin")
                //给每一个连接指定自定义属性
                .childAttr(clientKey, "clentValue")
                /**
                 * childOption
                 * 可以为每一条连接设置TCP底层相关的属性
                 */
                //表示关闭Nagle算法，true表示关闭，false表示开启。如果要求高实时性，有数据发送时就马上发送，就关闭，如果需要减少发送次数减少网络交互，就开启。
                .childOption(ChannelOption.TCP_NODELAY, true)
                //表示是否开启TCP底层心跳机制，true表示开启
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                //服务端channel设置一些属性
                /**
                 * 表示系统用于临时存放已完成三次握手的请求的队列的最大长度，如果连接建立频繁，服务器处理创建新连接较慢，可以适当调大这个参数
                 */
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel ch) {
                        System.out.println("服务端启动中！");
//                        System.out.println(ch.attr(clientKey));
                        //接收到客户端发来的数据之后被回调。
//                        ch.pipeline().addLast(new FirstServerHandler());
                        ch.pipeline().addLast(new InboundHandlerA());
                        ch.pipeline().addLast(new InboundHandlerB());
                        ch.pipeline().addLast(new InboundHandlerC());


                        ch.pipeline().addLast(new OutBoundHandlerA());
                        ch.pipeline().addLast(new OutBoundHandlerB());
                        ch.pipeline().addLast(new OutBoundHandlerC());

                    }
                });
        bind(serverBootstrap, POST);
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            public void operationComplete(Future<? super Void> future) {
                if (future.isSuccess()) {
                    System.out.println(new Date() + "端口[" + port + "]绑定成功!");
                } else {
                    System.err.println("端口[" + port + "]绑定失败!");
                    bind(serverBootstrap, port + 1);
                }
            }
        });
    }
}
