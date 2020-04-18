package com.easyArch;

import com.easyArch.invoker.MessageInvoker;
import com.easyArch.net.NettyServer;
import com.easyArch.utils.config.NettyConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ServerMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(NettyConfig.class);
        NettyServer server = context.getBean(NettyServer.class);
        server.start();

//        MessageInvoker invoker = context.getBean(MessageInvoker.class);

    }
}
