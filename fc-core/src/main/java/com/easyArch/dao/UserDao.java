package com.easyArch.dao;

import com.easyArch.entity.PlayerInfo;
import com.easyArch.entity.UserInfo;

public interface UserDao {


    int searchById(String id);

    int searchByName(String name);

    UserInfo searchUserById(String id);

    int insertUser(UserInfo user);

    UserInfo searchUserByUserInfo(UserInfo user);

    int updatePlayer(PlayerInfo player);


}
