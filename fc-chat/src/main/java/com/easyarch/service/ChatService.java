package com.easyarch.service;

public interface ChatService {


    /**
     *
     * @param msg 发送的消息
     * @param userId 要发送给的玩家
     * @return 是否发送成功
     */
    boolean sendMessage(String msg,String userId,String fromId);

}
