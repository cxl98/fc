import com.easyarch.dao.UserDao;
import com.easyarch.entity.UserInfo;


import com.easyarch.handler.NettyEncoder;
import com.easyarch.utils.ProtoStuffSerializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.InputStream;


public class Client {


    @Test
    public void connect(){
        try {
            Bootstrap client = new Bootstrap();

            EventLoopGroup group = new NioEventLoopGroup();
            client.group(group);

            client.channel(NioSocketChannel.class);

            client.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new DelimiterBasedFrameDecoder(
                            Integer.MAX_VALUE, Delimiters.lineDelimiter()[0]));

//                    ch.pipeline().addLast(new NettyEncoder(UserInfo.class,new ProtoStuffSerializer()));
                    ch.pipeline().addLast(new StringDecoder());
                    ch.pipeline().addLast(new StringEncoder());

                    ch.pipeline().addLast(new SimpleClientHandler());
                }
            });

            ChannelFuture future = client.connect("localhost",8888).sync();

            String userId = "184500237";
            String pwd = "123456";
            String userName = "sujia";
            UserInfo uf = new UserInfo();
            uf.setUserId(userId);
            uf.setUserPwd(pwd);
            uf.setUserName(userName);
//            System.out.println(uf.getUserName());
            future.channel().writeAndFlush(uf);
            System.out.println("发送");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }






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
        SqlSession session = sessionFactory.openSession();
        mapper = session.getMapper(UserDao.class);

    }


//    public void regist(){
//        String userId = "184500237";
//        String pwd = "123456";
//        String userName = "sujia";
//        UserInfo uf = new UserInfo();
//        uf.setUserId(userId);
//        uf.setUserPwd(pwd);
//        uf.setUserName(userName);
//        mapper.insertUser(uf);
//    }


//    @After
//    public void disConnect(){
//
//    }


}
