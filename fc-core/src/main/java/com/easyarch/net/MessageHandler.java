package com.easyarch.net;

<<<<<<< HEAD:fc-core/src/main/java/com/easyArch/net/MessageHandler.java
import com.easyArch.net.model.Message;
=======
import com.easyarch.invoker.MessageInvoker;
import io.netty.channel.ChannelHandler;
>>>>>>> e9e232c31600fc0c463fbcdcee7ab33752014b6e:fc-core/src/main/java/com/easyarch/net/MessageHandler.java
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
<<<<<<< HEAD:fc-core/src/main/java/com/easyArch/net/MessageHandler.java

=======
import com.easyarch.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
>>>>>>> e9e232c31600fc0c463fbcdcee7ab33752014b6e:fc-core/src/main/java/com/easyarch/net/MessageHandler.java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageHandler extends SimpleChannelInboundHandler<Message> {

    private static MessageInvoker invoker= new MessageInvoker();

    private static ExecutorService pool = Executors.newFixedThreadPool(20);

    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

<<<<<<< HEAD:fc-core/src/main/java/com/easyArch/net/MessageHandler.java
=======
    public static Map<String, ChannelId> userMap = new ConcurrentHashMap<>();

>>>>>>> e9e232c31600fc0c463fbcdcee7ab33752014b6e:fc-core/src/main/java/com/easyarch/net/MessageHandler.java
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        int code = msg.getMsgCode();
        System.out.println("code:"+code);
        System.out.println("------reading------");
        System.out.println(msg.getObj());
        pool.execute(new Runnable() {
            @Override
            public void run() {
                ctx.writeAndFlush(invoker.handle(ctx,msg));
            }
        });
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("-------Regist-------");
        group.add(ctx.channel());
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
//        MessageInvoker.userMap.keySet().
        System.out.println("-------UnRegist-------");
        super.channelUnregistered(ctx);
    }
}
