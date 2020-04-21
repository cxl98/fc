package function;

import com.easyArch.model.Message;
import com.easyArch.model.UserInfo;
import com.easyArch.model.code.CODE;

import java.util.concurrent.CountDownLatch;

public class TestMatch {

    public static volatile int returnCode;

    public static void main(String[] args) {
        final CountDownLatch cdl = new CountDownLatch(1);

        NettyClient client = new NettyClient();

        Message message = new Message();
        message.setMsgCode(CODE.LOGIN);

        UserInfo p1 = new UserInfo();
        p1.setUserId("aaaa");
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
                message.setObj("aaaa");
                client.sendMessage(message);
            }
        }).start();



        new Thread(new Runnable() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                try {
                    cdl.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (returnCode!=CODE.ENEMY){
                    if(2*60*1000==(System.currentTimeMillis()-start)){
                        Message m = new Message();
                        m.setMsgCode(CODE.MATCH_FAIL);

//                        m.setObj("test1");
//                        m.setObj("test2");
//                        m.setObj("test3");
                        m.setObj("aaaa");
//                        m.setObj("test5");

                        client.sendMessage(m);
                        break;
                    }
                }
                System.out.println("Time out!");

            }
        }).start();

    }
}
