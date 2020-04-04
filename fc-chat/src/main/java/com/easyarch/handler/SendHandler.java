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
            chatServiceImp.sendMessageToAll(ctx,msg);
        }else if(msg.getType()==ChatType.GROUP){
            chatServiceImp.sendMessageToGroup(ctx,msg);
        }else if(msg.getType()==ChatType.ONE){
            chatServiceImp.sendMessageToOne(ctx,msg);
        }else{
            throw new Exception("消息类型出错了!");
        }

    }



}
