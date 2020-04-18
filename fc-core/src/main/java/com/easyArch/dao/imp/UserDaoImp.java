package com.easyArch.dao.imp;


import com.easyArch.dao.UserDao;
import com.easyArch.entity.PlayerInfo;
import com.easyArch.entity.UserInfo;
import com.easyArch.utils.MybatisConf;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;


@Repository
public class UserDaoImp implements UserDao {

    private SqlSession session = MybatisConf.getSqlSession();

    @Override
    public int searchById(String id) {
        return session.selectOne("searchById",DigestUtils.md5Hex(id));
    }

    @Override
    public int searchByName(String name) {
        return session.selectOne("searchByName",name);
    }

    @Override
    public UserInfo searchUserById(String id) {
        return session.selectOne("searchUserById",DigestUtils.md5Hex(id));
    }

    @Override
    public int insertUser(UserInfo user) {
        int x = session.insert("insertUser",setMd5Hex(user));
        session.commit();
        return x;
    }

    @Override
    public UserInfo searchUserByUserInfo(UserInfo user) {
        UserInfo userInfo = setMd5Hex(user);
        if(session.selectOne("searchUserByUserInfo",userInfo)!=null)
            return user;
        else{
            return null;
        }
    }

    @Override
    public int updatePlayer(PlayerInfo player) {
        int x = session.update("updatePlayer",player);
        session.commit();
        return x;
    }

    public PlayerInfo getPlayer(String userId){
        return session.selectOne("getPlayer",userId);
    }

    public int insertPlayer(PlayerInfo player){
        int x = session.insert("insertPlayer",player);
        session.commit();
        return x;
    }

    private UserInfo setMd5Hex(UserInfo user){
        UserInfo userInfo = new UserInfo();
        String pwd = DigestUtils.md5Hex(user.getUserPwd());
        String id = DigestUtils.md5Hex(user.getUserId());
        userInfo.setUserId(id);
        userInfo.setUserPwd(pwd);
        return userInfo;
    }
}
