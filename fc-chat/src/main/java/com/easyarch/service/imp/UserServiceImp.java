package com.easyarch.service.imp;

import com.easyarch.dao.UserDao;
import com.easyarch.entity.UserInfo;
import com.easyarch.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    private UserDao dao;

    @Override
    public UserInfo login(UserInfo user) {
        if(isUser(user.getUserId())){
            return dao.searchUserByUserInfo(user);
        }
        return null;
    }

    @Override
    public boolean regist(UserInfo user) {
        if (isUser(user.getUserId())){
            return dao.insertUser(user) == 1;
        }
        return false;
    }

    private boolean isUser(String id){
        return dao.searchById(id) != 0;
    }
}
