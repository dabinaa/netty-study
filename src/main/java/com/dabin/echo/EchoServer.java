package com.dabin.echo;


import io.netty.bootstrap.ServerBootstrap;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @ClassName:Server
 * @author: dabin
 * @date: 2020/3/90:20
 */
public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        int port = 9999;
        EchoServer echoServer = new EchoServer(port);
        System.out.println("服务器即将启动！！！");
        echoServer.start();
        System.out.println("服务即将关闭");
    }

    private void start() throws InterruptedException {
        final EchoServerHandle serverHandle = new EchoServerHandle();
        /**
         * 线程组
         */
        EventLoopGroup group = new NioEventLoopGroup();
        try {

            /**
             * 服务端启用必备
             */
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    //指明使用nio进行网络连接
                    .channel(NioServerSocketChannel.class)
                    //指明服务器监听端口
                    .localAddress(new InetSocketAddress(port))
                    //接收到连接请求，新启动一个socket通信，也就是channel，每一个channel有自己的事件的handler
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //这里指的是所有得socket公用一个实例
                            socketChannel.pipeline().addLast(serverHandle);
                        }
                    });
            /**
             * 绑定到端口，阻塞直到连接完成
             */
            ChannelFuture f = b.bind().sync();
            //阻塞，直到channel关闭
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }
}
