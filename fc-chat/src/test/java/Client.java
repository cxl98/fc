import com.easyarch.dao.UserDao;
import com.easyarch.entity.UserInfo;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import org.junit.Test;


import java.io.InputStream;


public class Client {
    public static String host = "localhost";
    public static int port = 8888;

    public void connect(){

    }
    private static SqlSession session;

    private static UserDao mapper=null;

    static {

        //mybatis的配置文件
        String resource = "mybatis/mybatis.xml";

        //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = Client.class.getResourceAsStream(resource);

       //构建sqlSession的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);

        //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
        //Reader reader = Resources.getResourceAsReader(resource);
        //构建sqlSession的工厂
        //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //创建能执行映射文件中sql的sqlSession

        session = sessionFactory.openSession();
        mapper = session.getMapper(UserDao.class);

    }

    @Test
    public void regist(){
        String userId = "184500237";
        String pwd = "123456";
        String userName = "sujia";
        UserInfo uf = new UserInfo();
        uf.setUserId(userId);
        uf.setUserPwd(pwd);
        uf.setUserName(userName);
        mapper.insertUser(uf);
    }

}
