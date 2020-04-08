package com.easyarch.handler;

import com.easyarch.entity.UserInfo;
import com.easyarch.service.imp.UserServiceImp;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class LoginHandler extends SimpleChannelInboundHandler<UserInfo> {

    //登录后 处理玩家状态， 包括  加载好友列表，个人信息，玩家redis缓存

    @Autowired
    public UserServiceImp userService;
    private ChannelId id ;

    LoginHandler(ChannelId id){
        this.id = id;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, UserInfo msg) throws Exception {
        System.out.println(msg.getUserName());
        UserInfo user = userService.login(msg);
        if(user!=null){
            NettyServerInitializer.userMap.put(msg.getUserId(),id);

            ctx.writeAndFlush("登陆成功");
        }
        ctx.writeAndFlush("登录失败");
    }


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("LoginHandler----------register----------");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("LoginHandler----------unregister----------");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("LoginHandler----------active----------");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("LoginHandler----------inactive----------");
        super.channelInactive(ctx);
    }
}
