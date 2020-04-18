package com.easyArch.net.handler;

import com.easyArch.net.model.Message;
import io.netty.channel.ChannelHandlerContext;

public class MessageLogin implements IMessageFactory{
    @Override
    public Message handle(ChannelHandlerContext ctx, Message msg) {
        return null;
    }
}
