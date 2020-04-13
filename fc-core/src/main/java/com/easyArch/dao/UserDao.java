package com.easyArch.dao;

import com.easyArch.entity.PlayerInfo;
import com.easyArch.entity.UserInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    int insertNewUser(UserInfo userInfo);

    void updateUserData(PlayerInfo userData);

    int searchUserCount();

    PlayerInfo searchUserById(String userId);

    PlayerInfo searchUserByName(String name);



}
