package com.easyarch.handler;

import com.easyarch.entity.UserInfo;
import com.easyarch.service.UserService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginHandler extends SimpleChannelInboundHandler<UserInfo> {

    @Autowired
    public UserService userService;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, UserInfo msg) throws Exception {

//        userService.

//        ctx.writeAndFlush();
    }
}
