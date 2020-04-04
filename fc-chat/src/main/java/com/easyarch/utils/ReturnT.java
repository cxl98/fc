package com.easyarch.utils;

import lombok.Data;

@Data
public class ReturnT<T> {
    public static final int OK = 200;
    public static final int ERROR = 500;

    private int code;
    private T contains;

    public ReturnT(int code,T t){
        this.code = code;
        this.contains = t;
    }

}
