package com.easyarch.dao.imp;


<<<<<<< HEAD:fc-core/src/main/java/com/easyArch/dao/imp/UserDaoImp.java
import com.easyArch.dao.UserDao;
import com.easyArch.entity.PlayerInfo;
import com.easyArch.entity.UserInfo;
import com.easyArch.utils.MybatisConf;
import org.apache.ibatis.session.SqlSession;

public class UserDaoImp implements UserDao {

    private SqlSession session = MybatisConf.getSqlSession();
    @Override
    public int searchById(String id) {
        return session.selectOne("searchById",id);
=======
import com.easyarch.dao.UserDao;
import com.easyarch.model.PlayerInfo;
import com.easyarch.model.UserInfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserDaoImp implements UserDao {

    private SqlSession sqlSession;

    @Autowired
    private void setSqlSession(SqlSession sqlSession){
        this.sqlSession = sqlSession;
    }

    public UserDaoImp(){

    }


    @Override
    public int searchById(String id) {
        return sqlSession.selectOne("searchById",DigestUtils.md5Hex(id));
>>>>>>> e9e232c31600fc0c463fbcdcee7ab33752014b6e:fc-core/src/main/java/com/easyarch/dao/imp/UserDaoImp.java
    }

    @Override
    public int searchByName(String name) {
        return sqlSession.selectOne("searchByName",name);
    }

    @Override
    public UserInfo searchUserById(String id) {
<<<<<<< HEAD:fc-core/src/main/java/com/easyArch/dao/imp/UserDaoImp.java
        return session.selectOne("searchUserById",id);
=======
        return sqlSession.selectOne("searchUserById",DigestUtils.md5Hex(id));
>>>>>>> e9e232c31600fc0c463fbcdcee7ab33752014b6e:fc-core/src/main/java/com/easyarch/dao/imp/UserDaoImp.java
    }

    @Override
    public int insertUser(UserInfo user) {
<<<<<<< HEAD:fc-core/src/main/java/com/easyArch/dao/imp/UserDaoImp.java
        int x = session.insert("insertUser",user);
        session.commit();
=======
        int x = sqlSession.insert("insertUser",setMd5Hex(user));
        sqlSession.commit();
>>>>>>> e9e232c31600fc0c463fbcdcee7ab33752014b6e:fc-core/src/main/java/com/easyarch/dao/imp/UserDaoImp.java
        return x;
    }

    @Override
    public UserInfo searchUserByUserInfo(UserInfo user) {
<<<<<<< HEAD:fc-core/src/main/java/com/easyArch/dao/imp/UserDaoImp.java
        return session.selectOne("searchUserByUserInfo",user);
=======
        UserInfo userInfo = setMd5Hex(user);
        if(sqlSession.selectOne("searchUserByUserInfo",userInfo)!=null)
            return user;
        else{
            return null;
        }
>>>>>>> e9e232c31600fc0c463fbcdcee7ab33752014b6e:fc-core/src/main/java/com/easyarch/dao/imp/UserDaoImp.java
    }

    @Override
    public int updatePlayer(PlayerInfo player) {
        int x = sqlSession.update("updatePlayer",player);
        sqlSession.commit();
        return x;
    }
<<<<<<< HEAD:fc-core/src/main/java/com/easyArch/dao/imp/UserDaoImp.java
=======

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
>>>>>>> e9e232c31600fc0c463fbcdcee7ab33752014b6e:fc-core/src/main/java/com/easyarch/dao/imp/UserDaoImp.java
}
