package com.easyarch.handler;

import com.easyarch.NettyServer;
import com.easyarch.entity.UserInfo;
import com.easyarch.handler.model.CODE;
import com.easyarch.handler.model.Message;
//import com.easyarch.service.imp.ChatServiceImp;
//import com.easyarch.service.imp.FriendServiceImp;
//import com.easyarch.service.imp.GroupServiceImp;
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
    private ChatServiceImp chatServiceImp;
    @Autowired
    private FriendServiceImp friendServiceImp;
    @Autowired
    private UserServiceImp userServiceImp ;
    @Autowired
    private GroupServiceImp groupServiceImp;


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        int msgType = msg.getMsgCode();
        System.out.println("code:"+msgType);
        Object obj = msg.getObj();
        //根据消息代码 分发给不同的service处理...
        if(msgType<10){
            if(msgType == CODE.REGIST){
                System.out.println("REGIST");
                NettyServer.pool.execute(new Runnable() {
                    @Override
                    public void run() {
                        if(userServiceImp.regist((UserInfo) obj)){
                            msg.setObj(userServiceImp.getObj(obj));
                        }else{
                            msg.setObj("Error");
                        }
                        ctx.writeAndFlush(msg);
                    }
                });
            }else if(msgType == CODE.LOGIN){
                System.out.println("LOGIN");
                NettyServer.pool.execute(new Runnable() {
                    @Override
                    public void run() {
                        msg.setObj(userServiceImp.getObj(obj));
                        ctx.writeAndFlush(msg);
                    }
                });
            }
            else if(msgType == CODE.MESSAGE){
                System.out.println("SEND MESSAGE");
                NettyServer.pool.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(obj);
                        msg.setObj(chatServiceImp.getObj(obj));
                    }
                });
            }else if(msgType == CODE.GROUP){
                System.out.println("SEND GROUP MESSAGE");
                NettyServer.pool.execute(new Runnable() {
                    @Override
                    public void run() {
                        msg.setObj(groupServiceImp.getObj(obj));
                    }
                });
            }else if (msgType == CODE.FRIEND){
                System.out.println("FRIEND");
                NettyServer.pool.execute(new Runnable() {
                    @Override
                    public void run() {
                        msg.setObj(friendServiceImp.getObj(obj));
                        ctx.writeAndFlush(msg);
                    }
                });
            }
        }
        ctx.writeAndFlush(msg).sync();
    }


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Message----------register----------");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Message----------unregister----------");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Message----------active----------");
        ctx.channel().read();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Message----------inactive----------");
    }
}
