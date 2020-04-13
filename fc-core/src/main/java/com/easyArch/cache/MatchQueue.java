package com.easyArch.cache;

import com.easyArch.entity.Attribute;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 消息队列缓存
 * 系统推送
 */
@Component
public class MatchQueue {

    /**
     * <战斗力,线性阻塞队列>
     */
    public static Map<Integer, LinkedBlockingQueue<Attribute>> matchMap = new ConcurrentHashMap<>();



}
