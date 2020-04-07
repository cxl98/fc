import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class RedisTest {
    private RedisClient client;
    private StatefulRedisConnection<String,String> connection;

    @Before
    public void init(){
        //单机测试
        RedisURI uri = RedisURI.builder()
                .withHost("localhost")
                .withPort(6379)
                .withTimeout(Duration.of(10, ChronoUnit.SECONDS))
                .build();                                         //创建单机连接信息

        client = RedisClient.create(uri);                         //创建客户端
        connection = client.connect();                            //创建线程安全
//        RedisCommands<String ,String> redisCommands = connection.sync();      //创建同步命令
    }

    @Test
    public void testSetGet(){
        RedisCommands<String,String> redisCommands = connection.sync();
        String setK = "testSet";
        String setV = "aaa";
        redisCommands.set(setK,setV);
        String v = redisCommands.get(setK);
        Assert.assertEquals(setV,v);
    }

    @Test
    public void testHset(){
        RedisCommands<String,String> redisCommands = connection.sync();
        Map<String,String> mv = new HashMap<>();
        mv.put("1","a");
        mv.put("2","b");
        mv.put("3","c");
        redisCommands.hmset("testHset",mv);

//        不能直接获得一个map,需要有field
//        Assert.assertEquals(mv,redisCommands.hmget("testHset"));

//        java.lang.AssertionError:
//        Expected :{1=a, 2=b, 3=c}
//        Actual   :[KeyValue[1, a], KeyValue[2, b], KeyValue[3, c]]
//        Assert.assertEquals(mv,redisCommands.hmget("testHset","1","2","3"));

        Assert.assertEquals("a",redisCommands.hget("testHset","1"));
    }

    @Test
    public void testList(){
        RedisCommands<String,String> redisCommands = connection.sync();

    }



    @After
    public void close(){
        connection.close();
        client.shutdown();
    }


}
