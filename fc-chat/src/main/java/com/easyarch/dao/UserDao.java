package com.easyarch.dao;

import com.easyarch.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {


    int searchById(String id);

    int searchByName(String name);

    UserInfo searchUserById(String id);

    int insertUser(UserInfo user);

    UserInfo searchUserByUserInfo(UserInfo user);


}
