package com.easyarch.net;

<<<<<<< HEAD:fc-core/src/main/java/com/easyArch/net/NettyServerInitializer.java

import com.easyArch.net.model.Message;
import com.easyArch.utils.Beat;
=======
import com.easyarch.model.Message;
import com.easyarch.serialize.imp.ProtoStuffSerializer;
import com.easyarch.utils.Beat;
>>>>>>> e9e232c31600fc0c463fbcdcee7ab33752014b6e:fc-core/src/main/java/com/easyarch/net/NettyServerInitializer.java
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
