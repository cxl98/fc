package com.easyarch;

import com.easyarch.handler.NettyDecoder;
import com.easyarch.handler.NettyEncoder;
import com.easyarch.handler.model.Message;
import com.easyarch.utils.ProtoStuffSerializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class NettyClient {
    private Bootstrap client;
//    public static ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200,
//            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(5));

    //    public static ExecutorService pool = Executors.newFixedThreadPool(20);
    public NettyClient() {
    }

    public void init() {
        client = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            client.group(group);

            client.channel(NioSocketChannel.class);

            client.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new NettyEncoder(Message.class, new ProtoStuffSerializer()));
                    ch.pipeline().addLast(new NettyDecoder(Message.class, new ProtoStuffSerializer()));

                    ch.pipeline().addLast(new SimpleClientHandler());
                }
            });

            ChannelFuture future = client.connect("localhost", 8888).sync();
            InputStreamReader inputStreamReader=new InputStreamReader(System.in);
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            while(true){
                future.channel().writeAndFlush(bufferedReader).sync();
            }
//            future.channel().closeFuture().sync();
//            System.out.println("------connect------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }

//    public void sendMessage(Message message) {
//        try {
//            Channel channel = null;
//            channel.writeAndFlush(message).sync();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

}
