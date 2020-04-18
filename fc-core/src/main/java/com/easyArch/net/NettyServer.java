package com.easyArch.net;

import com.easyArch.utils.serialize.ProtoStuffSerializer;
import com.easyArch.utils.serialize.Serializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;


public class NettyServer implements Runnable{
    private int port;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private Thread nserver;

    static Serializer serializer = new ProtoStuffSerializer();

    @Override
    public void run() {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workerGroup);
            b.channel(NioServerSocketChannel.class);
            b.childHandler(new NettyServerInitializer());

            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();

        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private NettyServer(int port){
        this.port = port;

    }

    public static void main(String[] args) {

        new Thread(new NettyServer(8888)).start();
    }
}
