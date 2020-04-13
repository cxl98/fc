package com.easyArch.net;


import com.easyArch.net.model.Message;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {


    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
//        pipeline.addLast(new DelimiterBasedFrameDecoder(8192,Delimiters.lineDelimiter()) );
        pipeline.addLast(new NettyDecoder(Message.class,NettyServer.serializer));
        pipeline.addLast(new NettyEncoder(Message.class,NettyServer.serializer));

        pipeline.addLast(new MessageHandler());



    }
}
