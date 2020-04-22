package com.easyarch;

import com.easyarch.net.NettyServer;
import com.easyarch.utils.config.NettyConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ServerMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(NettyConfig.class);

        context.getBean(NettyServer.class);
    }
}
