package com.dabin.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName:NettyClient
 * @author: dabin
 */
public class NettyClient {

    private static final int POST = 8000;
    private static final String HOST = "127.0.0.1";
    private static final int MAX_RETRY = 5;


    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        //指定线程模型
        bootstrap.group(workGroup)
                //指定IO类型为NIO模型
                .channel(NioSocketChannel.class)
                //表示连接超时时间，超过这个时间还是连接不上的话就直接表示连接失败
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                //表示开启了TCP底层的心跳机制
                .option(ChannelOption.SO_KEEPALIVE, true)
                //表示关闭Nagle算法。如果要求高实时性，有数据发送时就马上发送，就设置为 true 关闭，如果需要减少发送次数减少网络交互，就设置为 false 开启
                .option(ChannelOption.TCP_NODELAY, true)
//                IO处理逻辑
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(new FirstClientHandler());
                    }
                });

        connect(bootstrap, HOST, POST, MAX_RETRY);

    }

    //异步操作
    private static void connect(Bootstrap bootstrap, String host, int port, int tetry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": 连接成功!");
            } else if (tetry == 0) {
                System.err.println("重试次数已用完，放弃连接！");
            } else {
                // 第几次重连
                int order = (MAX_RETRY - tetry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, tetry - 1), delay, TimeUnit
                        .SECONDS);
            }
        });
    }
}
