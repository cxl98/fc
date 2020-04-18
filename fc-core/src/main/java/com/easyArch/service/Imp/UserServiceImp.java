package com.easyArch.service.Imp;

import com.easyArch.dao.imp.UserDaoImp;
import com.easyArch.entity.PlayerInfo;
import com.easyArch.entity.UserInfo;
import com.easyArch.service.UserService;
import com.easyArch.utils.RedisUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDaoImp dao ;

    @Override
    public String login(UserInfo user) {
        System.out.println(user.getUserId());
        if(isUser(user.getUserId())){
            UserInfo userInfo = dao.searchUserByUserInfo(user);
            return userInfo.getUserId();
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
    public void playerInit(String id){

        PlayerInfo player = new PlayerInfo();
        player.setUserId(id);
        player.setUserName("test");
        player.setRank(10);
        //数据库初始化玩家信息
        if(0!=dao.insertPlayer(player)){
            //redis缓存一份
            RedisUtil.updatePlayer(player);
        }

    }

    public boolean updatePlayer(PlayerInfo player){
        return dao.updatePlayer(player) != 0;
    }

    public PlayerInfo getPlayer(String userId){
        return dao.getPlayer(userId);
    }

}
