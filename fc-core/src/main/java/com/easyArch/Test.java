package com.easyArch;

import com.easyArch.entity.PlayerInfo;
import com.easyArch.net.model.CODE;
import com.easyArch.net.model.Message;

import java.util.concurrent.CountDownLatch;

public class Test {

    public static volatile int returnCode;

    public static void main(String[] args) {
        final CountDownLatch cdl = new CountDownLatch(1);

        NettyClient client = new NettyClient();

        Message message = new Message();
        message.setMsgCode(CODE.LOGIN);

//        message.setMsgCode(CODE.MATCH);
//
        PlayerInfo p1 = new PlayerInfo();
        p1.setUserId("test1");
        p1.setRank(10);

//        PlayerInfo p2 = new PlayerInfo();
//        p2.setUserId("test2");
//        p2.setRank(50);
//
//        PlayerInfo p3 = new PlayerInfo();
//        p3.setUserId("test3");
//        p3.setRank(43);
////
//        PlayerInfo p4 = new PlayerInfo();
//        p4.setUserId("test4");
//        p4.setRank(16);
//
//        PlayerInfo p5 = new PlayerInfo();
//        p5.setUserId("test5");
//        p5.setRank(25);

        message.setObj(p1);
//        message.setObj(p2);
//        message.setObj(p3);
//        message.setObj(p4);
//        message.setObj(p5);

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
                message.setObj("test1");
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
                        m.setObj("test1");
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
