package com.easyArch.model;


import lombok.Data;

import java.io.Serializable;


@Data
public class UserInfo implements Serializable {
    private String userId;
    private String userPwd;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    @Override
    public String toString(){
        return "UserInfo(userId:" +userId+
                ",userPwd:" +userPwd+
                ")";
    }
}
