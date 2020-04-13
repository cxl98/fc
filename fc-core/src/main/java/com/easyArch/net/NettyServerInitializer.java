package com.easyArch.net;


import com.easyArch.net.model.Message;
import com.easyArch.utils.serialize.ProtoStuffSerializer;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;

public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {




    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("framer",new DelimiterBasedFrameDecoder(8192,Delimiters.lineDelimiter()) );
        pipeline.addLast("decoder",new NettyDecoder(Message.class,new ProtoStuffSerializer()));
        pipeline.addLast("encoder",new NettyEncoder(Message.class,new ProtoStuffSerializer()));



    }
}
