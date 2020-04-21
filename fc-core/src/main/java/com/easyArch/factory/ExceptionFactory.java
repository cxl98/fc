package com.easyArch.factory;

import com.easyArch.net.model.Message;
import org.springframework.stereotype.Component;

@Component
public class ExceptionFactory extends MessageAbstractFactory {

    @Override
    public Message handle(Message msg) {
        return null;
    }
}
