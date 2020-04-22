package com.easyarch.exception;

import com.easyarch.model.Message;
import com.easyarch.model.code.CODE;

public class MatchException extends Exception{

    public Message sendException(){
        Message msg = new Message();
        msg.setMsgCode(CODE.RETRY);
        msg.setObj(getMessage());
        return msg;
    }

    public MatchException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
