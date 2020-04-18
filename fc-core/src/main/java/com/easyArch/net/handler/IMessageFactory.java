package com.easyArch.net.handler;

import com.easyArch.net.model.Message;
import io.netty.channel.ChannelHandlerContext;

public interface IMessageFactory {

    public Message handle(ChannelHandlerContext ctx,Message msg);

}
