package com.easyarch.entity;

import lombok.Data;

/**
 * 用户偏好设置
 */
@Data
public class UserSetting {

    //是否接受好友请求
    private boolean isAcceptFriendRequest = true;

    //是否接受留言
    private boolean isAcceptMessage = true;

//    private
}
