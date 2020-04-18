package com.easyArch.utils.config;

import com.easyArch.invoker.MessageInvoker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.easyArch.*")
public class NettyConfig {

    @Autowired
    private MessageInvoker messageInvoker;

}
