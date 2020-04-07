package com.easyArch.net.handler;

import com.easyArch.net.HandlerInterface;
import io.netty.channel.ChannelHandlerContext;

public class UserOffLineHandler implements HandlerInterface {

    //处理参数，
    //根据这个参数进行不同的处理，
    //比如是意外离线，还是安全退出等
    private String handlerParameter;

    /**
     *
     * @param msg 传过来参数
     * @return 给服务器返回是否成功 的 参数
     */
    public String handler(String msg) {
        handlerParameter = msg;
        return null;
    }


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

    }
}
