package com.easyarch.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
@Configuration
@PropertySource("classpath:config.properties")
public class MybatisConf {

//    private static MybatisConf mm= new MybatisConf();

    private static SqlSessionFactory sqlSessionFactory;
    /*
    总是读取不到,spring各种坑
     */
    @Value("${mybatisSource}")
    private String source ;
    private SqlSessionFactory sessionFactory;

    @PostConstruct
    public void init() {
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(source);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 构建sqlSessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 获取sqlSession
    }

    @Bean
    public SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }


/**
 * bean 注入的方式还是有问题....
 *
 */


//    @Value("${mybatisSource}")
//    private String source;

//    @Bean
//    public SqlSessionFactory createFactory(){
//        InputStream inputStream = null;
//        try{
//            inputStream = Resources.getResourceAsStream(source);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return new SqlSessionFactoryBuilder().build(inputStream);
//    }
//
//
//    @Bean
//    public SqlSession getSqlSession(SqlSessionFactory sqlSessionFactory){
//        return sqlSessionFactory.openSession();
//    }

}
