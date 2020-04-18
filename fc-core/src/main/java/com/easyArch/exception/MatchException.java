package com.easyArch.exception;

import com.easyArch.net.model.CODE;
import com.easyArch.net.model.Message;

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
