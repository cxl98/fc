import com.easyarch.NettyClient;
import com.easyarch.entity.SendMessage;
import com.easyarch.entity.Type.MsgType;
import com.easyarch.entity.UserInfo;
import com.easyarch.handler.model.CODE;
import com.easyarch.handler.model.Message;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Client {
    private NettyClient client;

    @Before
    public void connect(){

        client = new NettyClient();
    }

    @Test
    public void regist(){
        UserInfo us = new UserInfo();
        us.setUserId("111111");
        us.setUserPwd("123456");
        us.setUserName("test1");
        Message message = new Message(CODE.REGIST,us);
//        client.sendMessage(message);
    }

    @Test
    public void login(){

        UserInfo us = new UserInfo();
        us.setUserId("222222");
        us.setUserPwd("123456");
        us.setUserName("test2");
        Message message = new Message(CODE.LOGIN,us);
//        client.sendMessage(message);
    }

    @Test
    public void chat(){
        SendMessage m = new SendMessage();
        m.setFromId("xxx");
        m.setType(MsgType.ALL);
        m.setMsg("+++");
        Message message = new Message(CODE.REGIST,m);
//        client.sendMessage(message);
    }


}
