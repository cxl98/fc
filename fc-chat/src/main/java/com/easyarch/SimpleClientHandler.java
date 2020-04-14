package com.easyarch;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


public class SimpleClientHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

<<<<<<< HEAD
        System.out.println("收到服务器的数据:"+msg);
=======
        System.out.println("收到的返回信息:"+msg.getMsgCode()+"收到服务器的数据:"+msg.getObj());
>>>>>>> f937d453f3e2973650ffe17e51be850e8ba79f11

    }
}
