package com.easyArch.net.handler;

import com.easyArch.entity.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 消息处理，
 *
 * 根据消息的级别 判定是系统还是玩家。。。
 */
public class MessageHandler extends SimpleChannelInboundHandler<Message> {



    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {

    }
}
