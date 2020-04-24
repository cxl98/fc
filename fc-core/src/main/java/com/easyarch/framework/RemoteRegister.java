package com.easyarch.framework;

import com.alibaba.fastjson.JSON;

import io.lettuce.core.api.sync.RedisCommands;
import com.easyarch.utils.RedisUtil;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class RemoteRegister {


    public static void register(String interfaceName, URL url) {
        RedisCommands<String, String> commands = RedisUtil.getRedisConnection();
        commands.lpush(interfaceName, JSON.toJSONString(url));
    }

    public static URL get(String interfaceName) {
        RedisCommands<String, String> commands = RedisUtil.getRedisConnection();
        List<String> urlList = commands.lrange(interfaceName, 0, -1);

        List<URL> currentUrls = urlList.stream().map(e -> JSON.parseObject(e, URL.class)).collect(Collectors.toList());
        Collections.shuffle(currentUrls);

        return currentUrls.get(0);
    }
}
