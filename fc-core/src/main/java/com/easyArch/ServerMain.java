package com.easyArch;

import com.easyArch.net.NettyServer;
import com.easyArch.utils.config.NettyConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ServerMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(NettyConfig.class);

        context.getBean(NettyServer.class);
    }
}
