package com.example.cms.dao;

import com.example.cms.entity.Player;

import java.util.List;

public interface UserDao {

    //查询所有
    List<Player> searchUsers();


    Player searchByUserId(String userId);

    Player searchByUserName(String username);

}
