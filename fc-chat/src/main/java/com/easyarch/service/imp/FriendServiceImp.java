package com.easyarch.service.imp;

import com.easyarch.dao.UserDao;
import com.easyarch.entity.FriendRequest;
import com.easyarch.entity.Type.DealType;
import com.easyarch.handler.NettyServerInitializer;
import com.easyarch.service.FriendService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import org.springframework.stereotype.Service;

/**
 * 好友业务
 *
 * 添加好友  留言 等
 */
@Service
public class FriendServiceImp implements FriendService {
    private UserDao userDao;

    public Object getObj(Object obj){
        FriendRequest fr = (FriendRequest)obj;
        short status = fr.getStatus();
        if(status== DealType.WAITING){
            return sendFriendRequest(fr);
        }
        return null;
    }

    @Override
    public boolean sendFriendRequest(FriendRequest fq) {
        fq.setStatus(DealType.DELETE);
        String toId = fq.getToId();

//        if()检查一下玩家是否在线
//       若在线，则发送，不在线则入库


        ChannelId channelId = NettyServerInitializer.userMap.get(toId);
        Channel channel = NettyServerInitializer.group.find(channelId);
        if(channel.isActive()){
            channel.writeAndFlush(fq);
            return true;
        }else{
            System.out.println(fq.toString());
            //入库 保留
        }
        return false;
    }

    @Override
    public boolean sendFriendResponse(FriendRequest fq) {
        return false;
    }
}
