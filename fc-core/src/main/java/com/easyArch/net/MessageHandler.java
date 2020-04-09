package com.easyArch.net;

import com.easyArch.fight.MonsterImp;
import com.easyArch.net.model.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MessageHandler extends SimpleChannelInboundHandler<Message> {

    private MonsterImp monster = new MonsterImp();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {

    }
}
