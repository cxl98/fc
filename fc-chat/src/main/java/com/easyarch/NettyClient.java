package com.easyarch;

import com.easyarch.handler.NettyDecoder;
import com.easyarch.handler.NettyEncoder;
import com.easyarch.handler.model.Message;
import com.easyarch.utils.ProtoStuffSerializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.*;

public class NettyClient{
    private Bootstrap client;
    private volatile ChannelFuture future;
    public static ThreadPoolExecutor executor = new ThreadPoolExecutor(5,10,200,
            TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(5));
    public static ExecutorService pool = Executors.newFixedThreadPool(20);
    public NettyClient(){
        client = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        client.group(group);

        client.channel(NioSocketChannel.class);

        client.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
//                    ch.pipeline().addLast(new DelimiterBasedFrameDecoder(
//                            Integer.MAX_VALUE, Delimiters.lineDelimiter()[0]));
                    ch.pipeline().addLast(new NettyEncoder(Message.class,new ProtoStuffSerializer()));
                    ch.pipeline().addLast(new NettyDecoder(Message.class,new ProtoStuffSerializer()));

                    ch.pipeline().addLast(new SimpleClientHandler());
                }
            }).option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000);

        init();
    }

    private void init() {
        try {
            future = client.connect("localhost",8888).sync();
            System.out.println("------connect------");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Message message){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    future.channel().writeAndFlush(message).sync();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
