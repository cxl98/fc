package com.easyArch.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisConf {
    private static MybatisConf mm= new MybatisConf();
    private static SqlSessionFactory sqlSessionFactory;
    private MybatisConf(){
        // 读取配置文件
        InputStream inputStream = null;
        try {
            String resource = "mybatis/mybatis.xml";
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 构建sqlSessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 获取sqlSession
    }

    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }
}
