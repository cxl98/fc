package com.easyarch.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class SendMessage implements Serializable {
    private static final long serialVersionUID=2L;
    private String fromId;     //发送人
    private MsgType type;      //私聊或者群聊

    private String toGroupId;  //发送给哪个群

    private String toId;    //发给谁
    private String msg;     //发送信息
}
