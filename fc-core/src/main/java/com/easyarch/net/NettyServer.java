package com.easyarch.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
<<<<<<< HEAD:fc-core/src/main/java/com/easyArch/net/NettyServer.java

=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;

@Controller
@PropertySource("classpath:config.properties")
public class NettyServer{
>>>>>>> e9e232c31600fc0c463fbcdcee7ab33752014b6e:fc-core/src/main/java/com/easyarch/net/NettyServer.java

public class NettyServer implements Runnable{
    private int port;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private Thread nserver;

    static Serializer serializer = new ProtoStuffSerializer();

<<<<<<< HEAD:fc-core/src/main/java/com/easyArch/net/NettyServer.java
    @Override
    public void run() {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
=======
    @PostConstruct
    public void start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
>>>>>>> e9e232c31600fc0c463fbcdcee7ab33752014b6e:fc-core/src/main/java/com/easyarch/net/NettyServer.java
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
