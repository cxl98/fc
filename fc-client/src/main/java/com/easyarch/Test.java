package com.easyArch;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Test {

    public void connect(int port, String host) throws Exception {
        // 配置客户端NIO线程组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline().addLast(new StringDecoder())
                                    .addLast(new StringEncoder())
                                    .addLast(new TimeClientHandler());
                        }
                    });

            // 发起异步连接操作
            ChannelFuture f = b.connect(host, port).sync();
            InputStreamReader inputStreamReader=new InputStreamReader(System.in, StandardCharsets.UTF_8);
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            while(true){
                    f.channel().writeAndFlush(bufferedReader.readLine()+"\n").sync();
            }
            // 当代客户端链路关闭
        } finally {
            // 优雅退出，释放NIO线程组
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {

        new Test().connect(8888, "127.0.0.1");


//        Socket s=new Socket("127.0.0.1",8888);

//        OutputStream os=s.getOutputStream();
//        os.write("bbb".getBytes());
//        os.flush();
//        s.close();

    }
}
