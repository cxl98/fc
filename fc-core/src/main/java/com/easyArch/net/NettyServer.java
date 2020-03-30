package com.easyArch.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class NettyServer implements Runnable{
    private int port;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private Thread nserver;
    public static ThreadPoolExecutor connectThreadPool = new ThreadPoolExecutor(16, 16, 600L,
            TimeUnit.SECONDS, new ArrayBlockingQueue<>(65536));
    public static ThreadPoolExecutor msgThreadPool = new ThreadPoolExecutor(16, 16, 600L,
            TimeUnit.SECONDS, new ArrayBlockingQueue<>(65536));
    public static ThreadPoolExecutor fightThreadPool = new ThreadPoolExecutor(16, 16, 600L,
            TimeUnit.SECONDS, new ArrayBlockingQueue<>(65536));

    private NettyServerInitializer nsi = new NettyServerInitializer();

    private void init(){
        nserver = new Thread(this);
        nserver.start();
    }

    public void run() {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workerGroup);
            b.channel(NioServerSocketChannel.class);
            b.childHandler(nsi);

            ChannelFuture f = b.bind(port).sync();
            Channel channel = f.channel();


        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private NettyServer(int port){
        this.port = port;
        init();
    }

    public static void main(String[] args) {

        new NettyServer(8888);
    }
}
