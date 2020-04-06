package com.easyarch.service;

import com.easyarch.entity.UserInfo;


public interface UserService {

    UserInfo login(UserInfo user);

    boolean regist(UserInfo user);


}
