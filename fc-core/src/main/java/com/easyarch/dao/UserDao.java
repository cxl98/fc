package com.easyarch.dao;

<<<<<<< HEAD:fc-core/src/main/java/com/easyArch/dao/UserDao.java
import com.easyArch.entity.PlayerInfo;
import com.easyArch.entity.UserInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
=======

import com.easyarch.model.PlayerInfo;
import com.easyarch.model.UserInfo;
>>>>>>> e9e232c31600fc0c463fbcdcee7ab33752014b6e:fc-core/src/main/java/com/easyarch/dao/UserDao.java

public interface UserDao {

    int searchById(String id);

    int searchByName(String name);

    UserInfo searchUserById(String id);

    int insertUser(UserInfo user);

    UserInfo searchUserByUserInfo(UserInfo user);

    int updatePlayer(PlayerInfo player);


}
