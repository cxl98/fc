package com.easyArch.net;


import com.easyArch.net.model.Message;
import com.easyArch.utils.Beat;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {


    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new IdleStateHandler(0, 0,
                Beat.BEAT_INTERVAL * 3, TimeUnit.SECONDS));
        pipeline.addLast(new NettyDecoder(Message.class,NettyServer.serializer));
        pipeline.addLast(new NettyEncoder(Message.class,NettyServer.serializer));

        pipeline.addLast(new MessageHandler());



    }
}
