package com.easyarch.service.imp;

import com.easyarch.entity.SendMessage;
import com.easyarch.service.ChatService;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImp implements ChatService {


    @Override
    public boolean sendMessageToOne(ChannelHandlerContext ctx, SendMessage sm) {
        return false;
    }

    @Override
    public boolean sendMessageToGroup(ChannelHandlerContext ctx, SendMessage sm) {
        return false;
    }

    @Override
    public boolean sendMessageToAll(ChannelHandlerContext ctx, SendMessage sm) {
        return false;
    }
}
