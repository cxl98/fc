package com.easyarch.handler;

import io.netty.channel.ChannelId;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
    //所有人组
    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    //注册进map中
    public static Map<String, ChannelId> userMap = new ConcurrentHashMap<>();

//    private Serializer serializer ;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
//        group.add(ch);

        pipeline.addLast("decoder",new StringDecoder());
        pipeline.addLast("encoder",new StringEncoder());
//        pipeline.addLast("decoder",new NettyDecoder(UserInfo.class,new ProtoStuffSerializer()));
//        pipeline.addLast("decoder",new NettyDecoder(Message.class,new ProtoStuffSerializer()));


        pipeline.addLast("framer",new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()) );
        //处理信息
        pipeline.addLast(new MessageHandler());
        pipeline.addLast(new LoginHandler(ch.id()));
    }

//    static {
//        NettyServer.pool.submit(new Runnable() {
////            @SneakyThrows
//            @Override
//            public void run() {
//                while (true){
////                    Thread.sleep(60000);
//                    for(String id : userMap.keySet()){
//                        if(!group.find(userMap.get(id)).isActive()){
//                            userMap.remove(id);
//                        }
//                    }
//                }
//
//            }
//        });
//    }

}
