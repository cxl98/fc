package com.easyArch.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {
    private String userId;
    private String userPwd;
}
