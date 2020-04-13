package com.easyArch.net.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Message implements Serializable {
    private int msgCode;   //消息代码  用于分辨处理
    private Object obj;

    public Message(){

    }
    public Message(int code,Object obj){
        this.msgCode = code;
        this.obj = obj;
    }

}
