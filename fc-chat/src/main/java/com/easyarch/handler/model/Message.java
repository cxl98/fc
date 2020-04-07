package com.easyarch.handler.model;

import lombok.Data;

@Data
public class Message<T> {
    private int msgCode;   //消息代码  用于分辨处理
    private T obj;

}
