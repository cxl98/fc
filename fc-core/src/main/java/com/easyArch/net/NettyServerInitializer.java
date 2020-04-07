package com.easyArch.net;

import com.easyArch.entity.Message;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.json.JsonObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {


    MyServerHandler handler = new MyServerHandler();

    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("framer",new DelimiterBasedFrameDecoder(8192,Delimiters.lineDelimiter()) );
        pipeline.addLast("decoder",new StringDecoder());
        pipeline.addLast("encoder",new StringEncoder());
//        pipeline.addLast(new ObjectDecoder(1024, ));
//        pipeline.addLast(new Tcp)

        pipeline.addLast("com/easyArch/net/handler",handler);
    }
}
