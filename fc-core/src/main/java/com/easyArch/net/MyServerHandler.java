package com.easyArch.net;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyServerHandler extends SimpleChannelInboundHandler<String> {

//    @Autowired
//    public

    private HandlerInterface testHandler = new TestHandler();

    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        String retMsg = testHandler.handler(s);
        /*
        线程池提交
         */
//        NettyServer.msgThreadPool.submit(()->)
        ctx.writeAndFlush(retMsg);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+"channelRegistered");
        /*
        线程池提交
        连接注册确认，保存玩家ip地址
         */
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+"channelUnregistered");
        /*
        线程池提交
        离线处理，有可能是重连，发包确认，包括锁住服务器玩家内存，确认后安全保存数据
         */
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        /*
        线程池提交
        发送心跳
         */
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
