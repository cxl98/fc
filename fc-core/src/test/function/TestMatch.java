package function;

import com.easyarch.model.Message;
import com.easyarch.model.UserInfo;
import com.easyarch.model.code.CODE;

import java.util.concurrent.CountDownLatch;

public class TestMatch {

    public static volatile int returnCode;

    public static void main(String[] args) {


        NettyClient client = new NettyClient();
        Message message = new Message();

        message.setMsgCode(CODE.LOGIN);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId("iiii");
        userInfo.setUserPwd("123456");
        message.setObj(userInfo);
        client.sendMessage(message);

        try {
            Thread.sleep(100);
            Message m = new Message();
            m.setMsgCode(CODE.MATCH);
            m.setObj("iiii");
            client.sendMessage(m);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        new Thread(new Runnable() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                while (returnCode!=CODE.ENEMY){
                    if(2*60*1000==(System.currentTimeMillis()-start)){
                        Message m = new Message();
                        m.setMsgCode(CODE.MATCH_FAIL);

                        m.setObj("iiii");

                        client.sendMessage(m);
                        break;
                    }
                }
                System.out.println("Time out!");

            }
        }).start();

    }
}
