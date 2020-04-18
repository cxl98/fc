package com.easyArch.fight.Imp;

import com.easyArch.dao.UserDao;
import com.easyArch.dao.imp.UserDaoImp;
import com.easyArch.entity.PlayerInfo;
import com.easyArch.entity.UserInfo;
import com.easyArch.fight.UserService;
import com.easyArch.utils.RedisUtil;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    private UserDao dao = new UserDaoImp();

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
            if(dao.insertUser(user)==1){
                RedisUtil.initPlayer(user.getUserId());
                return true;
            }
            return false;
        }
        return false;
    }

    private boolean isUser(String id){
        return dao.searchById(id) != 0;
    }

    public boolean updatePlayer(PlayerInfo player){
        return dao.updatePlayer(player) != 0;
    }

}
