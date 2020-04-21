package com.easyArch.invoker;

import com.easyArch.factory.*;
import com.easyArch.net.model.CODE;
import com.easyArch.net.model.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MessageInvoker {


    private Map<String, ChannelId> userMap = new ConcurrentHashMap<>();

    @Autowired
    MessageAbstractFactory factory ;

    //服务器redis缓存一份玩家基本信息
    //public static Map<String, PlayerInfo> playerInfoMap = new ConcurrentHashMap<>();

    public Message handle(ChannelHandlerContext ctx, Message msg){
        int code = msg.getMsgCode();

        if(code<=CODE.USER_TYPE){
            factory = new UserFactory(ctx);
        }
        //打机器人
        else if (code == CODE.FIGHT){
            factory = new MonsterFactory();
        }
        //匹配
        else if(code <= CODE.MATCH_TYPE){
            factory = new MatchFactory();
        }
        else{
            factory = new ExceptionFactory();
        }

        msg = factory.handle(msg);
        return msg;
    }














}
