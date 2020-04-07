package com.easyArch.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Message implements Serializable {

    private String userFrom;   //可以是来自系统的
    private String context;
    private PrivilegeLevel mesLevel;      //消息等级


}
