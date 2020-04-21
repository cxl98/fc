package com.easyArch.dao.imp;


import com.easyArch.dao.UserDao;
import com.easyArch.model.PlayerInfo;
import com.easyArch.model.UserInfo;
import com.easyArch.utils.MybatisConf;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;



public class UserDaoImp implements UserDao {


    private SqlSession sqlSession = MybatisConf.getSqlSession();


    @Override
    public int searchById(String id) {
        return sqlSession.selectOne("searchById",DigestUtils.md5Hex(id));
    }

    @Override
    public int searchByName(String name) {
        return sqlSession.selectOne("searchByName",name);
    }

    @Override
    public UserInfo searchUserById(String id) {
        return sqlSession.selectOne("searchUserById",DigestUtils.md5Hex(id));
    }

    @Override
    public int insertUser(UserInfo user) {
        int x = sqlSession.insert("insertUser",setMd5Hex(user));
        sqlSession.commit();
        return x;
    }

    @Override
    public UserInfo searchUserByUserInfo(UserInfo user) {
        UserInfo userInfo = setMd5Hex(user);
        if(sqlSession.selectOne("searchUserByUserInfo",userInfo)!=null)
            return user;
        else{
            return null;
        }
    }

    @Override
    public int updatePlayer(PlayerInfo player) {
        int x = sqlSession.update("updatePlayer",player);
        sqlSession.commit();
        return x;
    }

    public PlayerInfo getPlayer(String userId){
        System.out.println(userId);
        return sqlSession.selectOne("getPlayer",userId);
    }

    public int insertPlayer(PlayerInfo player){
        int x = sqlSession.insert("insertPlayer",player);
        sqlSession.commit();
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
