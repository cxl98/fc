package com.easyArch.utils;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class RedisUtil {

    public static RedisCommands<String, String> getRedisConnection() {
        RedisClient redisClient = RedisClient.create("redis://localhost");
        StatefulRedisConnection<String, String> connect = redisClient.connect();
        return connect.sync();
    }
}
