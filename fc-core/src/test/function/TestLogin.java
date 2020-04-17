package function;

import com.easyArch.entity.PlayerInfo;
import com.easyArch.entity.UserInfo;
import com.easyArch.net.model.CODE;
import com.easyArch.net.model.Message;

import java.util.concurrent.CountDownLatch;

public class TestLogin {

    static volatile PlayerInfo player ;

    public static void main(String[] args) {
        final CountDownLatch cdl = new CountDownLatch(1);

        NettyClient client = new NettyClient();

        Message message = new Message();
        message.setMsgCode(CODE.LOGIN);

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId("18539403150");
        userInfo.setUserPwd("123456");
        message.setObj(userInfo);

        new Thread(new Runnable() {
            @Override
            public void run() {
                client.sendMessage(message);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cdl.countDown();
            }
        }).start();

        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(player);

    }
}
