package com.easyArch.dao;

import com.easyArch.entity.GameData;
import com.easyArch.entity.GameUser;


public interface UserDao {

    int insertNewUser(GameUser userInfo);

    void updateUserData(GameData userData);

    int searchUserCount();

    GameData searchUserById(String userId);

    GameData searchUserByName(String name);



}
