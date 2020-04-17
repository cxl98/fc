package function;

import com.easyArch.entity.UserInfo;
import com.easyArch.net.model.CODE;
import com.easyArch.net.model.Message;

import java.util.concurrent.CountDownLatch;

public class TestMatch {

    public static volatile int returnCode;

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



        new Thread(new Runnable() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                while (returnCode!=CODE.ENEMY){
                    if(2*60*1000==(System.currentTimeMillis()-start)){
                        Message m = new Message();
                        m.setMsgCode(CODE.MATCH_FAIL);

//                        m.setObj("test1");
//                        m.setObj("test2");
//                        m.setObj("test3");
                        m.setObj("18539403150");
//                        m.setObj("test5");

                        client.sendMessage(m);
                        break;
                    }
                }
                System.out.println("Time out!");

            }
        });

    }
}
