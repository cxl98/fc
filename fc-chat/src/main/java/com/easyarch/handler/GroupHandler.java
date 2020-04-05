package com.easyarch.handler;

import com.easyarch.entity.GroupMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class GroupHandler extends SimpleChannelInboundHandler<GroupMsg> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMsg msg) throws Exception {

    }
}
