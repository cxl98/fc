package com.easyarch.service;

import com.easyarch.entity.FriendRequest;

public interface FriendService {


    /**
     *
     * @param fq 好友请求
     * @return 是否发送成功 (因为有人是拒绝好友申请的设置)
     */
    boolean sendFriendRequest(FriendRequest fq);

}
