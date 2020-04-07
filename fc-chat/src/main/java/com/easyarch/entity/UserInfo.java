package com.easyarch.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {
    private static final long serialVersionUID=2L;
    private String userId;
    private String userPwd;
    private String userName;
}
