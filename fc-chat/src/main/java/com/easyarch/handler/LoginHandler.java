package com.easyarch.handler;

import com.easyarch.entity.UserInfo;
import com.easyarch.service.UserService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class LoginHandler extends SimpleChannelInboundHandler<UserInfo> {

    //登录后 处理玩家状态， 包括  加载好友列表，个人信息，玩家redis缓存

    @Autowired
    public UserService userService;
    private ChannelId id ;

    LoginHandler(ChannelId id){
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
