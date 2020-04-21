package com.easyArch.net;

import com.easyArch.model.Message;
import com.easyArch.serialize.imp.ProtoStuffSerializer;
import com.easyArch.utils.Beat;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    private MessageHandler messageHandler;


    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new IdleStateHandler(0, 0,
                Beat.BEAT_INTERVAL * 3, TimeUnit.SECONDS));
        pipeline.addLast(new NettyDecoder(Message.class,new ProtoStuffSerializer()));
        pipeline.addLast(new NettyEncoder(Message.class,new ProtoStuffSerializer()));

        pipeline.addLast(messageHandler);



    }
}
