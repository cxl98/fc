package com.easyArch.model;


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

    public int getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(int msgCode) {
        this.msgCode = msgCode;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
