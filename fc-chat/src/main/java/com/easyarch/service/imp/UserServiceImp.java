package com.easyarch.service.imp;

import com.easyarch.dao.UserDao;
import com.easyarch.dao.imp.UserDaoImp;
import com.easyarch.entity.UserInfo;
import com.easyarch.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    private UserDao dao = new UserDaoImp();

    public Object getObj(Object obj){
        //从handler转发到service层的obj  处理
        //这里简略，只处理了登录
        UserInfo user = (UserInfo)obj;
        return login(user);
    }

    @Override
    public UserInfo login(UserInfo user) {
        System.out.println(user.getUserId());
        if(isUser(user.getUserId())){
            return dao.searchUserByUserInfo(user);
        }
        return null;
    }

    @Override
    public boolean regist(UserInfo user) {
        if (!isUser(user.getUserId())){
            return dao.insertUser(user) == 1;
        }
        return false;
    }

    private boolean isUser(String id){

        return dao.searchById(id) != 0;
    }
}
