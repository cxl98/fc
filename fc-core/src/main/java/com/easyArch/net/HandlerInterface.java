package com.easyArch.net;


import io.netty.channel.ChannelInboundHandler;

/**
 * 这个接口用于做handler接收到的msg做扩展，分析啥的
 */
public interface HandlerInterface extends ChannelInboundHandler {

    public String handler(String msg);

}
