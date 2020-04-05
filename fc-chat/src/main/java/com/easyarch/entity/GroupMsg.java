package com.easyarch.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GroupMsg implements Serializable {
    private static final long serialVersionUID=2L;

    private String groupId;
    private String groupName;
    private List<UserInfo> members;

}
