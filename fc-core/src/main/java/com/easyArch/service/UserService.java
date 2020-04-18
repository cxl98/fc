package com.easyArch.service;

import com.easyArch.entity.UserInfo;

public interface UserService {

    String login(UserInfo user);

    boolean regist(UserInfo user);


}
