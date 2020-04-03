package com.easyarch.service;

public interface FriendService {


    /**
     *
     * @param fromId 来自
     * @param toId 加谁
     * @param msg 申请信息
     * @return 是否发送成功 (因为有人是拒绝好友申请的设置)
     */
    boolean sendFriendRequest(String fromId,String toId,String msg);

}
