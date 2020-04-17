package function;

import com.easyArch.entity.PlayerInfo;
import com.easyArch.net.model.CODE;
import com.easyArch.net.model.Message;

public class TestLogin {

    public static void main(String[] args) {

        NettyClient client = new NettyClient();

        Message message = new Message();
        message.setMsgCode(CODE.UPDATE);
        PlayerInfo playerInfo = new PlayerInfo();
        playerInfo.setUserId("18539403150");
        playerInfo.setFightCount(0);
        playerInfo.setUserName("test2");
        playerInfo.setClimbLevel(0);
        playerInfo.setRank(16);
        playerInfo.setWinCount(0);
        playerInfo.setMoney(0);

//        UserInfo userInfo = new UserInfo();
//        userInfo.setUserId("18539403150");
//        userInfo.setUserPwd("123456");
        message.setObj(playerInfo);

        client.sendMessage(message);
    }
}
