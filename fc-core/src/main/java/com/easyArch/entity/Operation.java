package com.easyArch.entity;

import lombok.Data;

@Data
public class Operation {

    private Attribute attribute;
    private int action;
    private boolean code;  //0 初始化 1 正在打

}
