package com.easyarch.handler;

import com.easyarch.entity.FriendRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class FriendHandler extends SimpleChannelInboundHandler<FriendRequest> {



    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FriendRequest msg) throws Exception {
//        if(msg.getType()== DealType.)
    }
}
