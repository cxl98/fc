package com.easyArch.net;

import com.easyArch.net.model.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageHandler extends SimpleChannelInboundHandler<Message> {

    private static MessageInvoker invoker= new MessageInvoker();

    public static ExecutorService pool = Executors.newFixedThreadPool(20);

    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        int code = msg.getMsgCode();
        System.out.println("code:"+code);
        System.out.println("------reading------");
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
        super.channelUnregistered(ctx);
    }
}
