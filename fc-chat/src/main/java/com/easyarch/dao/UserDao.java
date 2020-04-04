package com.easyarch.dao;

import com.easyarch.entity.UserInfo;

public interface UserDao {


    int searchById(String id);

    int searchByName(String name);

    UserInfo searchUserById(String id);

    int insertUser(UserInfo user);

    UserInfo searchUserByUserInfo(UserInfo user);

}
