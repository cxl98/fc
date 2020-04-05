package com.easyarch.service.imp;

import com.easyarch.NettyServer;
import com.easyarch.entity.SendMessage;
import com.easyarch.handler.NettyServerInitializer;
import com.easyarch.service.ChatService;
import com.easyarch.utils.TimeUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Service
public class ChatServiceImp implements ChatService {
//    Lo

    @Override
    public boolean sendMessageToOne(SendMessage sm) {
        String toId = sm.getToId();
        ChannelId id = NettyServerInitializer.userMap.get(toId);
        Channel channel = NettyServerInitializer.group.find(id);
        if(channel.isActive()){
            //如果在线，发送即时消息
            channel.writeAndFlush(sm);
        }else{
            System.out.println(TimeUtils.getAllTime()+"---来自--"+sm.getFromId()+"--的离线消息:"+sm.getMsg());
            //发送离线留言
        }
        return false;
    }

    @Override
    public boolean sendMessageToGroup(SendMessage sm) {

        return false;
    }

    @Override
    public boolean sendMessageToAll(SendMessage sm) {
        NettyServerInitializer.group.writeAndFlush(sm);
        return true;
    }
}
