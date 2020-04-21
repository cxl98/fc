package com.easyArch.factory;

import com.easyArch.net.model.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public abstract class MessageAbstractFactory {

    ChannelHandlerContext ctx;

    static Map<String, ChannelId> userMap = new ConcurrentHashMap<>();


    /*
    必须实现的方法
     */
    public abstract Message handle(Message msg);




    MessageAbstractFactory(ChannelHandlerContext ctx){
        this.ctx = ctx;
        /*
        ...
        ...
         */
    }

    MessageAbstractFactory(){
        /*
        ...
        ...
         */
    }

    //每个工厂都会有的一些方法不必抽象
    void logger(){
        /*
        日志...
         */
    }
    /*
    ...
    ...
    ...
     */

    void exception(){

    }
}
