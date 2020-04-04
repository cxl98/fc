package com.easyarch.entity;

import lombok.Data;

@Data
public class SendMessage {
    private String fromId;     //发送人
    private ChatType type;      //私聊或者群聊

    private String toGroupId;  //发送给哪个群

    private String toId;    //发给谁
    private String msg;     //发送信息
}
