package com.easyArch.net;


import com.easyArch.net.model.Message;
import com.easyArch.utils.serialize.ProtoStuffSerializer;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {

//    @Autowired
//    MessageHandler messageHandler ;

    protected void initChannel(SocketChannel ch) throws Exception {
        MessageHandler m = new MessageHandler();
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("framer",new DelimiterBasedFrameDecoder(8192,Delimiters.lineDelimiter()) );
        pipeline.addLast("decoder",new NettyDecoder(Message.class,NettyServer.serializer));
        pipeline.addLast("encoder",new NettyEncoder(Message.class,NettyServer.serializer));

        pipeline.addLast(new MessageHandler());

//        System.out.println("-----"+messageHandler);
//        System.out.println("+++++"+m);




    }
}
