package com.easyarch.service.imp;

import com.easyarch.entity.GroupMsg;
import com.easyarch.entity.Type.GroupStatus;
import com.easyarch.handler.NettyServerInitializer;
import com.easyarch.service.GroupService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GroupServiceImp implements GroupService {

    public static Map<String, ChannelGroup> groupMap = new ConcurrentHashMap<>();

    public Object getObj(Object obj){
        GroupMsg group = (GroupMsg)obj;
        short status = group.getStatus();
        if(status == GroupStatus.APPLY){
            return createGroup(group);
        }
        if(status == GroupStatus.DELETE){
            return destroyGroup(group);
        }
        return null;
    }
    @Override
    public boolean createGroup(GroupMsg group) {
        String groupId = group.getGroupId();
        List<String> members = group.getMembersId();
        ChannelGroup cg = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
        for (String member : members) {
            ChannelId id = NettyServerInitializer.userMap.get(member);
            Channel channel = NettyServerInitializer.group.find(id);
            cg.add(channel);
        }
        if(cg.isEmpty()){
            return false;
        }else{
            groupMap.put(groupId,cg);
            return true;
        }
    }

    @Override
    public boolean destroyGroup(GroupMsg group) {
        groupMap.remove(group.getGroupId());
        return true;
    }

    @Override
    public boolean addManager(String groupId, String userId) {

        return false;
    }

    @Override
    public boolean quitGroup() {
        return false;
    }
}
