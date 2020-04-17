package function;

import com.easyArch.entity.PlayerInfo;
import com.easyArch.entity.UserInfo;
import com.easyArch.net.model.CODE;
import com.easyArch.net.model.Message;
import org.junit.Test;

public class TestClient {

    private static NettyClient client = new NettyClient();

    @Test
    public void regist(){
        Message msg = new Message();
        msg.setMsgCode(CODE.REGIST);
        UserInfo us = new UserInfo();
        us.setUserId("111111");
        us.setUserPwd("123456");
        msg.setObj(us);

        client.sendMessage(msg);
    }

    /**
     * 初次注册登录都会返回一个PlayerInfo信息
     */
    @Test
    public void login(){
        Message msg = new Message();
        msg.setMsgCode(CODE.LOGIN);
        UserInfo us = new UserInfo();
        us.setUserId("111111");
        us.setUserPwd("123456");
        msg.setObj(us);

        client.sendMessage(msg);
    }

    /**
     * 要改信息要把整个playerInfo都发过去...
     * 我还没有写 改单个属性
     * 因为客户端会有一个自己的playerInfo信息,改了之后直接发过去
     */
    @Test
    public void updatePlayer(){
        Message msg = new Message();
        msg.setMsgCode(CODE.UPDATE);
        PlayerInfo playerInfo = new PlayerInfo();
        playerInfo.setMoney(0);
        playerInfo.setWinCount(0);
        playerInfo.setClimbLevel(0);
        playerInfo.setRank(10);
        playerInfo.setUserName("test1");
        playerInfo.setFightCount(0);
        msg.setObj(playerInfo);

        client.sendMessage(msg);
    }

    /**
     * 根据rank的匹配，要测的话要先注册用户,然后改一下rank分
     *
     */
    @Test
    public void match(){
        Message msg = new Message();
        msg.setMsgCode(CODE.MATCH);
        //带上自己的id就行
        msg.setObj("184500237");
        client.sendMessage(msg);
    }

}
