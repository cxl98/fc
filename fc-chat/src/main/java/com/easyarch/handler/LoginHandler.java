package com.easyarch.handler;

import com.easyarch.entity.UserInfo;
import com.easyarch.service.UserService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginHandler extends SimpleChannelInboundHandler<UserInfo> {

    @Autowired
    public UserService userService;
    private ChannelId id ;

    public LoginHandler(ChannelId id){
        this.id = id;

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, UserInfo msg) throws Exception {
        UserInfo user = userService.login(msg);
        if(user!=null){
            NettyServerInitializer.userMap.put(msg.getUserId(),id);
            ctx.writeAndFlush("登陆成功");
        }
        ctx.writeAndFlush("登录失败");
    }
}
