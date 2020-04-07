package com.dabin.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * netty客户端
 */
public class EchoClient {
    private final int port;
    private final String host;

    public EchoClient(int port, String host) {
        this.port = port;
        this.host = host;
    }

    private void start() throws InterruptedException {
        //启动的一个线程组
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            //客戶端启动必备的启动组件
            Bootstrap b = new Bootstrap();
            //建造模式，
            b.group(group)
                    //        指明使用nio进行网络通讯
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))//配置远程服务器的地址
                    .handler(new EchoClientHandler());
            /**
             *连接到远程节点，并且阻塞等待到连接完成（.sync（）阻塞连接等待完成）
             */
            ChannelFuture f = b.connect().sync();
            /**
             * 阻塞，直到channel关闭
             */
            f.channel().closeFuture().sync(); 
        } finally {
            group.shutdownGracefully().sync();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        new EchoClient(9999, "127.0.0.1").start();
    }


}
