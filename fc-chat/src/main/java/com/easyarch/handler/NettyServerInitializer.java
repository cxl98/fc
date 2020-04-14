package com.easyarch.handler;

import com.easyarch.handler.model.Message;
import com.easyarch.utils.ProtoStuffSerializer;
import com.easyarch.utils.Serializer;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelId;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
    private static Logger logger = LoggerFactory.getLogger(NettyServerInitializer.class);

    //所有人组
    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    //注册进map中
    public static Map<String, ChannelId> userMap = new ConcurrentHashMap<>();

    private Serializer serializer ;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        group.add(ch);
        ch.closeFuture().addListener((ChannelFutureListener) future -> group.remove(future.channel()));
        pipeline.addLast("decoder",new NettyDecoder(Message.class,new ProtoStuffSerializer()));
        pipeline.addLast("encoder",new NettyEncoder(Message.class,new ProtoStuffSerializer()));

//        pipeline.addLast("framer",new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()) );
        //处理信息
        pipeline.addLast(new MessageHandler());
    }

//    static {
//        NettyServer.pool.submit(new Runnable() {
//            @SneakyThrows
//            @Override
//            public void run() {
//                while (true){
//                    Thread.sleep(60000);
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
