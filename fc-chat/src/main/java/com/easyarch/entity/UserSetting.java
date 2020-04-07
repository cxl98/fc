package com.easyarch.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户偏好设置
 */
@Data
public class UserSetting implements Serializable {

    private static final long serialVersionUID=2L;

    //是否接受好友请求
    private boolean isAcceptFriendRequest = true;

    //是否接受留言
    private boolean isAcceptMessage = true;

//    private
}
