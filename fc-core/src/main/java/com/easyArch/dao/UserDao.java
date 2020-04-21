package com.easyArch.dao;


import com.easyArch.model.PlayerInfo;
import com.easyArch.model.UserInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    int searchById(String id);

    int searchByName(String name);

    UserInfo searchUserById(String id);

    int insertUser(UserInfo user);

    UserInfo searchUserByUserInfo(UserInfo user);

    int updatePlayer(PlayerInfo player);


}
