package com.easyarch;

import com.easyarch.entity.SendMessage;
import com.easyarch.entity.Type.MsgType;
import com.easyarch.entity.UserInfo;
import com.easyarch.handler.model.CODE;
import com.easyarch.handler.model.Message;

public class ClientTest {
    public static void main(String[] args) {

         NettyClient client = new NettyClient();

         Message message = new Message();
         message.setMsgCode(CODE.MESSAGE);
         SendMessage msg = new SendMessage();
         msg.setFromId("184500237");
         msg.setType(MsgType.ALL);
         msg.setMsg("Hello!");
         message.setObj(msg);
//         client.sendMessage(message);



//            UserInfo us = new UserInfo();
//            us.setUserId("333333");
//            us.setUserPwd("123456");
//            us.setUserName("test3");
//            message.setMsgCode(CODE.MESSAGE);
//            message.setObj(us);

         client.sendMessage(message);

    }
}
