package function;

import com.easyArch.entity.UserInfo;
import com.easyArch.net.model.CODE;
import com.easyArch.net.model.Message;

import java.util.concurrent.CountDownLatch;

public class TestFight {
    public static void main(String[] args) {
        final CountDownLatch cdl = new CountDownLatch(1);

        NettyClient client = new NettyClient();

        Message message = new Message();
        message.setMsgCode(CODE.LOGIN);

        UserInfo p1 = new UserInfo();
        p1.setUserId("18539403150");
        p1.setUserPwd("123456");

        message.setObj(p1);


        new Thread(new Runnable() {
            @Override
            public void run() {
                client.sendMessage(message);
                cdl.countDown();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cdl.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                message.setMsgCode(CODE.MATCH);
                message.setObj("18539403150");
                client.sendMessage(message);
            }
        }).start();
    }
}
