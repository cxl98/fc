package com.easyarch.handler;

import com.easyarch.handler.model.CODE;
import com.easyarch.handler.model.Message;
import com.easyarch.service.imp.ChatServiceImp;
import com.easyarch.service.imp.FriendServiceImp;
import com.easyarch.service.imp.GroupServiceImp;
import com.easyarch.service.imp.UserServiceImp;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 管理消息分发
 */
@Slf4j
public class MessageHandler extends SimpleChannelInboundHandler<Message> {

    @Autowired
    public ChatServiceImp chatServiceImp;

    @Autowired
    public FriendServiceImp friendServiceImp;

    @Autowired
    public UserServiceImp userServiceImp;

    @Autowired
    public GroupServiceImp groupServiceImp;


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        int msgType = msg.getMsgCode();
        Object obj = msg.getObj();
        //根据消息代码 分发给不同的service处理...
        Object res = null;
        if(msgType<10){
            if(msgType == CODE.LOGIN){
                res = userServiceImp.getObj(obj);
            }else if(msgType == CODE.MESSAGE){
                res = chatServiceImp.getObj(obj);
            }else if(msgType == CODE.GROUP){
                res = groupServiceImp.getObj(obj);
            }else if (msgType == CODE.FRIEND){
                res = friendServiceImp.getObj(obj);
            }
        }
        if (res != null) {
            ctx.writeAndFlush(res);
        }
        ctx.writeAndFlush("error");//返回一个错误信息
    }



}
