package com.easyarch;

import com.easyarch.handler.NettyServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NettyServer implements Runnable{
    private int port;
    private String ip;
    public static ExecutorService pool = Executors.newFixedThreadPool(20);



    private NettyServer(String ip, int port){
        this.ip=ip;
        this.port=port;
    }
    @Override
    public void run(){
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workGroup);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.childHandler(new NettyServerInitializer());

            ChannelFuture future = bootstrap.bind(ip,port).sync();
            Channel f = future.channel();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

        System.out.println("close");
    }

    public static void main(String[] args) {

        new Thread(new NettyServer("localhost",8888)).start();



    }

}
