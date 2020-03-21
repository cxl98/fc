package net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NettyServer implements Runnable{
    private int port;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private Thread nserver;

    @Autowired
    NettyServerInitializer nsi;

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

    NettyServer(int port){
        this.port = port;
    }
}
