package com.easyarch.handler;

import com.easyarch.entity.ChatType;
import com.easyarch.entity.SendMessage;
import com.easyarch.service.imp.ChatServiceImp;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 管理消息分发
 */
public class SendHandler extends SimpleChannelInboundHandler<SendMessage> {

    @Autowired
    public ChatServiceImp chatServiceImp;


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SendMessage msg) throws Exception {

        if(msg.getType()== ChatType.ALL){

            chatServiceImp.sendMessageToAll(msg);
        }else if(msg.getType()==ChatType.GROUP){

            chatServiceImp.sendMessageToGroup(msg);
        }else if(msg.getType()==ChatType.ONE){

            chatServiceImp.sendMessageToOne(msg);
        }else{
            ctx.writeAndFlush("发送失败");
            throw new Exception("消息类型出错了!");
        }

        ctx.writeAndFlush("发送成功");

    }



}
