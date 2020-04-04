package com.easyarch.service;

import com.easyarch.entity.UserInfo;
import com.easyarch.utils.ReturnT;

public interface UserService {

    boolean login(UserInfo user);

    boolean regist(UserInfo user);


}
