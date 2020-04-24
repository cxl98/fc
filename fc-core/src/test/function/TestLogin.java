package function;

import com.easyarch.model.Message;
import com.easyarch.model.UserInfo;
import com.easyarch.model.code.CODE;

import java.util.concurrent.CountDownLatch;

public class TestLogin {

    static volatile PlayerInfo player ;

    public static void main(String[] args) {
        final CountDownLatch cdl = new CountDownLatch(1);

        NettyClient client = new NettyClient();

        Message message = new Message();
        message.setMsgCode(CODE.LOGIN);

        UserInfo userInfo = new UserInfo();
<<<<<<< HEAD
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
=======
        userInfo.setUserId("iiii");
        userInfo.setUserPwd("123456");
        message.setObj(userInfo);
        client.sendMessage(message);



//        UserInfo userInfo = new UserInfo();
//        userInfo.setUserId("18539403150");
//        userInfo.setUserPwd("123456");
//        message.setObj(userInfo);
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                client.sendMessage(message);
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                cdl.countDown();
//            }
//        }).start();
//
//        try {
//            cdl.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(player);
>>>>>>> e9e232c31600fc0c463fbcdcee7ab33752014b6e

    }
}
