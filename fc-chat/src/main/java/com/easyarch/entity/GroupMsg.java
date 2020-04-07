package com.easyarch.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GroupMsg implements Serializable {
    private static final long serialVersionUID=2L;

    private String groupId;    //组id
    private String groupName;  //组名
    private List<String> membersId;  //组成员
    private List<String> managers;   //管理员

    private short status;

//    private

}
