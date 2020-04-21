package com.easyArch.invoker;

import com.easyArch.factory.*;
import io.netty.channel.ChannelHandlerContext;

import com.easyArch.model.Message;
import com.easyArch.model.code.CODE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public class MessageInvoker {

    //服务器redis缓存一份玩家基本信息
    //public static Map<String, PlayerInfo> playerInfoMap = new ConcurrentHashMap<>();


    public Message handle(ChannelHandlerContext ctx, Message msg){
        int code = msg.getMsgCode();

        MessageAbstractFactory factory;

        if(code<= CODE.USER_TYPE){
            factory = new UserFactory();
            factory.setCtx(ctx);
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


//    @Autowired
//    private UserFactory userFactory;
//
//    @Autowired
//    private MonsterFactory monsterFactory;
//
//    @Autowired
//    private MatchFactory matchFactory;
//
//    @Autowired
//    private ExceptionFactory exceptionFactory;

    //服务器redis缓存一份玩家基本信息
    //public static Map<String, PlayerInfo> playerInfoMap = new ConcurrentHashMap<>();

//    public Message handle(ChannelHandlerContext ctx, Message msg) {
//        int code = msg.getMsgCode();
//
//        if (code <= CODE.USER_TYPE) {
//            msg = userFactory.handle(msg);
//        }
//        //打机器人
//        else if (code == CODE.FIGHT) {
//            msg = monsterFactory.handle(msg);
//        }
//        //匹配
//        else if (code <= CODE.MATCH_TYPE) {
//            msg = matchFactory.handle(msg);
//        } else {
//            msg = exceptionFactory.handle(msg);
//        }
//
//        return msg;
//
//    }
}
