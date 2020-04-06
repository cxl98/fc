package com.easyarch.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class FriendRequest implements Serializable {
    private String fromId;
    private String toId;
    private short type;   //处理结果

    private String msg;


}
