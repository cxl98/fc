package com.easyarch.service;

import com.easyarch.entity.SendMessage;
import io.netty.channel.ChannelHandlerContext;

public interface ChatService {

    /**
     *
     * @param sm 发送的消息类型
     * @return 是否发送成功
     */
    boolean sendMessageToOne(SendMessage sm);

    boolean sendMessageToGroup(SendMessage sm);

    boolean sendMessageToAll(SendMessage sm);



}
