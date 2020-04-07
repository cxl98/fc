package com.easyarch.service.imp;

import com.easyarch.entity.Type.MsgType;
import com.easyarch.entity.SendMessage;
import com.easyarch.handler.NettyServerInitializer;
import com.easyarch.service.ChatService;
import com.easyarch.utils.TimeUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImp implements ChatService {
//    Lo

    public Object getObj(Object msg){
        SendMessage sm = (SendMessage)msg;
        short type = sm.getType();
        if(type == MsgType.ALL){
            return sendMessageToAll(sm);
        }else if(type== MsgType.GROUP){
            return sendMessageToGroup(sm);
        }else if(type== MsgType.ONE){
            return sendMessageToOne(sm);
        }else{
            return "消息类型错误";  //未来要将错误也转化成错误代数
        }
    }

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
        String groupId = sm.getToGroupId();
        ChannelGroup cg = GroupServiceImp.groupMap.get(groupId);
        if(cg!=null){
            cg.writeAndFlush(sm);
            return true;
        }
        System.out.println("发送失败");
        return false;
    }

    @Override
    public boolean sendMessageToAll(SendMessage sm) {
        NettyServerInitializer.group.writeAndFlush(sm);
        return true;
    }
}
