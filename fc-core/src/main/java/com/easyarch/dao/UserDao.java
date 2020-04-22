package com.easyarch.dao;


import com.easyarch.model.PlayerInfo;
import com.easyarch.model.UserInfo;

public interface UserDao {

    int searchById(String id);

    int searchByName(String name);

    UserInfo searchUserById(String id);

    int insertUser(UserInfo user);

    UserInfo searchUserByUserInfo(UserInfo user);

    int updatePlayer(PlayerInfo player);


}
